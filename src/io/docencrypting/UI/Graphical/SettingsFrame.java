package io.docencrypting.UI.Graphical;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger; // обратил внимание?

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


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

        boxSettings.add(BoxCreator.makeLineBox(replace, replaceExistsFileCheck, postfix, postfixField));
        boxSettings.add(BoxCreator.makeLineBox(screenSize, widthScreenField, x, heightScreenField));
        boxSettings.add(BoxCreator.makeLineBox(save, cancel));



        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.debug("Start processing");
                try {
                    if(widthScreenField.getText().equals("") && heightScreenField.getText().equals(""))
                    {
                        setScreenSize(widthScreenField.getText(), heightScreenField.getText());
                    }
                    if(replaceExistsFileCheck.isSelected())
                    {
                        if(postfixField.getText().equals(""))
                        {

                        }
                    }
                } catch (Exception ex) {
                    logger.error("Something failed", e);
                }
                JOptionPane.showMessageDialog(null, "Success!");
                logger.debug("done");
            }
        });
//это удалить надо
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
        File filesettings = new File("settings.txt");
        //log.write("string");

    }

}
