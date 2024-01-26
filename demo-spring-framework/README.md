# Spring Framework

## AOP

https://docs.spring.io/spring-framework/docs/3.0.x/reference/aop.html

## IoC

https://docs.spring.io/spring-framework/docs/3.0.x/reference/new-in-3.html#new-feature-java-config

## SpEL

https://docs.spring.io/spring-framework/docs/3.0.x/reference/expressions.html
https://itmyhome.com/spring/expressions.html

Spring Expression Language (SpEL)是一种强大的运行时表达式语言，由Spring框架提供，用于在Java应用程序中查询和操作对象图。它允许您在配置、注解以及其他需要动态处理属性或表达式的地方进行复杂的逻辑操作。
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