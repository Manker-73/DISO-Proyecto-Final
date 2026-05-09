package main.strategy;

import main.abstracta.Enemy;
import main.abstracta.Character;
import main.actions.Action;

public class DefensiveStrategy implements EnemyStrategy {
    @Override
    public Action decidirAction(Enemy enemy, Character character) {
        return enemy.nextAction();
    }
}