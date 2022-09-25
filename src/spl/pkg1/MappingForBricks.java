
package spl.pkg1;



import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MappingForBricks {
    public int map[][];
    public int widthOfBrick;
    public int heightOfBrick;

    public MappingForBricks(int row, int col) {
        map = new int[row][col];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = 1;
            }
        }
        widthOfBrick = 540 / col;
        heightOfBrick = 150 / row;
    }

    public void draw(Graphics2D g, Color c) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0) {
                    g.setColor(c);
                    g.fillRect(j * widthOfBrick + 80, i * heightOfBrick + 50, widthOfBrick, heightOfBrick);

                    g.setStroke(new BasicStroke(4));
                    g.setColor(Color.black);
                    g.drawRect(j * widthOfBrick + 80, i * heightOfBrick + 50, widthOfBrick, heightOfBrick);
                }
            }
        }
    }

    public void setBrickValue(int value, int row, int col) {
        map[row][col] = value;
    }
}