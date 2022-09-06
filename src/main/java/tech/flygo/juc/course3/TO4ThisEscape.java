package tech.flygo.juc.course3;

import java.io.IOException;

/**
 * @description: this对象溢出
 * @author: flygo
 * @time: 2022/8/19 11:18
 */
public class TO4ThisEscape {

  private int num = 8;

  public TO4ThisEscape() {
    new Thread(() -> System.out.println(num)).start();
  }

  public static void main(String[] args) throws IOException {
    new TO4ThisEscape();
    System.in.read();
  }
}
