package Fauna;
import Bebidas.Bebida;
import Comidas.Alimento;
import Comidas.AlimentoTipoEnum;
import Pessoa.Dono;
import Utils.ScreenBuilder;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class Cachorro extends Animais {
    public Cachorro() {
        super();
    }
    public Cachorro(String nome) {
        this.setNome(nome);
        this.setFome(100);
        this.setEnergia(10);
        this.setSede(60);
        this.setTipo(TipoAnimal.Cachorro);
    }

    @Override
    public void comer(Alimento alimento) {
    if(alimento.getTipo() != AlimentoTipoEnum.Racao && alimento.getTipo() != AlimentoTipoEnum.Carne){
        System.out.println("Já viu um Cachorro comer " + alimento.getTipo() + "?");
        return;
        }
        this.setFome(getFome() + alimento.getPoints());
        System.out.println(this.getNome() + " Foi Alimentado Corretamente!");
    }

    @Override
    public void beberAgua(Bebida b) {
        this.setSede(getSede() + b.getPoints());
        System.out.println(this.getNome() + " Está Bebendo Água!");
    }
    @Override
    public void dormir(Animais a, JLabel j) {
        Timer time = new Timer();
        if(this.getEnergia() <= 30){
            TimerTask dormir = new TimerTask() {
                @Override
                public void run() {
                    setEnergia(getEnergia() + 25);
                    new ScreenBuilder().atualizar(a, getEnergia(), j);
                    if(getEnergia() >= 100){
                        time.cancel();
                        System.out.println("Acorda baiano");
                    }
                }
            };
            time.scheduleAtFixedRate(dormir, 0,  60 * 1000);
        }
    }
    @Override
    public void brincar() {

    }
}
