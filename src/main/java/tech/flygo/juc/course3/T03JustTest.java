package tech.flygo.juc.course3;

import org.openjdk.jol.info.ClassLayout;

/**
 * @description: 一个简单的测试
 * @author: flygo
 * @time: 2022/8/19 10:57
 */
public class T03JustTest {

  public static void main(String[] args) {
    Object o = new Object();

    String s = ClassLayout.parseInstance(o).toPrintable();
    System.out.println(s);
  }
}
