package Fauna;
import Bebidas.Bebida;
import Comidas.Alimento;
import Pessoa.Dono;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;
public class Gatos extends Animais{

    public Gatos() {
        super();
    }

    public Gatos(String nome) {
        this.setNome(nome);
        this.setFome(60);
        this.setEnergia(100);
        this.setSede(60);
        this.setTipo(TipoAnimal.Gato);
    }

    @Override
    public void comer(Alimento alimento) {

            this.setFome(this.getFome() + alimento.getPoints());
            System.out.println(this.getNome() + " Foi Alimentado Corretamente!");
    }

    @Override
    public void beberAgua(Bebida b){
        this.setSede(getSede() + b.getPoints());
        System.out.println(this.getNome() + "Está Bebendo Água");
    }

    @Override
    public void dormir(Animais a, JLabel j) {
        Timer time = new Timer();
        if(this.getEnergia() <= 30){
            TimerTask dormir = new TimerTask() {
                @Override
                public void run() {
                    a.setEnergia(a.getEnergia() + 25);
                    if(getEnergia() >= 100){
                        time.cancel();
                        System.out.println("Acorda Bahiano");
                    }
                }
            };
            time.scheduleAtFixedRate(dormir, 0,60 * 1000);
        }
    }

    @Override
    public void brincar() {
        if (this.getEnergia() >= 20){

        }
    }
}
