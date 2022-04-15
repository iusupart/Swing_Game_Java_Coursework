package main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tool {

    public BufferedImage scaleImage(BufferedImage original, int W, int H){

        BufferedImage scaledImage = new BufferedImage(W, H, original.getType());
        Graphics2D graphics2D = scaledImage.createGraphics();
        graphics2D.drawImage(original, 0, 0, W, H, null);
        graphics2D.dispose();

        return scaledImage;
    }

}
