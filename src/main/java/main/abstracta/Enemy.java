package main.abstracta;

import main.enums.World;
import main.actions.Action;
import main.strategy.EnemyStrategy;
import main.state.NormalState;

public abstract class Enemy extends Character {

    protected World world;
    protected EnemyStrategy enemyStrategy;


    public Enemy(World world, EnemyStrategy enemyStrategy){
        super();
        this.world = world;
        this.enemyStrategy = enemyStrategy;
        this.estadoActual = new NormalState();
    }

    public abstract Action nextAction();
}
