package main.factory;

import main.abstracta.Warrior;
import main.abstracta.Wizard;
import main.strategy.EnemyStrategy;

public class SeaEnemyFactory implements EnemyFactory{

    @Override
    public Warrior createWarrior(EnemyStrategy enemyStrategy) {
        throw new UnsupportedOperationException("Pendiente Persona 3");
        //TODO
        //return new SeaWarrior() ;
    }

    @Override
    public Wizard createWizard(EnemyStrategy enemyStrategy) {
        throw new UnsupportedOperationException("Pendiente Persona 3");
        //TODO
        //return new SeaWizard();
    }
}
