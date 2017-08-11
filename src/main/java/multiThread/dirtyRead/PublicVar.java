package multiThread.dirtyRead;

public class PublicVar {
    public String username="A";
    public String password="AA";
    synchronized public void setValue(String username,String password){
        try {
            this.username=username;
            Thread.sleep(1000);
            this.password=password;
            System.out.println("thread:"+Thread.currentThread().getName()+
                    " username"+this.username+" password"+this.password);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void getValue(){
        System.out.println("thread:"+Thread.currentThread().getName()+
                " username"+this.username+" password"+this.password);
    }
}
