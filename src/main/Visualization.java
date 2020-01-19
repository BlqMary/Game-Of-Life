package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class Visualization implements ActionListener {
    private final SettingsMenu settingsMenu;
    private final Simulation simulation;
    private final Grid grid;
    private final GridRenderer gridRenderer;
    private final Timer timer;
    JButton startButton;
    JButton resetButton;
    final JFrame frame = new JFrame("Game of Life");
    private JLabel header;

    Visualization(SettingsMenu settingsMenu, Simulation simulation, int delay) {

        this.simulation = simulation;
        this.grid = simulation.getGrid();
        this.settingsMenu = settingsMenu;
        timer = new Timer(delay, this);

        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.startButton = new JButton("Start");
        startButton.setBounds(frame.getWidth()/2 - 160,20,80,20);
        frame.add(startButton);

        this.resetButton = new JButton("Restart");
        resetButton.setBounds(frame.getWidth()/2+80,20,80,20);
        frame.add(resetButton);

        this.header = new JLabel("Narysuj co Ci przyjdzie do głowy i kliknij start! ");
        this.header.setFont(new Font(header.getFont().getName(), Font.BOLD,15));
        header.setBounds(frame.getWidth()/2 - 180, 50,400,20);
        frame.add(header);

        gridRenderer = new GridRenderer(this.simulation,this.grid, this);
        gridRenderer.setSize(new Dimension(1, 1));
        gridRenderer.addMouseListener(listener);
        frame.add(gridRenderer);


        startButton.addActionListener(evt -> {
            timer.start();
            gridRenderer.removeMouseListener(listener);
            startButton.setEnabled(false);
            header.setVisible(false);
        });

        resetButton.addActionListener(evt -> {
            this.settingsMenu.setVisible(true);
            JComponent component = (JComponent) evt.getSource();
            Window settings = SwingUtilities.getWindowAncestor(component);
            settings.dispose();
        });

    }

    MouseListener listener = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            int x = mouseEvent.getX() / gridRenderer.widthScale;
            int y = mouseEvent.getY() / gridRenderer.heightScale;
            grid.changeStateOfCell(x,y);
            gridRenderer.repaint();
        }
    };

    @Override
    public void actionPerformed(ActionEvent e) {
            if(simulation.isRunning()){
                simulation.generation();
                gridRenderer.repaint();
            }
            else{
                timer.stop();
                startButton.setEnabled(true);
                header.setVisible(true);
                gridRenderer.addMouseListener(listener);
            }
    }
}