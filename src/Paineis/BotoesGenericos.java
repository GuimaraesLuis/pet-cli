package Paineis;

import Bebidas.Bebida;
import Comidas.Alimento;
import Fauna.Animais;
import Fauna.AnimaisActions;
import Fauna.TipoAnimal;
import Models.Rectangle;
import Pessoa.Dono;
import Sprites.CriarAnimation;
import Utils.ScreenBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class BotoesGenericos {

    private boolean aberto = false;
    private Paineltransparente paineltransparente; // mantém a referência

    public JButton buttonComprarComida(Rectangle rectangle, Dono dono, Alimento alimento, JLabel label) {
        JButton buttonComprar = new ScreenBuilder().buttonBilder(
                new Rectangle(rectangle.w, rectangle.h, rectangle.x, rectangle.y),
                "Comprar",
                new Color(0xB7B7B7),
                Color.white,
                null
        );

        buttonComprar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dono.getCoins() >= alimento.getCusto()) {
                    dono.setCoins(dono.getCoins() - alimento.getCusto());
                    dono.getInventario().put(alimento.getNome(), dono.getInventario().getOrDefault(alimento.getNome(), 0) + 1);
                    label.setBounds(85, 40, 510, 110);
                    label.setText("Compra efetuada com sucesso!");
                } else {
                    label.setBounds(165, 40, 410, 110);
                    label.setText("Saldo insuficiente!");
                }
            }
        });

        return buttonComprar;
    }

    public JButton buttonComprarAgua(Rectangle rectangle, Dono dono, Bebida bebida, JLabel label) {
        JButton buttonComprar = new ScreenBuilder().buttonBilder(
                new Rectangle(rectangle.w, rectangle.h, rectangle.x, rectangle.y),
                "Comprar",
                new Color(0xB7B7B7),
                Color.white,
                null
        );

        buttonComprar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dono.getCoins() >= bebida.getCusto()) {
                    dono.setCoins(dono.getCoins() - bebida.getCusto());
                    dono.getInventario().put(bebida.getNome(), dono.getInventario().getOrDefault(bebida.getNome(), 0) + 1);
                    label.setBounds(85, 80, 510, 110);
                    label.setText("Compra efetuada com sucesso!");
                } else {
                    label.setBounds(165, 40, 410, 110);
                    label.setText("Saldo insuficiente!");
                }
            }
        });

        return buttonComprar;
    }

    public JButton buttonSleep(Rectangle rectangle, Animais animais, CriarAnimation criarAnimation, String caminho, JFrame painelPrincipal, JLabel textenergia, String CaminhoIDLE, String CaminhoDormir, int CenasIDLE, int TileW, int TileH, int w) {
        ImageIcon icon = new ImageIcon("./imagens/" + caminho + ".png");
        JButton buttonAnimacao = new ScreenBuilder().buttonBilder(rectangle, null, null, null, icon);
        buttonAnimacao.setBackground(new Color(0, 0, 0, 0));

        buttonAnimacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (animais.getEnergia() < 100 && !aberto) {
                    aberto = true;

                    paineltransparente = new Paineltransparente(Color.BLACK, 0.6f);
                    paineltransparente.setBounds(-90, 0, 800, 800);
                    criarAnimation.mudancaAcao(8, CaminhoDormir, TileW, TileH);
                    painelPrincipal.add(paineltransparente);
                    painelPrincipal.add(criarAnimation);
                    paineltransparente.setVisible(true);


                    Timer timer = new Timer();
                    timer.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            if (animais.getEnergia() < 100) {
                                animais.setEnergia(Math.min(100, animais.getEnergia() + 10));
                                SwingUtilities.invokeLater(() -> {
                                    new ScreenBuilder().atualizar(animais, animais.getEnergia(), textenergia);
                                });
                            } else {
                                timer.cancel();
                                SwingUtilities.invokeLater(() -> {
                                    criarAnimation.mudancaAcao(CenasIDLE, CaminhoIDLE, w, 256);
                                    paineltransparente.setVisible(false);
                                    aberto = false;
                                });
                            }
                        }
                    }, 0, 1000); // atualiza a cada 1 segundo
                } else {
                    System.out.println("Animal está sem sono ou já está dormindo.");
                }
            }
        });

        return buttonAnimacao;
    }

    public JButton buttonEat(Animais animais, String imagem, Rectangle rectangle, JFrame Painel){
        ImageIcon icon = new ImageIcon("./imagens/" + imagem + ".png");
        JButton buttonComer = new ScreenBuilder().buttonBilder(rectangle, null, null, null, icon);
        buttonComer.setBackground(new Color(0, 0, 0, 0));
        buttonComer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton iComida1, iComida2, iBebida1, iBebida2;
                if(animais.getFome() <= 60 || animais.getSede() <= 60){
                    PainelGenerico painelInventario = new PainelGenerico(30, 30, new Color(0xD3D3D3));
                    painelInventario.setBounds(13, 30, 560, 400); // X, Y, largura, altura
                    TipoAnimal tipoAnimal = animais.getTipo();
                    switch (tipoAnimal){
                        case Cachorro:
                            iComida1 =  new ScreenBuilder().buttonBilder(new Rectangle(200, 270, 110, -50), null, null, null, new ImageIcon("./imagens/InventarioFile.png"));
                            iComida1.setBackground(new Color(0, 0, 0, 0));

                            iComida2 = new ScreenBuilder().buttonBilder(new Rectangle(200, 270, 310, -50), null, null, null, new ImageIcon("./imagens/InventarioRacao.png"));
                            iComida2.setBackground(new Color(0, 0, 0, 0));

                            iBebida1 = new ScreenBuilder().buttonBilder(new Rectangle(200, 270, 310, 140), null, null, null, new ImageIcon("./imagens/InventarioAgua.png"));
                            iBebida1.setBackground(new Color(0, 0, 0, 0));

                            iBebida2 = new ScreenBuilder().buttonBilder(new Rectangle(200, 270, 110, 140), null, null, null, new ImageIcon("./imagens/InventarioCoco.png"));
                            iBebida2.setBackground(new Color(0, 0, 0, 0));

                            painelInventario.add(iComida1);
                            painelInventario.add(iComida2);
                            painelInventario.add(iBebida1);
                            painelInventario.add(iBebida2);
                            Painel.getContentPane().removeAll();
                            Painel.add(painelInventario);
                            break;
                        case Gato:

                            break;
                        default:
                    }
                }
            }
        });
        return buttonComer;
    }
}
