package main.decorator;

import main.abstracta.Character;
import main.actions.Action;
import main.combat.CombatResult;

public class QuickAttackDecorator extends ActionDecorator{
    public QuickAttackDecorator(Action accionEnvuelta){
        super(accionEnvuelta);
    }
    //@Override
    //public CombatResult executeAction(main.abstracta.Character origen, Character destino)
}
