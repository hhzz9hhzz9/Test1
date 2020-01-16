package com.woniuxy.servlets;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckCodeServlet
 */
@WebServlet("/checkCode.do")
public class CheckCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckCodeServlet() {
        super();
    }
    private Random random=new Random();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedImage image = new BufferedImage(100, 30, BufferedImage.TYPE_INT_BGR);
		
		Graphics graphics = image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0,0,100,30);
		 //生成干扰线
        for (int i=0;i<10;i++){
            graphics.setColor(getRandomColor());
            int x1=random.nextInt(100);
            int y1=random.nextInt(30);
            int x2=random.nextInt(100);
            int y2=random.nextInt(30);
            graphics.drawLine(x1,y1,x2,y2);
        }
        String baseNums = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ";
        StringBuilder builder = new StringBuilder();
        int len = baseNums.length();
        for (int i = 0; i < 4; i++) {
        	builder.append(baseNums.charAt(random.nextInt(len)));
		}
        request.getSession().setAttribute("checkCode",builder.toString());
        
        graphics.setFont(new Font("微软雅黑", Font.BOLD, 25));
        for (int i=0;i<builder.toString().length();i++)
        {
            graphics.setColor(getRandomColor());
          
            char item=builder.toString().charAt(i);
            graphics.drawString(item+"",5+(i*22),25); 
            
        }
		ImageIO.write(image,"png",response.getOutputStream());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	  private Color getRandomColor(){
	        int r=random.nextInt(256);
	         int g=random.nextInt(256);
	         int b=random.nextInt(256);
	        return new Color(r,g,b); 
	  
	     }
}
