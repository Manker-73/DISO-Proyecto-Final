package main.actions;

import main.abstracta.Character;
import main.combat.CombatResult;

public class Flee implements Action {

    @Override
    public CombatResult executeAction(Character origen, Character destino) {
        if (origen.getAgilidad() >= 15) {
            return new CombatResult(-1, null, false);
        }
        return new CombatResult(0, null, false);
    }
}