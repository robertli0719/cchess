/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package 中国象棋;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.util.ArrayList;
/**
 *
 * @author robert
 */
public class MainPanel extends JPanel
{
    MainPanel()
    {
        this.addMouseListener(new MainPanelMouseListener());
        clsPan();        
    }
    private void paintZi(int x,int y,String z,Color color,Graphics2D g2)
    {
        int size=47;
        Font f=new Font("黑体",Font.BOLD,size-20);
        g2.setFont(f);
        g2.setColor(Color.ORANGE);
        Ellipse2D aEllipse=new Ellipse2D.Double(x,y,size,size);
        g2.fill(aEllipse);
        g2.setColor(color);
        g2.drawString(z, x+11, y+33);
    }
    private void paintPanGround(Graphics2D g2)
    {        
        for(int x=TX;x<TX+Size*8;x+=Size)
            for(int y=TY;y<TY+Size*4;y+=Size)
            {
                Rectangle2D aRrct=new Rectangle2D.Double(x,y,Size,Size);
                g2.draw(aRrct);
            }
        for(int x=TX;x<TX+Size*8;x+=Size)
            for(int y=TY+Size*5;y<TY+Size*9;y+=Size)
            {
                Rectangle2D aRrct=new Rectangle2D.Double(x,y,Size,Size);
                g2.draw(aRrct);
            }
        Line2D ln1=new Line2D.Double(TX,TY+Size*4,TX,TY+Size*5);
        g2.draw(ln1);
        Line2D ln2=new Line2D.Double(TX+Size*8,TY+Size*4,TX+Size*8,TY+Size*5);
        g2.draw(ln2);
        Line2D ln3=new Line2D.Double(TX+Size*3,TY,TX+Size*5,TY+Size*2);
        g2.draw(ln3);
        Line2D ln4=new Line2D.Double(TX+Size*3,TY+Size*2,TX+Size*5,TY);
        g2.draw(ln4);
        Line2D ln5=new Line2D.Double(TX+Size*3,TY+Size*7,TX+Size*5,TY+Size*9);
        g2.draw(ln5);
        Line2D ln6=new Line2D.Double(TX+Size*3,TY+Size*9,TX+Size*5,TY+Size*7);
        g2.draw(ln6);
        for(int y=TY+Size*3;y<=TY+Size*6;y+=Size*3)
        for(int x=TX;x<=TX+Size*8;x+=Size*2)
        {
            paintPoint(x,y,4,g2,x!=TX?true:false,x!=TX+Size*8?true:false);
        }
        paintPoint(TX+Size,TY+Size*2,4,g2,true,true);
        paintPoint(TX+Size,TY+Size*7,4,g2,true,true);
        paintPoint(TX+Size*7,TY+Size*2,4,g2,true,true);
        paintPoint(TX+Size*7,TY+Size*7,4,g2,true,true);
        

    }
    private void paintPoint(int x,int y,int size,Graphics2D g2,boolean L,boolean R)
    {
        int c=3;
        if(L){
        Line2D ln=new Line2D.Double(x-size,y-size*c,x-size,y-size);
        g2.draw(ln);
        ln.setLine(x-size,y-size,x-size*c,y-size);
        g2.draw(ln);
        ln.setLine(x-size,y+size,x-size*c,y+size);
        g2.draw(ln);
        ln.setLine(x-size,y+size,x-size,y+size*c);
        g2.draw(ln);
        }
        if(R){
        Line2D ln=new Line2D.Double(x+size,y-size*c,x+size,y-size);
        g2.draw(ln);
        ln.setLine(x+size,y-size,x+size*c,y-size);
        g2.draw(ln);
        ln.setLine(x+size,y+size,x+size*c,y+size);
        g2.draw(ln);
        ln.setLine(x+size,y+size,x+size,y+size*c);
        g2.draw(ln);
        }
    }

