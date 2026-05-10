package main.combat;
import main.abstracta.Character;
import main.abstracta.Enemy;
import main.actions.Action;
import main.console.TurnMenu;

public class CombatManager {
    private Character player;
    private Enemy enemy;
    private TurnMenu turnMenu;
    private boolean jugadorBloqueando;

    public CombatManager(Character player, Enemy enemy, TurnMenu turnMenu){
        this.player = player;
        this.enemy = enemy;
        this.turnMenu = turnMenu;
    }

    public boolean startCombat(){
        boolean jugadorPrimero = Math.random() > 0.5;
        while(true){
            if(jugadorPrimero){
                turnoJugador();
                if(!player.estaVivo()){
                    return false;
                }
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
            }
        }
    }

    private void turnoJugador(){
        this.jugadorBloqueando = false;
        player.getEstadoActual().applyEffect(player);
        if(!player.getEstadoActual().canAct()){
            return;
        }
        // TODO SE PASA EL MENÚ
        Action accion = turnMenu.elegirAccion(player);

        CombatResult resultado = accion.executeAction(player, enemy);

        if(resultado.isBlocking()){
            jugadorBloqueando = true;
        }else {
            double dano = player.getFuerza() * (100.0 / (100.0 + enemy.getResistencia()));
            enemy.setVida(enemy.getVida() - dano);
        }
        if(enemy.getEstadoActual().isReplaceable()&& resultado.getNewState() != null){
            enemy.setEstadoActual(resultado.getNewState());
        }
    }
    private void turnoEnemigo(){
        enemy.getEstadoActual().applyEffect(enemy);
        if(!enemy.getEstadoActual().canAct()){
            return;
        }
        Action accion = enemy.nextAction();

        CombatResult resultado = accion.executeAction(enemy, player);
        double dano = 0.0;
        if(jugadorBloqueando){
            dano = Math.max(0, enemy.getFuerza()- player.getResistencia()*0.5);
        }else{
            dano = enemy.getFuerza() * (100.0 / (100.0 + player.getResistencia()));
        }
        player.setVida(player.getVida()-dano);

        if(player.getEstadoActual().isReplaceable()&& resultado.getNewState() != null){
            player.setEstadoActual(resultado.getNewState());
        }
    }
}
