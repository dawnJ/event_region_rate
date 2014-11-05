package event_regionid_rate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Set;

public class ReadFile{
	
	public void dealSumFile() {
		// TODO 读取node文件
		HashMap<String, String> map0=new HashMap<String, String>();
		HashMap<String, String> map1=new HashMap<String, String>();
		FileReader fr1;
		FileWriter fw;
		try {
			fw = new FileWriter("E:\\NRDC1202\\PersonalWork\\zjj\\YWJ\\区域转移概率矩阵实验计算\\结果\\201103event,regionid,sum.txt");
			for(int i=0;i<5;i++){
				int j=i+3;
				fr1=new FileReader("E:\\NRDC1202\\PersonalWork\\zjj\\YWJ\\区域转移概率矩阵实验计算\\分母\\2011030"+j+"event,regionid,quantity.txt");
				BufferedReader reader1 = new BufferedReader(fr1);
				String line="";
				while((line=reader1.readLine())!=null){
					if(line=="")
						continue;
					String[]arrl = line.split("\t");
					if(arrl[0].equals("0")){
						if(map0.containsKey(arrl[1])){
							String value=map0.get(arrl[1]);
							int sum=Integer.parseInt(arrl[2])+Integer.parseInt(value);
							map0.put(arrl[1],sum+"");
						}
						else{
							map0.put(arrl[1], arrl[2]);
						}
					}
					else{
						if(map1.containsKey(arrl[1])){
							String value=map1.get(arrl[1]);
							int sum=Integer.parseInt(arrl[2])+Integer.parseInt(value);
							map1.put(arrl[1], sum+"");
						}
						else{
							map1.put(arrl[1], arrl[2]);
						}
					}
				}
				reader1.close();
			}
			Set set=map0.entrySet();
			java.util.Iterator iterator=map0.entrySet().iterator();
			while(iterator.hasNext()){
				java.util.Map.Entry entry=(java.util.Map.Entry)iterator.next();
				fw.write("0\t"+entry.getKey()+"\t"+entry.getValue()+"\n");
			}
			set=map1.entrySet();
			iterator=map1.entrySet().iterator();
			while(iterator.hasNext()){
				java.util.Map.Entry entry=(java.util.Map.Entry)iterator.next();
				fw.write("1\t"+entry.getKey()+"\t"+entry.getValue()+"\n");
			}
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void dealRegionNFile() {
		HashMap<String, String> map0=new HashMap<String, String>();
		HashMap<String, String> map1=new HashMap<String, String>();
		FileReader fr1;
		FileWriter fw;
		try {
			fw = new FileWriter("E:\\NRDC1202\\PersonalWork\\zjj\\YWJ\\区域转移概率矩阵实验计算\\结果\\201103event,regionid,regionid_next.txt");
			for(int i=0;i<5;i++){
				int j=i+3;
				fr1=new FileReader("E:\\NRDC1202\\PersonalWork\\zjj\\YWJ\\区域转移概率矩阵实验计算\\分子\\2011030"+j+"event,regionid,regionid_next,quantity.txt");
				BufferedReader reader1 = new BufferedReader(fr1);
				String line="";
				while((line=reader1.readLine())!=null){
					if(line=="")
						continue;
					String[]arrl = line.split("\t");
					if(arrl[0].equals("0")){
						String string=arrl[1]+"\t"+arrl[2];
						if(map0.containsKey(string)){
							String value=map0.get(string);
							int sum=Integer.parseInt(arrl[3])+Integer.parseInt(value);
							map0.put(string,sum+"");
						}
						else{
							map0.put(string, arrl[3]);
						}
					}
					else{
						String string=arrl[1]+"\t"+arrl[2];
						if(map1.containsKey(string)){
							String value=map1.get(string);
							int sum=Integer.parseInt(arrl[3])+Integer.parseInt(value);
							map1.put(string, sum+"");
						}
						else{
							map1.put(string, arrl[3]);
						}
					}
				}
				reader1.close();
			}
			Set set=map0.entrySet();
			java.util.Iterator iterator=map0.entrySet().iterator();
			while(iterator.hasNext()){
				java.util.Map.Entry entry=(java.util.Map.Entry)iterator.next();
				fw.write("0\t"+entry.getKey()+"\t"+entry.getValue()+"\n");
			}
			set=map1.entrySet();
			iterator=map1.entrySet().iterator();
			while(iterator.hasNext()){
				java.util.Map.Entry entry=(java.util.Map.Entry)iterator.next();
				fw.write("1\t"+entry.getKey()+"\t"+entry.getValue()+"\n");
			}
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void dealRateFile() {
		HashMap<String, String> map=new HashMap<String, String>();
		FileReader fr1;
		FileReader fr2;
		FileWriter fw;
		try {
			fw = new FileWriter("E:\\NRDC1202\\PersonalWork\\zjj\\YWJ\\区域转移概率矩阵实验计算\\结果\\201103event,regionid,regionid_next,rate.txt");
			fr1=new FileReader("E:\\NRDC1202\\PersonalWork\\zjj\\YWJ\\区域转移概率矩阵实验计算\\结果\\201103event,regionid,sum.txt");
			fr2=new FileReader("E:\\NRDC1202\\PersonalWork\\zjj\\YWJ\\区域转移概率矩阵实验计算\\结果\\201103event,regionid,regionid_next.txt");
				
			BufferedReader reader1 = new BufferedReader(fr1);
			BufferedReader reader2 = new BufferedReader(fr2);
			String line="";
			while((line=reader1.readLine())!=null){
				if(line=="")
					continue;
				String[]arrl = line.split("\t");
				map.put(arrl[1],arrl[2]);	
			}
			reader1.close();
		    line="";
			while((line=reader2.readLine())!=null){
				if(line=="")
					continue;
				String[]arrl = line.split("\t");
				String value=map.get(arrl[1]);
				DecimalFormat dFormat=new DecimalFormat("######0.00");
				if(Integer.parseInt(arrl[3])>Integer.parseInt(value)){
					System.out.print(arrl[1]+"\t"+arrl[2]+"\t");
					System.out.print(arrl[3]+"\t"+value+"\n");
				}
				double rate=Double.parseDouble(arrl[3])/Double.parseDouble(value);
				fw.write(arrl[0]+"\t"+arrl[1]+"\t"+arrl[2]+"\t"+dFormat.format(rate)+"\n");
			}
			reader2.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

