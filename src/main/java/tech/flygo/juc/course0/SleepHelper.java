package tech.flygo.juc.course0;

import java.util.concurrent.TimeUnit;

/**
 * @description: 休眠工具类
 * @author: flygo
 * @time: 2022/8/13 22:05
 */
public class SleepHelper {

  public static void sleepSeconds(int seconds) {
    try {
      TimeUnit.SECONDS.sleep(seconds);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
