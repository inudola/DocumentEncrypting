package io.docencrypting.UI.Graphical;

import io.docencrypting.UI.IDataGet;
import io.docencrypting.UI.UserInterface;
import javax.swing.*;
import java.awt.*;

public class GUIF extends JFrame implements IDataGet, UserInterface {

    JPanel mainPanel;
    JFrame theFrame;
    JTextField fileInTextField;
    JTextField fileOutTextField;
    JPasswordField passwordField;

    public GUIF(){
        showFrame();
    }

    @Override
    public void showFrame() {
        theFrame = new JFrame("DocumentEncrypting");
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderLayout layout = new BorderLayout();
        JPanel background = new JPanel(layout);
        background.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        theFrame.setSize(500,500);
        setVisible(true);
    }

    @Override
    public String getFileIn() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getFileOut() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getPassword() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean getNeedHiddenFiles() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getNameEncryptingAlgorithm() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void makeUI() {

    }

    public static void main(String[] args) {
        GUIF guif = new GUIF();

    }
}
