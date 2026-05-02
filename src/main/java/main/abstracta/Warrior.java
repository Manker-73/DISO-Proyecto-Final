package main.abstracta;

import main.enums.World;
import main.strategy.EnemyStrategy;

public abstract class Warrior extends Enemy{
    public static final Integer ALCANCE_DEF = 2;

    private Integer alcance;

    public Warrior(World world, EnemyStrategy enemyStrategy){
        this(ALCANCE_DEF, world, enemyStrategy);
    }

    public Warrior(Integer alcance,  World world, EnemyStrategy enemyStrategy){
        super(world,enemyStrategy);
        this.alcance = alcance;
    }
    public abstract void atacarCuerpoACuerpo();

    public Integer getAlcance() {
        return alcance;
    }
}
