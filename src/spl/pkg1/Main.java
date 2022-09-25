
package spl.pkg1;


import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        JFrame f = new JFrame();
        ForPlayGame gamePlay = new ForPlayGame();

        f.setBounds(10, 10, 700, 600);
        f.setTitle("Brick Breaker");
        f.setResizable(false);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(gamePlay);
    }
}

