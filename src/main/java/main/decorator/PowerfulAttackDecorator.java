package main.decorator;
import main.abstracta.Character;
import main.actions.Action;
import main.combat.CombatResult;

public class PowerfulAttackDecorator extends ActionDecorator{
    public PowerfulAttackDecorator(Action accionEnvuelta){
        super(accionEnvuelta);
    }
    @Override
    public CombatResult executeAction(Character origen, Character destino){
        if(origen.getFuerza() >= 15){
            origen.setFuerza(origen.getFuerza() * 1.5);
            CombatResult resultado = accionEnvuelta.executeAction(origen, destino);
            origen.setFuerza(origen.getFuerza() / 1.5); // restaurar
            return resultado;
        }
        return accionEnvuelta.executeAction(origen, destino);
    }
}
