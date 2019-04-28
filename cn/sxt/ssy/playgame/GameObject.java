package cn.sxt.ssy.playgame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 * 游戏物体的父类。
 * @author 沈思宇
 *
 */

public class GameObject
{
	Image  img;//物体需要重复花自己，所需图片
	double  x,y;//物体的在frame 中的横坐标、纵坐标
	int   speed;//物体在frame中，移动的速度
	int  width, height;//物体的高度和宽度
	
	public  void  drawSelf(Graphics  g)
	{
		g.drawImage(img, (int)x,(int) y, null);
	}

	public GameObject(Image img, double x, double y, int speed, int width, int height) 
	{
		super();
		this.img = img;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.width = width;
		this.height = height;
	}

	public GameObject(Image img, double x, double y) 
	{
		super();
		this.img = img;
		this.x = x;
		this.y = y;
	}
	
	public GameObject()
	{
	}
	
	//矩形碰撞检测原理，返回物体的坐标和宽度、长度。
	public  Rectangle   getRect(){
		return  new Rectangle((int)x, (int)y, width, height);
	}

}
