package Paineis;

import Fauna.Animais;
import Fauna.TipoAnimal;
import Models.Rectangle;
import Pessoa.Dono;
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

    public void painel(JFrame Painel, Paineltransparente p, CriarAnimation animalPet, Animais animais, JLabel textenergia, Dono dono, JLabel fome, JLabel sede) {
        Painel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JButton buttonComer;
                TipoAnimal tipoAnimal = animais.getTipo();
                switch (tipoAnimal) {
                    case Gato:
                        if (paineltransparente == null) {
                            if (e.getX() >= 252 && e.getX() <= 346) {
                                if (e.getY() >= 427 && e.getY() <= 517) {
                                    paineltransparente = new Paineltransparente(new Color(0xD3D3D3), 0.75f);
                                    JButton buttonSleep = new BotoesGenericos().buttonSleep(new Rectangle(70, 70, 200, 200), animais, animalPet, "sleppcat", Painel, textenergia, "./Sprites/Gato/sprite_gato_2x", "./Sprites/Gato/SLEEP_4x", 8, 320, 256, 320);
                                    buttonSleep.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            Painel.remove(paineltransparente);
                                            Painel.repaint();
                                            paineltransparente = null;
                                        }
                                    });

                                    //comer
                                    buttonComer = new BotoesGenericos().buttonEat(animais, "comida-de-gato", new Rectangle(70, 70, 200, 30), Painel, dono, animalPet, 7, "./Sprites/Gato/EAT_4x", 320, 256, fome, 8, "./Sprites/Gato/sprite_gato_2x", sede);
                                    buttonComer.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            Painel.remove(paineltransparente);
                                            Painel.repaint();
                                            paineltransparente = null;
                                        }
                                    });

                                    paineltransparente.add(buttonComer);
                                    paineltransparente.add(buttonSleep);
                                    paineltransparente.setBounds(130, 270, 310, 310);
                                    Painel.add(paineltransparente);
                                    Painel.add(animalPet);
                                    Painel.repaint();
                                }
                            } else {

                            }
                        } else {
                            if (e.getX() >= 252 && e.getX() <= 346) {
                                if (e.getY() >= 427 && e.getY() <= 517) {
                                    Painel.remove(paineltransparente);
                                    Painel.repaint();
                                    paineltransparente = null;
                                }
                            }
                        }
                        break;
                    case Cachorro:
                        if (paineltransparente == null) {
                            if (e.getX() >= 254 && e.getX() <= 379) {
                                if (e.getY() >= 453 && e.getY() <= 556) {
                                    paineltransparente = new Paineltransparente(new Color(0xD3D3D3), 0.75f);
                                    //dormir
                                    JButton buttonSleep = new BotoesGenericos().buttonSleep(new Rectangle(70, 70, 200, 200), animais, animalPet, "dogSleep", Painel, textenergia, "./Sprites/Cachorro/lobo 4x", "./Sprites/Cachorro/HuskySleep_4x", 6, 256 , 256, 256);
                                    buttonSleep.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            Painel.remove(paineltransparente);
                                            Painel.repaint();
                                            paineltransparente = null;
                                        }
                                    });

                                             buttonComer = new BotoesGenericos().buttonEat(animais, "osso", new Rectangle(70, 70, 200, 30), Painel, dono, animalPet, 24, "./Sprites/Cachorro/HuskySniff_4x", 256, 256, fome, 6, "./Sprites/Cachorro/lobo 4x", sede);
                                            buttonComer.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    Painel.remove(paineltransparente);
                                                    Painel.repaint();
                                                    paineltransparente = null;
                                                }
                                            });

                                    paineltransparente.add(buttonComer);
                                    paineltransparente.add(buttonSleep);
                                    paineltransparente.setBounds(130, 270, 310, 310);
                                    Painel.add(paineltransparente);
                                    Painel.add(animalPet);
                                    Painel.repaint();
                                }
                            }
                        } else {
                            if (e.getX() >= 254 && e.getX() <= 379) {
                                if (e.getY() >= 453 && e.getY() <= 556) {
                                    Painel.remove(paineltransparente);
                                    Painel.repaint();
                                    paineltransparente = null;
                                }
                            }
                        }
                        break;
                    default:
                }
            }
        });

    }
}
