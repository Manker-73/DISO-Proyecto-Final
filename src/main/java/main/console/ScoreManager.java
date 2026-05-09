package main.console;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ScoreManager {

    private static final String RANKING_FILE = "ranking.txt";
    private static final int    MAX_ENTRADAS = 10;
    private static final DateTimeFormatter FMT =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");




    public void saveScore(String nombre, int puntuacion) {
        String entrada = nombre + "|" + puntuacion + "|" + LocalDateTime.now().format(FMT);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RANKING_FILE, true))) {
            bw.write(entrada);
            bw.newLine();
        } catch (IOException e) {
            System.out.println(ConsoleUI.RED
                    + "  [ScoreManager] No se pudo guardar la puntuación: "
                    + e.getMessage() + ConsoleUI.RESET);
        }
    }




    public void showRanking() {
        List<ScoreEntry> entradas = loadEntries();

        System.out.println(ConsoleUI.YELLOW + ConsoleUI.BOLD);
        System.out.println("  ╔══════════════════════════════════════════════╗");
        System.out.println("  ║              RANKING — TOP " + MAX_ENTRADAS
                + "                ║");
        System.out.println("  ╠══════╦══════════════╦═══════════╦════════════╣");
        System.out.println("  ║  Pos ║ Nombre       ║ Puntos    ║ Fecha      ║");
        System.out.println("  ╠══════╬══════════════╬═══════════╬════════════╣");
        System.out.print(ConsoleUI.RESET);

        if (entradas.isEmpty()) {
            System.out.println("  ║     Todavía no hay partidas registradas.     ║");
        } else {
            int pos = 1;
            for (ScoreEntry e : entradas) {
                String medal = switch (pos) {
                    case 1 -> ConsoleUI.YELLOW + " 🥇 " + ConsoleUI.RESET;
                    case 2 -> ConsoleUI.WHITE  + " 🥈 " + ConsoleUI.RESET;
                    case 3 -> ConsoleUI.RED    + " 🥉 " + ConsoleUI.RESET;
                    default -> "  " + pos + " ";
                };
                System.out.printf("  ║ %-5s ║ %-12s ║ %-9d ║ %-10s ║%n",
                        medal,
                        truncate(e.nombre(), 12),
                        e.puntuacion(),
                        e.fecha().length() > 10 ? e.fecha().substring(0, 10) : e.fecha());
                pos++;
            }
        }
        System.out.println(ConsoleUI.YELLOW + ConsoleUI.BOLD
                + "  ╚══════╩══════════════╩═══════════╩════════════╝"
                + ConsoleUI.RESET + "\n");
    }



    private List<ScoreEntry> loadEntries() {
        if (!Files.exists(Path.of(RANKING_FILE))) return Collections.emptyList();
        List<ScoreEntry> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(RANKING_FILE))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) continue;
                String[] p = linea.split("\\|");
                if (p.length < 3) continue;
                try {
                    lista.add(new ScoreEntry(p[0].trim(),
                            Integer.parseInt(p[1].trim()), p[2].trim()));
                } catch (NumberFormatException ignored) {}
            }
        } catch (IOException e) {
            System.out.println(ConsoleUI.RED
                    + "  [ScoreManager] Error al leer el ranking: "
                    + e.getMessage() + ConsoleUI.RESET);
        }
        lista.sort(Comparator.comparingInt(ScoreEntry::puntuacion).reversed());
        return lista.size() > MAX_ENTRADAS ? lista.subList(0, MAX_ENTRADAS) : lista;
    }

    private String truncate(String s, int max) {
        if (s == null) return "";
        return s.length() <= max ? s : s.substring(0, max - 1) + "…";
    }

    private record ScoreEntry(String nombre, int puntuacion, String fecha) {}
}