package io.docencrypting.UI.Graphical;

import javax.swing.*;
import java.awt.*;

public class BoxCreator {

    public static Box makeLineBox(JComponent ... components) {
        Box box = new Box(BoxLayout.X_AXIS);
        for (JComponent component : components) {
            box.add(component);
        }
        box.setMaximumSize(new Dimension(box.getMaximumSize().width, box.getMinimumSize().height));
        return box;
    }

}