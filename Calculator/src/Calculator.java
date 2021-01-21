import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Calculator implements ActionListener
{
	// Variable
	///////////////////////////////////
	int button_count = 10, function_button_count = 9;
	
	double num1 = 0, num2 = 0, result = 0;
	char operator;
	
	// Instances
	///////////////////////////////////
	JFrame frame;
	JTextField text_Field;
	JButton[] number_Buttons = new JButton[button_count];
	JButton[] function_Buttons = new JButton[function_button_count];
	JButton add_B, sub_B, mul_B, div_B, dec_B, equ_B, del_B, clr_B, neg_B;
	JPanel panel;
	
	Font font = new Font("Ink Free", Font.BOLD, 30);
	
	// Declaration
	///////////////////////////////////
	
	
	Calculator()
	{
		// Setting the frame
		frame = new JFrame("Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420, 550);
		frame.setLayout(null);
		
		// text field
		text_Field = new JTextField();
		text_Field.setBounds(50,  25,  300,  50);
		text_Field.setFont(font);
		text_Field.setEditable(false);
		
		// buttons
		add_B = new JButton("+");
		sub_B = new JButton("-");
		mul_B = new JButton("*");
		div_B = new JButton("/");
		dec_B = new JButton(".");
		equ_B = new JButton("=");
		del_B = new JButton("Del");
		clr_B = new JButton("Clear");
		neg_B = new JButton("Neg");
		
		function_Buttons[0] = add_B;
		function_Buttons[1] = sub_B;
		function_Buttons[2] = mul_B;
		function_Buttons[3] = div_B;
		function_Buttons[4] = dec_B;
		function_Buttons[5] = equ_B;
		function_Buttons[6] = del_B;
		function_Buttons[7] = clr_B;
		function_Buttons[8] = neg_B;
		
		for (int a = 0; a  < function_button_count; a++)
		{
			function_Buttons[a].addActionListener(this);
			function_Buttons[a].setFont(font);
			function_Buttons[a].setFocusable(false);
		}
		
		for (int a = 0; a < button_count; a++)
		{
			number_Buttons[a] = new JButton(String.valueOf(a));
			number_Buttons[a].addActionListener(this);
			number_Buttons[a].setFont(font);
			number_Buttons[a].setFocusable(false);
		}
		
		neg_B.setBounds(50, 430, 100, 50);
		del_B.setBounds(150, 430, 100, 50);
		clr_B.setBounds(250, 430, 100, 50);
		
		panel = new JPanel();
		panel.setBounds(50, 100, 300, 300);
		panel.setLayout(new GridLayout(4, 4, 10, 10));
		
		for (int a = 1, b = 0; a < button_count; a++)
		{
			panel.add(number_Buttons[a]);
			if ((a % 3) == 0)
			{
				panel.add(function_Buttons[b]);
				b++;
			}
		}
		
		panel.add(dec_B);
		panel.add(number_Buttons[0]);
		panel.add(equ_B);
		panel.add(div_B);
		
		frame.add(panel);
		
		frame.add(neg_B);
		frame.add(del_B);
		frame.add(clr_B);
		
		frame.add(text_Field);
		
		frame.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		Calculator calc = new Calculator();
	}
	
	@Override
	public void actionPerformed(ActionEvent actionE) 
	{
		for (int a = 0; a < button_count; a++)
		{
			if (actionE.getSource() == number_Buttons[a])
			{
				text_Field.setText(text_Field.getText().concat(String.valueOf(a)));
			}
		}
		
		for (int a = 0; a < function_button_count; a++)
		{
			if (actionE.getSource() == function_Buttons[a])
			{
				if (a == 4)
					text_Field.setText(text_Field.getText().concat(function_Buttons[a].getText()));
				else if (a == 5)
				{
					num2 = Double.parseDouble(text_Field.getText());
					
					switch(operator)
					{
					case '+':
						result = num1 + num2;
						break;
					case '-':
						result = num1 - num2;
						break;
					case '*':
						result = num1 * num2;
						break;
					case '/':
						result = num1 / num2;
						break;
					}
					
					text_Field.setText(String.valueOf(result));
					num1 = result;
				}
				else if (a == 6)
				{
					String string = text_Field.getText();
					text_Field.setText("");
					for(int b = 0; b < string.length() - 1; b++)
					{
						text_Field.setText(text_Field.getText() + string.charAt(b));
					}
				}
				else if (a == 7)
					text_Field.setText("");
				else if (a == 8)
				{
					double temp = Double.parseDouble(text_Field.getText());
					temp*=-1;
					text_Field.setText(String.valueOf(temp));
				}
				else 
				{ 
					num1 = Double.parseDouble(text_Field.getText());
					operator = function_Buttons[a].getText().charAt(0);
					text_Field.setText("");
				}
			}
		}
		
		
	}
}
