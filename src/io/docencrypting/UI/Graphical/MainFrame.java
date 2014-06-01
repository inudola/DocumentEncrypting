package io.docencrypting.UI.Graphical;

import io.docencrypting.Controllers.EncryptingController;
import io.docencrypting.Crypto.DialogHandler;
import io.docencrypting.Crypto.EncryptingKinds;
import io.docencrypting.UI.IDataGet;
import io.docencrypting.UI.UserInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainFrame extends JFrame implements IDataGet, UserInterface {

    JButton fileInButton = new JButton("Upload");
    JButton fileOutButton = new JButton("Save path");
    JButton encryptButton = new JButton("Encrypt");
    JButton decryptButton = new JButton("Decrypt");
    JButton settingsButton = new JButton("Settings");
    JFileChooser fileInChooser = new JFileChooser();
    JFileChooser fileOutChooser = new JFileChooser();
    JCheckBox hiddenCheckBox = new JCheckBox("Hidden");
    JComboBox<EncryptingKinds> typeComboBox;
    JTextField fileInField = new JTextField();
    JTextField fileOutField = new JTextField();
    JTextField passwordField = new JTextField();
    JLabel fileInLabel = new JLabel("File In:");
    JLabel fileOutLabel = new JLabel("File Out:");
    JLabel typeLabel = new JLabel("Type:");
    JLabel passwordLabel = new JLabel("Password:");
    JLabel hiddenLabel = new JLabel("Hidden:");
    private static final Logger logger = LogManager.getLogger(SettingsFrame.class);
    Box box = new Box(BoxLayout.Y_AXIS);

    EncryptingController encryptingController;

    DialogHandler handler;

    private void createDialogHandler() {
        handler = new DialogHandler() {
            @Override
            public boolean show(String message, String title) {
                if (!getNeedChange().equals(Type.NOT_ANSWER)) {
                    return isNeedChange();
                }
                int answer = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
                this.setNeedChange(answer == JOptionPane.YES_OPTION ? DialogHandler.Type.YES : Type.NO);
                return isNeedChange();
            }
        };
    }

    public MainFrame() throws HeadlessException {
        encryptingController = new EncryptingController();
        encryptingController.setView(this);
        makeUI();

    }

    @Override
    public File[] getFilesIn() {
        return fileInChooser.getSelectedFiles();
    }

    @Override
    public File getFileOut() {
        return fileOutChooser.getSelectedFile();
    }

    @Override
    public String getPassword() {
        return passwordField.getText();
    }

    @Override
    public boolean getNeedHiddenFiles() {
        return hiddenCheckBox.isSelected();
    }

    @Override
    public DialogHandler getDialogHandler() {
        return handler;
    }

    @Override
    public String getNameEncryptingAlgorithm() {
        return ((EncryptingKinds) typeComboBox.getSelectedItem()).name();
    }

    @Override
    public void makeUI() {

        typeComboBox = new JComboBox<>(encryptingController.getAvailableEncrypting());

        fileInChooser.setMultiSelectionEnabled(true);
        fileInChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileOutChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileOutChooser.setMultiSelectionEnabled(false);

        box.add(BoxCreator.makeLineBox(fileInLabel, fileInField, fileInButton));
        box.add(BoxCreator.makeLineBox(fileOutLabel, fileOutField, fileOutButton));
        box.add(BoxCreator.makeLineBox(typeLabel, typeComboBox));
        box.add(BoxCreator.makeLineBox(passwordLabel, passwordField));
        box.add(BoxCreator.makeLineBox(hiddenLabel, hiddenCheckBox));
        box.add(BoxCreator.makeLineBox(encryptButton, decryptButton, settingsButton));


        fileInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileInChooser.setFileHidingEnabled(!hiddenCheckBox.isSelected());
                int choose = fileInChooser.showOpenDialog(null);
                if (choose == JFileChooser.APPROVE_OPTION) {
                    fileInField.setText(fileInChooser.getSelectedFile().getAbsoluteFile().toString());
                }
            }
        });

        fileOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choose = fileOutChooser.showSaveDialog(null);
                if (choose == JFileChooser.APPROVE_OPTION) {
                    fileOutField.setText(fileOutChooser.getSelectedFile().getAbsoluteFile().toString());
                }
            }
        });

        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.debug("Encrypting file");
                try {
                    createDialogHandler();
                    encryptingController.encryptWith();
                    logger.debug("Encrypting success");
                } catch (IOException ex) {
                    logger.error("Encrypting failed");
                    JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
                }
                logger.debug("Encrypting done");
            }
        });



        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.debug("Dencrypting file");
                try {
                    encryptingController.decrypt();
                    logger.debug("Dencrypting success");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
                    logger.error("Dencrypting failed");
                }
                logger.debug("Dencrypting done");
            }
        });

        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingsFrame settingsFrame = new SettingsFrame();
            }
        });


        typeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(typeComboBox.getSelectedItem().equals(EncryptingKinds.Morse))
                {
                    passwordField.setVisible(false);
                    passwordLabel.setVisible(false);
                }
                else
                {
                    passwordField.setVisible(true);
                    passwordLabel.setVisible(true);
                }
            }
        });


        centerFrame();
        setContentPane(box);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public String getFileInPath(){
        File[] arrayFile = fileInChooser.getSelectedFiles();
        if(arrayFile.length > 1)
        {
            return arrayFile[0].getParent();
        }
        return arrayFile[0].getAbsolutePath();
    }

    public void centerFrame(){

        String arSettings = getSettings();
        String[] arraySettings = arSettings.split("\n");
        String width = arraySettings[0];
        String height = arraySettings[1];

        if(width.equals("0") || height.equals("0"))
        {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            setSize((int)screenSize.getWidth()/2,(int)screenSize.getHeight()/2);
        }else
        {
            setSize(Integer.parseInt(width), Integer.parseInt(height));
        }

        setLocationRelativeTo(null);
    }

    public String getSettings()
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

