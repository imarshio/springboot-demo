# Spring Framework

## Spring启动间都发生了什么？

https://docs.spring.io/spring-framework/docs/3.0.x/reference/overview.html

接下来我们跟踪源码进入Spring的启动过程，看看Spring启动时都发生了什么？

## 1.1 入口

```java
// 首先，我们从SpringApplication类开始，SpringApplication类是SpringBoot的入口点，SpringBoot启动时，会调用SpringApplication.run方法
// SpringApplication.run方法会调用SpringApplication.run方法，SpringApplication.run方法会调用SpringApplication.run方法，SpringApplication.run方法会调用SpringApplication.run方法，SpringApplication.run方法会调用SpringApplication()
@SpringBootApplication
public class BootProcessApplication {

    public static void main(String[] args) {
        // 1.1启动入口
        SpringApplication.run(BootProcessApplication.class, args);
    }
}
```

### 1.2 调用SpringApplication.run方法

```java
public static ConfigurableApplicationContext run(Class<?> primarySource, String... args) {
    // 调用SpringApplication.run方法
    return run(new Class[]{primarySource}, args);
}

public static ConfigurableApplicationContext run(Class<?>[] primarySources, String[] args) {
    // 调用SpringApplication.run方法,声明一个SpringApplication对象，对对象的属性进行初始化
    return (new SpringApplication(primarySources)).run(args);
}

public SpringApplication(ResourceLoader resourceLoader, Class<?>... primarySources) {
    this.resourceLoader = resourceLoader;
    Assert.notNull(primarySources, "PrimarySources must not be null");
    this.primarySources = new LinkedHashSet<>(Arrays.asList(primarySources));
    // 获取应用类型
    this.webApplicationType = WebApplicationType.deduceFromClasspath();
    setInitializers((Collection) getSpringFactoriesInstances(ApplicationContextInitializer.class));
    setListeners((Collection) getSpringFactoriesInstances(ApplicationListener.class));
    this.mainApplicationClass = deduceMainApplicationClass();
}

// 调用SpringApplication.run方法，这个方法可以让我们看到Spring启动期间大致做了什么事
public ConfigurableApplicationContext run(String... args) {
    // 定义全局的计时器
    StopWatch stopWatch = new StopWatch();
    // 开始计时
    stopWatch.start();
    // 定义Spring应用上下文的变量
    ConfigurableApplicationContext context = null;
    // 定义SpringBoot异常报告栈
    Collection<SpringBootExceptionReporter> exceptionReporters = new ArrayList();
    // 1.3 配置headless mode，详情可以参考下面的注释
    this.configureHeadlessProperty();
    // 1.4 获取SpringBoot监听器并启动监听器
    SpringApplicationRunListeners listeners = this.getRunListeners(args);
    listeners.starting();

    Collection exceptionReporters;
    try {
        // 获取SpringBoot启动参数： []
        ApplicationArguments applicationArguments = new DefaultApplicationArguments(args);
        // 1.5 准备SpringBoot环境变量
        ConfigurableEnvironment environment = this.prepareEnvironment(listeners, applicationArguments);
        this.configureIgnoreBeanInfo(environment);
        // 打印SpringBoot启动banner
        Banner printedBanner = this.printBanner(environment);
        // 1.6 创建Spring应用上下文
        context = this.createApplicationContext();
        exceptionReporters = this.getSpringFactoriesInstances(SpringBootExceptionReporter.class, new Class[]{ConfigurableApplicationContext.class}, context);
        // 2.1 准备SpringBoot上下文，重点1
        this.prepareContext(context, environment, listeners, applicationArguments, printedBanner);
        // 3.1 刷新SpringBoot上下文，重点2
        this.refreshContext(context);
        // 4.1 运行SpringBoot上下文，重点3
        this.afterRefresh(context, applicationArguments);
        stopWatch.stop();
        if (this.logStartupInfo) {
            (new StartupInfoLogger(this.mainApplicationClass)).logStarted(this.getApplicationLog(), stopWatch);
        }

        listeners.started(context);
        this.callRunners(context, applicationArguments);
    } catch (Throwable var10) {
        this.handleRunFailure(context, var10, exceptionReporters, listeners);
        throw new IllegalStateException(var10);
    }

    try {
        listeners.running(context);
        return context;
    } catch (Throwable var9) {
        this.handleRunFailure(context, var9, exceptionReporters, (SpringApplicationRunListeners) null);
        throw new IllegalStateException(var9);
    }
}

```

