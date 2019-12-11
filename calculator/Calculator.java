package Calculator;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.*;

public class Calculator {

    public Calculator() {
        MainFrame  f = new MainFrame();
        f.setBounds(400,300,300,300);
        //ограничение размеров окна
        f.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = e.getComponent().getWidth();
                int height = e.getComponent().getHeight();
                int newWidth = width;
                int newHeight = height;
                if (width > 350) {
                    newWidth = 350;
                }
                if (width < 250) {
                    newWidth = 250;
                }
                if (height > 350) {
                    newHeight = 350;
                }
                if (height < 250) {
                    newHeight = 250;
                }
                if (newHeight != height || newWidth != width) {
                    f.setSize(newWidth, newHeight);
                }
            }
        });
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setVisible(true);

    }
}