package dwemthy;

import static dwemthy.Kernel.*;

public class Rabbit extends Creature.Base {
  {
    bombs = 3;
    life = 10;
    strength = 2;
    charisma = 44;
    weapon = 4;
  }

  private int bombs;
  @Trait public int bombs() {
    return bombs;
  }

  // little boomerang
  @Command('^') public void boomerang(Creature enemy) {
    fight(enemy, 13);
  }

  // the hero's sword is unlimited!!
  @Command('/') public void sword(Creature enemy) {
    fight(enemy, rand(4 + (enemy.life() % 10) * (enemy.life() % 10)));
  }

  // lettuce will build your strength and extra ruffage
  // will fly in the face of your opponent!!
  @Command('%') public void lettuce(Creature enemy) {
    int lettuce = rand(charisma);
    Kernel.puts("[Healthy lettuce gives you %d life points!!]", lettuce);
    life += lettuce;
    fight(enemy, 0);
  }

  // bombs, but you only have three!!
  @Command('*') public void bomb(Creature enemy) {
    if (bombs == 0) {
      puts("[UHN!! You're out of bombs!!]");
      return;
    }
    bombs -= 1;
    fight(enemy, 86);
  }

}