package main.combat;

import main.state.CharacterState;

public class CombatResult {
    private final int damage;
    private final CharacterState newState;
    private final boolean isBlocking;

    public CombatResult(int damage, CharacterState newState, boolean isBlocking){
        this.damage = damage;
        this.newState = newState;
        this.isBlocking = isBlocking;
    }
    public int getDamage(){
        return damage;
    }
    public CharacterState getNewState(){
        return newState;
    }
    public boolean isBlocking(){
        return isBlocking;
    }
}
