package Paineis;

import Fauna.Animais;
import Models.Rectangle;
import Sprites.CriarAnimation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Paineltransparente extends JPanel {
    private int arcWidth;
    private int arcHeight;
    private Color backgroundColor;
    private float transparencia;
    private Paineltransparente paineltransparente;
    private Color cor;


    public Paineltransparente(Color cor, float transparencia) {
        this.transparencia = transparencia;
        this.cor = cor;
        this.backgroundColor = cor;
        this.arcWidth = 200;
        this.arcHeight = 200;
        setLayout(new FlowLayout());
        setOpaque(false);
    }
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Suavização dos cantos (anti-aliasing)
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Define transparência (0.0 = totalmente transparente, 1.0 = totalmente opaco)
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparencia));

        // Cor de fundo com alpha aplicado
        g2d.setColor(cor);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);

        g2d.dispose();
        super.paintComponent(g);
    }

    public void painel(JFrame Painel, Paineltransparente p, CriarAnimation animalPet, Animais animais, JLabel textenergia) {
        Paineltransparente finalP = p;

        Painel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (paineltransparente == null) {
                    if (e.getX() >= 252 && e.getX() <= 346) {
                        if (e.getY() >= 427 && e.getY() <= 517) {
                            paineltransparente = new Paineltransparente(new Color(0xD3D3D3), 0.75f);
                            JButton buttonSleep = new BotoesGenericos().buttonSleep(new Rectangle(70,70,200, 200), animais, animalPet, "sleppcat", Painel, textenergia);
                            buttonSleep.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    Painel.remove(paineltransparente);
                                    Painel.repaint();
                                    paineltransparente = null;
                                }
                            });
                            paineltransparente.add(buttonSleep);
                            System.out.println("Clicou no gato!");
                            System.out.println("X:" + e.getX() + "Y:" + e.getY());
                            paineltransparente.setBounds(130, 270, 310, 310);
                            Painel.add(paineltransparente);
                            Painel.add(animalPet);
                            Painel.repaint();
                        }
                    } else {
                        System.out.println("Clicou fora do gato!");
                        System.out.println("X:" + e.getX() + "Y:" + e.getY());
                    }
                } else {
                    if (e.getX() >= 252 && e.getX() <= 346) {
                        if (e.getY() >= 427 && e.getY() <= 517) {
                            System.out.println("Fecharr");
                            Painel.remove(paineltransparente);
                            Painel.repaint();
                            paineltransparente = null;
                        }
                    }
                }
            }
        });
    }
}
