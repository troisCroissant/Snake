package src.main.java.configuration;


import src.main.java.frame.SnakeFrame;
import src.main.java.logic.Game;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ConfigFrame extends JFrame {

    Game game = Game.getInstance();
    private JPanel contentPane;
    private static ConfigFrame configFrame;
    private Configs configs = Configs.getInstance();

    public static ConfigFrame getInstance() throws InterruptedException {
        if (configFrame == null) configFrame = new ConfigFrame();
        return configFrame;
    }

    private ConfigFrame() throws InterruptedException {
        setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                configFrame.dispose();
                configFrame = null;
                SnakeFrame.getInstance().setEnabled(true);
                SnakeFrame.getInstance().requestFocus();

            }
        });
        setBounds(100, 100, 517, 640);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
        gbl_contentPane.rowHeights = new int[]{0, 0, 0};
        gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl_contentPane);

        setSpeed();

        setVisible(true);
    }

    private void setSpeed() {
        JButton speedSetterButton = new JButton("set Speed");
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton.gridx = 0;
        gbc_btnNewButton.gridy = 0;
        contentPane.add(speedSetterButton, gbc_btnNewButton);

        JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, 1,10,5);
        speedSlider.setPaintTicks(true);
        speedSlider.setMajorTickSpacing(1);
        speedSlider.setPaintLabels(true);

        GridBagConstraints gbc_slider = new GridBagConstraints();
        gbc_slider.fill = GridBagConstraints.HORIZONTAL;
        gbc_slider.insets = new Insets(0, 0, 5, 0);
        gbc_slider.gridwidth = 5;
        gbc_slider.gridx = 1;
        gbc_slider.gridy = 0;
        contentPane.add(speedSlider, gbc_slider);

        speedSetterButton.addActionListener( l ->{
            int newSpeedValue = 275-speedSlider.getValue()*25;
            configs.setSpeed(newSpeedValue);
        });
    }

}