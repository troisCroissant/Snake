package src.main.java.configuration;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ConfigFrame extends JFrame {

    private JPanel contentPane;

    private static ConfigFrame configFrame;

    public static ConfigFrame getInstance(){
        if (configFrame == null) configFrame = new ConfigFrame();
        return configFrame;
    }

    private ConfigFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        JButton speedSetterButton = new JButton("set Speed");
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton.gridx = 0;
        gbc_btnNewButton.gridy = 0;
        contentPane.add(speedSetterButton, gbc_btnNewButton);

        JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, 1,7,4);
        speedSlider.setPaintTicks(true);
        speedSlider.setMajorTickSpacing(1);

        GridBagConstraints gbc_slider = new GridBagConstraints();
        gbc_slider.fill = GridBagConstraints.HORIZONTAL;
        gbc_slider.insets = new Insets(0, 0, 5, 0);
        gbc_slider.gridwidth = 5;
        gbc_slider.gridx = 1;
        gbc_slider.gridy = 0;
        contentPane.add(speedSlider, gbc_slider);

        setVisible(true);
    }

}