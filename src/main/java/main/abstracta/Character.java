package main.abstracta;
import main.state.CharacterState;
import main.state.NormalState;

public abstract class Character {
    public static final String NOMBRE_DEF = "NAME";
    public static final Double VALOR_DEF = 0.0;

    public static final CharacterState ESTADO_DEF = new NormalState();

    protected String nombre;
    protected Double vida;
    protected Double fuerza;
    protected Double resistencia;
    protected Double agilidad;
    protected CharacterState estadoActual;
    protected Double maxVida;

    public Character() {
        this(NOMBRE_DEF, VALOR_DEF, VALOR_DEF, VALOR_DEF, VALOR_DEF, ESTADO_DEF);
    }

    public Character(String nombre) {
        this(nombre, VALOR_DEF, VALOR_DEF, VALOR_DEF, VALOR_DEF, ESTADO_DEF);
    }

    public Character(String nombre, Double vida, Double fuerza, Double resistencia, Double agilidad) {
        this(nombre, vida, fuerza, resistencia, agilidad, ESTADO_DEF);
    }

    public Character(String nombre, Double vida, Double fuerza, Double resistencia, Double agilidad, CharacterState estadoActual) {
        this.nombre = nombre;
        this.vida = vida;
        this.fuerza = fuerza;
        this.resistencia = resistencia;
        this.agilidad = agilidad;
        this.estadoActual = estadoActual;
        this.maxVida = vida;
    }

    public Boolean estaVivo(){
        return this.vida > 0;
    }

    public abstract String toString();

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Double getVida() {
        return vida;
    }
    public void setVida(Double vida) {
        this.vida = vida;
    }
    public Double getFuerza() {
        return fuerza;
    }
    public void setFuerza(Double fuerza) {
        this.fuerza = fuerza;
    }
    public Double getResistencia() {
        return resistencia;
    }
    public void setResistencia(Double resistencia) {
        this.resistencia = resistencia;
    }
    public Double getAgilidad() {
        return agilidad;
    }
    public void setAgilidad(Double agilidad) {
        this.agilidad = agilidad;
    }
    public CharacterState getEstadoActual() {
        return estadoActual;
    }
    public void setEstadoActual(CharacterState estadoActual) {
        this.estadoActual = estadoActual;
    }
    public Double getMaxVida(){return maxVida;}
}
