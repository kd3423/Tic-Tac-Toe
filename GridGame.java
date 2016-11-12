import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

class GridGame{
	private String[][] a=new String[3][3];;
	private int i,j,screenWidth,screenHeight;
	
	public int flagb = 0, flaga = 0 , flag=0,check,refX = -1,resX=-1,resY=-1,fflag,aiX,aiY,cpuX,cpuY,flagtext = 1;
	public static JFrame mainFrame = new JFrame();
	public static JPanel mainPanel = new JPanel();
	public Grid objInner = new Grid();
	public GameOption objGameOption = new GameOption();
	public Timer timeObj;

	public GridGame()
	{
		newArray();
		// Selecting Frame size depending on the display screen resolution
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		screenHeight = screenSize.height;
		screenWidth = screenSize.width;
		
		mainFrame.setSize(screenWidth/3, screenHeight/2);
		// making frame to come in center of display screen
		mainFrame.setTitle("Tic-Tac-Toe");

		// Setting image for Icon
		Image img = new ImageIcon("imgIcon.png").getImage();
		mainFrame.setIconImage(img);
		mainPanel.setLayout(null);
		JLabel mainString = new JLabel("Tic-Tac-Toe");
		mainPanel.add(mainString);
		mainString.setBounds(screenWidth/8,screenHeight/6,screenWidth/10,screenHeight/40);
		mainString.setFont(new Font("Serif", Font.BOLD, 20));
		mainString.setForeground(Color.RED);
		addButton("Start Game");
		addButton("Exit");
		mainFrame.add(mainPanel);
		mainFrame.setBackground(Color.BLACK);
		mainPanel.setBackground(Color.BLACK);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
	
	}

