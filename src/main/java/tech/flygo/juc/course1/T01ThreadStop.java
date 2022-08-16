package tech.flygo.juc.course1;

import tech.flygo.juc.course0.SleepHelper;

/**
 * @description: 使用stop方法结束线程(已过期，简单粗暴，不建议使用)
 * @author: flygo
 * @time: 2022/8/15 21:52
 */
public class T01ThreadStop {

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

    t.stop();
  }
}
