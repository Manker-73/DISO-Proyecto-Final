package main.enemies;

import main.abstracta.Wizard;
import main.actions.Action;
import main.actions.Attack;
import main.decorator.ParalyzedAttackDecorator;
import main.enums.World;
import main.strategy.EnemyStrategy;

public class DesertWizard extends Wizard {

    public DesertWizard(EnemyStrategy enemyStrategy){
        super(World.DESERT, enemyStrategy);
        this.vida = 90.0;
        this.fuerza = 22.0;
        this.resistencia = 16.0;
        this.agilidad = 12.0;
        this.maxVida = 90.0;
        this.nombre = "DesertWizard";

    }
    @Override
    public void lanzarHechizo() {
        nextAction();
    }

    @Override
    protected Action elegirAccionConcreta(Action estrategia) {
        Action hechizo = new Attack();
        return new ParalyzedAttackDecorator(hechizo);
    }

    @Override
    public String toString() {
        return "DesertWizard | Vida: " + vida + " | Fuerza: " + fuerza + " | Resistencia: " + resistencia + " | Agilidad: " + agilidad;
    }

}
