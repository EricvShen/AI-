package cn.sxt.ssy.playgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

/**
 * 飞机游戏的主窗口
 * @author ���
 *
 */
public class MyGameFrame  extends  Frame 
{
	
	Image   planeImg  = GameUtil.getImage("images/plane.png");
	Image   bg  = GameUtil.getImage("images/bg.jpg");
	
	Plane   plane = new Plane(planeImg,250,250);
	Shell[]   shells = new Shell[50];
	
	Explode   bao ;
	Date  startTime = new Date();
	Date  endTime;
	int period;   
	
	
	public static void main(String[] args) 
	{
		MyGameFrame  f = new MyGameFrame();
		f.launchFrame();
	}
	
	@Override
	public void paint(Graphics g)
	{		//
		Color   c =  g.getColor();
		g.drawImage(bg, 0, 0, null);
		
		plane.drawSelf(g);  //画飞机
		
		//画出50个炮弹
		for(int i=0;i<shells.length;i++)
		{
			shells[i].draw(g);
			
			//碰撞检测
			boolean  peng = shells[i].getRect().intersects(plane.getRect());
			if(peng){
				plane.live = false;
				if(bao ==null){
					bao  = new Explode(plane.x, plane.y);
					
					endTime = new Date();
					period = (int)((endTime.getTime()-startTime.getTime())/1000);
				}
				bao.draw(g);
			}
			
			//判断飞机是否爆炸，并输出游戏时间
			if(!plane.live){
				g.setColor(Color.red);
				Font   f  =  new Font("", Font.BOLD, 50);
				g.setFont(f);
				g.drawString("ʱ"+period+"", (int)plane.x, (int)plane.y);
			}
			}
			
		}

	public void launchFrame()
	{
		// TODO Auto-generated method stub
		
	}
		
		g.setColor(c);
	}
	
	
	//这是一个内部类。多线程，重画飞机。
	class  PaintThread  extends  Thread 
	{
		@Override
		public void run() 
		{
			while(true)
			{
				repaint();		
				
				try {
					Thread.sleep(40);   	//1s=1000ms
				} catch (InterruptedException e) {
					e.printStackTrace();
				}		
			}
		}
		
	}
	
	//这也是个内部类。用于捕获在键盘上的代码。
	class   KeyMonitor extends  KeyAdapter 
	{

		@Override
		public void keyPressed(KeyEvent e) 
		{
			plane.addDirection(e);
		}

		@Override
		public void keyReleased(KeyEvent e)
		{
			plane.minusDirection(e);
		}
		
		
	}
	
	
	/**
	 *画出窗口
	 */
	public  void  launchFrame(){
		this.setTitle("尚学堂 沈");
		this.setVisible(true);
		this.setSize(Constant.GAME_WIDTH	, Constant.GAME_HEIGHT);
		this.setLocation(300, 300);
		
		
		this.addWindowListener(new WindowAdapter() 
		{
			@Override
			public void windowClosing(WindowEvent e) 
			{
				System.exit(0);
			}
		});
		
		new PaintThread().start();	//启动
		addKeyListener(new KeyMonitor());   //启动键盘监听
		
		
		for(int i=0;i<shells.length;i++)//调用shell的无参构造方法，new这50个炮弹，随后调用draw方法画出。
		{
			shells[i] = new Shell();
		}
		
	}
	
	
	
	private Image offScreenImage = null;
	 
	public void update(Graphics g)
	{
	    if(offScreenImage == null)
	        offScreenImage = this.createImage(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);//
	     
	    Graphics gOff = offScreenImage.getGraphics();
	    paint(gOff);
	    g.drawImage(offScreenImage, 0, 0, null);
	}

	private void paint(Graphics gOff)
	{
		// TODO Auto-generated method stub
		
	}

	private Image createImage(int gameWidth, int gameHeight)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
}
