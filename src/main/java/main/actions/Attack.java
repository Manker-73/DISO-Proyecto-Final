package main.actions;
import main.abstracta.Character;
import main.combat.CombatResult;

public class Attack implements Action {
    @Override
    public CombatResult executeAction(Character origen, Character destino) {
        return new CombatResult(null,false );
    }

}
