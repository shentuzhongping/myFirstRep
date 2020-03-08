package com.shentu.tank;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.Assert.*;

public class TankTest1 {
    @Test
    public void paint(){
        try {
            BufferedImage tankU = ImageIO.read(ResourceMagr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
            assertNotNull(tankU);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}