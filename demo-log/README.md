# 日志

## 日志框架

- 参考：https://zhuanlan.zhihu.com/p/86249472

- 参考：https://zhuanlan.zhihu.com/p/365154773


## 演进


https://www.yuque.com/u21559410/xqh5kz/tcevtc7ulef4tnzb?inner=enOcZ


## 日志级别

- TRACE
- DEBUG
- INFO
- WARN
- ERROR
- FATAL

从小到大为：DEBUG < INFO < WARN < ERROR < FATAL.

## Log4j

https://github.com/apache/logging-log4j1

使用参考

- 官方手册：https://logging.apache.org/log4j/1.2/manual.html
- https://www.jianshu.com/p/ccafda45bcea

目前Log4j已不再继续维护，因为有了更强大的Log4j2。Log4j是一种日志记录的实现。


### 核心组件

Log4j有三个核心组件

- loggers，负责发送日志打印请求并决定该请求是否生效，负责定义每一个日志请求的日志级别，负责定义全局日志可输出级别，负责接受输出日志的内容（从程序获取的部分，如`log.info("this is an info level log.");`中的`this is an info level log.`）
- appenders，负责决定日志的输出目的，如`console`，`.log`文件等，也支持异步，一个logger可以对应多个appenders，一个请求会被所有的appenders处理
- layouts，负责定义输出格式

引用官方文档：`Log4j has three main components: loggers, appenders and layouts. These three types of components work together to enable developers to log messages according to message type and level, and to control at runtime how these messages are formatted and where they are reported. `





