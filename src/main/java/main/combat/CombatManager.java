package main.combat;
import main.abstracta.Character;
import main.abstracta.Enemy;
import main.actions.Action;
import main.console.TurnMenu;

public class CombatManager {
    private Character player;
    private Enemy enemy;
    private TurnMenu turnMenu;

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
                if(player.estaVivo() == false){
                    return false;
                }
                turnoEnemigo();
                if(enemy.estaVivo() == false){
                    return true;
                }
            } else{
                turnoEnemigo();
                if(enemy.estaVivo() == false){
                    return true;
                }
                turnoJugador();
                if(player.estaVivo() == false){
                    return false;
                }
            }
        }
    }

    private void turnoJugador(){
        player.getEstadoActual().applyEffect(player);
        if(!player.getEstadoActual().canAct()){
            return;
        }
        // TODO SE PASA EL MENÚ
        Action accion = turnMenu.elegirAccion(player);

        CombatResult resultado = accion.executeAction(player, enemy);

        double dano = CombatCalculator.getInstance().calculateDamage(player, enemy);
        enemy.setVida(enemy.getVida() - dano);

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
        double dano = 0;
        if(resultado.isBlocking()){
            double block = CombatCalculator.getInstance().calculateBlock(player);
            dano = resultado.getDamage() * (CombatCalculator.getInstance().calculateDamage(enemy, player) - block);
        } else{
            dano = CombatCalculator.getInstance().calculateDamage(enemy, player);
        }
        player.setVida(player.getVida() - dano);

        if(player.getEstadoActual().isReplaceable()&& resultado.getNewState() != null){
            player.setEstadoActual(resultado.getNewState());
        }
    }
}
