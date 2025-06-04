package Paineis;
import Comidas.Alimento;
import Comidas.FileDeFrango;
import Pessoa.Dono;
import Utils.ScreenBuilder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotoesComprar {
    public JButton buttonFilé(Dono d){
        JButton buttonComprarFile = new ScreenBuilder().buttonBilder(new Models.Rectangle(110, 40, 155, 315), "Comprar", new Color(115, 115, 115), Color.white, null);
        buttonComprarFile.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            FileDeFrango file = new FileDeFrango();
         d.getInventario().put(file.getNome(), d.getInventario().getOrDefault(file.getNome(), 0)+1);

        }
    });
    return  buttonComprarFile;
    }

    public JButton buttonRacao(){
        JButton buttonComprarRacao = new ScreenBuilder().buttonBilder(new Models.Rectangle(110, 40, 360, 315), "Comprar", new Color(115, 115, 115), Color.white, null);
        buttonComprarRacao.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    });
    return buttonComprarRacao;
    }

    public JButton buttonAgua() {
        JButton buttonComprarAgua = new ScreenBuilder().buttonBilder(new Models.Rectangle(110, 40, 155, 315), "Comprar", new Color(115, 115, 115), Color.white, null);
        buttonComprarAgua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        return buttonComprarAgua;
    }

    public JButton buttonCoco(){
        JButton buttonComprarCoco = new ScreenBuilder().buttonBilder(new Models.Rectangle(110, 40, 360, 315), "Comprar", new Color(115, 115, 115), Color.white, null);
        buttonComprarCoco.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        return buttonComprarCoco;
    }


}
