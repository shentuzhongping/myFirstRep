package com.shentu.tank;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ResourceMagr {
	static BufferedImage goodTankU,goodTankD,goodTankL,goodTankR;
	static BufferedImage badTankU,badTankD,badTankL,badTankR;
	static BufferedImage bulletU,bulletD,bulletL,bulletR;
	static BufferedImage[] explodes = new BufferedImage[16];
	
	static {
		try {
			goodTankU = ImageIO.read(ResourceMagr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
			goodTankD = ImageUtil.rotateImage(goodTankU, 180);
			goodTankL = ImageUtil.rotateImage(goodTankU, -90);
			goodTankR = ImageUtil.rotateImage(goodTankU, 90);
			badTankU = ImageIO.read(ResourceMagr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
			badTankD = ImageUtil.rotateImage(badTankU, 180);
			badTankL = ImageUtil.rotateImage(badTankU, -90);
			badTankR = ImageUtil.rotateImage(badTankU, 90);
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
