package Mole.Game;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import io.netty.channel.ChannelHandlerContext;

/*public class MoleUI extends JFrame {
	private MolePanel molePanel;
	
	
	public MoleUI() throws IOException, InterruptedException { // Mole UI 창
		setTitle("Mole Game");
		setSize(800, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		molePanel = new MolePanel(); // molePanel 생성

		add(molePanel);
		setVisible(true);
	}
}*/

public class MoleUI extends JPanel {
	//public static void main(String[] args) throws IOException, InterruptedException {
	//	new f();
//	}

	private BufferedImage backImage, humanHud, moleHud, humanInv, moleInv, intHuman, intMole;
	public JLabel counterLabel;
	private JLabel vegcountLabel;
	private Font font1 = new Font("Arial", Font.BOLD, 30);
	private Font font2 = new Font("Arial", Font.BOLD, 15);

	Timer timer;

	MoleThread m1;
	MoleThread m2;
	MoleThread m3;
	MoleThread m4;
	MoleThread m5;
	MoleThread m6;
	MoleThread m7;
	MoleThread m8;
	MoleThread m9;
	private vegetableThread v1;
	private vegetableThread v2;
	private vegetableThread v3;

	private ImageIcon itemteeth = new ImageIcon("img/strongteeth.png");
	private ImageIcon itemtrap = new ImageIcon("img/trapM.png");
	private ImageIcon itemsnakepipe = new ImageIcon("img/Snakepipe.png");
	private JLabel itembox1;
	private JLabel itembox2;

	itemBoxThread i0;
	itemBoxThread i1;
	Human hum;
	// itemBoxThread i2;
	// itemBoxThread i3;

	int second, minute;
	String ddSecond, ddMinute;
	DecimalFormat dFormat = new DecimalFormat("00");
	public static MoleInHumanPerformance moleInHumanPerformance;
	
