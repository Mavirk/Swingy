package com.wethinkcode.swingy.view;

import com.wethinkcode.swingy.controller.Controller;
import com.wethinkcode.swingy.model.*;
import javax.swing.JFrame;

public class Swingy extends JFrame implements View{
	public  boolean buttonClicked;
	private String input;
	private final Controller controller;
	
	public Swingy(Controller controller) {
		super("swingy");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.controller = controller;
		initComponents();
	}
	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jScrollPane1 = new javax.swing.JScrollPane();
                outputTextArea = new javax.swing.JTextArea();
                inputTextBox = new javax.swing.JTextField();
                btnSendMessgae = new javax.swing.JButton();
                headerLable = new javax.swing.JLabel();
                errorLable = new javax.swing.JLabel();
                filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0));

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                setBackground(new java.awt.Color(204, 214, 228));

                outputTextArea.setColumns(20);
                outputTextArea.setRows(5);
                jScrollPane1.setViewportView(outputTextArea);

                inputTextBox.setName("inputTextField"); // NOI18N

                btnSendMessgae.setText("SEND MESSAGE");
                btnSendMessgae.setActionCommand("");
                btnSendMessgae.setName("btnSendMessage"); // NOI18N
                btnSendMessgae.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                letterSent(evt);
                        }
                });

                headerLable.setText("TEXT BASED ADVENTURE !!!");
                headerLable.setName("lblHeading"); // NOI18N

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(errorLable)
                                                .addGap(477, 477, 477)
                                                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane1)
                                        .addComponent(headerLable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(inputTextBox)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnSendMessgae)))
                                .addContainerGap(131, Short.MAX_VALUE))
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(headerLable, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(inputTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnSendMessgae))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(errorLable)
                                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(25, Short.MAX_VALUE))
                );

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void letterSent(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_letterSent
		buttonClicked = true;	
		swingyClicked();
		controller.swingyClicked(input);
        }//GEN-LAST:event_letterSent

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btnSendMessgae;
        private javax.swing.JLabel errorLable;
        private javax.swing.Box.Filler filler1;
        private javax.swing.JLabel headerLable;
        private javax.swing.JTextField inputTextBox;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JTextArea outputTextArea;
        // End of variables declaration//GEN-END:variables
        private void swingyClicked() {
		if (errorLable.getText().equals("")){errorLable.setText("");}
		try{input = inputTextBox.getText();}
		catch(NullPointerException e){
			invalid(input);
		}
        }                                        
	@Override
	public void printLine(String line){
		outputTextArea.append(line + "\n");
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
		String ANSI_RED = "\u001B[31m";
		String ANSI_RESET = "\u001B[0m";
		
		printLine(" Map Size : " + size + "x" + size);
		for(int j = size - 1; j >= 0; j--) {
			for(int i = 0; i < size; i++) {
				if (i == playerX && j == playerY) {
					System.out.print(ANSI_RED + "|" + i + "," + j + "|" + ANSI_RESET);
				}else {
					System.out.print("|" + i + "," + j + "|");
//                    System.out.print(coordinates[i][j].getSymbol());
				}
			}
			printLine("");
		}
	}
	
	@Override
	public void navigation() {
		printLine("navigatioMenu");
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
		printLine(h.getName()+" attacked "+e.getName()+" with "+h.getAtk()+" damage");
		printLine(e.getName()+" lost "+ (h.getAtk() - e.getDef()) + " hp");
		printLine(e.getName()+" attacked "+h.getName()+" with "+e.getAtk()+" damage");
		printLine(h.getName()+" lost "+(e.getAtk() - h.getDef()) + " hp");
		
		printLine(h.getName()+" has "+h.getHp()+"hp left");
		printLine(e.getName()+" has "+e.getHp()+"hp left");
	}
	
	@Override
	public void fightMenu(Hero h, Enemy e) {
		printLine("fightMenu");
		printLine(h.getName() + " Are you brave enough to fight the " + e.getName() + "?");
//		TODO PRINT HEALTH , atack and def
		printLine("fight");
		printLine("run");
	}
	
	@Override
	public void fightOutcome(Hero h, Enemy e){
		if(e.getHp() <= 0) printLine("The enemy "+e.getName()+" is dead");
		else if(h.getHp() <= 0){
			printLine(h.getName()+" has died");
		}
//	print HEALTH AT`ACK DEF
	}
	
	@Override
	public void loot(Artifact artifact) {
		printLine("lootMenu");
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
		printLine("deathMenu");
		printLine("You were slayed by your enemies");
		printLine("menu");
		printLine("exit");
	}
	@Override
	public void save(){
		printLine("saveGame");
		printLine("Would you like to save your game ??");
		printLine("yes");
		printLine("no");
	}
	@Override
	public void load(){
		printLine("loadGame");
	}
	@Override	
	public void invalid(String input){
		printErrorLable("'" + input + "' <-you cant say that Sir they wont know what you mean");
	}
	private void printErrorLable(String input){
		errorLable.setText(input);
	}
}
