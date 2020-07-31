package MyThread;

public class JoinThread{
    public static void main(String[] args) throws Exception{
        JoinThread1 jt = new JoinThread1("join 1");
        for(int i=0;i<100;i++){
            if(i==2){
                jt.start();
                jt.join();
            }
            System.out.println(Thread.currentThread().getName()+" "+i);
        }
    }
}

class JoinThread1 extends Thread{
    public JoinThread1(String name){
        super(name);
    }
    public void run(){
        JoinThread2 jt = new JoinThread2("join 2");
        for(int i=0;i<100;i++){
            if(i==2){
                jt.start();
                try {
                    jt.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(getName()+" "+i);
        }
    }
}

class JoinThread2 extends Thread{
    public JoinThread2(String name){
        super(name);
    }
    public void run(){
        for(int i=0;i<100;i++){

            System.out.println(getName()+" "+i);
        }
    }
}
/**

public class JoinThread extends Thread{
    public JoinThread(String name){
        super(name);
    }
    public void run(){
        for(int i=0;i<100;i++){
            System.out.println(getName()+" "+i);
        }
    }

    public static void main(String[] args) throws Exception{
        new JoinThread("new Thread").start();
        for(int i=0;i<100;i++){
            if(i==2){
                JoinThread jt = new JoinThread("join Thread");
                jt.start();
                jt.join();
            }
            System.out.println(Thread.currentThread().getName()+" "+i);
        }
    }
}
 *
 */