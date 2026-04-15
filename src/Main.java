import javax.swing.*;

public class Main{
    public static void main(String[] args) {

    Thread.setDefaultUncaughtExceptionHandler((t,e) ->

                    JOptionPane.showMessageDialog(null,
                            "Unexpected Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE
                    )
            );

    SwingUtilities.invokeLater(() -> {

        CalculatorView view = new CalculatorView();
        new CalculatorController(view);

    });

    }
}