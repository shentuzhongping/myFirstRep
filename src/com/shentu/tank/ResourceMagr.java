package com.shentu.tank;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ResourceMagr {
	static BufferedImage tankU,tankD,tankL,tankR;
	static BufferedImage bulletU,bulletD,bulletL,bulletR;
	static BufferedImage[] explodes = new BufferedImage[16];
	
	static {
		try {
			tankU = ImageIO.read(ResourceMagr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
			tankD = ImageUtil.rotateImage(tankU, 180);
			tankL = ImageUtil.rotateImage(tankU, -90);
			tankR = ImageUtil.rotateImage(tankU, 90);
			bulletU = ImageIO.read(ResourceMagr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
			bulletD = ImageUtil.rotateImage(bulletU, 180);
			bulletL = ImageUtil.rotateImage(bulletU, -90);
			bulletR = ImageUtil.rotateImage(bulletU, 90);
			for (int i = 0; i < 16; i++) {
				explodes[i] = ImageIO.read(ResourceMagr.class.getClassLoader().getResourceAsStream("images/e" + (i + 1) +".gif"));
			}
					
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
