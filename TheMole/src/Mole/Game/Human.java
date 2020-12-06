package Mole.Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

import Mole.Game.Sound.EffectSound;

public class Human extends JLabel {

	private double x;
	private int y;
	private boolean timerstop = false;
	private static int status = 0; // 1은 오른쪽, 2는 왼쪽,
	public int humsecond = 0;
	public int shosecond = 15;
	private Font font1 = new Font("Arial", Font.BOLD, 30);
	private boolean shooting = false;
	private int humanlife = 2;

	private Timer mover;
	private Timer humtraptimer;
	private int humtrapsecond = 10;
	private boolean moving = false;
	private Timer shoesTimer;
	MolePanel mainpanel;

	private JLabel itembox1;
	private JLabel itembox2;
	private int humanspeed = 3;
	
	private int bulletcount = 5;

	ImageIcon shoes = new ImageIcon("img/itemResource/shoes.png");
	ImageIcon bullets = new ImageIcon("img/itemResource/bullet.png");
	ImageIcon trapM = new ImageIcon("img/itemResource/trapH.png");

	private ImageIcon human[] = { new ImageIcon("img/humanResource/human1.png"),
			new ImageIcon("img/humanResource/human2.png"), new ImageIcon("img/humanResource/human3.png"),
			new ImageIcon("img/humanResource/human4.png"), new ImageIcon("img/humanResource/human5.png"),
			new ImageIcon("img/humanResource/human6.png"), new ImageIcon("img/humanResource/human7.png"),
			new ImageIcon("img/humanResource/human8.png"), new ImageIcon("img/humanResource/human9.png"),
			new ImageIcon("img/humanResource/human10.png") };

