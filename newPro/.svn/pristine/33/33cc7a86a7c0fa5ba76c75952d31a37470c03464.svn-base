package com.honghe.recordweb.util.base.util.picconverter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader; 
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;

import com.honghe.convert.sdk.ConvertServiceClient;
import com.honghe.recordweb.util.ConfigUtil;
import com.honghe.service.client.Result;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class PicConverter {
	
	public static Logger logger = Logger.getLogger(PicConverter.class);
	public static void converter(File imgFile,String format,File formatFile)
            throws IOException{
        BufferedImage bIMG =ImageIO.read(imgFile);
        ImageIO.write(bIMG, format, formatFile);
    }
	
	 public static void copyFile(File sourceFile, File targetFile) throws IOException {
         BufferedInputStream inBuff = null;
         BufferedOutputStream outBuff = null;
         try {

            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));


            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));


            byte[] b = new byte[1024 * 5];
             int len;
             while ((len = inBuff.read(b)) != -1) {
                 outBuff.write(b, 0, len);
             }

            outBuff.flush();
         } finally {

            if (inBuff != null)
                 inBuff.close();
             if (outBuff != null)
                 outBuff.close();
             
             
         }
     }
	private static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}
	public static boolean  imageConvert(String imagePath){
		try
        {
			File source = new File(imagePath);
			String filename = source.getName().substring(0, source.getName().indexOf("."));
			String path = source.getPath().replaceAll(source.getName(), "");
			new File(path + filename).mkdir();
			String name = getExtensionName(imagePath);
//			ConvertServiceClient convertServiceClient = new ConvertServiceClient("localhost",7001);
			Integer port=Integer.parseInt(ConfigUtil.get("serviceport"));
			String serviceIp = com.honghe.recordweb.util.ConfigUtil.get("serviceip");
			final ConvertServiceClient convertServiceClient = new ConvertServiceClient(serviceIp, port);
			if (name.equalsIgnoreCase("gif")) {
				try {
					IOUtils.copy(new FileInputStream(source), new FileOutputStream(path + filename + File.separator + "image.gif"));
				} catch (Exception e) {
					return false;
				}
				Map<String, String> params = new HashMap<String, String>();
				params.put("source", imagePath);
				params.put("target", path + filename + File.separator + "thumb.png");
				Result result = convertServiceClient.executeT(ConvertServiceClient.ThumbMethod.Image_Thumb, params);
				if (result.getCode() != 0 || result.getValue().toString().equals("false")) {
					return false;
				}
				return true;
			} else {
				Map<String, String> params = new HashMap<String, String>();
				params.put("source", imagePath);
				params.put("target", path + filename);
				params.put("fileName", "image");
				Result result = convertServiceClient.executeC(ConvertServiceClient.ConvertMethod.Image_2_Png, params);
				if (result.getCode() != 0 || result.getValue().toString().equals("false")) {
					return false;
				}
				params.clear();
				params.put("source", imagePath);
				params.put("target", path + filename + File.separator + "thumb.png");
				params.put("width", "120");
				result = convertServiceClient.executeT(ConvertServiceClient.ThumbMethod.Image_Thumb, params);
				if (result.getCode() != 0 || result.getValue().toString().equals("false")) {
					return false;
				}
				return true;
			}
        }catch (Exception e){
        	 e.printStackTrace();
        	 logger.error("pic convert fail！", e);
        	 return false;
        }
	}
	public static PicInfo imageInfo(String imagePath){
		PicInfo imageInfo = new PicInfo();
		try {
			File source  = new File(imagePath);
			BufferedImage src = ImageIO.read(new File(imagePath));
			imageInfo.setHeight(src.getHeight(null));
		    imageInfo.setWidth(src.getWidth(null));
			imageInfo.setFileSize(source.length());
            String filename = source.getName().substring(0,source.getName().indexOf(".") );
			String path =  source.getPath().replaceAll(source.getName(), "");
			String suffix = source.getName().replaceAll(filename+".","" );
			imageInfo.setFormat(suffix);
		} catch (IOException e) {
			logger.error("get pic info fail!", e);
			return null;
		}
		return imageInfo;
	}
	
	public static void resize(File originalFile, File resizedFile, int newWidth, float quality) throws IOException {
		if (quality > 1) {
			throw new IllegalArgumentException("Quality has to be between 0 and 1");
		}
		ImageIcon ii = new ImageIcon(originalFile.getCanonicalPath());
		Image i = ii.getImage();
		Image resizedImage = null;
		int iWidth = i.getWidth(null);
		int iHeight = i.getHeight(null);
		if (iWidth > iHeight) {
			resizedImage = i.getScaledInstance(newWidth, (newWidth * iHeight)
					/ iWidth, Image.SCALE_SMOOTH);
		} else {
			resizedImage = i.getScaledInstance((newWidth * iWidth) / iHeight,
					newWidth, Image.SCALE_SMOOTH);
		}

		// This code ensures that all the pixels in the image are loaded.
		Image temp = new ImageIcon(resizedImage).getImage();

		// Create the buffered image.
		BufferedImage bufferedImage = new BufferedImage(temp.getWidth(null),
				temp.getHeight(null), BufferedImage.TYPE_INT_RGB);

		// Copy image to buffered image.
		Graphics g = bufferedImage.createGraphics();

		// Clear background and paint the image.
		g.setColor(Color.white);
		g.fillRect(0, 0, temp.getWidth(null), temp.getHeight(null));
		g.drawImage(temp, 0, 0, null);
		g.dispose();

		// Soften.
		float softenFactor = 0.05f;
		float[] softenArray = { 0, softenFactor, 0, softenFactor,
				1 - (softenFactor * 4), softenFactor, 0, softenFactor, 0 };
		Kernel kernel = new Kernel(3, 3, softenArray);
		ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
		bufferedImage = cOp.filter(bufferedImage, null);

		// Write the jpeg to a file.
		FileOutputStream out = new FileOutputStream(resizedFile);

		// Encodes image as a JPEG data stream
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		// createJPEGEncoder(OutputStream dest)
		
		JPEGEncodeParam param = encoder
				.getDefaultJPEGEncodeParam(bufferedImage);

		param.setQuality(quality, true);

		encoder.setJPEGEncodeParam(param);
		encoder.encode(bufferedImage);
	}
}
