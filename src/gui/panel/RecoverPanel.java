package gui.panel;
 
import javax.swing.JButton;
import javax.swing.JPanel;
 
import util.ColorUtil;
import util.GUIUtil;
 
public class RecoverPanel extends JPanel {
    static{
        GUIUtil.useLNF();
    }
    public static RecoverPanel instance = new RecoverPanel();
 
    JButton bRecover =new JButton("»Ö¸´");
 
    public RecoverPanel() {
 
        GUIUtil.setColor(ColorUtil.blueColor, bRecover);
        this.add(bRecover);
         
    }
 
    public static void main(String[] args) {
        GUIUtil.showPanel(RecoverPanel.instance);
    }
 
}