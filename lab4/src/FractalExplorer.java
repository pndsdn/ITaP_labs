
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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

    public void createAndShowGUI() {
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

    private void drawFractal() {
        for (int x = 0; x < displaySize; ++x) {
            for (int y = 0; y < displaySize; ++y) {
                double xCoord = FractalGenerator.getCoord(complexAreaRange.x,
                        complexAreaRange.x + complexAreaRange.width,
                        displaySize,
                        x);
                double yCoord = FractalGenerator.getCoord(complexAreaRange.y,
                        complexAreaRange.y + complexAreaRange.height,
                        displaySize,
                        y);

                int numOfIters = fractalGenerator.numIterations(xCoord, yCoord);

                if (numOfIters == -1) {
                    displayImage.drawPixel(x, y, 0);
                }
                else {
                    float hue = 0.7f + (float)numOfIters / 200f;
                    displayImage.drawPixel(x, y, Color.HSBtoRGB(hue, 1f, 1f));
                }
            }
        }
        displayImage.repaint();
    }

    private class ResetActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            displayImage.clearImage();
            fractalGenerator.getInitialRange(complexAreaRange);
            drawFractal();
        }
    }

    private class ZoomMouseListener extends MouseAdapter implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            double xCoord = FractalGenerator.getCoord(complexAreaRange.x,
                    complexAreaRange.x + complexAreaRange.width,
                    displaySize,
                    e.getX());

            double yCoord = FractalGenerator.getCoord(complexAreaRange.y,
                    complexAreaRange.y + complexAreaRange.height,
                    displaySize,
                    e.getY());

            fractalGenerator.recenterAndZoomRange(complexAreaRange, xCoord, yCoord, 0.5);
            drawFractal();
        }
    }
}
