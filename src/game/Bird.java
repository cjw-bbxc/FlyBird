package game;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bird {
	BufferedImage image;
	
	/** xλ�� */
	int x;
	/** yλ�� */
	int y;
	
	/** �� */
	int width;
	/** �� */
	int high;
	
	/** ��С */
	int size;	//������ײ���
	
	/** �������ٶ� */
	double g;
	
	/** λ�Ƶļ��ʱ�� */
	double t;
	
	/** ��������ٶ� */
	double v0;
	
	/** ��ǰ�����ٶ� */
	double speed;
	
	/** ����ʱ��t֮���λ�� */
	double s;
	
	/**С�����ǣ���С��Ļ��ȣ�*/
	double alpha;
	
	/** һ��ͼƬ����¼С��Ķ���֡ */
	BufferedImage[] images;
	
	/** ����֡������±� */
	int index;
	
	/**
	 * @Title:  Bird  
	 * @Description:���캯��-��ʼ��С��
	 * @author �װ�С��
	 * @date  2019��
	 */
	public Bird() {
		//��ʼ����������
		try {
			image = ImageIO.read(getClass().getResource("/BirdPng/0.png"));
		} catch (IOException e) {
			System.out.println(e);			
		}
		
		width = image.getWidth();
		high = image.getHeight();
		
		x = 132;
		y = 280;
		
		size = 40;
		
		//��ʼ��λ�Ʋ���
		g = 4;
		v0 = 20;
		t = 0.25;
		speed = v0;
		s = 0;
		alpha = 0;
		
		//��ʼ������֡����
		images = new BufferedImage[8];
		for (int i = 0; i < images.length; i++) {
			try {
				images[i] = ImageIO.read(getClass().getResource("/BirdPng/" + i + ".png"));
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		
		index = 0;
		
	}
	
	/** ���ж��� �仯֡ */
	public void fly() {
		index++;
		image = images[(index / 12) % 8];
	}
	
	/** �ƶ�һ�� */
	public void step() {
		double v0 = speed;
		
		//��������λ��
		s = v0 * t + g * t * t / 2;
		
		//�����������λ��
		y = y - (int)s;
		
		//�����´ε��ƶ��ٶ�
		double v = v0 - g * t;
		speed = v;
		
		//������ǣ������к�����
		alpha = Math.atan(s / 8);
	}
	
	/** ���Ϸ��� */
	public void flappy() {
		speed = v0;
	}
	
	/** ���С���Ƿ���ײ������ */
	public boolean hit(Ground ground) {
		boolean hit = y + size / 2 > ground.y;
		if(hit) {
			y = ground.y - size / 2;
			alpha = (-1 * Math.PI) / 2;
		}
		
		return hit;
	}
	
	/** ���С���Ƿ�ײ������ */
	public boolean hit(Column column) {
		//���ȼ���Ƿ������ӷ�Χ��
		if(x > column.x - column.width / 2 - size / 2 
				&& x < column.x + column.width / 2 + size / 2) {
			//Ȼ�����Ƿ������ӵķ�϶��
			if(y > column.y - column.ColumnGap / 2 + size / 2
					&& y < column.y + column.ColumnGap / 2 - size / 2) {
				return false;
			}
			return true;
		}
		
		return false;
	}
	
	
	
	
	
	
	
	
	
	
}
