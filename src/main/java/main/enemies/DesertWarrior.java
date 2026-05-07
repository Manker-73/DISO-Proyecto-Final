package main.enemies;

import main.abstracta.Warrior;
import main.actions.Action;
import main.actions.SwordAttack;
import main.decorator.PowerfulAttackDecorator;
import main.enums.World;
import main.strategy.EnemyStrategy;

public class DesertWarrior extends Warrior {

    public DesertWarrior(EnemyStrategy enemyStrategy){
        super(World.DESERT, enemyStrategy);
        this.vida = 90.0;
        this.fuerza= 55.0;
        this.resistencia= 95.0;
        this.agilidad = 90.0;
        this.maxVida = 90.0;
        this.nombre = "DesertWarrior";

    }
    @Override
    public void atacarCuerpoACuerpo() {
        nextAction();
    }

    @Override
    protected void evaluarSituacion() {
        if (objetivo.getVida() >= 60) {
            System.out.println("Objetivo con vida alta, atacamos");
        } else {
            System.out.println("Objetivo débil, rematamos");
        }
    }

    @Override
    protected Action elegirAccionConcreta(Action estrategia) {
        Action espada = new SwordAttack();
        Action espadaPoderosa = new PowerfulAttackDecorator(espada);
        return espadaPoderosa;
    }

    @Override
    public String toString() {
        return "DesertWarrior | Vida: " + vida + " | Fuerza: " + fuerza + " | Resistencia: " + resistencia + " | Agilidad: " + agilidad;
    }

}

