# Spring Cloud Config Client配置拉取

### 拉取configServer配置文件:
1. 拉取文件默认是${applicationName}.yml
2. 默认情况下拉取configsever的配置文件，是一次性的，配置文件内容变后，客户端不会同步刷新.
若需要同步刷新客户端的配置，需要添加actuator依赖，并开启端口，需要刷新的类作用域下加上`@RefreshScope`注解，
并且每次更新配置文件后，需要POST方式调用客户端的`http://{ip:port}/actuator/refresh`接口,配置文件会同步更新。
