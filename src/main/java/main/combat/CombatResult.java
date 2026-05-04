package main.combat;

import main.state.CharacterState;

public class CombatResult {
    private final double damage;
    private final CharacterState newState;
    private final boolean isBlocking;

    public CombatResult(double damage, CharacterState newState, boolean isBlocking){
        this.damage = damage;
        this.newState = newState;
        this.isBlocking = isBlocking;
    }
    public double getDamage(){
        return damage;
    }
    public CharacterState getNewState(){
        return newState;
    }
    public boolean isBlocking(){
        return isBlocking;
    }
}
