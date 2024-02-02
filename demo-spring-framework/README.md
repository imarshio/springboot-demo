# Spring Framework

## Spring启动间都发生了什么？

https://docs.spring.io/spring-framework/docs/3.0.x/reference/overview.html

接下来我们跟踪源码进入Spring的启动过程，看看Spring启动时都发生了什么？

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

```java
public static ConfigurableApplicationContext run(Class<?> primarySource, String... args) {
    // 1.2调用SpringApplication.run方法
    return run(new Class[]{primarySource}, args);
}

public static ConfigurableApplicationContext run(Class<?>[] primarySources, String[] args) {
    // 1.3调用SpringApplication.run方法
    return (new SpringApplication(primarySources)).run(args);
}

// 1.4调用SpringApplication.run方法，这个方法可以让我们看到Spring启动期间大致做了什么事
public ConfigurableApplicationContext run(String... args) {
    // 定义全局的计时器
    StopWatch stopWatch = new StopWatch();
    // 开始计时
    stopWatch.start();
    // 定义Spring应用上下文的变量
    ConfigurableApplicationContext context = null;
    // 定义SpringBoot异常报告栈
    Collection<SpringBootExceptionReporter> exceptionReporters = new ArrayList();
    // 1.5 
    this.configureHeadlessProperty();
    SpringApplicationRunListeners listeners = this.getRunListeners(args);
    listeners.starting();

    Collection exceptionReporters;
    try {
        ApplicationArguments applicationArguments = new DefaultApplicationArguments(args);
        ConfigurableEnvironment environment = this.prepareEnvironment(listeners, applicationArguments);
        this.configureIgnoreBeanInfo(environment);
        Banner printedBanner = this.printBanner(environment);
        context = this.createApplicationContext();
        exceptionReporters = this.getSpringFactoriesInstances(SpringBootExceptionReporter.class, new Class[]{ConfigurableApplicationContext.class}, context);
        this.prepareContext(context, environment, listeners, applicationArguments, printedBanner);
        this.refreshContext(context);
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
        this.handleRunFailure(context, var9, exceptionReporters, (SpringApplicationRunListeners)null);
        throw new IllegalStateException(var9);
    }
}

private void configureHeadlessProperty() {
    System.setProperty("java.awt.headless", System.getProperty("java.awt.headless", Boolean.toString(this.headless)));
}
```



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
- Aviator是一个高性能、轻量级的Java表达式求值引擎，它允许在Java应用中进行简单的数学和逻辑表达式的计算，尤其适合于动态生成或处理SQL查询语句等场景。https://github.com/killme2008/aviatorscript
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
