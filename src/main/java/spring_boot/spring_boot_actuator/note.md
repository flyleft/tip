- ### 参考
  - http://blog.csdn.net/liaokailin/article/details/48186331
  
- ### 添加依赖
```
    compile ("org.springframework.boot:spring-boot-starter-actuator:${springBootVersion}")
```
- ### 主要暴露的功能
  | 请求方法 | url | 说明 | 鉴权 |
  | :-------------- | :------------ | :------------ | :------------ |
  | GET    | /autoconfig | 查看自动配置的使用情况 | true |
  | GET    | /configprops | 查看配置属性，包括默认配置 | true |
  | GET    | /beans | 查看bean及其关系列表 | true |
  | GET    | /dump | 打印线程栈 | true |
  | GET    | /env | 查看所有环境变量 | true |
  | GET    | /env/{name} | 查看具体变量值 | true |
  | GET    | /health | 查看应用健康指标 | false |
  | GET    | /info | 查看应用信息 | false |
  | GET    | /mappings | 查看所有url映射 | true |
  | GET    | /metrics | 查看应用基本指标 | true |
  | GET    | /metrics/{name} | 查看具体指标 | true |
  | POST   | /shutdown | 关闭应用 | true |
  | GET    | /trace | 查看基本追踪信息 | true |

- ### 注意
1. spring boot 2.0版本url需加上前缀/application
2. 本demo设置了端口号
```yaml
management:
  port: 54001
  health:
    mail:
      enabled: false
```
所以访问url为http://127.0.0.1:54001/application/health
