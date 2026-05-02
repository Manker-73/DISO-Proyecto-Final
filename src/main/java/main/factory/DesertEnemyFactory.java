package main.factory;

import main.abstracta.Warrior;
import main.abstracta.Wizard;
import main.strategy.EnemyStrategy;

public class DesertEnemyFactory implements EnemyFactory{

    @Override
    public Warrior createWarrior(EnemyStrategy enemyStrategy) {
        throw new UnsupportedOperationException("Pendiente Persona 3");
        //TODO
        //return new DesertWarrior() ;
    }

    @Override
    public Wizard createWizard(EnemyStrategy enemyStrategy) {
        throw new UnsupportedOperationException("Pendiente Persona 3");
        //TODO
        //return new DesertWizard();
    }
}
