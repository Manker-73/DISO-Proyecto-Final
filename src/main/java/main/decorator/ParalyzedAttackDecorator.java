package main.decorator;

import main.abstracta.Character;
import main.actions.Action;
import main.combat.CombatResult;
import main.state.ParalyzedState;

public class ParalyzedAttackDecorator extends ActionDecorator{
    public ParalyzedAttackDecorator(Action accionEnvuelta){
        super(accionEnvuelta);
    }
    @Override
    public CombatResult executeAction(Character origen, Character destino) {
        CombatResult resultado = accionEnvuelta.executeAction(origen, destino);
        ParalyzedState paralyzedState = new ParalyzedState();
        destino.setEstadoActual(paralyzedState);
        return accionEnvuelta.executeAction(origen, destino);
    }
}
