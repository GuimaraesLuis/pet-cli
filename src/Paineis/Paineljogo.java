package Paineis;
import BancoDados.AttBancoDados;
import Bebidas.Agua;
import Bebidas.AguaCoco;
import Bebidas.Leite;
import Comidas.DelGato;
import Comidas.FileDeFrango;
import Comidas.Pedigree;
import Comidas.Sardinha;
import Fauna.Animais;
import Fauna.TipoAnimal;
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
    private JLabel iconComida1, iconComida2, iconBebida1, iconBebida2;
    private JButton buttonComprarBebida, buttonComprarBebida2, buttonComprar1, buttonComprar2;

    public void PanelGame(Animais animal, JFrame Painel, Dono d) {
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
                painelStore = new PainelConfig(30, 30, new Color(0xD3D3D3));
                PainelConfig painelPesquisa = new PainelConfig(30, 30, new Color(0xB7B7B7));
                painelPesquisa.setBounds(13, 30, 50, 400);
                JLabel labelBemVindo = new ScreenBuilder().textBuilder("Bem-vindo a pet-store", new Rectangle(410, 110, 145, 160), 30, Color.white);
                JButton button = new ScreenBuilder().buttonBilder(new Rectangle(30, 30, 22, 40), null, new Color(0xB7B7B7), null, new ImageIcon("./imagens/fechar.png"));
                //barra lateral
                JButton buttonBebidas = new ScreenBuilder().buttonBilder(new Rectangle(40, 40, 18, 200), null, new Color(0xB7B7B7), null, new ImageIcon("./imagens/Bebidas.png"));
                JButton buttonPaisagem = new ScreenBuilder().buttonBilder(new Rectangle(40, 40, 18, 300), null, new Color(0xB7B7B7), null, new ImageIcon("./imagens/cenario.png"));
                JButton buttonComidas = new ScreenBuilder().buttonBilder(new Rectangle(40, 40, 18, 100), null , new Color(0xB7B7B7), null, new ImageIcon("./imagens/Comidas.png"));
                //comidas
                 TipoAnimal tipoAnimal = animal.getTipo();
                switch (tipoAnimal){
                    case Cachorro:
                         iconComida1 = new ScreenBuilder().iconBuilder("Filé.png", new Rectangle(180, 240, 110, 80), new Rectangle(370, 436));// X, Y, largura, altura
                         iconComida2 = new ScreenBuilder().iconBuilder("RaçãoCachorro.png", new Rectangle(180, 240, 310, 80), new Rectangle(370, 436));
                        buttonComprar1 = new BotoesComprar().buttonComprarComida(new Rectangle(110, 40, 155, 315), d, new FileDeFrango(), labelBemVindo);
                        buttonComprar2 = new BotoesComprar().buttonComprarComida(new Rectangle(110, 40, 360, 315), d, new Pedigree(), labelBemVindo);
                         //bebidas
                         iconBebida1 = new ScreenBuilder().iconBuilder("AguaProduto.png", new Rectangle(180, 240, 110, 80), new Rectangle(370, 436));// X, Y, largura, altura
                         iconBebida2 = new ScreenBuilder().iconBuilder("cocoProduto.png", new Rectangle(180, 240, 310, 80), new Rectangle(370, 436));
                        buttonComprarBebida = new BotoesComprar().buttonComprarAgua(new Rectangle(110, 40, 155, 315), d, new Agua(), labelBemVindo);
                        buttonComprarBebida2 = new BotoesComprar().buttonComprarAgua(new Rectangle(110, 40, 360, 315), d, new AguaCoco(), labelBemVindo);
                         break;
                    case Gato:
                        iconComida1 = new ScreenBuilder().iconBuilder("sardinha.png", new Rectangle(180, 240, 110, 80), new Rectangle(370, 436));// X, Y, largura, altura
                        iconComida2 = new ScreenBuilder().iconBuilder("RacaoCat.png", new Rectangle(180, 240, 310, 80), new Rectangle(370, 436));
                        buttonComprar1 = new BotoesComprar().buttonComprarComida(new Rectangle(110, 40, 155, 315), d, new Sardinha(), labelBemVindo);
                        buttonComprar2 = new BotoesComprar().buttonComprarComida(new Rectangle(110, 40, 360, 315), d, new DelGato(), labelBemVindo);
                        //bebidas
                        iconBebida1 = new ScreenBuilder().iconBuilder("AguaProduto.png", new Rectangle(180, 240, 110, 80), new Rectangle(370, 436));// X, Y, largura, altura
                        iconBebida2 = new ScreenBuilder().iconBuilder("leite.png", new Rectangle(180, 240, 310, 80), new Rectangle(370, 436));
                        buttonComprarBebida = new BotoesComprar().buttonComprarAgua(new Rectangle(110, 40, 155, 315), d, new Agua(), labelBemVindo);
                        buttonComprarBebida2 = new BotoesComprar().buttonComprarAgua(new Rectangle(110, 40, 360, 315), d, new Leite(), labelBemVindo);
                        break;
                    default:
                        return;
                }


                //Bebidas
                //

                buttonBebidas.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        labelBemVindo.setBounds(145, 40, 410, 110);
                        Painel.add(buttonComprarBebida2);
                        Painel.add(iconBebida1);
                        Painel.add(iconBebida2);
                        Painel.remove(iconComida1);
                        Painel.remove(iconComida2);
                        Painel.remove(buttonComprar2);
                        Painel.remove(buttonComprar1);
                        Painel.add(buttonComprarBebida);
                        Painel.add(painelStore);
                        Painel.repaint();
                    }
                });
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Painel.remove(painelStore);
                        Painel.remove(iconBebida1);
                        Painel.remove(iconBebida2);
                        Painel.remove(buttonComprarBebida);
                        Painel.remove(buttonComprarBebida2);
                        Painel.remove(buttonPaisagem);
                        Painel.remove(buttonComidas);
                        Painel.remove(buttonComprar2);
                        Painel.remove(buttonComprar1);
                        Painel.remove(painelPesquisa);
                        Painel.remove(button);
                        Painel.remove(labelBemVindo);
                        Painel.remove(buttonBebidas);
                        Painel.remove(iconComida1);
                        Painel.remove(iconComida2);
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
                        labelBemVindo.setBounds(145, 40, 410, 110);
                        Painel.remove(buttonComprarBebida2);
                        Painel.remove(iconBebida1);
                        Painel.remove(iconBebida2);
                        Painel.remove(buttonComprarBebida);
                        Painel.add(iconComida2);
                        Painel.add(iconComida1);
                        Painel.add(buttonComprar2);
                        Painel.add(buttonComprar1);
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
                Painel.add(labelBemVindo);
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
                new AttBancoDados().atualizarContaPet(d);
                new AttBancoDados().atualizarContaPessoa(d);
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

        /*Timer timerSave = new Timer();
            TimerTask save = new TimerTask() {
                @Override
                public void run() {
                    new AttBancoDados().atualizarContaPet(d);
                }
        };
        timerSave.scheduleAtFixedRate(save,0L, 5 * 60 *1000L);*/
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
