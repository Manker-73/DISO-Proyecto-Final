package main.combat;
import main.abstracta.Character;

public class CombatCalculator {
    private static final CombatCalculator INSTANCE = new CombatCalculator();

    private CombatCalculator(){

    }
    public static CombatCalculator getInstance(){
        return INSTANCE;
    }
    public double calculateDamage(Character atacante, Character defensor){
        double resultado = (atacante.getFuerza() * atacante.getAgilidad()*0.5 - defensor.getResistencia()*0.2);
        return Math.max(1, resultado);
    }
    public double calculateBlock(Character defensor){
        double resultado = (defensor.getResistencia()*0.5);
        return Math.max(1, resultado);
    }
}
