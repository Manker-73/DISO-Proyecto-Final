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
        this.vida = 50.0;
        this.fuerza= 20.0;
        this.resistencia= 50.0;
        this.agilidad = 10.0;
        this.maxVida = 50.0;
        this.nombre = "JungleWizard";

    }
    @Override
    public void lanzarHechizo() {
        nextAction();
    }

    @Override
    protected void evaluarSituacion() {
        if (objetivo.getVida() >= 30) {
            System.out.println("Objetivo con vida alta, lanzamos hechizo");
        } else {
            System.out.println("Objetivo débil, rematamos");
        }
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
