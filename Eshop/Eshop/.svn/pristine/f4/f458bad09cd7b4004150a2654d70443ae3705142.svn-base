package com.shop.util;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;


public class FileUtil {

	public static boolean uploadMyFile(File file, String filePath, String fileName) {
		boolean flag=true;
		File path = new File(filePath);
		if (!path.exists()) {
			path.mkdirs();
		}
		try {
			InputStream stream = new FileInputStream(file);
			OutputStream bos = new FileOutputStream(filePath + fileName);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}
			bos.close();
			stream.close();
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
			flag=false;
			return flag;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			flag=false;
			return flag;
		}
		return flag;
	}
	
	public static String getFileExt(String fileName) {
        if (fileName == null) {
            return "";
        }
        int i = fileName.lastIndexOf(".");
        if (i == -1) {
            return "";
        }
        return fileName.substring(i + 1, fileName.length());
    }
	
	/**
	 * 获取产品图片的名称
	 * @param type
	 * @return
	 */
	public static String getPicName(String ext) {
		return System.nanoTime()+"."+ext;
	}
	
	public static boolean checkImageFileExt(String fileExt) {
        if (!fileExt.equalsIgnoreCase("gif")
                && !fileExt.equalsIgnoreCase("jpg")
                && !fileExt.equalsIgnoreCase("png")
                && !fileExt.equalsIgnoreCase("wbmp")
                && !fileExt.equalsIgnoreCase("dgif")) {
            return false;
        }
        return true;
    }
	
	/**
	 * 压缩图片，并保存图片
	 * @param src            操作的图片（类型为BufferedImage）
	 * @param descFilePath   存储图片的路径（类型为String）
	 * @param format         操作图片的格式（类型为String）
	 * @param compressQuality压缩图片的程度（类型为flost)
	 * @return               true :压缩成功
	 * @throws IOException
	 */
	public static boolean compressPicture(BufferedImage src, String descFilePath, String format, float compressQuality) throws IOException {
        FileOutputStream out = null;
        ImageWriter imgWriter;
        ImageWriteParam imgWriteParams;

        // 指定写图片的方式为 jpg
        imgWriter = (ImageWriter) ImageIO.getImageWritersByFormatName(format).next();
        imgWriteParams = new javax.imageio.plugins.jpeg.JPEGImageWriteParam(
                null);
        // 要使用压缩，必须指定压缩方式为MODE_EXPLICIT
        imgWriteParams.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        // 这里指定压缩的程度，参数qality是取值0~1范围内，
        imgWriteParams.setCompressionQuality(compressQuality);
        imgWriteParams.setProgressiveMode(ImageWriteParam.MODE_DISABLED);
        ColorModel colorModel = src.getColorModel();// ColorModel.getRGBdefault();
        // 指定压缩时使用的色彩模式
        imgWriteParams.setDestinationType(new javax.imageio.ImageTypeSpecifier(
                colorModel, colorModel.createCompatibleSampleModel(16, 16)));

        try {
        	// 创建文件输出流
        	out = new FileOutputStream(descFilePath);
        	// 重新设置imgWriter    
        	imgWriter.reset();
                
        	// 必须先指定 out值，才能调用write方法, ImageOutputStream可以通过任何
        	// OutputStream构造
        	imgWriter.setOutput(ImageIO.createImageOutputStream(out));
                
        	// 调用write方法，就可以向输入流写图片
        	imgWriter.write(null, new IIOImage(src, null, null),
        			imgWriteParams);
             
        	// 清除并关闭out
        	out.flush();
        	out.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
	}
	/**
	 * 获取年月日文件路径
	 * @return
	 */
	public static String getYMDPath() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.DATE)+"/";
	}
}
