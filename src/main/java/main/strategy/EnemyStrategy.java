package main.strategy;

import main.abstracta.Enemy;
import main.actions.Action;

public interface EnemyStrategy {
    Action decidirAction(Enemy enemy, Character character);
}
