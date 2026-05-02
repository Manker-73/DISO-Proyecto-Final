package main.model;

import main.abstracta.Character;
import main.enums.World;

public class Player extends Character {
    public static final Integer NIVEL_DEF = 1;
    public static final Double XP_DEF = 0.0;
    public static final Double XP_REQ_DEF = 100.0;


    private World world;
    private Integer nivel;
    private Double experienciaActual;
    private Double experienciaNecesaria;

    public Player(World world){
        this(world,NIVEL_DEF);
    }

    public Player(World world, Integer nivel){
        super();
        this.world = world;
        this.nivel = nivel;
        this.experienciaActual = XP_DEF;
        this.experienciaNecesaria = XP_REQ_DEF;

    }

    @Override
    public String toString() {
        return "El personaje " + getNombre() + " está en " + getWorld()
                + " y tiene nivel " + getNivel();
    }

    public void recibirExperiencia(Double xp){
        setExperienciaActual(getExperienciaActual() + xp);
        while (getExperienciaActual()>=getExperienciaNecesaria()){
            subirNivel();
        }
    }

    private void subirNivel(){
        setNivel(getNivel()+1);
        setExperienciaActual(getExperienciaActual()-getExperienciaNecesaria());
        setExperienciaNecesaria(getExperienciaNecesaria()*1.5);
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public Integer getNivel() {
        return nivel;
    }

    private void setNivel(Integer nivel){
        this.nivel = nivel;
    }

    public Double getExperienciaActual() {
        return experienciaActual;
    }

    private void setExperienciaActual(Double experienciaActual) {
        this.experienciaActual = experienciaActual;
    }

    public Double getExperienciaNecesaria() {
        return experienciaNecesaria;
    }

    public void setExperienciaNecesaria(Double experienciaNecesaria) {
        this.experienciaNecesaria = experienciaNecesaria;
    }


}
