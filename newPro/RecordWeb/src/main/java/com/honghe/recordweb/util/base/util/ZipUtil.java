package com.honghe.recordweb.util.base.util;




import com.honghe.recordweb.service.frontend.news.SearchPathTools;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.*;

/**
 * 压缩和解压缩工具类
 * @author zhuangwei
 *
 */
public class ZipUtil {
	/**
	 * 私有构造方法，防止类的实例化，因为工具类不需要实例化。
	 */
	private ZipUtil() {
	}

	/**
	 * 比特流之间压缩
	 * 
	 * @param src
	 *            输入数据字节
	 * @param dest
	 *            压缩数据的缓冲区
	 * @return int类型 压缩数据的实际字节数
	 */
	public static int zip(byte src[], byte dest[]) {
		Deflater compresser = new Deflater();
		// 为压缩设置输入数据
		compresser.setInput(src);
		compresser.finish();
		// 使用压缩数据填充指定缓冲区
		return compresser.deflate(dest);
	}

	/**
	 * 比特流之间解压缩
	 * 
	 * @param src
	 *            输入数据字节
	 * @param dest
	 *            未压缩数据的缓冲区
	 * @param start
	 *            输入数据的初始偏移量
	 * @param length
	 *            输入数据的长度
	 * @return 实际未压缩字节数
	 * @throws java.util.zip.DataFormatException
	 *             如果压缩数据格式无效
	 */
	public static int unZip(byte src[], byte dest[], int start, int length)
			throws DataFormatException {
		Inflater decompresser = new Inflater();
		decompresser.setInput(src, start, length);
		int result = decompresser.inflate(dest);
		decompresser.end();
		return result;
	}

	/**
	 * 将指定文件夹压缩到目标文件 zip压缩
	 * 
	 * @param zipFileName
	 *            字符串 压缩后文件的路径全名ZIP
	 * @param dirName
	 *            字符串 要压缩的文件夹的路径全名
	 * @return 如果为true 压缩成功 否则失败
	 * */
	public static boolean zip(String zipFileName, String dirName) {
		try {
			File inputFile = new File(dirName);
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
					zipFileName));
			zip(out, inputFile, "");
			// System.out.println("zip done");
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param zipFileName 打包后文件名字  例："c:\\test.zip";
	 * @param inputFileName 输入一个文件夹
	 * @throws Exception
	 */
	 public static void zipDe(String zipFileName,String inputFileName) throws Exception {
	        zipDe(zipFileName, new File(inputFileName));
	    }

	    private static void zipDe(String zipFileName, File inputFile) throws Exception {
	        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
	        zipDe(out, inputFile, "");
	        out.close();
	    }

	    private static void zipDe(ZipOutputStream out, File f, String base) throws Exception {
	        if (f.isDirectory()) {
	           File[] fl = f.listFiles();
	           out.putNextEntry(new org.apache.tools.zip.ZipEntry(base + "/"));
	           base = base.length() == 0 ? "" : base + "/";
	           for (int i = 0; i < fl.length; i++) {
	           zip(out, fl[i], base + fl[i].getName());
	         }
	        }else {
	           out.putNextEntry(new org.apache.tools.zip.ZipEntry(base));
	           FileInputStream in = new FileInputStream(f);
	           int b;
	           System.out.println(base);
	           while ( (b = in.read()) != -1) {
	            out.write(b);
	         }
	         in.close();
	       }
	    }
	
	
	static final int BUFFER = 2048;

