package main.enemies;

import main.abstracta.Wizard;
import main.actions.Action;
import main.actions.SpellCast;
import main.decorator.QuickAttackDecorator;
import main.enums.World;
import main.strategy.EnemyStrategy;

public class SeaWizard extends Wizard {

    public SeaWizard(EnemyStrategy enemyStrategy){
        super(World.SEA, enemyStrategy);
        this.vida = 30.0;
        this.fuerza= 40.0;
        this.resistencia= 80.0;
        this.agilidad = 40.0;
        this.maxVida = 30.0;
        this.nombre = "SeaWizard";

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
        Action hechizo = new SpellCast("hechizo Asfixiador");
        Action hechizoRapido = new QuickAttackDecorator(hechizo);
        return hechizoRapido;
    }

    @Override
    public String toString() {
        return "SeaWizard | Vida: " + vida + " | Fuerza: " + fuerza + " | Resistencia: " + resistencia + " | Agilidad: " + agilidad;
    }

}
