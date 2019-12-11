package Calculator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame {
    private JTextField value;
    private final JButton buttonCE;
    private final JButton button0;
    private final JButton button1;
    private final JButton button2;
    private final JButton button3;
    private final JButton button4;
    private final JButton button5;
    private final JButton button6;
    private final JButton button7;
    private final JButton button8;
    private final JButton button9;
    private final JButton buttonPoint;
    private final JButton buttonAddition;
    private final JButton buttonSubtraction;
    private final JButton buttonMultiplication;
    private final JButton buttonDivision;
    private final JButton buttonResult;
    private double register = 0.0;
    String lastOperation="";


    public MainFrame (){
        super("Calculator");

        JPanel panel = new JPanel(null);
        panel.setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel(new GridLayout(4,4, 10,5));
        buttonPanel.setBorder(new EmptyBorder(5,5,5,5));
        value = new JTextField(30);
        value.setText("0");


        //запрет ввода с клавиатуры
        value.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                e.consume();
            }
        });


        button0 = new JButton("0");
        button1 = new JButton("1");
        button2 = new JButton("2");
        button3 = new JButton("3");
        button4 = new JButton("4");
        button5 = new JButton("5");
        button6 = new JButton("6");
        button7 = new JButton("7");
        button8 = new JButton("8");
        button9 = new JButton("9");
        buttonCE = new JButton("CE");
        buttonResult = new JButton("=");
        buttonAddition = new JButton("+");
        buttonSubtraction = new JButton("-");
        buttonMultiplication = new JButton("*");
        buttonDivision = new JButton("/");
        buttonPoint = new JButton(".");

        buttonResult.setSize(200,40);

        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setBorder(new EmptyBorder(20,20,20,20));
        panel.add(value, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(buttonResult, BorderLayout.SOUTH);
        buttonPanel.add(button7);
        buttonPanel.add(button8);
        buttonPanel.add(button9);
        buttonPanel.add(buttonAddition);
        buttonPanel.add(button4);
        buttonPanel.add(button5);
        buttonPanel.add(button6);
        buttonPanel.add(buttonSubtraction);
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(buttonMultiplication);
        buttonPanel.add(buttonCE);
        buttonPanel.add(button0);
        buttonPanel.add(buttonPoint);
        buttonPanel.add(buttonDivision);


        // ввоод чисел в строку
        ActionListener numbersListener = (ActionEvent e) -> {
            String s = value.getText();
            if (s.equals("0")){
                String s1 = ((JButton)e.getSource()).getText();
                value.setText(s1);}
            else {
                String s1 = ((JButton)e.getSource()).getText();
                value.setText(s+s1);
            }
        };

        button0.addActionListener(numbersListener);
        button1.addActionListener(numbersListener);
        button2.addActionListener(numbersListener);
        button3.addActionListener(numbersListener);
        button4.addActionListener(numbersListener);
        button5.addActionListener(numbersListener);
        button6.addActionListener(numbersListener);
        button7.addActionListener(numbersListener);
        button8.addActionListener(numbersListener);
        button9.addActionListener(numbersListener);

        ActionListener dotListener = (ActionEvent e) -> {
            String s = value.getText();
            if (s.contains(".")){}
            else {
                value.setText(s+".");
            }
        };

        buttonPoint.addActionListener(dotListener);

        ActionListener ceListener = (ActionEvent e) -> {
            value.setText("0");
            register = 0.0;
        };

        buttonCE.addActionListener(ceListener);

        ActionListener operationListener = (ActionEvent e) -> {
            String s = value.getText();
            String s1 = ((JButton)e.getSource()).getText();
            switch (lastOperation) {
                case "":
                    register = Double.parseDouble(s);
                    value.setText("");
                    lastOperation = s1;
                    System.out.println(register);
                    break;
                case "+":
                    register += Double.parseDouble(s);
                    value.setText("");
                    lastOperation = s1;
                    System.out.println(register);
                    break;
                case "-":
                    register -= Double.parseDouble(s);
                    value.setText("");
                    lastOperation = s1;
                    System.out.println(register);
                    break;
                case "*":
                    register *= Double.parseDouble(s);
                    value.setText("");
                    lastOperation = s1;
                    System.out.println(register);
                    break;
                case "/":
                    register /= Double.parseDouble(s);
                    value.setText("");
                    lastOperation = s1;
                    System.out.println(register);
                    break;
            }
        };

        buttonAddition.addActionListener(operationListener);
        buttonSubtraction.addActionListener(operationListener);
        buttonMultiplication.addActionListener(operationListener);
        buttonDivision.addActionListener(operationListener);


        ActionListener resultListener = (ActionEvent e) -> {
            String s = value.getText();
            switch (lastOperation) {
                case "+":
                    register += Double.parseDouble(s);
                    lastOperation="";
                    break;
                case "-":
                    register -= Double.parseDouble(s);
                    lastOperation="";
                    break;
                case "*":
                    register *= Double.parseDouble(s);
                    lastOperation="";
                    break;
                case "/":
                    register /= Double.parseDouble(s);
                    lastOperation="";
                    break;
            };
            
            //String sss= Double.toString(register);
            
            value.setText(Double.toString(register));
        };

       buttonResult.addActionListener(resultListener);

    }
}