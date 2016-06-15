import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

/**
 * @author 1kistoroh
 * Main Method
 * 
 * Create a GUI that contains
 * 	A 7x7 selection grid using a JRadioButton
 * 	A button that launches the function call to my class that will be
 * 		used to determine a hit or a miss to a ship
 * 	A field to track each turn (A counter)
 * 	A message field to display a hit or a miss
 * 	Another message field to display: Sunk a battleship or win
 * 
 * The launch method will
 * 	When the launch method is pressed, sends the 2d array location corresponding
 * 		to the 7x7 grid selection made by the user
 * 	The method will send to the 2D arrya location as int ROW and int COLUMN
 * 		to the hitDetect method in the Battleship class
 * 	The method will receive from the hitDetect method in the battleship class 
 * 		and the booleans HIT and SINK
 */

public class Battleship_RohitK_R11 extends JComponent {
	
	/**
	 * Creator: Rohit Terry Kisto
	 * Purpose: Create a program that allows the user to play battleship with a premade class file
	 * Date: 10/5/2016
	 * Rev: 8U
	 */
	
	private static final long serialVersionUID = -2602494860686992077L;

	//Dimension stuff
	private final int WIDTH = 400;
	private final int HEIGHT = 350;
	private Dimension d;

	//Layout
	private FlowLayout f;

	//Button stuff
	private JRadioButton[][] b; //2D array of JRadioButton
	private JButton launch; //Button for launch
	private JButton restart; //Restart game button
	private ButtonGroup buttonGroup; //A button group, makes it easier to manage stuff

	//Positions
	private int ROW;
	private int COL;
	private int[][] position;
	
	//Hit detect
	private Boolean[] bool = new Boolean[2];
	private int sinkCounter;
	
	//Character array
	private String[] chars = {"A", "B", "C", "D", "E", "G", "H"}; //To make GUI look nice
	
	//Counter stuff
	private int counter;
	private JLabel counterLabel;
	
	//Colours
	private static final Color SEA_BLUE = new Color(104, 161, 216);
	private final Color MISS = new Color(0, 255, 0);
	private final Color HIT = new Color(255, 0, 0);
	
	//Image
	private Image image;
	private static BufferedImage bg;
	
	//Action Listener
	private ActionComponent ac;
	
	//BATTLESHIP CLASS ~!~!~!~!~!~!~!~!~!~!   @@@   !~!~!~!~!~!~!~!~!~!~
	//private Battleship_Test bt; //My test class
	/* TEST LAYOUT
	{1, 0, 1, 0, 0, 0, 0},
	{1, 0, 1, 0, 0, 0, 0},
	{1, 0, 1, 0, 0, 0, 0},
	{0, 0, 1, 0, 0, 0, 0},
	{0, 0, 0, 0, 0, 0, 0},
	{0, 0, 0, 0, 0, 0, 0},
	{0, 0, 1, 1, 1, 1, 1}
	 */
	//private Battleship_Test bt;
	private BattleShip_TylerT_R3 bt1;
	
	//======================================================================================\\
	
	public Battleship_RohitK_R11() {
		init();
		
		//Sets the size and requests focus
		setPreferredSize(d);
		setFocusable(true);
		requestFocus();
		
		//Sets the layout to a new flow layout
		setLayout(f);
		
		//Adds the JRadioButton to the screen and to the button group
		for(int i = 0;i < b.length;i++) {
			for(int j = 0;j < b[i].length;j++) {				
				b[i][j] = new JRadioButton(chars[i] + "," + (j+1));
				buttonGroup.add(b[i][j]);
				b[i][j].setBackground(SEA_BLUE);
				add(b[i][j]);
			}
		}
		//Sets the size of the launch button, adds an action listener to it and adds it to the screen
		launch.setPreferredSize(new Dimension(WIDTH, 30));
		launch.addActionListener(ac);
		add(launch);
		
		restart.setPreferredSize(new Dimension(WIDTH, 30));
		restart.addActionListener(ac);
		add(restart);
		
		add(counterLabel);
	}
	
