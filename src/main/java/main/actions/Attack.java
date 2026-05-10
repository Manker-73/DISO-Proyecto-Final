package main.actions;
import main.abstracta.Character;
import main.combat.CombatResult;

public class Attack implements Action {
    @Override
    public CombatResult executeAction(Character origen, Character destino) {
        double damage = origen.getFuerza();
        destino.setVida(destino.getVida() - damage);
        return new CombatResult(damage, null,false );
    }

}