### 1.3 配置headless mode

```java
// 1.3 配置headless mode
private void configureHeadlessProperty() {
    // 结合上下文，可以知道，这里是给java.awt.headless设置默认值（根据上下文可知）：true
    // java.awt.headless参数的意义是：在没有显示器的、键盘、鼠标等设备的情况下，程序可以运行
    // 可以通过：System.setProperty("java.awt.headless", "true"); 或者添加启动参数：java -Djava.awt.headless=true
    // Headless mode is a system configuration in which the display device, keyboard, or mouse is lacking. 
    // Sounds unexpected, but actually you can perform different operations in this mode, even with graphic data.
    System.setProperty("java.awt.headless", System.getProperty("java.awt.headless", Boolean.toString(this.headless)));
}
```

### 1.4 获取SpringBoot监听器并启动监听器

```java
// 1.4 获取SpringBoot启动监听器
private SpringApplicationRunListeners getRunListeners(String[] args) {
    Class<?>[] types = new Class<?>[]{SpringApplication.class, String[].class};
    // 1.4.1 定义SpringBoot启动监听器
    return new SpringApplicationRunListeners(logger,
            // SpringApplicationRunListener,跟踪源码可以看到，这个接口的实现为EventPublishingRunListener
            getSpringFactoriesInstances(SpringApplicationRunListener.class, types, this, args));
}

// 1.4.2 获取Spring工厂实例
private <T> Collection<T> getSpringFactoriesInstances(Class<T> type, Class<?>[] parameterTypes, Object... args) {
    // 这里获取到的classLoader是AppClassLoader
    ClassLoader classLoader = getClassLoader();
    // Use names and ensure unique to protect against duplicates
    Set<String> names = new LinkedHashSet<>(SpringFactoriesLoader.loadFactoryNames(type, classLoader));
    List<T> instances = createSpringFactoriesInstances(type, parameterTypes, classLoader, args, names);

    AnnotationAwareOrderComparator.sort(instances);
    return instances;
}

/**
 *
 * @param type SpringApplicationRunListener
 * @param parameterTypes SpringApplication.class, String[].class
 * @param classLoader AppClassLoader
 * @param args []
 * @param names class的完整类名
 * @return List<T>
 * @param <T> 类
 */
private <T> List<T> createSpringFactoriesInstances(Class<T> type, Class<?>[] parameterTypes,
                                                   ClassLoader classLoader, Object[] args, Set<String> names) {
    List<T> instances = new ArrayList<>(names.size());
    // 1.4.2.1 遍历SpringBoot启动监听器
    for (String name : names) {
        try {
            Class<?> instanceClass = ClassUtils.forName(name, classLoader);
            Assert.isAssignable(type, instanceClass);
            Constructor<?> constructor = instanceClass.getDeclaredConstructor(parameterTypes);
            // 熟悉的代码，同样的反射构造
            T instance = (T) BeanUtils.instantiateClass(constructor, args);
            instances.add(instance);
        } catch (Throwable ex) {
            throw new IllegalArgumentException("Cannot instantiate " + type + " : " + name, ex);
        }
    }
    return instances;
}

// 1.4.3 初始化SpringBoot启动监听器
class SpringApplicationRunListeners {
    // 注意这里两个类是不一样的，不是递归调用哦
	void starting() {
        // 区别SpringApplicationRunListeners：SpringApplicationRunListener，可以这么理解，
        // SpringApplicationRunListeners是SpringApplicationRunListener的集合（代理）
 		for (SpringApplicationRunListener listener : this.listeners) {
			listener.starting();
		}
	}
}

public interface SpringApplicationRunListener {
    default void starting() {
	}
}

public class EventPublishingRunListener implements SpringApplicationRunListener, Ordered {
    
	@Override
	public void starting() {
        // 广播一个新的事件：new ApplicationStartingEvent(this.application, this.args)，表示SpringBoot应用正在启动
		this.initialMulticaster.multicastEvent(new ApplicationStartingEvent(this.application, this.args));
	}    
}
```


