package dwemthy;

import static dwemthy.Kernel.*;

public class BattleOfGrottoOfSausageSmells {

  private static class ScubaArgentine extends Creature.Base {
    {
      life = 46;
      strength = 35;
      charisma = 91;
      weapon = 2;
    }
    // Need a command if you want to play as Scuba!
    @Command('&') public void slime(Creature enemy) {
      fight(enemy, 10);
    }

  }

  public static void main(String[] args) {
    battle(new Rabbit(), new ScubaArgentine());
    // Play as scuba...
    // battle(new ScubaArgentine(),new Rabbit());
  }

}
