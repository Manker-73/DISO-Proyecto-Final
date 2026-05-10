package main.console;

import main.factory.JungleEnemyFactory;
import main.factory.SeaEnemyFactory;
import main.factory.DesertEnemyFactory;
import main.controller.GameController;
import main.factory.EnemyFactory;
import main.model.Player;
import main.enums.World;

import java.util.Scanner;

public class ConsoleUI {


    public static final String RESET   = "\u001B[0m";
    public static final String BOLD    = "\u001B[1m";
    public static final String RED     = "\u001B[31m";
    public static final String GREEN   = "\u001B[32m";
    public static final String YELLOW  = "\u001B[33m";
    public static final String CYAN    = "\u001B[36m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String WHITE   = "\u001B[37m";

    private final Scanner          scanner;
    private final ScoreManager     scoreManager;
    private final CharacterCreator characterCreator;

    public ConsoleUI() {
        this.scanner           = new Scanner(System.in);
        this.scoreManager      = new ScoreManager();
        this.characterCreator  = new CharacterCreator(scanner);
    }




    public void start() {
        showWelcomeBanner();
        boolean running = true;
        while (running) {
            showMainMenu();
            switch (readInt(1, 3)) {
                case 1 -> playGame();
                case 2 -> scoreManager.showRanking();
                case 3 -> { running = false; showGoodbye(); }
            }
        }
        scanner.close();
    }



    private void playGame() {

        Player player = characterCreator.createPlayer();


        EnemyFactory factory = getFactoryForWorld(player.getWorld());


        GameController gc = GameController.getInstance();
        gc.resetPartida(player, factory);
        gc.empezarPartida();







        int puntuacion = player.getNivel() * 100;
        scoreManager.saveScore(player.getNombre(), puntuacion);

        System.out.println(RED + BOLD + "\n  Has caído en combate..." + RESET);
        System.out.println("  Puntuación final: " + YELLOW + BOLD + puntuacion + RESET);
        scoreManager.showRanking();
    }


    private EnemyFactory getFactoryForWorld(World world) {
        return switch (world) {
            case JUNGLE -> new JungleEnemyFactory();
            case SEA    -> new SeaEnemyFactory();
            case DESERT -> new DesertEnemyFactory();
        };
    }



    private void showWelcomeBanner() {
        System.out.println(MAGENTA + BOLD);
        System.out.println("  ╔═══════════════════════════════════════╗");
        System.out.println("  ║       DESIGN PATTERNS COMBAT          ║");
        System.out.println("  ║       El juego de rol en Java         ║");
        System.out.println("  ╚═══════════════════════════════════════╝");
        System.out.println(RESET);
    }

    private void showMainMenu() {
        System.out.println(YELLOW + BOLD + "\n  ── MENÚ PRINCIPAL ──" + RESET);
        System.out.println("  " + WHITE + "1." + RESET + " Nueva partida");
        System.out.println("  " + WHITE + "2." + RESET + " Ver ranking");
        System.out.println("  " + WHITE + "3." + RESET + " Salir");
        System.out.print(CYAN + "\n  Elige una opción: " + RESET);
    }

    private void showGoodbye() {
        System.out.println(MAGENTA + "\n  ¡Hasta la próxima, guerrero!" + RESET);
    }



    public int readInt(int min, int max) {
        while (true) {
            try {
                int v = Integer.parseInt(scanner.nextLine().trim());
                if (v >= min && v <= max) return v;
                System.out.print(RED + "  Opción no válida (" + min + "-" + max + "): " + RESET);
            } catch (NumberFormatException e) {
                System.out.print(RED + "  Introduce un número: " + RESET);
            }
        }
    }

    public Scanner getScanner() { return scanner; }
}

