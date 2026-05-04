package main.actions;
import main.abstracta.Character;
import main.combat.CombatResult;

public interface Action {
    CombatResult executeAction(Character origen, Character destino);
}
