package main.decorator;

import main.abstracta.Character;
import main.actions.Action;
import main.combat.CombatResult;

public class QuickAttackDecorator extends ActionDecorator{
    public QuickAttackDecorator(Action accionEnvuelta){
        super(accionEnvuelta);
    }
    public CombatResult executeAction(Character origen, Character destino) {
        CombatResult resultadoAtaqueAgil1 = accionEnvuelta.executeAction(origen, destino);
        if (origen.getAgilidad() >= 15) {
            CombatResult resultadoAtaqueAgil2 = accionEnvuelta.executeAction(origen, destino);

            double danoTotal = resultadoAtaqueAgil1.getDamage() + resultadoAtaqueAgil2.getDamage();
            CombatResult resultadoAtaquesAgiles = new CombatResult(danoTotal, resultadoAtaqueAgil1.getNewState(),resultadoAtaqueAgil1.isBlocking());
            return resultadoAtaquesAgiles;
        }
        return resultadoAtaqueAgil1;
    }
}
