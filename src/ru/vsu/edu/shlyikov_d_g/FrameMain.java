package ru.vsu.edu.shlyikov_d_g;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class FrameMain extends JFrame{
    private JPanel MainPanel;
    private JPanel SettingsPanel;
    private JPanel DrawPanel;
    private JPanel ListOfTransformationsPanel;
    private JPanel CurrentSettingsPanel;
    private JPanel ChoosingSettingsPanel;
    private JComboBox comboBox1;
    private JPanel RotatePanel;
    private JPanel ScalePanel;
    private JPanel BasicPrimeChiefPrimaryPanel;
    private myPanel painter;

    public FrameMain() {
        this.setTitle("task 2 kg");
        this.setContentPane(MainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        painter = new myPanel(10);
        DrawPanel.add(painter);
        this.pack();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    class myPanel extends JPanel
    {
        int size;
        myPanel(int size)
        {
            this.size = size;
            this.setBorder(BorderFactory.createLineBorder(Color.red));
            this.setBackground(Color.white);
        }
        public Dimension getPreferredSize()
        {
            return new Dimension(390,250);
        }
        public void doDrawing(Graphics g){
            double x,y,x1,y1;
            Graphics2D gr;

            gr = (Graphics2D) g;

            // Делаем белый фон
            Rectangle2D rect = new Rectangle2D.Double(0,0,this.getWidth(),this.getHeight());
            gr.setPaint(Color.white);
            gr.fill(rect);
            gr.draw(rect);

            // Рисуем сетку
            gr.setPaint(Color.LIGHT_GRAY);
            gr.setStroke(new BasicStroke((float) 0.2));
            for(y=0;y<=this.getWidth();y+=size){
                gr.draw(new Line2D.Double(0,y  ,this.getWidth(),y));
                gr.draw(new Line2D.Double(y,0,y,this.getHeight()));
            }
            // Рисуем оси
            gr.setPaint(Color.GREEN);
            gr.setStroke(new BasicStroke((float) 2));
            int x0 = this.getWidth() / 2 + (size - (this.getWidth() / 2) % size);
            int y0 = this.getHeight() / 2 + (size - (this.getHeight() / 2) % size);
            gr.draw(new Line2D.Double(x0,0,x0,this.getHeight()));
            gr.draw(new Line2D.Double(0,y0,this.getWidth(),y0));

            gr.setPaint(Color.BLACK);
            gr.setStroke(new BasicStroke((float) 2));
        }

        @Override
        public void paintComponent(Graphics g) {

            super.paintComponent(g);
            doDrawing(g);
        }
    }
}
