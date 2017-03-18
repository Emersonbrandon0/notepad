import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class EditableNotepadEx extends JFrame implements ActionListener {
	JFrame frm;
	JMenuBar mnubr;
	JMenu fileMenu,editMenu,helpMenu;
	JMenuItem cutItem,copyItem,pasteItem,selectAll,newFile,openFile,saveFile,saveFileAs,getHelp;
	JTextArea textArea;
	
	EditableNotepadEx(){
		super("NotePad");
		setLayout(new FlowLayout());	
		frm=new JFrame();
		frm.setResizable(false);
		
		cutItem=new JMenuItem("Cut");
		copyItem=new JMenuItem("Copy");
		pasteItem=new JMenuItem("Paste");
		selectAll=new JMenuItem("Select All");
		newFile=new JMenuItem("New");
		openFile=new JMenuItem("Open");
		saveFile=new JMenuItem("Save");
		saveFileAs=new JMenuItem("Save As");
		getHelp=new JMenuItem("Help");
		
		cutItem.addActionListener(this);
		copyItem.addActionListener(this);
		pasteItem.addActionListener(this);
		selectAll.addActionListener(this);
		getHelp.addActionListener(this);
		openFile.addActionListener(this);
		newFile.addActionListener(this);
		
		mnubr=new JMenuBar();
		mnubr.setBounds(0,0,1100,20);
		fileMenu=new JMenu("File");
		editMenu=new JMenu("Edit");
		helpMenu=new JMenu("Help");
		
		fileMenu.add(newFile);
		fileMenu.add(openFile);
		fileMenu.add(saveFile);
		fileMenu.add(saveFileAs);
		editMenu.add(cutItem);
		editMenu.add(copyItem);
		editMenu.add(pasteItem);
		editMenu.add(selectAll);
		helpMenu.add(getHelp);
		mnubr.add(fileMenu);
		mnubr.add(editMenu);
		mnubr.add(helpMenu);
		
		textArea=new JTextArea();
		textArea.setBounds(0,20,1100,540);
		frm.add(mnubr);
		frm.add(textArea);
		frm.setLayout(null);
		frm.setSize(1100,600);
		frm.setVisible(true);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setLocationRelativeTo(null);
	}
	public void actionPerformed(ActionEvent ae){
		if(ae.getSource()==cutItem)
			textArea.cut();
		else if (ae.getSource()==copyItem)
			textArea.copy();
		else if (ae.getSource()==pasteItem)
			textArea.paste();
		else if (ae.getSource()==selectAll)
			textArea.selectAll();
		else if (ae.getSource()==getHelp)
			JOptionPane.showMessageDialog(null,"Do you need help?");
		else if (ae.getSource()==openFile)
			FileChooserDialog();
		else if (ae.getSource()==newFile)
		{
			if((textArea.getText().replaceAll(" ",""))!="")
			{
				int newResult=JOptionPane.showConfirmDialog(frm, "Are you sure you would you like to create a new file?");
				if(newResult==JOptionPane.YES_OPTION)
				{
					textArea.setText("");
				}	
				else
				{
					System.out.println("I won't delete anything.");
				}
			}
			else
			{
				System.out.println("Nothing to delete.");
			}
		}
	}
	public void FileChooserDialog(){
		JFileChooser fileopen = new JFileChooser("C:\\Users\\emersonbrandon0\\workspace\\");
	    int ret = fileopen.showDialog(null, "Open file");
	    if (ret == JFileChooser.APPROVE_OPTION) {
	      File file = fileopen.getSelectedFile();
	      System.out.println(file.toString());
	      ReadFromFile(file.toString());
	    }
	}
	public void ReadFromFile(String file) {
		BufferedReader reader;
		String line;
		textArea.setText("");
		{
			try{
				reader = Files.newBufferedReader(Paths.get(file));
				line=reader.readLine();
				while((line=reader.readLine())!= null){
					System.out.println(line);
					textArea.append(line);
					textArea.append("\n");
				}
				reader.close();
			}
			catch(IOException ioEx){
				System.err.println("Failed to read from specified file!");
			}	
		}
	}
}
