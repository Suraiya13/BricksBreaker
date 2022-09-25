
package spl.pkg1;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ForPlayGame extends JPanel implements KeyListener, ActionListener {  //KeyListener detecting arroy key and ActionListener for moving the ball 

    private boolean play = false;
    public int score = 0;
    private int totalBricks = 40;
    private Timer timer;
    private int delay = 9;
    private int paddle = 300;
    private int ballX = 290;
    private int ballY = 350;
    private int ballXdir = randomNumberForX();
    private int ballYdir = randomNumberForY();

    private MappingForBricks map;

    public ForPlayGame() {
        map = new MappingForBricks(4, 10);

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);

        
        map.draw((Graphics2D) g, Color.WHITE);

        
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 1, 3, 592);

        
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 22));
        g.drawString("Score: " + score + "/200", 490, 30);

        
        g.setColor(Color.green);
        g.fillRect(paddle, 550, 100, 8);

        if (play == false) {
            
            g.setColor(Color.YELLOW);
            g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 25));
            g.drawString("Press Enter/Left/Right Arrow to start the game!", 90, 350);

            
            g.setColor(Color.black);
            g.fillOval(ballX, ballY, 20, 20);
        }
        else
        {
            
            g.setColor(Color.green);
            g.fillOval(ballX, ballY, 20, 20);
        }

        if (score >= 50 && score < 100) {
            
            g.setColor(Color.blue);
            g.fillOval(ballX, ballY, 21, 21);
        } 
        else if (score >= 100 && score < 150) {
            
            g.setColor(Color.yellow);
            g.fillOval(ballX, ballY, 22, 22);
        }
        else if (score >= 150) {
            
            g.setColor(Color.red);
            g.fillOval(ballX, ballY, 23, 23);
        }

        if (totalBricks <= 0) {
            play = false;
            ballXdir = 0;
            ballYdir = 0;

            
            g.setColor(Color.black);
            g.fillOval(ballX, ballY, 23, 23);

            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("You Win! Score: " + score, 200, 300);

            g.setColor(Color.YELLOW);
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press Enter to Restart..", 230, 330);

            
            g.setColor(Color.black);
            g.setFont(new Font("serif", Font.BOLD, 22));
            g.drawString("Score: " + score + "/200", 490, 30);

            
            map.draw((Graphics2D) g, Color.BLACK);

            
            g.setColor(Color.black);
            g.fillRect(paddle, 550, 100, 8);

            
            g.setColor(Color.BLACK);
            g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 25));
            g.drawString("Press Enter/Left/Right Arrow to start the game!", 90, 350);
        }

        if (ballY > 570) { 
            play = false;
            ballXdir = 0;
            ballYdir = 0;

            
            g.setColor(Color.black);
            g.fillOval(ballX, ballY, 23, 23);

            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Game over! Score: " + score, 200, 300);

            g.setColor(Color.YELLOW);
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press Enter to Restart..", 230, 330);

            
            g.setColor(Color.black);
            g.setFont(new Font("serif", Font.BOLD, 22));
            g.drawString("Score: " + score + "/200", 490, 30);

            
            map.draw((Graphics2D) g, Color.BLACK);

            
            g.setColor(Color.black);
            g.fillRect(paddle, 550, 100, 8);

          
            
            g.setColor(Color.BLACK);
            g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 25));
            g.drawString("Press Enter/Left/Right Arrow to start the game!", 90, 350);
        }
        g.dispose();
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (paddle >= 600) {
                paddle = 600;
            }
            else {
                moveRight();
            }
        }
        if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            if (paddle < 10) {
                paddle = 10;
            } 
            else {
                moveLeft();
            }
        }
        if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!play) {
                play = true;
                paddle = 310;
                ballX = 290;
                ballY = 350;
                ballXdir = randomNumberForX();
                ballYdir = randomNumberForY();
                totalBricks = 40;

                map = new MappingForBricks(4, 10);
                score = 0;

                repaint();
            }
        }
    }

    public void moveRight() {
        play = true;
        paddle += 20;
    }

    public void moveLeft() {
        play = true;
        paddle -= 20;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        timer.start();

        if (play) {
            if (new Rectangle(ballX, ballY, 20, 20).intersects(new Rectangle(paddle, 550, 100, 8))) {
                ballYdir = -ballYdir;
            }

            
            for (int i = 0; i < map.map.length; i++) {
                for (int j = 0; j < map.map[0].length; j++) {
                    if (map.map[i][j] > 0) {
                        int brickX = j * map.widthOfBrick + 80;
                        int brickY = i * map.heightOfBrick + 50;
                        int brickWidth = map.widthOfBrick;
                        int brickHeight = map.heightOfBrick;

                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballX, ballY, 20, 20);
                        Rectangle brickRect = rect;

                        if (ballRect.intersects(brickRect)) {
                            map.setBrickValue(0, i, j);
                            totalBricks--;
                            score += 5;

                            if (ballX + 19 <= brickRect.x || ballX + 1 >= brickRect.x + brickRect.width) {
                                ballXdir = -ballXdir;
                            } else {
                                ballYdir = -ballYdir;
                            }
                            break ;
                        }
                    }
                }
            }

            ballX += ballXdir;
            ballY += ballYdir;

            if (ballX < 0) {  //for move left
                ballXdir = -ballXdir;
            }
            if (ballY < 0) { //for move top
                ballYdir = -ballYdir;
            }
            if (ballX > 670) { //for move right
                ballXdir = -ballXdir;
            }
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyReleased(KeyEvent ke) {

    }

    public int randomNumberForY() {
        Random random = new Random();
        int mx = -1;
        int mn = -5;
        int randomNumber = mn + random.nextInt(mx - mn + 1);
        return randomNumber;
    }

    public int randomNumberForX() {
        Random random = new Random();
        int mx = -1;
        int mn = -3;
        int randomNumber = mn + random.nextInt(mx - mn + 1);
        return randomNumber;
    }
}
            
            
            
            
            
			

    