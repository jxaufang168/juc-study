package tech.flygo.juc.course0;

import java.util.concurrent.*;

/**
 * @description: 创建线程的5种方法
 * @author: flygo
 * @time: 2022/8/10 22:35
 */
public class T02HowToCreateThread {

  /**
   * @description: 方式1: 自定义线程类，继承Thread，重写run()方法
   * @author: flygo
   * @time: 2022/8/10 22:38
   */
  static class MyThread extends Thread {
    @Override
    public void run() {
      System.out.println("Hello MyThread!");
    }
  }

  /**
   * @description: 方式2: 自定义线程类，实现接口Runnable的run()方法
   * @author: flygo
   * @time: 2022/8/10 22:39
   */
  static class MyRun implements Runnable {
    @Override
    public void run() {
      System.out.println("Hello MyRun!");
    }
  }

  /**
   * @description: 方式4: 自定义线程类，实现Callable接口的call()方法，带返回值异步线程
   * @author: flygo
   * @time: 2022/8/10 22:41
   */
  static class MyCall implements Callable<String> {
    @Override
    public String call() throws Exception {
      System.out.println("Hello MyCall!");
      return "success";
    }
  }

  /**
   * description: 启动线程的5中方式 <br>
   * date: 2022/8/10 22:42 <br>
   * author: flygo <br>
   *
   * @param: args
   * @return void
   */
  public static void main(String[] args) throws Exception {
    // 方式1: 自定义线程类，实现接口Runnable的run()方法
    new MyThread().start();

    // 方式2: 自定义线程类，实现接口Runnable的run()方法
    new Thread(new MyRun()).start();

    // 方式3: 使用线程Runnable Lambda表达式的方式
    new Thread(
            () -> {
              System.out.println("Hello Lambda");
            })
        .start();

    // 方式4: 自定义线程类，实现Callable接口的call()方法，带返回值异步线程
    Thread thread = new Thread(new FutureTask<String>(new MyCall()));
    thread.start();

    // 方式5: 使用ThreadPool线程池
    ExecutorService service = Executors.newCachedThreadPool();
    service.execute(
        () -> {
          System.out.println("Hello ThreadPool");
        });

    // 使用FutureTask，可以获取到返回值的方式
    FutureTask<String> futureTask = (FutureTask<String>) service.submit(new MyCall());
    // 特别注意: futureTask.get()是线程阻塞的，需要等待结果返回，才会往下执行
    String str = futureTask.get();
    System.out.println(str);

    service.shutdown();
  }
}
