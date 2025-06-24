package Paineis;

import Bebidas.Bebida;
import Comidas.Alimento;
import Fauna.Animais;
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

    public JButton buttonSleep(Rectangle rectangle, Animais animais, CriarAnimation criarAnimation, String caminho, JFrame painelPrincipal, JLabel textenergia) {
        ImageIcon icon = new ImageIcon("./imagens/" + caminho + ".png");
        JButton buttonAnimacao = new ScreenBuilder().buttonBilder(rectangle, null, null, null, icon);
        buttonAnimacao.setBackground(new Color(218, 218, 218, 128));

        buttonAnimacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    if (animais.getEnergia() <= 30) {
                        paineltransparente = new Paineltransparente(Color.BLACK, 0.6f);
                        paineltransparente.setBounds(-100, 0, 800,800);
                        criarAnimation.mudancaAcao(8, "./Sprites/Gato/SLEEP_4x", 320, 256);
                        painelPrincipal.add(paineltransparente);
                        painelPrincipal.add(criarAnimation);
                        for (int i = 0; i < 100; i++) {
                            java.util.Timer timer = new Timer();
                            TimerTask tarefa = new TimerTask() {
                                @Override
                                public void run() {
                                    if(animais.getFome()<= 100){
                                        animais.setEnergia(animais.getEnergia() + 30);
                                        new ScreenBuilder().atualizar(animais, animais.getFome(), textenergia);
                                    }else{
                                        criarAnimation.mudancaAcao(8, "./Sprites/Gato/sprite_gato_2x", 320, 256);
                                        painelPrincipal.remove(paineltransparente);
                                        painelPrincipal.repaint();
                                        timer.cancel();
                                    }
                                }
                            };
                            timer.scheduleAtFixedRate(tarefa, 0L,30 * 1000L);
                        }

                    } else {
                        System.out.println("Animal está sem sono!");
                    }
            }
        });

        return buttonAnimacao;
    }
    public boolean isAberto() {
        return aberto;
    }

    public void setAberto(boolean aberto) {
        this.aberto = aberto;
    }
}
