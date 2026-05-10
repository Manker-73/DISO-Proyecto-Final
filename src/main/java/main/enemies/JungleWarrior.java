package main.enemies;

import main.abstracta.Warrior;
import main.actions.Action;
import main.actions.SwordAttack;
import main.decorator.PoisonedAttackDecorator;

import main.enums.World;
import main.strategy.EnemyStrategy;

public class JungleWarrior extends Warrior {

    public JungleWarrior(EnemyStrategy enemyStrategy){
        super(World.JUNGLE, enemyStrategy);
        this.vida = 80.0;
        this.fuerza = 15.0;
        this.resistencia = 10.0;
        this.agilidad = 12.0;
        this.maxVida = 80.0;
        this.nombre = "JungleWarrior";

    }
    @Override
    public void atacarCuerpoACuerpo() {
        nextAction();
    }


    @Override
    protected Action elegirAccionConcreta(Action estrategia) {
        Action espada = new SwordAttack();
        Action espadaEnvenenada = new PoisonedAttackDecorator(espada);
        return espadaEnvenenada;
    }

    @Override
    public String toString() {
        return "JungleWarrior | Vida: " + vida + " | Fuerza: " + fuerza + " | Resistencia: " + resistencia + " | Agilidad: " + agilidad;
    }

}
