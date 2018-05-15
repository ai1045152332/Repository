package com.honghe.recordweb.util.base.util;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


/**
 * 文件工具类
 *
 * @author zhuangwei
 *
 */
public class FileUtil {

	private static Logger logger= Logger.getLogger(FileUtil.class);
	/**
	 * 私有构造方法，防止类的实例化，因为工具类不需要实例化。
	 */
	private FileUtil() {
	}

	/**
	 * 实现文件复制功能，将src文件内容复制到des文件中
	 *
	 * @param souFile
	 *            File类型 源
	 * @param desFile
	 *            File类型 目的
	 * @return
	 */
	public static boolean copyFile(File souFile, File desFile) {
		FileInputStream fin=null;
		FileOutputStream fout=null;
		boolean flag=true;
		try {
			if (!souFile.exists()) {
				return false;
			}
			logger.debug("是否存在目标目录"+desFile.exists());
			fin = new FileInputStream(souFile);
			fout = new FileOutputStream(desFile);

			byte[] buf = new byte[1024];
			int c;
			while ((c = fin.read(buf)) != -1)// 返回实际读取到的大小
			{
				fout.write(buf, 0, c);
			}

		} catch (IOException e) {
			e.printStackTrace();
			flag = false;
		}
		finally{
			try {
				fout.flush();
				fout.close();
				fin.close();
			} catch (Exception e2) {
				logger.error("删除文件失败",e2);
				flag = false;
			}
		}
		return flag;
	}

	/**
	 * add by dingshangjun , copy file by path;
	 * @param src
	 * @param dst
	 */
	public static void copyFileByPath(String src,String dst){
		File srcFile = new File(src);

		File dstFile = new File(dst);

		copyFile(srcFile,dstFile);
	}

	/**
	 * 实现文件夹复制功能，将oldPath文件夹内全部内容复制到newPath文件夹中
	 *
	 * @param oldPath
	 *            String类型 源
	 * @param newPath
	 *            String类型 目的
	 * @return
	 */
	public static void copyFolder(String oldPath, String newPath) {

		try {
			(new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
			File a = new File(oldPath);
			String[] file = a.list();
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + file[i]);
				} else {
					temp = new File(oldPath + File.separator + file[i]);
				}

				if (temp.isFile()) {
					FileInputStream input = new FileInputStream(temp);
					FileOutputStream output = new FileOutputStream(newPath
							+ "/" + (temp.getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				}
				if (temp.isDirectory()) {// 如果是子文件夹
					copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
				}
			}
		} catch (Exception e) {
			logger.error("复制整个文件夹内容操作出错",e);
			e.printStackTrace();

		}

	}

	/**
	 * 实现文件夹复制功能，将oldPath文件夹内全部内容复制到newPath文件夹中,可以自定义忽略列表
	 *
	 * @param oldPath
	 *            String类型 源
	 * @param newPath
	 *            String类型 目的
	 * @param filters
	 *            忽略的集合
	 */
	public static void copyFolder(String oldPath, String newPath,
			Collection<String> filters) {
		try {
			(new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
			File a = new File(oldPath);
			String[] file = a.list();
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				String fileOrPathName = new File(file[i]).getName();
				if (filters.contains(fileOrPathName))
					continue;
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + file[i]);
				} else {
					temp = new File(oldPath + File.separator + file[i]);
				}

				if (temp.isFile()) {
					FileInputStream input = new FileInputStream(temp);
					FileOutputStream output = new FileOutputStream(newPath
							+ "/" + (temp.getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				}
				if (temp.isDirectory()) {// 如果是子文件夹
					copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
				}
			}
		} catch (Exception e) {
			logger.error("复制整个文件夹内容操作出错",e);
		}

	}

