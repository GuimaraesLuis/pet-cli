package Pessoa;
import Fauna.Animais;

import java.util.HashMap;

public class Dono {
    private int id;
    private String name;
    private String senha;
    private Animais pet;
    private HashMap<String, Integer> inventario = new HashMap<>();
    public Dono() {
    }

    public Dono(String name, String senha, Animais pet, HashMap<String, Integer > inventario) {
        this.name = name;
        this.senha = senha;
        this.pet = pet;
        this.inventario = inventario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Animais getPet() {
        return pet;
    }

    public void setPet(Animais pet) {
        this.pet = pet;
    }

    public HashMap<String, Integer> getInventario() {
        return inventario;
    }

    public void setInventario(HashMap<String, Integer> inventario) {
        this.inventario = inventario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