	public MoleUI(ChannelHandlerContext ctx, String name, int v1Location, int v2Location, int v3Location, int crop1, int crop2, int crop3) {
		try {
			setLayout(null);
			backImage = ImageIO.read(new File("img/Back4.png"));
			humanHud = ImageIO.read(new File("img/humanHud.png"));
			moleHud = ImageIO.read(new File("img/moleHud.png"));
			humanInv = ImageIO.read(new File("img/inventory.png"));
			moleInv = ImageIO.read(new File("img/inventory.png"));
			intHuman = ImageIO.read(new File("img/humanint.png"));
			intMole = ImageIO.read(new File("img/moleint.png"));

			m1 = new MoleThread(ctx, name, 50, 400);
			m2 = new MoleThread(ctx, name, 100, 400);
			m3 = new MoleThread(ctx, name, 150, 400);
			m4 = new MoleThread(ctx, name, 50, 450);
			m5 = new MoleThread(ctx, name, 100, 450);
			m6 = new MoleThread(ctx, name, 150, 450);
			m7 = new MoleThread(ctx, name, 50, 500);
			m8 = new MoleThread(ctx, name, 100, 500);
			m9 = new MoleThread(ctx, name, 150, 500);

			v1 = new vegetableThread(v1Location, crop1);
			v2 = new vegetableThread(v2Location, crop2);
			v3 = new vegetableThread(v3Location, crop3);
			add(v1);
			add(v2);
			add(v3);

			i0 = new itemBoxThread(0);
			i1 = new itemBoxThread(1);
			add(i0);
			add(i1);
			
			moleInHumanPerformance = new MoleInHumanPerformance(200, 225);
			add(moleInHumanPerformance);
			//add(new MoleInHumanPerformance(200, 225));
			// i2 = new itemBoxThread(2);
			// i3 = new itemBoxThread(3);

			// add(v0);
			// add(v1);
			// add(v2);

			counterLabel = new JLabel("");
			counterLabel.setBounds(345, 0, 100, 50);
			counterLabel.setHorizontalAlignment(JLabel.CENTER);
			counterLabel.setFont(font1);

			add(counterLabel);

			counterLabel.setText("03:00");

			second = 0;
			minute = 3;

			normalTimer();
			timer.start();

			vegcountLabel = new JLabel("15");
			vegcountLabel.setBounds(758, 90, 20, 20);
			vegcountLabel.setHorizontalAlignment(JLabel.CENTER);
			vegcountLabel.setFont(font2);

			add(vegcountLabel);

			itembox1 = new JLabel();
			itembox2 = new JLabel();
			itembox1.setBounds(655, 6, 36, 36);
			itembox2.setBounds(696, 6, 36, 36);
			itembox1.setVisible(false);
			add(itembox1);
			add(itembox2);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public vegetableThread getV1() {
		return v1;
	}
	public vegetableThread getV2() {
		return v2;
	}
	public vegetableThread getV3() {
		return v3;
	}

	public void normalTimer() {
		timer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				second--;

				ddSecond = dFormat.format(second);
				ddMinute = dFormat.format(minute);

				counterLabel.setText(ddMinute + ":" + ddSecond);
				//vegcountLabel.setText(15 - (v0.getvegcount() + v1.getvegcount() + v2.getvegcount()) + "");

				if (second == -1) {
					second = 59;
					minute--;

					ddSecond = dFormat.format(second);
					ddMinute = dFormat.format(minute);
					counterLabel.setText(ddMinute + ":" + ddSecond);
				}
				if (counterLabel.getText().equals("00:00")) {
					timer.stop();
					JOptionPane.showMessageDialog(null, "인간 승리!!", "Result", JOptionPane.PLAIN_MESSAGE);
					// MainFrame main = new MainFrame();
					// main.setVisible(true);
				}
				if (vegcountLabel.getText().equals("0")) {
					timer.stop();
					JOptionPane.showMessageDialog(null, "두더지는 자유다 두더지 만만세", "Result", JOptionPane.PLAIN_MESSAGE);
					// MainFrame main = new MainFrame();
					// main.setVisible(true);
				}
			}
		});
	}

	class MoleThread extends Thread {
		private int x, y;
		private JButton moleButton;
		private Rectangle champion;
		private int molesecond = 0;
		private int direction = 0;
		//private ImageIcon mole = new ImageIcon("img/moleimg.png");
		//private ImageIcon moleSelect = new ImageIcon("img/moleselect.png");
		private ImageIcon mole[] = { new ImageIcon("img/moleResource/moleL1.png"),
				new ImageIcon("img/moleResource/moleL2.png"), new ImageIcon("img/moleResource/moleL3.png"),
				new ImageIcon("img/moleResource/moleLS1.png"), new ImageIcon("img/moleResource/moleLS2.png"),
				new ImageIcon("img/moleResource/moleLS3.png"), new ImageIcon("img/moleResource/moleR1.png"),
				new ImageIcon("img/moleResource/moleR2.png"), new ImageIcon("img/moleResource/moleR3.png"),
				new ImageIcon("img/moleResource/moleRS1.png"), new ImageIcon("img/moleResource/moleRS2.png"),
				new ImageIcon("img/moleResource/moleRS3.png"), new ImageIcon("img/moleResource/moleS.png"),
				new ImageIcon("img/moleResource/moleSS.png") };

		private Timer timer;
		private double speed = 0.15;
		private Long startTime;

		private Timer eattimer;
		private int eatsecond;
		private boolean eating = false;

		private double targetX, targetY;
		private double startX, startY;
		private double runTime;

		private boolean enhenceteeth = false;
		private ChannelHandlerContext ctx;
		private String name;

		public int getx() {
			return x;
		}

		public int gety() {
			return y;
		}

		public MoleThread(ChannelHandlerContext ctx, String name, int x, int y) {
			this.ctx = ctx;
			this.x = x;
			this.y = y;
			this.name = name;

			champion = new Rectangle(x, y, 32, 32);

			moleButton = new JButton(mole[12]);
			moleButton.setBorderPainted(false);
			moleButton.setFocusPainted(false);
			moleButton.setContentAreaFilled(false);
			moleButton.setBounds(x, y, 32, 32);
			add(moleButton);

			moleButton.addActionListener(e -> {
				if (e.getSource() == moleButton && eating == false) {
					moleButton.setIcon(mole[13]);
					addMouseListener(new MouseAdapter() {
						@Override
						public void mousePressed(MouseEvent e) {
							if (e.getButton() == MouseEvent.BUTTON3) {
								if (eating == true) // 먹었을 때 해제하게 만듬
									moleButton.setIcon(mole[12]);
								if (moleButton.getIcon().equals(mole[13]) || moleButton.getIcon().equals(mole[11])
										|| moleButton.getIcon().equals(mole[10]) || moleButton.getIcon().equals(mole[9])
										|| moleButton.getIcon().equals(mole[5]) || moleButton.getIcon().equals(mole[4])
										|| moleButton.getIcon().equals(mole[3])) {
									timer.stop();
									calculateChampionMovement(e.getX(), e.getY(), champion);
									startTime = System.currentTimeMillis();
									timer.start();
								}
							}
							if (e.getButton() == MouseEvent.BUTTON1) {
								if (moleButton.getIcon().equals(mole[13]))
									moleButton.setIcon(mole[12]);
								else if (moleButton.getIcon().equals(mole[11]))
									moleButton.setIcon(mole[8]);
								else if (moleButton.getIcon().equals(mole[10]))
									moleButton.setIcon(mole[7]);
								else if (moleButton.getIcon().equals(mole[9]))
									moleButton.setIcon(mole[6]);
								else if (moleButton.getIcon().equals(mole[5]))
									moleButton.setIcon(mole[2]);
								else if (moleButton.getIcon().equals(mole[4]))
									moleButton.setIcon(mole[1]);
								else if (moleButton.getIcon().equals(mole[3]))
									moleButton.setIcon(mole[0]);
							}
						}
					});
				}
			});
			timer = new Timer(30, e -> {
				if (eating == false) {

					molesecond++;
					molesecond = molesecond % 4;
					if (direction == 1) { // 오른쪽방향으로 움직일때 -누름
						if (molesecond == 1)
							moleButton.setIcon(mole[8]);
						else if (molesecond == 2)
							moleButton.setIcon(mole[9]);
						else if (molesecond == 3)
							moleButton.setIcon(mole[10]);
					}
					if (direction == 2) { // 왼쪽방향으로 움직일때 -누름
						if (molesecond == 1)
							moleButton.setIcon(mole[3]);
						else if (molesecond == 2)
							moleButton.setIcon(mole[4]);
						else if (molesecond == 3)
							moleButton.setIcon(mole[5]);
					}
					try {
						TimeMove();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				} else
					timer.stop();
			});
		}

		public void molegetitem() {
			int itemnum = ((int) (Math.random() * 10));
			switch (itemnum) {
			case 0:
			case 9:
				System.out.println(itemnum);
				System.out.println("뱀피리 획득");
				System.out.println(itembox1.isVisible());
				if (itembox1.isVisible() == false) {
					itembox1.setVisible(true);
					itembox1.setIcon(itemsnakepipe);
				} else {
					itembox2.setIcon(itemsnakepipe);
					System.out.println("2");
				}
				break;
			case 1:
			case 8:
				System.out.println(itemnum);
				System.out.println("사람 정지");
				System.out.println(itembox1.isVisible());
				if (itembox1.isVisible() == false) {
					itembox1.setVisible(true);
					itembox1.setIcon(itemtrap);
				} else {
					itembox2.setIcon(itemtrap);
					System.out.println("2");
				}
				break;
			default:
				enhenceteeth = true;
				System.out.println("강철이빨 획득");
				System.out.println(itembox1.isVisible());
				if (itembox1.isVisible() == false) {
					itembox1.setVisible(true);
					itembox1.setIcon(itemteeth);
				} else {
					itembox2.setIcon(itemteeth);
					System.out.println("2");
				}
				break;
			}
		}

		public void v1EatTimer() {
			eating = true;
			eattimer = new Timer(1000, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					eatsecond++;
					if (eatsecond == 3) {
						eattimer.stop();
						eating = false;
						ctx.writeAndFlush("[v1EAT]," + name);
						v1.setVisible(false);
					}
				}
			});
		}
		public void v2EatTimer() {
			eating = true;
			eattimer = new Timer(1000, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					eatsecond++;
					if (eatsecond == 3) {
						eattimer.stop();
						eating = false;
						ctx.writeAndFlush("[v2EAT]," + name);
						v2.setVisible(false);
					}
				}
			});
		}
		public void v3EatTimer() {
			eating = true;
			eattimer = new Timer(1000, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					eatsecond++;
					if (eatsecond == 3) {
						eattimer.stop();
						eating = false;
						ctx.writeAndFlush("[v3EAT]," + name);
						v3.setVisible(false);
					}
				}
			});
		}
		

		public void run() {
		}

		public void TimeMove() throws InterruptedException {
			long duration = System.currentTimeMillis() - startTime;
			double progress = duration / runTime;

			if (progress >= 1.0) {
				progress = 1.0;
				timer.stop();
			}

			double x = (int) (startX + ((targetX - startX) * progress));
			double y = (int) (startY + ((targetY - startY) * progress));

			repaint();
			if (y >= 270 && x >= 12 && x <= 770) {
				moleButton.setBounds((int) x - 15, (int) y - 15, 30, 30);
				champion.setRect(x - 5, y - 5, 10, 10);
			}

			if (i0.getX() == x && i0.getY() >= y - 20 && i0.timerstop == false && eating == false) {
				i0.setVisible(false);
				i0.setsecond(0);
				i0.itemtimer();
				i0.itemtimer.start();
				molegetitem();
				
			} else if (i1.getX() == x && i1.getY() >= y - 20 && i1.timerstop == false && eating == false) {
				i1.setVisible(false);
				i1.setsecond(0);
				i1.itemtimer();
				i1.itemtimer.start();
				molegetitem();
			}

			if (v1.getX() == x && v1.getY() >= y - 20 && v1.timerstop == false && eating == false) {
				eatsecond = 0;
				if (enhenceteeth == false) {
					v1EatTimer();
					eattimer.start();
				} /*else {
					enhenceteeth = false;
					if (itembox1.getIcon() == itemteeth && itembox2.getIcon() == itemteeth) {
						itembox1.setIcon(null);
						enhenceteeth = true;
					} else if (itembox1.getIcon() == itemteeth)
						itembox1.setIcon(null);
					else
						itembox2.setIcon(null);
				}*/
				v1.plusvegcount();
			} else if (v2.getX() == x && v2.getY() >= y - 20 && v2.timerstop == false && eating == false) {
			/*	v1.setVisible(false);
				v1.setsecond(0);
				v1.vegtimer();
				v1.vegtimer.start();*/
				eatsecond = 0;
				if (enhenceteeth == false) {
					v2EatTimer();
					eattimer.start();
				} else {
					enhenceteeth = false;
					if (itembox1.getIcon() == itemteeth && itembox2.getIcon() == itemteeth) {
						itembox1.setIcon(null);
						enhenceteeth = true;
					} else if (itembox1.getIcon() == itemteeth)
						itembox1.setIcon(null);
					else
						itembox2.setIcon(null);
				}
				v2.plusvegcount();

			} else if (v3.getX() == x && v3.getY() >= y - 20 && v3.timerstop == false && eating == false) {
				/*v2.setVisible(false);
				v2.setsecond(0);
				v2.vegtimer();
				v2.vegtimer.start();*/
				eatsecond = 0;
				if (enhenceteeth == false) {
					v3EatTimer();
					eattimer.start();
				} else {
					enhenceteeth = false;
					if (itembox1.getIcon() == itemteeth && itembox2.getIcon() == itemteeth) {
						itembox1.setIcon(null);
						enhenceteeth = true;
					} else if (itembox1.getIcon() == itemteeth)
						itembox1.setIcon(null);
					else
						itembox2.setIcon(null);
				}
				v3.plusvegcount();
			}
		}

		public void calculateChampionMovement(double x, double y, Rectangle champion) {

			if (x != champion.getCenterX() || y != champion.getCenterY()) {

				targetX = x;
				targetY = y;

				System.out.println(targetX + "     " + targetY);
				startX = champion.getCenterX();
				startY = champion.getCenterY();

				/*
				 * if(targetX - startX > 0) { if(targetY - startY > 0) {
				 * System.out.println("4사분면"); }else System.out.println("1사분면"); } else if
				 * (targetX-startX < 0) { if(targetY - startY > 0) { System.out.println("3사분면");
				 * }else System.out.println("2사분면"); }
				 */

				double distance = Math
						.sqrt((startX - targetX) * (startX - targetX) + (startY - targetY) * (startY - targetY));

				runTime = distance / (double) speed;
//				if ((x <= v0.getx() + 10 && x > v0.getx() - 10) || (x <= v1.getx() + 10 && x > v1.getx() - 10)
//						|| (x <= v2.getx() + 10 && x > v2.getx() - 10) && y < 290) {
//					eat = true;
//				}
			}
		}
	}

	public void paintComponent(Graphics g) {// 그리는 함수
		super.paintComponent(g);
		g.drawImage(backImage, 0, 0, null);
		//g.drawImage(humanHud, 0, 70, null);
		g.drawImage(moleHud, 715, 70, null);
		//g.drawImage(humanInv, 55, 0, null);
		g.drawImage(moleInv, 650, 0, null);
		//g.drawImage(intHuman, 0, 0, null);
		g.drawImage(intMole, 740, 0, null);
	}
}

/*class f extends JFrame {
	private MoleUI molePanel;

	public f() throws IOException, InterruptedException { // Mole UI 창
		setTitle("Mole Game");
		setSize(800, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		molePanel = new MoleUI(); // molePanel 생성

		add(molePanel);
		setVisible(true);
	}
}*/

