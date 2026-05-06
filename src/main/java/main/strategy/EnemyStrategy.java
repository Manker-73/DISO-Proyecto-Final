package main.strategy;

import main.abstracta.Enemy;
import main.actions.Action;
import main.abstracta.Character;

public interface EnemyStrategy {
    Action decidirAction(Enemy enemy, Character character);
}
