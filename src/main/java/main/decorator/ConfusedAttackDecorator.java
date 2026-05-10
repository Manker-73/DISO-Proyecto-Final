package main.decorator;

import main.abstracta.Character;
import main.actions.Action;
import main.combat.CombatResult;


public class ConfusedAttackDecorator extends ActionDecorator{
    public ConfusedAttackDecorator(Action accionEnvuelta){
        super(accionEnvuelta);
    }
    public CombatResult executeAction(Character origen, Character destino) {
        if(origen.getAgilidad() >= 15){
            origen.setFuerza(origen.getFuerza() * 2);
            CombatResult resultado = accionEnvuelta.executeAction(origen, destino);
            origen.setFuerza(origen.getFuerza() / 2);
            return resultado;
        }
        return accionEnvuelta.executeAction(origen, destino);
    }
}
