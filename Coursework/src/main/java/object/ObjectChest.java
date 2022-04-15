package object;

import main.ScreenPanel;

import javax.imageio.ImageIO;
import java.io.IOException;


public class ObjectChest extends MainObject{

    ScreenPanel screenPanel;
    public ObjectChest(ScreenPanel screenPanel) {
        this.screenPanel = screenPanel;
        name = "Chest";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
            tool.scaleImage(image, screenPanel.elementSize, screenPanel.elementSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
