package Fauna;
import Bebidas.Bebida;
import Comidas.Alimento;
import javax.swing.*;
public abstract class Animais {
    private int id;
    private int fome;
    private int energia;
    private int sede;
    private String nome;
    private TipoAnimal Tipo;
    public String dono;

    public abstract void comer(Alimento alimento);
    public abstract void beberAgua(Bebida b);
    public abstract void dormir(Animais a, JLabel j);
    public abstract void  brincar();

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = Math.max(0, Math.min(energia, 100));

    }

    public int getFome() {
        return fome;
    }

    public void setFome(int fome) {
        this.fome = Math.max(0, Math.min(fome, 100));
    }

    public int getSede() {
        return sede;
    }

    public void setSede(int sede) {
        this.sede = Math.max(0, Math.min(sede, 100));
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoAnimal getTipo() {
        return Tipo;
    }

    public void setTipo(TipoAnimal tipo) {
        Tipo = tipo;
    }

    public String getDono() {
        return dono;
    }

    public void setDono(String dono) {
        this.dono = dono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
