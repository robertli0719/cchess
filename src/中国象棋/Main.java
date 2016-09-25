/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package 中国象棋;
import javax.swing.*;
import java.awt.BorderLayout;
/**
 *
 * @author robert
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       MainFrame aMainFrame=new MainFrame();
       aMainFrame.setDefaultCloseOperation(MainFrame.EXIT_ON_CLOSE);
       aMainFrame.setVisible(true);
         
        /*
        PCplayer pcplayer=new PCplayer();
        Pan pan=new Pan(pcplayer.getZobeistCreater());
        pcplayer.getDo(pan);
        */
       
    }

}

class MainFrame extends JFrame
{
    MainFrame()
    {
        setTitle("中国象棋");
        setLocation(400,100);
        setSize(600,600);
        aMainPanel=new MainPanel();
        add(new MainMenu(),BorderLayout.NORTH);
        add(aMainPanel);

    }
    class MainMenu extends JMenuBar
    {
        MainMenu()
        {
            Action action=new Action();
            JMenuItem a=new JMenuItem("重新开始");
            a.addActionListener(action);
            m1.add(a);
            a=new JMenuItem("悔棋");
            a.addActionListener(action);
            m1.add(a);
            add(m1);
        }
        class Action extends AbstractAction
        {
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                if(e.getActionCommand().equals("重新开始"))
                aMainPanel.clsPan();
                else if(e.getActionCommand().equals("悔棋"))
                    aMainPanel.backPan();
            }
        }
        private JMenu m1=new JMenu("控制");
    }
    MainPanel aMainPanel;

}
