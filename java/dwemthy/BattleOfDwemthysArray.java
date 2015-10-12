package dwemthy;

import static dwemthy.Kernel.*;

/**
 * Since days, an army of totalitarian rabbits invades my brain. DWEMTHY'S
 * ARRAY, MOAR, DWEMTHY'S ARRAY, MOAR SAUCE, MOAR!!!!1!1!, they cry. <br>
 *
 * @author Clinton Begin <clinton.begin@gmail.com>
 *         adapted from source by Adrian Kuhn <akuhn@iam.unibe.ch>
 */
public class BattleOfDwemthysArray {
  private static class IndustrialRaverMonkey extends Creature.Base {
    {
      life = 46;
      strength = 35;
      charisma = 91;
      weapon = 2;
    }
  }

  private static class DwarvenAngel extends Creature.Base {
    {
      life = 540;
      strength = 6;
      charisma = 144;
      weapon = 50;
    }
  }

  private static class AssistantViceTentacleAndOmbudsman extends Creature.Base {
    {
      life = 320;
      strength = 6;
      charisma = 144;
      weapon = 50;
    }
  }

  private static class TeethDeer extends Creature.Base {
    {
      life = 655;
      strength = 192;
      charisma = 19;
      weapon = 109;
    }
  }

  private static class IntrepidDecomposedCyclist extends Creature.Base {
    {
      life = 901;
      strength = 560;
      charisma = 422;
      weapon = 105;
    }
  }

  private static class Dragon extends Creature.Base {
    {
      life = 1340; // tough scales
      strength = 451; // bristling veins
      charisma = 1020; // toothy smile
      weapon = 939; // fire breath
    }
  }

  public static void main(String[] args) {
    Creature rabbit = new Rabbit();
    Creature dwemthy = DwemthysArray.asCreature(
        new IndustrialRaverMonkey(),
        new DwarvenAngel(),
        new AssistantViceTentacleAndOmbudsman(),
        new TeethDeer(),
        new IntrepidDecomposedCyclist(),
        new Dragon()
    );
    battle(rabbit, dwemthy);
    //How many rabbits does it take?
    //for (int i=0; i < 1000; i++) autoBattle(new Rabbit(), dwemthy);
  }

}
