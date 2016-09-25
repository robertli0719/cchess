/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package 中国象棋;
import java.util.ArrayList;
/**
 *
 * @author robert
 */
public class PCplayer
{
    public PCplayer()
    {

    }
    private Do[] sortDoList(ArrayList<Do> list,int player)
    {
        Do rtnlist[]=new Do[list.size()];
        list.toArray(rtnlist);
        for(int i=rtnlist.length-1;i>0;i--)
            for(int p=0;p<i;p++)
                if(hisTable.getFen(rtnlist[p],player)<hisTable.getFen(rtnlist[p+1],player))
                {
                    Do temp=rtnlist[p];
                    rtnlist[p]=rtnlist[p+1];
                    rtnlist[p+1]=temp;
                }
        return rtnlist;
    }
    private int Quies(int alpha,int beta,Pan pan)
    {
        if(pan.getWin()!=0)return pan.evaluate();
        if(zhash.hasValue(pan.getZobeist32(),pan.getZobeist64(),alpha,beta,0))
            return zhash.getValue();
        int val=pan.evaluate();
        if(val>=beta)return beta;
        if(val>alpha)alpha=val;

        ArrayList<Do> cando=pan.getCanDo();
        Do list[]=sortDoList(cando,pan.getPlayer());
        for(int i=0;i<list.length;i++)
        {
            if(list[i].zi==0)continue;
            pan.doIt(list[i]);
            val=-Quies(-beta,-alpha,pan);
            pan.back();
            if(val>=beta)return beta;
            if(val>alpha)alpha=val;
        }        
        return alpha;
    }
    
    private int AlphaBeta(Pan pan,int deep,int alpha,int beta)
    {
        if(deep==0 || pan.getWin()!=0)return Quies(alpha,beta,pan);
        if(zhash.hasValue(pan.getZobeist32(),pan.getZobeist64(),alpha,beta,deep))
            return zhash.getValue();

        int hashf=zhash.hashfALPHA;
        ArrayList<Do> cando=pan.getCanDo();
        Do list[]=sortDoList(cando,pan.getPlayer());
        Do bestDo=null;
        if(list.length==0)
            return -Pan.getMaxFen();
        for(int i=0;i<list.length;i++)
        {
            Do theDo=list[i];
            pan.doIt(theDo);
            int val=-AlphaBeta(pan,deep-1,-beta,-alpha);
            pan.back();
            if(val>=beta)
            {
                bestDo=theDo;zhash.putValue(pan.getZobeist32(),pan.getZobeist64(),beta,deep,zhash.hashfBETA);
                hisTable.addFen(bestDo.fx,bestDo.fy,bestDo.x,bestDo.y,deep,pan.getPlayer());return beta;
            }
            if(val>alpha){bestDo=theDo;alpha=val;hashf=zhash.hashfEXACT;}
        }        
        if(alpha>0 && Pan.getMaxFen()-alpha<100)
            return alpha-1;
        if(alpha<0 && Pan.getMaxFen()+alpha<100)
            return alpha+1;        
        zhash.putValue(pan.getZobeist32(),pan.getZobeist64(),alpha,deep,hashf);
        if(bestDo!=null)
        hisTable.addFen(bestDo.fx,bestDo.fy,bestDo.x,bestDo.y,deep,pan.getPlayer());
        return alpha;
    }
    private Do rootSearch(Pan pan,int deep)
    {
        //System.out.println("\n:"+pan.getZobeist32());
        int e=-Pan.getMaxFen();
        Do rtndo=null;
        int hashf=zhash.hashfALPHA;
        ArrayList<Do> cando=pan.getCanDo();
        for(int i=0;i<cando.size();i++)
        {
            pan.doIt(cando.get(i));            
            int em=-AlphaBeta(pan,deep-1,-Pan.getMaxFen(),-e);
            if(em>e)
            {
                e=em;
                rtndo=cando.get(i);
            }
            pan.back();
            Do d=cando.get(i);
            //System.out.println(ZI.getString(pan.getZi(d.fx, d.fy))+" 从fx:"+d.fx+" fy:"+d.fy+"到 x:"+d.x+" y:"+d.y+" 分数："+em);
        }
        zhash.putValue(pan.getZobeist32(),pan.getZobeist64(),e,deep-1,hashf);
        if(rtndo!=null)
        hisTable.addFen(rtndo.fx,rtndo.fy,rtndo.x,rtndo.y,deep,pan.getPlayer());
        System.out.println(e);
        return rtndo;
    }
    public ZobeistCreater getZobeistCreater(){return zober;}
    public Do getDo(Pan pan)
    {
        hisTable=new HistoryTable();
        Do rtnDo=null;
        long startTime=System.nanoTime();
        int deep=1;
        while(System.nanoTime()-startTime<3000000000L && deep<60)
        {
            System.out.print(deep+" "+(System.nanoTime()-startTime)/1000+" ");
            rtnDo=rootSearch(pan,deep);
            deep++;
        }
        System.out.println();
        return rtnDo;
    }
    private ZobeistCreater zober=new ZobeistCreater();
    private ZobeistHash zhash=new ZobeistHash(HashSize);
    private HistoryTable hisTable;
    private static final int DEEP=6;
    private static final int HashSize=1024*1024;

}
