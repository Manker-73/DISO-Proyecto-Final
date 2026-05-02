package main.controller;

import main.abstracta.Enemy;
import main.factory.EnemyFactory;
import main.model.Player;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    private static GameController gameController;
    private Player player;
    private List<Enemy> enemiesRound;
    private EnemyFactory enemyFactory;

    private GameController(){
        enemiesRound = new ArrayList<Enemy>();
    }

    public static GameController getInstance(){
        if(gameController == null){
            gameController = new GameController();
        }
        return gameController;
    }

    public void resetPartida(Player player, EnemyFactory enemyFactory){
        this.player = player;
        this.enemyFactory = enemyFactory;
    }
    public void empezarPartida(){}
    public void empezarRonda(){}
    public void jugarTurno(){}
    public void finalizarRonda(){}
    public void siguienteRonda(){}
    public void terminarPartida(){}
}
