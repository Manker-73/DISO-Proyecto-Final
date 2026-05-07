package main.enemies;

import main.abstracta.Wizard;
import main.actions.Action;
import main.actions.SpellCast;
import main.decorator.PowerfulAttackDecorator;
import main.enums.World;
import main.strategy.EnemyStrategy;

public class DesertWizard extends Wizard {

    public DesertWizard(EnemyStrategy enemyStrategy){
        super(World.JUNGLE, enemyStrategy);
        this.vida = 45.0;
        this.fuerza= 40.0;
        this.resistencia= 85.0;
        this.agilidad = 10.0;
        this.maxVida = 45.0;
        this.nombre = "DesertWizard";

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
        Action hechizo = new SpellCast("hechizo Tormenta de arena");
        Action hechizoPotenciado = new PowerfulAttackDecorator(hechizo);
        return hechizoPotenciado;
    }

    @Override
    public String toString() {
        return "DesertWizard | Vida: " + vida + " | Fuerza: " + fuerza + " | Resistencia: " + resistencia + " | Agilidad: " + agilidad;
    }

}