	public Human(MolePanel pan, int y) {
		x = 200;
		this.y = y;
		mainpanel = pan;

		setBounds((int) x, (int) y, 50, 64);
		setIcon(human[0]);
		pan.setFocusable(true);
		//EffectSound itemGetSound = new EffectSound();

		itembox1 = new JLabel();
		itembox2 = new JLabel();
		itembox1.setBounds(60, 5, 38, 38);
		itembox2.setBounds(102, 5, 38, 38);

		pan.add(itembox1);
		pan.add(itembox2);
		itembox1.setVisible(false);
		pan.addKeyListener(new KeyListener() {

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT && pan.humstop == false) { // 왼쪽 방향키
					moving = true;
					status = 2;
					setX(-humanspeed);
					if (timerstop == false) {
						humsecond = 0;
						timerstop = true;
						timerstart();
					}
					setBounds(getX(), y, 50, 64);
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT && pan.humstop == false) { // 오른쪽 방향키
					moving = true;
					status = 1;
					setX(humanspeed);
					if (timerstop == false) {
						humsecond = 0;
						timerstop = true;
						timerstart();
					}
					setBounds(getX(), y, 50, 64);
				}
				if (e.getKeyCode() == KeyEvent.VK_A && shooting == false && pan.humstop == false && bulletcount != 0 && status == 2) {
					shooting = true;
					Bullet a = new Bullet((int)x, 2,status, pan);
					minusbcount();
				}
				if (e.getKeyCode() == KeyEvent.VK_D && shooting == false && pan.humstop == false && bulletcount != 0 && status == 1) {
					shooting = true;
					Bullet b = new Bullet((int)x, 1,status, pan);
					minusbcount();
				}
				if (e.getKeyCode() == KeyEvent.VK_S && shooting == false && pan.humstop==false && bulletcount != 0) {
					shooting = true;
					Bullet a = new Bullet((int)x, 3, status, pan);
					minusbcount();
				}
				if (pan.i0.getX() > x - 10 && pan.i0.getX() < x + 3 && pan.i0.timerstop == false) {
					pan.i0.setVisible(false);
					pan.i0.setsecond(0);
					pan.i0.itemtimer();
					pan.i0.itemtimer.start();
					//itemGetSound.playSound("sound/itemGet.mp3");
					humangetitem();
				}
				if (pan.i1.getX() > x - 10 && pan.i1.getX() < x + 10 && pan.i1.timerstop == false) {
					pan.i1.setVisible(false);
					pan.i1.setsecond(0);
					pan.i1.itemtimer();
					pan.i1.itemtimer.start();
					//itemGetSound.playSound("sound/itemGet.mp3");
					humangetitem();
				}
			}

			public void timerstart() {
				mover();
				mover.start();
			}

			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) { // 왼쪽 방향키
					moving = false;
					setIcon(human[5]);
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) { // 오른쪽 방향키
					moving = false;
					setIcon(human[0]);
				} else if (e.getKeyCode() == KeyEvent.VK_A) {
					shooting = false;
				} else if (e.getKeyCode() == KeyEvent.VK_D) {
					shooting = false;
				} else if (e.getKeyCode() == KeyEvent.VK_S) {
                    shooting = false;
                }
			}

			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}
		});
	}

	public void mover() {
		mover = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				humsecond++;
				humsecond = humsecond % 5;
				if (moving == true && status == 1) { // 오른쪽방향으로 움직일때 -누름
					/*
					 * for(int i = 0;i<6;i++) { setX(1); }
					 */
					if (humsecond == 1)
						setIcon(human[1]);
					else if (humsecond == 2)
						setIcon(human[2]);
					else if (humsecond == 3)
						setIcon(human[3]);
					else if (humsecond == 4)
						setIcon(human[4]);
				}
				if (moving == true && status == 2) { // 왼쪽방향으로 움직일때 -누름
					/*
					 * for(int i = 0;i<6;i++) { setX(-1); }
					 */
					if (humsecond == 1)
						setIcon(human[6]);
					else if (humsecond == 2)
						setIcon(human[7]);
					else if (humsecond == 3)
						setIcon(human[8]);
					else if (humsecond == 4)
						setIcon(human[9]);
				}
				if (moving == false) {
					mover.stop();
					timerstop = false;
				}
				if(mainpanel.m1.getx() >= x - 5 && mainpanel.m1.getx() <= x + 5 && mainpanel.m1.gety()<= 275 && mainpanel.m1.getlife() == true) {
					mainpanel.m1.moledie();
				} else if(mainpanel.m2.getx() >= x - 5 && mainpanel.m2.getx() <= x + 5&& mainpanel.m2.gety()<= 275 && mainpanel.m2.getlife() == true) {
					mainpanel.m2.moledie();
				} else if(mainpanel.m3.getx() >= x - 5 && mainpanel.m3.getx() <= x + 5 && mainpanel.m3.gety()<= 275 && mainpanel.m3.getlife() == true) {
					mainpanel.m3.moledie();
				} else if(mainpanel.m4.getx() >= x - 5 && mainpanel.m4.getx() <= x + 5 && mainpanel.m4.gety()<= 275 && mainpanel.m4.getlife() == true) {
					mainpanel.m4.moledie();
				}
				else if(mainpanel.m5.getx() >= x - 5 && mainpanel.m5.getx() <= x + 5 && mainpanel.m5.gety()<= 275 && mainpanel.m5.getlife() == true) {
					mainpanel.m5.moledie();
				}
				else if(mainpanel.m6.getx() >= x - 5 && mainpanel.m6.getx() <= x + 5 && mainpanel.m6.gety()<= 275 && mainpanel.m6.getlife() == true) {
					mainpanel.m6.moledie();
				}
				else if(mainpanel.m7.getx() >= x - 5 && mainpanel.m7.getx() <= x + 5 && mainpanel.m7.gety()<= 275 && mainpanel.m7.getlife() == true) {
					mainpanel.m7.moledie();
				}
				else if(mainpanel.m8.getx() >= x - 5 && mainpanel.m8.getx() <= x + 5 && mainpanel.m8.gety()<= 275 && mainpanel.m8.getlife() == true) {
					mainpanel.m8.moledie();
				}
				else if(mainpanel.m9.getx() >= x - 5 && mainpanel.m9.getx() <= x + 5 && mainpanel.m9.gety()<= 275 && mainpanel.m9.getlife() == true) {
					mainpanel.m9.moledie();
				}
			}
		});
	}

	public void shoesTimer() {
		shoesTimer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				shosecond--;
				System.out.println(shosecond);
				if (itembox1.getIcon() == shoes) {
					humanspeed = 5;
					itembox1.setFont(font1);
					itembox1.setText(shosecond + "");
					itembox1.setVerticalTextPosition(JLabel.CENTER);
					itembox1.setHorizontalTextPosition(JLabel.CENTER);
					itembox1.setForeground(Color.cyan);
				} else {
					humanspeed = 5;
					itembox2.setText(shosecond + "");
					itembox2.setFont(font1);
					itembox2.setVerticalTextPosition(JLabel.CENTER);
					itembox2.setHorizontalTextPosition(JLabel.CENTER);
					itembox2.setForeground(Color.cyan);
				}
				if (shosecond == 0) {
					humanspeed = 3;
					System.out.println("사람속도 하향");
					if (itembox1.getIcon() == shoes) {
						itembox1.setIcon(null);
						itembox1.setText(null);
						itembox1.setVisible(false);
					} else {
						itembox2.setIcon(null);
						itembox2.setText(null);
					}
					humanspeed = 5;
					shosecond = 15;
					itembox1.setVisible(false);
					shoesTimer.stop();
				}
			}
		});
	}

	public void humtraptimer() {
		mainpanel.sethumtrap(true);
		humtraptimer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				humtrapsecond--;
				System.out.println(mainpanel.gethumtrap());
				if (itembox1.getIcon() == trapM) {
					itembox1.setText(humtrapsecond + "");
					itembox1.setFont(font1);
					itembox1.setVerticalTextPosition(JLabel.CENTER);
					itembox1.setHorizontalTextPosition(JLabel.CENTER);
					itembox1.setForeground(Color.cyan);
				}
				if (mainpanel.gethumtrap() == false) {
					humtrapsecond = 0;
				}
				if (humtrapsecond == 0) {
					if (itembox1.getIcon() == trapM) {
						itembox1.setIcon(null);
						itembox1.setText(null);
						itembox1.setVisible(false);
					} else {
						itembox2.setIcon(null);
						itembox2.setText(null);
					}
					humtrapsecond = 10;
					mainpanel.sethumtrap(false);
					humtraptimer.stop();
				}
			}
		});
	}
	public int gethumanlife() {
		return humanlife;
	}
	public void minushumanlife(int a) {
		humanlife -= 1;
		System.out.println("뱀닿음" );
	}

	public void humangetitem() {
		System.out.println("아이템 섭취");
		//int itemnum = 0;
		int itemnum = ((int)(Math.random()*10));
		switch (itemnum) {
		case 0:
		case 9:
			if (itembox1.isVisible() == false) {
				itembox1.setVisible(true);
				itembox1.setIcon(trapM);
				humtraptimer();
				humtraptimer.start();
			} else if (itembox1.getIcon() == trapM || itembox2.getIcon() == trapM) {
				humtrapsecond += 10;
			} else {
				humtraptimer();
				humtraptimer.start();
				itembox2.setIcon(trapM);
			}
			break;
		case 1:
		case 8:
			System.out.println("신발");
			if (itembox1.isVisible() == false) {
				itembox1.setVisible(true);
				itembox1.setIcon(shoes);
				shoesTimer();
				shoesTimer.start();
			} else if (itembox1.getIcon() == shoes || itembox2.getIcon() == shoes) {
				shosecond += 15;
			} else {
				shoesTimer();
				shoesTimer.start();
				itembox2.setIcon(shoes);
			}
			break;
		default:
			System.out.println("총알 획득");
			plusbcount();
//			if (itembox1.isVisible() == false) {
//				itembox1.setVisible(true);
//				itembox1.setIcon(bullets);
//			} else {
//				itembox2.setIcon(bullets);
//			}
			break;
		}

	}

	public int getX() {
		return (int) x;
	}

	public int getY() {
		return (int) y;
	}

	public void setX(double x) {
		this.x = this.x + x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getStatus() {
		return status;
	}
	
	public int getbcount() {
		return bulletcount;
	}
	
	public int minusbcount() {
		return bulletcount--;
	}
	
	public int plusbcount() {
		return bulletcount = bulletcount+2;
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 50, 64);
	}
}