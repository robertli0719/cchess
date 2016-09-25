/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package 中国象棋;
import java.util.ArrayList;
import java.util.Stack;
/**
 *
 * @author robert
 */

public class Pan
{
    public Pan(ZobeistCreater tzober)
    {
        zober=tzober;
        pan=new int[9][10];
        for(int y=0;y<=9;y++)
            for(int x=0;x<=8;x++)
                pan[x][y]=0;
        startPan();
    }
    public Pan(Pan pn,ZobeistCreater tzober)
    {
        zober=tzober;
        pan=new int[9][10];
        for(int y=0;y<=9;y++)
            for(int x=0;x<=8;x++)
                pan[x][y]=pn.pan[x][y];
        player=pn.player;
        win=pn.win;
        his=(Stack<Do>)pn.his.clone();
        createNum();
    }
    private void 車cando(final int x,final int y,ArrayList<Do> list)
    {
        if(pan[x][y]>0)
        {
            int i;
            for(i=x-1;i>=0 && pan[i][y]==0;i--)
                list.add(new Do(i,y,0,x,y));
            if(i>=0 && pan[i][y]<0)
                list.add(new Do(i,y,pan[i][y],x,y));
            for(i=x+1;i<=8 && pan[i][y]==0;i++)
                list.add(new Do(i,y,0,x,y));
            if(i<=8 && pan[i][y]<0)
                list.add(new Do(i,y,pan[i][y],x,y));

            for(i=y-1;i>=0 && pan[x][i]==0;i--)
                list.add(new Do(x,i,0,x,y));
            if(i>=0 && pan[x][i]<0)
                list.add(new Do(x,i,pan[x][i],x,y));
            for(i=y+1;i<=9 && pan[x][i]==0;i++)
                list.add(new Do(x,i,0,x,y));
            if(i<=9 && pan[x][i]<0)
                list.add(new Do(x,i,pan[x][i],x,y));
        }
        else if(pan[x][y]<0)
        {
            int i;
            for(i=x-1;i>=0 && pan[i][y]==0;i--)
                list.add(new Do(i,y,0,x,y));
            if(i>=0 && pan[i][y]>0)
                list.add(new Do(i,y,pan[i][y],x,y));
            for(i=x+1;i<=8 && pan[i][y]==0;i++)
                list.add(new Do(i,y,0,x,y));
            if(i<=8 && pan[i][y]>0)
                list.add(new Do(i,y,pan[i][y],x,y));

            for(i=y-1;i>=0 && pan[x][i]==0;i--)
                list.add(new Do(x,i,0,x,y));
            if(i>=0 && pan[x][i]>0)
                list.add(new Do(x,i,pan[x][i],x,y));
            for(i=y+1;i<=9 && pan[x][i]==0;i++)
                list.add(new Do(x,i,0,x,y));
            if(i<=9 && pan[x][i]>0)
                list.add(new Do(x,i,pan[x][i],x,y));
        }
    }
    private void 马cando(final int x,final int y,ArrayList<Do> list)
    {
        if(pan[x][y]>0)
        {
            if(x-2>=0 && pan[x-1][y]==0)
            {
                if(y-1>=0 && pan[x-2][y-1]<=0)
                    list.add(new Do(x-2,y-1,pan[x-2][y-1],x,y));
                if(y+1<=9 && pan[x-2][y+1]<=0)
                    list.add(new Do(x-2,y+1,pan[x-2][y+1],x,y));
            }
            if(x+2<9 && pan[x+1][y]==0)
            {
                if(y-1>=0 && pan[x+2][y-1]<=0)
                    list.add(new Do(x+2,y-1,pan[x+2][y-1],x,y));
                if(y+1<=9 && pan[x+2][y+1]<=0)
                    list.add(new Do(x+2,y+1,pan[x+2][y+1],x,y));
            }
            if(y-2>=0 && pan[x][y-1]==0)
            {
                if(x-1>=0 && pan[x-1][y-2]<=0)
                    list.add(new Do(x-1,y-2,pan[x-1][y-2],x,y));
                if(x+1<9 && pan[x+1][y-2]<=0)
                    list.add(new Do(x+1,y-2,pan[x+1][y-2],x,y));
            }
            if(y+2<=9 && pan[x][y+1]==0)
            {
                if(x-1>=0 && pan[x-1][y+2]<=0)
                    list.add(new Do(x-1,y+2,pan[x-1][y+2],x,y));
                if(x+1<9 && pan[x+1][y+2]<=0)
                    list.add(new Do(x+1,y+2,pan[x+1][y+2],x,y));
            }
        }
        else if(pan[x][y]<0)
        {
            if(x-2>=0 && pan[x-1][y]==0)
            {
                if(y-1>=0 && pan[x-2][y-1]>=0)
                    list.add(new Do(x-2,y-1,pan[x-2][y-1],x,y));
                if(y+1<=9 && pan[x-2][y+1]>=0)
                    list.add(new Do(x-2,y+1,pan[x-2][y+1],x,y));
            }
            if(x+2<9 && pan[x+1][y]==0)
            {
                if(y-1>=0 && pan[x+2][y-1]>=0)
                    list.add(new Do(x+2,y-1,pan[x+2][y-1],x,y));
                if(y+1<=9 && pan[x+2][y+1]>=0)
                list.add(new Do(x+2,y+1,pan[x+2][y+1],x,y));
            }
            if(y-2>=0 && pan[x][y-1]==0)
            {
                if(x-1>=0 && pan[x-1][y-2]>=0)
                    list.add(new Do(x-1,y-2,pan[x-1][y-2],x,y));
                if(x+1<9 && pan[x+1][y-2]>=0)
                    list.add(new Do(x+1,y-2,pan[x+1][y-2],x,y));
            }
            if(y+2<=9 && pan[x][y+1]==0)
            {
                if(x-1>=0 && pan[x-1][y+2]>=0)
                    list.add(new Do(x-1,y+2,pan[x-1][y+2],x,y));
                if(x+1<9 && pan[x+1][y+2]>=0)
                    list.add(new Do(x+1,y+2,pan[x+1][y+2],x,y));
            }
        }
    }
    private void 炮cando(final int x,final int y,ArrayList<Do> list)
    {
        if(pan[x][y]>0)
        {
            int i;
            for(i=x-1;i>=0 && pan[i][y]==0;i--)
                list.add(new Do(i,y,0,x,y));
            if(i>=1)
            {
                for(i--;i>=0;i--)
                    if(pan[i][y]>0)break;
                    else if(pan[i][y]<0)
                    {list.add(new Do(i,y,pan[i][y],x,y));break;}
            }

            for(i=x+1;i<=8 && pan[i][y]==0;i++)
                list.add(new Do(i,y,0,x,y));
            if(i<=7)
            {
                for(i++;i<=8;i++)
                    if(pan[i][y]>0)break;
                    else if(pan[i][y]<0)
                        {list.add(new Do(i,y,pan[i][y],x,y));break;}
            }

            for(i=y-1;i>=0 && pan[x][i]==0;i--)
                list.add(new Do(x,i,0,x,y));
            if(i>=1)
            {
                for(i--;i>=0;i--)
                    if(pan[x][i]>0)break;
                    else if(pan[x][i]<0)
                        {list.add(new Do(x,i,pan[x][i],x,y));break;}
            }

            for(i=y+1;i<=9 && pan[x][i]==0;i++)
                list.add(new Do(x,i,0,x,y));
            if(i<=8)
            {
                for(i++;i<=9;i++)
                    if(pan[x][i]>0)break;
                    else if(pan[x][i]<0)
                        {list.add(new Do(x,i,pan[x][i],x,y));break;}
            }
        }
        else if(pan[x][y]<0)
        {
            int i;
            for(i=x-1;i>=0 && pan[i][y]==0;i--)
                list.add(new Do(i,y,0,x,y));
            if(i>=1)
            {
                for(i--;i>=0;i--)
                    if(pan[i][y]<0)break;
                    else if(pan[i][y]>0)
                    {list.add(new Do(i,y,pan[i][y],x,y));break;}
            }

            for(i=x+1;i<=8 && pan[i][y]==0;i++)
                list.add(new Do(i,y,0,x,y));
            if(i<=7)
            {
                for(i++;i<=8;i++)
                    if(pan[i][y]<0)break;
                    else if(pan[i][y]>0)
                        {list.add(new Do(i,y,pan[i][y],x,y));break;}
            }

            for(i=y-1;i>=0 && pan[x][i]==0;i--)
                list.add(new Do(x,i,0,x,y));
            if(i>=1)
            {
                for(i--;i>=0;i--)
                    if(pan[x][i]<0)break;
                    else if(pan[x][i]>0)
                        {list.add(new Do(x,i,pan[x][i],x,y));break;}
            }

            for(i=y+1;i<=9 && pan[x][i]==0;i++)
                list.add(new Do(x,i,0,x,y));
            if(i<=8)
            {
                for(i++;i<=9;i++)
                    if(pan[x][i]<0)break;
                    else if(pan[x][i]>0)
                        {list.add(new Do(x,i,pan[x][i],x,y));break;}
            }
        }
    }
    private void 兵cando(final int x,final int y,ArrayList<Do> list)
    {
        if(pan[x][y]>0)
        {
            if(y!=0 && pan[x][y-1]<=0)
                list.add(new Do(x,y-1,pan[x][y-1],x,y));
            if(y<5)
            {
                if(x!=0 && pan[x-1][y]<=0)
                    list.add(new Do(x-1,y,pan[x-1][y],x,y));
                if(x!=8 && pan[x+1][y]<=0)
                    list.add(new Do(x+1,y,pan[x+1][y],x,y));
            }
        }
        else if(pan[x][y]<0)
        {
            if(y!=9 && pan[x][y+1]>=0)
                list.add(new Do(x,y+1,pan[x][y+1],x,y));
            if(y>=5)
            {
                if(x!=0 && pan[x-1][y]>=0)
                    list.add(new Do(x-1,y,pan[x-1][y],x,y));
                if(x!=8 && pan[x+1][y]>=0)
                    list.add(new Do(x+1,y,pan[x+1][y],x,y));
            }
        }

    }
    private void 相cando(final int x,final int y,ArrayList<Do> list)
    {
        if(pan[x][y]>0)
        {
            if(x!=0)
            {
                if(y!=9 && pan[x-1][y+1]==0 && pan[x-2][y+2]<=0)
                    list.add(new Do(x-2,y+2,pan[x-2][y+2],x,y));
                if(y!=5 && pan[x-1][y-1]==0 && pan[x-2][y-2]<=0)
                    list.add(new Do(x-2,y-2,pan[x-2][y-2],x,y));
            }
            if(x!=8)
            {
                if(y!=9 && pan[x+1][y+1]==0 && pan[x+2][y+2]<=0)
                    list.add(new Do(x+2,y+2,pan[x+2][y+2],x,y));
                if(y!=5 && pan[x+1][y-1]==0 && pan[x+2][y-2]<=0)
                    list.add(new Do(x+2,y-2,pan[x+2][y-2],x,y));
            }
        }
        else if(pan[x][y]<0)
        {
            if(x!=0)
            {
                if(y!=4 && pan[x-1][y+1]==0 && pan[x-2][y+2]>=0)
                    list.add(new Do(x-2,y+2,pan[x-2][y+2],x,y));
                if(y!=0 && pan[x-1][y-1]==0 && pan[x-2][y-2]>=0)
                    list.add(new Do(x-2,y-2,pan[x-2][y-2],x,y));
            }
            if(x!=8)
            {
                if(y!=4 && pan[x+1][y+1]==0 && pan[x+2][y+2]>=0)
                    list.add(new Do(x+2,y+2,pan[x+2][y+2],x,y));
                if(y!=0 && pan[x+1][y-1]==0 && pan[x+2][y-2]>=0)
                    list.add(new Do(x+2,y-2,pan[x+2][y-2],x,y));
            }
        }
    }
    private void 士cando(final int x,final int y,ArrayList<Do> list)
    {
        if(pan[x][y]>0)
        {
            if(x!=4 && pan[4][8]<=0)
                list.add(new Do(4,8,pan[4][8],x,y));
            if(x==4)
            {
                if(pan[3][7]<=0)
                    list.add(new Do(3,7,pan[3][7],x,y));
                if(pan[3][9]<=0)
                    list.add(new Do(3,9,pan[3][9],x,y));
                if(pan[5][7]<=0)
                    list.add(new Do(5,7,pan[5][7],x,y));
                if(pan[5][9]<=0)
                    list.add(new Do(5,9,pan[5][9],x,y));
            }
        }
        else if(pan[x][y]<0)
        {
            if(x!=4 && pan[4][1]>=0)
                list.add(new Do(4,1,pan[4][1],x,y));
            if(x==4)
            {
                if(pan[3][0]>=0)
                    list.add(new Do(3,0,pan[3][0],x,y));
                if(pan[3][2]>=0)
                    list.add(new Do(3,2,pan[3][2],x,y));
                if(pan[5][0]>=0)
                    list.add(new Do(5,0,pan[5][0],x,y));
                if(pan[5][2]>=0)
                    list.add(new Do(5,2,pan[5][2],x,y));
            }
        }
    }
    private void 将cando(final int x,final int y,ArrayList<Do> list)
    {
        if(pan[x][y]>0)
        {
            if(x!=5 && pan[x+1][y]<=0)
                list.add(new Do(x+1,y,pan[x+1][y],x,y));
            if(x!=3 && pan[x-1][y]<=0)
                list.add(new Do(x-1,y,pan[x-1][y],x,y));
            if(y!=7 && pan[x][y-1]<=0)
                list.add(new Do(x,y-1,pan[x][y-1],x,y));
            if(y!=9 && pan[x][y+1]<=0)
                list.add(new Do(x,y+1,pan[x][y+1],x,y));
            upLastOne将(x,y,list);
        }
        else if(pan[x][y]<0)
        {
            if(x!=5 && pan[x+1][y]>=0)
                list.add(new Do(x+1,y,pan[x+1][y],x,y));
            if(x!=3 && pan[x-1][y]>=0)
                list.add(new Do(x-1,y,pan[x-1][y],x,y));
            if(y!=2 && pan[x][y+1]>=0)
                list.add(new Do(x,y+1,pan[x][y+1],x,y));
            if(y!=0 && pan[x][y-1]>=0)
                list.add(new Do(x,y-1,pan[x][y-1],x,y));
            downLastOne将(x,y,list);
        }
    }
    private void upLastOne将(int X,int Y,ArrayList<Do> list)
    {
        int x=X,y=Y-1;
        while(pan[x][y]==0 && y!=0)
            y--;
        if((pan[x][y]>=0?pan[x][y]:-pan[x][y])==ZI.将)
           list.add(new Do(x,y,pan[x][y],X,Y));
    }
    private void downLastOne将(int X,int Y,ArrayList<Do> list)
    {
        int x=X,y=Y+1;
        while(pan[x][y]==0 && y!=9)
            y++;
        if((pan[x][y]>=0?pan[x][y]:-pan[x][y])==ZI.将)
            list.add(new Do(x,y,pan[x][y],X,Y));
    }
    private void canDo(final int x,final int y,ArrayList<Do> list)
    {
        switch(pan[x][y])
        {                        
            case ZI.兵:case -ZI.兵:
                兵cando(x,y,list);
                break;                
            case ZI.士:case -ZI.士:
                士cando(x,y,list);                
                break;                      
            case ZI.将:case -ZI.将:                               
                将cando(x,y,list);                           
                break;                
            case ZI.炮:case -ZI.炮:
                炮cando(x,y,list);                
                break;                
            case ZI.相:case -ZI.相:
                相cando(x,y,list);                
                break;                
            case ZI.車:case -ZI.車:        
                車cando(x,y,list);                
                break;                
            case ZI.马:case -ZI.马:            
                马cando(x,y,list);                
                break;                   
        }
    }
    public void startPan()
    {
        pan[0][9]=ZI.車;
        pan[1][9]=ZI.马;
        pan[2][9]=ZI.相;
        pan[3][9]=ZI.士;
        pan[4][9]=ZI.将;
        pan[5][9]=ZI.士;
        pan[6][9]=ZI.相;
        pan[7][9]=ZI.马;
        pan[8][9]=ZI.車;
        pan[1][7]=ZI.炮;
        pan[7][7]=ZI.炮;
        for(int i=0;i<=8;i+=2)
            pan[i][6]=ZI.兵;
        pan[0][0]=-ZI.車;
        pan[1][0]=-ZI.马;
        pan[2][0]=-ZI.相;
        pan[3][0]=-ZI.士;
        pan[4][0]=-ZI.将;
        pan[5][0]=-ZI.士;
        pan[6][0]=-ZI.相;
        pan[7][0]=-ZI.马;
        pan[8][0]=-ZI.車;
        pan[1][2]=-ZI.炮;
        pan[7][2]=-ZI.炮;
        for(int i=0;i<=8;i+=2)
            pan[i][3]=-ZI.兵;        
      
        //************
   
        //************
        player=Rplayer;
        win=0;
        his=new Stack<Do>();
        createNum();
    }
    private void createNum()
    {
        p1Fen=0;
        p2Fen=0;
        for(int x=0;x<9;x++)
            for(int y=0;y<10;y++)
                if(pan[x][y]>0)
                    p1Fen+=FenCounter.getFen(x, y,pan[x][y]);
                else
                    p2Fen+=FenCounter.getFen(x, y,pan[x][y]);
        Zobeist32=zober.getNum32(pan, player);
        Zobeist64=zober.getNum64(pan, player);
    }
    public int evaluate()
    {
        if(win==player)return getMaxFen();
        if(win==player%2+1)return -getMaxFen();
        if(player==Rplayer)return p1Fen-p2Fen;
        else return p2Fen-p1Fen;
    }
    public static int getMaxFen()
    {
        return 100000000;
    }
    public int getFen()
    {
        if(win==Rplayer)return getMaxFen();
        if(win==Bplayer)return -getMaxFen();
        return p1Fen-p2Fen;
        /*
        int fen=0;
        for(int y=0;y<=9;y++)
            for(int x=0;x<=8;x++)
                switch(pan[x][y])
                {
                    case ZI.兵:  fen+=10;if(y<=4)fen+=20;break;
                    case -ZI.兵: fen-=10;if(y>=5)fen-=20;break;
                    case ZI.士:  fen+=20;break;
                    case -ZI.士: fen-=20;break;
                    case ZI.将:  fen+=10000;break;
                    case -ZI.将: fen-=10000;break;
                    case ZI.炮:  fen+=45;break;
                    case -ZI.炮: fen-=45;break;
                    case ZI.相:  fen+=20;break;
                    case -ZI.相: fen-=20;break;
                    case ZI.車:  fen+=90;break;
                    case -ZI.車: fen-=90;break;
                    case ZI.马:  fen+=40;break;
                    case -ZI.马: fen-=40;break;
                }
                
        return fen;
         * */
    }
    public int getZi(int x,int y)
    {return pan[x][y];}
    public int getPlayer(){return player;}
    public ArrayList<Do> getCanDo()
    {        
        ArrayList<Do> rtnList=new  ArrayList<Do>(0);
        if(win!=0)return rtnList;

        if(player==Rplayer)
        for(int y=0;y<=9;y++)
            for(int x=0;x<=8;x++)
                if(pan[x][y]>0)
                canDo(x,y,rtnList);
        if(player==Bplayer)
        for(int y=0;y<=9;y++)
            for(int x=0;x<=8;x++)
                if(pan[x][y]<0)
                canDo(x,y,rtnList);
        return rtnList;
    }
    public int getWin(){return win;}
    public void doIt(Do d)
    {        
        his.push(d);
        int fzi=pan[d.fx][d.fy];
        Zobeist32^=zober.getNum32(fzi,d.fx,d.fy,player);
        Zobeist32^=zober.getNum32(fzi,d.x,d.y,player);
        Zobeist32^=zober.getNum32(d.zi,d.x,d.y,player%2+1);
        Zobeist64^=zober.getNum64(fzi,d.fx,d.fy,player);
        Zobeist64^=zober.getNum64(fzi,d.x,d.y,player);
        Zobeist64^=zober.getNum64(d.zi,d.x,d.y,player%2+1);
        if(player==Rplayer)
        {
            p1Fen-=FenCounter.getFen(d.fx,d.fy,fzi);
            p1Fen+=FenCounter.getFen(d.x,d.y,fzi);
            p2Fen-=FenCounter.getFen(d.x,d.y,pan[d.x][d.y]);
        }
        else
        {
            p2Fen-=FenCounter.getFen(d.fx,d.fy,fzi);
            p2Fen+=FenCounter.getFen(d.x,d.y,fzi);
            p1Fen-=FenCounter.getFen(d.x,d.y,pan[d.x][d.y]);
        }
        pan[d.x][d.y]=fzi;
        pan[d.fx][d.fy]=0;
        if(player==Rplayer)player=Bplayer;
        else player=Rplayer;

        if(d.zi==ZI.将)win=Bplayer;
        else if(d.zi==-ZI.将)win=Rplayer;
    }
    public boolean back()
    {
        win=0;
        if(his.empty())return false;
        Do d=his.pop();
        pan[d.fx][d.fy]=pan[d.x][d.y];
        pan[d.x][d.y]=d.zi;
        if(player==Rplayer)player=Bplayer;
        else player=Rplayer;
        int fzi=pan[d.fx][d.fy];
        if(player==Rplayer)
        {
            p1Fen+=FenCounter.getFen(d.fx,d.fy,fzi);
            p1Fen-=FenCounter.getFen(d.x,d.y,fzi);
            p2Fen+=FenCounter.getFen(d.x,d.y,pan[d.x][d.y]);
        }
        else
        {
            p2Fen+=FenCounter.getFen(d.fx,d.fy,fzi);
            p2Fen-=FenCounter.getFen(d.x,d.y,fzi);
            p1Fen+=FenCounter.getFen(d.x,d.y,pan[d.x][d.y]);
        }
        Zobeist32^=zober.getNum32(fzi,d.fx,d.fy,player);
        Zobeist32^=zober.getNum32(fzi,d.x,d.y,player);
        Zobeist32^=zober.getNum32(d.zi,d.x,d.y,player%2+1);
        Zobeist64^=zober.getNum64(fzi,d.fx,d.fy,player);
        Zobeist64^=zober.getNum64(fzi,d.x,d.y,player);
        Zobeist64^=zober.getNum64(d.zi,d.x,d.y,player%2+1);
        return true;
    }
    public int getZobeist32(){return Zobeist32;}
    public long getZobeist64(){return Zobeist64;}
    public int getHisNum(){return his.size();}
    private int[][] pan;
    private int player;
    private int win=0;
    private int p1Fen;
    private int p2Fen;
    private final int Rplayer=1,Bplayer=2;
    private Stack<Do> his;
    private int Zobeist32;
    private long Zobeist64;
    private ZobeistCreater zober;
}

class ZI
{
    static final int 車=7;
    static final int 马=6;
    static final int 炮=5;
    static final int 兵=4;
    static final int 相=3;
    static final int 士=2;
    static final int 将=1;
    static String getString(int n)
    {
        n=n>0?n:-n;
        switch(n)
        {
            case 7:
                return "車";
            case 6:
                return "马";
            case 5:
                return "炮";
            case 4:
                return "兵";
            case 3:
                return "相";
            case 2:
                return "士";
            case 1:
                return "将";
        }
        return null;
    }
}

class Do
{
    public Do(int x,int y,int zi,int fx,int fy)
    {
        this.x=x;this.y=y;this.zi=zi;this.fx=fx;this.fy=fy;
    }
    public Do(Do other)
    {x=other.x;y=other.y;zi=other.zi;fx=other.fx;fy=other.fy;}
    public boolean equals(Do other)
    {
        if(equalsHaventZi(other) && zi==other.zi)
            return true;
        else
            return false;
    }
    public boolean equalsHaventZi(Do other)
    {
        if(x==other.x && y==other.y && fx==other.fx && fy==other.fy)
            return true;
        else
            return false;
    }
    public int x;
    public int y;
    public int fx;
    public int fy;
    public int zi;
}
