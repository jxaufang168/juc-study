package tech.flygo.juc.course1;

import tech.flygo.juc.course0.SleepHelper;

/**
 * @description: 使用线程的suspend()和resume()方法(已过期，不建议使用)
 * @author: flygo
 * @time: 2022/8/15 22:07
 */
public class T02ThreadSuspendAndResume {

  public static void main(String[] args) {
    Thread t =
        new Thread(
            () -> {
              while (true) {
                System.out.println("go on!");
                SleepHelper.sleepSeconds(1);
              }
            });

    t.start();

    SleepHelper.sleepSeconds(5);

    // 线程暂停
    t.suspend();
    System.out.println("-------线程暂停3秒-------");
    SleepHelper.sleepSeconds(3);
    // 线程恢复重新开始
    t.resume();
  }
}
