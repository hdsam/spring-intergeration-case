# openFeign使用

### openFeign声明式调用外部服务
1. 开启feign调用: @EnableFeignClient
2. 声明feign客户端: @FeignClient

### 针对单个feignClient配置
1. `BasicAuthRequestInterceptor`和`RequestInterceptor`基础认证
2. feign调用日志配置
3. circuitbreaker-resilience4j断路器使用
4. 使用`ErrorDecoder`处理外部调用异常



    