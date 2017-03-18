import javax.swing.JFileChooser;
import javax.swing.JFrame;


public class MainClass extends JFrame {
	public MainClass(){
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Open a File");
		this.getContentPane().add(chooser);
		chooser.setVisible(true);
	}
	
	public static void main(String[] args) {
		JFrame frm = new MainClass();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setSize(500,400);
		frm.pack();
		frm.setVisible(true);
		frm.setLocationRelativeTo(null);
	}

}
