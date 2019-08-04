package com.ming.utils;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;


/**
 *  验证码生成
 */
public class ValidateCode extends ActionSupport {

    private int width = 64;
    private int height = 40;


    public String generateValidateCode() throws Exception{

        // 清空当且session
        ServletActionContext.getRequest().getSession().setMaxInactiveInterval(300);

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics graphics = bufferedImage.getGraphics();
        graphics.setColor(Color.decode("#45C3F1"));
        graphics.fillRect(0, 0, width, height);

        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(Color.WHITE);
        graphics2D.setFont(new Font("宋体", Font.BOLD, 25));

        String words = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";  //课后：1）设置为中文；2）新建一个记事本，每一行四个中文（成语），然后每次随机读取一行

        Random random = new Random();

        StringBuffer sb = new StringBuffer();

        int x = 5;

        for(int i = 0;i < 4;i++){
            //获取随机位置
            int randomIndex = random.nextInt(words.length());
            //拿到该位置的单词
            char randomChar = words.charAt(randomIndex);
            //拼接
            sb.append(randomChar);

            //设置倾斜角度 -30 - 30
            int degree = random.nextInt(60) - 30;
            //换成弧度
            double theta = Math.PI / 180 * degree;

            graphics2D.rotate(theta, x, 26);
            graphics2D.drawString(String.valueOf(randomChar), x, 26);
            graphics2D.rotate(-theta, x, 26);
            x += 12;
        }


        ImageIO.write(bufferedImage, "jpg", ServletActionContext.getResponse().getOutputStream());
        graphics.dispose();

        ServletActionContext.getRequest().getSession().setAttribute("checkCode", sb.toString());

        return NONE;
    }

}
