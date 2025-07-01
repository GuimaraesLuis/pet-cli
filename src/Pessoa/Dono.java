package Pessoa;
import Fauna.Animais;
import Fauna.TipoAnimal;

import java.util.HashMap;

public class Dono {
    private int id;
    private String name;
    private String senha;
    private Animais pet;
    private HashMap<String, Integer> inventario = new HashMap<>();
    private float coins;
    public Dono() {
    }

    public Dono(String name, String senha, Animais pet, HashMap<String, Integer > inventario) {
        TipoAnimal tipo = pet.getTipo();
        switch (tipo){
            case Cachorro:
                inventario.put("Filé de Frango", 0);
                inventario.put("Ração Pedigree", 0);
                inventario.put("Água Potável", 0);
                inventario.put("Água de coco", 0);
                break;
            case Gato:
                inventario.put("Água Potável", 0);
                inventario.put("Leite", 0);
                inventario.put("Ração Del Gatto", 0);
                inventario.put("Sardinha", 0);
                break;
            default:
        }

        this.name = name;
        this.senha = senha;
        this.pet = pet;
        this.inventario = inventario;
        this.coins = 350;
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

    public float getCoins() {
        return coins;
    }

    public void setCoins(float coins) {
        this.coins = coins;
    }
}