	//LAUNCH METHOD ===========================================
	@SuppressWarnings("static-access")
	private void launch(int i, int j) {
		ROW = i;
		COL = j;
		
		bool = bt1.hitDetect(ROW, COL);
		//bool = bt1.hitDetect(ROW, COL); //EDIT OBJECT HERE!!!!!!!!!!!!!!!!!!!!!!!!
		
		//HIT CHECK
		if(bool[0] == true) {
			JOptionPane.showMessageDialog(null, "HIT",
					"Hit Pane", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "MISS",
					"Hit Pane", JOptionPane.INFORMATION_MESSAGE);
		}
		
		//SINK CHECK
		if(bool[1] == true) {
			JOptionPane.showMessageDialog(null, "You sunk a battleship!",
					"Sink Pane", JOptionPane.INFORMATION_MESSAGE);
			sinkCounter++;
		}
		
		if(sinkCounter > 2) {
			JOptionPane.showMessageDialog(null, "Great Job!\n"
					+ "You eliminated all the Battleships!",
					"Victory Pane", JOptionPane.INFORMATION_MESSAGE);
			launch.setEnabled(false);
			JOptionPane.showMessageDialog(null, "Please restart game!",
					"Restart Warning Pane", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void getImage(Image image) {
		 this.image = image;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
	
	//Inits my variables
	private void init() {
		d = new Dimension(WIDTH, HEIGHT);
		
		getImage(bg);
		
		f = new FlowLayout(FlowLayout.CENTER, 10, 10);
		//pos = new int[7][7];
		b = new JRadioButton[7][7];
	
		launch = new JButton("LAUNCH");
	
		buttonGroup = new ButtonGroup();
	
		restart = new JButton("RESTART GAME!");
		
		reset();
		
		ac = new ActionComponent();		
		
		//INIT BATTLESHIPS HERE
		//bt = new Battleship_Test(); //My test class
		bt1 = new BattleShip_TylerT_R3(); //Tylers class
	}
	
	private void reset() {
		counter = 0;
		counterLabel = new JLabel("Current Turn: " + counter);
		
		position = new int[7][7];
		for(int i = 0;i < position.length;i++) {
			for(int j = 0;j < position[i].length;j++) {
				position[i][j] = 0;
			}
		}
		sinkCounter = 0;
	}
	
	//Main Method
	public static void main(String[] args) {
		JFrame f = new JFrame("Rohits Battleship!"); //New JFrame
		
		try {
			bg = ImageIO.read(new File("src\\oceanImage.jpg"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		f.setContentPane(new Battleship_RohitK_R11()); //Sets the content pane
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Sets the close option
		f.setResizable(false); //Makes it so you can't re-size the window
		f.setLocationRelativeTo(null); //Sets position to center screen
		f.setBackground(SEA_BLUE); //Sets colour of the JFrame
		f.pack(); //Packs window
		f.setVisible(true); //Makes window visible
	}
	
	//Private class that handles the action listener
	private class ActionComponent implements ActionListener {		
		//If action belongs to this, do something
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == launch) {
				for(int i = 0;i < b.length;i++) {
					for(int j = 0;j < b[i].length;j++) {
						if(b[i][j].isEnabled() == false && b[i][j].isSelected()) {
							
						} else if (b[i][j].isSelected()) {
							counter++;
							counterLabel.setText("Current Turn: " + counter);
							//JOptionPane.showMessageDialog(null, "Launch Succesful"
							//		+ " at " + chars[i] + "," + (j+1),
							//		"Launch Pane", JOptionPane.INFORMATION_MESSAGE);
							position[i][j] = 1;
							launch(i, j);
							if(bool[0] == true) {
								b[i][j].setBackground(HIT);
							} else {
								b[i][j].setBackground(MISS);
							}
							b[i][j].setEnabled(false);
						} 
					}
				}
			} else if(e.getSource() == restart) {
				reset();
				for(int i = 0;i < b.length;i++) {
					for(int j = 0;j < b[i].length;j++) {
						b[i][j].setEnabled(true);
						b[i][j].setBackground(SEA_BLUE);
					}
				}
				JOptionPane.showMessageDialog(null, "Game Successfully Reset!",
						"Reset Pane", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}