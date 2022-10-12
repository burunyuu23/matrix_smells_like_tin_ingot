package ru.vsu.edu.shlyikov_d_g;

import javax.swing.*;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        winMain();
    }

    public static void winMain(){
        Locale.setDefault(Locale.ROOT);
        //SwingUtils.setDefaultFont("Microsoft Sans Serif", 18);

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() { new FrameMain().setVisible(true); }
        });
        // 0 - Plain
        // 1 - Bold
        // 2 - Italic
        // 3 - Bold+Italic
    }
}
