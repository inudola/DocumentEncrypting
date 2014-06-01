package io.docencrypting.UI.Graphical;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.NumberFormat;
import java.util.Scanner;

/**
 *  Singleton Class that contains common application settings
 */


public class SettingsFrame extends JFrame {

    private JCheckBox replaceExistsFileCheck = new JCheckBox();
    private JTextField postfixField = new JTextField("_postfix");
    private JTextField widthScreenField = new JTextField();
    private JTextField heightScreenField = new JTextField();
    private JButton save = new JButton("Save");
    private JButton cancel = new JButton("Cancel");

    private JLabel replace = new JLabel("Replace exists file");
    private JLabel postfix = new JLabel("Postfix:");
    private JLabel x = new JLabel("x");
    private JLabel screenSize = new JLabel("Screen size:");

    //private static Log log = new Log();
    private static final Logger logger = LogManager.getLogger(SettingsFrame.class);

    Box boxSettings = new Box(BoxLayout.Y_AXIS);

    public SettingsFrame() {

        widthScreenField = new JFormattedTextField(NumberFormat.getInstance());
        heightScreenField = new JFormattedTextField(NumberFormat.getInstance());
        boxSettings.add(BoxCreator.makeLineBox(screenSize, widthScreenField, x, heightScreenField));
        boxSettings.add(BoxCreator.makeLineBox(save, cancel));

        String arSettings = getSettings();
        String[] arraySettings = arSettings.split("\n");
        String width = arraySettings[0];
        String height = arraySettings[1];
        widthScreenField.setText(width);
        heightScreenField.setText(height);

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.debug("Centralization of the dialog box");
                try {
                    if (widthScreenField.getText() != "" && heightScreenField.getText() != "") {
                        setScreenSize(widthScreenField.getText(), heightScreenField.getText());
                    }
                } catch (Exception ex) {
                    logger.error("Something failed", e);
                }
                JOptionPane.showMessageDialog(null, "Success!");
                logger.debug("Done");
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });


        centerFrame();
        setContentPane(boxSettings);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public void centerFrame(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int)screenSize.getWidth()/2,(int)screenSize.getHeight()/2);
        setLocationRelativeTo(null);
    }

    public static void  setScreenSize(String width, String height){
        FileWriter writeFile = null;
        try {
            File sFile = new File("settings.txt");
            writeFile = new FileWriter(sFile);
            writeFile.write(width + "\n");
            writeFile.write(height + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(writeFile != null) {
                try {
                    writeFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getSettings()
    {
        File file = new File("settings.txt");
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
            try {
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {
                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

}
