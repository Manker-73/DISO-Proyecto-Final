package main.abstracta;

import main.enums.World;
import main.strategy.EnemyStrategy;

public abstract class Wizard extends Enemy{
    public static final Integer ALCANCE_DEF = 10;

    private Integer alcance;

    public Wizard(World world, EnemyStrategy enemyStrategy){
        this(ALCANCE_DEF, world, enemyStrategy);
    }

    public Wizard(Integer alcance,  World world, EnemyStrategy enemyStrategy){
        super(world,enemyStrategy);
        this.alcance = alcance;
    }
    public abstract void lanzarHechizo();

    public Integer getAlcance() {
        return alcance;
    }
}
