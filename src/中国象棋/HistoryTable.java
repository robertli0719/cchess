/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package 中国象棋;

/**
 *
 * @author 李理
 */
public class HistoryTable
{
    public HistoryTable()
    {
        arr=new int[9][10][9][10][2];
    }
    public void addFen(int fx,int fy,int x,int y,int deep,int player)
    {
        arr[fx][fy][x][y][player-1]+=deep*deep;
    }
    public int getFen(Do theDo,int player)
    {
        return arr[theDo.fx][theDo.fy][theDo.x][theDo.y][player-1];
    }    
    private int arr[][][][][];
}
