package Utils;
import java.awt.*;
import java.io.File;
import java.io.IOException;
public class FontePersonalizada {
    public Font FonPixelada(float tamanho) {
        try {
            Font fontePersonalizada = Font.createFont(Font.TRUETYPE_FONT, new File("E:\\Projetos\\pet-cli\\Minecraft.ttf"));
            fontePersonalizada = fontePersonalizada.deriveFont(tamanho); // tamanho da fonte
            return fontePersonalizada;
        } catch (FontFormatException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }
}

