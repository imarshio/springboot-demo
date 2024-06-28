## Swagger

[swagger官网](https://swagger.io/)

[swagger介绍](https://swagger.io/about/)

[swagger文档功能](https://swagger.io/solutions/api-documentation/)

[swagger规范](https://swagger.io/docs/specification/about/)

参考：https://doc.xiaominfo.com/

[//]: # (Swagger-tools:提供各种与Swagger进行集成和交互的工具。例如模式检验、Swagger 1.2文档转换成Swagger 2.0文档等功能。)

[//]: # ()
[//]: # (Swagger-core: 用于Java/Scala的的Swagger实现。与JAX-RS&#40;Jersey、Resteasy、CXF…&#41;、Servlets和Play框架进行集成。)

[//]: # ()
[//]: # (Swagger-js: 用于JavaScript的Swagger实现。)

[//]: # ()
[//]: # (Swagger-node-express: Swagger模块，用于node.js的Express web应用框架。)

[//]: # ()
[//]: # (Swagger-ui：一个无依赖的HTML、JS和CSS集合，可以为Swagger兼容API动态生成优雅文档。)

[//]: # ()
[//]: # (Swagger-codegen：一个模板驱动引擎，通过分析用户Swagger资源声明以各种语言生成客户端代码。)

## 介绍

Swagger 又称Open API，是一种API规范，用于描述RESTful API，就像接口，只是定义需要哪些东西，但是不包括具体的实现。

## springfox

使用前我们需要先了解一下[springfox](https://springfox.github.io/springfox/docs/current/).

## 快速开始

### pom

```pom
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-boot-starter</artifactId>
            <version>3.0.0</version>
        </dependency>
```

### 启动服务

打开[http://localhost:port/swagger-ui/#](http://localhost:18888/swagger-ui/#)，即可看到默认注册的服务接口以及请求方法，请求参数。

### 注册接口

```java
@EnableOpenApi
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        // DocumentationType.OAS_30 表示这是 OpenAPI 3.0，另外它还支持 swagger 1.2 、swagger 2.0
        Docket docket = new Docket(DocumentationType.OAS_30)
                // 注册接口信息
                .apiInfo(apiInfo()).enable(true)
                .select()
                // apis： 添加swagger接口所在的 base包
                .apis(RequestHandlerSelectors.basePackage("com.marshio.demo.controller"))
                // 符合条件的路径,支持正则
                .paths(PathSelectors.any())
                .build();

        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("swagger - springfox 演示")
                .description("关于swagger的实现springfox的演示项目")
                // 作者
                .contact(new Contact("marshio", "https://marshio.com", "marshioman@gmail.com"))
                .version("v1.0")
                .build();
    }
}
```

### 面对文档编程

#### `@Api`

多用于controller层

```java
@Api(tags = "用户接口", value = "用户接口value")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

}
```

#### `@ApiOperation`

一般直接用于方法上

```java
    @ApiOperation("根据id查询用户")
    public Response<UserVO> getUserById(@PathVariable Integer id) {
        return Response.success(UserConverter.INSTANCE.toVO(userService.getUserById(id)));
    }
```

#### `@ApiParam`

一般用于方法的参数上

```java
    @GetMapping("/{id}")
    @ApiOperation("根据id查询用户")
    public Response<UserVO> getUserById(@ApiParam(value = "用户id") @PathVariable Integer id) {
        return Response.success(UserConverter.INSTANCE.toVO(userService.getUserById(id)));
    }
```

#### `@ApiImplicitParam`

多用于方法的参数上，跟`@ApiParam`不一样的是，`@ApiImplicitParam`更适用于非直接参数，如请求头，查询参数，表单参数。

#### `@ApiImplicitParams`

是`@ApiImplicitParam`的集合，可以同时使用多个`@ApiImplicitParam`

#### `@ApiResponse`

表示一个方法可能的响应。

```java
@ApiResponses(value = {
        @ApiResponse(responseCode = 400, description = "Invalid ID supplied"),
        @ApiResponse(responseCode = 404, description = "Customer not found")})
@GetMapping("/{id}")
public ResponseEntity<CustomerResponse> getCustomer(@PathVariable("id") Long id) {
    return ResponseEntity.ok(customerService.getById(id));
}
```

摘自--[springfox](https://springfox.github.io/springfox/docs/current/#api-response-annotations)

#### `@ApiResponses`

#### `@ApiModel`

用于描述一个模型对象，多为前后端交互的数据模型。

```java
@Data
@ApiModel("用户请求信息")
@EqualsAndHashCode(callSuper = true)
public class UserRequest extends User {
    
}
```

#### `@ApiModelProperty`

用于描述一个模型对象属性。

```java
    @ApiModelProperty("用户id列表")
    List<Integer> ids;
```

## Knife4j

