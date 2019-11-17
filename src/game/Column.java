package game;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * 柱子类
 * @ClassName:  Column   
 * @author 曹佳伟
 * @version 1.0
 * @date Nov 14, 2019 11:01:34 PM
 *
 */
public class Column {
	BufferedImage image;
	
	//设置位置
	int x;
	int y;
	
	//宽和高
	int width;
	int high;
	
	//设置柱子之间的缝隙
	int ColumnGap;
	
	//设置柱子之间的距离
	int ColumnDistance;
	
	//随机数函数
	Random random = new Random();
	
	
	public Column(int n) {
		try {
			image = ImageIO.read(getClass().getResource("/BirdPng/column.png"));
		} catch (IOException e) {
			// TODO 输出异常信息
			System.out.println(e);
		}
		
		width = image.getWidth();
		high = image.getHeight();
		
		ColumnGap = 144;
		ColumnDistance = 245;
		
		x = 550 + (n - 1) * ColumnDistance;
		y = random.nextInt(218) + 132;
	}
	
	//向左移动
	public void step() {
		x--;
		if(x == -width / 2) {
			x = ColumnDistance * 2 - width / 2;
			y = random.nextInt(218) + 132;
		}
	}
	
}