	/**
	 * 实现文件删除功能，将src文件删除掉
	 *
	 * @param src
	 *            File类型
	 */
	public static void delFile(File src) {
		if (src.isFile()) {
			src.delete();
		}
		if (src.isDirectory()) {
			File[] f = src.listFiles();
			for (int i = 0; i < f.length; i++) {
				if (f[i].isFile()) {
					f[i].delete();
				}
				if (f[i].isDirectory()) {
					delFile(f[i]); // 递归
					f[i].delete(); // 将空文件夹删除，若不加这句，文件夹是删不掉的
				}
			}
		}
	}

	/**
	 * 删除文件夹和里面所有内容
	 *
	 * @param folderPath
	 *            字符串 文件夹完整绝对路径
	 * @return 删除成功返回true 否则false
	 */
	public static boolean delFolder(String folderPath) {
		try {
			delAllFile(folderPath);

			// 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 删除文件夹下所有文件
	 *
	 * @param path
	 *            字符串 文件夹完整绝对路径
	 * @return 删除成功返回true 否则false
	 */
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		if (tempList == null) {
			logger.debug("no file deleted...");
			return true;
		}
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				if(!temp.delete()){
					logger.error("文件："+temp.getAbsolutePath()+"删除失败");
				}
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 利用时间生成文件名
	 *
	 * @param fileName
	 *            字符串 文件夹完整绝对路径
	 * @return 生成的字符串文件名
	 */
	public static String renameFile(String fileName) {
		String newName;
		Date date = new Date();
		int index = fileName.indexOf(".");
		newName = "" + date.getTime() + fileName.substring(index);
		return newName;
	}

	/**
	 * 对单个文件进行重命名 磁盘格式相同情况下可以当剪切用
	 *
	 * @param src
	 *            字符串 文件夹完整绝对路径
	 * @param newName
	 *            字符串 文件夹完整绝对路径
	 */
	public static void rename(File src, String newName) {
		File newFile = new File(newName);
		if (src.exists()) {
			src.renameTo(newFile);
			logger.debug("重命名成功！");
		} else {
			logger.error("该文件不存在！");
		}
	}

	static ArrayList<String> filename = new ArrayList<String>();

	/**
	 * 列出src中的所有文件，返回一个List
	 *
	 * @param src
	 *            File类型
	 * @return ArrayList<String> 名称列表
	 */
	public static ArrayList<String> listFile(File src) {
		File[] f = src.listFiles();
		for (int i = 0; i < f.length; i++) {
			String name = f[i].getName();
			filename.add(name);
			if (f[i].isDirectory()) {
				listFile(f[i]);

			}
		}
		return filename;
	}

	// uploadFile(FormFile file, String filePath,String fileURL)
	/*
	 * public static boolean uploadFile(FormFile file, String filePath, String
	 * fileURL) { // String fileName = file.getFileName(); // String size =
	 * (file.getFileSize() + " bytes");
	 *
	 * try { File path = new File(filePath); if (!path.exists()) {
	 * path.mkdirs(); } filePath += "/" + fileURL; // retrieve the file data
	 * InputStream stream = file.getInputStream();
	 *
	 * // write the file to the file specified OutputStream bos = new
	 * FileOutputStream(filePath); int bytesRead = 0; byte[] buffer = new
	 * byte[8192]; while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
	 * bos.write(buffer, 0, bytesRead); } bos.flush(); bos.close();
	 *
	 * stream.close(); } catch (FileNotFoundException fnfe) {
	 * fnfe.printStackTrace(); return false; } catch (IOException ioe) {
	 * ioe.printStackTrace(); return false; }
	 *
	 * file.destroy(); return true; }
	 */
	// 将一个序列化的类写到指定的文件中
	/*
	 * public static void writeObjectToFile(User u,File f){ User user = null;
	 * try { FileOutputStream fos = new FileOutputStream(f); ObjectOutputStream
	 * obs = new ObjectOutputStream(fos); obs.writeObject(u); FileInputStream
	 * fis = new FileInputStream(f); ObjectInputStream ois = new
	 * ObjectInputStream(fis); user = (User)ois.readObject();
	 * System.out.println(user.name); System.out.println(user.password);
	 * System.out.println(user.age); } catch (FileNotFoundException e) {
	 * e.printStackTrace(); }catch (IOException e) { e.printStackTrace(); }
	 * catch (ClassNotFoundException e) { e.printStackTrace(); }
	 *
	 * }
	 */

	// 从文件中读出这个类
	/*
	 * public static Object readObjectFromFile(File f){ User u = null; try {
	 * FileInputStream fis = new FileInputStream(f); ObjectInputStream ois = new
	 * ObjectInputStream(fis); u = (User)ois.readObject();
	 * System.out.println(u.name); System.out.println(u.password);
	 * System.out.println(u.age); } catch (FileNotFoundException e) {
	 * e.printStackTrace(); }catch (IOException e) { e.printStackTrace(); }
	 * catch (ClassNotFoundException e) { e.printStackTrace(); } return u; }
	 */

	/**
	 * 将文件组合在一个文件中
	 *
	 * @param f1
	 *            File类型
	 * @param f2
	 *            File类型
	 * @param f3
	 *            File类型
	 */
	public static void compposite(File f1, File f2, File f3) {
		try {
			FileInputStream fis1 = new FileInputStream(f1);
			FileInputStream fis2 = new FileInputStream(f2);
			SequenceInputStream sis = new SequenceInputStream(fis1, fis2);
			FileOutputStream fos = new FileOutputStream(f3);
			int rs = 0;
			while ((rs = sis.read()) != -1) {
				fos.write(rs);
			}
			logger.debug("文件合并成功！");
			fis1.close();
			fis2.close();
			sis.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 显示指定网页的源代码
	 *
	 * @param strUrl
	 *            网页的URL
	 * @return 字符串格式源代码
	 */
	public static String getCode(String strUrl) {
		try {
			URL url = new URL(strUrl);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					url.openStream()));
			String s = "";
			StringBuffer sb = new StringBuffer("");
			while ((s = br.readLine()) != null) {
				sb.append(s + "/r/n");
			}
			br.close();
			return sb.toString();
		} catch (Exception e) {
			return "无法显示网页" + strUrl;
		}
		// return "无法显示网页"+strUrl;
	}

	/**
	 * 分隔符替换 window下测试通过
	 *
	 * @param path
	 * @return
	 */
	public static String separatorReplace(String path) {
		return path.replace("\\", "/");
	}

	/**
	 * 通过路径获得文件， 若不存在则抛异常， 若存在则返回该文件。
	 *
	 * @param path
	 * @return
	 * @throws java.io.FileNotFoundException
	 */
	public static File getFile(String path) throws FileNotFoundException {
		path = separatorReplace(path);
		File file = new File(path);
		if (!file.isFile()) {
			throw new FileNotFoundException("file not found!");
		}
		return file;
	}

	public static String getFileContent(String path,String code){
		try {
			File file = FileUtil.getFile(path);
			RandomAccessFile readFis = new RandomAccessFile(file, "rw");
			byte[] buffer = new byte[1024*1024];
			readFis.read(buffer);
			readFis.close();
			String modelHtml = new String(buffer,code).trim();
			return modelHtml;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void writeFileContent(String path,String content,String code){
		try {
			File previewFile =  new File(path);
			RandomAccessFile writeFis = new RandomAccessFile(previewFile, "rw");
			writeFis.write(content.getBytes(code));
			writeFis.close();
		} catch (Exception e) {
			logger.error("path : "+path,e);
		}
	}

	public static void writeFileArray(String path,byte[] content){
		try {
			File previewFile =  new File(path);
			RandomAccessFile writeFis = new RandomAccessFile(previewFile, "rw");
			writeFis.write(content);
			writeFis.close();
		} catch (Exception e) {
			logger.error("path : "+path,e);
		}
	}

	/**
	 * 根据文件路径删除文件，如果路径指向的文件不存在或删除失败则抛出异常。
	 *
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static void deleteFile(String path) throws Exception {
		path = separatorReplace(path);
		File file = getFile(path);
		if (!file.delete()) {
			throw new Exception("delete file failure");
		}
	}

	/**
	 * 创建文件夹，如果文件夹存在则不进行创建。
	 *
	 * @param path
	 * @throws Exception
	 */
	public static void createFolder(String path) throws Exception {
		path = separatorReplace(path);
		File folder = new File(path);
		if (folder.isDirectory()) {
			return;
		} else if (folder.isFile()) {
			deleteFile(path);
		}
		folder.mkdirs();
	}

	/**
	 * 通过路径获得文件夹， 若不存在则抛异常， 若存在则返回该文件夹。
	 *
	 * @param path
	 * @return
	 * @throws java.io.FileNotFoundException
	 */
	public static File getFolder(String path) throws FileNotFoundException {
		path = separatorReplace(path);
		File folder = new File(path);
		if (!folder.isDirectory()) {
			throw new FileNotFoundException("folder not found!");
		}
		return folder;
	}

	/**
	 * 根据路径删除文件夹，如果路径指向的目录不存在则抛出异常， 若存在则先遍历删除子项目后再删除文件夹本身。
	 *
	 * @param path
	 * @throws Exception
	 */
	public static void deleteFolder(String path) throws Exception {
		path = separatorReplace(path);
		File folder = getFolder(path);
		File[] files = folder.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				deleteFolder(file.getAbsolutePath());
			} else if (file.isFile()) {
				deleteFile(file.getAbsolutePath());
			}
		}
		folder.delete();
	}

	/**
	 * 创建一个新的文件夹，如果文件夹存在，则删除后再创建。
	 *
	 * @param path
	 * @throws Exception
	 */
	public static void createNewFolder(String path) throws Exception {
		path = separatorReplace(path);
		File folder = new File(path);
		if (folder.isDirectory()) {
			deleteFolder(path);
		} else if (folder.isFile()) {
			deleteFile(path);
		}
		folder.mkdirs();
	}

	/**
	 * 创建父目录
	 *
	 * @param file
	 * @throws Exception
	 */
	private static void createParentFolder(File file) throws Exception {
		if (!file.getParentFile().exists()) {
			if (!file.getParentFile().mkdirs()) {
				throw new Exception("create parent directory failure!");
			}
		}
	}

	/**
	 * 创建文件及其父目录。
	 *
	 * @param file
	 * @throws Exception
	 */
	public static File createFile(File file) throws Exception {
		createParentFolder(file);
		if (!file.createNewFile()) {
			throw new Exception("create file failure!");
		}
		return file;
	}

	/**
	 * 创建一个文件，如果文件存在则不进行创建。
	 *
	 * @param path
	 * @throws Exception
	 */
	public static File createFile(String path) throws Exception {
		path = separatorReplace(path);
		File file = new File(path);
		if (file.isFile()) {
			return file;
		} else if (file.isDirectory()) {
			deleteFolder(path);
		}
		return createFile(file);
	}

	/**
	 * 创建一个新文件，如果存在同名的文件或文件夹将会删除该文件或文件夹， 如果父目录不存在则创建父目录。
	 *
	 * @param path
	 * @throws Exception
	 */
	public static File createNewFile(String path) throws Exception {
		path = separatorReplace(path);
		File file = new File(path);
		if (file.isFile()) {
			deleteFile(path);
		} else if (file.isDirectory()) {
			deleteFolder(path);
		}
		return createFile(file);
	}

	public static void getChildrenDir(File resource,List<File> subDirList){
		if(resource.isDirectory()){
			File[] fileList=resource.listFiles();
			for(File file:fileList){
				if(file.isDirectory()){
					subDirList.add(file);
					getChildrenDir(file, subDirList);
				}
			}
		}
	}

	public static void main(String[] args) {
//		File f1 = new File("e:/test/a/b.txt");
//		rename(f1, "e:/test/c/b.txt");
		File file=new File("F:\\WW电脑他人资料\\HiteVision软件\\流媒体-龙腾");
		List<File> fileList=new ArrayList<File>(0);
		getChildrenDir(file, fileList);
		for(File file1:fileList){
			System.out.println(file1.getAbsolutePath());
		}

	}

}