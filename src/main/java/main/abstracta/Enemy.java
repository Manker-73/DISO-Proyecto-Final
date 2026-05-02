package main.abstracta;

import main.enums.World;
import main.factory.Action;
import main.strategy.EnemyStrategy;

public abstract class Enemy extends Character {

    protected World world;
    protected EnemyStrategy enemyStrategy;


    public Enemy(World world, EnemyStrategy enemyStrategy){
        super();
        this.world = world;
        this.enemyStrategy = enemyStrategy;
    }

    public abstract Action nextAction();
}