有关监听器这里还有一些细节，其方法的具体意义跟其方法命名一致，即字面意思。

```java
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.support.SpringFactoriesLoader;

@SuppressWarnings("all")
public interface SpringApplicationRunListener {

	/**
	 * Called immediately when the run method has first started. Can be used for very
	 * early initialization.
     * 
     * 翻译过来就是：在应用启动的时候调用，用于最开始的初始化
	 */
	default void starting() {
	}

	/**
	 * Called once the environment has been prepared, but before the
	 * {@link ApplicationContext} has been created.
	 * @param environment the environment
	 */
	default void environmentPrepared(ConfigurableEnvironment environment) {
	}

	/**
	 * Called once the {@link ApplicationContext} has been created and prepared, but
	 * before sources have been loaded.
	 * @param context the application context
	 */
	default void contextPrepared(ConfigurableApplicationContext context) {
	}

	/**
	 * Called once the application context has been loaded but before it has been
	 * refreshed.
	 * @param context the application context
	 */
	default void contextLoaded(ConfigurableApplicationContext context) {
	}

	/**
	 * The context has been refreshed and the application has started but
	 * {@link CommandLineRunner CommandLineRunners} and {@link ApplicationRunner
	 * ApplicationRunners} have not been called.
	 * @param context the application context.
	 * @since 2.0.0
	 */
	default void started(ConfigurableApplicationContext context) {
	}

	/**
	 * Called immediately before the run method finishes, when the application context has
	 * been refreshed and all {@link CommandLineRunner CommandLineRunners} and
	 * {@link ApplicationRunner ApplicationRunners} have been called.
	 * @param context the application context.
	 * @since 2.0.0
	 */
	default void running(ConfigurableApplicationContext context) {
	}

	/**
	 * Called when a failure occurs when running the application.
	 * @param context the application context or {@code null} if a failure occurred before
	 * the context was created
	 * @param exception the failure
	 * @since 2.0.0
	 */
	default void failed(ConfigurableApplicationContext context, Throwable exception) {
	}

}
```

### 1.5 准备SpringBoot环境变量

```java
private ConfigurableEnvironment prepareEnvironment(SpringApplicationRunListeners listeners,
        ApplicationArguments applicationArguments) {
    // Create and configure the environment
    ConfigurableEnvironment environment = getOrCreateEnvironment();
    // 配置环境变量
    configureEnvironment(environment, applicationArguments.getSourceArgs());
    ConfigurationPropertySources.attach(environment);
    // 监听器发布 程序环境准备已完成
    listeners.environmentPrepared(environment);
    bindToSpringApplication(environment);
    if (!this.isCustomEnvironment) {
        environment = new EnvironmentConverter(getClassLoader()).convertEnvironmentIfNecessary(environment,
                deduceEnvironmentClass());
    }
    ConfigurationPropertySources.attach(environment);
    return environment;
}

private ConfigurableEnvironment getOrCreateEnvironment() {
    if (this.environment != null) {
        return this.environment;
    }
    switch (this.webApplicationType) {
        case SERVLET:
            // Servlet环境，构造器
            return new StandardServletEnvironment();
        case REACTIVE:
            return new StandardReactiveWebEnvironment();
        default:
            return new StandardEnvironment();
    }
}
```

### 1.6 创建Spring应用上下文

```java

// 上下文变量，初始化为null
private Class<? extends ConfigurableApplicationContext> applicationContextClass;

// 1.10 创建Spring应用上下文
@SuppressWarnings("all")
protected ConfigurableApplicationContext createApplicationContext() {
    Class<?> contextClass = this.applicationContextClass;
    // 初次创建时applicationContextClass为null
    if (contextClass == null) {
        try {
            // 启动时，因为带了spring-boot-starter-web依赖，所以在启动时，webApplicationType为SERVLET
            switch (this.webApplicationType) {
                case SERVLET:
                    contextClass = Class.forName(DEFAULT_SERVLET_WEB_CONTEXT_CLASS);
                    break;
                case REACTIVE:
                    contextClass = Class.forName(DEFAULT_REACTIVE_WEB_CONTEXT_CLASS);
                    break;
                default:
                    contextClass = Class.forName(DEFAULT_CONTEXT_CLASS);
            }
        } catch (ClassNotFoundException ex) {
            throw new IllegalStateException(
                    "Unable create a default ApplicationContext, please specify an ApplicationContextClass", ex);
        }
    }
    // 实例化上下文，通过反射去调用方法的构造方法进行实例化
    return (ConfigurableApplicationContext) BeanUtils.instantiateClass(contextClass);
}
```

