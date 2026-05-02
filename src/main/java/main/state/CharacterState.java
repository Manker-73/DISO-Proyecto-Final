package main.state;

public interface CharacterState {
    public String getDescription();
    public void applyEffect(Character character);
}
