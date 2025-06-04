package Paineis;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.geom.RoundRectangle2D;
import java.awt.*;
public class PainelConfig extends JPanel{
    private int arcWidth;
    private int arcHeight;
    private Color backgroundColor;
    public PainelConfig(int arcWidth, int arcHeight, Color backgroundColor){
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        this.backgroundColor = backgroundColor;
        setLayout(new FlowLayout());
        setOpaque(false);
    }
    public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        // Suavização dos cantos (anti-aliasing)
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Desenhar o fundo arredondado
        g2.setColor(backgroundColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);

        g2.dispose();

        super.paintComponent(g);
    }
}