## 2.1 准备SpringBoot上下文，重点

```java
private void prepareContext(ConfigurableApplicationContext context, ConfigurableEnvironment environment,
        SpringApplicationRunListeners listeners, ApplicationArguments applicationArguments, Banner printedBanner) {
    // 设置上下文环境
    context.setEnvironment(environment);
    // 2.2 前置处理
    postProcessApplicationContext(context);
    // 2.3 程序上下文初始化
    applyInitializers(context);
    listeners.contextPrepared(context);
    if (this.logStartupInfo) {
        logStartupInfo(context.getParent() == null);
        logStartupProfileInfo(context);
    }
    // Add boot specific singleton beans，添加指定的单例bean，获取bean工厂
    ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
    beanFactory.registerSingleton("springApplicationArguments", applicationArguments);
    if (printedBanner != null) {
        beanFactory.registerSingleton("springBootBanner", printedBanner);
    }
    if (beanFactory instanceof DefaultListableBeanFactory) {
        ((DefaultListableBeanFactory) beanFactory)
                .setAllowBeanDefinitionOverriding(this.allowBeanDefinitionOverriding);
    }
    if (this.lazyInitialization) {
        context.addBeanFactoryPostProcessor(new LazyInitializationBeanFactoryPostProcessor());
    }
    // Load the sources
    Set<Object> sources = getAllSources();
    Assert.notEmpty(sources, "Sources must not be empty");
    load(context, sources.toArray(new Object[0]));
    listeners.contextLoaded(context);
}
```

### 2.2 应用上下文的前置处理
```java
// 应用上下文的前置处理
protected void postProcessApplicationContext(ConfigurableApplicationContext context) {
    // bean name 生成器
    if (this.beanNameGenerator != null) {
        context.getBeanFactory().registerSingleton(AnnotationConfigUtils.CONFIGURATION_BEAN_NAME_GENERATOR,
                this.beanNameGenerator);
    }
    // 资源加载器
    if (this.resourceLoader != null) {
        if (context instanceof GenericApplicationContext) {
            ((GenericApplicationContext) context).setResourceLoader(this.resourceLoader);
        }
        if (context instanceof DefaultResourceLoader) {
            ((DefaultResourceLoader) context).setClassLoader(this.resourceLoader.getClassLoader());
        }
    }
    // 添加转换服务
    if (this.addConversionService) {
        context.getBeanFactory().setConversionService(ApplicationConversionService.getSharedInstance());
    }
}
```

### 2.3 初始化容器前的处理
```java
// 如果我们有定制化需求，我们也可以自己实现这个接口
protected void applyInitializers(ConfigurableApplicationContext context) {
    // 通过getInitializers() 我们可以知道Springboot内置的初始化器都有哪些
    for (ApplicationContextInitializer initializer : getInitializers()) {
        Class<?> requiredType = GenericTypeResolver.resolveTypeArgument(initializer.getClass(),
                ApplicationContextInitializer.class);
        Assert.isInstanceOf(requiredType, context, "Unable to call initializer.");
        initializer.initialize(context);
    }
}

// 接口
public interface ApplicationContextInitializer<C extends ConfigurableApplicationContext> {

	/**
	 * Initialize the given application context.
	 * @param applicationContext the application to configure
	 */
	void initialize(C applicationContext);

}
```

补充
```java

```

## 3.1 刷新SpringBoot上下文，重点


## 4.1 运行SpringBoot上下文，重点



> TIPS : BeanUtils.instantiateClass()，是一个很不错的Spring专用的工具类


参考

- [Headless配置]https://www.oracle.com/technical-resources/articles/javase/headless.html

## AOP

https://docs.spring.io/spring-framework/docs/3.0.x/reference/aop.html

## IoC

https://docs.spring.io/spring-framework/docs/3.0.x/reference/new-in-3.html#new-feature-java-config

