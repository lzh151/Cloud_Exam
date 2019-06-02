package servlet;

import com.sun.webkit.dom.RGBColorImpl;
import org.w3c.dom.css.CSSPrimitiveValue;
import org.w3c.dom.css.RGBColor;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        int width = 200;
        int height = 100;

        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();//画笔对象

        Random random = new Random();
        int R = random.nextInt(255);
        int G = random.nextInt(255);
        int B = random.nextInt(255);
        Color color = new Color(R,G,B);

        g.setColor(color);//设置画笔颜色
        g.fillRect(0,0,width,height);

        R = random.nextInt(255);
        G = random.nextInt(255);
        B = random.nextInt(255);
        color = new Color(R,G,B);

        g.setColor(color);
        g.drawRect(0,0,width - 1,height - 1);

        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghigklmnopqrstuvwxyz0123456789";

        Random ran = new Random();

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i <= 4; i++) {
            int index = ran.nextInt(str.length());
            char ch = str.charAt(index);//随机字符
            stringBuilder.append(ch);
            g.setFont(new Font("Quicksand",Font.BOLD,50));
            g.drawString(ch+"",width/5*i,height/2);
        }
        String checkCode_session  = stringBuilder.toString();
        request.getSession().setAttribute("checkCode_session",checkCode_session);

        for (int i = 0; i < 10; i++) {
            R = random.nextInt(255);
            G = random.nextInt(255);
            B = random.nextInt(255);
            color = new Color(R,G,B);
            g.setColor(color);
            int x1 = ran.nextInt(width);
            int x2 = ran.nextInt(width);

            int y1 = ran.nextInt(height);
            int y2 = ran.nextInt(height);
            g.drawLine(x1,y1,x2,y2);
        }

        ImageIO.write(image,"jpg",response.getOutputStream());


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
