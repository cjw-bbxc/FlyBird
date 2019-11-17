package game;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 地面类
 * @ClassName:  Ground   
 * @author 曹佳伟
 * @version 1.0
 * @date Nov 14, 2019 11:01:49 PM
 *
 */
public class Ground {
	BufferedImage image;
	
	//位置
	int x;
	int y;
	
	//宽度和高度
	int width,high;
	
	public Ground() {
		// TODO 构造函数 初始化
		try {
			image = ImageIO.read(getClass().getResource("/BirdPng/ground.png"));
		} catch (IOException e) {
			// TODO 输出异常信息
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
