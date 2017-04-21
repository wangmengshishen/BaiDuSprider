/*  
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
  
*//** 
 * 任务队列示例程序 
 * @author zlf 
 *//*  
public class TaskExample {  
    private TaskRunner taskRunner;  
      
    *//** 
     * 做任务队列的初始化工作 
     *//*  
    @Before  
    public void init() {  
        // 获取任务运行器  
        taskRunner = TaskRunner.getInstance();  
        // 将任务运行器放入线程进行调度  
        Thread thread = new Thread(taskRunner);  
        thread.start();  
    }  
      
    *//** 
     * 等待任务执行完成，并做最后的退出工作 
     *//*  
    @After  
    public void exit() throws InterruptedException {  
        Thread.sleep(600);  
        System.exit(0);  
    }  
      
    *//** 
     * 最简单的任务运行示例 
     *//*  
    @Test  
    public void example1() {  
        // 添加任务到任务运行器  
        taskRunner.addTask(new BasicTask() {  
            @Override  
            public void run() {  
                System.out.println("This is running in task runner thread, and thread is " + Thread.currentThread());  
            }  
        });  
    }  
      
    *//** 
     * 加入优先执行顺序的任务运行器 
     *//*  
    @Test  
    public void example2() {  
        // 添加任务到任务运行器  
        taskRunner.addTask(new BasicTask(0) {  
            @Override  
            public void run() {  
                System.out.println("This is a normal task");  
            }  
        });  
        taskRunner.addTask(new BasicTask(-1) {  
            @Override  
            public void run() {  
                System.out.println("This is a task a bit high than normal");  
            }  
        });  
    }  
      
    *//** 
     * 重复添加的任务只会运行第一个 
     *//*  
    @Test  
    public void example3() {  
        // 添加任务到任务运行器  
        taskRunner.addTask(new BasicTask(TaskSignature.ONE) {  
            @Override  
            public void run() {  
                System.out.println("This is task one");  
            }  
        }, TaskProperty.NOT_REPEAT);  
        taskRunner.addTask(new BasicTask(TaskSignature.ONE) {  
            @Override  
            public void run() {  
                System.out.println("This is also task one");  
            }  
        }, TaskProperty.NOT_REPEAT);  
    }  
      
    *//** 
     * 重复添加的任务只会运行最后一个 
     *//*  
    @Test  
    public void example4() {  
        // 添加任务到任务运行器  
        taskRunner.addTask(new BasicTask(TaskSignature.ONE) {  
            @Override  
            public void run() {  
                System.out.println("This is task one");  
            }  
        }, TaskProperty.NOT_REPEAT_OVERRIDE);  
        taskRunner.addTask(new BasicTask(TaskSignature.ONE) {  
            @Override  
            public void run() {  
                System.out.println("This is also task one");  
            }  
        }, TaskProperty.NOT_REPEAT_OVERRIDE);  
    }  
}  */