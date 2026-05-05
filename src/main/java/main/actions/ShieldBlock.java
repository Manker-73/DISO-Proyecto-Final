package main.actions;
import main.abstracta.Character;
import main.combat.CombatResult;

public class ShieldBlock implements Action{
    @Override
    public CombatResult executeAction(Character origen, Character destino) {
        return new CombatResult(0, null, true);
    }
}
