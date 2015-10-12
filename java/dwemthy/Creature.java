package dwemthy;

import static dwemthy.Kernel.*;

import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.*;

public interface Creature {
  @Trait int life();
  @Trait int strength();
  @Trait int charisma();
  @Trait int weapon();

  void hit(int damage);
  void fight(Creature enemy, int weapon);
  void command(Character op, Object... args);
  void randCommand(Object... args);

  public static class Base implements Creature {
    protected int life, strength, charisma, weapon;
    protected Map<Character, Method> COMMANDS = new HashMap<Character, Method>();

    public Base() {
      Class type = this.getClass();
      for (Method m : type.getMethods()) {
        Command command = m.getAnnotation(Command.class);
        if (command != null) {
          COMMANDS.put(command.value(), m);
        }
      }
    }

    @Trait public int life() {
      return life;
    }

    @Trait public int strength() {
      return strength;
    }

    @Trait public int charisma() {
      return charisma;
    }

    @Trait public int weapon() {
      return weapon;
    }

    public void hit(int damage) {
      int powerUp = rand(charisma);
      if (powerUp % 9 == 7) {
        life += powerUp / 4;
        puts("[%s magick powers up %d!]", getClass().getSimpleName(), powerUp);
      }
      life -= damage;
      if (life <= 0) puts("[%s has died.]", getClass().getSimpleName());
    }

    public void fight(Creature enemy, int weapon) {
      if (life <= 0) {
        puts("[%s is too dead to fight!]", getClass().getSimpleName());
        return;
      }
      // Attack the opponent
      int yourHit = rand(strength + weapon);
      puts("[You hit with %d points of damage!]", yourHit);
      enemy.hit(yourHit);
      // Retaliation
      puts(enemy);
      if (enemy.life() > 0) {
        int enemyHit = rand(enemy.strength() + enemy.weapon());
        puts("[Your enemy hit with %d points of damage!]", enemyHit);
        this.hit(enemyHit);
      }
    }

    public String toString() {
      try {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%s #<%s:0x%s", getClass().getSimpleName(), this.getClass().getSimpleName(), Integer.toString(hashCode(), 0x10)));
        for (Method method : this.getClass().getMethods()) {
          if (method.getAnnotation(Trait.class) != null) {
            builder.append(String.format(" @%s=%s", method.getName(), method.invoke(this)));
          }
        }
        return builder.append(">").toString();
      } catch (Exception e) { throw new RuntimeException(e); }
    }

    public void command(Character op, Object... args) {
      try {
        COMMANDS.get(op).invoke(this, args);
      } catch (Exception e) { throw new RuntimeException(e); }
    }

    public void randCommand(Object... args) {
      try {
        new ArrayList<Map.Entry<Character,Method>>(COMMANDS.entrySet()).get(rand(COMMANDS.size())).getValue().invoke(this, args);
      } catch (Exception e) { throw new RuntimeException(e); }
    }

  }

  @Target(ElementType.METHOD)
  @Retention(RetentionPolicy.RUNTIME)
  public @interface Trait {
  }

  @Target(ElementType.METHOD)
  @Retention(RetentionPolicy.RUNTIME)
  public @interface Command {
    char value();
  }

}