    private void paintZi(Pan pan,Graphics2D g2)
    {
        for(int y=0;y<10;y++)
        for(int x=0;x<9;x++)
        {
            int z=pan.getZi(x,y);
            if(z!=0)
            {
                if(z>0)paintZi(TopX+Size*x,TopY+Size*y,ZI.getString(z),Color.RED,g2);
                else paintZi(TopX+Size*x,TopY+Size*y,ZI.getString(z),Color.BLACK,g2);

            }
        }
    }
    public void paintComponent(Graphics g)
    {        
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;
        paintPanGround(g2);        
        paintZi(pan,g2);
        aXuanKuang.show(g2);
        
    }
    private class MainPanelMouseListener extends MouseAdapter
    {
        public void mouseClicked(MouseEvent e)
        {
            if(player!=pan.getPlayer())return;
            int x=e.getX()-TopX;
            int y=e.getY()-TopY;
            if(x<0 || y<0)
                return;
            x/=Size;y/=Size;
            if(x>8 || y>9)
                return;
            int X=aXuanKuang.getX(),Y=aXuanKuang.getY();            
            if(aXuanKuang.getVisible())
            {
                if(x==X && y==Y)
                return;
                if(pan.getZi(x,y)==0 || (pan.getPlayer()==1&&pan.getZi(x,y)<0) || (pan.getPlayer()==2&&pan.getZi(x,y)>0))
                {
                    aXuanKuang.setVisible(false);                    
                    Do theDo=new Do(x,y,pan.getZi(x,y),X,Y);
                    ArrayList<Do> cando=pan.getCanDo();
                    if(cando==null)
                    {
                        aXuanKuang.setVisible(false);
                        repaint();
                        javax.swing.JOptionPane.showMessageDialog(null,"游戏结束");
                        return;
                    }
                    for(int i=0;i<cando.size();i++)
                    {
                        if(cando.get(i).equals(theDo))
                        {
                            pan.doIt(theDo);
                            aXuanKuang.setLocation(theDo.x, theDo.y);
                            aXuanKuang.setVisible(true);
                            repaint();
                            new Thread(){
                            public void run(){                                
                                Do pcDo=aPCplayer.getDo(new Pan(pan,aPCplayer.getZobeistCreater()));
                                if(pcDo!=null)
                                {
                                    pan.doIt(pcDo);
                                    aXuanKuang.setLocation(pcDo.x, pcDo.y);
                                    aXuanKuang.setVisible(true);
                                }
                                else
                                    javax.swing.JOptionPane.showMessageDialog(null,"电脑认输");                                
                                repaint();
                            }

                            }.start();
                            
                            return;
                        }                        
                    }
                                   
                }
                else if((pan.getZi(x,y)>0 && pan.getPlayer()==1)||(pan.getZi(x,y)<0 && pan.getPlayer()==2))
                    aXuanKuang.setLocation(x,y);
            }
            else if(pan.getZi(x,y)!=0)
            {
                aXuanKuang.setLocation(x,y);
                aXuanKuang.setVisible(true);
            }
            repaint();            
        }
    }
    private class XuanKuang
    {
        XuanKuang()
        {}
        public void show(Graphics2D g2)
        {            
            if(Visible==false)return;
            g2.setColor(Color.RED);
            Line2D ln=new Line2D.Double(TopX+Size*x+3,TopY+Size*y+3,TopX+Size*x+3,TopY+Size*y+9);
            g2.draw(ln);
            ln.setLine(TopX+Size*x+3,TopY+Size*y+3,TopX+Size*x+9,TopY+Size*y+3);
            g2.draw(ln);

            ln.setLine(TopX+Size*x+3,TopY+Size*(y+1)-3,TopX+Size*x+3,TopY+Size*(y+1)-9);
            g2.draw(ln);
            ln.setLine(TopX+Size*x+3,TopY+Size*(y+1)-3,TopX+Size*x+9,TopY+Size*(y+1)-3);
            g2.draw(ln);

            ln.setLine(TopX+Size*(x+1)-3,TopY+Size*(y+1)-3,TopX+Size*(x+1)-9,TopY+Size*(y+1)-3);
            g2.draw(ln);
            ln.setLine(TopX+Size*(x+1)-3,TopY+Size*(y+1)-3,TopX+Size*(x+1)-3,TopY+Size*(y+1)-9);
            g2.draw(ln);

            ln.setLine(TopX+Size*(x+1)-3,TopY+Size*y+3,TopX+Size*(x+1)-9,TopY+Size*y+3);
            g2.draw(ln);
            ln.setLine(TopX+Size*(x+1)-3,TopY+Size*y+3,TopX+Size*(x+1)-3,TopY+Size*y+9);
            g2.draw(ln);
        }
        public void setVisible(boolean v)
        {
            if(!v){x=-1;y=-1;}
            Visible=v;
        }
        public void setLocation(int x,int y)
        {
            this.x=x;this.y=y;
        }
        public void setLocation_p(int x,int y)
        {
            this.x=x*Size+TopX;this.y=y*Size+TopY;
        }
        public int getX(){return x;}
        public int getY(){return y;}
        public int getXp(){return (int)((x-TopX)/Size);}
        public int getYp(){return (int)((y-TopY)/Size);}
        public boolean getVisible(){return Visible;}
        private int x=-1;
        private int y=-1;
        private boolean Visible=false;
    }

    public void clsPan()
    {
        pan=new Pan(aPCplayer.getZobeistCreater());
        aXuanKuang=new XuanKuang();        
        player=pan.getPlayer();
        repaint();
    }
    public void backPan(){pan.back();repaint();}
    private int TopX=50;
    private int TopY=50;
    private int Size=50;
    private int TX=TopX+Size/2,TY=TopY+Size/2;
    private Pan pan;
    private XuanKuang aXuanKuang;
    private static int player;
    private PCplayer aPCplayer=new PCplayer();
}

