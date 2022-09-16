import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


public class Stardrop extends JFrame{
	
	//타이머 스레드
	class TimerThread extends Thread { //시간 라벨
		private JLabel GameTimer; //타이머 값이 출력되는 레이블
		
		public TimerThread(JLabel GameTimer) {
			this.GameTimer = GameTimer; //타이머 카운트

		}
		
		public void run() {
			int n = 10;

			while(n>=0) {
				GameTimer.setText(Integer.toString(n)); // 레이블에 카운트 값 출력
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

	//점수판 스레드
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
	
	//점수 별 떨어뜨리는 스레드
	 class YellowStarThread extends Thread {
		ImageIcon YstarImg = new ImageIcon("images/star.png");
		JLabel YellowStar = new JLabel(YstarImg);
		int x,y;
		int h = (int)(Math.random()*30+10);
		
	 	public YellowStarThread(int x, int y) {//생성자
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
			
		 	public RedStarThread(int x, int y) {//생성자
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
			
		 	public BlueStarThread(int x, int y) {//생성자
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
	
	 
	//이미지
	private ImageIcon[] image= {
			new ImageIcon("images/basket.png"),
			new ImageIcon("images/star.png"),
			new ImageIcon("images/redstar.png"),
			new ImageIcon("images/anise.png")
	};
	
	//바구니
	private JLabel basket = new JLabel(image[0]); //객체생성
	
	//아이템 버튼
	private JButton randomScore = new JButton("★");
	private JButton randomStarV = new JButton("▼");
	private JButton randomBasketV = new JButton("♨");
	
	//메뉴 (점수)
	private JLabel StarScore = new JLabel("점수");
	private JLabel GameTimer = new JLabel("시간");
	
	//별
	//private JLabel YellowStar = new JLabel(image[0]);
	
	//별 스레드 레퍼런스 변수 
	private YellowStarThread kiiroi;
	private RedStarThread akai;
	private BlueStarThread aoi;
	
	//생성자
	public Stardrop() {
		super("STAR DROP");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//컨텐트팬
		Container c = getContentPane();
		c.setBackground(Color.DARK_GRAY);
		c.setLayout(null);
		
		//아이템 버튼
		randomScore.setBackground(new Color(135,206,250));
		randomStarV.setBackground(new Color(135,206,250));
		randomBasketV.setBackground(new Color(135,206,250));
		
		//아이템 크기 및 위치 설정
		randomScore.setSize(80,30);
		randomStarV.setSize(80,30);
		randomBasketV.setSize(80,30);
		
		randomScore.setLocation(15,400);
		randomStarV.setLocation(260,400);
		randomBasketV.setLocation(495,400);
		
		c.add(randomScore); c.add(randomStarV); c.add(randomBasketV);
		
		//메뉴 점수
		StarScore.setFont(new Font("빙그레 메로나체",Font.PLAIN, 30));
		StarScore.setForeground(Color.white);
		
		//메뉴 시간
		GameTimer.setFont(new Font("빙그레 메로나체",Font.PLAIN,30));
		GameTimer.setForeground(Color.white);
		
		
		//메뉴 크기 및 위치 설정
		StarScore.setSize(60,60);
		GameTimer.setSize(60,60);
		StarScore.setLocation(15,15);
		GameTimer.setLocation(515,15);
		
		c.add(StarScore); c.add(GameTimer);
		
		
		//스레드 :: 별 시작
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
		
		
		
		//바구니
		
		basket.setSize(64,64); //사이즈
		basket.setLocation(260,320);//위치
		c.add(basket); //붙이기
		
		//바구니 이동 이벤트
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
		
		c.addKeyListener(new MovingBasket()); //key 이벤트 리스너 달기
	
		//스레드 :: 타이머
		TimerThread timer = new TimerThread(GameTimer); timer.start();
						
		//스레드 :: 점수판
		ScoreThread counter = new ScoreThread(StarScore,basket); counter.start();
						
		setSize(600,480);
		setVisible(true);
		
		
		//keyEvent를 위한 포커스 주기
		c.setFocusable(true);
		c.requestFocus();
		
		//컨텐트팬에 포커스를 잃었어도 마우스를 클릭하면 다시 포커스를 얻게
		
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
