# PostConstruct

[8.0 官网](https://docs.oracle.com/javase/8/docs/api/javax/annotation/PostConstruct.html)

## 使用方法

* 被`@PostConstruct` 注解标记的方法，不能含有任何参数，除了拦截器之外，。

需要配合容器进行使用，在非容器环境下，`@PostConstruct` 注解是不会生效的（在没有实现调用带有`@PostConstruct`注解的方法的功能前提下）。




Spring 容器中，`@PostConstruct` 注解会在容器创建完对象之后，调用带有`@PostConstruct`注解的方法。

具体的流程如下：

* 创建`Bean`实例。
* 对`Bean`进行依赖注入，填充所有需要的属性和依赖。
* 检查是否存在`@PostConstruct`注解标记的方法。
* 如果找到这样的方法，则由`CommonAnnotationBeanPostProcessor`或者实现了`InitializingBean`接口的`afterPropertiesSet`
  方法来负责调用这些方法。
* 一旦`@PostConstruct`方法执行完毕，`Bean`就被认为是完全初始化并可以被应用程序使用的。
