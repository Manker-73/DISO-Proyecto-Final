package main.factory;

import main.abstracta.Warrior;
import main.abstracta.Wizard;
import main.enemies.DesertWarrior;
import main.enemies.DesertWizard;
import main.strategy.EnemyStrategy;

public class DesertEnemyFactory implements EnemyFactory{

    @Override
    public Warrior createWarrior(EnemyStrategy enemyStrategy) {
        return new DesertWarrior(enemyStrategy) ;
    }

    @Override
    public Wizard createWizard(EnemyStrategy enemyStrategy) {
        return new DesertWizard(enemyStrategy);
    }
}
