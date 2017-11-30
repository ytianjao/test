package crf;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;



public class Command {
	public static void write(String path, String content, String encoding)  
            throws IOException {  
        File file = new File(path);  
        file.delete();  
        file.createNewFile();  
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(  
                new FileOutputStream(file), encoding));  
        writer.write(content);  
        writer.close();  
    }  
	
	public static void train(String filePath){
		String commandStr = "crf_learn  template "+filePath+" model";
		try {
			Process p = Runtime.getRuntime().exec(commandStr);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(),"utf-8"));
			String line = null;
			while ((line = br.readLine()) != null) {
				if(line.length() < 1){
					System.out.println("\n");
					continue;
				}
				else {
					System.out.println(line);
				}
					
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void test(String filePath,String filename){
		String commandStr = "crf_test -m model "+filePath+" >output.txt";
		try {
			Process p = Runtime.getRuntime().exec(commandStr);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(),"utf-8"));
			String line = null;
			String sb = new String();
			while ((line = br.readLine()) != null) {
				if(line.length() < 1){
					System.out.println("\n");
					sb+="\r\n";
					continue;
				}
				else {
					sb+=(line + "\r\n");
					System.out.println(line);
				}
					
			}
			write(filename+"_crfresult.txt", sb.toString(), "utf-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void score(String path, String encoding) throws IOException {  
		File file = new File(path);  
        BufferedReader reader = new BufferedReader(new InputStreamReader(  
                new FileInputStream(file), encoding));  
        String line = null;  
        int n = 0;
        int word_true=0;
        while ((line = reader.readLine()) != null) {       
        	if(line.length() < 1){
        		continue;
        	}
        	++n;
        	String[] ss = line.split("\t");
        	if(ss[1].equals(ss[2])){
        		word_true+=1;
        	}
        	else if(!(ss[1].equals(ss[2]))) {
        		System.out.println(ss[0]);
        	}
        }
        System.out.println("总字数"+n);
        System.out.println("正确字数"+word_true);
        Float p=(float) (word_true*1.0/n);
        System.out.println("正确率"+p);
	}
}
//	public static void exeCmd(String commandStr,String flag) {
//		BufferedReader br = null;
//		try {
////			Process p = Runtime.getRuntime().exec(commandStr);
////			br = new BufferedReader(new InputStreamReader(p.getInputStream(),"utf-8"));
////			String line = null;
////			StringBuilder sb = new StringBuilder();
////			while ((line = br.readLine()) != null) {
////				if(line.length() < 1){
////					System.out.println("\n");
////					sb.append("\n");
////					continue;
////				}
////				if(flag.equalsIgnoreCase("test"))
////					sb.append(line + "\n");
////				else
////					System.out.println(line);
////			}
////			if(flag.equalsIgnoreCase("test")) //测试时输出结果到文件
////				CRFFormat.write("data/output.txt", sb.toString(), "utf-8");
//		} catch (Exception e) {
//			e.printStackTrace();
//		} 
//		finally
//		{
//			if (br != null)
//			{
//				try {
//					br.close();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}


