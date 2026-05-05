package main.decorator;

import main.abstracta.Character;
import main.actions.Action;
import main.combat.CombatResult;
import main.state.PoisonedState;

public class PoisonedAttackDecorator extends ActionDecorator {
    public PoisonedAttackDecorator(Action actionEnvuelta) {
        super(actionEnvuelta);
    }

    public CombatResult executeAction(Character origen, Character destino) {
        CombatResult resultado= accionEnvuelta.executeAction(origen,destino);
        PoisonedState poisonEstado = new PoisonedState();
        destino.setEstadoActual(poisonEstado);
        CombatResult resultadoAtaque= new CombatResult(resultado.getDamage(), poisonEstado, resultado.isBlocking());


        return resultadoAtaque;
    }
}