package main.state;
import main.abstracta.Character;

public class PoisonedState implements CharacterState{
    private int turnosActivos = 0;

    @Override
    public String getDescription() {
        return "Poisoned";
    }

    @Override
    public void applyEffect(Character character) {
        // Despues de 2 rondas, hay una probabilidad del 50% o 75% de que se vaya el efecto
        if(turnosActivos>4){
            if (Math.random() < 0.75) {
                character.setEstadoActual(new NormalState());
                return;
            }
        }else if(turnosActivos>2){
            if (Math.random() < 0.50) {
                character.setEstadoActual(new NormalState());
                return;
            }
        }

        // Cada ronda que este envenenado se pierde 1/16 de vida
        double restar = character.getMaxVida() / 16;
        character.setVida(character.getVida() - restar);

        turnosActivos++;
    }

    @Override
    public boolean canAct() {
        return true;
    }

    @Override
    public boolean isReplaceable() {
        return false;
    }
}
