package main.abstracta;

import main.actions.Action;
import main.enums.World;
import main.strategy.EnemyStrategy;

public abstract class Warrior extends Enemy{
    public static final Integer ALCANCE_DEF = 2;

    private final Integer alcance;

    protected Character objetivo;

    public Warrior(World world, EnemyStrategy enemyStrategy){
        this(ALCANCE_DEF, world, enemyStrategy);
    }

    public Warrior(Integer alcance,  World world, EnemyStrategy enemyStrategy){
        super(world, enemyStrategy);
        this.alcance = alcance;
    }
    public abstract void atacarCuerpoACuerpo();

    public Integer getAlcance() {
        return alcance;
    }

    public void setObjetivo(Character objetivo) {
        this.objetivo = objetivo;
    }

    @Override
    public Action nextAction() {
        Action estrategia= this.consultarEstrategia();
        Action elegida =this.elegirAccionConcreta(estrategia);
        return this.aplicarModificadores(elegida);
    }

    protected Action consultarEstrategia(){
        return enemyStrategy.decidirAction(this, objetivo);
    }
    protected Action aplicarModificadores(Action accion){
        return accion;
    }

    protected abstract Action elegirAccionConcreta(Action estrategia);
}
