package dwemthy;

import java.io.*;

public abstract class Kernel {

  public static int rand(int value) {
    try {
      return (int) (Math.random() * value);
    } catch (Exception e) { throw new RuntimeException(e); }
  }

  public static void puts(String string, Object... args) {
    System.out.printf(string, args);
    System.out.println();
  }

  public static void puts(Object one) {
    System.out.println(one);
  }

  public static Character getChar() {
    try {
      return new BufferedReader(new InputStreamReader(System.in)).readLine().charAt(0);
    } catch (IOException e) { throw new RuntimeException(e); }
  }

  public static void battle(Creature c1, Creature c2) {    
    while (c1.life() > 0 && c2.life() > 0) {
      puts(c1);
      c1.command(getChar(), c2);
    }
  }

  public static void autoBattle(Creature c1, Creature c2) {
    while (c1.life() > 0 && c2.life() > 0) {
      puts(c1);
      c1.randCommand(c2);
    }
  }

}
