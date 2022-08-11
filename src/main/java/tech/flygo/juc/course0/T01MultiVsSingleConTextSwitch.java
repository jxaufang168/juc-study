package tech.flygo.juc.course0;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @description: 工作线程数是不是设置的越大越好？<br>
 *     线程小示例对比
 * @author: flygo
 * @time: 2022/8/11 15:04
 */
public class T01MultiVsSingleConTextSwitch {

  // --------------单线程计算1亿个数的和--------------- //

  private static double[] nums = new double[1_0000_0000];
  private static Random r = new Random();
  private static DecimalFormat df = new DecimalFormat("0.00");

  static {
    for (int i = 0; i < nums.length; i++) {
      nums[i] = r.nextDouble();
    }
  }

  /**
   * description: 单线程计算1亿个数的和 <br>
   * date: 2022/8/11 15:15 <br>
   * author: flygo <br>
   *
   * @return void
   */
  private static void m1() {
    long start = System.currentTimeMillis();

    double result = 0.0;
    for (int i = 0; i < nums.length; i++) {
      result += nums[i];
    }

    long end = System.currentTimeMillis();

    System.out.println(
        "单线程计算1亿个数的和-m1-计算所用时间: " + (end - start) + ",计算结果 result:" + df.format(result));
  }

  // --------------两个线程计算1亿个数的和--------------- //

  static double result1 = 0.0, result2 = 0.0, result = 0.0;

  /**
   * description: 两个线程计算1亿个数的和 <br>
   * date: 2022/8/11 15:16 <br>
   * author: flygo <br>
   *
   * @return void
   */
  private static void m2() throws InterruptedException {
    Thread t1 =
        new Thread(
            () -> {
              for (int i = 0; i < nums.length / 2; i++) {
                result1 += nums[i];
              }
            });
    Thread t2 =
        new Thread(
            () -> {
              for (int i = nums.length / 2; i < nums.length; i++) {
                result2 += nums[i];
              }
            });

    long start = System.currentTimeMillis();

    t1.start();
    t2.start();

    t1.join();
    t2.join();

    result = result1 + result2;

    long end = System.currentTimeMillis();

    System.out.println(
        "两个线程计算1亿个数的和-m2-计算所用时间: " + (end - start) + ",计算结果 result:" + df.format(result));
  }

  // --------------很多线程计算1亿个数的和--------------- //

  /**
   * description: 很多线程计算1亿个数的和 <br>
   * date: 2022/8/11 15:21 <br>
   * author: flygo <br>
   *
   * @return void
   */
  private static void m3() throws InterruptedException {
    final int threadCount = 100000;
    Thread[] threads = new Thread[threadCount];
    double[] results = new double[threadCount];

    final int segmentCount = nums.length / threadCount;
    CountDownLatch latch = new CountDownLatch(threadCount);

    for (int i = 0; i < threadCount; i++) {
      int m = i;
      threads[i] =
          new Thread(
              () -> {
                for (int j = m * segmentCount; j < (m + 1) * segmentCount && j < nums.length; j++) {
                  results[m] += nums[j];
                }
              });
      latch.countDown();
    }

    double resultM3 = 0.0;

    long start = System.currentTimeMillis();
    for (Thread t : threads) {
      t.start();
    }

    latch.await();
    for (int i = 0; i < results.length; i++) {
      resultM3 += results[i];
    }

    long end = System.currentTimeMillis();

    System.out.println(
        threadCount
            + "个线程计算1亿个数的和-m3-计算所用时间: "
            + (end - start)
            + ",计算结果 result:"
            + df.format(result));
  }

  public static void main(String[] args) throws InterruptedException {
    // 单线程计算1亿个数的和
    m1();
    // 两个线程计算1亿个数的和
    m2();
    // 很多线程计算1亿个数的和
    m3();
  }
}
