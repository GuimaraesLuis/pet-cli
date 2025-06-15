package Sprites;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.File;
import java.io.IOException;

public class Sprite {
    private static BufferedImage spriteSheet;
    private static String currentFile = null;
    private static int TILE_WIDTH = 0;
    private static int TILE_HEIGHT = 0;

    public static BufferedImage loadSprite(String file, int width, int height) {
        try {
            TILE_WIDTH = width;
            TILE_HEIGHT = height;
            currentFile = file;
            spriteSheet = ImageIO.read(new File("./" + file + ".png"));
            return spriteSheet;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static BufferedImage getSprite(int xIndex, String file, int width, int height) {
        if (spriteSheet == null || !file.equals(currentFile)) {
            spriteSheet = loadSprite(file, width, height);
        }

        if (spriteSheet == null) return null;

        int x = xIndex * TILE_WIDTH;

        // Correção: evitar acessar fora da imagem
        if (x + TILE_WIDTH > spriteSheet.getWidth()) {
            System.err.println("Erro: xIndex fora do limite: " + xIndex);
            return null;
        }

        try {
            return spriteSheet.getSubimage(x, 0, TILE_WIDTH, TILE_HEIGHT);
        } catch (RasterFormatException e) {
            System.err.println("Erro ao recortar sprite: " + e.getMessage());
            return null;
        }
    }
}
