package block;

import main.ScreenPanel;
import main.Tool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BlockDisplay {
    ScreenPanel screenPanel;
    public Block[] block;
    public int mapScheme[][];

    public BlockDisplay(ScreenPanel screenPanel) {
        this.screenPanel = screenPanel;
        block = new Block[10];
        mapScheme = new int[screenPanel.maxWorldCol][screenPanel.maxWorldRow];
        getBlocksImage();
        loadNewMap("/maps/world01.txt");
    }

    public void getBlocksImage() {
            setup(0, "grass", false);
            setup(1, "wall", true);
            setup(2, "water", true);
            setup(3, "earth", false);
            setup(4, "tree", true);
            setup(5, "sand", false);
    }
    public void setup(int i, String imagePath, boolean collision){
        Tool tool = new Tool();

        try{
            block[i] = new Block();
            block[i].image = ImageIO.read(getClass().getResourceAsStream("/blocks/" + imagePath +".png"));
            block[i].image = tool.scaleImage(block[i].image, screenPanel.elementSize, screenPanel.elementSize);
            block[i].collision = collision;

        }catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void loadNewMap(String map){
        try {
            InputStream inputStream = getClass().getResourceAsStream(map);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            int col = 0;
            int row = 0;

            while (col < screenPanel.maxWorldCol && row < screenPanel.maxWorldRow) {

                String line = bufferedReader.readLine();

                while (col < screenPanel.maxWorldCol) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapScheme[col][row] = num;
                    col++;
                }
                if(col == screenPanel.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            bufferedReader.close();
        } catch (Exception e) {

        }
    }

    public void draw(Graphics2D graphics2D) {
//        graphics2D.drawImage(block[0].image, 0, 0, screenPanel.elementSize, screenPanel.elementSize, null);

        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < screenPanel.maxWorldCol && worldRow < screenPanel.maxWorldRow) {

            int mapNum = mapScheme[worldCol][worldRow];

            int worldX = worldCol * screenPanel.elementSize;
            int worldY = worldRow * screenPanel.elementSize;
            int screenX = worldX - screenPanel.hero.worldX + screenPanel.hero.screenX;
            int screenY= worldY - screenPanel.hero.worldY + screenPanel.hero.screenY;

            if(screenPanel.hero.screenX > screenPanel.hero.worldX) {
                screenX = worldX;
            }

            if(screenPanel.hero.screenY > screenPanel.hero.worldY) {
                screenY = worldY;
            }

            if((screenPanel.maxScreenCol * screenPanel.elementSize) - screenPanel.hero.screenX > screenPanel.worldWidth - screenPanel.hero.worldX){
                screenX = (screenPanel.maxScreenCol * screenPanel.elementSize) - (screenPanel.worldWidth - worldX);
            }

            if((screenPanel.maxScreenRow * screenPanel.elementSize) - screenPanel.hero.screenY > screenPanel.worldHeight - screenPanel.hero.worldY) {
                screenY = (screenPanel.maxScreenRow * screenPanel.elementSize) - (screenPanel.worldHeight - worldY);
            }

            if(worldX + screenPanel.elementSize> screenPanel.hero.worldX - screenPanel.hero.screenX &&
                    worldX - screenPanel.elementSize< screenPanel.hero.worldX + screenPanel.hero.screenX &&
                    worldY + screenPanel.elementSize > screenPanel.hero.worldY - screenPanel.hero.screenY &&
                    worldY - screenPanel.elementSize < screenPanel.hero.worldY + screenPanel.hero.screenY){
                graphics2D.drawImage(block[mapNum].image, screenX, screenY, null);
            } else if(screenPanel.hero.screenX > screenPanel.hero.worldX || screenPanel.hero.screenY > screenPanel.hero.worldY || (screenPanel.maxScreenCol * screenPanel.elementSize) - screenPanel.hero.screenX > screenPanel.worldWidth - screenPanel.hero.worldX || (screenPanel.maxScreenRow * screenPanel.elementSize) - screenPanel.hero.screenY > screenPanel.worldHeight - screenPanel.hero.worldY){
                graphics2D.drawImage(block[mapNum].image, screenX, screenY, null);
            }

            worldCol++;

            if (worldCol == screenPanel.maxWorldCol) {
                        worldCol = 0;
                        worldRow++;
            }
        }
    }
}
