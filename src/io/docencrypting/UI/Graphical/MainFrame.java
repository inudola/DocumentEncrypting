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
    public void show() {

    }

    @Override
    public void makeUI() {
        add(BoxCreator.makeLineBox(fileInLabel, fileInField, fileInButtom));
        add(BoxCreator.makeLineBox(fileOutLabel, fileOutField, fileOutButtom));
        add(BoxCreator.makeLineBox(typeLabel, typeComboBox));
        add(BoxCreator.makeLineBox(passwordLabel, passwordField));
        add(BoxCreator.makeLineBox(hiddenLabel, hiddenCheckBox));
        add(BoxCreator.makeLineBox(encryptButtom, decryptButtom, settingsButtom));

        fileInButtom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileInChooser.showOpenDialog(null);
            }
        });

        fileOutButtom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileOutChooser.showSaveDialog(null);
            }
        });

//        centerFrame();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void centerFrame(){
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((screenSize.getWidth() - getWidth()) / 2);
        if (x < 0) {
            x = 0;
        }
        int y = (int) ((screenSize.getHeight() - getHeight()) / 2);
        if (y < 0) {
            y = 0;
        }
        setBounds(x, y, getWidth(), getHeight());
    }
}

