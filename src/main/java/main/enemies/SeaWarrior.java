package main.enemies;

import main.abstracta.Warrior;
import main.actions.Action;
import main.actions.Attack;
import main.decorator.ConfusedAttackDecorator;
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
    protected Action elegirAccionConcreta(Action estrategia) {
        Action espada = new Attack();
        return new ConfusedAttackDecorator(espada);
    }

    @Override
    public String toString() {
        return "SeaWarrior | Vida: " + vida + " | Fuerza: " + fuerza + " | Resistencia: " + resistencia + " | Agilidad: " + agilidad;
    }

}
