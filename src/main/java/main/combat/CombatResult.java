package main.combat;

import main.state.CharacterState;

public class CombatResult {
    private final CharacterState newState;
    private final boolean isBlocking;

    public CombatResult(CharacterState newState, boolean isBlocking){
        this.newState = newState;
        this.isBlocking = isBlocking;
    }
    public CharacterState getNewState(){
        return newState;
    }
    public boolean isBlocking(){
        return isBlocking;
    }
}
