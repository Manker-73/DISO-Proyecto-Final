package main.controller;

import main.abstracta.Enemy;
import main.abstracta.Warrior;
import main.abstracta.Wizard;
import main.abstracta.Warrior;
import main.combat.CombatManager;
import main.console.GameRenderer;
import main.console.TurnMenu;
import main.factory.EnemyFactory;
import main.model.Player;
import main.strategy.AggressiveStrategy;
import main.strategy.DefensiveStrategy;
import main.strategy.EnemyStrategy;
import main.strategy.RandomStrategy;

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
    private GameRenderer gameRenderer;

    private GameController(){
        enemiesRound = new ArrayList<Enemy>();
        this.ronda = 1;
        gameRenderer = new GameRenderer();
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
        this.ronda = 1;
        this.scanner = new Scanner(System.in);
        this.enemiesRound.clear();
        player.inicializar();
    }
    public void empezarPartida(){
        player.inicializar();
        while(player.estaVivo()){
            empezarRonda();
        }
        terminarPartida();
    }

    public void empezarRonda(){
        System.out.println("\n  Ronda " + ronda);

        EnemyStrategy strategy = ronda <= 2
                ? new DefensiveStrategy()
                : ronda <= 4
                  ? new AggressiveStrategy()
                  : new RandomStrategy();

        Enemy enemy;
        if(ronda % 2 == 0){
            enemy = enemyFactory.createWizard(strategy);
        } else {
            enemy = enemyFactory.createWarrior(strategy);
        }

        if(enemy instanceof Warrior) ((Warrior)enemy).setObjetivo(player);
        if(enemy instanceof Wizard)  ((Wizard)enemy).setObjetivo(player);

        TurnMenu turnMenu = new TurnMenu(scanner);
        CombatManager cm = new CombatManager(player, enemy, turnMenu);

        if(cm.startCombat()){
            player.recibirExperiencia(50.0 * ronda);
            finalizarRonda();
            siguienteRonda();
        }
    }

    public void jugarTurno(){
        System.out.println("  Turno en curso...");
    }

    public void finalizarRonda(){
        gameRenderer.renderVictory(ronda, player.getNivel() * 100);
    }
    public void siguienteRonda(){
        ronda++;
    }

    public void terminarPartida(){
        gameRenderer.renderGameOver(player.getNombre(), player.getNivel() * 100);
    }
}
