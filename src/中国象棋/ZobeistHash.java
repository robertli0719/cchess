/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package 中国象棋;

/**
 *
 * @author 李理
 */
public class ZobeistHash
{
    public ZobeistHash(int hashsize)
    {
        HashSize=hashsize;
        Azobeist=new long[HashSize];
        Adeep=new int[HashSize];
        Aflags=new int[HashSize];
        Avalue=new int[HashSize];
    }
    public boolean hasValue(int zob32,long zob64,int alpha,int beta,int deep)
    {
        int v=zob32%HashSize;
        if(v<0)v=-v;        
        if(Aflags[v]==hashfNULL || Adeep[v]<deep || Azobeist[v]!=zob64)return false;
        rtnV=Avalue[v];
        if(Aflags[v]==hashfEXACT)return true;
        if(Aflags[v]==hashfALPHA && rtnV<=alpha){rtnV=alpha;return true;}
        if(Aflags[v]==hashfBETA && rtnV>=beta){rtnV=beta;return true;}
        return false;
    }
    public int getValue(){return rtnV;}
    public void putValue(int zob32,long zob64,int value,int deep,int flags)
    {
        int v=zob32%HashSize;
        if(v<0)v=-v;
        Adeep[v]=deep;
        Aflags[v]=flags;
        Avalue[v]=value;
        Azobeist[v]=zob64;
    }

    private int rtnV=0;
    private long Azobeist[];
    private int Adeep[];
    private int Aflags[];
    private int Avalue[];
    private int HashSize;
    public static final int hashfNULL=0;
    public static final int hashfEXACT=3;
    public static final int hashfALPHA=1;
    public static final int hashfBETA=2;
}
