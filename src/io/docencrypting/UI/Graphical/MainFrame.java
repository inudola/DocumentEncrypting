package io.docencrypting.UI.Graphical;

import io.docencrypting.Crypto.EncryptingKinds;
import io.docencrypting.UI.IDataGet;
import io.docencrypting.UI.UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements IDataGet, UserInterface {
    JButton fileInButtom = new JButton("Upload");
    JButton fileOutButtom = new JButton("Save path");
    JButton encryptButtom = new JButton("Encrypt");
    JButton decryptButtom = new JButton("Decrypt");
    JButton settingsButtom = new JButton("Settings");
    JFileChooser fileInChooser = new JFileChooser();
    JFileChooser fileOutChooser = new JFileChooser();
    JCheckBox hiddenCheckBox = new JCheckBox("Hidden");
    JComboBox typeComboBox = new JComboBox(EncryptingKinds.values());
    JTextField fileInField = new JTextField();
    JTextField fileOutField = new JTextField();
    JTextField passwordField = new JTextField();
    JLabel fileInLabel = new JLabel("File In:");
    JLabel fileOutLabel = new JLabel("File Out:");
    JLabel typeLabel = new JLabel("Type:");
    JLabel passwordLabel = new JLabel("Password:");
    JLabel hiddenLabel = new JLabel("Hidden:");

    Box box = new Box(BoxLayout.Y_AXIS);

    public MainFrame() throws HeadlessException {

        makeUI();

    }

    @Override
    public String getFileIn() {
        return null;
    }

    @Override
    public String getFileOut() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean getNeedHiddenFiles() {
        return false;
    }

    @Override
    public String getNameEncryptingAlgorithm() {
        return null;
    }

    @Override
    public void makeUI() {
        box.add(BoxCreator.makeLineBox(fileInLabel, fileInField, fileInButtom));
        box.add(BoxCreator.makeLineBox(fileOutLabel, fileOutField, fileOutButtom));
        box.add(BoxCreator.makeLineBox(typeLabel, typeComboBox));
        box.add(BoxCreator.makeLineBox(passwordLabel, passwordField));
        box.add(BoxCreator.makeLineBox(hiddenLabel, hiddenCheckBox));
        box.add(BoxCreator.makeLineBox(encryptButtom, decryptButtom, settingsButtom));

        fileInButtom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileInChooser.showOpenDialog(null);
                fileInField.setText(fileInChooser.getSelectedFile().getAbsoluteFile().toString());
            }
        });

        fileOutButtom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileOutChooser.showSaveDialog(null);
                fileOutField.setText(fileOutChooser.getSelectedFile().getAbsoluteFile().toString());
            }
        });

        centerFrame();
        setContentPane(box);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void centerFrame(){

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int)screenSize.getWidth()/2,(int)screenSize.getHeight()/2);
        setLocationRelativeTo(null);
    }
}