	/**
	 * 将指定文件压缩到指定目录中
	 * 
	 * @param zipFileName
	 *            字符串 待解压文件的路径全名 ZIP
	 * @param destDir
	 *            字符串 解压后的目标地址
	 * @return 如果为true 解压缩成功 否则失败
	 * */
	public static boolean unZip(String zipFileName, String destDir) {
		try {
			String fileName = zipFileName;
			String filePath = destDir;
			ZipFile zipFile = new ZipFile(fileName);
			Enumeration emu = zipFile.entries();
			int i = 0;
			while (emu.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) emu.nextElement();
				// 会把目录作为一个file读出一次，所以只建立目录就可以，之下的文件还会被迭代到。
				if (entry.isDirectory()) {
					new File(filePath + entry.getName()).mkdirs();
					continue;
				}
				BufferedInputStream bis = new BufferedInputStream(
						zipFile.getInputStream(entry));
				File file = new File(filePath + entry.getName());
				// 加入这个的原因是zipfile读取文件是随机读取的，这就造成可能先读取一个文件
				// 而这个文件所在的目录还没有出现过，所以要建出目录来。
				File parent = file.getParentFile();
				if (parent != null && (!parent.exists())) {
					parent.mkdirs();
				}
				FileOutputStream fos = new FileOutputStream(file);
				BufferedOutputStream bos = new BufferedOutputStream(fos, BUFFER);

				int count;
				byte data[] = new byte[BUFFER];
				while ((count = bis.read(data, 0, BUFFER)) != -1) {
					bos.write(data, 0, count);
				}
				bos.flush();
				bos.close();
				bis.close();
			}
			zipFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	private static void zip(ZipOutputStream out, File f, String base)
			throws Exception {
		if (f.isDirectory()) {
			File[] fl = f.listFiles();
			out.putNextEntry(new org.apache.tools.zip.ZipEntry(base + "/"));
			base = base.length() == 0 ? "" : base + "/";
			for (int i = 0; i < fl.length; i++) {
				zip(out, fl[i], base + fl[i].getName());
			}
		} else {
			out.putNextEntry(new org.apache.tools.zip.ZipEntry(base));
			FileInputStream in = new FileInputStream(f);
			int b;
			// System.out.println(base);
			while ((b = in.read()) != -1) {
				out.write(b);
			}
			in.close();
		}
	}
	/**
	 * zip/jar copy
	 * @author jun chen
	 * @param srcFileName
	 * @param targetFileName
	 */
	public static void zipCopy(String srcFileName,String targetFileName)
	 { 
	 
	     InputStream inStream = null; 

	 try 
	 { 
	     inStream = new FileInputStream(srcFileName); 
	     
	 } catch (FileNotFoundException e) 
	 { 
		 System.err.println( "读取文件[ " + srcFileName + "]发生错误 " + "\r\n " 
		 + e.getCause()); 
	 
	 return; 
	 
	 } 

		 File targetFile = new File(targetFileName); 
		 OutputStream outStream = null; 
	 try 
	  { 
		 targetFile.createNewFile(); 

		 outStream = new FileOutputStream(targetFile); 

		 byte[] by = new byte[1024]; 

		 while (inStream.available() > 0) 
		 { 
		 inStream.read(by); 
		 outStream.write(by); 
		 } 
	 } catch (IOException e) 
	
	 { 
	 System.err.println( "创建文件[ " + targetFileName + "]发生错误 " + "\r\n " 
	 + e.getCause()); 
	 
	 } finally { 
		 
	 if (null != inStream) 
	 { 
		 
	 try { 
	
		 inStream.close(); 
		 
	 } catch (IOException e) 
	 
	 { 
	 System.err.println(e.getCause()); 
	 
	 } 
	 
	 } 
	     if (null != outStream) 
	    	 
	 { 
	    	 
	 try  { 
		 
	 outStream.flush(); 
	 
	 } catch (IOException e) 
	 
	 { 
		 
	 System.err.println(e.getCause()); 
	 
	 } 
	 try { 
		 
	 outStream.close(); 
	 
	 } catch (IOException e) 
	 { 
		 
	 System.err.println(e.getCause()); 
	 
	  } 
   } 
} 
	
} 
	/**
	 * @author dux
	 * 压缩除media以及下载列表之外的所有文件
	 * 
	 * */
	public  static boolean zipFolder(String folderPath,String target,List<String> exceptFile,String zipName,String type)throws Exception{
		
		String linshiPathString= SearchPathTools.tempPath+UUIDUtil.getuuidString()+"/";
		//源文件夹
		File srcFile=new File(folderPath);
		//临时存放文件夹
		File tempFile=new File(linshiPathString);
		tempFile.mkdirs();

		//目标文件架
		File targetFile=new File(target);
		targetFile.mkdirs();
		if(!srcFile.exists()){
			System.out.println("目录为空");
			return false;
		}
		//复制源文件夹的所有子文件
		File[] childFiles=srcFile.listFiles();
		for(File cf:childFiles){

			if(cf.isFile()){
				System.out.println("file name is :"+cf.getName());
				System.out.println("exceptFile.contains(f.getName())"+exceptFile.contains(cf.getName()));
				if(!exceptFile.contains(cf.getName())){
					System.out.println("copy");
					FileUtil.copyFile(cf, new File(linshiPathString+cf.getName()));
				}
			}else{
				System.out.println("file name is :"+cf.getName());
				System.out.println("exceptFile.contains(f.getName())"+exceptFile.contains(cf.getName()));
				if(!exceptFile.contains(cf.getName())){
					System.out.println("copy");
					FileUtil.copyFolder(folderPath+cf.getName(), linshiPathString+cf.getName());
				}
				
			}
		}
		/*if(download!=null&&download!=""){
			File resourFile=new File(download);
			if(resourFile.exists()){
				File linshimulu2=new File(linshiPathString2);
				File[] childs=resourFile.listFiles();
				String[] strs=download.split("/");
				String name =strs[strs.length-2];
				for(File rf:childs){
					
					
				}
			}
		}*/
		//压缩到目标目录
		if("".equals(type)||type==null){
			zip(target+zipName+".zip",linshiPathString);
		}else{
			zip(target+zipName+"."+type,linshiPathString);
		}
		
		FileUtil.delFolder(linshiPathString);
		System.out.println("end");
		return true;
	}
	
public static void main(String[] args) throws Exception {
	String resourcePathString="C:/Users/dux/Desktop/nginx11111111111111/install/tomcat/webapps/ManagementCenter/data/projects/edit/admin/2/";
	String newPathString="d:/压缩后的文件/";
	List<String> list=new ArrayList<String>();
	list.add("media");
	list.add("downloadList.xml");
	zipFolder(resourcePathString,newPathString,list,"我是压缩文件","");
}
}
