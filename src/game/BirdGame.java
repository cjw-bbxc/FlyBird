package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BirdGame extends JPanel{
	BufferedImage background;		//背景图片

	/** 开始图片 */
	BufferedImage startImage;
	
	/** 结束图片 */
	BufferedImage endImage;
	
	/** 地面 */
	Ground ground;
	
	/** 柱子1 */
	Column column1;
	/** 柱子2 */
	Column column2;
	
	/** 小鸟 */
	Bird bird;
	
	/** 游戏分数 */
	int score;
	
	/** 游戏状态 */
	int state;
	
	/** 状态常量-开始 */
	public static final int START = 0;
	/** 状态常量-运行 */
	public static final int RUNNING = 1;
	/** 状态常量-结束 */
	public static final int END = 2;
	
	/**
	 * 
	 * @Title:  BirdGame  
	 * @Description:初始化游戏
	 * @author 白白小草
	 * @date  2019年
	 */
	public BirdGame() {
		// 初始化
		try {
			background = ImageIO.read(getClass().getResource("/BirdPng/bg.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
		//初始化开始、结束图片
		try {
			startImage = ImageIO.read(getClass().getResource("/BirdPng/start.png"));
			endImage = ImageIO.read(getClass().getResource("/BirdPng/end.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ground = new Ground();
		column1 = new Column(1);
		column2 = new Column(2);
		bird = new Bird();
		
		//初始化分数
		score = 0;
		
		//初始化状态
		state = START;
		
		
	}
	
	/**
	 * 绘制界面
	 */
	public void paint(Graphics g) {
		//绘制背景
		g.drawImage(background, 0, 0, null);
		
		//绘制地面
		g.drawImage(ground.image, ground.x, ground.y, null);
		
		//绘制柱子
		g.drawImage(column1.image, column1.x - column1.width / 2,
				column1.y - column1.high / 2, null);
		g.drawImage(column2.image, column2.x - column2.width / 2,
				column2.y - column2.high / 2, null);
		
		//绘制小鸟
		Graphics2D g2 = (Graphics2D)g;
		g2.rotate(-bird.alpha, bird.x, bird.y);
		g.drawImage(bird.image, bird.x - bird.width / 2,
				bird.y - bird.high / 2, null);
		g2.rotate(bird.alpha, bird.x, bird.y);
		
		//绘制分数
		Font f = new Font(Font.SANS_SERIF, Font.BOLD, 40);
		g.setFont(f);
		g.drawString("" + score, 40, 60);
		g.setColor(Color.WHITE);
		g.drawString("" + score, 40 - 3, 60 - 3);
		
		//绘制开始和结束界面
		switch (state) {
		case START:
			g.drawImage(startImage, 0, 0, null);
			break;
		case END:
			g.drawImage(endImage, 0, 0, null);
			break;
		}
		
		
	}
	
	/** 开始游戏 */
	public void action() {
		
		MouseListener l = new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					switch (state) {
						case START:
							//开始状态
							state = RUNNING;
							break;
						case RUNNING:
							//运行状态
							bird.flappy();
							break;
						case END:
							/*
							 *  结束状态
							 * 按下鼠标重置数据
							 * 再次转为开始态
							 */
							column1 = new Column(1);
							column2 = new Column(2);
							bird = new Bird();
							score = 0;
							state = START;
							break;
					}
				} catch (Exception e2) {
					System.out.println(e2);
				}
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		
		addMouseListener(l);
		
		//不断的移动和重绘
		while (true) {
			switch (state) {
			case START:
				//小鸟做出飞行动作
				bird.fly();
				//地面移动
				ground.step();
				break;
			case RUNNING:
				//地面向左移动
				ground.step();
				//柱子移动
				column1.step();
				column2.step();
				//小鸟做出飞行动作
				bird.fly();
				//小鸟上下移动
				bird.step();
				//计算分数
				if(bird.x == column1.x || bird.x == column2.x) {
					score++;
				}
				
				//检测碰撞
				if(bird.hit(ground) || bird.hit(column1) || bird.hit(column2)) {
					state = END;
				}
				break;				
			}
			
			//重新绘制界面
			repaint();
			//休眠
			try {
				Thread.sleep(1000 / 60);
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
	}
	
	
	//测试
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		BirdGame bg = new BirdGame();
		frame.add(bg);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setSize(440,670);
		frame.setResizable(false);
		bg.action();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
