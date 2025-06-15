package Sprites;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class CriarAnimation extends JPanel {
    private Animation animation;

    public CriarAnimation(int frameCount, String file, int width, int height) {
        BufferedImage[] frames = new BufferedImage[frameCount];
        for (int i = 0; i < frameCount; i++) {
            frames[i] = Sprite.getSprite(i, file, width, height); // Carrega cada frame do sprite
        }

        animation = new Animation(frames, 8); // Delay padrão entre frames
        animation.start();

        // Timer para atualizar animação e repintar painel ~60 FPS
        Timer timer = new Timer(1000 / 60, e -> {
            animation.update();
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage frame = animation.getSprite();
        if (frame != null) {
            g.drawImage(frame, 0, 0, null); // Desenha o frame no painel
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(350, 350); // Tamanho padrão do painel
    }

    // Controle da animação
    public void pause() {
        animation.pause();
    }

    public void resume() {
        animation.resume();
    }

    public void stop() {
        animation.stop();
    }

    public void reverse() {
        animation.reverse();
    }

    public void setAnimationSpeed(int speed) {
        animation.setFrameDelay(speed);
    }

    public void changeAnimationFrames(BufferedImage[] newFrames) {
        animation.setFrames(newFrames);
    }

    public void mudancaAcao(int frameCount, String file, int width, int height) {
        BufferedImage[] frames = new BufferedImage[frameCount];
        for (int i = 0; i < frameCount; i++) {
            frames[i] = Sprite.getSprite(i, file, width, height);
        }
        animation.setFrames(frames); // já reseta e começa
        repaint();
    }

}
