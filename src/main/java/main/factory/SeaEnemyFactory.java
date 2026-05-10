package main.factory;

import main.abstracta.Warrior;
import main.abstracta.Wizard;
import main.enemies.SeaWarrior;
import main.enemies.SeaWizard;
import main.strategy.EnemyStrategy;

public class SeaEnemyFactory implements EnemyFactory{

    @Override
    public Warrior createWarrior(EnemyStrategy enemyStrategy) {
        return new SeaWarrior(enemyStrategy) ;
    }

    @Override
    public Wizard createWizard(EnemyStrategy enemyStrategy) {
        return new SeaWizard(enemyStrategy);
    }
}
