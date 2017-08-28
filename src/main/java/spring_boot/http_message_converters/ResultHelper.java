package spring_boot.http_message_converters;

/**
 * Created by zhipeng.zuo on 2017/8/15.
 */
public class ResultHelper<T> {
    private Result<T> result;

    public ResultHelper() {
        result=new Result<>();
        result.setCode(1);
        result.setMessage("success");
    }

    public Result<T>  setData(T t){
        this.result.setData(t);
        return this.result;
    }

    public Result<T> setErrorMsg(String msg){
        this.result.setCode(0);
        this.result.setMessage(msg);
        return this.result;
    }
}
