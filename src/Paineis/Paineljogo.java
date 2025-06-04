package Paineis;
import Fauna.Animais;
import Models.Rectangle;
import Pessoa.Dono;
import Utils.ScreenBuilder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class Paineljogo {
    PainelConfig painelConfig;
    PainelConfig painelStore;

    public void PinelGame(Animais animal, JFrame Painel, Dono d) {
        JLabel iconFome = new ScreenBuilder().iconBuilder("fome.png", new Rectangle(40, 40, 120, 10), new Rectangle(30, 30));
        JLabel textFome = new ScreenBuilder().textBuilder(animal.getFome() + "%", new Rectangle(100, 100, 165, -15), 18, null);
        JLabel iconSede = new ScreenBuilder().iconBuilder("sede.png", new Rectangle(40, 40, 230, 10), new Rectangle(30, 30));
        JLabel textSede = new ScreenBuilder().textBuilder(animal.getSede() + "%", new Rectangle(100, 100, 270, -15), 18, null);
        JLabel iconEnergia = new ScreenBuilder().iconBuilder("energia.png", new Rectangle(40, 40, 330, 10), new Rectangle(30, 30));
        JLabel textEnergia = new ScreenBuilder().textBuilder(animal.getEnergia() + "%", new Rectangle(100, 100, 370, -15), 18, null);
        //Butoes
        ImageIcon imagemCompras = new ImageIcon("./imagens/cesta.png");
        JButton buttonLoja = new ScreenBuilder().buttonBilder(new Rectangle(30, 30, 20, 15), null, new Color(0xF3F3F7), null, imagemCompras);
        ImageIcon imagemSair = new ImageIcon("./imagens/saida.png");
        JButton buttonSair = new ScreenBuilder().buttonBilder(new Rectangle(30, 30, 540, 605), null, new Color(0xF3F3F7), null, imagemSair);
        ImageIcon imagemVoltar = new ImageIcon("./imagens/engrenagem.png");
        JButton buttonVoltar = new ScreenBuilder().buttonBilder(new Rectangle(30, 30, 540, 15), null, new Color(0x555554), null, imagemVoltar);
        ImageIcon imagemEgrenagem = new ImageIcon("./imagens/engrenagem.png");
        JButton buttonEngrenagem = new ScreenBuilder().buttonBilder(new Rectangle(30, 30, 540, 15), null, new Color(0xF3F3F7), null, imagemEgrenagem);
        //Loja
        buttonLoja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                painelStore = new PainelConfig(30, 30, new Color(0x555554));
                PainelConfig painelPesquisa = new PainelConfig(30, 30, Color.DARK_GRAY);
                painelPesquisa.setBounds(13, 30, 50, 400);
                JButton button = new ScreenBuilder().buttonBilder(new Rectangle(30, 30, 22, 40), null, Color.DARK_GRAY, null, new ImageIcon("./imagens/fechar.png"));
                //barra lateral
                JButton buttonBebidas = new ScreenBuilder().buttonBilder(new Models.Rectangle(40, 40, 20, 200), null, Color.DARK_GRAY, null, new ImageIcon("./imagens/Bebidas.png"));
                JButton buttonPaisagem = new ScreenBuilder().buttonBilder(new Models.Rectangle(40, 40, 20, 300), null, Color.DARK_GRAY, null, new ImageIcon("./imagens/cenario.png"));
                JButton buttonComidas = new ScreenBuilder().buttonBilder(new Models.Rectangle(40, 40, 20, 100), null , Color.darkGray, null, new ImageIcon("./imagens/Comidas.png"));
                //comidas
                JLabel iconCarne = new ScreenBuilder().iconBuilder("Filé.png", new Models.Rectangle(180, 240, 110, 80), new Models.Rectangle(370, 436));// X, Y, largura, altura
                JLabel iconRaçãoDog = new ScreenBuilder().iconBuilder("RaçãoCachorro.png", new Models.Rectangle(180, 240, 310, 80), new Models.Rectangle(370, 436));
                JButton buttonComprarFilé = new BotoesComprar().buttonFilé(d);
                JButton buttonComprarRacao = new BotoesComprar().buttonRacao();
                //Bebidas
                JLabel iconAgua = new ScreenBuilder().iconBuilder("AguaProduto.png", new Models.Rectangle(180, 240, 110, 80), new Models.Rectangle(370, 436));// X, Y, largura, altura
                JLabel iconAguaCoco = new ScreenBuilder().iconBuilder("cocoProduto.png", new Models.Rectangle(180, 240, 310, 80), new Models.Rectangle(370, 436));
                JButton buttonComprarAgua = new BotoesComprar().buttonAgua();
                JButton buttonComprarCoco = new BotoesComprar().buttonCoco();
                buttonBebidas.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Painel.add(buttonComprarCoco);
                        Painel.add(iconAgua);
                        Painel.add(iconAguaCoco);
                        Painel.remove(iconCarne);
                        Painel.remove(iconRaçãoDog);
                        Painel.remove(buttonComprarRacao);
                        Painel.remove(buttonComprarFilé);
                        Painel.add(buttonComprarAgua);
                        Painel.add(painelStore);
                        Painel.repaint();
                    }
                });
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Painel.remove(painelStore);
                        Painel.remove(iconAgua);
                        Painel.remove(iconAguaCoco);
                        Painel.remove(buttonComprarAgua);
                        Painel.remove(buttonComprarCoco);
                        Painel.remove(buttonPaisagem);
                        Painel.remove(buttonComidas);
                        Painel.remove(buttonComprarRacao);
                        Painel.remove(buttonComprarFilé);
                        Painel.remove(painelPesquisa);
                        Painel.remove(button);
                        Painel.remove(buttonBebidas);
                        Painel.remove(iconCarne);
                        Painel.remove(iconRaçãoDog);
                        Painel.add(buttonEngrenagem);
                        Painel.add(iconSede);
                        Painel.add(textSede);
                        Painel.add(iconFome);
                        Painel.add(textFome);
                        Painel.add(iconEnergia);
                        Painel.add(textEnergia);
                        Painel.add(buttonLoja);
                        Painel.repaint();
                    }
                });
                buttonComidas.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Painel.remove(buttonComprarCoco);
                        Painel.remove(iconAgua);
                        Painel.remove(iconAguaCoco);
                        Painel.remove(buttonComprarAgua);
                        Painel.add(iconRaçãoDog);
                        Painel.add(iconCarne);
                        Painel.add(buttonComprarRacao);
                        Painel.add(buttonComprarFilé);
                        Painel.add(painelStore);
                        Painel.repaint();
                    }
                });
                Painel.remove(buttonEngrenagem);
                Painel.remove(buttonLoja);
                Painel.remove(buttonEngrenagem);
                Painel.remove(iconSede);
                Painel.remove(textSede);
                Painel.remove(iconFome);
                Painel.remove(textFome);
                Painel.remove(iconEnergia);
                Painel.remove(textEnergia);
                painelStore.setBounds(13, 30, 560, 400); // X, Y, largura, altura
                Painel.add(buttonPaisagem);
                Painel.add(buttonComidas);
                Painel.add(buttonBebidas);
                Painel.add(button);
                Painel.add(painelPesquisa);
                Painel.add(painelStore);
                Painel.repaint();
            }
        });
        //Botão de saida
        buttonSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Painel.dispose();
                System.exit(0);
            }
        });
        //Botao de Configurações
        buttonEngrenagem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonVoltar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Painel.remove(painelConfig);
                        Painel.remove(buttonVoltar);
                        Painel.add(buttonEngrenagem);
                        Painel.add(buttonSair);
                        Painel.add(iconSede);
                        Painel.add(textSede);
                        Painel.add(iconEnergia);
                        Painel.add(textEnergia);
                        Painel.repaint();
                    }
                });
                painelConfig = new PainelConfig(30, 30, new Color(0x555554));
                Painel.add(buttonVoltar);
                Painel.add(painelConfig);
                Painel.remove(buttonEngrenagem);
                Painel.remove(buttonSair);
                Painel.remove(iconSede);
                Painel.remove(textSede);
                Painel.remove(iconEnergia);
                Painel.remove(textEnergia);
                painelConfig.setBounds(210, 0, 400, 700); // X, Y, largura, altura
                Painel.repaint();
            }
        });
        //Temporizador
        Timer timer = new Timer();
        TimerTask tarefa = new TimerTask() {
            @Override
            public void run() {
                animal.setSede(animal.getSede() - 2);
                animal.setFome(animal.getFome() - 1);
                System.out.println(animal.getFome() + " fome");
                System.out.println(animal.getSede() + " sede");
                new ScreenBuilder().atualizar(animal, animal.getSede(), textSede);
                new ScreenBuilder().atualizar(animal, animal.getFome(), textFome);
                System.out.println(animal.getNome());
            }
        };
        timer.scheduleAtFixedRate(tarefa, 0L, 3 * 60 * 1000L);
        //considerações finais
        Painel.add(textEnergia);
        Painel.add(buttonSair);
        Painel.add(buttonLoja);
        Painel.add(buttonEngrenagem);
        Painel.add(iconEnergia);
        Painel.add(textSede);
        Painel.add(iconSede);
        Painel.add(iconFome);
        Painel.add(textFome);
        Painel.setVisible(true);
    }
}
