package Paineis;
import Bebidas.Bebida;
import Comidas.Alimento;
import Models.Rectangle;
import Pessoa.Dono;
import Utils.ScreenBuilder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotoesComprar {
    public JButton buttonComprarComida(Rectangle rectangle, Dono dono, Alimento alimento){
        JButton buttonComprar = new ScreenBuilder().buttonBilder(new Models.Rectangle(rectangle.w, rectangle.h, rectangle.x, rectangle.y), "Comprar", new Color(0xB7B7B7), Color.white, null);
        buttonComprar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(dono.getCoins() >= alimento.getCusto()){
                dono.setCoins(dono.getCoins() - alimento.getCusto());
                dono.getInventario().put(alimento.getNome(), dono.getInventario().getOrDefault(alimento.getNome(), 0)+1);
            }else{
                System.out.println("saldo insuficiente");
            }
        }
    });
    return  buttonComprar;
    }

    public JButton buttonComprarAgua(Rectangle rectangle, Dono dono, Bebida bebida) {
        JButton buttonComprar = new ScreenBuilder().buttonBilder(new Models.Rectangle(rectangle.w, rectangle.h, rectangle.x, rectangle.y), "Comprar", new Color(0xB7B7B7), Color.white, null);
        buttonComprar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dono.getCoins() >= bebida.getCusto()) {
                    dono.setCoins(dono.getCoins() - bebida.getCusto());
                    dono.getInventario().put(bebida.getNome(), dono.getInventario().getOrDefault(bebida.getNome(), 0) + 1);
                } else {
                    System.out.println("sando insuficiente");
                }
            }
        });
        return buttonComprar;
    }
}
