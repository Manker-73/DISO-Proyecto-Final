package main.abstracta;

import main.actions.Action;
import main.enums.World;
import main.strategy.EnemyStrategy;

public abstract class Wizard extends Enemy{
    public static final Integer ALCANCE_DEF = 10;

    private Integer alcance;

    protected Character objetivo;

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

    public void setObjetivo(Character objetivo) {
        this.objetivo = objetivo;
    }

    @Override
    public Action nextAction() {
        Action estrategia= this.consultarEstrategia();
        Action elegida =this.elegirAccionConcreta(estrategia);
        Action ultimaAccion= this.aplicarModificadores(elegida);
        return ultimaAccion;
    }

    protected Action consultarEstrategia(){
        Action action= enemyStrategy.decidirAction(this, objetivo);
        return action;
    }
    protected Action aplicarModificadores(Action accion){
        return accion;
    }

    protected abstract Action elegirAccionConcreta(Action estrategia);

}
