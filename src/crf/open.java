
package crf;

import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import org.ansj.splitWord.analysis.ToAnalysis;

import com.hankcs.hanlp.HanLP;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class open {

	private JFrame frame;
	private Frame f;
	private FileDialog openDia;
	private File file;
	private JTextField path;
	private JTextArea text;
	private JTextArea result;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JLabel label_1;
	private JButton button;
	private JButton btnAnsj;
	private JButton btnClear;
	private JButton btnTrain;
	private JButton btnCrf;
	private String filepath;
	private String filename;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					open window = new open();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public open() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public String getCharset(String fileName) throws IOException{
        
        BufferedInputStream bin = new BufferedInputStream(new FileInputStream(fileName));  
        int p = (bin.read() << 8) + bin.read();  
        
        String code = null;  
        
        switch (p) {  
            case 0xefbb:  
                code = "UTF-8";  
                break;  
            case 0xfffe:  
                code = "Unicode";  
                break;  
            case 0xfeff:  
                code = "UTF-16BE";  
                break;  
            default:  
                code = "GBK";  
        }  
        return code;
}
	private void initialize() {
		f=new Frame("open");
		openDia=new FileDialog(f, "打开文件", FileDialog.LOAD);
		f.addWindowListener(new WindowAdapter(){
			public void windowclosing(WindowEvent e){
				System.exit(0);
			}
		});

		frame = new JFrame();
		frame.setBounds(100, 100, 999, 669);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u6587\u4EF6\u8DEF\u5F84\uFF1A");
		label.setBounds(21, 20, 75, 15);
		frame.getContentPane().add(label);
		
		path = new JTextField();
		path.setHorizontalAlignment(FlowLayout.RIGHT);
		path.setBounds(106, 17, 747, 21);
		frame.getContentPane().add(path);
		path.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(21, 77, 939, 237);
		frame.getContentPane().add(scrollPane);
		
		text = new JTextArea();
		text.setLineWrap(true);
		scrollPane.setViewportView(text);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(21, 354, 939, 219);
		frame.getContentPane().add(scrollPane_1);
		
		result = new JTextArea();
		result.setLineWrap(true);
		scrollPane_1.setViewportView(result);
		
		label_1 = new JLabel("\u6587\u4EF6\u9884\u89C8\uFF1A");
		label_1.setBounds(21, 54, 75, 15);
		frame.getContentPane().add(label_1);
		
		button = new JButton("\u6253\u5F00\u6587\u4EF6");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openDia.setVisible(true);
			    filepath=openDia.getDirectory();
				filename=openDia.getFile();
				if(filepath==null||filename==null)
					return;
				path.setText("");
				file=new File(filepath,filename);
				try {
					BufferedReader bufr = new BufferedReader(new InputStreamReader(  
					        new FileInputStream(file), getCharset(filepath+filename)));
					String line=null;
					while((line=bufr.readLine())!=null){
						text.append(line+"\n");
					}
					bufr.close();
					path.setText(filepath+filename);
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		button.setBounds(867, 16, 93, 23);
		frame.getContentPane().add(button);
		
		btnAnsj = new JButton("ansj\u5206\u8BCD");
		btnAnsj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				result.setText("");
				String line1=text.getText();
				result.append(ToAnalysis.parse(line1)+"\n");
			}
		});
		btnAnsj.setBounds(58, 586, 107, 23);
		frame.getContentPane().add(btnAnsj);
		
		btnClear = new JButton("\u6E05\u7A7A");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				result.setText("");
			}
		});
		btnClear.setBounds(853, 586, 107, 23);
		frame.getContentPane().add(btnClear);
		
		btnTrain = new JButton("\u8BAD\u7EC3");
		btnTrain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Command.train("train.data");
			}
		});
		btnTrain.setBounds(188, 586, 107, 23);
		frame.getContentPane().add(btnTrain);
		
		btnCrf = new JButton("crf\u751F\u6210");
		btnCrf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Command.test(filepath,filename);
			}
		});
		btnCrf.setBounds(318, 586, 107, 23);
		frame.getContentPane().add(btnCrf);
		
		JButton btnScore = new JButton("\u8BC4\u5206");
		btnScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Command.score("output.txt", "utf-8");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnScore.setBounds(732, 586, 107, 23);
		frame.getContentPane().add(btnScore);
		
		JLabel label_2 = new JLabel("\u6587\u672C\u5904\u7406\uFF1A");
		label_2.setBounds(21, 327, 75, 15);
		frame.getContentPane().add(label_2);
		
		JButton btnHanlp1 = new JButton("hanlp1");
		btnHanlp1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HanLP.Config.enableDebug(); 
				//HanlpCmd.hanlp1(text.getText());
				result.setText(HanLP.parseDependency(text.getText()).toString());
			}
		});
		btnHanlp1.setBounds(435, 586, 93, 23);
		frame.getContentPane().add(btnHanlp1);
	}
}
