package test;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

import com.shentu.tank.ImageUtil;
import com.shentu.tank.ResourceMagr;

public class TankTest {

	@Test
	public void test() {
		try {
			BufferedImage tankU = ImageIO.read(ResourceMagr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
			assertNotNull(tankU);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
