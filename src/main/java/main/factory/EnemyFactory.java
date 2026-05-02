package main.factory;

import main.abstracta.Warrior;
import main.abstracta.Wizard;
import main.strategy.EnemyStrategy;

public interface EnemyFactory {
    Warrior createWarrior(EnemyStrategy enemyStrategy);
    Wizard createWizard(EnemyStrategy enemyStrategy);
}
