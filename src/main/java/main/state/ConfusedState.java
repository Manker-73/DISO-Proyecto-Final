package main.state;

import main.abstracta.Character;

public class ConfusedState implements CharacterState{
    private int turnosActivos = 0;
    private boolean actuaEsteTurno;

    @Override
    public String getDescription() {
        return "Confused";
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

        // Cuando esta confundido hay un 50% de que se haga daño y no pueda actuar
        if(Math.random()>0.5){
            actuaEsteTurno = false;

            double restar = character.getMaxVida() / 16;
            character.setVida(character.getVida() - restar);
        } else{
            actuaEsteTurno = true;
        }

        turnosActivos++;
    }

    @Override
    public boolean canAct() {
        return actuaEsteTurno;
    }

    @Override
    public boolean isReplaceable() {
        return false;
    }
}
