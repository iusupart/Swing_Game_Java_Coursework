package object;

import main.ScreenPanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectKey extends MainObject{

    ScreenPanel screenPanel;
    public ObjectKey(ScreenPanel screenPanel) {
        this.screenPanel = screenPanel;
        name = "Key";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
            tool.scaleImage(image, screenPanel.elementSize, screenPanel.elementSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
