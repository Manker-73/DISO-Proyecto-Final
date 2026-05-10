package main.strategy;

import main.abstracta.Enemy;
import main.abstracta.Character;
import main.actions.Action;
import java.util.Random;

public class RandomStrategy implements EnemyStrategy {
    private final AggressiveStrategy aggressive = new AggressiveStrategy();
    private final DefensiveStrategy defensive   = new DefensiveStrategy();
    private final Random random                 = new Random();

    @Override
    public Action decidirAction(Enemy enemy, Character character) {
        if(random.nextBoolean()){
            return aggressive.decidirAction(enemy, character);
        } else {
            return defensive.decidirAction(enemy, character);
        }
    }
}