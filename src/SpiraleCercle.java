import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SpiraleCercle extends JPanel {

    public void paint(Graphics g) {
        g.setColor(Color.WHITE);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        double radius = 60;
        double deltaTheta = Math.PI / 3;
        double deltaRadius = 15;

        for (double theta = 0; radius <= 500; theta += deltaTheta) {
            double x = radius * Math.cos(theta);
            double y = radius * Math.sin(theta);
            g.fillOval((int) (centerX + x), (int) (centerY + y), 25, 25);
            radius += deltaRadius;
            // deltaTheta -= deltaTheta * 0.04;
            // deltaTheta = Math.PI / 16 / radius;
            deltaTheta = Math.toDegrees(20 * 360 / (2 * Math.PI * radius) / (radius + 500));
        }
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.getContentPane().add(new SpiraleCercle());
        f.setSize(500, 500);
        f.getContentPane().setBackground(Color.BLACK);
        f.setVisible(true);
    }
}
