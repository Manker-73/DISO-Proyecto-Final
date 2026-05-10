package main.actions;
import main.abstracta.Character;
import main.combat.CombatResult;

public class Heal implements Action{
    @Override
    public CombatResult executeAction(Character origen, Character destino) {
        origen.setVida(origen.getVida() + 15);
        return new CombatResult(null, false);
    }
}
