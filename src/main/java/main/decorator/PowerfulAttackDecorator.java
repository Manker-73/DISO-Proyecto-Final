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
        CombatResult resultado = accionEnvuelta.executeAction(origen, destino);
        if(origen.getFuerza()>=15){
            double ataquePotenciado = resultado.getDamage() * 1.5;
            CombatResult resultadoPotenciado= new CombatResult(ataquePotenciado, resultado.getNewState(),resultado.isBlocking());
            return resultadoPotenciado;
        }
        return resultado;
    }
}
