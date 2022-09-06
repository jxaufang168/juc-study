package tech.flygo.juc.course3;

import java.util.concurrent.CountDownLatch;

/**
 * @description: 线程的乱序执行验证
 * @author: flygo
 * @time: 2022/8/19 10:14
 */
public class T01DisOrdered {

  private static int x = 0, y = 0;
  private static int a = 0, b = 0;

  public static void main(String[] args) throws InterruptedException {
    for (int i = 0; i < Long.MAX_VALUE; i++) {
      x = 0;
      y = 0;
      a = 0;
      b = 0;

      CountDownLatch latch = new CountDownLatch(2);

      Thread one =
          new Thread(
              () -> {
                a = 1;
                x = b;
                latch.countDown();
              });

      Thread other =
          new Thread(
              () -> {
                b = 1;
                y = a;
                latch.countDown();
              });

      one.start();
      other.start();
      latch.await();

      String result = "第" + i + "次 (x=" + x + ",y=" + y + ")";

      if (x == 0 && y == 0) {
        System.out.println(result);
        break;
      }
    }
  }
}
