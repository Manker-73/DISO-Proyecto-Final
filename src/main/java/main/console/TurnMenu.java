package main.console;

import main.abstracta.Character;
import main.actions.Action;
import main.actions.SwordAttack;
import main.actions.ShieldBlock;
import main.actions.Heal;
import main.actions.Flee;

import java.util.Scanner;

public class TurnMenu {

    private final Scanner scanner;

    public TurnMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    public Action elegirAccion(Character player) {
        printTurnHeader(player);
        printOptions(player);
        int opcion = readInt(1, 4);
        return buildAction(opcion);
    }

    private void printTurnHeader(Character player) {
        System.out.println("\n" + ConsoleUI.CYAN
                + "  ── Tu turno ────────────────────────" + ConsoleUI.RESET);

        String vidaStr = healthBar(player.getVida(), player.getMaxVida());
        String estado  = getEstado(player);

        System.out.println("  " + ConsoleUI.BOLD + player.getNombre() + ConsoleUI.RESET
                + "  |  Vida: " + vidaStr
                + "  |  Estado: " + ConsoleUI.YELLOW + estado + ConsoleUI.RESET);
    }

    private void printOptions(Character player) {
        Double agilidad = player.getAgilidad();
        String fleeHint = agilidad >= 15
                ? ConsoleUI.GREEN + "(puedes huir — Agilidad " + agilidad.intValue() + " >= 15)" + ConsoleUI.RESET
                : ConsoleUI.RED   + "(dificil — Agilidad " + agilidad.intValue() + " < 15)"  + ConsoleUI.RESET;

        System.out.println();
        System.out.println("  " + ConsoleUI.WHITE + "1." + ConsoleUI.RESET
                + " Atacar con espada    "
                + ConsoleUI.GREEN + "(dano = Fuerza)" + ConsoleUI.RESET);
        System.out.println("  " + ConsoleUI.WHITE + "2." + ConsoleUI.RESET
                + " Bloquear             "
                + ConsoleUI.GREEN + "(reducir dano recibido)" + ConsoleUI.RESET);
        System.out.println("  " + ConsoleUI.WHITE + "3." + ConsoleUI.RESET
                + " Curar                "
                + ConsoleUI.GREEN + "(+15 de vida)" + ConsoleUI.RESET);
        System.out.println("  " + ConsoleUI.WHITE + "4." + ConsoleUI.RESET
                + " Huir                 " + fleeHint);
        System.out.print("\n  " + ConsoleUI.CYAN + "Elige accion [1-4]: " + ConsoleUI.RESET);
    }

    private Action buildAction(int opcion) {
        return switch (opcion) {
            case 1 -> new SwordAttack();
            case 2 -> new ShieldBlock();
            case 3 -> new Heal();
            case 4 -> new Flee();
            default -> new SwordAttack();
        };
    }

    private String getEstado(Character player) {
        try {
            return player.getEstadoActual().getDescription();
        } catch (Exception e) {
            return "Normal";
        }
    }

    private String healthBar(Double vida, Double maxVida) {
        if (maxVida == null || maxVida <= 0) return "??/??";
        int filled = (int) Math.round((vida / maxVida) * 10);
        filled = Math.max(0, Math.min(10, filled));
        String color = vida > maxVida * 0.5 ? ConsoleUI.GREEN
                : vida > maxVida * 0.2 ? ConsoleUI.YELLOW
                : ConsoleUI.RED;
        return color + "█".repeat(filled) + ConsoleUI.RESET
                + "░".repeat(10 - filled)
                + "  " + vida.intValue() + "/" + maxVida.intValue();
    }

    private int readInt(int min, int max) {
        while (true) {
            try {
                int v = Integer.parseInt(scanner.nextLine().trim());
                if (v >= min && v <= max) return v;
                System.out.print(ConsoleUI.RED + "  Elige entre " + min + " y " + max + ": " + ConsoleUI.RESET);
            } catch (NumberFormatException e) {
                System.out.print(ConsoleUI.RED + "  Introduce un numero: " + ConsoleUI.RESET);
            }
        }
    }
}