package cn.sxt.ssy.playgame;

import java.awt.Color;
import java.awt.Graphics;

/**
 * 炮弹类和飞机类一样，都要继承gameobject，因为他们都有相同的一些属性，比如坐标、飞行速度、宽段高度等。
 * @author 沈
 *
 */
public class Shell   extends  GameObject 
{
	
	double  degree;
	
	public  Shell()
	{
		x = 200;
		y = 200;
		width=10;
		height = 10;
		speed = 3;
		degree = Math.random()*Math.PI*2;
	}
	
	public  void   draw(Graphics  g)
	{
		Color   c =  g.getColor();
		g.setColor(Color.YELLOW);
		
		g.fillOval((int)x,(int) y, width, height);
		
		
		x += speed*Math.cos(degree);//炮弹一开始沿着任意角度飞。
		y += speed*Math.sin(degree);
		
		
		if(x<0||x>Constant.GAME_WIDTH-width)//不要让炮弹跑出frame。
		{
			degree  = Math.PI - degree;
		}
		
		if(y<30||y>Constant.GAME_HEIGHT-height)
		{
			degree  = - degree;
		}
		
		
		
		
		g.setColor(c);
	}
	
}
