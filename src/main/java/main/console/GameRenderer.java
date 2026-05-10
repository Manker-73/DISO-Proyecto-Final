package main.console;

import main.abstracta.Character;
import main.abstracta.Enemy;
import main.combat.CombatResult;
import main.model.Player;

public class GameRenderer {




    public void renderCombatStatus(Character player, Enemy enemy) {
        System.out.println("\n" + ConsoleUI.BOLD + ConsoleUI.MAGENTA
                + "  ════════════════════════════════════" + ConsoleUI.RESET);
        renderCharacterRow("TÚ     ", player, ConsoleUI.GREEN);
        renderCharacterRow("ENEMIGO", enemy,  ConsoleUI.RED);
        System.out.println(ConsoleUI.BOLD + ConsoleUI.MAGENTA
                + "  ════════════════════════════════════" + ConsoleUI.RESET);
    }

    private void renderCharacterRow(String label, Character c, String color) {
        String nombre = c.getNombre();
        String barra  = healthBar(getSafeVida(c), getSafeVidaMax(c), color);
        String estado = getSafeEstado(c);

        System.out.printf("  %-8s %-12s  %s  [%s%s%s]%n",
                label,
                ConsoleUI.BOLD + nombre + ConsoleUI.RESET,
                barra,
                color, estado, ConsoleUI.RESET);
    }




    public void renderActionResult(String attackerName, CombatResult result, double dano, boolean esJugador) {
        String color  = esJugador ? ConsoleUI.GREEN : ConsoleUI.RED;
        String icono  = esJugador ? "  ⚔  " : "  👹 ";

        if (result.isBlocking()) {
            System.out.println(icono + color + ConsoleUI.BOLD + attackerName
                    + ConsoleUI.RESET + color + " se pone en guardia." + ConsoleUI.RESET);

        } else if (dano > 0) {
            System.out.printf("%s%s%s%s causa %s%.1f de daño!%s%n",
                    icono, color, ConsoleUI.BOLD, attackerName,
                    ConsoleUI.RESET + color, dano, ConsoleUI.RESET);

        } else {
            System.out.println(icono + color + ConsoleUI.BOLD + attackerName
                    + ConsoleUI.RESET + color + " actúa sin causar daño." + ConsoleUI.RESET);
        }

        if (result.getNewState() != null) {
            try {
                String desc = result.getNewState().getDescription();
                System.out.println("  ⚡ Nuevo estado: "
                        + ConsoleUI.YELLOW + desc + ConsoleUI.RESET);
            } catch (Exception ignored) {}
        }
    }




    public void renderVictory(int numeroCombate, int puntuacion) {
        System.out.println(ConsoleUI.GREEN + ConsoleUI.BOLD);
        System.out.println("  ╔══════════════════════════════════╗");
        System.out.printf("  ║   ¡COMBATE %-2d SUPERADO!          ║%n", numeroCombate);
        System.out.printf("  ║   Puntuación: %-19d║%n", puntuacion);
        System.out.println("  ╚══════════════════════════════════╝");
        System.out.print(ConsoleUI.RESET);
        pause(1200);
    }


    public void renderGameOver(String nombre, int puntuacion) {
        System.out.println(ConsoleUI.RED + ConsoleUI.BOLD);
        System.out.println("  ╔══════════════════════════════════╗");
        System.out.println("  ║          GAME OVER               ║");
        System.out.printf ("  ║  %-32s║%n", nombre + " ha caído...");
        System.out.printf ("  ║  Puntuación final: %-13d║%n", puntuacion);
        System.out.println("  ╚══════════════════════════════════╝");
        System.out.print(ConsoleUI.RESET);
    }


    public void renderFlee(String nombre) {
        System.out.println("\n  " + ConsoleUI.YELLOW + ConsoleUI.BOLD
                + nombre + " huye del combate!" + ConsoleUI.RESET);
    }




    private String getSafeEstado(Character c) {
        try {
            return c.getEstadoActual().getDescription();
        } catch (Exception e) {
            return "Normal";
        }
    }


    private double getSafeVida(Character c) {
        try { return c.getVida(); } catch (Exception e) { return 100; }
    }


    private double getSafeVidaMax(Character c) {
        try { return c.getMaxVida(); } catch (Exception e) { return 100; }
    }




    private String healthBar(double vida, double vidaMax, String color) {
        if (vidaMax <= 0) return "░░░░░░░░░░ 0/0";
        int filled = (int) Math.round((vida / vidaMax) * 10);
        filled = Math.max(0, Math.min(10, filled));
        String barColor = vida > vidaMax * 0.5 ? ConsoleUI.GREEN
                : vida > vidaMax * 0.2 ? ConsoleUI.YELLOW
                : ConsoleUI.RED;
        return barColor + "█".repeat(filled)
                + ConsoleUI.RESET + "░".repeat(10 - filled)
                + "  " + ConsoleUI.BOLD + (int) vida + "/" + (int) vidaMax + ConsoleUI.RESET;
    }

    private void pause(int ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }
}