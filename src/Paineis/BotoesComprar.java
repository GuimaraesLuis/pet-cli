package Paineis;
import Bebidas.Bebida;
import Comidas.Alimento;
import Comidas.FileDeFrango;
import Models.Rectangle;
import Pessoa.Dono;
import Utils.ScreenBuilder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotoesComprar {
    public JButton buttonComprarComida(Rectangle rectangle, Dono d, Alimento alimento){
        JButton buttonComprar = new ScreenBuilder().buttonBilder(new Models.Rectangle(rectangle.w, rectangle.h, rectangle.x, rectangle.y), "Comprar", new Color(115, 115, 115), Color.white, null);
        buttonComprar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
         d.getInventario().put(alimento.getNome(), d.getInventario().getOrDefault(alimento.getNome(), 0)+1);
            System.out.println(d.getInventario());
        }
    });
    return  buttonComprar;
    }

    public JButton buttonComprarAgua(Rectangle rectangle, Dono d, Bebida bebida) {
        JButton buttonComprar = new ScreenBuilder().buttonBilder(new Models.Rectangle(rectangle.w, rectangle.h, rectangle.x, rectangle.y), "Comprar", new Color(115, 115, 115), Color.white, null);
        buttonComprar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            d.getInventario().put(bebida.getNome(), d.getInventario().getOrDefault(bebida.getNome(), 0)+ 1);
                System.out.println(d.getInventario());
            }
        });
        return buttonComprar;
    }
}
