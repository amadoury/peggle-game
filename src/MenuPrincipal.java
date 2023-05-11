import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import java.awt.event.*;

public class MenuPrincipal extends JPanel {
  private Image imgbcg;
  private JPanel ButtonPanel = new JPanel();
  private int width, height;
  public JButton playButton = createButton("PLAY");
  public JButton tutoButton = createButton("TUTORIAL");
  public JButton editButton = createButton("EDITOR");
  public JButton exitButton = createButton("EXIT");
  private Dimension dim;
  private Sound sound;

  public MenuPrincipal(Dimension dim) {
    this.dim = dim;
    try {
      imgbcg = ImageIO.read(this.getClass().getResource("ressources/bcg-Menu-principal.jpg"));
    } catch (Exception e) {
      e.printStackTrace();
    }

    Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();

    width = (int) screensize.getWidth();
    height = (int) screensize.getHeight();

    ButtonPanel.setLayout(null);
    ButtonPanel.setOpaque(false);
    ButtonPanel.setPreferredSize(new Dimension(1920, 1080));

    double reso = Toolkit.getDefaultToolkit().getScreenResolution() / 100.;

    double w = width * reso;
    double h = height * reso;

    playButton.setBounds((int) (w * 0.40), (int) (h * 0.46), (int) (420 * reso), (int) (75 * reso));
    tutoButton.setBounds((int) (w * 0.40), (int) (h * 0.56), (int) (420 * reso), (int) (75 * reso));
    editButton.setBounds((int) (w * 0.40), (int) (h * 0.66), (int) (420 * reso), (int) (75 * reso));
    exitButton.setBounds((int) (w * 0.40), (int) (h * 0.76), (int) (420 * reso), (int) (75 * reso));

    ButtonPanel.add(playButton);
    ButtonPanel.add(tutoButton);
    ButtonPanel.add(editButton);
    ButtonPanel.add(exitButton);

    ArrayList<String> paths = new ArrayList<String>();
    paths.add("ressources/audio/boutonClick.wav");
    sound = new Sound(paths);

    this.add(ButtonPanel);
    this.setVisible(true);
  }

  @Override
  public void paintComponent(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    g2d.drawImage(imgbcg, 0, 0, (int) dim.getWidth(), (int) dim.getHeight(), null, null);
  }

  private menuBouton createButton(String text) {
    menuBouton button = new menuBouton(text);

    String path_font = "ressources/font_style/font.ttf";
    InputStream is;
    Font font;

    try {
      is = this.getClass().getResourceAsStream(path_font);
      font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(57f);
      button.setFont(font);
    } catch (Exception e) {
      e.printStackTrace();
    }
    button.setFocusPainted(false);

    // 178,255,102
    // 153,255,51
    Color colorPrinciaple = new Color(153, 255, 51);
    // Color colorPrinciaple = new Color(212,226,8);
    Color colorClicked = new Color(165, 176, 3);
    Color textColor = new Color(153, 0, 153);
    button.setBackground(colorPrinciaple);
    button.setForeground(textColor);

    button.addMouseListener(new MouseAdapter() {
      public void mouseEntered(MouseEvent e) {
        button.setBackground(Color.WHITE);
        button.setColor(new Color(255, 217, 60));
        button.setForeground(Color.BLACK);
      }
    });

    button.addMouseListener(new MouseAdapter() {
      public void mouseExited(MouseEvent e) {
        button.setBackground(colorPrinciaple);
        button.setColor(colorPrinciaple);
        button.setForeground(textColor);
      }
    });

    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        button.setBackground(colorClicked);
        button.setColor(colorClicked);
        sound.setFile(0);
        sound.play();
      }
    });

    return button;
  }

  public class menuBouton extends JButton {

    private Color color;

    public menuBouton() {
      setBorder(new EmptyBorder(10, 10, 10, 10));
      setContentAreaFilled(false);
      setCursor(new Cursor(Cursor.HAND_CURSOR));
      color = new Color(153, 255, 51);
    }

    public menuBouton(String s) {
      this();
      setText(s);
    }

    protected void paintComponent(Graphics g) {
      Graphics2D g2 = (Graphics2D) g.create();
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g2.setColor(getForeground());
      int width = getWidth() - 1;
      int height = getHeight() - 1;
      g2.draw(new RoundRectangle2D.Double(0, 0, width, height, height, height));
      g2.dispose();
      g.setColor(color);
      g.fillRoundRect(0, 0, getSize().width, getSize().height, 100, 100);
      super.paintComponent(g);
    }

    public void setColor(Color color) {
      this.color = color;
    }
  }
}
