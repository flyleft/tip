###自己平常实现的代码库

### Android
- [fresco自定义ImagePipeline，使用OkHttp加载图片,并加入SSL访问证书](https://github.com/jcalaz/xmarket/blob/master/app/src/main/java/me/jcala/xmarket/app/App.java)
> 第30行，通过自定义的ImagePipelineConfig和OkHttpClient，在自定义的OkHttpClient中加入SSL证书。
- [retrofit通过okHttp拦截器实现token验证，过期自动获取新token](https://github.com/jcalaz/xmarket/blob/master/app/src/main/java/me/jcala/xmarket/network/TokenInterceptor.java)
> 通过实现okhttp的Interceptor实现，在me/jcala/xmarket/network/ReqExecutor.java的61行进行注册。
- [retrofit支持https访问](https://github.com/jcalaz/xmarket/blob/master/app/src/main/java/me/jcala/xmarket/network/ReqExecutor.java)
> 在第47行，证书所在位置R.raw.xmarket，证书利用JDK的keytool工具生成。
- [RecyclerView万能适配器](https://github.com/jcalaz/xmarket/blob/master/app/src/main/java/me/jcala/xmarket/view/RecyclerCommonAdapter.java)
> 通过定义的RecyclerCommonAdapter和RecyclerViewHolder，在https://github.com/jcalaz/xmarket/blob/master/app/src/main/java/me/jcala/xmarket/mvp/school/SchoolPresenterImpl.java中的67行有这个万能适配器的使用
- [retrofit http日志打印](https://github.com/jcalaz/xmarket/blob/master/app/src/main/java/me/jcala/xmarket/network/ReqExecutor.java)
> 第41行，需要导入依赖compile 'com.squareup.okhttp3:logging-interceptor:3.4.1'
- [结合RxJava实现的后台轮询](https://github.com/jcalaz/xmarket/blob/master/app/src/main/java/me/jcala/xmarket/mvp/message/MessageService.java)
> 实现了安卓的Service，设置onStartCommand方法的返回值可以设置后台轮询的类型，使用了RxJava的Observable.interval方法。如果需要后台服务更新ui，可以通过在需要更新ui的页面接收广播实现。
- [MVP模式的实现](https://github.com/jcalaz/xmarket/tree/master/app/src/main/java/me/jcala/xmarket/mvp/school)
> 目的：减少Activity代码量，实现分层，提高可维护性
- [Dagger2实现简单依赖注入](https://github.com/jcalaz/xmarket/tree/master/app/src/main/java/me/jcala/xmarket/di)
> 目的：降耦合，不是类似spring ioc的反射注入，而是编译生成代码注入，不会降低性能，但也明显不如spring ioc或者google guice的简洁
- [Realm数据库实现页面数据存储](https://github.com/jcalaz/xmarket/blob/master/app/src/main/java/me/jcala/xmarket/mvp/sort/TradeTagPresenterImpl.java)
> 每次打开Activity，从Realm中读取数据(37行)，每次下拉刷新后，先删除原有数据，再存入新的数据(69行)。这里没有判断新数据与原数据相同，一并删除，可以自己判断下
- [fresco加载gif，实现启动动画](https://github.com/jcalaz/xmarket/blob/master/app/src/main/java/me/jcala/xmarket/mvp/splash/SplashActivity.java)
```java
public class SplashActivity extends BaseActivity {
    //...
 protected void initVariables() {
        view=(SimpleDraweeView)findViewById(R.id.loading_gif);
        DraweeController builder = Fresco.newDraweeControllerBuilder()
                .setAutoPlayAnimations(true)
                .setUri(Uri.parse("res://drawable/" + R.drawable.splash_loading))//设置uri
                .build();
        view.setController(builder);
    }
    //...
}
```
- [fresco实现圆形头像](https://github.com/jcalaz/xmarket/blob/master/app/src/main/res/layout/main_slide.xml)
```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
     <com.facebook.drawee.view.SimpleDraweeView
            android:id="@+id/info_avatar"
            android:layout_width="56dp"
            android:layout_height="56dp"
            fresco:roundedCornerRadius="56dp"
            fresco:roundBottomLeft="true"
            fresco:roundBottomRight="true"
            fresco:placeholderImage="@mipmap/person"
            android:layout_margin="16dp"/>
</LinearLayout>
```
- [RxJava+retrofit实现HTTP访问](https://github.com/jcalaz/xmarket/blob/master/app/src/main/java/me/jcala/xmarket/mvp/school/SchoolModelImpl.java)

> retrofit的接口方法，这里通过在ReqExecutor单例中通过tradeReq()方法获取TradeReq的实例
```java
public interface TradeReq {
        @GET(ApiConf.get_school_trades)
        Observable<Result<List<Trade>>> getSchoolTrades(
                @Path("schoolName")  String school,
                @Query("page") int page,
                @Query("size") int size
        );
}
```

- [retrofit实现多图片和javabean同时上传](https://github.com/jcalaz/xmarket/blob/master/app/src/main/java/me/jcala/xmarket/mvp/trade/add/TradeAddModelImpl.java)
> retrofit中的接口方法需要将图片文件转为MultipartBody.Part类型(me/jcala/xmarket/mvp/trade/add/TradeAddPresenterImpl.java的77行)
```java
public interface TradeReq {
       @Multipart
       @POST(ApiConf.create_trade)
       Observable<Result<String>> addTrade(
               @Part("trade") RequestBody trade,
               @Part List<MultipartBody.Part> parts);
}
```

### Spring boot
- [spring data mongo使用MongoTemplate实现复杂数据操作](https://github.com/jcalaz/xmarket-server/blob/master/src/main/java/me/jcala/xmarket/server/repository/CustomRepositoryImpl.java)
- [Multipart接收多多图片存储，并生成图片获取链接](https://github.com/jcalaz/xmarket-server/blob/master/src/main/java/me/jcala/xmarket/server/utils/FileTool.java)
- [使用SpringMVC拦截器验证Token是否过期和合法](https://github.com/jcalaz/xmarket-server/blob/master/src/main/java/me/jcala/xmarket/server/interceptor/TokenInterceptor.java)
- [swagger配置，自动根据springmvc的控制器注解生成API文档](https://github.com/jcalaz/xmarket-server/blob/master/src/main/java/me/jcala/xmarket/server/conf/RestConfig.java)
- [MongoRepository设置从mongo读取列](https://github.com/jcalaz/xmarket-server/blob/master/src/main/java/me/jcala/xmarket/server/repository/TradeRepository.java)
- [jwt token的创建](https://github.com/jcalaz/xmarket-server/blob/master/src/main/java/me/jcala/xmarket/server/repository/TradeRepository.java)
- [spring boot配置https](https://github.com/jcalaz/xmarket-server/blob/master/src/main/resources/application-dev.yml)


### 加密
- [用md5实现的简单图片防篡改](https://github.com/jcalaz/tip/blob/master/src/main/java/me/jcala/tip/img/PreventImgTamper.java)
