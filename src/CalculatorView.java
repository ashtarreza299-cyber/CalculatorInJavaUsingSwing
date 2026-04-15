import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class CalculatorView extends JFrame {

    JTextPane screenTextPane = new JTextPane();

    MyButton btn7 = new MyButton("7");
    MyButton btn8 = new MyButton("8");
    MyButton btn9 = new MyButton("9");
    MyButton operDiv = new MyButton("/");

    MyButton btn4 = new MyButton("4");
    MyButton btn5 = new MyButton("5");
    MyButton btn6 = new MyButton("6");
    MyButton operMul = new MyButton("x");

    MyButton btn1 = new MyButton("1");
    MyButton btn2 = new MyButton("2");
    MyButton btn3 = new MyButton("3");
    MyButton operSub = new MyButton("-");

    MyButton btnC = new MyButton("C");
    MyButton btn0 = new MyButton("0");
    MyButton operAdd = new MyButton("+");
    MyButton operEq = new MyButton("=");

    public CalculatorView() {
        adjustFrame();
        setupUI();
        setVisible(true);
    }

    private void adjustFrame() {
        setTitle("Calculator");
        setSize(320, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(30, 30, 30));
    }

    private JPanel createTopPanel() {

        JTextPane title = new JTextPane();
        StyledDocument doc = title.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        title.setText("Calculator");
        title.setEditable(false);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        title.setBackground(new Color(30, 30, 30));
        title.setFocusable(false);

        // Screen styling
        StyledDocument screenDoc = screenTextPane.getStyledDocument();
        SimpleAttributeSet right = new SimpleAttributeSet();
        StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);
        screenDoc.setParagraphAttributes(0, screenDoc.getLength(), right, false);

        screenTextPane.setText("0");
        screenTextPane.setFont(new Font("Segoe UI", Font.BOLD, 32));
        screenTextPane.setForeground(Color.WHITE);
        screenTextPane.setBackground(new Color(50, 50, 50));
        screenTextPane.setEditable(false);
        screenTextPane.setFocusable(false);
        screenTextPane.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        screenTextPane.setPreferredSize(new Dimension(300, 100));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(30, 30, 30));
        topPanel.add(title, BorderLayout.NORTH);
        topPanel.add(screenTextPane, BorderLayout.CENTER);

        return topPanel;
    }

    private JPanel createButtons() {

        JPanel panel = new JPanel(new GridLayout(4, 4, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(new Color(30, 30, 30));

        // Row 1
        panel.add(btn7);
        panel.add(btn8);
        panel.add(btn9);
        panel.add(operDiv);

        // Row 2
        panel.add(btn4);
        panel.add(btn5);
        panel.add(btn6);
        panel.add(operMul);

        // Row 3
        panel.add(btn1);
        panel.add(btn2);
        panel.add(btn3);
        panel.add(operSub);

        // Row 4
        panel.add(btnC);
        panel.add(btn0);
        panel.add(operAdd);
        panel.add(operEq);

        return panel;
    }

    private void setupUI() {
        add(createTopPanel(), BorderLayout.NORTH);
        add(createButtons(), BorderLayout.CENTER);
    }

}