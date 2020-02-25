package com.shentu.tank;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ResourceMagr {
	static BufferedImage tankU,tankD,tankL,tankR,bulletU,bulletD,bulletL,bulletR;
	
	static {
		try {
			tankU = ImageIO.read(ResourceMagr.class.getClassLoader().getResourceAsStream("images/tankU.gif"));
			tankD = ImageIO.read(ResourceMagr.class.getClassLoader().getResourceAsStream("images/tankD.gif"));
			tankL = ImageIO.read(ResourceMagr.class.getClassLoader().getResourceAsStream("images/tankL.gif"));
			tankR = ImageIO.read(ResourceMagr.class.getClassLoader().getResourceAsStream("images/tankR.gif"));
			bulletU = ImageIO.read(ResourceMagr.class.getClassLoader().getResourceAsStream("images/bulletU.gif"));
			bulletD = ImageIO.read(ResourceMagr.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
			bulletL = ImageIO.read(ResourceMagr.class.getClassLoader().getResourceAsStream("images/bulletL.gif"));
			bulletR = ImageIO.read(ResourceMagr.class.getClassLoader().getResourceAsStream("images/bulletR.gif"));
					
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
