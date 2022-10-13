package ru.vsu.edu.shlyikov_d_g;

import ru.vsu.edu.shlyikov_d_g.figure.Figure;
import ru.vsu.edu.shlyikov_d_g.figure.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class FrameMain extends JFrame{
    private JPanel MainPanel;
    private JPanel SettingsPanel;
    private JPanel DrawPanel;
    private JPanel ListOfTransformationsPanel;
    private JPanel CurrentSettingsPanel;
    private JPanel ChoosingSettingsPanel;
    private JComboBox settingsBox;
    private JPanel RotatePanel;
    private JPanel ScalePanel;
    private JPanel BasicPrimeChiefPrimaryPanel;
    private JPanel FigurePanel;
    private JComboBox chooseFigureBox;
    private JButton drawFigure;
    private JTextField scaleXField;
    private JButton SCALEXButton;
    private JLabel scaleXText;
    private JButton SCALEButton;
    private JButton SCALEYButton;
    private JTextField scaleField;
    private JLabel scaleText;
    private JTextField scaleYField;
    private JLabel scaleYText;
    private JLabel settingsText;
    private JLabel chooseFigureText;
    private JButton chooseCurrentSettingButton;
    private JLabel historyTransformationText;
    private JPanel historyTransformationsPanel;
    private JTextField degreeRotateInput;
    private JButton degreeRotateButton;
    private JButton radiansRotateButton;
    private JTextField radiansRotateInput;
    private JPanel freeFormPositionsPanel;
    private JTextField inputPointsField;
    private JButton inputPointsButton;
    private JLabel errorPointsText;
    private JComboBox historyTransformationComboBox;
    private JComboBox pointsComboBox;
    private JButton deletePointButton;
    private JPanel SetCenterPanel;
    private JTextField setCenterField;
    private JButton SETButton;
    private JPanel InfoPanel;
    private JLabel currentCenterPointText;
    private JLabel clickedPointText;
    private JButton setClickedCenterButton;
    private JPanel figuresConfigurations;
    private JLabel inputPointsText;
    private JButton chooseFigureButton;
    private JPanel TranslatePanel;
    private JPanel ShiftPanel;
    private JButton shiftVertRadButton;
    private JTextField shiftVertRadField;
    private JTextField shiftHorRadField;
    private JTextField shiftVertDegrField;
    private JButton shiftHorRadButton;
    private JButton shiftVertDegrButton;
    private JButton shiftHorDegrButton;
    private JButton shiftRadButton;
    private JButton shiftDegrButton;
    private JTextField shiftHorDegrField;
    private JTextField shiftRadField;
    private JTextField shiftDegrField;
    private JTextField translateXField;
    private JButton TRANSLATEXButton;
    private JButton TRANSLATEYButton;
    private JButton TRANSLATEButton;
    private JTextField translateYField;
    private JTextField translateField;
    private JButton deleteButton;

    private myPanel painter;

    private Figure figure;

    private int startX;
    private int startY;
    private int w = 8;
    private int h = 8;
    private int size = 10;
    private int clickedX = -10;
    private int clickedY = -10;
    private List<Point> pointList = new ArrayList<>();

    private List<Transform> transformList = new ArrayList<>();

    private void setFigure(Figure figure){
        this.figure = figure;
    }

    public static void goToLayout(JPanel jf, String name) {
        CardLayout cardLayout = (CardLayout) jf.getLayout();
        cardLayout.show(jf, name);
    }

    private void setClickedPoint(int x, int y){
        this.clickedX = x;
        this.clickedY = y;
    }

    public FrameMain() {
        this.setTitle("task 2 kg");
        this.setContentPane(MainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        painter = new myPanel(size);
        DrawPanel.add(painter);
        DrawPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setClickedPoint(e.getX(), e.getY());
                updateClickedPointText();
                repaint();
            }
        });
        this.pack();

        int x0 = this.getWidth() / 2 + (size - (this.getWidth() / 2) % size)  ;
        int y0 = this.getHeight() / 2 + (size - (this.getHeight() / 2) % size);
        setStartCoords(x0, y0);

        updateCenterPointText();

        CurrentSettingsPanel.add(FigurePanel, "Draw figures");
        CurrentSettingsPanel.add(ScalePanel, "Scale");
        CurrentSettingsPanel.add(RotatePanel, "Rotate");
        CurrentSettingsPanel.add(TranslatePanel, "Translate");
        CurrentSettingsPanel.add(ShiftPanel, "Shift");
        CurrentSettingsPanel.add(SetCenterPanel,"Set center");

        goToLayout(CurrentSettingsPanel, "Draw figures");

        settingsBox.insertItemAt("Draw figures", 0);
        settingsBox.insertItemAt("Scale", 1);
        settingsBox.insertItemAt("Rotate", 2);
        settingsBox.insertItemAt("Translate", 3);
        settingsBox.insertItemAt("Shift", 4);
        settingsBox.insertItemAt("Set center", 5);
        settingsBox.setSelectedIndex(0);

        chooseFigureBox.insertItemAt("Free form", 0);
        chooseFigureBox.insertItemAt("Triangle", 1);
        chooseFigureBox.insertItemAt("Quadrilateral", 2);

        chooseCurrentSettingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String current = Objects.requireNonNull(settingsBox.getSelectedItem()).toString();
                goToLayout(CurrentSettingsPanel, current);
                switch (current) {
                    case "Draw figures" -> settingsBox.setSelectedIndex(0);
                    case "Scale" -> settingsBox.setSelectedIndex(1);
                    case "Rotate" -> settingsBox.setSelectedIndex(2);
                    case "Translate" -> settingsBox.setSelectedIndex(3);
                    case "Shift" -> settingsBox.setSelectedIndex(4);
                    case "Set center" -> settingsBox.setSelectedIndex(5);
                }
            }
        });

        deletePointButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int index = pointsComboBox.getSelectedIndex();
                pointList.remove(index);
                pointsComboBox.removeItemAt(index);
                updateInputPointText();
            }
        });

        chooseFigureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                pointList.clear();
                pointsComboBox.removeAllItems();
                transformList.clear();
                historyTransformationComboBox.removeAllItems();
                updateInputPointText();
            }
        });

        inputPointsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Point p = Point.parsePoint(inputPointsField.getText());
                pointList.add(p);
                inputPointsField.setText("");
                pointsComboBox.insertItemAt(("(" + p.getX() + ", " + p.getY() + ")"),pointList.size() - 1);
                updateInputPointText();
            }
            //(0,100),(20,100)
        });

        drawFigure.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String current = Objects.requireNonNull(chooseFigureBox.getSelectedItem()).toString();
                        if (chooseFigureBox.getSelectedIndex() != 0 && pointList.size() != 2) {
                            errorPointsText.setText("Input 2 points, not a " + pointList.size() + " points!");
                            return;
                        }
                    Figure figure = new Figure(pointList, startX, startY, current);
                    setFigure(figure);
                    System.out.println(figure.getCoords().toString());
                repaint();
            }
        });

        SCALEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                double scale = Double.parseDouble(scaleField.getText());
                Transform transform = new Transform(figure);
                figure = transform.scale(scale);
                transformList.add(transform);
                historyTransformationComboBox.insertItemAt(transform, transformList.size() - 1);
                repaint();
            }
        });

        SCALEXButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                double scale = Double.parseDouble(scaleXField.getText());
                Transform transform = new Transform(figure);
                figure = transform.scaleX(scale);
                transformList.add(transform);
                historyTransformationComboBox.insertItemAt(transform, transformList.size() - 1);
                repaint();
            }
        });

        SCALEYButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                double scale = Double.parseDouble(scaleYField.getText());
                Transform transform = new Transform(figure);
                figure = transform.scaleY(scale);
                transformList.add(transform);
                historyTransformationComboBox.insertItemAt(transform, transformList.size() - 1);
                repaint();
            }
        });

        degreeRotateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                double degree = Double.parseDouble(degreeRotateInput.getText());
                Transform transform = new Transform(figure);
                figure = transform.rotateDegrees(degree);
                transformList.add(transform);
                historyTransformationComboBox.insertItemAt(transform, transformList.size() - 1);
                repaint();
            }
        });

        TRANSLATEXButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int x = Integer.parseInt(translateXField.getText());
                Transform transform = new Transform(figure);
                figure = transform.translateX(x);
                transformList.add(transform);
                historyTransformationComboBox.insertItemAt(transform, transformList.size() - 1);
                repaint();
            }
        });

        TRANSLATEYButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int y = Integer.parseInt(translateYField.getText());
                Transform transform = new Transform(figure);
                figure = transform.translateY(y);
                transformList.add(transform);
                historyTransformationComboBox.insertItemAt(transform, transformList.size() - 1);
                repaint();
            }
        });

        TRANSLATEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int x,y;
                x = y = Integer.parseInt(translateField.getText());
                Transform transform = new Transform(figure);
                figure = transform.translate(x, y);
                transformList.add(transform);
                historyTransformationComboBox.insertItemAt(transform, transformList.size() - 1);
                repaint();
            }
        });

        shiftDegrButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                double alpha, beta;
                alpha = beta = Double.parseDouble(shiftDegrField.getText());
                Transform transform = new Transform(figure);
                figure = transform.shiftDegrees(alpha, beta);
                transformList.add(transform);
                historyTransformationComboBox.insertItemAt(transform, transformList.size() - 1);
                repaint();
            }
        });

        shiftRadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                double alpha, beta;
                alpha = beta = Double.parseDouble(shiftRadField.getText());
                Transform transform = new Transform(figure);
                figure = transform.shiftRadians(alpha, beta);
                transformList.add(transform);
                historyTransformationComboBox.insertItemAt(transform, transformList.size() - 1);
                repaint();
            }
        });

        shiftVertDegrButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                double alpha = Double.parseDouble(shiftVertDegrField.getText());
                Transform transform = new Transform(figure);
                figure = transform.vertShiftDegrees(alpha);
                transformList.add(transform);
                historyTransformationComboBox.insertItemAt(transform, transformList.size() - 1);
                repaint();
            }
        });

        shiftHorDegrButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                double alpha = Double.parseDouble(shiftHorDegrField.getText());
                Transform transform = new Transform(figure);
                figure = transform.horShiftDegrees(alpha);
                transformList.add(transform);
                historyTransformationComboBox.insertItemAt(transform, transformList.size() - 1);
                repaint();
            }
        });

        shiftVertRadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                double alpha = Double.parseDouble(shiftVertRadField.getText());
                Transform transform = new Transform(figure);
                figure = transform.vertShiftRadians(alpha);
                transformList.add(transform);
                historyTransformationComboBox.insertItemAt(transform, transformList.size() - 1);
                repaint();
            }
        });

        shiftHorRadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                double alpha = Double.parseDouble(shiftHorRadField.getText());
                Transform transform = new Transform(figure);
                figure = transform.horShiftRadians(alpha);
                transformList.add(transform);
                historyTransformationComboBox.insertItemAt(transform, transformList.size() - 1);
                repaint();
            }
        });

        radiansRotateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                double radians = Double.parseDouble(radiansRotateInput.getText());
                Transform transform = new Transform(figure);
                figure = transform.rotateRadians(radians);
                transformList.add(transform);
                historyTransformationComboBox.insertItemAt(transform, transformList.size() - 1);
                repaint();
            }
        });

        SETButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Point p = Point.parsePoint(setCenterField.getText());
                Transform transform = new Transform("Translate transform pos", startX, startY, p.getX(), p.getY());
                transformList.add(transform);
                historyTransformationComboBox.insertItemAt(transform, transformList.size() - 1);
                setStartCoords(p);
                updateCenterPointText();
                repaint();
            }
        });

        setClickedCenterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Point p = new Point(clickedX, clickedY);
                Transform transform = new Transform("Translate transform pos", startX, startY, p.getX(), p.getY());
                transformList.add(transform);
                historyTransformationComboBox.insertItemAt(transform, transformList.size() - 1);
                setStartCoords(p);
                repaint();
            }
        });
    }

    private void updateInputPointText(){
        String current = Objects.requireNonNull(chooseFigureBox.getSelectedItem()).toString();
        switch (current.toLowerCase(Locale.ROOT)) {
            case "free form" -> {
                if (pointList.size() == 0) {

                } else {
                    inputPointsText.setText("You can input other points in (x, y) format");
                }
            }
            case "triangle", "quadrilateral" -> {
                if (pointList.size() == 0){
                    inputPointsText.setText("Input left start point and height+weight: (x,y),(h,w)");
                }
                else if (pointList.size() == 1) {
                    inputPointsText.setText("Now input height+weight: (h,w)");
                }
                else{
                    inputPointsText.setText("You input all points. Now - draw!");
                }
            }
        }
    }

    private void updateCenterPointText(){
        currentCenterPointText.setText("Current center point - (" + this.startX + ", " + this.startY + ").");
    }

    private void updateClickedPointText(){
        clickedPointText.setText("Clicked point - (" + this.clickedX + ", " + this.clickedY + ").");
    }

    private void setStartCoords(int startX, int startY){
        this.startX = startX;
        this.startY = startY;
    }

    private void setStartCoords(Point p){
        this.startX = p.getX();
        this.startY = p.getY();
    }

    private int getW(){
        return this.w;
    }

    private int getH(){
        return this.h;
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
        public void doDrawing(Graphics g) {
            double y;
            Graphics2D gr;

            gr = (Graphics2D) g;

            // Делаем белый фон
            Rectangle2D rect = new Rectangle2D.Double(0, 0, this.getWidth(), this.getHeight());
            gr.setPaint(Color.white);
            gr.fill(rect);
            gr.draw(rect);

            // Рисуем сетку
            gr.setPaint(Color.LIGHT_GRAY);
            gr.setStroke(new BasicStroke((float) 0.2));
            for (y = 0; y <= this.getWidth(); y += size) {
                gr.draw(new Line2D.Double(0, y, this.getWidth(), y));
                gr.draw(new Line2D.Double(y, 0, y, this.getHeight()));
            }
            // Рисуем оси
            gr.setPaint(Color.GREEN);
            gr.setStroke(new BasicStroke((float) 2));
            gr.draw(new Line2D.Double(startX, 0, startX, this.getHeight()));
            gr.draw(new Line2D.Double(0, startY, this.getWidth(), startY));

            int w, h;
            w = getW();
            h = getH();
            gr.setPaint(Color.RED);
            gr.fillOval(startX - w / 2, startY - h / 2, w, h);

            if (startX != clickedX && startY != clickedY) {
                gr.setPaint(Color.BLUE);
                gr.fillOval(clickedX, clickedY, w, h);
            }

            gr.setPaint(Color.BLACK);
            gr.setStroke(new BasicStroke((float) 2));

            if (figure != null && figure.getCoords() != null) {
                figure.changeStart(startX, startY);
                gr.draw(figure.getPath());
            }
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            doDrawing(g);
        }
    }
}
