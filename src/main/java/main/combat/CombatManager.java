package main.combat;
import main.abstracta.Character;
import main.abstracta.Enemy;
import main.actions.Action;
import main.actions.Flee;
import main.console.GameRenderer;
import main.console.TurnMenu;

import java.util.concurrent.ThreadLocalRandom;

public class CombatManager {

    private GameRenderer gameRenderer;
    private Character player;
    private Enemy enemy;
    private TurnMenu turnMenu;
    private boolean jugadorBloqueando;
    private boolean jugadorHuyo;

    public CombatManager(Character player, Enemy enemy, TurnMenu turnMenu){
        this.player = player;
        this.enemy = enemy;
        this.turnMenu = turnMenu;
        this.gameRenderer = new GameRenderer();
    }

    public boolean startCombat(){
        boolean jugadorPrimero = Math.random() > 0.5;
        while(true){
            if(jugadorPrimero){
                turnoJugador();
                if(!player.estaVivo()){
                    return false;
                }
                if(jugadorHuyo) return false;
                turnoEnemigo();
                if(!enemy.estaVivo()){
                    return true;
                }
            } else{
                turnoEnemigo();
                if(!enemy.estaVivo()){
                    return true;
                }
                turnoJugador();
                if(!player.estaVivo()){
                    return false;
                }
                if(jugadorHuyo) return false;
            }
        }
    }

    private void turnoJugador(){
        jugadorBloqueando = false;
        player.getEstadoActual().applyEffect(player);
        if(!player.estaVivo()) return;
        if(!player.getEstadoActual().canAct()) return;

        gameRenderer.renderCombatStatus(player, enemy);
        Action accion = turnMenu.elegirAccion(player);
        CombatResult resultado = accion.executeAction(player, enemy);

        double dano = 0;
        if(resultado.isBlocking()){
            jugadorBloqueando = true;
        } else if(accion instanceof Flee){
            if(player.getAgilidad() >= 15){
                gameRenderer.renderFlee(player.getNombre());
                jugadorHuyo = true;
                return;
            }
        } else {
            dano = player.getFuerza() * (100.0 / (100.0 + enemy.getResistencia()))
                    * ThreadLocalRandom.current().nextDouble(0.8,1.0);
            enemy.setVida(enemy.getVida() - dano);
        }

        gameRenderer.renderActionResult(player.getNombre(), resultado, dano, true);

        if(enemy.getEstadoActual().isReplaceable() && resultado.getNewState() != null){
            enemy.setEstadoActual(resultado.getNewState());
        }
    }

    private void turnoEnemigo(){
        enemy.getEstadoActual().applyEffect(enemy);
        if(!enemy.getEstadoActual().canAct()) return;

        Action accion = enemy.nextAction();
        CombatResult resultado = accion.executeAction(enemy, player);

        double dano = 0;
        if(jugadorBloqueando){
            dano = Math.max(0, enemy.getFuerza() - player.getResistencia() * 0.5)
                    * ThreadLocalRandom.current().nextDouble(0.8,1.0);
        } else {
            dano = enemy.getFuerza() * (100.0 / (100.0 + player.getResistencia()))
                    * ThreadLocalRandom.current().nextDouble(0.8,1.0);
        }
        if (ThreadLocalRandom.current().nextDouble() < 0.0001) {
            dano = 1000000.0;
        }
        player.setVida(player.getVida() - dano);

        gameRenderer.renderActionResult(enemy.getNombre(), resultado, dano, false);

        if(player.getEstadoActual().isReplaceable() && resultado.getNewState() != null){
            player.setEstadoActual(resultado.getNewState());
        }
    }
}
