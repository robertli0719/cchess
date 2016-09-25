/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package 中国象棋;
import java.util.Random;
/**
 *
 * @author 李理
 */
public class ZobeistCreater
{
    public ZobeistCreater()
    {
        Random rand=new Random();
        for(int i=0;i<15;i++)
            for(int x=0;x<9;x++)
                for(int y=0;y<10;y++)
                    for(int c=0;c<2;c++)
                    {
                        arr64[i][x][y][c]=rand.nextLong();
                        arr32[i][x][y][c]=rand.nextInt();
                    }
    }
    public long getNum64(int zi,int x,int y,int c)
    {
        return arr64[zi+7][x][y][c-1];
    }
    public int getNum32(int zi,int x,int y,int c)
    {
        return arr32[zi+7][x][y][c-1];
    }
    public long getNum64(int pan[][],int player)
    {
        long num=player;
        for(int x=0;x<9;x++)
            for(int y=0;y<10;y++)
                num^=getNum64(pan[x][y],x,y,player);
        return num;
    }
    public int getNum32(int pan[][],int player)
    {
        int num=player;
        for(int x=0;x<9;x++)
            for(int y=0;y<10;y++)
                num^=getNum32(pan[x][y],x,y,player);
        return num;
    }
    public long arr64[][][][]=new long[15][9][10][2];
    public int arr32[][][][]=new int[15][9][10][2];
}
