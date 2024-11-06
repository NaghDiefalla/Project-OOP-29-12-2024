import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame {
    private JPanel panel;
    private JLabel JLbl1, JLbl2, JLbl3, JLbl4, JLbl5, JLbl6, JLbl7;
    private JButton JBTN1, JBTN2;
    private JTextField JTXF1, JTXF2;
    private JComboBox<String> JCMBX;
    private static DrawingPanel drawingPanel;

    public GUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] arr = {"Rectangle", "Square", "Circle"};

        setTitle("OOP Project");
        setMinimumSize(new Dimension(750, 750));
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        JLbl1 = new JLabel("Select your shape:");
        JCMBX = new JComboBox<>(arr);
        JCMBX.addActionListener(new Swap_Shape());
        JLbl2 = new JLabel("Enter height:");
        JTXF1 = new JTextField(5);
        JLbl3 = new JLabel("Enter width:");
        JTXF2 = new JTextField(5);

        JBTN1 = new JButton("Display");
        JBTN1.setForeground(Color.red);
        JBTN1.addActionListener(new Calc());
        JBTN2 = new JButton("Reset");
        JLbl4 = new JLabel("Area:");
        JLbl6 = new JLabel();

        JLbl5 = new JLabel("Perimeter:");
        JLbl7 = new JLabel();

        panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        panel.add(JLbl1);
        panel.add(JCMBX);
        panel.add(JLbl2);
        panel.add(JTXF1);
        panel.add(JLbl3);
        panel.add(JTXF2);
        panel.add(JLbl4);
        panel.add(JLbl6);
        panel.add(JLbl5);
        panel.add(JLbl7);
        panel.add(JBTN1);
        panel.add(JBTN2);

        drawingPanel = new DrawingPanel();
        add(drawingPanel, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        JBTN2.addActionListener(new Reset());
    }

    class DrawingPanel extends JPanel {
        private Shape drawnShape;
        private static final double RECTANGLE_AREA_THRESHOLD = 3025000;
        private static final double CIRCLE_AREA_THRESHOLD = 238000;
        private static final double SQUARE_AREA_THRESHOLD = 3025000;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (drawnShape != null) {
                double area = drawnShape.getArea();
                if (drawnShape instanceof Rectangle && area > RECTANGLE_AREA_THRESHOLD) {
                    JOptionPane.showMessageDialog(this, "Rectangle area exceeds threshold. It will not be drawn.");
                } else if (drawnShape instanceof Circle && area > CIRCLE_AREA_THRESHOLD) {
                    JOptionPane.showMessageDialog(this, "Circle area exceeds threshold. It will not be drawn.");
                } else if (drawnShape instanceof Square && area > SQUARE_AREA_THRESHOLD) {
                    JOptionPane.showMessageDialog(this, "Square area exceeds threshold. It will not be drawn.");
                } else {
                    if (JCMBX.getSelectedIndex() == 0) {
                        String text = JTXF1.getText();
                        double enteredNumber = Double.parseDouble(text);
                        if(enteredNumber > 550)
                        {
                            JOptionPane.showMessageDialog(this, "Rectangle area exceeds threshold. It will not be drawn.");
                        }
                        else if(enteredNumber > 550)
                        {
                            JOptionPane.showMessageDialog(this, "Rectangle area exceeds threshold. It will not be drawn.");
                        }
                        else drawnShape.draw(g);
                    } else if (JCMBX.getSelectedIndex() == 1) {
                        String text = JTXF1.getText();
                        double enteredNumber = Double.parseDouble(text);
                        if(enteredNumber > 550)
                        {
                            JOptionPane.showMessageDialog(this, "Square area exceeds threshold. It will not be drawn.");
                        }
                        else if(enteredNumber > 550)
                        {
                            JOptionPane.showMessageDialog(this, "Square area exceeds threshold. It will not be drawn.");
                        }
                        else drawnShape.draw(g);
                    } else if (JCMBX.getSelectedIndex() == 2) {
                        String text = JTXF1.getText();
                        double enteredNumber = Double.parseDouble(text);
                        if(enteredNumber > 275)
                        {
                            JOptionPane.showMessageDialog(this, "Circle area exceeds threshold. It will not be drawn.");
                        }
                        else if(enteredNumber > 275)
                        {
                            JOptionPane.showMessageDialog(this, "Circle area exceeds threshold. It will not be drawn.");
                        }
                        else drawnShape.draw(g);
                    }
                    else
                    {
                        drawnShape.draw(g);
                    }
                }
            }
        }

        public void setDrawnShape(Shape shape) {
            this.drawnShape = shape;
            repaint();
        }
    }

    class Reset implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JTXF1.setText("");
            JTXF2.setText("");
            JLbl6.setText("");
            JLbl7.setText("");
            drawingPanel.setDrawnShape(null);
        }
    }

    class Swap_Shape implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (JCMBX.getSelectedIndex() == 0) {
                JLbl2.setText("Enter height:");
                JLbl3.setVisible(true);
                JTXF2.setVisible(true);
            } else if (JCMBX.getSelectedIndex() == 1) {
                JLbl2.setText("Enter side length:");
                JLbl3.setVisible(false);
                JTXF2.setVisible(false);
            } else if (JCMBX.getSelectedIndex() == 2) {
                JLbl2.setText("Enter radius:");
                JLbl3.setVisible(false);
                JTXF2.setVisible(false);
            }
        }
    }

    class Calc implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (JCMBX.getSelectedIndex() == 0) { // Rectangle
                try {
                    double length = Double.parseDouble(JTXF1.getText());
                    double width = Double.parseDouble(JTXF2.getText());

                    if (length < 0 || width < 0) {
                        JOptionPane.showMessageDialog(null, "Please enter non-negative parameters!");
                    } else {
                        Rectangle r1 = new Rectangle(length, width);
                        JLbl6.setText(String.valueOf(r1.getArea()));
                        JLbl7.setText(String.valueOf(r1.getPer()));
                        drawingPanel.setDrawnShape(r1);
                    }
                } catch (NumberFormatException | HeadlessException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid parameters!");
                }
            } else if (JCMBX.getSelectedIndex() == 1) { // Square
                try {
                    double side = Double.parseDouble(JTXF1.getText());

                    if (side < 0) {
                        JOptionPane.showMessageDialog(null, "Please enter a non-negative side length!");
                    } else {
                        Square s1 = new Square(side);
                        JLbl6.setText(String.valueOf(s1.getArea()));
                        JLbl7.setText(String.valueOf(s1.getPer()));
                        drawingPanel.setDrawnShape(s1);
                    }
                } catch (NumberFormatException | HeadlessException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid parameters!");
                }
            } else if (JCMBX.getSelectedIndex() == 2) { // Circle
                try {
                    double radius = Double.parseDouble(JTXF1.getText());

                    if (radius < 0) {
                        JOptionPane.showMessageDialog(null, "Please enter a non-negative radius!");
                    } else {
                        Circle c1 = new Circle(radius);
                        JLbl6.setText(String.valueOf(c1.getArea()));
                        JLbl7.setText(String.valueOf(c1.getPer()));
                        drawingPanel.setDrawnShape(c1);
                    }
                } catch (NumberFormatException | HeadlessException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid parameters!");
                }
            }
        }
    }

    abstract static class Shape {
        protected String color;
        protected boolean isFilled;

        public Shape() {
            this.color = null;
            this.isFilled = false;
        }

        public abstract double getArea();

        public abstract double getPer();

        public abstract void draw(Graphics g);
    }

    static class Circle extends Shape {
        protected double radius;

        public Circle(double radius) {
            this.radius = radius;
        }

        @Override
        public double getArea() {
            return Math.PI * Math.pow(radius, 2);
        }

        @Override
        public double getPer() {
            return 2 * Math.PI * radius;
        }

        @Override
        public void draw(Graphics g) {
            int diameter = (int) (radius * 2);
            int x = getX();
            int y = (drawingPanel.getHeight() - diameter) / 2;
            g.drawOval(x, y, diameter, diameter);
        }

        private int getX() {
            // Implement logic to determine the x-coordinate for drawing
            return 50;
        }
    }

    static class Rectangle extends Shape {
        protected double length, width;

        public Rectangle(double length, double width) {
            this.length = length;
            this.width = width;
        }

        @Override
        public double getArea() {
            return length * width;
        }

        @Override
        public double getPer() {
            return 2 * (length + width);
        }

        @Override
        public void draw(Graphics g) {
            int x = getX();
            int y = (drawingPanel.getHeight() - (int) width) / 2;
            g.drawRect(x, y, (int) length, (int) width);
        }

        private int getX() {
            // Implement logic to determine the x-coordinate for drawing
            return 100;
        }
    }

    static class Square extends Rectangle {
        public Square(double side) {
            super(side, side);
        }
    }
}
