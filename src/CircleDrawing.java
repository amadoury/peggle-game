import java.awt.*;
import javax.swing.*;

public class CircleDrawing extends JComponent {
    private int radius;
    private int pointSpacing;

    public CircleDrawing(int radius, int pointSpacing) {
        this.radius = radius;
        this.pointSpacing = pointSpacing;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        for (int i = 0; i < 360; i += pointSpacing) {
            int x = centerX + (int) (radius * Math.cos(Math.toRadians(i)));
            int y = centerY + (int) (radius * Math.sin(Math.toRadians(i)));
            g.fillOval(x - 2, y - 2, 50, 50);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        CircleDrawing circle = new CircleDrawing(150, 30);
        frame.add(circle);
        frame.setVisible(true);
    }
}
