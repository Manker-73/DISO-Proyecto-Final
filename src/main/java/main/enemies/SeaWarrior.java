package main.enemies;

import main.abstracta.Warrior;
import main.actions.Action;
import main.actions.Attack;
import main.decorator.PowerfulAttackDecorator;
import main.enums.World;
import main.strategy.EnemyStrategy;

public class SeaWarrior extends Warrior {

    public SeaWarrior(EnemyStrategy enemyStrategy){
        super(World.SEA, enemyStrategy);
        this.vida = 100.0;
        this.fuerza = 20.0;
        this.resistencia = 15.0;
        this.agilidad = 10.0;
        this.maxVida = 100.0;
        this.nombre = "SeaWarrior";

    }
    @Override
    public void atacarCuerpoACuerpo() {
        nextAction();
    }

    @Override
    protected void evaluarSituacion() {
        if (objetivo.getVida() >= 30) {
            System.out.println("Objetivo con vida alta, atacamos");
        } else {
            System.out.println("Objetivo débil, rematamos");
        }
    }

    @Override
    protected Action elegirAccionConcreta(Action estrategia) {
        Action espada = new Attack();
        Action espadaDecorada = new PowerfulAttackDecorator(espada);
        return espadaDecorada;
    }

    @Override
    public String toString() {
        return "SeaWarrior | Vida: " + vida + " | Fuerza: " + fuerza + " | Resistencia: " + resistencia + " | Agilidad: " + agilidad;
    }

}
