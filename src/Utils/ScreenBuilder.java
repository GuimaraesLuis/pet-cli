package Utils;
import Fauna.Animais;
import Models.Rectangle;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ScreenBuilder{
    public JLabel iconBuilder(String icon, Rectangle rectangle, Rectangle imageSize){
        ImageIcon imagemfomeOriginal = new ImageIcon("./imagens/" + icon);
        Image imagemfomeRedimensionada = imagemfomeOriginal.getImage().getScaledInstance(imageSize.w, imageSize.h, Image.SCALE_SMOOTH);
        ImageIcon imagemfomeFinal = new ImageIcon(imagemfomeRedimensionada);
        JLabel labelImagem = new JLabel(imagemfomeFinal);
        labelImagem.setBounds(rectangle.x, rectangle.y, rectangle.w, rectangle.h);
        return labelImagem;
    }
    public JLabel textBuilder(String value, Rectangle rectangle, int tamanho, Color cor) {
        JLabel labelFome = new JLabel(value);
        labelFome.setBounds(rectangle.x, rectangle.y, rectangle.w, rectangle.h);
        try {
            Font baseFont = Font.createFont(Font.TRUETYPE_FONT, new File("Minecraft.ttf"));
            Font pixelFont = baseFont.deriveFont((float) tamanho);
            labelFome.setFont(pixelFont);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException("Erro ao carregar a fonte personalizada", e);
        }
        labelFome.setForeground(cor);
        return labelFome;
    }

    public void atualizar(Animais p, int number, JLabel j){
        j.setText(number+"%");
    }

    public JFrame criarPainel(int w, int h){
        JFrame p = new JFrame();
        p.setSize(w, h);
        p.setLocationRelativeTo(null);
        p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p.setLayout(null);
        p.getContentPane().setBackground(Color.getColor("0xF3F3F7"));
        return p;
    }
    public JTextField capoDeTexto(Rectangle r, Color cor){
        JTextField obj = new JTextField(1);
        obj.setBounds(r.w, r.h, r.x, r.y);
        Border bordas = BorderFactory.createLineBorder(cor);
        obj.setBorder(bordas);
        obj.setBackground(cor);
        return obj;
    }
    public JButton buttonBilder(Rectangle r, String n, Color corFundo, Color corLetra, ImageIcon i) {
        JButton b = new JButton(n, i) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int arc = 30; // Raio de arredondamento
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);

                super.paintComponent(g);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                // Borda transparente ou personalizada (se quiser)
            }

            @Override
            public void updateUI() {
                super.updateUI();
                setOpaque(false); // Permite o efeito visual
            }
        };
        b.setBounds(r.x, r.y, r.w, r.h);
        b.setBackground(corFundo);
        b.setForeground(corLetra);
        b.setFocusPainted(false);
        b.setHorizontalTextPosition(SwingConstants.CENTER);
        b.setVerticalTextPosition(SwingConstants.BOTTOM); // Ícone em cima, texto embaixo

        return b;
        }

        public String serenizarHash(HashMap<String, Integer> hashMap) throws JsonProcessingException {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(hashMap);
            return json;
        }

        public HashMap<String, Integer> desserenizarHash(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
            HashMap<String, Integer> map = mapper.readValue(json,
                    new TypeReference<HashMap<String, Integer>>() {});
            return map;
        }
    }