## SpEL

### 用法

https://docs.spring.io/spring-framework/docs/3.0.x/reference/expressions.html
https://itmyhome.com/spring/expressions.html

Spring Expression Language (SpEL)
是一种强大的运行时表达式语言，即表达式引擎，由Spring框架提供，用于在Java应用程序中查询和操作对象图。它允许您在配置、注解以及其他需要动态处理属性或表达式的地方进行复杂的逻辑操作。
SpEL表达式的结构丰富多样，可以执行以下操作：

- 属性访问
    - 访问对象的属性：`#{object.property}`
    - 访问集合元素：`#{array[index]}` 或 `#{list[0]}`
    - 遍历和投影集合：`#{collection.![property]}`
- 方法调用
    - 调用对象的方法：`#{object.method(args)}`
    - 内置函数调用：`#{T(java.lang.Math).random()}`
- 条件判断与运算符
    - 简单比较：`#{age > 18}`
    - 逻辑运算：`#{age > 18 && age < 60}`
    - 正则表达式匹配：`#{name matches '^[A-Za-z]+$'}`
- 类型转换
    - 显式类型转换：`#{#strings.toUpperCase(name)}`
- 构造器调用
    - 创建新对象实例：`#{new java.util.Date()}`
    - 构造一个LocalDate：`#{T(java.time.LocalDate).of(2023, 1, 1)}`
    - 构造一个Map：`{'key':'k1','value':'v1'}`
    - 构造一个List：`{1,2,3,4}`
- 变量定义和引用
    - 定义变量并在表达式中使用：`#{#myVar = 'value'; #myVar}`
- 流式表达式
    - 流式操作（如Java 8 Stream API）：`#{user.?[age >= 30].size()}`
    - `.![]` 是流式过滤操作符，它会遍历集合并对每个元素应用内部的条件表达式（这里是 `age >= 30`），返回满足条件的元素的某个
      **属性（在这个例子中是年龄）** 组成的列表。
    - `.?[]` 是流式过滤操作符，但它是用来过滤出符合条件的**完整对象**，并非单个属性
- 集合操作
    - 合并、过滤等集合操作：`#{#names + ['John', 'Jane']}`
- 模板表达式
    - 在Spring Boot的application.properties或application.yml文件中，通过`${...}`语法读取外部属性值。

部分参考：

- https://www.cnblogs.com/xfeiyun/p/16914131.html

### 常见的表达式引擎

- MVEL (MVFLEX Expression Language)：是一个独立的开源项目，它提供了一个轻量级且功能丰富的运行时表达式引擎，可在Java应用中进行复杂的表达式求值。
-

Aviator是一个高性能、轻量级的Java表达式求值引擎，它允许在Java应用中进行简单的数学和逻辑表达式的计算，尤其适合于动态生成或处理SQL查询语句等场景。https://github.com/killme2008/aviatorscript

- OGNL (Object-Graph Navigation Language)：主要用于Apache Struts框架和一些其他Java库，用于获取和设置Java对象的属性以及调用方法。
- JEXL (Java Expression Language)：来自Apache Commons项目，也是一个轻量级的、可嵌入的表达式语言，能够执行简单的到较为复杂的表达式。
- JavaScript scripting in Java：通过javax.script包（即JSR 223: Scripting for the Java
  Platform）可以实现Java环境中执行JavaScript表达式，这在需要动态脚本能力时非常有用。
- Groovy expressions: Groovy作为一种与Java高度兼容的编程语言，其表达式可以在Java应用程序中被当作脚本语言来执行
- Janino：一个纯Java编写的类文件生成器和轻量级的脚本引擎，可以将Java代码作为字符串编译并执行。
- JEP (Java Mathematical Expression Parser)：一个用Java编写的数学表达式解析器，它可以解析和评估包含变量、函数和操作符的数学表达式。
- EL (Expression Language)：在Java
  EE环境中广泛使用的标准表达式语言，用于在JSP、JSF等技术中动态插入或修改内容。它也可以通过javax.el.*包在非Web应用中使用。
- Velocity Templating Engine 和 FreeMarker：虽然主要作为模板引擎使用，但它们同样支持在模板中编写表达式以进行简单的逻辑判断和数据操作。

### 案例
