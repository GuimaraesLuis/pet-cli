import BancoDados.ConectionBase;
import BancoDados.PesquisasBanco;
import BancoDados.addAoBanco;
import Fauna.Cachorro;
import Fauna.Gatos;
import Models.Rectangle;
import Paineis.Paineljogo;
import Pessoa.Dono;
import Utils.FontePersonalizada;
import Utils.ScreenBuilder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        ConectionBase.connect();
        //Uteis
        final Gatos[] cat = {new Gatos(null)};
        final Cachorro[] dog = {new Cachorro(null)};
        final Dono[] pessoa = new Dono[1];
        //Todos os paineis
        JFrame painelLogin = new ScreenBuilder().criarPainel(600, 600);
        JFrame painelCriarPet = new ScreenBuilder().criarPainel(500, 400);
        JFrame painelJogo = new ScreenBuilder().criarPainel(600, 700);
        //Painel de Login
        JLabel iconLogo = new ScreenBuilder().iconBuilder("logo.png", new Rectangle(400, 150, 80, 90), new Rectangle(400, 400));
        JLabel textLogo = new ScreenBuilder().textBuilder("Bem-Vindo ao Nosso Sistema!", new Rectangle(290, 20, 155, 240), 18, new Color(0x555554));
        JLabel textLogin = new ScreenBuilder().textBuilder("Login:", new Rectangle(270, 20, 155, 280), 18, new Color(0x555554));
        JLabel textSenha = new ScreenBuilder().textBuilder("Senha:", new Rectangle(270, 20, 150, 320), 18, new Color(0x555554));
        JTextField campodeTextLogin = new ScreenBuilder().capoDeTexto(new Rectangle(210, 280, 190, 20), new Color(0x555554));
        campodeTextLogin.setFont(new FontePersonalizada().FonPixelada(13f));
        campodeTextLogin.setForeground(Color.lightGray);
        JTextField campodeTextSenha = new ScreenBuilder().capoDeTexto(new Rectangle(210, 320, 190, 20), new Color(0x555554));
        campodeTextSenha.setFont(new FontePersonalizada().FonPixelada(13f));
        campodeTextSenha.setForeground(Color.lightGray);
        //botao de registrar
        JButton buttoRegistrar = new ScreenBuilder().buttonBilder(new Rectangle(95, 30, 210, 360), "Registrar", new Color(0x555554), Color.lightGray, null);
        buttoRegistrar.setFont(new FontePersonalizada().FonPixelada(13f));
        buttoRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = campodeTextLogin.getText().trim();
                String senha = campodeTextSenha.getText().trim();
                if (new PesquisasBanco().vericarNome(nome) == true) {
                    if (!nome.isEmpty() && !senha.isEmpty()) {
                        if (nome.length() >= 4 && senha.length() >= 4) {
                            painelLogin.dispose();
                            painelCriarPet.setVisible(true);
                        } else {
                            textLogo.setText("Digite pelo-menos 4 letras!");
                        }

                    } else {
                        textLogo.setText("Nao deixe nenhum campo vazio!");
                    }
                }else{
                    textLogo.setText("Nome ja existe!");
                }
            }
        });
        //Botao de Login
        JButton buttonLogin = new ScreenBuilder().buttonBilder(new Rectangle(90, 30, 310, 360), "Entrar", new Color(0x555554), Color.lightGray, null);
        buttonLogin.setFont(new FontePersonalizada().FonPixelada(13f));
        buttonLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = campodeTextLogin.getText().trim();
                String senha = campodeTextSenha.getText().trim();
                if (!nome.isEmpty() && !senha.isEmpty()) {
                    if (nome.length() >= 4 && senha.length() >= 4) {
                        try {
                            pessoa[0] = new PesquisasBanco().pesquisarLogin(nome, senha);
                            if ( pessoa[0]!= null){
                                painelLogin.dispose();
                                new Paineljogo().PinelGame(pessoa[0].getPet(), painelJogo, pessoa[0]);
                            }else{
                             textLogo.setText("Conta nao encontrada!");
                            }
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    } else {
                        textLogo.setText("Digite pelo-menos 4 letras!");
                    }
                } else {
                    textLogo.setText("Nao deixe nenhum campo vazio!");
                }
            }
        });
        painelLogin.add(textLogin);
        painelLogin.add(buttoRegistrar);
        painelLogin.add(textLogo);
        painelLogin.add(iconLogo);
        painelLogin.add(textSenha);
        painelLogin.add(campodeTextSenha);
        painelLogin.add(campodeTextLogin);
        painelLogin.setVisible(true);
        painelLogin.add(buttonLogin);
        //Painel Criação
        ImageIcon cachorrroImage = new ImageIcon("./imagens/cachorro.png");
        ImageIcon gato = new ImageIcon("./imagens/gato.png");
        JLabel textoCriacao = new ScreenBuilder().textBuilder("Escolha seu pet", new Rectangle(260, 60, 110, 30), 34, new Color(0x555554));
        JLabel textoCachorro = new ScreenBuilder().textBuilder("Cao", new Rectangle(230, 60, 105, 240), 26, new Color(0x555554));
        JLabel textoGato = new ScreenBuilder().textBuilder("Gato", new Rectangle(230, 60, 323, 240), 26, new Color(0x555554));
        JLabel textNamePet = new ScreenBuilder().textBuilder("Nome:", new Rectangle(65, 30, 45, 305), 20, new Color(0x555554));
        JTextField campoTextPet = new ScreenBuilder().capoDeTexto(new Rectangle(110, 305, 320, 30), new Color(0xF4F9FE));
        campoTextPet.setFont(new FontePersonalizada().FonPixelada(13f));
        //botao Criar cachorro
        JButton buttonCachorro = new ScreenBuilder().buttonBilder(new Rectangle(180, 200, 40, 90), null, new Color(0xF4F9FE), null, cachorrroImage);
        buttonCachorro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nomePet = campoTextPet.getText();
                if (!nomePet.isEmpty() && !nomePet.isEmpty()) {
                    if (nomePet.length() >= 4 && nomePet.length() <= 16) {
                        dog[0] = new Cachorro(campoTextPet.getText());
                        pessoa[0] = new Dono(campodeTextLogin.getText(), campodeTextSenha.getText(), dog[0], new HashMap<>());
                        dog[0].setDono(pessoa[0].getName());
                        painelCriarPet.dispose();
                        try{
                            pessoa[0] = new addAoBanco().adicionarPessoa(pessoa[0]);
                            dog[0] = (Cachorro) new addAoBanco().adicionarPet(dog[0]);
                        }catch (SQLException ex){
                            ex.printStackTrace();
                        }
                        new Paineljogo().PinelGame(dog[0], painelJogo, pessoa[0]);
                    } else {
                        textLogo.setText("Digite pelo-menos 4 letras!");
                    }
                } else {
                    textLogo.setText("Nao deixe nenhum campo vazio!");
                }

            }
        });
        //botao criar gato
        JButton buttonGato = new ScreenBuilder().buttonBilder(new Rectangle(180, 200, 260, 90), null, new Color(0xF4F9FE), null, gato);
        buttonGato.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nomePet = campoTextPet.getText();
                if (!nomePet.isEmpty() && !nomePet.isEmpty()) {
                    if (nomePet.length() >= 4 && nomePet.length() <= 16) {
                        cat[0] = new Gatos(campoTextPet.getText());
                        pessoa[0] = new Dono(campodeTextLogin.getText(), campodeTextSenha.getText(), cat[0], new HashMap<>());
                        cat[0].setDono(pessoa[0].getName());
                        painelCriarPet.dispose();
                        try{
                            pessoa[0] = new addAoBanco().adicionarPessoa(pessoa[0]);
                            cat[0] = (Gatos) new addAoBanco().adicionarPet(cat[0]);
                        }catch (SQLException ex){
                            ex.printStackTrace();
                        }
                        new Paineljogo().PinelGame(cat[0], painelJogo, pessoa[0]);
                    } else {
                        textLogo.setText("Digite pelo-menos 4 letras!");
                    }
                } else {
                    textLogo.setText("Não deixe nenhum campo vazio!");
                }

            }
        });
        textNamePet.setOpaque(true);
        textNamePet.setBackground(new Color(0xF4F9FE));
        painelCriarPet.add(textNamePet);
        painelCriarPet.add(textoCachorro);
        painelCriarPet.add(textoGato);
        painelCriarPet.add(textoCriacao);
        painelCriarPet.add(buttonGato);
        painelCriarPet.add(buttonCachorro);
        painelCriarPet.add(campoTextPet);
    }
}