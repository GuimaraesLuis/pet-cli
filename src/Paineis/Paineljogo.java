package Paineis;
import BancoDados.AttBancoDados;
import BancoDados.RemovBanco;
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
import Sprites.Animation;
import Sprites.CriarAnimation;
import Utils.ScreenBuilder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class Paineljogo {
    PainelGenerico painelConfig;
    PainelGenerico painelStore;
    Animation animation;
    private JLabel iconComida1;
    private JLabel iconComida2;
    private JLabel iconBebida1;
    private JLabel iconBebida2;
    private CriarAnimation animalPet;
    private JButton buttonComprarBebida, buttonComprarBebida2, buttonComprar1, buttonComprar2;
    private int rotacao;

    public void PanelGame(Animais animal, JFrame Painel, Dono d) {

        Paineltransparente paineltransparente = new Paineltransparente(new Color(0xD3D3D3), 0.75f);
        JLabel iconFome = new ScreenBuilder().iconBuilder("fome.png", new Rectangle(40, 40, 120, 10), new Rectangle(30, 30));
        JLabel textFome = new ScreenBuilder().textBuilder(animal.getFome() + "%", new Rectangle(100, 100, 165, -15), 18, null);
        JLabel iconSede = new ScreenBuilder().iconBuilder("sede.png", new Rectangle(40, 40, 230, 10), new Rectangle(30, 30));
        JLabel textSede = new ScreenBuilder().textBuilder(animal.getSede() + "%", new Rectangle(100, 100, 270, -15), 18, null);
        JLabel iconEnergia = new ScreenBuilder().iconBuilder("energia.png", new Rectangle(40, 40, 330, 10), new Rectangle(30, 30));
        JLabel labelBemVindo = new ScreenBuilder().textBuilder("Bem-vindo a pet-store", new Rectangle(410, 110, 145, 160), 30, Color.white);
        JLabel textEnergia = new ScreenBuilder().textBuilder(animal.getEnergia() + "%", new Rectangle(100, 100, 370, -15), 18, null);
        //Butoes
        ImageIcon imagemCompras = new ImageIcon("./imagens/cesta.png");
        JButton buttonLoja = new ScreenBuilder().buttonBilder(new Rectangle(30, 30, 20, 15), null, new Color(0, 0, 0, 0), null, imagemCompras);
        ImageIcon imagemSair = new ImageIcon("./imagens/saida.png");
        JButton buttonSair = new ScreenBuilder().buttonBilder(new Rectangle(30, 30, 540, 605), null, new Color(0, 0, 0, 0), null, imagemSair);
        ImageIcon imagemVoltar = new ImageIcon("./imagens/engrenagem.png");
        JButton buttonVoltar = new ScreenBuilder().buttonBilder(new Rectangle(30, 30, 540, 15), null, new Color(0x555554), null, imagemVoltar);
        ImageIcon imagemEgrenagem = new ImageIcon("./imagens/engrenagem.png");
        JButton buttonEngrenagem = new ScreenBuilder().buttonBilder(new Rectangle(30, 30, 540, 15), null, new Color(0, 0, 0, 0), null, imagemEgrenagem);
        //Loja
        TipoAnimal tipoAnimal = animal.getTipo();
        switch (tipoAnimal) {
            case Cachorro:
                iconComida1 = new ScreenBuilder().iconBuilder("Filé.png", new Rectangle(180, 240, 110, 80), new Rectangle(370, 436));// X, Y, largura, altura
                iconComida2 = new ScreenBuilder().iconBuilder("RaçãoCachorro.png", new Rectangle(180, 240, 310, 80), new Rectangle(370, 436));
                buttonComprar1 = new BotoesGenericos().buttonComprarComida(new Rectangle(110, 40, 155, 315), d, new FileDeFrango(), labelBemVindo);
                buttonComprar2 = new BotoesGenericos().buttonComprarComida(new Rectangle(110, 40, 360, 315), d, new Pedigree(), labelBemVindo);
                //bebidas
                iconBebida1 = new ScreenBuilder().iconBuilder("AguaProduto.png", new Rectangle(180, 240, 110, 80), new Rectangle(370, 436));// X, Y, largura, altura
                iconBebida2 = new ScreenBuilder().iconBuilder("cocoProduto.png", new Rectangle(180, 240, 310, 80), new Rectangle(370, 436));
                buttonComprarBebida = new BotoesGenericos().buttonComprarAgua(new Rectangle(110, 40, 155, 315), d, new Agua(), labelBemVindo);
                buttonComprarBebida2 = new BotoesGenericos().buttonComprarAgua(new Rectangle(110, 40, 360, 315), d, new AguaCoco(), labelBemVindo);
                animalPet = new CriarAnimation(6, "./Sprites/Cachorro/lobo 4x", 256, 256);
                animalPet.setBounds(180, 280, 350, 350);
                animalPet.setOpaque(false);
                paineltransparente.painel(Painel, paineltransparente, animalPet, animal, textEnergia, d, textFome, textSede);
                break;
            case Gato:
                iconComida1 = new ScreenBuilder().iconBuilder("sardinha.png", new Rectangle(180, 240, 110, 80), new Rectangle(370, 436));// X, Y, largura, altura
                iconComida2 = new ScreenBuilder().iconBuilder("RacaoCat.png", new Rectangle(180, 240, 310, 80), new Rectangle(370, 436));
                buttonComprar1 = new BotoesGenericos().buttonComprarComida(new Rectangle(110, 40, 155, 315), d, new Sardinha(), labelBemVindo);
                buttonComprar2 = new BotoesGenericos().buttonComprarComida(new Rectangle(110, 40, 360, 315), d, new DelGato(), labelBemVindo);
                //bebidas
                iconBebida1 = new ScreenBuilder().iconBuilder("AguaProduto.png", new Rectangle(180, 240, 110, 80), new Rectangle(370, 436));// X, Y, largura, altura
                iconBebida2 = new ScreenBuilder().iconBuilder("leite.png", new Rectangle(180, 240, 310, 80), new Rectangle(370, 436));
                buttonComprarBebida = new BotoesGenericos().buttonComprarAgua(new Rectangle(110, 40, 155, 315), d, new Agua(), labelBemVindo);
                buttonComprarBebida2 = new BotoesGenericos().buttonComprarAgua(new Rectangle(110, 40, 360, 315), d, new Leite(), labelBemVindo);
                animalPet = new CriarAnimation(8, "./Sprites/Gato/sprite_gato_2x", 320, 256);
                animalPet.setBounds(130, 300, 350, 350);
                animalPet.setOpaque(false);
                paineltransparente.painel(Painel, paineltransparente, animalPet, animal, textEnergia, d, textFome, textSede);

                break;
            default:
                return;
        }
        buttonLoja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                painelStore = new PainelGenerico(30, 30, new Color(0xD3D3D3));
                PainelGenerico painelPesquisa = new PainelGenerico(30, 30, new Color(0xB7B7B7));
                painelPesquisa.setBounds(13, 30, 50, 400);
                JButton button = new ScreenBuilder().buttonBilder(new Rectangle(30, 30, 22, 40), null, new Color(0xB7B7B7), null, new ImageIcon("./imagens/fechar.png"));
                //barra lateral
                JButton buttonBebidas = new ScreenBuilder().buttonBilder(new Rectangle(40, 40, 18, 200), null, new Color(0xB7B7B7), null, new ImageIcon("./imagens/Bebidas.png"));
                JButton buttonPaisagem = new ScreenBuilder().buttonBilder(new Rectangle(40, 40, 18, 300), null, new Color(0xB7B7B7), null, new ImageIcon("./imagens/cenario.png"));
                JButton buttonComidas = new ScreenBuilder().buttonBilder(new Rectangle(40, 40, 18, 100), null, new Color(0xB7B7B7), null, new ImageIcon("./imagens/Comidas.png"));
                //comidas

                buttonBebidas.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        labelBemVindo.setText("Bebidas");
                        labelBemVindo.setBounds(240, 40, 410, 110);
                        Painel.add(buttonComprarBebida2);
                        Painel.add(iconBebida1);
                        Painel.add(iconBebida2);
                        Painel.remove(iconComida1);
                        Painel.remove(animalPet);
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
                        Painel.add(animalPet);
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
                        labelBemVindo.setText("Comidas");
                        labelBemVindo.setBounds(240, 40, 410, 110);
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
                labelBemVindo.setText("Bem-vindo a pet-store");
                labelBemVindo.setBounds(145, 180, 410, 110);
                Painel.remove(buttonEngrenagem);
                Painel.remove(buttonLoja);
                Painel.remove(buttonEngrenagem);
                Painel.remove(iconSede);
                Painel.remove(textSede);
                Painel.remove(iconFome);
                Painel.remove(textFome);
                Painel.remove(animalPet);
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
                        //PainelConfig painelConfig1 = new PainelConfig(200, 200, Color.BLUE);
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
                painelConfig = new PainelGenerico(30, 30, new Color(0x555554));
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
        //Temporizador fome, energia, sede, coins
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

        //prova de vida
        Timer timer3 = new Timer();
        timer3.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(rotacao < 40) {
                    if (animal.getEnergia() == 0 || animal.getSede() == 0 || animal.getFome() == 0) {
                        rotacao++;
                        rotacao++;
                        rotacao++;
                        rotacao++;
                        rotacao++;
                        animalPet.mudancaAcao(4, "./Sprites/Gato/HURT_4x", 320, 256);
                    }
                }else{
                    timer3.cancel();
                    SwingUtilities.invokeLater(() -> {
                        animalPet.mudancaAcao(9, "./Sprites/Gato/DEATH_4x", 320, 256);
                    });

                    new RemovBanco().RemovPessoa(d);
                    new RemovBanco().RmemovAnimal(animal);
                    Painel.dispose();
                    System.exit(0);
                }
            }
        }, 0, 1000); // atualiza a cada 1 segundo

        Painel.add(textEnergia);
        Painel.add(iconEnergia);
        Painel.add(textSede);
        Painel.add(iconSede);
        Painel.add(iconFome);
        Painel.add(textFome);
        Painel.add(animalPet);
        Painel.add(buttonSair);
        Painel.add(buttonLoja);
        Painel.add(buttonEngrenagem);
        Painel.setVisible(true);
    }
}
