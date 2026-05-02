package main.strategy;

import main.abstracta.Enemy;
import main.factory.Action;

public interface EnemyStrategy {
    Action decidirAction(Enemy enemy, Character character);
}
