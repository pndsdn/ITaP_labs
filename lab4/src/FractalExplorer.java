
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
public class FractalExplorer {
    private int displaySize;
    private JImageDisplay displayImage;
    private FractalGenerator fractalGenerator;
    private Rectangle2D.Double complexAreaRange;

    public FractalExplorer (int size) {
        displaySize = size;
        fractalGenerator = new Mandelbrot();
        complexAreaRange = new Rectangle2D.Double(0, 0, 0, 0);
        fractalGenerator.getInitialRange(complexAreaRange);
    }

    public void creatAndShowGUI() {
        displayImage = new JImageDisplay(displaySize, displaySize);
//        displayImage.addMouseListener(new );

        JButton button = new JButton("Reset");
//        button.addActionListener(new );

        JFrame frame = new JFrame("fractal");
        frame.setLayout(new BorderLayout());
        frame.add(displayImage, BorderLayout.CENTER);
        frame.add(button, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    
}
