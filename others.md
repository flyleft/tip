  
---

# 代码实现

***Android***
- [fresco自定义ImagePipeline，使用OkHttp加载图片,并加入SSL访问证书](https://github.com/jcalaz/xmarket/blob/master/app/src/main/java/me/jcala/xmarket/app/App.java)
- [retrofit通过okHttp拦截器实现token验证，过期自动获取新token](https://github.com/jcalaz/xmarket/blob/master/app/src/main/java/me/jcala/xmarket/network/TokenInterceptor.java)
- [retrofit支持https访问](https://github.com/jcalaz/xmarket/blob/master/app/src/main/java/me/jcala/xmarket/network/ReqExecutor.java)
- [RecyclerView万能适配器](https://github.com/jcalaz/xmarket/blob/master/app/src/main/java/me/jcala/xmarket/view/RecyclerCommonAdapter.java)
- [retrofit http日志打印](https://github.com/jcalaz/xmarket/blob/master/app/src/main/java/me/jcala/xmarket/network/ReqExecutor.java)
- [结合RxJava实现的后台轮询](https://github.com/jcalaz/xmarket/blob/master/app/src/main/java/me/jcala/xmarket/mvp/message/MessageService.java)
- [MVP模式的实现](https://github.com/jcalaz/xmarket/tree/master/app/src/main/java/me/jcala/xmarket/mvp/school)
- [Dagger2实现简单依赖注入](https://github.com/jcalaz/xmarket/tree/master/app/src/main/java/me/jcala/xmarket/di)
- [Realm数据库实现页面数据存储](https://github.com/jcalaz/xmarket/blob/master/app/src/main/java/me/jcala/xmarket/mvp/sort/TradeTagPresenterImpl.java)
- [fresco加载gif，实现启动动画](https://github.com/jcalaz/xmarket/blob/master/app/src/main/java/me/jcala/xmarket/mvp/splash/SplashActivity.java)
- [GridView或者ListView的万能适配器](https://github.com/jcalaz/xmarket/blob/master/app/src/main/java/me/jcala/xmarket/view/CommonAdapter.java)
- [fresco实现圆形头像](https://github.com/jcalaz/xmarket/blob/master/app/src/main/res/layout/main_slide.xml)
- [retrofit实现多图片和javabean同时上传](https://github.com/jcalaz/xmarket/blob/master/app/src/main/java/me/jcala/xmarket/mvp/trade/add/TradeAddModelImpl.java)

***javaEE***
- [spring data mongo使用MongoTemplate实现复杂数据操作](https://github.com/jcalaz/xmarket-server/blob/master/src/main/java/me/jcala/xmarket/server/repository/CustomRepositoryImpl.java)
- [Multipart接收多多图片存储，并生成图片获取链接](https://github.com/jcalaz/xmarket-server/blob/master/src/main/java/me/jcala/xmarket/server/utils/FileTool.java)
- [使用SpringMVC拦截器验证Token是否过期和合法](https://github.com/jcalaz/xmarket-server/blob/master/src/main/java/me/jcala/xmarket/server/interceptor/TokenInterceptor.java)
- [swagger配置，自动根据springmvc的控制器注解生成API文档](https://github.com/jcalaz/xmarket-server/blob/master/src/main/java/me/jcala/xmarket/server/conf/RestConfig.java)
- [MongoRepository设置从mongo读取列](https://github.com/jcalaz/xmarket-server/blob/master/src/main/java/me/jcala/xmarket/server/repository/TradeRepository.java)
- [jwt token的创建](https://github.com/jcalaz/xmarket-server/blob/master/src/main/java/me/jcala/xmarket/server/service/UserServiceImpl.java)
- [spring boot配置https](https://github.com/jcalaz/xmarket-server/blob/master/src/main/resources/application-dev.yml)
- [keytool生成HTTPS证书](https://github.com/jcalaz/xmarket-server)
- [spring boot整合mybatis](https://github.com/jcalaz/jcalaBlog/blob/master/src/main/resources/application-dev.yml)
- [全注解使用mybatis](https://github.com/jcalaz/jcalaBlog/blob/master/src/main/java/me/jcala/blog/mapping/BlogMapper.java)
- [velocity的宏使用](https://github.com/jcalaz/jcalaBlog/blob/master/src/main/resources/templates/VM_global_library.vm)
- [spring boot运行时自动创建数据库和表，并设置初始数据](https://github.com/jcalaz/jcalaBlog/blob/master/src/main/resources/application-dev.yml)
- [spring boot开启HTTP文件压缩](https://github.com/jcalaz/jcalaBlog/blob/master/src/main/resources/application-dev.yml)
- [用md5实现的简单图片防篡改](https://github.com/jcalaz/tip/blob/master/src/main/java/me/jcala/tip/img/PreventImgTamper.java)
- [netty5自定义路由](https://github.com/jcalaz/nettyServer/blob/master/src/main/java/smart/cfg/RouterSetting.java)
- [netty5使用ehcache模拟session](https://github.com/jcalaz/nettyServer/blob/master/src/main/java/smart/action/LoginAct.java)
- [netty5同时做http和WeSocket的服务器](https://github.com/jcalaz/nettyServer/blob/master/src/main/java/smart/core/netty/HttpHandler.java)


***前端***
- [vuex的使用](https://github.com/jcalaz/jchat/tree/master/ui/src/vuex)
- [使用vue-cli，js代码中图片访问链接设置](https://github.com/jcalaz/jchat/tree/master/ui/static)


# 数据结构
- [时间复杂度](https://github.com/jcalaz/tip/blob/master/data_structure/%E6%97%B6%E9%97%B4%E5%A4%8D%E6%9D%82%E5%BA%A6.md)
- [数据结构-线性表](https://github.com/jcalaz/tip/blob/master/data_structure/%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84-%E7%BA%BF%E6%80%A7%E8%A1%A8.md)
- [数据结构-栈](https://github.com/jcalaz/tip/blob/master/data_structure/%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84-%E6%A0%88.md)
- [数据结构-队列](https://github.com/jcalaz/tip/blob/master/data_structure/%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84-%E9%98%9F%E5%88%97.md)
- [数据结构-树](https://github.com/jcalaz/tip/blob/master/data_structure/%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84-%E4%BA%8C%E5%8F%89%E6%A0%91.md)
- [数据结构-图](https://github.com/jcalaz/tip/blob/master/data_structure/%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84-%E5%9B%BE.md)
- [数据结构-排序](https://github.com/jcalaz/tip/blob/master/data_structure/%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84-%E6%8E%92%E5%BA%8F.md)

---

# 设计模式
- [面向对象七大设计原则](https://github.com/jcalaz/tip/blob/master/design_pattern/%E9%9D%A2%E5%90%91%E5%AF%B9%E8%B1%A1%E8%AE%BE%E8%AE%A1%E5%8E%9F%E5%88%99.md)
- [责任链模式](https://github.com/jcalaz/tip/blob/master/design_pattern/%E8%B4%A3%E4%BB%BB%E9%93%BE%E6%A8%A1%E5%BC%8F.md)
- [组合模式-如何让孩子爱上设计模式 ——9.组合模式(Composite Pattern)](http://www.jianshu.com/p/4e9a951415d2?utm_source=tuicool&utm_medium=referral)
- [组合模式-设计模式之组合模式（Composite 模式）](http://www.jianshu.com/p/685dd6299d96?utm_source=tuicool&utm_medium=referral)

---

# LeetCode
- [leetcode刷题(一)](https://github.com/jcalaz/tip/blob/master/algorithm/leetcode%E5%88%B7%E9%A2%98(%E4%B8%80).md)

---

# 面试题
- [面试题_基础](https://github.com/jcalaz/tip/blob/master/interview/%E9%9D%A2%E8%AF%95%E9%A2%98_%E5%9F%BA%E7%A1%80.md)
- [面试题_web](https://github.com/jcalaz/tip/blob/master/interview/%E9%9D%A2%E8%AF%95%E9%A2%98_web.md)
- [面试题_框架](https://github.com/jcalaz/tip/blob/master/interview/%E9%9D%A2%E8%AF%95%E9%A2%98_%E6%A1%86%E6%9E%B6.md)
- [面试题_并发与多线程](https://github.com/jcalaz/tip/blob/master/interview/%E9%9D%A2%E8%AF%95%E9%A2%98_%E5%B9%B6%E5%8F%91%E4%B8%8E%E5%A4%9A%E7%BA%BF%E7%A8%8B.md)
- [面试题_数据库](https://github.com/jcalaz/tip/blob/master/interview/%E9%9D%A2%E8%AF%95%E9%A2%98_%E6%95%B0%E6%8D%AE%E5%BA%93.md)
- [面试题_JVM与内存](https://github.com/jcalaz/tip/blob/master/interview/%E9%9D%A2%E8%AF%95%E9%A2%98_JVM%E4%B8%8E%E5%86%85%E5%AD%98.md)

---
---

# 喜爱电影
#### 电影
- 我是谁？没有绝对安全的系统
- 永无止境
- 黑暗骑士
- 前目的地
- 心理游戏
- 非常嫌疑犯
- V字仇杀队
- 搏击俱乐部 
- 一级恐惧
- 致命ID
- 源代码
- 低俗小说
- 幸运数字斯莱文
- 热血警探
- 记忆碎片
- 盗梦空间
- Her
- 第九区
- 灵异第六感
- 杀人回忆
- 无耻混蛋
- 老无所依
- 恐怖游轮
- 恐怖直播
- 电锯惊魂

#### 电视剧
- 神探夏洛克
- 黑客军团
- 黑镜
