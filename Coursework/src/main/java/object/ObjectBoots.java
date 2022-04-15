package object;

import main.ScreenPanel;

import javax.imageio.ImageIO;
import java.io.IOException;


public class ObjectBoots  extends MainObject{
    ScreenPanel screenPanel;
    public ObjectBoots(ScreenPanel screenPanel) {

        this.screenPanel = screenPanel;
        name = "Boots";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
            tool.scaleImage(image, screenPanel.elementSize, screenPanel.elementSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
