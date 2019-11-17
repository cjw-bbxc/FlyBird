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
	BufferedImage background;		//����ͼƬ

	/** ��ʼͼƬ */
	BufferedImage startImage;
	
	/** ����ͼƬ */
	BufferedImage endImage;
	
	/** ���� */
	Ground ground;
	
	/** ����1 */
	Column column1;
	/** ����2 */
	Column column2;
	
	/** С�� */
	Bird bird;
	
	/** ��Ϸ���� */
	int score;
	
	/** ��Ϸ״̬ */
	int state;
	
	/** ״̬����-��ʼ */
	public static final int START = 0;
	/** ״̬����-���� */
	public static final int RUNNING = 1;
	/** ״̬����-���� */
	public static final int END = 2;
	
	/**
	 * 
	 * @Title:  BirdGame  
	 * @Description:��ʼ����Ϸ
	 * @author �װ�С��
	 * @date  2019��
	 */
	public BirdGame() {
		// ��ʼ��
		try {
			background = ImageIO.read(getClass().getResource("/BirdPng/bg.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
		//��ʼ����ʼ������ͼƬ
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
		
		//��ʼ������
		score = 0;
		
		//��ʼ��״̬
		state = START;
		
		
	}
	
	/**
	 * ���ƽ���
	 */
	public void paint(Graphics g) {
		//���Ʊ���
		g.drawImage(background, 0, 0, null);
		
		//���Ƶ���
		g.drawImage(ground.image, ground.x, ground.y, null);
		
		//��������
		g.drawImage(column1.image, column1.x - column1.width / 2,
				column1.y - column1.high / 2, null);
		g.drawImage(column2.image, column2.x - column2.width / 2,
				column2.y - column2.high / 2, null);
		
		//����С��
		Graphics2D g2 = (Graphics2D)g;
		g2.rotate(-bird.alpha, bird.x, bird.y);
		g.drawImage(bird.image, bird.x - bird.width / 2,
				bird.y - bird.high / 2, null);
		g2.rotate(bird.alpha, bird.x, bird.y);
		
		//���Ʒ���
		Font f = new Font(Font.SANS_SERIF, Font.BOLD, 40);
		g.setFont(f);
		g.drawString("" + score, 40, 60);
		g.setColor(Color.WHITE);
		g.drawString("" + score, 40 - 3, 60 - 3);
		
		//���ƿ�ʼ�ͽ�������
		switch (state) {
		case START:
			g.drawImage(startImage, 0, 0, null);
			break;
		case END:
			g.drawImage(endImage, 0, 0, null);
			break;
		}
		
		
	}
	
	/** ��ʼ��Ϸ */
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
							//��ʼ״̬
							state = RUNNING;
							break;
						case RUNNING:
							//����״̬
							bird.flappy();
							break;
						case END:
							/*
							 *  ����״̬
							 * ���������������
							 * �ٴ�תΪ��ʼ̬
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
		
		//���ϵ��ƶ����ػ�
		while (true) {
			switch (state) {
			case START:
				//С���������ж���
				bird.fly();
				//�����ƶ�
				ground.step();
				break;
			case RUNNING:
				//���������ƶ�
				ground.step();
				//�����ƶ�
				column1.step();
				column2.step();
				//С���������ж���
				bird.fly();
				//С�������ƶ�
				bird.step();
				//�������
				if(bird.x == column1.x || bird.x == column2.x) {
					score++;
				}
				
				//�����ײ
				if(bird.hit(ground) || bird.hit(column1) || bird.hit(column2)) {
					state = END;
				}
				break;				
			}
			
			//���»��ƽ���
			repaint();
			//����
			try {
				Thread.sleep(1000 / 60);
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
	}
	
	
	//����
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
