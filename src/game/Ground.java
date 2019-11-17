package game;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * ������
 * @ClassName:  Ground   
 * @author �ܼ�ΰ
 * @version 1.0
 * @date Nov 14, 2019 11:01:49 PM
 *
 */
public class Ground {
	BufferedImage image;
	
	//λ��
	int x;
	int y;
	
	//��Ⱥ͸߶�
	int width,high;
	
	public Ground() {
		// TODO ���캯�� ��ʼ��
		try {
			image = ImageIO.read(getClass().getResource("/BirdPng/ground.png"));
		} catch (IOException e) {
			// TODO ����쳣��Ϣ
			System.out.println(e);
		}
		
		width = image.getWidth();
		high = image.getHeight();
		
		x = 0;
		y = 500;
	}
	
	public void step() {
		x--;
		if(x == -109) {
			x = 0;
		}
	}
}
