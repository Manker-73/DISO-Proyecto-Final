package main.controller;

import main.abstracta.Enemy;
import main.abstracta.Warrior;
import main.abstracta.Wizard;
import main.combat.CombatManager;
import main.console.ConsoleUI;
import main.console.GameRenderer;
import main.console.TurnMenu;
import main.enums.World;
import main.factory.DesertEnemyFactory;
import main.factory.EnemyFactory;
import main.factory.JungleEnemyFactory;
import main.factory.SeaEnemyFactory;
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

        if(ronda == 1){
            System.out.println("  Mundo: " + ConsoleUI.YELLOW + player.getWorld() + ConsoleUI.RESET);
        } else {
            int mundoAleatorio = (int)(Math.random() * 3);
            switch(mundoAleatorio){
                case 0 -> {
                    enemyFactory = new JungleEnemyFactory();
                    player.setWorld(World.JUNGLE);
                    System.out.println("  Mundo: " + ConsoleUI.YELLOW + "JUNGLE" + ConsoleUI.RESET);
                }
                case 1 -> {
                    enemyFactory = new SeaEnemyFactory();
                    player.setWorld(World.SEA);
                    System.out.println("  Mundo: " + ConsoleUI.YELLOW + "SEA" + ConsoleUI.RESET);
                }
                case 2 -> {
                    enemyFactory = new DesertEnemyFactory();
                    player.setWorld(World.DESERT);
                    System.out.println("  Mundo: " + ConsoleUI.YELLOW + "DESERT" + ConsoleUI.RESET);
                }
            }
        }

        int nivelDificultad = (ronda - 1) / 3;
        double multiplicador = 1.0 + (nivelDificultad * 0.3);

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

        enemy.setFuerza(enemy.getFuerza() * multiplicador);
        enemy.setResistencia(enemy.getResistencia() * multiplicador);
        enemy.setVida(enemy.getVida() * multiplicador);
        enemy.setMaxVida(enemy.getMaxVida() * multiplicador);

        if(enemy instanceof Warrior) ((Warrior)enemy).setObjetivo(player);
        if(enemy instanceof Wizard)  ((Wizard)enemy).setObjetivo(player);

        TurnMenu turnMenu = new TurnMenu(scanner);
        CombatManager cm = new CombatManager(player, enemy, turnMenu);

        if(cm.startCombat()){
            player.recibirExperiencia(ronda * Math.random()*10);
            finalizarRonda();
            siguienteRonda();

            if(ronda % 3 == 0){
                player.setVida(player.getMaxVida());
                System.out.println(ConsoleUI.GREEN + ConsoleUI.BOLD
                        + "\n  ¡Descansas y recuperas toda tu vida!"
                        + ConsoleUI.RESET);
            }
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

    public int getRonda() {
        return ronda;
    }
}
