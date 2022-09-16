import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


public class Stardrop extends JFrame{
	
	//Ÿ�̸� ������
	class TimerThread extends Thread { //�ð� ��
		private JLabel GameTimer; //Ÿ�̸� ���� ��µǴ� ���̺�
		
		public TimerThread(JLabel GameTimer) {
			this.GameTimer = GameTimer; //Ÿ�̸� ī��Ʈ

		}
		
		public void run() {
			int n = 10;

			while(n>=0) {
				GameTimer.setText(Integer.toString(n)); // ���̺� ī��Ʈ �� ���
				n--;
				try {
					Thread.sleep(1000);
				}
				catch(InterruptedException e) {
					return;
				}
			}
		}
	}

	//������ ������
	class ScoreThread extends Thread {
		private JLabel StarScore;
		//private Thread YellowStarThread;
		private JLabel basket;
		private JLabel YellowStar;
	
		public ScoreThread(JLabel StarScore,JLabel basket) {
			this.StarScore = StarScore;
			this.basket = basket;
		}
		
		public void run() {
			int n=0;
			
			while(true) {
				int bx = basket.getX();
				int by = basket.getY();
				int sx = YellowStar.getX();
				int sy = YellowStar.getY();
				
				/*
				 * if((bx==sx)&&(by==sy)) {

					n = n+5;
					
				}
				 */
				StarScore.setText(Integer.toString(n));
				
				try {
					sleep(1);
				}
				catch(InterruptedException e) {
					return;
				}
			}
		}
		
	}
	
	//���� �� ����߸��� ������
	 class YellowStarThread extends Thread {
		ImageIcon YstarImg = new ImageIcon("images/star.png");
		JLabel YellowStar = new JLabel(YstarImg);
		int x,y;
		int h = (int)(Math.random()*30+10);
		
	 	public YellowStarThread(int x, int y) {//������
	 		this.x = x;
	 		this.y = y;
	 		YellowStar.setLocation(x,y);
	 		YellowStar.setSize(32,32);
	 		repaint();
	 		add(YellowStar);
			
		}
		
		public void run() {
		
			while(y<=480) {
				y += h;
				YellowStar.setLocation(x,y);
				repaint();
				try {
					sleep(200);
					
				}
				catch(InterruptedException e) {
					return ;
				}
				if(y>=380) {
					YellowStar.setVisible(false);
				}
			}
		}
	}
	 
	 class RedStarThread extends Thread {
			ImageIcon RstarImg = new ImageIcon("images/redstar.png");
			JLabel RedStar = new JLabel(RstarImg);
			int x,y;
			int h = (int)(Math.random()*20+5);
			
		 	public RedStarThread(int x, int y) {//������
		 		this.x = x;
		 		this.y = y;
		 		RedStar.setLocation(x,y);
		 		RedStar.setSize(32,32);
		 		repaint();
		 		add(RedStar);
				
			}
			
			public void run() {
			
				while(y<=480) {
					y += h;
					RedStar.setLocation(x,y);
					repaint();
					try {
						sleep(200);
					}
					catch(InterruptedException e) {
						return ;
					}
					if(y>=480) {
						RedStar.setVisible(false);
					}
				}
			}
		}

	 class BlueStarThread extends Thread {
			ImageIcon BstarImg = new ImageIcon("images/anise.png");
			JLabel BlueStar = new JLabel(BstarImg);
			int x,y;
			int h = (int)(Math.random()*20+40);
			
		 	public BlueStarThread(int x, int y) {//������
		 		this.x = x;
		 		this.y = y;
		 		BlueStar.setLocation(x,y);
		 		BlueStar.setSize(32,32);
		 		repaint();
		 		add(BlueStar);
				
			}
			
			public void run() {
			
				while(y<=480) {
					y += h;
					BlueStar.setLocation(x,y);
					repaint();
					try {
						sleep(200);
					}
					catch(InterruptedException e) {
						return ;
					}
					if(y>=480) {
						BlueStar.setVisible(false);
					}
				}
			}
		}
	
	 
	//�̹���
	private ImageIcon[] image= {
			new ImageIcon("images/basket.png"),
			new ImageIcon("images/star.png"),
			new ImageIcon("images/redstar.png"),
			new ImageIcon("images/anise.png")
	};
	
