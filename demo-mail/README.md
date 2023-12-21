# Springboot 继承 Mail 服务

## 引入依赖
```xml
    <dependencies>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-mail</artifactId>
        </dependency>

    </dependencies>
```

## 邮件服务配置

参考：`org.springframework.boot.autoconfigure.mail.MailProperties`


```yaml
spring:
  application:
    name: demo-mail
  mail:
    # smtp server host，邮件服务提供商，根据选择的邮件服务提供商不同而不同，以下示例为阿里云企业邮箱的smtp服务的host
    host: smtp.qiye.aliyun.com
    # smtp的可登录用户名称
    username: user@company.com
    # smtp的可登录用户密码
    password: 1234...
    # smtp服务端口
    port: 465
    # smtp服务的默认编码
    default-encoding: UTF-8
    # 额外可用且支持的配置
    properties:
      mail:
        smtp:
          socketFactory:
            class: javax.net.ssl.SSLSocketFactory
          auth: true
          starttls:
            enable: true
            required: true

```

## 阅读官方文档

参考：
- https://docs.spring.io/spring-boot/docs/2.1.x/reference/html/boot-features-email.html
- https://docs.spring.io/spring-framework/docs/5.1.19.RELEASE/spring-framework-reference/integration.html#mail

通过如上文档，我们可以知道，当我们引用了`spring-boot-starter-mail`并配置了`spring.mail.host`之后，spring boot会帮我们创建一个默认的`JavaMailSender`的bean对象，前提是同名对象的bean不存在。

官方文档建议我们给配置文件加上超时时间限制，因为默认情况下，超时时间是无限的。如果你想避免一个线程被无限阻塞，那么就可以按照官方的建议设置一下超时时间。
```yaml
    properties:
      mail:
        smtp:
          # smtp服务连接超时时间
          connection: 5000
          # 发送超时时间
          timeout: 3000
          # 写入超时时间
          writeTimeout: 5000
```

除此之外，官方还建议我们配置`jndi-name`，方便Java程序与服务器的命名服务合目录服务交互。
```yaml
spring:
  mail:
    jndi-name: mail/Session
```

然后，我本人在使用的过程中，觉得有必要在`properties`配置项中添加`form`配置项，因为我们在发送邮件的时候需要指明发送方邮件地址。
```yaml
    properties:
      mail:
        # 这里我直接使用的是上面配置的用户，如果配置其他用户需要权限校验
        from: ${spring.mail.username}
```

## 结合代码使用

详情请参考代码，这里我列出一些我做的一些demo接口使用方法。

- 单人邮件发送

```java

```

- 批量邮件发送

```java

```

- 发送纯文本邮件

```java

```

- 发送HTML格式邮件

```java

```

- 发送纯文本+HTML格式的邮件

JavaMailSender支持同时发送两种格式的邮件，客户可以选择其中一种进行查看。

```java

```

- 根据邮件模板发送邮件

我们可能会收到带外链的邮件，如果只通过字符拼接可能会出现显示问题，不雅观，此时我们就可以通过模板引擎发送，只需要获取需要填充的对象数据即可。

```java

```


- 发送带附件邮件

```java

```

