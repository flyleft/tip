## swagger

1. 内部接口可以在swagger显示，但无法调用，经过gateway的外部接口即可以显示，也可以调试。
2. swagger只显示运行中的服务的接口。
3. swagger调试权限参照google，授权后记录登录状态。
4. 可以显示不同版本之间的接口变化对比。
5. eureka的注册服务器检测到新的实例上线，更新正在运行的服务名，版本列表，并同步刷新权限。提供一个refresh接口，可以强制刷新。
6. *接口命名规则校验等。
7. manager提供给前端的swagger接口：
	- GET /docs/{serviceName}/{version} 根据服务名和版本查询接口json
	- GET /swagger-resources 获取正在运行的服务名，版本列表
	- GET /swagger-resources/configuration/security 安全性token的配置
   - GET /diff/{serviceName}?currentVeriosn=v1.4&diffVersion=1.3 显示两个版本的接口变化
   - POST /refresh/{serviceName}/{version} 强制刷新，更新正在运行的服务名，版本列表，并同步刷新权限。
8. 添加一个表用于存储服务的swagger json。字段：
   - service_id: 服务id
   - version: 服务版本
   - swagger_json: swagger json
