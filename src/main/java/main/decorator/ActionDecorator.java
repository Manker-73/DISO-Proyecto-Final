package main.decorator;
import main.actions.Action;
import main.abstracta.Character;
import main.combat.CombatResult;

public abstract class ActionDecorator implements Action{
    protected Action accionEnvuelta;

    public ActionDecorator(Action accionEnvuelta){
        this.accionEnvuelta = accionEnvuelta;
    }

    @Override
    public CombatResult executeAction(Character origen,Character destino){
        return accionEnvuelta.executeAction(origen,destino);
    }
}
