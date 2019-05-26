package com.wethinkcode.swingy.view;

import com.wethinkcode.swingy.controller.*;
import com.wethinkcode.swingy.model.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.print.PrintService;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class Swingy extends JFrame implements View {
	private boolean buttonClicked = false;
	private String input;
	private final Controller controller;
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JLabel errorLable;
	private javax.swing.Box.Filler filler1;
	private javax.swing.JLabel headerLable;
	private javax.swing.JScrollPane jScrollPane1;
	// private javax.swing.JTextArea outputTextArea;
	private javax.swing.JButton btnSendMessage;
	private javax.swing.JTextField inputTextBox;

	private JTextPane pane;
	private Document doc;
	private SimpleAttributeSet attributeSet = new SimpleAttributeSet();

	private Color whiteColor = new Color(255, 255, 255);
	private Color blackColor = new Color(0, 0, 0);
	private Color redColor = new Color(255, 0, 0);
	private Color greenColor = new Color(0, 255, 0);
	private Color blueColor = new Color(0, 0, 255);
	// End of variables declaration//GEN-END:variables

	public Swingy(Controller controller) {
		super("swingy");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.controller = controller;
		initComponents();
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jScrollPane1 = new javax.swing.JScrollPane();
		jScrollPane1.setBounds(100, 100, 200, 50);
		pane = new JTextPane();
		pane.setBackground(blackColor);
		pane.setForeground(whiteColor);
		pane.setBounds(0, 0, 200, 50);
		// outputTextArea = new javax.swing.JTextArea();
		doc = pane.getStyledDocument();

		inputTextBox = new javax.swing.JTextField();
		btnSendMessage = new javax.swing.JButton();
		headerLable = new javax.swing.JLabel();
		errorLable = new javax.swing.JLabel();
		filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0),
				new java.awt.Dimension(0, 0));

		getRootPane().setDefaultButton(btnSendMessage);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setBackground(new java.awt.Color(204, 214, 228));
		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				inputTextBox.requestFocus();
			}
		});

		// outputTextArea.setColumns(20);
		// outputTextArea.setRows(5);
		// outputTextArea.setBackground(blackColor);
		// outputTextArea.setForeground(whiteColor);
		jScrollPane1.setViewportView(pane);

		inputTextBox.setName("inputTextField"); // NOI18N
		inputTextBox.setBackground(blackColor);
		inputTextBox.setForeground(whiteColor);	
		inputTextBox.setFont(new Font("Tahoma", Font.BOLD, 14));

		btnSendMessage.setText("SEND MESSAGE");
		btnSendMessage.setActionCommand("");
		btnSendMessage.setName("btnSendMessage"); // NOI18N
		btnSendMessage.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				letterSent(evt);
			}
		});

		headerLable.setText("TEXT BASED ADVENTURE !!!");
		headerLable.setName("lblHeading"); // NOI18N
		headerLable.setForeground(redColor);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addGap(80, 80, 80)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
						.addGroup(layout.createSequentialGroup().addComponent(errorLable).addGap(477, 477, 477)
								.addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addComponent(jScrollPane1)
						.addComponent(headerLable, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								layout.createSequentialGroup().addComponent(inputTextBox)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(btnSendMessage)))
				.addContainerGap(131, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addComponent(headerLable, javax.swing.GroupLayout.PREFERRED_SIZE, 32,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 312,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(inputTextBox, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(btnSendMessage))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(errorLable).addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(25, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void letterSent(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_letterSent
		swingyClicked();
	}// GEN-LAST:event_letterSent

	private void swingyClicked() {
		System.out.println("swingClicked()--inside swingy");
		if (inputTextBox.getText().equals("")) {
			errorLable.setText("input empty");
		}
		try {
			input = inputTextBox.getText();
		} catch (NullPointerException e) {
			invalid(input);
		}
		buttonClicked = true;
		System.out.println("Button clicked :" + buttonClicked);
	}

	@Override
	public void clear() {
		pane.setText("");
	}

	@Override
	public void printLine(String line) {
		StyleConstants.setBold(attributeSet, true);
		try {
			doc.insertString(doc.getLength(), line + "\n", attributeSet);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
		// outputTextArea.append(line + "\n");
	}

	@Override
	public void printSpacer() {
		printLine("-----------------------");
	}

	@Override	
	public void main(){
		this.setVisible(true);
		printLine("mainMenu");
		printLine("new");
		printLine("load");
	}

	@Override
	public void printMap(int playerX, int playerY, int size){
		printLine(" Map Size : " + size + "x" + size);
		for(int j = size - 1; j >= 0; j--) {
			for(int i = 0; i < size; i++) {
				if (i == playerX && j == playerY) {
					try {
						StyleConstants.setForeground(attributeSet, Color.red);  
						doc.insertString(doc.getLength(), " |*| ", attributeSet);
						attributeSet = new SimpleAttributeSet();
					} catch (BadLocationException e) {
						e.printStackTrace();
					}
					// outputTextArea.setForeground(redColor);
					// outputTextArea.append(" |*| ");
				}else {
					try {
						doc.insertString(doc.getLength(), " |*| ", attributeSet);
					} catch (BadLocationException e) {
						e.printStackTrace();
					}
					// outputTextArea.setForeground(whiteColor);
					// outputTextArea.append(" | | ");
//                    System.out.print(coordinates[i][j].getSymbol());
				}
			}
			printLine("");
		}
	}
	
	@Override
	public void navigation() {
		// printLine("navigatioMenu");
		printLine("north");
		printLine("east");
		printLine("south");
		printLine("west");
	}
	
	@Override
	public void characterName(){
		printLine("Whats your name ?");
	}

	@Override	
	public void characterCreation() {
		printLine("What is your class");
		printLine("archer");
		printLine("knight");
		printLine("paladin");
		printLine("wizard");
	}
	
	
	@Override
	public void battle(Hero h, Enemy e){
		printLine(h.getName() + " attacked " + e.getName() + " with " + h.getAtk() + " damage");
		printLine(e.getName() + " has " + e.getHp() + "hp left");

		printLine(e.getName() + " attacked " + h.getName() + " with " + e.getAtk() + " damage");
		printLine(h.getName() + " has " + h.getHp() + "hp left");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
	
	@Override
	public void fightMenu(Hero h, Enemy e) {
		// printLine("fightMenu");
		printLine(h.getName() + " Are you brave enough to fight the " + e.getName() + "?");
//		TODO PRINT HEALTH , atack and def
		printLine("fight");
		printLine("run");
	}
	
	@Override
	public void fightOutcome(Hero h, Enemy e){
		if(e.getHp() <= 0)
			printLine("The enemy "+e.getName()+" is dead");
		else if(h.getHp() <= 0){
			printLine(h.getName()+" has died");
		}
//	print HEALTH AT`ACK DEF
	}
	
	@Override
	public void lootMenu(Artifact artifact) {
		// printLine("lootMenu");
		printLine("You found an ancient" + artifact.type);
		printLine("its name seems to be " + artifact.name);
		printLine("Take it ???");
		printLine("take");
		printLine("leave");
	}
	
	@Override
	public void win() {
		printLine("You got to the edge of the map without dying");
		printLine("menu");
		printLine("exit");
	}
	
	@Override
	public void death() {
		// printLine("deathMenu");
		printLine("You were slayn by your enemies");
		printLine("menu");
		printLine("exit");
	}

	@Override
	public void save(){
		printLine("Game Saved");
	}


	// TODO Remove-- showSavedGames() Reason: It isnt referenced anywhere in the code
	// @Override
	// public void showSavedGames(File[] savedGames) {
	// 	printSpacer();
	// 	printLine("SAVED GAMES:");
	// 	for (File f : savedGames) printLine(f.getName());
	// }

	@Override
	public void printGameStats(Model game) {
		printSpacer();
		printLine("Hero: " + game.hero.getName());
		printLine("Class: " + game.hero.getOccupation());	
		printLine("Level: " + game.hero.getLevel());	
		printLine("Experience: " + game.hero.getXp());	
		printLine("Attack: " + game.hero.getAtk());	
		printLine("Defence: " + game.hero.getDef());	
		printLine("Health: " + game.hero.getHp());	
	}

	@Override
	public void printHeroStats(Hero hero) {
		printLine(hero.getName() + "'s Stats:");
		printLine("Location: [" + hero.getX() + ", " + hero.getY() + "]");
		printLine("Level: " + hero.getLevel() + " Experience: " + hero.getXp());	
		printLine(
			"Attack: " + hero.getAtk() +
		 	" Defence: " + hero.getDef() +
			" Health: " + hero.getHp());
		printLine(
			"Helm: " + hero.getItemArtifact("helm").name + 
			" Weapon: " + hero.getItemArtifact("weapon").name + 
			" Armour: " + hero.getItemArtifact("armour").name);
	} 

	@Override
	public void load(){
		printLine("Game Loaded");
	}

	@Override	
	public void invalid(String input){
		printErrorLable("'" + input + "' <-you cant say that Sir they wont know what you mean");
	}
	
	private void printErrorLable(String input){
		errorLable.setText(input);
	}

	@Override
	public JButton getButton() {
		inputTextBox.requestFocus();
		return btnSendMessage;
	}
	@Override
	public JTextField getInput() {
		return inputTextBox;
	}

	@Override
	public void printLevelUp(Hero hero) {
		printSpacer();
		printLine("!!! Level UP !!!");
		printLine(hero.getName() + " went from " + (hero.getLevel() - 1) + " - " + hero.getLevel());
		printSpacer();
	}
}
