package object;

import main.ScreenPanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectDoor  extends MainObject{

    ScreenPanel screenPanel;

    public ObjectDoor(ScreenPanel screenPanel) {
        this.screenPanel = screenPanel;
        name = "Door";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
            tool.scaleImage(image, screenPanel.elementSize, screenPanel.elementSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
