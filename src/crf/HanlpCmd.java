package crf;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLSentence;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.ansj.splitWord.analysis.ToAnalysis;

public class HanlpCmd {
    public static String getCharset(String fileName) throws IOException{
        
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
    public static boolean isNumber(String str){
    	for (int i = 0; i < str.length(); i++){
    		if(!Character.isDigit(str.charAt(i)))
    				return false;
    	}
    	return true;
    }
	public static void write(String path, String content, String encoding)  
            throws IOException {  
        File file = new File(path);  
        file.delete();  
        file.createNewFile();  
        //FileWriter writer = new FileWriter(); 
//        writer.write(content);  
//        writer.close();  
    }  
	//����䷨����
	public static void hanlp1(String text) throws IOException {
		String []ss1=HanLP.parseDependency(text).toString().split("\t");
		String str1=null;
		String str2=null;
		String str3=null;
		int y1=0;
		int y2=0;
		int y3=0;
		int y4=0;
	    System.out.println(HanLP.parseDependency(text));
		//�Һ��Ĺ�ϵ��nȫ������
	    for(int x=0;x<ss1.length;x++){
			if(ss1[x].equals("���Ĺ�ϵ")){
				for(int i=0;i<ss1.length;i++){
					if(ss1[i].equals("�Ҹ��ӹ�ϵ"))
					y4=i;
				}
				//��ϵ
				for(int i=0;i<ss1.length;i++){
					if(ss1[i].equals("n")){
						//str2=ss1[i-5];
						y2=i-2;
					}
				}
				//��һ������
				for(int i=0;i<ss1.length;i++){
					if(ss1[i].equals("nr")){
						//str1=ss1[i-2];
						y1=i-2;
						break;
					}
				}
				//�ڶ�������
				for(int i=y1+10;i<ss1.length;i++){
					if(ss1[i].equals("nr")){
						//str3=ss1[i-2];
						y3=i-2;
					}
				}
				if(x<y4){
					str1=ss1[y1];
					str2=ss1[y2];
					str3=ss1[y3];
				}
				else if(x>y4){
					str1=ss1[y3];
					str2=ss1[y2];
					str3=ss1[y1];
				}
				break;
			}
		}
	    //System.out.println(ToAnalysis.parse(text));
		System.out.println(str1+"\t"+str2+"\t"+str3);
	}
	   
	//����ʵ��ʶ��
	public static String hanlp2(String text){
		String []ss1=HanLP.parseDependency(text).toString().split("\t");
		String str1=null;
		String str2=null;
		String str3=null;
		int y1=0;
		int y2=0;
		int y3=0;
		int y4=0;
	    for(int x=0;x<ss1.length;x++){
				if(ss1[x].equals("���Ĺ�ϵ")){
					for(int i=0;i<ss1.length;i++){
						if(ss1[i].equals("�Ҹ��ӹ�ϵ"))
						y4=i;
					}
					//��ϵ
					for(int i=0;i<ss1.length;i++){
						if(ss1[i].equals("n")){
							//str2=ss1[i-5];
							y2=i-2;
						}
					}
					//��һ������
					for(int i=0;i<ss1.length;i++){
						if(ss1[i].equals("nr")){
							//str1=ss1[i-2];
							y1=i-2;
							break;
						}
					}
					//�ڶ�������
					for(int i=y1+10;i<ss1.length;i++){
						if(ss1[i].equals("nr")){
							//str3=ss1[i-2];
							y3=i-2;
						}
					}
					if(x<y4){
						str1=ss1[y1];
						str2=ss1[y2];
						str3=ss1[y3];
					}
					else if(x>y4){
						str1=ss1[y3];
						str2=ss1[y2];
						str3=ss1[y1];
					}
					break;
				}
			}
		return str1+"\t"+str2+"\t"+str3;
	}
	public static void hanlp3(String filepath1,String filepath2) throws UnsupportedEncodingException, FileNotFoundException{
		
	}
	public static void main(String args[]) throws IOException{
		hanlp1("�������������");
//		String[] testCase = new String[]{
//			    "ǩԼ��ʽǰ���ع��١���ͺ㡢��͵�һͬ����˲μ�ǩԼ����ҵ�ҡ�",
//			    "����ǿ���߷塢�����ų�������ͷ��������С��",
//			    "�źƺͺ�������Ա�ؼ���",
//			    "���ܺ�С�������",
//			    "����۾��ֺͻ�����˵",
//			    "�����й�������й��¼�",
//			    "��ѧƽ���쵼,��ӱ����ǰ",
//			    };
//			Segment segment = HanLP.newSegment().enableNameRecognize(true);
//			for (String sentence : testCase)
//			{
//			    List<Term> termList = segment.seg(sentence);
//			    System.out.println(termList);
//			}
//          System.out.println(HanLP.parseDependency("��ã���ӭʹ��HanLP��"));		
	}
		
	

}
