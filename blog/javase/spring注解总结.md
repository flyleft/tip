## spring IOC相关

- **@Configuration: ** 把一个类作为一个IoC容器，它的某个方法头上如果注册了@Bean，就会作为这个Spring容器中的Bean。
- **@Scope: **注解 作用域
- **@Lazy(true): **表示延迟初始化
- **@Service： **用于标注业务层组件、
- **@Controller：**用于标注控制层组件（如struts中的action）
- **@Repository：**用于标注数据访问组件，即DAO组件。
- **@Component **泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注。
- **@Scope：**用于指定scope作用域的（用在类上）
- **@PostConstruct：**用于指定初始化方法（用在方法上）
- **@PreDestory：**用于指定销毁方法（用在方法上）
- **@DependsOn：**定义Bean初始化及销毁时的顺序
- **@Primary：**自动装配时当出现多个Bean候选者时，被注解为@Primary的Bean将作为首选者，否则将抛出异常
- **@Autowired：** 默认按类型装配，如果我们想使用按名称装配，可以结合@Qualifier注解一起使用。如下：
- **@Autowired @Qualifier("personDaoBean")： **存在多个实例配合使用
- **@Resource：**默认按名称装配，当找不到与名称匹配的bean才会按类型装配。
- **@PostConstruct：** 初始化注解
- **@PreDestroy：** 摧毁注解 默认 单例  启动就加载
- **@Async：** 异步方法调用

## spring MVC相关

- **@ControllerAdvice:** 作用于所有控制器，常用来做全局异常处理器。
- **@CookieValue:** 从Http请求头中的Cookie提取指定的某个Cookie。处理request header部分
  ```java

   @RequestMapping(value = "/jsessionId")

    public String jsessionId(@CookieValue(value = "JSESSIONID", required = true,                         defaultValue = "sdd") String jsessionId, Model model) {

        model.addAttribute("jsessionId", jsessionId);

        return "request/cookieValue";

    }

  ```
- **@CrossOrigin:** 允许方法或者类跨域
- **@DeleteMapping：** 映射HTTP的delete请求
- **@GetMapping:** 映射HTTP的get请求
- **@PostMapping:** 映射HTTP的post请求
- **@PutMapping:** 映射HTTP的put请求
- **@PatchMapping:** 映射HTTP的patch请求
- **@RequestMapping:** 映射HTTP请求
- **@ExceptionHandler：** 拦截异常
  ```java
  @ControllerAdvice

  @Slf4j

  public class CtrlExceptionHandler {

        @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)

        @ExceptionHandler(Exception.class)

        public String handleIOException(Exception e, Model model) {

            if (e!=null){

                log.warn(e.getMessage());

                model.addAttribute("errorMsg",e.getLocalizedMessage());

            }

            return "/error";

        }

    }

  ```
- **@InitBinder:** 绑定请求参数到指定的参数编辑器
    ``` java

      @InitBinder

    public void initBinder(WebDataBinder binder) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        dateFormat.setLenient(false);

        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));

    }
    ```
- **@MatrixVariable:** 映射请求url多参数情况
    ```java
     // GET /owners/42;q=11/pets/21;q=22
    @RequestMapping(value = "/owners/{ownerId}/pets/{petId}", method = RequestMethod.GET)
    public voidfindPet(
    @MatrixVariable(value="q", pathVar="ownerId") intq1,
    @MatrixVariable(value="q", pathVar="petId") intq2) {
    // q1 == 11
    // q2 == 22
    }
    ```
- **@ModelAttribute：** 绑定Model参数到命令对象,比如表单数据绑定到对象
  ```java
  public String test1(@ModelAttribute("user") UserModel user){...}
  ```
- **@PathVariable：** url路径映射
  ```java
  @RequestMapping("/pets/{petId}")
  public void findPet(@PathVariable String petId, Model model) {
    // implementation omitted
  }
  ```
- **@RequestAttribute: ** 可以映射request的attribute(由过滤器或拦截器创建的、预先存在的请求属性)
- **@RequestBody: ** 把所有请求参数作为json解析
- **@RequestHeader: ** 映射HTTP的header参数
- **@RequestParam: **  绑定单个请求数据，既可以是URL中的参数，也可以是表单提交的参数或上传的文件。
- **@RequestPart: ** 绑定“multipart/form-data”参数
- **@ResponseBody: ** 绑定方法返回参数为HTTP返回数据对象,跳过视图处理部分。
- **@ResponseStatus：** 返回一个指定的http response状态码
- **@SessionAttributes: ** 定义一个session attributes
```java
@Controller
@SessionAttributes( { "user" })
@RequestMapping("/test")
public class Controller {
}
```



## 参考

- [@MatrixVariable的使用](http://lucky16.iteye.com/blog/1901435)