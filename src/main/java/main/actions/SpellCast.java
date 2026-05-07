package main.actions;
import main.abstracta.Character;
import main.combat.CombatResult;

public class SpellCast implements Action {
    private String spell;
    public SpellCast(String spell){
        this.spell=spell;
    }
    @Override
    public CombatResult executeAction(Character origen, Character destino) {
        double damage = origen.getFuerza();
        destino.setVida(destino.getVida() - damage);
        return new CombatResult(damage, null,false );
    }

}
