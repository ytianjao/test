package crf;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.ansj.splitWord.analysis.ToAnalysis;

import com.hankcs.hanlp.HanLP;

import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class MainWindows_TAB extends JFrame {

	private JPanel contentPane;
	private JTextField path;
	private Frame f;
	private FileDialog openDia;
	private File file;
	private String filepath;
	private String filename;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindows_TAB frame = new MainWindows_TAB();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
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

	/**
	 * Create the frame.
	 */
	public MainWindows_TAB() {
		f=new Frame("open");
		openDia=new FileDialog(f, "打开文件", FileDialog.LOAD);
		f.addWindowListener(new WindowAdapter(){
			public void windowclosing(WindowEvent e){
				System.exit(0);
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel label = new JLabel("\u529F\u80FD\u9009\u62E9\uFF1A");
		panel.add(label);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("人名识别", null, panel_1, null);
		panel_1.setLayout(null);
		
		
		
		JLabel label_1 = new JLabel("\u6587\u4EF6\u8DEF\u5F84\uFF1A");
		label_1.setBounds(14, 17, 100, 23);
		panel_1.add(label_1);
		
		path = new JTextField();
		path.setBounds(95, 14, 602, 24);
		panel_1.add(path);
		path.setColumns(10);
		
		JLabel label_2 = new JLabel("\u6587\u4EF6\u5185\u5BB9\uFF1A");
		label_2.setBounds(14, 53, 100, 23);
		panel_1.add(label_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(14, 74, 444, 496);
		panel_1.add(scrollPane);
		
		JTextArea text = new JTextArea();
		text.setLineWrap(true);
		scrollPane.setViewportView(text);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(509, 74, 444, 496);
		panel_1.add(scrollPane_1);
		
		JTextArea result = new JTextArea();
		result.setLineWrap(true);
		scrollPane_1.setViewportView(result);
		
		JLabel label_3 = new JLabel("\u8BC6\u522B\u7ED3\u679C\uFF1A");
		label_3.setBounds(509, 51, 100, 23);
		panel_1.add(label_3);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("关系抽取", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel label_4 = new JLabel("\u5206\u6790\u7ED3\u679C\uFF1A");
		label_4.setBounds(489, 17, 93, 26);
		panel_2.add(label_4);
		
		JLabel label_5 = new JLabel("\u53E5\u5B50\u5185\u5BB9\uFF1A");
		label_5.setBounds(14, 17, 93, 26);
		panel_2.add(label_5);
		
		JLabel label_6 = new JLabel("\u62BD\u53D6\u7ED3\u679C\uFF1A");
		label_6.setBounds(14, 337, 93, 26);
		panel_2.add(label_6);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_2.setBounds(491, 49, 462, 275);
		panel_2.add(scrollPane_2);
		
		JTextArea textArea_3 = new JTextArea();
		scrollPane_2.setViewportView(textArea_3);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_3.setBounds(14, 49, 462, 275);
		panel_2.add(scrollPane_3);
		
		JTextArea textArea_2 = new JTextArea();
		scrollPane_3.setViewportView(textArea_2);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_4.setBounds(14, 370, 939, 200);
		panel_2.add(scrollPane_4);
		
		JTextArea textArea_4 = new JTextArea();
		scrollPane_4.setViewportView(textArea_4);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("文本相似度", null, panel_3, null);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("文本分类", null, panel_4, null);
		
		JButton button = new JButton("\u6253\u5F00\u6587\u4EF6");
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
		button.setBounds(711, 13, 113, 27);
		panel_1.add(button);
		
		JButton btnAnsj = new JButton("\u4EBA\u540D\u8BC6\u522B");
		btnAnsj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				result.setText("");
				String line1=text.getText();
				result.append(ToAnalysis.parse(line1)+"\n");
			}
		});
		btnAnsj.setBounds(840, 13, 113, 27);
		panel_1.add(btnAnsj);
		
		JButton button_2 = new JButton("\u53E5\u6CD5\u5206\u6790");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea_3.setText(HanLP.parseDependency(textArea_2.getText()).toString());
			}
		});
		button_2.setBounds(840, 17, 113, 27);
		panel_2.add(button_2);
		
		JButton button_3 = new JButton("\u5173\u7CFB\u62BD\u53D6");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea_4.setText(HanlpCmd.hanlp2(textArea_2.getText()));
			}
		});
		button_3.setBounds(840, 337, 113, 27);
		panel_2.add(button_3);
	}
}
