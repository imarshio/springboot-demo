# 日志

## 日志框架

目前常用的日志框架有log4j,slf4j,,logback,这些日志框架可以分为两种类型：日志门面和日志实现

### 日志门面

只提供日志相关的接口定义，不提供具体的实现，日志门面必须配合日志系统使用，日志门面可以动态或静态的指定具体的日志框架实现，使用户可以灵活的选择。

门面日志框架包含：commons logging，slf4j

门面指的是外观模式，

### 日志实现

提供了日志接口的实现

日志实现框架包括log4j，JUL，logback。

- 参考：https://www.loggly.com/ultimate-guide/java-logging-basics/

- 参考：https://zhuanlan.zhihu.com/p/86249472

- 参考：https://zhuanlan.zhihu.com/p/365154773

## 演进

https://www.yuque.com/u21559410/xqh5kz/tcevtc7ulef4tnzb?inner=enOcZ

顺带一提，SpringBoot自带的日志体系是：slf4j和logback的组合，引入的其他日志框架如log4j会通过`log4j-to-slf4j`
重定向到slf4j实现，包括jul。

```xml

<dependencies>
    <dependency>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-classic</artifactId>
        <version>1.2.3</version>
        <scope>compile</scope>
    </dependency>
    <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-to-slf4j</artifactId>
        <version>2.13.3</version>
        <scope>compile</scope>
    </dependency>
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>jul-to-slf4j</artifactId>
        <version>1.7.30</version>
        <scope>compile</scope>
    </dependency>
</dependencies>
```

## Log4j

https://github.com/apache/logging-log4j1

使用参考

- 官方手册：https://logging.apache.org/log4j/1.2/manual.html
- https://www.jianshu.com/p/ccafda45bcea

目前Log4j已不再继续维护，因为有了更强大的Log4j2，所以这里强烈建议不再使用log4j。
Log4j是一种日志记录的实现。

### 日志级别

- TRACE
- DEBUG
- INFO
- WARN
- ERROR
- FATAL

从小到大为：DEBUG < INFO < WARN < ERROR < FATAL.

### 核心组件

Log4j有三个核心组件

-
loggers，负责发送日志打印请求并决定该请求是否生效，负责定义每一个日志请求的日志级别，负责定义全局日志可输出级别，负责接受输出日志的内容（从程序获取的部分，如`log.info("this is an info level log.");`
中的`this is an info level log.`）

- appenders，负责决定日志的输出目的，如`console`，`.log`文件等，也支持异步，一个logger可以对应多个appenders，一个请求会被所有的appenders处理

- layouts，负责定义输出格式

引用官方文档：`Log4j has three main components: loggers, appenders and layouts. These three types of components work together to enable developers to log messages according to message type and level, and to control at runtime how these messages are formatted and where they are reported. `

---

## J.U.L(jdk-logging)

java.util.logging 简称jul，自jdk1.4开始存在，与Java绑定。
所以这也是Java使用日志最简单的方式，如果你的程序不需要很复杂的日志结构，且依赖受限，JUL将是你最好的选择。

参考

- 官方文档：https://docs.oracle.com/en/java/javase/11/docs/api/java.logging/java/util/logging/package-summary.html

- https://www.loggly.com/ultimate-guide/java-logging-basics/

- https://mkyong.com/logging/java-logging-apis-tutorial/

### 日志级别

官方文档：https://docs.oracle.com/en/java/javase/11/docs/api/java.logging/java/util/logging/Level.html

- SEVERE
- WARNING
- INFO
- CONFIG
- FINE
- FINER
- FINEST

从小到大为：FINEST < FINER < FINE < CONFIG < INFO < WARNING < SEVERE

### 核心组件

与Log4j相同，Java logging也有三个核心组件logger，handler（类似于Log4j的appenders），formatter（类似于Log4j的layouts），功能也与log4j类似，这里不在展开详述。

### 配置

J.U.L默认会加载logging.properties。

- `$JAVA_HOME/jre/lib/`： Java 8及之前的版本的配置文件放在这个位置
- `$JAVA_HOME/conf` ： Java 9及之后的版本的配置文件放在这个位置

除了默认的加载方式外，还可以通过其他方式加载配置文件

- 运行时指定加载文件

  ```sh
  # 通过相对路径指定日志文件位置
  $ java -jar -Djava.util.logging.config.file=./resource/logging.properties server.jar
  ```

- 通过LogManager加载配置文件

  ```java
  package com.test;
  
  import java.io.IOException;
  import java.io.InputStream;
  import java.util.logging.Level;
  import java.util.logging.LogManager;
  import java.util.logging.Logger;
  
  public class LoadLogProperties {
  
    static {
        // must set before the Logger
        // loads logging.properties from the classpath
        try (InputStream is = LoadLogProperties.class.getClassLoader().getResourceAsStream("logging.properties")) {
            LogManager.getLogManager().readConfiguration(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
  
    private static Logger logger = Logger.getLogger(LoadLogPropertiesFile.class.getName());
  
    public static void main(String[] args) {
  
        logger.info("This is level info logging");
  
    }
  
  }
  ```

- 通过系统变量加载配置文件

  ```java
  package com.mkyong;
  
  import java.io.IOException;
  import java.io.InputStream;
  import java.util.logging.Level;
  import java.util.logging.LogManager;
  import java.util.logging.Logger;
  
  public class LoadLogPropertiesFile2 {
  
    static {
        // must set before the Logger
        // loads logging.properties from the classpath
        String path = LoadLogPropertiesFile.class.getClassLoader().getResource("logging.properties").getFile();
        System.setProperty("java.util.logging.config.file", path);
  
    }
  
    private static Logger logger = Logger.getLogger(LoadLogPropertiesFile.class.getName());
  
    public static void main(String[] args) {
  
        logger.info("This is level info logging");
  
    }
  
  }
  ```

样例

```properties
# Logs to file and console
handlers=java.util.logging.FileHandler, java.util.logging.ConsoleHandler
# Global logging levels, 7 levels
.level=SEVERE
# Log file output in user's home directory, %h
java.util.logging.FileHandler.pattern=%h/java%u.log
java.util.logging.FileHandler.limit=50000
java.util.logging.FileHandler.count=1
java.util.logging.FileHandler.formatter=java.util.logging.XMLFormatter
# java.util.logging.FileHandler.formatter = java.util.logging.SimpleFormatter
java.util.logging.ConsoleHandler.level=INFO
java.util.logging.ConsoleHandler.formatter=java.util.logging.SimpleFormatter
java.util.logging.SimpleFormatter.format=[%1$tc] %4$s: %5$s %n
# log level for package
com.mkyong.level=SEVERE
```

## J.C.L(commons-logging)

Java commons logging 简称 J.C.L，
官方文档：https://commons.apache.org/proper/commons-logging/

wiki：https://cwiki.apache.org/confluence/display/commons/Logging

### 日志级别

- fatal
- error
- warn
- info
- debug
- trace

日志级别从小到大为：trace < debug < info < warn < error < fatal

### 核心组件



### 配置

JCL默认使用

## Logback

### 日志级别

### 核心组件

### 配置

## SLF4J

### 日志级别

### 核心组件

### 配置

## Log4j2

### 日志级别

### 核心组件

### 配置
