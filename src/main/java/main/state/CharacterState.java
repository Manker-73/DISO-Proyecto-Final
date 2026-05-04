package main.state;
import main.abstracta.Character;

public interface CharacterState {
    public String getDescription();
    public void applyEffect(Character character);
    public boolean canAct();
    public boolean isReplaceable();
}
