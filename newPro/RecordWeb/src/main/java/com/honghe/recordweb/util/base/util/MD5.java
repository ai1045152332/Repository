package com.honghe.recordweb.util.base.util;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;


/**
 * MD5工具类
 * @author zhuangwei
 *
 */
public class MD5 
{
	/**
	 * byte[]数组---->32位MD5加密字符串
	 * @param byte[]数组
	 * @return 32位MD5加密字符串
	 */
	public String getMD5(byte[] b)
	{
		MessageDigest md5=null;
		try
		{
			md5=MessageDigest.getInstance("MD5");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		byte[] md5Result=md5.digest(b);
		String value="";
        for(int i=0;i<16;i++)
        {
        	value+=byte2uchar(md5Result[i]);
        	//value+=NumberUtil.byte2uchar(md5Result[i]);
        }
        return value;
	}
	private final static String byte2uchar(byte b)
	{
		char[] hex={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		//char high='0';
		//char low='0';
		int high=(b>>>4)&0x0f;
		char highChar=hex[high];
		int low=b&0x0f;
		char lowChar=hex[low];
		return ""+highChar+lowChar;
	}
	/**
	 * String字符串---->32位MD5加密字符串
	 * @param String字符串
	 * @return 32位MD5加密字符串
	 */
	public String getMD5(String value)
	{		
		return getMD5(value.getBytes());
	}
/**
 * @param absoluteFileName
 * @return
 */
	public int getMD5Length(long length){
		int result = 0;
		if(length<=100*1000){
			return (int)length; 
		}else if(length<=1*1000*1000){
			result = 1000*((int)length/10000);
			if(length%10000==0){
				return result;
			}else{
				return result + 1000;
			}
		}else if(length<=10*1000*1000){
			result = 1000*((int)length/100000);
			if(length%100000==0){
				return result;
			}else{
				return result + 1000;
			}
		}else{
			result = 1000*((int)length/1000000);
			if(length%1000000==0){
				return result;
			}else{
				return result + 1000;
			}

		}
	}
	/**
	 * 文件绝对路径String---->32位MD5加密字符串
	 * @param 文件绝对路径String
	 * @return 32位MD5加密字符串
	 */
	public String getFileMD5(String absoluteFileName)
	{
		
		File f= new File(absoluteFileName);
		if(!f.exists() || (!f.isFile()) || f.length()==0 ){
			return null;
		}
		
		FileInputStream fis=null;
		int bSize = getMD5Length(f.length());
		byte[] fileBytes= new byte[bSize] ;
		
		try
		{			
		    fis=new FileInputStream(f);
		    if(f.length()<=100*1000){
		    	fis.read(fileBytes);
		    }else if(f.length()<=1*1000*1000){
				int visited = 0;
				int read = 0;
				for(int i=0;;i++){
					if((visited+1000)>f.length()){
						fis.read(fileBytes, read, (int)(f.length()-visited));
						break;
					}
					fis.read(fileBytes, read, 1000);
					fis.skip(10000-1000);
					
					visited += 10*1000;
					read += 1000;
					if(visited>=f.length()){
						break;
					}					
				}
			}else if(f.length()<=10*1000*1000){
				int visited = 0;
				int read = 0;
				for(int i=0;;i++){
					if((visited+1000)>f.length()){
						fis.read(fileBytes, read, (int)(f.length()-visited));
						break;
					}
					fis.read(fileBytes, read, 1000);
					fis.skip(100000-1000);
					
					visited += 100*1000;
					read += 1000;
					if(visited>=f.length()){
						break;
					}					
				}
			}else{
				int visited = 0;
				int read = 0;
				for(int i=0;;i++){
					if((visited+1000)>f.length()){
						fis.read(fileBytes, read, (int)(f.length()-visited));
						break;
					}
					fis.read(fileBytes, read, 1000);
					fis.skip(1000000-1000);
					
					visited += 1000*1000;
					read += 1000;
					if(visited>=f.length()){
						break;
					}					
				}
			}		    
			
			fis.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
        String md5Value=getMD5(fileBytes);        

		return md5Value;
	}
	
	/**
	 * 文件File---->32位MD5加密字符串
	 * @param 文件File
	 * @return 32位MD5加密字符串
	 */
	public String getFileMD5(File file) {
		if (!file.isFile()) {
			return null;
		}
		MessageDigest digest = null;
		FileInputStream in = null;
		byte buffer[] = new byte[1024];
		int len;
		try {
			digest = MessageDigest.getInstance("MD5");
			in = new FileInputStream(file);
			while ((len = in.read(buffer, 0, 1024)) != -1) {
				digest.update(buffer, 0, len);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		BigInteger bigInt = new BigInteger(1, digest.digest());
		return bigInt.toString(16);
	}
	
	public static void main(String[] args)
	{
		MD5 md5=new MD5();
		String str="E:\\test\\a.rar";
		//测试File
		System.out.println("File md5:"+md5.getFileMD5(new File(str)));
		System.out.println("String md5:"+md5.getFileMD5(str));
		//测试String
		System.out.println("String md5:"+md5.getMD5(str));
		System.out.println("String md5:"+md5.getMD5(str.getBytes()));
	}
}
