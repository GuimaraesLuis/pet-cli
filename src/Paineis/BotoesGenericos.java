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
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class BotoesGenericos {

    private boolean aberto = false;
    private Paineltransparente paineltransparente; // mantém a referência
    private int rotacao = 0;

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

    public JButton buttonEat(Animais animais, String imagem, Rectangle rectangle, JFrame Painel, Dono dono, CriarAnimation criarAnimation, int frames, String files, int w, int h, JLabel textfome, int CenasIDLE, String CaminhoIDLE, JLabel textSede){
        ImageIcon icon = new ImageIcon("./imagens/" + imagem + ".png");
        JButton buttonComer = new ScreenBuilder().buttonBilder(rectangle, null, null, null, icon);
        buttonComer.setBackground(new Color(0, 0, 0, 0));
        buttonComer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton iComida1, iComida2, iBebida1, iBebida2;
                JLabel textComida1, textComida2, textBebida1, textbebida2;
                int valorC1, valorC2, valorB1, ValorB2;
                JLabel poteComida = new ScreenBuilder().iconBuilder("BOWL_4x.png", new Rectangle(40, 40, 343, 512), new Rectangle(50, 50));
                if(animais.getFome() <= 60 || animais.getSede() <= 60){
                    PainelGenerico painelInventario = new PainelGenerico(30, 30, new Color(0xD3D3D3));
                    painelInventario.setBounds(13, 80, 560, 400);
                    HashMap<String, Integer> inventario = dono.getInventario();
                    TipoAnimal tipoAnimal = animais.getTipo();
                    switch (tipoAnimal){
                        case Cachorro:
                            valorC1 = inventario.get("Filé de Frango");
                            painelInventario.add(new ScreenBuilder().textBuilder(String.valueOf(valorC1), new Rectangle(100, 100, 225, 105), 18, Color.white));
                            iComida1 =  new ScreenBuilder().buttonBilder(new Rectangle(160, 230, 110, -50), null, null, null, new ImageIcon("./imagens/InventarioFile.png"));
                            iComida1.setBackground(new Color(0, 0, 0, 0));
                            iComida1.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(inventario.get("Filé de Frango") >= 1) {
                                        if(animais.getFome() < 50) {
                                            inventario.put("Filé de Frango", valorC1 - 1);
                                            criarAnimation.mudancaAcao(frames, files, w, h);

                                            Timer timer = new Timer();
                                            timer.scheduleAtFixedRate(new TimerTask() {
                                                @Override
                                                public void run() {
                                                    if (rotacao < 30) {
                                                        rotacao++;rotacao++;rotacao++;rotacao++;rotacao++;
                                                        animais.setFome(Math.min(100, animais.getFome() + 5));
                                                        SwingUtilities.invokeLater(() -> {
                                                            new ScreenBuilder().atualizar(animais, animais.getFome(), textfome);
                                                        });
                                                    } else {
                                                        timer.cancel();
                                                        SwingUtilities.invokeLater(() -> {
                                                            Painel.remove(poteComida);
                                                            criarAnimation.mudancaAcao(CenasIDLE, CaminhoIDLE, w, 256);
                                                        });
                                                    }
                                                }
                                            }, 0, 1000); // atualiza a cada 1 segundo

                                            Painel.add(poteComida, 2);
                                            Painel.remove(painelInventario);
                                            Painel.repaint();
                                        }else{
                                            System.out.println("seu animal nn esta com fome!");
                                            Painel.remove(painelInventario);
                                            Painel.repaint();
                                        }
                                    }else{
                                        System.out.println("Voce nn tem este item!");
                                        Painel.remove(painelInventario);
                                        Painel.repaint();
                                    }
                                }
                            });

                            valorC2 = inventario.get("Ração Pedigree");
                            painelInventario.add(new ScreenBuilder().textBuilder(String.valueOf(valorC2), new Rectangle(100, 100, 425, 105), 18, Color.white));
                            iComida2 = new ScreenBuilder().buttonBilder(new Rectangle(160, 230, 310, -50), null, null, null, new ImageIcon("./imagens/InventarioRacao.png"));
                            iComida2.setBackground(new Color(0, 0, 0, 0));
                            iComida2.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(inventario.get("Ração Pedigree") >= 1) {
                                        if(animais.getFome() < 50) {
                                            inventario.put("Ração Pedigree", valorC2 - 1);
                                            criarAnimation.mudancaAcao(frames, files, w, h);

                                            Timer timer = new Timer();
                                            timer.scheduleAtFixedRate(new TimerTask() {
                                                @Override
                                                public void run() {
                                                    if (rotacao < 60) {
                                                        rotacao++;rotacao++;rotacao++;rotacao++;rotacao++;
                                                        animais.setFome(Math.min(100, animais.getFome() + 5));
                                                        SwingUtilities.invokeLater(() -> {
                                                            new ScreenBuilder().atualizar(animais, animais.getFome(), textfome);
                                                        });
                                                    } else {
                                                        timer.cancel();
                                                        SwingUtilities.invokeLater(() -> {
                                                            Painel.remove(poteComida);
                                                            criarAnimation.mudancaAcao(CenasIDLE, CaminhoIDLE, w, 256);
                                                        });
                                                    }
                                                }
                                            }, 0, 1000); // atualiza a cada 1 segundo

                                            Painel.add(poteComida, 2);
                                            Painel.remove(painelInventario);
                                            Painel.repaint();
                                        }else{
                                            System.out.println("seu animal nn esta com fome!");
                                            Painel.remove(painelInventario);
                                            Painel.repaint();
                                        }
                                    }else{
                                        System.out.println("Voce nn tem este item!");
                                        Painel.remove(painelInventario);
                                        Painel.repaint();
                                    }
                                }
                            });

                            valorB1 = inventario.get("Água de coco");
                            painelInventario.add(new ScreenBuilder().textBuilder(String.valueOf(valorB1), new Rectangle(100, 100, 425, 295), 18, Color.white));
                            iBebida1 = new ScreenBuilder().buttonBilder(new Rectangle(160, 230, 310, 140), null, null, null, new ImageIcon("./imagens/InventarioCoco.png"));
                            iBebida1.setBackground(new Color(0, 0, 0, 0));
                            iBebida1.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(inventario.get("Água de coco") >= 1) {
                                        if(animais.getSede() < 50) {
                                            inventario.put("Água de coco", valorB1 - 1);
                                            criarAnimation.mudancaAcao(frames, files, w, h);

                                            Timer timer = new Timer();
                                            timer.scheduleAtFixedRate(new TimerTask() {
                                                @Override
                                                public void run() {
                                                    if (rotacao < 60) {
                                                        rotacao++;rotacao++;rotacao++;rotacao++;rotacao++;
                                                        animais.setSede(Math.min(100, animais.getSede() + 5));
                                                        SwingUtilities.invokeLater(() -> {
                                                            new ScreenBuilder().atualizar(animais, animais.getSede(), textSede);
                                                        });
                                                    } else {
                                                        timer.cancel();
                                                        SwingUtilities.invokeLater(() -> {
                                                            Painel.remove(poteComida);
                                                            criarAnimation.mudancaAcao(CenasIDLE, CaminhoIDLE, w, 256);
                                                        });
                                                    }
                                                }
                                            }, 0, 1000); // atualiza a cada 1 segundo

                                            Painel.add(poteComida, 2);
                                            Painel.remove(painelInventario);
                                            Painel.repaint();
                                        }else{
                                            System.out.println("seu animal nn esta com sede!");
                                            Painel.remove(painelInventario);
                                            Painel.repaint();
                                        }
                                    }else{
                                        System.out.println("Voce nn tem este item!");
                                        Painel.remove(painelInventario);
                                        Painel.repaint();
                                    }
                                }
                            });

                            ValorB2 = inventario.get("Água Potável");
                            painelInventario.add(new ScreenBuilder().textBuilder(String.valueOf(ValorB2), new Rectangle(100, 100, 225, 295), 18, Color.white));
                            iBebida2 = new ScreenBuilder().buttonBilder(new Rectangle(160, 230, 110, 140), null, null, null, new ImageIcon("./imagens/InventarioAgua.png"));
                            iBebida2.setBackground(new Color(0, 0, 0, 0));
                            iBebida2.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(inventario.get("Água Potável") >= 1) {
                                        if(animais.getSede() < 50) {
                                            inventario.put("Água Potável", ValorB2 - 1);
                                            criarAnimation.mudancaAcao(frames, files, w, h);

                                            Timer timer = new Timer();
                                            timer.scheduleAtFixedRate(new TimerTask() {
                                                @Override
                                                public void run() {
                                                    if (rotacao < 30) {
                                                        rotacao++;rotacao++;rotacao++;rotacao++;rotacao++;
                                                        animais.setSede(Math.min(100, animais.getSede() + 5));
                                                        SwingUtilities.invokeLater(() -> {
                                                            new ScreenBuilder().atualizar(animais, animais.getSede(), textSede);
                                                        });
                                                    } else {
                                                        timer.cancel();
                                                        SwingUtilities.invokeLater(() -> {
                                                            Painel.remove(poteComida);
                                                            criarAnimation.mudancaAcao(CenasIDLE, CaminhoIDLE, w, 256);
                                                        });
                                                    }
                                                }
                                            }, 0, 1000); // atualiza a cada 1 segundo

                                            Painel.add(poteComida, 2);
                                            Painel.remove(painelInventario);
                                            Painel.repaint();
                                        }else{
                                            System.out.println("seu animal nn esta com sede!");
                                            Painel.remove(painelInventario);
                                            Painel.repaint();
                                        }
                                    }else{
                                        System.out.println("Voce nn tem este item!");
                                        Painel.remove(painelInventario);
                                        Painel.repaint();
                                    }
                                }
                            });

                            painelInventario.add(iComida1);
                            painelInventario.add(iComida2);
                            painelInventario.add(iBebida1);
                            painelInventario.add(iBebida2);
                            Painel.add(painelInventario, 0);
                            Painel.repaint();
                            break;
                        case Gato:
                            valorC1 = inventario.get("Sardinha");
                            painelInventario.add(new ScreenBuilder().textBuilder(String.valueOf(valorC1), new Rectangle(100, 100, 225, 105), 18, Color.white));
                            iComida1 =  new ScreenBuilder().buttonBilder(new Rectangle(160, 230, 110, -50), null, null, null, new ImageIcon("./imagens/InventarioSardinha.png"));
                            iComida1.setBackground(new Color(0, 0, 0, 0));
                            iComida1.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(inventario.get("Sardinha") >= 1) {
                                        if(animais.getFome() < 50) {
                                            inventario.put("Sardinha", valorC1 - 1);
                                            poteComida.setBounds(40, 230, 343, 512);
                                            criarAnimation.mudancaAcao(frames, files, w, h);

                                            Timer timer = new Timer();
                                            timer.scheduleAtFixedRate(new TimerTask() {
                                                @Override
                                                public void run() {
                                                    if (rotacao < 30) {
                                                        rotacao++;rotacao++;rotacao++;rotacao++;rotacao++;
                                                        animais.setFome(Math.min(100, animais.getFome() + 5));
                                                        SwingUtilities.invokeLater(() -> {
                                                            new ScreenBuilder().atualizar(animais, animais.getFome(), textfome);
                                                        });
                                                    } else {
                                                        timer.cancel();
                                                        SwingUtilities.invokeLater(() -> {
                                                            Painel.remove(poteComida);
                                                            criarAnimation.mudancaAcao(CenasIDLE, CaminhoIDLE, w, 256);
                                                        });
                                                    }
                                                }
                                            }, 0, 1000); // atualiza a cada 1 segundo

                                            Painel.add(poteComida, 2);
                                            Painel.remove(painelInventario);
                                            Painel.repaint();
                                        }else{
                                            System.out.println("seu animal nn esta com fome!");
                                            Painel.remove(painelInventario);
                                            Painel.repaint();
                                        }
                                    }else{
                                        System.out.println("Voce nn tem este item!");
                                        Painel.remove(painelInventario);
                                        Painel.repaint();
                                    }
                                }
                            });

                            valorC2 = inventario.get("Ração Del Gatto");
                            painelInventario.add(new ScreenBuilder().textBuilder(String.valueOf(valorC2), new Rectangle(100, 100, 425, 105), 18, Color.white));
                            iComida2 = new ScreenBuilder().buttonBilder(new Rectangle(160, 230, 310, -50), null, null, null, new ImageIcon("./imagens/InventarioRacaoCat.png"));
                            iComida2.setBackground(new Color(0, 0, 0, 0));
                            iComida2.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(inventario.get("Ração Del Gatto") >= 1) {
                                        if(animais.getFome() < 50) {
                                            poteComida.setBounds(40, 230, 343, 512);
                                            inventario.put("Ração Del Gatto", valorC2 - 1);
                                            criarAnimation.mudancaAcao(frames, files, w, h);

                                            Timer timer = new Timer();
                                            timer.scheduleAtFixedRate(new TimerTask() {
                                                @Override
                                                public void run() {
                                                    if (rotacao < 60) {
                                                        rotacao++;rotacao++;rotacao++;rotacao++;rotacao++;
                                                        animais.setFome(Math.min(100, animais.getFome() + 5));
                                                        SwingUtilities.invokeLater(() -> {
                                                            new ScreenBuilder().atualizar(animais, animais.getFome(), textfome);
                                                        });
                                                    } else {
                                                        timer.cancel();
                                                        SwingUtilities.invokeLater(() -> {
                                                            Painel.remove(poteComida);
                                                            criarAnimation.mudancaAcao(CenasIDLE, CaminhoIDLE, w, 256);
                                                        });
                                                    }
                                                }
                                            }, 0, 1000); // atualiza a cada 1 segundo

                                            Painel.add(poteComida, 2);
                                            Painel.remove(painelInventario);
                                            Painel.repaint();
                                        }else{
                                            System.out.println("seu animal nn esta com fome!");
                                            Painel.remove(painelInventario);
                                            Painel.repaint();
                                        }
                                    }else{
                                        System.out.println("Voce nn tem este item!");
                                        Painel.remove(painelInventario);
                                        Painel.repaint();
                                    }
                                }
                            });

                            valorB1 = inventario.get("Leite");
                            painelInventario.add(new ScreenBuilder().textBuilder(String.valueOf(valorB1), new Rectangle(100, 100, 425, 295), 18, Color.white));
                            iBebida1 = new ScreenBuilder().buttonBilder(new Rectangle(160, 230, 310, 140), null, null, null, new ImageIcon("./imagens/InventarioLeite.png"));
                            iBebida1.setBackground(new Color(0, 0, 0, 0));
                            iBebida1.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(inventario.get("Leite") >= 1) {
                                        if(animais.getSede() < 50) {
                                            inventario.put("Leite", valorB1 - 1);
                                            poteComida.setBounds(40, 230, 343, 512);
                                            criarAnimation.mudancaAcao(frames, files, w, h);

                                            Timer timer = new Timer();
                                            timer.scheduleAtFixedRate(new TimerTask() {
                                                @Override
                                                public void run() {
                                                    if (rotacao < 60) {
                                                        rotacao++;rotacao++;rotacao++;rotacao++;rotacao++;
                                                        animais.setSede(Math.min(100, animais.getSede() + 5));
                                                        SwingUtilities.invokeLater(() -> {
                                                            new ScreenBuilder().atualizar(animais, animais.getSede(), textSede);
                                                        });
                                                    } else {
                                                        timer.cancel();
                                                        SwingUtilities.invokeLater(() -> {
                                                            Painel.remove(poteComida);
                                                            criarAnimation.mudancaAcao(CenasIDLE, CaminhoIDLE, w, 256);
                                                        });
                                                    }
                                                }
                                            }, 0, 1000); // atualiza a cada 1 segundo

                                            Painel.add(poteComida, 2);
                                            Painel.remove(painelInventario);
                                            Painel.repaint();
                                        }else{
                                            System.out.println("seu animal nn esta com sede!");
                                            Painel.remove(painelInventario);
                                            Painel.repaint();
                                        }
                                    }else{
                                        System.out.println("Voce nn tem este item!");
                                        Painel.remove(painelInventario);
                                        Painel.repaint();
                                    }
                                }
                            });

                            ValorB2 = inventario.get("Água Potável");
                            painelInventario.add(new ScreenBuilder().textBuilder(String.valueOf(ValorB2), new Rectangle(100, 100, 225, 295), 18, Color.white));
                            iBebida2 = new ScreenBuilder().buttonBilder(new Rectangle(160, 230, 110, 140), null, null, null, new ImageIcon("./imagens/InventarioAgua.png"));
                            iBebida2.setBackground(new Color(0, 0, 0, 0));
                            iBebida2.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(inventario.get("Água Potável") >= 1) {
                                        if(animais.getSede() < 50) {
                                            inventario.put("Água Potável", ValorB2 - 1);
                                            poteComida.setBounds(40, 230, 343, 512);
                                            criarAnimation.mudancaAcao(frames, files, w, h);

                                            Timer timer = new Timer();
                                            timer.scheduleAtFixedRate(new TimerTask() {
                                                @Override
                                                public void run() {
                                                    if (rotacao < 30) {
                                                        rotacao++;rotacao++;rotacao++;rotacao++;rotacao++;
                                                        animais.setSede(Math.min(100, animais.getSede() + 5));
                                                        SwingUtilities.invokeLater(() -> {
                                                            new ScreenBuilder().atualizar(animais, animais.getSede(), textSede);
                                                        });
                                                    } else {
                                                        timer.cancel();
                                                        SwingUtilities.invokeLater(() -> {
                                                            Painel.remove(poteComida);
                                                            criarAnimation.mudancaAcao(CenasIDLE, CaminhoIDLE, w, 256);
                                                        });
                                                    }
                                                }
                                            }, 0, 1000); // atualiza a cada 1 segundo

                                            Painel.add(poteComida, 2);
                                            Painel.remove(painelInventario);
                                            Painel.repaint();
                                        }else{
                                            System.out.println("seu animal nn esta com sede!");
                                            Painel.remove(painelInventario);
                                            Painel.repaint();
                                        }
                                    }else{
                                        System.out.println("Voce nn tem este item!");
                                        Painel.remove(painelInventario);
                                        Painel.repaint();
                                    }
                                }
                            });

                            painelInventario.add(iComida1);
                            painelInventario.add(iComida2);
                            painelInventario.add(iBebida1);
                            painelInventario.add(iBebida2);
                            Painel.add(painelInventario, 0);
                            Painel.repaint();
                            break;
                        default:

                    }
                }
            }
        });
        return buttonComer;
    }

    public JButton buttonAnim(Dono dono, Animais animais, String imagem, Rectangle rectangle, CriarAnimation criarAnimation, String acao1, String acao2, String acao3, String acao4, String acao5, int frames1, int fremes2, int frames3, int frames4, int frames5, int w, int h, int CenasIDLE, String CaminhoIDLE, JLabel textSede, JLabel textFome, JLabel textEnergia){
        ImageIcon icon = new ImageIcon("./imagens/" + imagem + ".png");
        JButton buttonBrincar = new ScreenBuilder().buttonBilder(rectangle, null, null, null, icon);
        buttonBrincar.setBackground(new Color(0, 0, 0, 0));
        buttonBrincar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int max = 5;
                int min = 1;
                int range = max - min + 1;
                int random = (int) (Math.random() * range) + min;

                if (animais.getEnergia() >= 20) {
                    System.out.println(random);
                switch (random) {
                    case 1:
                        criarAnimation.mudancaAcao(frames1, acao1, w, h);
                        Timer timer = new Timer();
                        timer.scheduleAtFixedRate(new TimerTask() {
                            @Override
                            public void run() {
                                if (rotacao < 30) {
                                    rotacao++;
                                    rotacao++;
                                    rotacao++;
                                    rotacao++;
                                    rotacao++;
                                    animais.setSede(animais.getSede() - 2);
                                    animais.setFome(animais.getFome() - 3);
                                    animais.setEnergia(animais.getEnergia() - 3);
                                    dono.setCoins(dono.getCoins() + 10);
                                    SwingUtilities.invokeLater(() -> {
                                        new ScreenBuilder().atualizar(animais, animais.getSede(), textSede);
                                        new ScreenBuilder().atualizar(animais, animais.getEnergia(), textEnergia);
                                        new ScreenBuilder().atualizar(animais, animais.getFome(), textFome);
                                    });
                                } else {
                                    timer.cancel();
                                    SwingUtilities.invokeLater(() -> {
                                        criarAnimation.mudancaAcao(CenasIDLE, CaminhoIDLE, w, 256);
                                    });
                                }
                            }
                        }, 0, 1000); // atualiza a cada 1 segundo

                        break;
                    case 2:
                        criarAnimation.mudancaAcao(fremes2, acao2, w, h);

                        Timer timer2 = new Timer();
                        timer2.scheduleAtFixedRate(new TimerTask() {
                            @Override
                            public void run() {
                                if (rotacao < 30) {
                                    rotacao++;
                                    rotacao++;
                                    rotacao++;
                                    rotacao++;
                                    rotacao++;
                                    animais.setSede(animais.getSede() - 2);
                                    animais.setFome(animais.getFome() - 3);
                                    animais.setEnergia(animais.getEnergia() - 3);
                                    dono.setCoins(dono.getCoins() + 10);
                                    SwingUtilities.invokeLater(() -> {
                                        new ScreenBuilder().atualizar(animais, animais.getSede(), textSede);
                                        new ScreenBuilder().atualizar(animais, animais.getEnergia(), textEnergia);
                                        new ScreenBuilder().atualizar(animais, animais.getFome(), textFome);
                                    });
                                } else {
                                    timer2.cancel();
                                    SwingUtilities.invokeLater(() -> {
                                        criarAnimation.mudancaAcao(CenasIDLE, CaminhoIDLE, w, 256);
                                    });
                                }
                            }
                        }, 0, 1000); // atualiza a cada 1 segundo

                        break;
                    case 3:
                        criarAnimation.mudancaAcao(frames3, acao3, w, h);

                        Timer timer3 = new Timer();
                        timer3.scheduleAtFixedRate(new TimerTask() {
                            @Override
                            public void run() {
                                if (rotacao < 30) {
                                    rotacao++;
                                    rotacao++;
                                    rotacao++;
                                    rotacao++;
                                    rotacao++;
                                    animais.setSede(animais.getSede() - 2);
                                    animais.setFome(animais.getFome() - 3);
                                    animais.setEnergia(animais.getEnergia() - 3);
                                    dono.setCoins(dono.getCoins() + 10);
                                    SwingUtilities.invokeLater(() -> {
                                        new ScreenBuilder().atualizar(animais, animais.getSede(), textSede);
                                        new ScreenBuilder().atualizar(animais, animais.getEnergia(), textEnergia);
                                        new ScreenBuilder().atualizar(animais, animais.getFome(), textFome);
                                    });
                                } else {
                                    timer3.cancel();
                                    SwingUtilities.invokeLater(() -> {
                                        criarAnimation.mudancaAcao(CenasIDLE, CaminhoIDLE, w, 256);
                                    });
                                }
                            }
                        }, 0, 1000); // atualiza a cada 1 segundo

                        break;
                    case 4:
                        criarAnimation.mudancaAcao(frames4, acao4, w, h);

                        Timer timer4 = new Timer();
                        timer4.scheduleAtFixedRate(new TimerTask() {
                            @Override
                            public void run() {
                                if (rotacao < 30) {
                                    rotacao++;
                                    rotacao++;
                                    rotacao++;
                                    rotacao++;
                                    rotacao++;
                                    animais.setSede(animais.getSede() - 2);
                                    animais.setFome(animais.getFome() - 3);
                                    animais.setEnergia(animais.getEnergia() - 3);
                                    dono.setCoins(dono.getCoins() + 10);
                                    SwingUtilities.invokeLater(() -> {
                                        new ScreenBuilder().atualizar(animais, animais.getSede(), textSede);
                                        new ScreenBuilder().atualizar(animais, animais.getEnergia(), textEnergia);
                                        new ScreenBuilder().atualizar(animais, animais.getFome(), textFome);
                                    });
                                } else {
                                    timer4.cancel();
                                    SwingUtilities.invokeLater(() -> {
                                        criarAnimation.mudancaAcao(CenasIDLE, CaminhoIDLE, w, 256);
                                    });
                                }
                            }
                        }, 0, 1000); // atualiza a cada 1 segundo

                        break;
                    case 5:
                        criarAnimation.mudancaAcao(frames5, acao5, w, h);

                        Timer timer5 = new Timer();
                        timer5.scheduleAtFixedRate(new TimerTask() {
                            @Override
                            public void run() {
                                if (rotacao < 30) {
                                    rotacao++;
                                    rotacao++;
                                    rotacao++;
                                    rotacao++;
                                    rotacao++;
                                    animais.setSede(animais.getSede() - 2);
                                    animais.setFome(animais.getFome() - 3);
                                    animais.setEnergia(animais.getEnergia() - 3);
                                    dono.setCoins(dono.getCoins() + 10);
                                    SwingUtilities.invokeLater(() -> {
                                        new ScreenBuilder().atualizar(animais, animais.getSede(), textSede);
                                        new ScreenBuilder().atualizar(animais, animais.getEnergia(), textEnergia);
                                        new ScreenBuilder().atualizar(animais, animais.getFome(), textFome);
                                    });
                                } else {
                                    timer5.cancel();
                                    SwingUtilities.invokeLater(() -> {
                                        criarAnimation.mudancaAcao(CenasIDLE, CaminhoIDLE, w, 256);
                                    });
                                }
                            }
                        }, 0, 1000); // atualiza a cada 1 segundo

                        break;
                    default:
                }
            }

            }
        });
        return buttonBrincar;
    }
}
