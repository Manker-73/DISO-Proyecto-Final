package main.enemies;

import main.abstracta.Wizard;
import main.actions.Action;
import main.actions.Attack;
import main.decorator.QuickAttackDecorator;
import main.enums.World;
import main.strategy.EnemyStrategy;

public class SeaWizard extends Wizard {

    public SeaWizard(EnemyStrategy enemyStrategy){
        super(World.SEA, enemyStrategy);
        this.vida = 70.0;
        this.fuerza = 18.0;
        this.resistencia = 12.0;
        this.agilidad = 14.0;
        this.maxVida = 70.0;
        this.nombre = "SeaWizard";

    }
    @Override
    public void lanzarHechizo() {
        nextAction();
    }


    @Override
    protected Action elegirAccionConcreta(Action estrategia) {
        Action hechizo = new Attack();
        Action hechizoRapido = new QuickAttackDecorator(hechizo);
        return hechizoRapido;
    }

    @Override
    public String toString() {
        return "SeaWizard | Vida: " + vida + " | Fuerza: " + fuerza + " | Resistencia: " + resistencia + " | Agilidad: " + agilidad;
    }

}
