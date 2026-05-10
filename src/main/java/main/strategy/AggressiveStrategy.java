package main.strategy;

import main.abstracta.Enemy;
import main.abstracta.Character;
import main.actions.Action;
import main.actions.Attack;

public class AggressiveStrategy implements EnemyStrategy {
    @Override
    public Action decidirAction(Enemy enemy, Character character) {
        return new Attack();
    }
}