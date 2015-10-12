package dwemthy;

import static dwemthy.Kernel.puts;

import java.lang.reflect.*;
import java.util.*;

public class DwemthysArray extends ArrayList<Creature>
    implements InvocationHandler {

  private Creature current;

  private DwemthysArray(Creature... creatures) {
    addAll(Arrays.asList(creatures));
    nextCreature();
  }

  public static Creature asCreature(Creature... creatures) {
    Creature creature = (Creature) Proxy.newProxyInstance(
        Creature.class.getClassLoader(), new Class[]{Creature.class}, new DwemthysArray(creatures));
    return creature;
  }

  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    try {
      return method.invoke(current, args);
    } finally {
      Creature.Trait trait = method.getAnnotation(Creature.Trait.class);
      if (trait != null && current.life() <= 0) {
        nextCreature();
      }
    }
  }

  private void nextCreature() {
    if (size() == 0) {
      puts("[Whoa.  You decimated Dwemthy's Array!]");
    } else {
      current = remove(0);
      Kernel.puts("[Get ready. %s has emerged.]", current.getClass().getSimpleName());
    }
  }
}
