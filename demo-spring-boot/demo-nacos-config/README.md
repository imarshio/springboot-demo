## 简介

Nacos /nɑ:kəʊs/ 是 Dynamic Naming and Configuration Service的首字母简称，一个更易于构建云原生应用的动态服务发现、配置管理和服务管理平台。

Nacos 致力于帮助您发现、配置和管理微服务。Nacos 提供了一组简单易用的特性集，帮助您快速实现动态服务发现、服务配置、服务元数据及流量管理。

Nacos 帮助您更敏捷和容易地构建、交付和管理微服务平台。 Nacos 是构建以**“服务”**为中心的现代应用架构 (例如微服务范式、云原生范式) 的服务基础设施。

## 引入依赖

```yaml

<dependencies>
        <!-- 版本 0.2.x.RELEASE 对应的是 Spring Boot 2.x 版本，版本 0.1.x.RELEASE 对应的是 Spring Boot 1.x 版本。 -->
        <dependency>
            <groupId>com.alibaba.boot</groupId>
            <artifactId>nacos-config-spring-boot-starter</artifactId>
            <version>${nacos.version}</version>
        </dependency>

        <dependency>
            <groupId>com.alibaba.boot</groupId>
            <artifactId>nacos-discovery-spring-boot-starter</artifactId>
            <version>${nacos.version}</version>
        </dependency>
</dependencies>
```

### 启动日志

```log

2024-08-13 14:28:11.622  INFO 26872 --- [           main] c.m.demo.SpringSecurityApplication       : Starting SpringSecurityApplication on DESKTOP-I2O884V with PID 26872 (C:\Data\Code\springboot-demo\demo-spring-boot\demo-security\target\classes started by shenqing in C:\Data\Code\springboot-demo)
2024-08-13 14:28:11.625  INFO 26872 --- [           main] c.m.demo.SpringSecurityApplication       : No active profile set, falling back to default profiles: default
2024-08-13 14:28:12.616  INFO 26872 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 18080 (http)
2024-08-13 14:28:12.627  INFO 26872 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2024-08-13 14:28:12.627  INFO 26872 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.39]
2024-08-13 14:28:12.698  INFO 26872 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2024-08-13 14:28:12.698  INFO 26872 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 1018 ms
2024-08-13 14:28:12.885  INFO 26872 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
2024-08-13 14:28:13.050  INFO 26872 --- [           main] .s.s.UserDetailsServiceAutoConfiguration : 

Using generated security password: a3da0057-80d1-4868-90c1-4aea66f35403

2024-08-13 14:28:13.123  INFO 26872 --- [           main] o.s.s.web.DefaultSecurityFilterChain     : Creating filter chain: any request, [org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter@4c6b4ed7, org.springframework.security.web.context.SecurityContextPersistenceFilter@10c47c79, org.springframework.security.web.header.HeaderWriterFilter@adcfad9, org.springframework.security.web.csrf.CsrfFilter@630390b9, org.springframework.security.web.authentication.logout.LogoutFilter@1d4fb213, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter@1835dc92, org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter@1e692555, org.springframework.security.web.authentication.ui.DefaultLogoutPageGeneratingFilter@21263314, org.springframework.security.web.authentication.www.BasicAuthenticationFilter@30c3ae63, org.springframework.security.web.savedrequest.RequestCacheAwareFilter@753fd7a1, org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter@3bb5ceb, org.springframework.security.web.authentication.AnonymousAuthenticationFilter@6ca30b8a, org.springframework.security.web.session.SessionManagementFilter@3a2e9f5b, org.springframework.security.web.access.ExceptionTranslationFilter@5b5dce5c, org.springframework.security.web.access.intercept.FilterSecurityInterceptor@650c405c]
2024-08-13 14:28:13.168  INFO 26872 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 18080 (http) with context path ''
2024-08-13 14:28:13.185  INFO 26872 --- [           main] c.m.demo.SpringSecurityApplication       : Started SpringSecurityApplication in 2.152 seconds (JVM running for 3.447)
2024-08-13 14:28:36.035  INFO 26872 --- [io-18080-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2024-08-13 14:28:36.035  INFO 26872 --- [io-18080-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2024-08-13 14:28:36.040  INFO 26872 --- [io-18080-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 5 ms
2024-08-13 14:28:36.196  WARN 26872 --- [io-18080-exec-1] o.a.c.util.SessionIdGeneratorBase        : Creation of SecureRandom instance for session ID generation using [SHA1PRNG] took [123] milliseconds.

```

## 配置

