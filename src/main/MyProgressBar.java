package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class MyProgressBar extends JPanel{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage img;
	private double progress;
	private Color color=Color.green;
	private int x=2;
	private double old;
	//构造函数
    public MyProgressBar() {super();//this.setSize(width,height);
    }
    
	public void setImg(Image img) {
		this.img=new BufferedImage(img.getWidth(this),img.getHeight(this), BufferedImage.TYPE_INT_RGB);		
	}
	public BufferedImage getImg() {return this.img;}
	public void setColor(Color c) {this.color=c;}
	public Color getColor() {return this.color;}

    @Override
    public void paintComponent(Graphics g) {
    	 //TODO Auto-generated method stub
    	super.paintComponent(g);
//    	Math.round()返回最近的long整数,四舍五入,保留三位小数
//    	d=Math.round(d*100)/100;
//    	向下转型
    	Graphics2D gra=(Graphics2D)g.create();
    	gra.setStroke(new BasicStroke(2.0f));
		gra.setColor(Color.black);
		gra.drawRect(1,1,getWidth()-2,getHeight()-2);       			
        gra.setColor(color);
        gra.fillRect(2,2,x-2,getHeight()-4); 
        gra.dispose();           
    }
    
    public void setProgress(double d) {
    	this.progress=d;     	   	
    	paintprogress();
    }
    public double getProgress() {return this.progress;}
	
    public void paintprogress() {
    	/*
    	 * 直接使用继承自paint()方法的graphics只能在画完后显示，但调用的getGraphics()能正常绘画，
    	 * 画完后结果却会消失。所以执行完该方法后会调用repaint()方法来一次性绘制消失的图形。
    	 * */
    	Graphics2D g=(Graphics2D)this.getGraphics();
    	while(old<progress) {    		    		   		
    		 g.setColor(color);
			 g.fillRect(x,2,(int)(0.01*getWidth()),getHeight()-4);			    		   			   
			 old+=0.01;
			 x+=(int)(0.01*getWidth());			
    		 try {
			    Thread.sleep(50);
			       } catch (InterruptedException e) {
			     e.printStackTrace();
			    }	
			}
    	 g.dispose();
    	}
}
