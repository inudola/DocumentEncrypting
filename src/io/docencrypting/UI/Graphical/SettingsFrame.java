package io.docencrypting.UI.Graphical;

import io.docencrypting.Config.Log;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SettingsFrame extends JFrame {

    private JCheckBox needHiddenCheck = new JCheckBox();
    private JCheckBox replaceExistsFileCheck = new JCheckBox();
    private JTextField postfixField = new JTextField("_postfix");
    private JTextField widthScreenField = new JTextField();
    private JTextField heightScreenField = new JTextField();
    private JButton save = new JButton("Save");
    private JButton cancel = new JButton("Cancel");

    private JLabel needHidden = new JLabel("Hide files");
    private JLabel replace = new JLabel("Replace exists file");
    private JLabel postfix = new JLabel("Postfix:");
    private JLabel x = new JLabel("x");
    private JLabel screenSize = new JLabel("Screen size:");

    private static Log log = new Log();

    Box boxSettings = new Box(BoxLayout.Y_AXIS);

    public SettingsFrame() {

        boxSettings.add(BoxCreator.makeLineBox(needHidden, needHiddenCheck));
        boxSettings.add(BoxCreator.makeLineBox(replace, replaceExistsFileCheck, postfix, postfixField));
        boxSettings.add(BoxCreator.makeLineBox(screenSize, widthScreenField, x, heightScreenField));
        boxSettings.add(BoxCreator.makeLineBox(save, cancel));

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //if(wi)

//                setScreenSize(widthScreenField., heightScreenField);
//                JOptionPane.showMessageDialog(null, "Success!");
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
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void centerFrame(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int)screenSize.getWidth()/2,(int)screenSize.getHeight()/2);
        setLocationRelativeTo(null);
    }

    public static void  setScreenSize(int width, int height){
        File filesettings = new File("settings.txt");
        //log.write("string");

    }

}
