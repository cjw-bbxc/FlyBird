package game;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * ������
 * @ClassName:  Column   
 * @author �ܼ�ΰ
 * @version 1.0
 * @date Nov 14, 2019 11:01:34 PM
 *
 */
public class Column {
	BufferedImage image;
	
	//����λ��
	int x;
	int y;
	
	//��͸�
	int width;
	int high;
	
	//��������֮��ķ�϶
	int ColumnGap;
	
	//��������֮��ľ���
	int ColumnDistance;
	
	//���������
	Random random = new Random();
	
	
	public Column(int n) {
		try {
			image = ImageIO.read(getClass().getResource("/BirdPng/column.png"));
		} catch (IOException e) {
			// TODO ����쳣��Ϣ
			System.out.println(e);
		}
		
		width = image.getWidth();
		high = image.getHeight();
		
		ColumnGap = 144;
		ColumnDistance = 245;
		
		x = 550 + (n - 1) * ColumnDistance;
		y = random.nextInt(218) + 132;
	}
	
	//�����ƶ�
	public void step() {
		x--;
		if(x == -width / 2) {
			x = ColumnDistance * 2 - width / 2;
			y = random.nextInt(218) + 132;
		}
	}
	
}
