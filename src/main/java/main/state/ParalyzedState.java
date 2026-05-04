package main.state;

import main.abstracta.Character;

public class ParalyzedState implements CharacterState{
    private int turnosActivos = 0;

    @Override
    public String getDescription() {
        return "Paralyzed";
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
        turnosActivos++;
    }

    @Override
    public boolean canAct() {
        return Math.random() < 0.5;
    }

    @Override
    public boolean isReplaceable() {
        return false;
    }
}
