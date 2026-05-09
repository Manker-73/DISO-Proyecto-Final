package main.factory;

import main.abstracta.Warrior;
import main.abstracta.Wizard;
import main.enemies.JungleWarrior;
import main.enemies.JungleWizard;
import main.strategy.EnemyStrategy;

public class JungleEnemyFactory implements EnemyFactory{

    @Override
    public Warrior createWarrior(EnemyStrategy enemyStrategy) {
        return new JungleWarrior(enemyStrategy) ;
    }

    @Override
    public Wizard createWizard(EnemyStrategy enemyStrategy) {
        return new JungleWizard(enemyStrategy);
    }
}