	public void addButton(String bName){
		JButton button = new JButton(bName);
		mainPanel.setLayout(null);
		if(flagb == 0){
			button.setBounds(screenWidth/8,screenHeight/4,screenWidth/15,screenHeight/40);
			flagb = 1;
		}
		else if(flagb == 1){
			button.setBounds(screenWidth/8,screenHeight/4 + screenHeight/20,screenWidth/15,screenHeight/40);	
		}
		button.setBackground(Color.BLACK);
		button.setForeground(Color.RED);
		button.setFocusPainted(false);
		mainPanel.add(button);

		if(flaga == 1){
			button.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent event)
				{
						System.exit(0);

				}
			});	
		}
		else if(flaga == 0){
			button.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent event)
				{	
					//add grid and start the game
					// Grid objInner = new Grid();
					JPanel startPanel = (JPanel) objInner.getPanel();
					
					// GameOption objGameOption = new GameOption();
					JPanel selectGame = (JPanel) objGameOption.getGameOption();
					JPanel textPanel = (JPanel) objGameOption.getTextPanel();
					mainPanel.removeAll();
					mainPanel.add(selectGame);
					mainPanel.add(startPanel);
					mainPanel.add(textPanel);
					mainPanel.revalidate();
					mainFrame.repaint();					
					selectGame.setBackground(Color.BLACK);
					startPanel.setBounds(screenWidth/8, screenHeight/15,screenWidth/5,screenHeight/4);
					selectGame.setBounds(screenWidth/150, screenHeight/60,screenWidth/10,screenHeight/3);
					textPanel.setBounds(screenWidth/8, screenHeight/3,screenWidth/5,screenHeight/15);
					textPanel.setBackground(Color.BLACK);
					textPanel.setForeground(Color.RED);
				}
				
			});
			flaga = 1;
		}

	}
	public void newArray(){
		for(i=0;i<3;i++){
			for(j=0;j<3;j++){
				a[i][j]="-";
			}
		}
	}
	public void put(int i,int j,String c)
	{
		a[i][j]=c;
	}

	public void print()
	{
		for(i=0;i<3;i++){
			for(j=0;j<3;j++){
				System.out.print(a[i][j]);
			}
			System.out.println();
		}
	}

	public String[][] get()
	{
		String[][] aa=new String[3][3];
		for(i=0;i<3;i++){
			for(j=0;j<3;j++){
				aa[i][j]=a[i][j];
			}
		}
		return aa;
	}

	public int checkState(String c1,String c2)
	{
		String ch;
		int flag =0;
		for(i=0;i<3;i++)
		{
			ch=a[0][i];
			flag=1;

			for(j=1;j<3;j++)
				if(a[j][i]=="-" || a[j][i]!=ch)
				{
					flag=0;
					break;
				}

			if(flag==1)
			{
				if(c1 == ch){	
					return 1;
				}
				else{
					return 2;
				}
			}
		}

		ch=a[0][0];
		flag=1;

		for(i=1;i<3;i++){
			if(a[i][i]=="-" || a[i][i]!=ch){ 
				flag=0;
				break;
			}
		}

		if(flag == 1)
		{
			if(c1 == ch){
				return 1;
			}
			else{
				return 2;
			}
		}

		for(i=0;i<3;i++)
		{
			ch=a[i][0];
			flag=1;

			for(j=1;j<3;j++)
				if(a[i][j]=="-" || a[i][j]!=ch)
				{
					flag=0;
					break;
				}

			if(flag == 1)
			{
				if(c1==ch){
					return 1;
				}
				else{
					return 2;
				}
			}
		}

		ch=a[0][2];
		flag=1;

		for(i=1;i<3;i++){
			if(a[i][2-i]=="-" || a[i][2-i]!=ch){ 
				flag=0;
				break;
			}
		}

		if(flag == 1)
		{
			if(c1 == ch){
				return 1;
			}
			else{
				return 2;
			}
		}

		flag=1;
		for(i=0;i<3;i++){
			for(j=0;j<3;j++){
				if(a[i][j]=="-")
				{
					flag=0;
					break;
				}
			}
		}
		if(flag==1){
			return 3;
		}

		else{
			return 0;
		}
	}

	

	public class Grid{
		public JPanel gridpanel= new JPanel();
		public JButton[][] buttonGrid = new JButton[3][3];
		public int flagGrid=0;
		public ActionListener action;
		public int x = 0,y=0;
		public Grid(){
			// JFrame  frame = new JFrame();
			gridpanel.setLayout(new GridLayout(3,3));
			// if(flagtext == 0){
				ActionListener buttonList = new ActionListener(){
					public void actionPerformed(ActionEvent event){
						if(fflag == 0 && !(objGameOption.getUser1().isEmpty()) && !(objGameOption.getUser2().isEmpty())){
							// cleanGrid();
							JButton pressedButton =(JButton) event.getSource();
							
							for(x = 0;x<buttonGrid.length;x++){
								for(y=0;y<buttonGrid[x].length;y++){
									if(buttonGrid[x][y] == pressedButton){
										setresX(x);
										setresY(y);
										if(flagGrid == 0 && buttonGrid[x][y].getText().isEmpty()){
											objGameOption.setTextForStat(objGameOption.getUser1() + " play");
											buttonGrid[x][y].setText("X");
											userMakemove(x,y,"X");
											buttonGrid[x][y].setFocusPainted(false);
											int check = checkState("X","O");
											if(check == 1 || check == 2 || check == 3)
											{	
												if(check == 1)
												{	
													print();
													System.out.println();
													System.out.println("X Wins");
													objGameOption.setTextForStat(objGameOption.getUser1() + " Wins !!!");
													objGameOption.setUser1("");
													objGameOption.setUser2("");
												}
												else if(check == 2)
												{
													print();
													System.out.println();
													System.out.println("O Wins");
													objGameOption.setTextForStat(objGameOption.getUser2() + " Wins !!!");
													objGameOption.setUser1("");
													objGameOption.setUser2("");
												}
												else if(check == 3)
												{
													print();
													System.out.println();
													System.out.println("Tie");
													objGameOption.setTextForStat("Tie between " + objGameOption.getUser1()+ " " + objGameOption.getUser2());
													objGameOption.setUser1("");
													objGameOption.setUser2("");
												}
											}
											flagGrid = 1;
										}
										else if(flagGrid == 1 && buttonGrid[x][y].getText().isEmpty()){
											objGameOption.setTextForStat(objGameOption.getUser2() + " play");
											buttonGrid[x][y].setText("O");
											userMakemove(x,y,"O");
											buttonGrid[x][y].setFocusPainted(false);
											int check = checkState("X","O");
											if(check == 1 || check == 2 || check == 3)
											{	
												if(check == 1)
												{	
													print();
													System.out.println();
													System.out.println("X Wins");
													objGameOption.setTextForStat(objGameOption.getUser1() + " Wins !!!");
													objGameOption.setUser1("");
													objGameOption.setUser2("");
												}
												else if(check == 2)
												{
													print();
													System.out.println();
													System.out.println("O Wins");
													objGameOption.setTextForStat(objGameOption.getUser2() + " Wins !!!");
													objGameOption.setUser1("");
													objGameOption.setUser2("");
												}
												else if(check == 3)
												{
													print();
													System.out.println();
													System.out.println("Tie");
													objGameOption.setTextForStat("Tie between " + objGameOption.getUser1()+ " " + objGameOption.getUser2());
													objGameOption.setUser1("");
													objGameOption.setUser2("");
												}
											}
											flagGrid = 0;
										}
									}
								}
							}
						}
						if(fflag == 1 && !(objGameOption.getUser1().isEmpty())){
							// cleanGrid();
							JButton pressedButton =(JButton) event.getSource();
							objGameOption.setTextForStat(objGameOption.getUser1() + "play");
							for(x = 0;x<buttonGrid.length;x++){
								for(y=0;y<buttonGrid[x].length;y++){
									if(buttonGrid[x][y] == pressedButton){
										setresX(x);
										setresY(y);
										if(flagGrid == 0 && buttonGrid[x][y].getText().isEmpty()){
											buttonGrid[x][y].setText("X");
											userMakemove(x,y,"X");
											buttonGrid[x][y].setFocusPainted(false);
											int check = checkState("X","O");
											if(check == 1 || check == 2 || check == 3)
											{	
												if(check == 1)
												{	
													print();
													System.out.println();
													System.out.println("X Wins");
													String var = objGameOption.getUser1();
													// System.out.println(var);
													objGameOption.setTextForStat(var + "Wins !!!");
													// objGameOption.setUser1("");
												}
												else if(check == 2)
												{
													print();
													System.out.println();
													System.out.println("O Wins");
													objGameOption.setTextForStat("CPU Wins !!!");
													objGameOption.setUser1("");
												}
												else if(check == 3)
												{
													print();
													System.out.println();
													System.out.println("Tie");
													objGameOption.setTextForStat("Tie between " + objGameOption.getUser1() + " CPU");
													// objGameOption.setUser1("");
												}
											}
											flagGrid = 1;
										}
										if(flagGrid == 1){
											objGameOption.setTextForStat("Cpu play");
											cpuMakemove("O");
											int xx = getCpumakemoveX();
											int yy = getCpumakemoveY();
											buttonGrid[xx][yy].setText("O");
											int check = checkState("X","O");
											if(check == 1 || check == 2 || check == 3)
											{	
												if(check == 1)
												{	
													print();
													System.out.println();
													System.out.println("X Wins");
													objGameOption.setTextForStat(objGameOption.getUser1() + "Wins !!!");
													objGameOption.setUser1("");
												}
												else if(check == 2)
												{
													print();
													System.out.println();
													System.out.println("O Wins");
													objGameOption.setTextForStat("CPU Wins !!!");
													objGameOption.setUser1("");
												}
												else if(check == 3)
												{
													print();
													System.out.println();
													System.out.println("Tie");
													objGameOption.setTextForStat("Tie between " + objGameOption.getUser1() + " CPU");
													objGameOption.setUser1("");
												}
											}
											flagGrid = 0;
										}
									}
								}
							}

						}

						if(fflag == 3 && !(objGameOption.getUser1().isEmpty())){
							// cleanGrid();
							JButton pressedButton =(JButton) event.getSource();
							objGameOption.setTextForStat(objGameOption.getUser1() + "play");
							for(x = 0;x<buttonGrid.length;x++){
								for(y=0;y<buttonGrid[x].length;y++){
									if(buttonGrid[x][y] == pressedButton){
										setresX(x);
										setresY(y);
										if(flagGrid == 0 && buttonGrid[x][y].getText().isEmpty()){
											buttonGrid[x][y].setText("X");
											userMakemove(x,y,"X");
											buttonGrid[x][y].setFocusPainted(false);
											int check = checkState("X","O");
											if(check == 1 || check == 2 || check == 3)
											{	
												if(check == 1)
												{	
													print();
													System.out.println();
													System.out.println("X Wins");
													objGameOption.setTextForStat(objGameOption.getUser1() + " Wins !!!");
													// objGameOption.setUser1("");
												}
												else if(check == 2)
												{
													print();
													System.out.println();
													System.out.println("O Wins");
													objGameOption.setTextForStat("AI Wins !!!");
													objGameOption.setUser1("");
												}
												else if(check == 3)
												{
													print();
													System.out.println();
													System.out.println("Tie");
													objGameOption.setTextForStat("Tie between AI");
													objGameOption.setUser1("");
												}
											}
											flagGrid = 1;
										}
										if(flagGrid == 1){
											objGameOption.setTextForStat("AI play");
											int z = aiMakemove("O");
											int xx = getAimakemoveX();
											int yy = getAimakemoveY();
											setAimove();
											// buttonGrid[xx][yy].setText("O");
											int check = checkState("X","O");
											if(check == 1 || check == 2 || check == 3)
											{	
												if(check == 1)
												{	
													print();
													System.out.println();
													System.out.println("X Wins");
													objGameOption.setTextForStat(objGameOption.getUser1() + " Wins !!!");
													objGameOption.setUser1("");
												}
												else if(check == 2)
												{
													print();
													System.out.println();
													System.out.println("O Wins");
													objGameOption.setTextForStat("AI Wins !!!");
													objGameOption.setUser1("");
												}
												else if(check == 3)
												{
													print();
													System.out.println();
													System.out.println("Tie");
													objGameOption.setTextForStat("Tie with AI");
													objGameOption.setUser1("");
												}
											}
											flagGrid = 0;
										}
									}
								}
							}

						}
					}

				};
				for(x=0;x<buttonGrid.length;x++){
					for(y=0;y<buttonGrid[x].length;y++){
						buttonGrid[x][y] = new JButton();
						buttonGrid[x][y].addActionListener(buttonList);
						setAction(buttonList);
						gridpanel.setBackground(Color.BLACK);
         				buttonGrid[x][y].setFont(new Font("Serif", Font.PLAIN, 20));
         				buttonGrid[x][y].setBackground(Color.BLACK);
         				buttonGrid[x][y].setForeground(Color.WHITE);
					}
				}
				cleanGrid(buttonList);
		}
		public void cleanGrid(ActionListener buttonList){
			for(x=0;x<buttonGrid.length;x++){
				for(y=0;y<buttonGrid[x].length;y++){
					buttonGrid[x][y].setText("");
					buttonGrid[x][y].addActionListener(buttonList);
            		gridpanel.add(buttonGrid[x][y]);
            		gridpanel.setBackground(Color.BLACK);
         			buttonGrid[x][y].setFont(new Font("Serif", Font.PLAIN, 20));
         			buttonGrid[x][y].setBackground(Color.BLACK);
         			buttonGrid[x][y].setForeground(Color.WHITE);
            		flagGrid = 0;
				}
			}
			newArray();
		}

		public void setAction(ActionListener buttonList){
			action = buttonList;
		}

		public ActionListener getAction(){
			return action;
		}

		public void setresX(int x){
			resX = x;
		}
		public void setresY(int y){
			resY = y;
		}
		public void aiCpuGame(){
			if(fflag == 2 )
			{	
				if(flagGrid == 0){
					objGameOption.setTextForStat("CPU play");
					cpuMakemove("X");
					int xx = getCpumakemoveX();
					int yy = getCpumakemoveY();
					buttonGrid[xx][yy].setText("X");
					int check = checkState("X","O");
					if(check == 1 || check == 2 || check == 3)
					{	
						if(check == 1)
						{	
							print();
							System.out.println();
							System.out.println("X Wins");
							objGameOption.setTextForStat("CPU Wins !!!");
							flagGrid = 0;
							timeObj.cancel();
						}
						else if(check == 2)
						{
							print();
							System.out.println();
							System.out.println("O Wins");
							objGameOption.setTextForStat("AI Wins !!!");
							flagGrid =0;
							timeObj.cancel();
						}
						else if(check == 3)
						{
							print();
							System.out.println();
							System.out.println("Tie");
							objGameOption.setTextForStat("Tie between  CPU and AI");
							flagGrid =0;
							timeObj.cancel();
						}							
					}
					flagGrid = 1;
				}
				else if(flagGrid == 1){
					objGameOption.setTextForStat("AI play");
					int z = aiMakemove("O");
					setAimove();
					int xx = getAimakemoveX();
					int yy = getAimakemoveY();
					int check = checkState("X","O");
					if(check == 1 || check == 2 || check == 3)
					{	
						if(check == 1)
						{	
							print();
							System.out.println();
							System.out.println("X Wins");
							objGameOption.setTextForStat("CPU Wins !!!");
							flagGrid =0;
							timeObj.cancel();
						}
						else if(check == 2)
						{
							print();
							System.out.println();
							System.out.println("O Wins");
							objGameOption.setTextForStat("AI Wins !!!");
							flagGrid =0;
							timeObj.cancel();
						}
						else if(check == 3)
						{
							print();
							System.out.println();
							System.out.println("Tie");
							objGameOption.setTextForStat("Tie between  CPU and AI");
							flagGrid =0;
							timeObj.cancel();
						}
					}
					flagGrid = 0;
				}
			}
		}
		public void setAimove(){
			int i =0,j=0;
			for(i =0 ; i<3;i++){
				for(j=0; j<3; j++){
					if(a[i][j].equals("O")){
						buttonGrid[i][j].setText("O");
					}
				}
			}			
		}
		
		public JPanel getPanel(){
			return gridpanel;
		}
		public JButton[][] getbuttonGrid(){
			return buttonGrid;
		}
	}

	public class GameOption{
		public JPanel gameOption= new JPanel();
		public JPanel textForStat = new JPanel();
		public JLabel showText = new JLabel("Select a Game Type !");
		public int x;
		public String userName1,userName2;
		public JButton [] gameButton = new JButton[5];
		public GameOption(){
			gameOption.setLayout(null);
			showText.setFont(new Font("Serif", Font.PLAIN, 20));
			showText.setForeground(Color.RED);
			ActionListener gameButtonAction = new ActionListener(){
				public void actionPerformed(ActionEvent event){
					JButton pressedgameButton =(JButton) event.getSource();
					for(x=0;x<gameButton.length;x++){
					
						if(gameButton[x] == pressedgameButton && x == 1){
							setTextForStat("User -VS- CPU type selected");
							flagtext = 0;
							objInner.cleanGrid(objInner.getAction());
							setOption(1);
							gameButton[0].setForeground(Color.WHITE);
							gameButton[2].setForeground(Color.WHITE);
							gameButton[3].setForeground(Color.WHITE);
							gameButton[x].setForeground(Color.RED);
							JFrame userName = new JFrame("Enter User Name");
							userName.setLayout(null);
							userName.setSize(400,250);
							userName.setLocationRelativeTo(null);
							userName.setResizable(false);
							
							JTextField inputUser1 = new JTextField(10);
							JPanel inputUser1Panel = new JPanel();
							inputUser1Panel.setLayout(null);
							
							JLabel user1 = new JLabel("User");
							JLabel wrongInp = new JLabel("Enter a User Name !");
							inputUser1Panel.add(inputUser1);
							inputUser1.setBounds(5,3,190,25);
							userName.add(inputUser1Panel);
							inputUser1Panel.setBorder(BorderFactory.createLineBorder(Color.black));
							inputUser1Panel.setBounds(150,20,200,30);
							userName.add(user1);
							user1.setBounds(20,25,200,20);
							user1.setFont(new Font("Serif", Font.PLAIN, 15));

							JButton oK = new JButton("OK");
							oK.addActionListener(new ActionListener()
							{
								public void actionPerformed(ActionEvent event)
								{
										if(inputUser1.getText().isEmpty()){
											userName.add(wrongInp);
											wrongInp.setBounds(150,90,200,30);
										}
										else{
											String s1 = inputUser1.getText();
											setUser1(s1);
											userName.dispose();
										}

								}
							});

							userName.add(oK);
							oK.setBackground(Color.BLACK);
							oK.setForeground(Color.RED);
							userName.setBackground(Color.BLACK);
							oK.setBounds(200,150,60,30);
							userName.setVisible(true);
							fflag =1;
						}

						else if(gameButton[x] == pressedgameButton && x == 0){
							setTextForStat("User1 -VS- User2 Game type selected");
							flagtext = 0;
							objInner.cleanGrid(objInner.getAction());
							setOption(x);
							gameButton[1].setForeground(Color.WHITE);
							gameButton[2].setForeground(Color.WHITE);
							gameButton[3].setForeground(Color.WHITE);
							gameButton[x].setForeground(Color.RED);

							JFrame userName = new JFrame("Enter User Name");
							userName.setLayout(null);
							userName.setSize(400,250);
							userName.setLocationRelativeTo(null);
							userName.setResizable(false);
							
							JTextField inputUser1 = new JTextField(10);
							JPanel inputUser1Panel = new JPanel();
							inputUser1Panel.setLayout(null);
							
							JTextField inputUser2 = new JTextField(10);
							JPanel inputUser2Panel = new JPanel();
							inputUser2Panel.setLayout(null);

							JLabel user1 = new JLabel("User 1");
							JLabel user2 = new JLabel("User 2");
							JLabel wrongInp = new JLabel("Enter a User Name !");
							inputUser1Panel.add(inputUser1);
							inputUser1.setBounds(5,3,190,25);
							userName.add(inputUser1Panel);
							inputUser1Panel.setBorder(BorderFactory.createLineBorder(Color.black));
							inputUser1Panel.setBounds(150,20,200,30);
							userName.add(user1);
							user1.setBounds(20,25,200,20);
							user1.setFont(new Font("Serif", Font.PLAIN, 15));

							inputUser2Panel.add(inputUser2);
							inputUser2.setBounds(5,3,190,25);
							userName.add(inputUser2Panel);
							inputUser2Panel.setBorder(BorderFactory.createLineBorder(Color.black));
							inputUser2Panel.setBounds(150,90,200,30);
							userName.add(user2);
							user2.setBounds(20,95,200,20);
							user2.setFont(new Font("Serif", Font.PLAIN, 15));


							JButton oK = new JButton("OK");
							oK.addActionListener(new ActionListener()
							{
								public void actionPerformed(ActionEvent event)
								{
										if(inputUser1.getText().isEmpty() || inputUser2.getText().isEmpty()){
											userName.add(wrongInp);
											wrongInp.setBounds(150,120,200,30);
										}
										else{
											String s1 = inputUser1.getText();
											String s2 = inputUser2.getText();
											setUser1(s1);
											setUser2(s2);
											userName.dispose();
										}

								}
							});

							userName.add(oK);
							oK.setBackground(Color.BLACK);
							oK.setForeground(Color.RED);
							userName.setBackground(Color.BLACK);
							oK.setBounds(200,150,60,30);
							userName.setVisible(true);
							fflag =0;
						}

						else if(gameButton[x] == pressedgameButton && x == 2){
							setTextForStat("CPU -VS- AI Bot type selected");
							flagtext = 0;
							objInner.cleanGrid(objInner.getAction());
							setOption(x);
							gameButton[1].setForeground(Color.WHITE);
							gameButton[0].setForeground(Color.WHITE);
							gameButton[3].setForeground(Color.WHITE);
							gameButton[x].setForeground(Color.RED);
							fflag =2;
							timeObj = new Timer();
							timeObj.scheduleAtFixedRate(new TimerTask(){
								public void run(){
									objInner.aiCpuGame();
								}

							},1000,1000);
						}

						else if(gameButton[x] == pressedgameButton && x == 3){
							setTextForStat("User -VS- AI Bot type selected");
							flagtext = 0;
							objInner.cleanGrid(objInner.getAction());
							setOption(x);
							gameButton[1].setForeground(Color.WHITE);
							gameButton[2].setForeground(Color.WHITE);
							gameButton[0].setForeground(Color.WHITE);
							gameButton[x].setForeground(Color.RED);

							JFrame userName = new JFrame("Enter User Name");
							userName.setLayout(null);
							userName.setSize(400,250);
							userName.setLocationRelativeTo(null);
							userName.setResizable(false);
							
							JTextField inputUser1 = new JTextField(10);
							JPanel inputUser1Panel = new JPanel();
							inputUser1Panel.setLayout(null);
							
							JLabel user1 = new JLabel("User");
							JLabel wrongInp = new JLabel("Enter a User Name !");
							inputUser1Panel.add(inputUser1);
							inputUser1.setBounds(5,3,190,25);
							userName.add(inputUser1Panel);
							inputUser1Panel.setBorder(BorderFactory.createLineBorder(Color.black));
							inputUser1Panel.setBounds(150,20,200,30);
							userName.add(user1);
							user1.setBounds(20,25,200,20);
							user1.setFont(new Font("Serif", Font.PLAIN, 15));

							JButton oK = new JButton("OK");
							oK.addActionListener(new ActionListener()
							{
								public void actionPerformed(ActionEvent event)
								{
										if(inputUser1.getText().isEmpty()){
											userName.add(wrongInp);
											wrongInp.setBounds(150,90,200,30);
										}
										else{
											String s1 = inputUser1.getText();
											setUser1(s1);
											userName.dispose();
										}

								}
							});

							userName.add(oK);
							oK.setBackground(Color.BLACK);
							oK.setForeground(Color.RED);
							userName.setBackground(Color.BLACK);
							oK.setBounds(200,150,60,30);
							userName.setVisible(true);
							fflag =3;
						}

						else if(gameButton[x] == pressedgameButton && x == 4){
							System.exit(0);
						}
					}
				}
			};
			for(x =0;x<gameButton.length;x++){
				if(x == 0){
					gameButton[x] = new JButton("User1 -VS- User2");
					gameButton[x].addActionListener(gameButtonAction);
					gameOption.add(gameButton[x]);
					gameButton[x].setBounds(5,50,170,30);
					gameButton[x].setFocusPainted(false);
					gameButton[x].setBackground(Color.BLACK);
					gameButton[x].setForeground(Color.WHITE);

				}
				else if(x == 1){
					gameButton[x] = new JButton("  User -VS- Cpu   ");
					gameButton[x].addActionListener(gameButtonAction);
					gameOption.add(gameButton[x]);
					gameButton[x].setBounds(5,120,170,30);
					gameButton[x].setFocusPainted(false);
					gameButton[x].setBackground(Color.BLACK);
					gameButton[x].setForeground(Color.WHITE);


				}
				else if(x == 2){
					gameButton[x] = new JButton("  Cpu -VS- AI Bot  ");
					gameButton[x].addActionListener(gameButtonAction);
					gameOption.add(gameButton[x]);
					gameButton[x].setBounds(5,190,170,30);
					gameButton[x].setFocusPainted(false);
					gameButton[x].setBackground(Color.BLACK);
					gameButton[x].setForeground(Color.WHITE);

	
				}
				else if(x == 3){
					gameButton[x] = new JButton(" User -VS- AI Bot ");
					gameButton[x].addActionListener(gameButtonAction);
					gameOption.add(gameButton[x]);
					gameButton[x].setBounds(5,260,170,30);
					gameButton[x].setFocusPainted(false);
					gameButton[x].setBackground(Color.BLACK);
					gameButton[x].setForeground(Color.WHITE);


				}
				else if(x ==4){
					gameButton[x] = new JButton("   Exit   ");
					gameButton[x].addActionListener(gameButtonAction);
					gameOption.add(gameButton[x]);
					gameButton[x].setBounds(5,330,170,30);
					gameButton[x].setFocusPainted(false);
					gameButton[x].setBackground(Color.BLACK);
					gameButton[x].setForeground(Color.WHITE);

				}
			}

			textForStat.add(showText);
			textForStat.setBackground(Color.BLACK);
			textForStat.setForeground(Color.RED);
			textForStat.setBorder(BorderFactory.createLineBorder(Color.black));

		}
		public void setOption(int x){
			refX = x;
		}
		
		public JPanel getGameOption(){
			return gameOption;
		}
		public String getUser1(){
			return userName1;
		}
		public String getUser2(){
			return	userName2;
		}
		public void setUser1(String s){
			userName1 = s;
		}
		public void setUser2(String s){
			userName2 = s;
		}
		public JPanel getTextPanel(){
			return textForStat;
		}
		public JLabel getLabelForStat(){
			return showText;
		}
		public void setTextForStat(String s){
			showText.setText(s);
			showText.setForeground(Color.RED);
		}

	}

	public void userMakemove(int x, int y,String c){
		put(x,y,c);
	}

	public void cpuMakemove(String c){
		
		int x1,x,y,i,j,k=0,l=0;
		int arri[] = new int [9];
		int arrj[] = new int [9];
		Random rand = new Random();
		String arr [][] = new String [3][3];
		arr = get();
		for(i=0;i<3;i++){
			for(j=0;j<3;j++){
				if(arr[i][j] == "-"){
					arri[k] = i;
					arrj[l] = j;
					k++;
					l++;
				}
			}
		}
		if(k != 0){
			x1 = rand.nextInt(k);
			x = arri[x1];
			y = arrj[x1];
			put(x,y,c);
			setCpuX(x);
			setCpuY(y);
		}
	}

	public int aiMakemove(String c){
		String arr[][] = new String [3][3];
		String ch;
		int i,j,count=0,x,y;
		arr = get();

		if(c == "X")
		{
			String cc = "O";
			// let ai win
			// check row wise for ai move
			for(i=0;i<3;i++){
				ch = arr[i][0];
				if(ch == c){

					for(j=1;j<3;j++){
						if(j<2 && arr[i][j] == ch && arr[i][j+1] == "-")
						{
							put(i,j+1,c);
							setAiX(i);
							setAiY(j+1);
							return 0;
						}
						else if( j<2 &&ch == arr[i][j+1] && arr[i][j] == "-")
						{
							put(i,j,c);
							setAiX(i);
							setAiY(j);
							return 0;
						}
					
					}

				}
				else if(ch == "-"){
					for(j=1;j<3;j++){
						if(j<2 && arr[i][j] == c && arr[i][j+1] == c)
						{
							put(i,0,c);
							setAiX(i);
							setAiY(0);
							return 0;
						}
					}
				}

			}

			// check column wise for ai move
			for(i=0;i<3;i++){
				ch = arr[0][i];
				if(ch == c)
				{
					for(j=1;j<2;j++){
						if(j<2 && arr[j][i] == ch && arr[j+1][i] == "-")
						{
							put(j+1,i,c);
							setAiX(j+1);
							setAiY(i);
							return 0;
						}
						else if(j<2 && arr[j][i] == "-" && arr[j+1][i] == ch)
						{
							put(j,i,c);
							setAiX(j);
							setAiY(i);
							return 0;
						}
					}
				}
				else if(ch == "-")
				{
					for(j=1;j<3;j++){
						if(j<2 && arr[j][i] == c && arr[j+1][i] == c)
						{
							put(0,i,c);
							setAiX(0);
							setAiY(i);
							return 0;
						}
					}
				}
			}

			// check diagonal left to  right
			for(i=1;i<3;i++){
				ch = arr[0][0];
				if(ch == c)
				{
					if(i<2 && arr[i][i] == ch && arr[i+1][i+1] == "-")
					{
						put(i+1,i+1,c);
						setAiX(i+1);
						setAiY(i+1);
						return 0;
					}
					else if(i<2 && arr[i][i] == "-" && arr[i+1][i+1] == ch)
					{
						put(i,i,c);
						setAiX(i);
						setAiY(i);
						return 0;
					}
				}
				else if (ch == "-")
				{
					if(i<2 && arr[i][i] == c && arr[i+1][i+1] == c)
					{
						put(0,0,c);
						setAiX(0);
						setAiY(0);	
						return 0;
					}
				}
			}

			// check diagonal right to left
			for(i=1;i<3;i++){
				ch = arr[0][2];
				if(ch == c)
				{
					if(i<2 && arr[i][2-i] == ch && arr[i+1][1-i] == "-")
					{
						put(i+1,1-i,c);
						setAiX(i+1);
						setAiY(1-i);
						return 0;
					}
					else if(i<2 && arr[i][2-i] == "-" && arr[i+1][1-i] == ch)
					{
						put(i,2-i,c);
						setAiX(i);
						setAiY(2-i);
						return 0;
					}
				}
				else if(ch == "-")
				{
					if(i<2 && arr[i][2-i] == c && arr[i+1][1-i] == c)
					{
						put(0,2,c);
						setAiX(0);
						setAiY(2);
						return 0;
					}
				}
			}

			// not let O win
			//  check row wise for ai move
			for(i=0;i<3;i++){
				ch = arr[i][0];
				if(ch == cc){
					
					for(j=1;j<3;j++){
						if(j<2 && arr[i][j] == ch && arr[i][j+1] == "-")
						{
							put(i,j+1,c);
							setAiX(i);
							setAiY(j+1);
							return 1;
						}
						else if( j<2 &&ch == arr[i][j+1] && arr[i][j] == "-")
						{
							put(i,j,c);
							setAiX(i);
							setAiY(j);
							return 1;
						}
					
					}
				}
				else if(ch == "-"){
					for(j=1;j<3;j++){
						if(j<2 && arr[i][j] == cc && arr[i][j+1] == cc)
						{
							put(i,0,c);
							setAiX(i);
							setAiY(0);
							return 1;
						}
					}
				}
			}

			// check column wise for ai move

			for(i=0;i<3;i++){
				ch = arr[0][i];
				if(ch == cc)
				{
					for(j=1;j<2;j++){
						if(j<2 && arr[j][i] == ch && arr[j+1][i] == "-")
						{
							put(j+1,i,c);
							setAiX(j+1);
							setAiY(i);
							return 1;
						}
						else if(j<2 && arr[j][i] == "-" && arr[j+1][i] == ch)
						{
							put(j,i,c);
							setAiX(j);
							setAiY(i);
							return 1;
						}
					}
				}
				else if(ch == "-")
				{
					for(j=1;j<3;j++){
						if(j<2 && arr[j][i] == cc && arr[j+1][i] == cc)
						{
							put(0,i,c);
							setAiX(0);
							setAiY(i);
							return 1;
						}
					}
				}
			}

			// check diagonal left to  right
			for(i=1;i<3;i++){
				ch = arr[0][0];
				if(ch == cc)
				{
					if(i<2 && arr[i][i] == ch && arr[i+1][i+1] == "-")
					{
						put(i+1,i+1,c);
						setAiX(i+1);
						setAiY(i+1);
						return 1;
					}
					else if(i<2 && arr[i][i] == "-" && arr[i+1][i+1] == ch)
					{
						put(i,i,c);
						setAiX(i);
						setAiY(i);
						return 1;
					}
				}
				else if (ch == "-")
				{
					if(i<2 && arr[i][i] == cc && arr[i+1][i+1] == cc)
					{
						put(0,0,c);
						setAiX(0);
						setAiY(0);
						return 1;
					}
				}
			}

			// check diagonal right to left
			for(i=1;i<3;i++){
				ch = arr[0][2];
				if(ch == cc)
				{
					if(i<2 && arr[i][2-i] == ch && arr[i+1][1-i] == "-")
					{
						put(i+1,1-i,c);
						setAiX(i+1);
						setAiY(1-i);
						return 1;
					}
					else if(i<2 && arr[i][2-i] == "-" && arr[i+1][1-i] == ch)
					{
						put(i,2-i,c);
						setAiX(i);
						setAiY(2-i);
						return 1;
					}
				}
				else if(ch == "-")
				{
					if(i<2 && arr[i][2-i] == cc && arr[i+1][1-i] == cc)
					{
						put(0,2,c);
						setAiX(0);
						setAiY(2);
						return 1;
					}
				}
			}

			// random
			cpuMakemove(c);
			
		}

		if(c == "O")
		{
			String cc = "X";
			// let ai win
			// check row wise for ai move
			for(i=0;i<3;i++){
				ch = arr[i][0];
				if(ch == c){

					for(j=1;j<3;j++){
						if(j<2 && arr[i][j] == ch && arr[i][j+1] == "-")
						{
							put(i,j+1,c);
							setAiX(i);
							setAiY(j+1);
							return 0;
						}
						else if( j<2 &&ch == arr[i][j+1] && arr[i][j] == "-")
						{
							put(i,j,c);
							setAiX(i);
							setAiY(j);
							return 0;
						}
					
					}

				}
				else if(ch == "-"){
					for(j=1;j<3;j++){
						if(j<2 && arr[i][j] == c && arr[i][j+1] == c)
						{
							put(i,0,c);
							setAiX(i);
							setAiY(0);
							return 0;
						}
					}
				}

			}

			// check column wise for ai move
			for(i=0;i<3;i++){
				ch = arr[0][i];
				if(ch == c)
				{
					for(j=1;j<2;j++){
						if(j<2 && arr[j][i] == ch && arr[j+1][i] == "-")
						{
							put(j+1,i,c);
							setAiX(j+1);
							setAiY(i);
							return 0;
						}
						else if(j<2 && arr[j][i] == "-" && arr[j+1][i] == ch)
						{
							put(j,i,c);
							setAiX(j);
							setAiY(i);
							return 0;
						}
					}
				}
				else if(ch == "-")
				{
					for(j=1;j<3;j++){
						if(j<2 && arr[j][i] == c && arr[j+1][i] == c)
						{
							put(0,i,c);
							setAiX(0);
							setAiY(i);
							return 0;
						}
					}
				}
			}

			// check diagonal left to  right
			for(i=1;i<3;i++){
				ch = arr[0][0];
				if(ch == c)
				{
					if(i<2 && arr[i][i] == ch && arr[i+1][i+1] == "-")
					{
						put(i+1,i+1,c);
						setAiX(i+1);
						setAiY(i+1);
						return 0;
					}
					else if(i<2 && arr[i][i] == "-" && arr[i+1][i+1] == ch)
					{
						put(i,i,c);
						setAiX(i);
						setAiY(i);
						return 0;
					}
				}
				else if (ch == "-")
				{
					if(i<2 && arr[i][i] == c && arr[i+1][i+1] == c)
					{
						put(0,0,c);
						setAiX(0);
						setAiY(0);
						return 0;
					}
				}
			}

			// check diagonal right to left
			for(i=1;i<3;i++){
				ch = arr[0][2];
				if(ch == c)
				{
					if(i<2 && arr[i][2-i] == ch && arr[i+1][1-i] == "-")
					{
						put(i+1,1-i,c);
						setAiX(i+1);
						setAiY(1-i);
						return 0;
					}
					else if(i<2 && arr[i][2-i] == "-" && arr[i+1][1-i] == ch)
					{
						put(i,2-i,c);
						setAiX(i);
						setAiY(2-i);
						return 0;
					}
				}
				else if(ch == "-")
				{
					if(i<2 && arr[i][2-i] == c && arr[i+1][1-i] == c)
					{
						put(0,2,c);
						setAiX(0);
						setAiY(2);
						return 0;
					}
				}
			}

			// not let O win
			//  check row wise for ai move
			for(i=0;i<3;i++){
				ch = arr[i][0];
				if(ch == cc){
					
					for(j=1;j<3;j++){
						if(j<2 && arr[i][j] == ch && arr[i][j+1] == "-")
						{
							put(i,j+1,c);
							setAiX(i);
							setAiY(j+1);
							return 1;
						}
						else if( j<2 &&ch == arr[i][j+1] && arr[i][j] == "-")
						{
							put(i,j,c);
							setAiX(i);
							setAiY(j);
							return 1;
						}
					
					}
				}
				else if(ch == "-"){
					for(j=1;j<3;j++){
						if(j<2 && arr[i][j] == cc && arr[i][j+1] == cc)
						{
							put(i,0,c);
							setAiX(i);
							setAiY(0);
							return 1;
						}
					}
				}
			}

			// check column wise for ai move

			for(i=0;i<3;i++){
				ch = arr[0][i];
				if(ch == cc)
				{
					for(j=1;j<2;j++){
						if(j<2 && arr[j][i] == ch && arr[j+1][i] == "-")
						{
							put(j+1,i,c);
							setAiX(j+1);
							setAiY(i);
							return 1;
						}
						else if(j<2 && arr[j][i] == "-" && arr[j+1][i] == ch)
						{
							put(j,i,c);
							setAiX(j);
							setAiY(i);
							return 1;
						}
					}
				}
				else if(ch == "-")
				{
					for(j=1;j<3;j++){
						if(j<2 && arr[j][i] == cc && arr[j+1][i] == cc)
						{
							put(0,i,c);
							setAiX(0);
							setAiY(i);
							return 1;
						}
					}
				}
			}

			// check diagonal left to  right
			for(i=1;i<3;i++){
				ch = arr[0][0];
				if(ch == cc)
				{
					if(i<2 && arr[i][i] == ch && arr[i+1][i+1] == "-")
					{
						put(i+1,i+1,c);
						setAiX(i+1);
						setAiY(i+1);
						return 1;
					}
					else if(i<2 && arr[i][i] == "-" && arr[i+1][i+1] == ch)
					{
						put(i,i,c);
						setAiX(i);
						setAiY(i);
						return 1;
					}
				}
				else if (ch == "-")
				{
					if(i<2 && arr[i][i] == cc && arr[i+1][i+1] == cc)
					{
						put(0,0,c);
						setAiX(0);
						setAiY(0);
						return 1;
					}
				}
			}

			// check diagonal right to left
			for(i=1;i<3;i++){
				ch = arr[0][2];
				if(ch == cc)
				{
					if(i<2 && arr[i][2-i] == ch && arr[i+1][1-i] == "-")
					{
						put(i+1,1-i,c);
						setAiX(i+1);
						setAiY(1-i);
						return 1;
					}
					else if(i<2 && arr[i][2-i] == "-" && arr[i+1][1-i] == ch)
					{
						put(i,2-i,c);
						setAiX(i);
						setAiY(2-i);
						return 1;
					}
				}
				else if(ch == "-")
				{
					if(i<2 && arr[i][2-i] == cc && arr[i+1][1-i] == cc)
					{
						put(0,2,c);
						setAiX(0);
						setAiY(2);
						return 1;
					}
				}
			}

			// random
			cpuMakemove(c);
			
		}
		return 1;

	}

	public void setCpuX(int x){
		cpuX=x;
	}
	public void setCpuY(int y){
		cpuY = y;
	}
	public int getCpumakemoveY(){
		return cpuY;
	}
	public int getCpumakemoveX(){
		return cpuX;
	}
	public void setAiX(int x){
		aiX = x;
	}
	public void setAiY(int y){
		aiY = y;
	}
	public int getAimakemoveX(){
		return aiX;
	}
	public int getAimakemoveY(){
		return aiX;
	}


}