import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.SwingWorker;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.ArrayList;

public class FractalExplorer {
    // display size in pixels
    private final int displaySize;
    private JImageDisplay displayImage;
    private FractalGenerator fractalGenerator;
    private Rectangle2D.Double complexAreaRange;

    private JComboBox<FractalGenerator> fractalSelector;

    public static void main (String[] args) {
        FractalExplorer fractalExplorer = new FractalExplorer(800);
        fractalExplorer.createAndShowGUI();
        fractalExplorer.drawFractal();
    }

    private FractalExplorer (int size) {
        this.displaySize = size;
        this.fractalGenerator = new Mandelbrot();
        this.complexAreaRange = new Rectangle2D.Double(0, 0, 0, 0);
        fractalGenerator.getInitialRange(this.complexAreaRange);
    }

    /*
        Setup GUI
     */
    public void createAndShowGUI() {
        displayImage = new JImageDisplay(displaySize, displaySize);
        displayImage.addMouseListener(new ZoomMouseListener());

        // reset button
        JButton resetButton = new JButton("Reset display");
        resetButton.addActionListener(new ResetActionListener());

        // save button
        JButton saveButton = new JButton("Save image");
        saveButton.addActionListener(new SaveActionListener());

        // comboBox
        JLabel label = new JLabel("Fractal");
        fractalSelector = new JComboBox<>();
        fractalSelector.addItem(new Mandelbrot());
        fractalSelector.addItem(new Tricorn());
        fractalSelector.addItem(new BurningShip());
        fractalSelector.addActionListener(new SelectFractalActionListener());

        // panels
        JPanel selectPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        selectPanel.add(label,  BorderLayout.CENTER);
        selectPanel.add(fractalSelector, BorderLayout.CENTER);
        bottomPanel.add(resetButton, BorderLayout.CENTER);
        bottomPanel.add(saveButton, BorderLayout.CENTER);

        JFrame frame = new JFrame("Fractal explorer");
        frame.setLayout(new BorderLayout());
        frame.add(selectPanel, BorderLayout.NORTH);
        frame.add(displayImage, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    /*
        Count num of iterations and set the color for each pixel
    */
    private void drawFractal() {
        for (int x = 0; x < displaySize; ++x) {
            for (int y = 0; y < displaySize; ++y) {
                int numOfIters = fractalGenerator.numIterations(
                        FractalGenerator.getCoord(
                                complexAreaRange.x,
                                complexAreaRange.x + complexAreaRange.width,
                                displaySize,
                                x
                        ),
                        FractalGenerator.getCoord(
                                complexAreaRange.y,
                                complexAreaRange.y + complexAreaRange.height,
                                displaySize,
                                y
                        )
                );
                int rgbColor;
                if (numOfIters == -1) {
                    rgbColor = 0;
                }
                else {
                    float hue = 0.7f + (float) numOfIters / 200f;
                    rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                }
                displayImage.drawPixel(x, y, rgbColor);
            }
        }
        displayImage.repaint();
    }

    /*
        Listener on Reset button. Reset zoom and redraw the fractal
     */
    private class ResetActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            displayImage.clearImage();
            fractalGenerator.getInitialRange(complexAreaRange);
            drawFractal();
        }
    }

    private class SelectFractalActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            fractalGenerator = (FractalGenerator) fractalSelector.getSelectedItem();
            fractalGenerator.getInitialRange(complexAreaRange);
            drawFractal();
        }
    }

    private class SaveActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("PNG Images", "png");
            fileChooser.setFileFilter(fileFilter);
            fileChooser.setAcceptAllFileFilterUsed(false);
            if (fileChooser.showSaveDialog(displayImage) == JFileChooser.APPROVE_OPTION) {
                try {
                    ImageIO.write(displayImage.image, "png", fileChooser.getSelectedFile());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(displayImage, ex.getMessage(), "Error saving fractal",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

        }
    }

    /*
        Listener on mouse click. Zoom the fractal
     */
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


    private class FractalWorker extends SwingWorker<Object, Object> {
        private int yCoordRow;
        private ArrayList<Integer> rowColors;

        FractalWorker(int y) {
            this.yCoordRow = y;
        }

        
    }
}