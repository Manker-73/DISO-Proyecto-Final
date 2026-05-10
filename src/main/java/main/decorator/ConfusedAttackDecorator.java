package main.decorator;

import main.abstracta.Character;
import main.actions.Action;
import main.combat.CombatResult;
import main.state.ConfusedState;


public class ConfusedAttackDecorator extends ActionDecorator{
    public ConfusedAttackDecorator(Action accionEnvuelta){
        super(accionEnvuelta);
    }
    @Override
    public CombatResult executeAction(Character origen, Character destino) {
        CombatResult resultado = accionEnvuelta.executeAction(origen, destino);
        ConfusedState confusedState = new ConfusedState();
        destino.setEstadoActual(confusedState);
        return accionEnvuelta.executeAction(origen, destino);
    }
}
