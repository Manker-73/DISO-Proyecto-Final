package main.state;
import main.abstracta.Character;

public class NormalState implements CharacterState{
    @Override
    public String getDescription() {
        return "Normal";
    }

    @Override
    public void applyEffect(Character character) {
        // Normal state no hace nada al inicio de la ronda
    }

    @Override
    public boolean canAct() {
        return true;
    }

    @Override
    public boolean isReplaceable() {
        return true;
    }
}
