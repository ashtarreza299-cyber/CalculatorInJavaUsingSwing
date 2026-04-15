

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Stack;

public class CalculatorController {

    CalculatorView view;
    String screenText = "";

    ArrayList<MyButton> buttons;
    public CalculatorController( CalculatorView view) {

        buttons = new ArrayList<>(

                Arrays.asList(
                        view.btn9, view.btn8, view.btn7,  view.operDiv,
                        view.btn6, view.btn5, view.btn4, view.operMul,
                        view.btn3, view.btn2, view.btn1, view.operSub,
                        view.btnC, view.btn0, view.operAdd
                )
        );

        this.view = view;

        for(MyButton btn: buttons){

            btn.addActionListener(new ActionListener() {
                public void  actionPerformed(ActionEvent e) {

                    if(
                            btn.getText().equals("/") || btn.getText().equals("x") ||
                                    btn.getText().equals("+") || btn.getText().equals("-")
                    )
                        screenText += (" " + btn.getText() + " ");
                    else
                        screenText += btn.getText();

                    view.screenTextPane.setText(screenText);
                }
            });
        }

        view.operEq.addActionListener(new ActionListener(){
            public void  actionPerformed(ActionEvent e) {
                // not handled yet

                try{
                    if (screenText.isEmpty()) {
                        screenText = "0";
                        view.screenTextPane.setText(screenText);
                    } else {

                        double value = evaluate();
                        if(value == (long)value){
                            screenText = String.valueOf((long)value);
                        }else
                            screenText = String.valueOf(value);

                        view.screenTextPane.setText(screenText);
                    }
                }catch(NumberFormatException e1){
                    view.screenTextPane.setText("Invalid Number Format");
                }catch(ArithmeticException e2){
                    view.screenTextPane.setText("Division by zero");
                }catch(EmptyStackException e3){
                    view.screenTextPane.setText("Incomplete Expression");
                }catch(Exception ex){
                    view.screenTextPane.setText("Error");
                }
            }
        });
        view.btnC.addActionListener(new ActionListener(){
            public void  actionPerformed(ActionEvent e) {

                screenText = "";
                view.screenTextPane.setText(screenText);
            }
        });

    }

    public double evaluate() {

        String expr = screenText.replace("x", "*")
                .replace("X", "*")
                .replace(" ", "");

        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);


            // If it's a digit
            if (Character.isDigit(c) || c == '.') {
                StringBuilder sb = new StringBuilder();

                while (i < expr.length() &&
                        (Character.isDigit(expr.charAt(i)) || expr.charAt(i) == '.')) {
                    sb.append(expr.charAt(i));
                    i++;
                }
                i--; // step back
                numbers.push(Double.parseDouble(sb.toString()));
            }

            // If operator
            else if (c == '+' || c == '-' || c == '*' || c == '/') {

                while (!operators.isEmpty() &&
                        precedence(operators.peek()) >= precedence(c)) {
                    compute(numbers, operators);
                }
                operators.push(c);
            }
        }

        // Final computation
        while (!operators.isEmpty()) {
            compute(numbers, operators);
        }

        return numbers.pop();
    }

    private int precedence(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        return 0;
    }

    private void compute(Stack<Double> numbers, Stack<Character> operators) {

        double b = numbers.pop();
        double a = numbers.pop();
        char op = operators.pop();

        double result = 0;

        switch (op) {
            case '+': result = a + b; break;
            case '-': result = a - b; break;
            case '*': result = a * b; break;
            case '/':
                if(b == 0)  throw new  ArithmeticException("Division by zero");
                result = a / b;
                break;
            default:
                throw new IllegalStateException("Unknown Operator: " + op);
        }

        numbers.push(result);
    }

}
