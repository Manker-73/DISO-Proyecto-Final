package main.console;

import main.model.Player;
import main.enums.World;

import java.util.Scanner;

public class CharacterCreator {

    private static final Double PUNTOS_TOTALES = 150.0;
    private static final Double MIN_ATR        = 5.0;
    private static final Double MAX_ATR        = 70.0;

    private final Scanner scanner;

    public CharacterCreator(Scanner scanner) {
        this.scanner = scanner;
    }

    public Player createPlayer() {
        printHeader();

        World world = askWorld();
        Player player = new Player(world);

        String nombre = askName();
        player.setNombre(nombre);

        askAndSetAttributes(player);

        printSummary(player);
        return player;
    }

    private void printHeader() {
        System.out.println(ConsoleUI.CYAN + ConsoleUI.BOLD);
        System.out.println("  ╔══════════════════════════════════╗");
        System.out.println("  ║     CREACION DE PERSONAJE        ║");
        System.out.println("  ╚══════════════════════════════════╝");
        System.out.println(ConsoleUI.RESET);
    }

    private World askWorld() {
        World[] worlds = World.values();
        System.out.println("  Elige tu mundo:");
        for (int i = 0; i < worlds.length; i++) {
            System.out.println("  " + ConsoleUI.WHITE + (i + 1) + "." + ConsoleUI.RESET + " " + worlds[i]);
        }
        System.out.print(ConsoleUI.CYAN + "\n  Opcion [1-" + worlds.length + "]: " + ConsoleUI.RESET);
        int opcion = readInt(1, worlds.length);
        return worlds[opcion - 1];
    }

    private String askName() {
        System.out.print("\n  " + ConsoleUI.WHITE + "Nombre de tu personaje: " + ConsoleUI.RESET);
        while (true) {
            String nombre = scanner.nextLine().trim();
            if (!nombre.isEmpty()) return nombre;
            System.out.print(ConsoleUI.RED + "  El nombre no puede estar vacio: " + ConsoleUI.RESET);
        }
    }

    private void askAndSetAttributes(Player player) {
        System.out.println("\n  Tienes " + ConsoleUI.YELLOW + PUNTOS_TOTALES.intValue() + " puntos"
                + ConsoleUI.RESET + " para repartir entre tus atributos.");
        System.out.println("  Cada atributo: entre " + MIN_ATR.intValue()
                + " y " + MAX_ATR.intValue() + " puntos.\n");

        Double fuerza, resistencia, agilidad;
        while (true) {
            fuerza      = askAtributo("Fuerza      (ataque)  ", PUNTOS_TOTALES - 2 * MIN_ATR);
            resistencia = askAtributo("Resistencia (defensa) ", PUNTOS_TOTALES - fuerza - MIN_ATR);
            agilidad    = PUNTOS_TOTALES - fuerza - resistencia;

            if (agilidad < MIN_ATR || agilidad > MAX_ATR) {
                System.out.println(ConsoleUI.RED
                        + "  Agilidad resultante (" + agilidad.intValue() + ") fuera de rango. Repite."
                        + ConsoleUI.RESET);
                continue;
            }

            System.out.println("  " + ConsoleUI.GREEN + "Agilidad (velocidad): "
                    + agilidad.intValue() + " (calculada automaticamente)" + ConsoleUI.RESET);
            break;
        }

        player.setFuerza(fuerza);
        player.setResistencia(resistencia);
        player.setAgilidad(agilidad);
    }

    private Double askAtributo(String label, Double maxPermitido) {
        Double tope = Math.min(maxPermitido, MAX_ATR);
        System.out.print("  " + ConsoleUI.WHITE + label
                + "[" + MIN_ATR.intValue() + "-" + tope.intValue() + "]: " + ConsoleUI.RESET);
        return (double) readInt(MIN_ATR.intValue(), tope.intValue());
    }

    private void printSummary(Player player) {
        System.out.println("\n  " + ConsoleUI.MAGENTA + ConsoleUI.BOLD
                + "── Tu personaje ──────────────────" + ConsoleUI.RESET);
        System.out.println("  Nombre:      " + ConsoleUI.CYAN + ConsoleUI.BOLD
                + player.getNombre() + ConsoleUI.RESET);
        System.out.println("  Mundo:       " + ConsoleUI.YELLOW + player.getWorld() + ConsoleUI.RESET);
        System.out.println("  Fuerza:      " + bar(player.getFuerza()));
        System.out.println("  Resistencia: " + bar(player.getResistencia()));
        System.out.println("  Agilidad:    " + bar(player.getAgilidad()));
        System.out.print("\n  " + ConsoleUI.YELLOW + "Empezamos? Pulsa ENTER..." + ConsoleUI.RESET);
        scanner.nextLine();
    }

    private String bar(Double valor) {
        int filled = (int) Math.round((valor / MAX_ATR) * 10);
        filled = Math.max(0, Math.min(10, filled));
        return ConsoleUI.GREEN + "█".repeat(filled) + ConsoleUI.RESET
                + "░".repeat(10 - filled)
                + "  " + ConsoleUI.YELLOW + valor.intValue() + ConsoleUI.RESET;
    }

    private int readInt(int min, int max) {
        while (true) {
            try {
                int v = Integer.parseInt(scanner.nextLine().trim());
                if (v >= min && v <= max) return v;
                System.out.print(ConsoleUI.RED + "  Valor fuera de rango [" + min + "-" + max + "]: "
                        + ConsoleUI.RESET);
            } catch (NumberFormatException e) {
                System.out.print(ConsoleUI.RED + "  Introduce un numero: " + ConsoleUI.RESET);
            }
        }
    }
}