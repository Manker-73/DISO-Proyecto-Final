package main.enemies;

import main.abstracta.Wizard;
import main.actions.Action;
import main.actions.Attack;
import main.decorator.PoisonedAttackDecorator;
import main.enums.World;
import main.strategy.EnemyStrategy;

public class JungleWizard extends Wizard {

    public JungleWizard(EnemyStrategy enemyStrategy){
        super(World.JUNGLE, enemyStrategy);
        this.vida = 60.0;
        this.fuerza = 12.0;
        this.resistencia = 8.0;
        this.agilidad = 8.0;
        this.maxVida = 60.0;
        this.nombre = "JungleWizard";

    }
    @Override
    public void lanzarHechizo() {
        nextAction();
    }

    @Override
    protected Action elegirAccionConcreta(Action estrategia) {
        Action hechizo = new Attack();
        Action hechizoVeneno = new PoisonedAttackDecorator(hechizo);
        return hechizoVeneno;
    }

    @Override
    public String toString() {
        return "JungleWizard | Vida: " + vida + " | Fuerza: " + fuerza + " | Resistencia: " + resistencia + " | Agilidad: " + agilidad;
    }

}