	//�ٱ���
	private JLabel basket = new JLabel(image[0]); //��ü����
	
	//������ ��ư
	private JButton randomScore = new JButton("��");
	private JButton randomStarV = new JButton("��");
	private JButton randomBasketV = new JButton("��");
	
	//�޴� (����)
	private JLabel StarScore = new JLabel("����");
	private JLabel GameTimer = new JLabel("�ð�");
	
	//��
	//private JLabel YellowStar = new JLabel(image[0]);
	
	//�� ������ ���۷��� ���� 
	private YellowStarThread kiiroi;
	private RedStarThread akai;
	private BlueStarThread aoi;
	
	//������
	public Stardrop() {
		super("STAR DROP");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//����Ʈ��
		Container c = getContentPane();
		c.setBackground(Color.DARK_GRAY);
		c.setLayout(null);
		
		//������ ��ư
		randomScore.setBackground(new Color(135,206,250));
		randomStarV.setBackground(new Color(135,206,250));
		randomBasketV.setBackground(new Color(135,206,250));
		
		//������ ũ�� �� ��ġ ����
		randomScore.setSize(80,30);
		randomStarV.setSize(80,30);
		randomBasketV.setSize(80,30);
		
		randomScore.setLocation(15,400);
		randomStarV.setLocation(260,400);
		randomBasketV.setLocation(495,400);
		
		c.add(randomScore); c.add(randomStarV); c.add(randomBasketV);
		
		//�޴� ����
		StarScore.setFont(new Font("���׷� �޷γ�ü",Font.PLAIN, 30));
		StarScore.setForeground(Color.white);
		
		//�޴� �ð�
		GameTimer.setFont(new Font("���׷� �޷γ�ü",Font.PLAIN,30));
		GameTimer.setForeground(Color.white);
		
		
		//�޴� ũ�� �� ��ġ ����
		StarScore.setSize(60,60);
		GameTimer.setSize(60,60);
		StarScore.setLocation(15,15);
		GameTimer.setLocation(515,15);
		
		c.add(StarScore); c.add(GameTimer);
		
		
		//������ :: �� ����
		for (int i=0;i<5;i++) {
			int x = (int)(Math.random()*500+50);
			int y = 0;
			kiiroi = new YellowStarThread(x,y);
			kiiroi.start();
		}
		
		for(int i=0;i<10;i++) {
			int x = (int)(Math.random()*500+50);
			int y = 0;
			akai = new RedStarThread(x,y);
			akai.start();
		}
		
		for(int i=0;i<3;i++) {
		int x = (int)(Math.random()*500+50);
		int y = 0;
		aoi = new BlueStarThread(x,y);
		aoi.start();
		}
		
		
		
		//�ٱ���
		
		basket.setSize(64,64); //������
		basket.setLocation(260,320);//��ġ
		c.add(basket); //���̱�
		
		//�ٱ��� �̵� �̺�Ʈ
		class MovingBasket extends KeyAdapter {
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				
				switch(keyCode) {
				case KeyEvent.VK_LEFT:
					basket.setLocation(basket.getX() - 45, basket.getY());
					break;
				case KeyEvent.VK_RIGHT:
					basket.setLocation(basket.getX() + 45, basket.getY());
					break;
				}
				
			}
		}
		
		c.addKeyListener(new MovingBasket()); //key �̺�Ʈ ������ �ޱ�
	
		//������ :: Ÿ�̸�
		TimerThread timer = new TimerThread(GameTimer); timer.start();
						
		//������ :: ������
		ScoreThread counter = new ScoreThread(StarScore,basket); counter.start();
						
		setSize(600,480);
		setVisible(true);
		
		
		//keyEvent�� ���� ��Ŀ�� �ֱ�
		c.setFocusable(true);
		c.requestFocus();
		
		//����Ʈ�ҿ� ��Ŀ���� �Ҿ�� ���콺�� Ŭ���ϸ� �ٽ� ��Ŀ���� ���
		
		c.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Component com = (Component)e.getSource();
				com.setFocusable(true);
				com.requestFocus();
			}
		});
			
	}
		
	public static void main(String[] args) {
		new Stardrop();
	}

}
