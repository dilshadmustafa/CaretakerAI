package com.dilmus.dilshad.boxworld;

import java.awt.*;
import javax.swing.*;

public class TView extends JPanel {

	private static final long serialVersionUID = 1L;
	private TMap m_map = null;

	public TView() {
		super();
	}
	
	public TView(TMap map) {
		super();
		m_map = map;
	}
	
	public void paint2(Graphics g) {
      g.setFont(new Font("",0,50));
      FontMetrics fm = getFontMetrics(new Font("",0,50));
      //String s = "A:FWMabc";
      String s = "WWWWWWWWWWWWWW";//x count 14, y count 11, dimension 700, 700
      int x = 5;
      int y = 5;
      int h = 0;
      
      for (int j = 0; j < 11; j++) {
    	  x = 5;
    	  y = y + h;
    	  
    	  for (int i = 0; i < s.length(); i++) {
	         char c = s.charAt(i);
	         h = fm.getHeight();
	         int w = fm.charWidth(c);
	         
	         //g.drawRect(x, y, w, h);
	         g.drawString(String.valueOf(c), x, y + h);
	         x = x + w;
	      }
      }
	}
	
  	public void paint(Graphics g) {
        g.setFont(new Font("",0,50));
        FontMetrics fm = getFontMetrics(new Font("",0,50));
        //String s = "A:FWMabc";
        //String s = "WWWWWWWWWWWWWW";//x 14, y 11, dimension 700, 700
        int x = 5;
        int y = 5;
        int h = 0;
        
        for (int j = 0; j < 11; j++) {
      	  x = 5;
      	  y = y + h;
      	  
      	  for (int i = 0; i < 14; i++) {
  	         char c = m_map.m_arrmap[j][i];
  	         h = fm.getHeight();
  	         //int w = fm.charWidth(c);
  	         int w = fm.charWidth('W'); //use fixed width
  	         g.drawRect(x, y, w, h);   
  	         g.drawString(String.valueOf(c), x, y + h);
  	         x = x + w;
  	      }
        }
      
   }
   public static void main(String[] args) {
      TMap map = new TMap();
	  JFrame frame = new JFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setContentPane(new TView(map));
      frame.setSize(700, 700);
      frame.setVisible(true);
   }
}

