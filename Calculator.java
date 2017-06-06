import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
/** 
 * Gui Calculator 
 *@author Berk Kurkcuoglu
 *
 */
public class Calculator extends JPanel implements ActionListener {
	
	
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH=400;
	public static final int HEIGHT=480;
	
	private GridBagLayout layout;
	private GridBagConstraints gbc;
	
	private JButton[] numberButtons;
	private JButton[] opButtons;
	
	private JTextField field;
	
	private double num1, num2, ans;
	private double op,memory;
	
	private Font buttonfont = new Font("Milford",Font.PLAIN,27);
		
// Matrix to arrange the places of the number buttons and size of them. ==> positionx,positiony,sizex,sizey	
	private int[][] numConstraints=new int[][]{
			{0,5,2,1},
			{0,4,1,1},
			{1,4,1,1},
			{2,4,1,1},
			{0,3,1,1},
			{1,3,1,1},
			{2,3,1,1},
			{0,2,1,1},
			{1,2,1,1},
			{2,2,1,1},
	};
	// Matrix to arrange the places of the operational buttons and size of them. ==> positionx,positiony,sizex,sizey	
	private int[][] opConstraints=new int[][]{
			{2,5,1,1},
			{3,5,2,1},
			{3,4,1,1},
			{3,3,1,1},
			{4,4,1,1},
			{4,3,1,1},
			{3,2,1,1},
			{4,1,1,1},
			{0,1,1,1},
			{1,1,1,1},
			{2,1,1,1},
			{3,1,1,1},
			{4,2,1,1}
	};
/**
 * Calculator constructor with a frame 400x480 which includes all the buttons and the field on it.
 */
	public Calculator(){
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		layout= new GridBagLayout();
		layout.columnWidths= new int[] {80,80,80,80,80};
		layout.rowHeights= new int[] {80,80,80,80,80,80};
		setLayout(layout);
		
		numberButtons=new JButton[10];
		for(int i = 0;i<numberButtons.length;i++){
			numberButtons[i]=new JButton(""+ i);
			numberButtons[i].setFont(buttonfont);
			numberButtons[i].addActionListener(this);
			gbc= new GridBagConstraints();
			gbc.gridx=numConstraints[i][0];
			gbc.gridy=numConstraints[i][1];
			gbc.gridwidth=numConstraints[i][2];
			gbc.gridheight=numConstraints[i][3];
			gbc.fill= GridBagConstraints.BOTH;
			gbc.insets= new Insets(2,2,2,2);
		
			add(numberButtons[i],gbc);
		}
		
		opButtons=new JButton[13];
		
		opButtons[0] = new JButton(".");
		opButtons[1] = new JButton("=");
		opButtons[2] = new JButton("+");
		opButtons[3] = new JButton("-");
		opButtons[4] = new JButton("x");
		opButtons[5] = new JButton("/");
		opButtons[6] = new JButton("C");
		opButtons[7] = new JButton("±");
		opButtons[8] = new JButton("MC");
		opButtons[9] = new JButton("M-");
		opButtons[10] = new JButton("M+");
		opButtons[11] = new JButton("MR");
		opButtons[12] = new JButton("√");
		
		
		for(int i=0;i < opButtons.length; i++){
			opButtons[i].setFont(buttonfont);
			gbc.gridx = opConstraints[i][0];
			gbc.gridy = opConstraints[i][1];
			gbc.gridwidth = opConstraints[i][2];
			gbc.gridheight = opConstraints[i][3];
			
			opButtons[i].addActionListener(this);
			
			add(opButtons[i],gbc);
		}
		
		field= new JTextField();
		field.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		field.setEditable(false);
		field.setFont(new Font("Times New Roman", Font.PLAIN, 36));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 5;
		gbc.gridheight = 1;
		
		add(field,gbc);
	}
/**
 * main method that creates the calculator frame and makes it visible.	
 * @param args default string for main method.
 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame=new JFrame("Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		frame.add(new Calculator(),BorderLayout.CENTER);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
/**
 * An action listener method checks which buttons is pressed and performs the operations related to the button.
 * @param e an action listener to check button clicks.
 */
	public void actionPerformed(ActionEvent e) {
		
		for(int i = 0; i < numberButtons.length; i++) {
			if(e.getSource() == numberButtons[i]){
				if(op==-1){
					op=0;
					field.setText("");
				}
				field.setText(field.getText() + i);	
			}
		}
			
	
		if(e.getSource() == opButtons[0] && !field.getText().contains(".")) { // decimal button operation
			field.setText(field.getText() + ".");
		}

		if(e.getSource() == opButtons[7]) { // +/- button operation
			field.setText("" + (-1 * Double.parseDouble(field.getText())));
		}

		if(e.getSource() == opButtons[6]) { // clear button operation
			field.setText("");
		}

		if(e.getSource() == opButtons[2]) { // add button operation
			num1 = Double.parseDouble(field.getText());
			op = 1;
			field.setText("");
		}

		if(e.getSource() == opButtons[3]) { // subtract button operation
			num1 = Double.parseDouble(field.getText());
			op = 2;
			field.setText("");
		}

		if(e.getSource() == opButtons[4]) { // multiply button operation
			num1 = Double.parseDouble(field.getText());
			op = 3;
			field.setText("");
		}

		if(e.getSource() == opButtons[5]) { // divide button operation
			num1 = Double.parseDouble(field.getText());
			op = 4;
			field.setText("");
		}
		if(e.getSource() == opButtons[12]){ // square root button function
			ans = Math.sqrt(Double.parseDouble(field.getText()));
			op=-1;
			field.setText("" + ans);			
		}
		
		if(e.getSource() == opButtons[8])  // MC button operation
			memory = 0;
		
		if(e.getSource() == opButtons[9])  // M- button operation
			memory -= Double.parseDouble(field.getText());
		
		if(e.getSource() == opButtons[10]) // M+ button operation
			memory += Double.parseDouble(field.getText());
		
		if(e.getSource() == opButtons[11]) { // MR button operation
			field.setText("" + memory);
			op=-1;
		}
		if(e.getSource() == opButtons[1]) { // equals button operations
			num2 = Double.parseDouble(field.getText());

		if(op == 1) 
			ans = num1 + num2;		
		else if(op == 2) 
			ans = num1 - num2;		 
		else if(op == 3) 
			ans = num1 * num2;		
		else if(op == 4) 
			ans = num1 / num2;
		
		op=-1;
		field.setText("" + ans);
		}
	}
		

}
