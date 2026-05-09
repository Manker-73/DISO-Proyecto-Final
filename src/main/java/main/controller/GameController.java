package main.controller;

import main.abstracta.Enemy;
import main.combat.CombatManager;
import main.console.TurnMenu;
import main.factory.EnemyFactory;
import main.model.Player;
import main.strategy.AggressiveStrategy;
import main.strategy.DefensiveStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameController {
    private static GameController gameController;
    private Player player;
    private List<Enemy> enemiesRound;
    private EnemyFactory enemyFactory;
    private Scanner scanner;
    private int ronda;

    private GameController(){
        enemiesRound = new ArrayList<Enemy>();
        this.ronda = 1;
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
        this.ronda =1;
        this.scanner = new Scanner(System.in);
        this.player.setVida(100.0);
    }
    public void empezarPartida(){
        while(player.estaVivo()){
            empezarRonda();
        }
        terminarPartida();
    }

    public void empezarRonda(){
        System.out.println("\n  Ronda " + ronda);
        var strategy = ronda <= 2 ? new DefensiveStrategy() : new AggressiveStrategy();
        Enemy enemy = enemyFactory.createWarrior(strategy);
        TurnMenu turnMenu = new TurnMenu(scanner);
        CombatManager cm = new CombatManager(player, enemy, turnMenu);
        boolean ganado = cm.startCombat();
        finalizarRonda();
        if(ganado){
            player.recibirExperiencia(50.0 * ronda);
            siguienteRonda();
        }
    }

    public void jugarTurno(){
        System.out.println("  Turno en curso...");
    }

    public void finalizarRonda(){
        System.out.println("  Ronda " + ronda + " finalizada.");
    }
    public void siguienteRonda(){
        ronda++;
    }

    public void terminarPartida(){
        System.out.println("\n  Partida terminada. Nivel alcanzado: " + player.getNivel());
    }
}
