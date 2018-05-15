package com.honghe.recordweb.util.base.util.picconverter;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifDirectory;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;



/**
 * 测试用于读取图片的EXIF信息 & 利用EXIF中方向属性旋转图�?
 * 
 */
public class PicExif {
	/**
	 * 利用EXIF中的方向信息判断旋转度数
	 * 
	 * @param src
	 *            图片路径
	 * @param end
	 *            转换后图片路�?
	 * @throws Exception
	 */
	public static void prepRotate(String src, String end) throws Exception {
		Metadata metadata = JpegMetadataReader.readMetadata(new File(src));
		Directory exif = metadata.getDirectory(ExifDirectory.class);
		if (!exif.containsTag(ExifDirectory.TAG_ORIENTATION)) {
		//	System.out.println("此图片没有EXIF信息");
		} else {
			int orientation = 0;
			orientation = exif.getInt(ExifDirectory.TAG_ORIENTATION);
			switch (orientation) {
			case 1:
				rotate(src, end, 0);
				break;
			case 2:
			//	System.out.println("Top, right side (Mirror horizontal)");
				break;
			case 3:
				rotate(src, end, 180);
				break;
			case 4:
				//System.out.println("Bottom, left side (Mirror vertical)");
				break;
			case 5:
				//System.out.println("Left side, top (Mirror horizontal and rotate 270 CW)");
				break;
			case 6:
				rotate(src, end, 90);
				break;
			case 7:
			//	System.out.println("Right side, bottom (Mirror horizontal and rotate 90 CW)");
				break;
			case 8:
				rotate(src, end, 270);
				break;
			default:
				//System.out.println(String.valueOf(orientation));
				break;
			}
		}

	}

	/**
	 * 旋转图片给定度数
	 * 
	 * @param src
	 *            目的图片路径
	 * @param end
	 *            转换后图片路�?
	 * @param degree
	 *            给定旋转度数
	 * @throws IOException
	 */
	public static void rotate(String src, String end, int degree)
			throws IOException {
		Color bgcolor = Color.black;
		BufferedImage image = (BufferedImage) ImageIO.read(new File(src));
		int iw = image.getWidth();// 原始图象的宽�?
		int ih = image.getHeight();// 原始图象的高�?
		int w = 0;
		int h = 0;
		int x = 0;
		int y = 0;
		degree = degree % 360;
		if (degree < 0)
			degree = 360 + degree;// 将角度转换到0-360度之�?
		double ang = Math.toRadians(degree);// 将角度转为弧�?

		/**
		 * 确定旋转后的图象的高度和宽度
		 */

		if (degree == 180 || degree == 0 || degree == 360) {
			w = iw;
			h = ih;
		} else if (degree == 90 || degree == 270) {
			w = ih;
			h = iw;
		} else {
			int d = iw + ih;
			w = (int) (d * Math.abs(Math.cos(ang)));
			h = (int) (d * Math.abs(Math.sin(ang)));
		}

		x = (w / 2) - (iw / 2);// 确定原点坐标
		y = (h / 2) - (ih / 2);
		BufferedImage rotatedImage = new BufferedImage(w, h, image.getType());
		Graphics2D gs = (Graphics2D) rotatedImage.getGraphics();
		if (bgcolor == null) {
			rotatedImage = gs.getDeviceConfiguration().createCompatibleImage(w,
					h, Transparency.TRANSLUCENT);
		} else {
			gs.setColor(bgcolor);
			gs.fillRect(0, 0, w, h);// 以给定颜色绘制旋转后图片的背�?
		}

		AffineTransform at = new AffineTransform();
		at.rotate(ang, w / 2, h / 2);// 旋转图象
		at.translate(x, y);
		AffineTransformOp op = new AffineTransformOp(at,
				AffineTransformOp.TYPE_BICUBIC);
		op.filter(image, rotatedImage);
		image = rotatedImage;
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ImageOutputStream iamgeOut = ImageIO.createImageOutputStream(byteOut);

		ImageIO.write(image, "jpg", iamgeOut);
		InputStream inputStream = new ByteArrayInputStream(
				byteOut.toByteArray());

		FileOutputStream fout = new FileOutputStream(end);
		OutputStream out = (OutputStream) fout;

		int c;
		while ((c = inputStream.read()) != -1) {
			out.write(c);
		}
		out.flush();
	}

	/**
	 * 目的图片和源图片合一
	 * 
	 * @param src
	 *            图片路径
	 */
	public static void exif(String src) {
		try {
			prepRotate(src, src);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 遍历给定图片�?��的EXIF信息
	 * 
	 * @param src
	 *            要遍历图片路�?
	 * @throws JpegProcessingException
	 */
	@SuppressWarnings("rawtypes")
	public static void getExifAll(String src) throws JpegProcessingException {
		File jpegFile = new File(src);
		Metadata metadata = JpegMetadataReader.readMetadata(jpegFile);
		Directory exif = metadata.getDirectory(ExifDirectory.class);// 这里要稍微注意下
		Iterator tags = exif.getTagIterator();
		while (tags.hasNext()) {
			Tag tag = (Tag) tags.next();
		//	System.out.println(tag);
		}
	}

	/**
	 * 获得给定图片EXIF信息中的方向信息
	 * 
	 * @param src
	 *            给定图片路径
	 * @throws Exception
	 */
	public static void getExif(String src) throws Exception {
		Metadata metadata = JpegMetadataReader.readMetadata(new File(src));
		Directory exif = metadata.getDirectory(ExifDirectory.class);
		int e = exif.getInt(ExifDirectory.TAG_ORIENTATION);
		//System.out.println(e);
	}

	/**
	 * 获得指定图片的指定信�?
	 * 
	 * @param src
	 *            指定图片路径
	 * @param key
	 *            指定EXIF信息
	 * @return EXIF 信息常量
	 * @throws Exception
	 */
	public static int getExifByKey(String src, int key) throws Exception {
		Metadata metadata = JpegMetadataReader.readMetadata(new File(src));
		Directory exif = metadata.getDirectory(ExifDirectory.class);
		int e = exif.getInt(key);
		return e;
	}

	/**
	 * 测试方法
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		String src = "D:/1.jpg";
		// getExifAll(src);

		PicExif.exif(src);
	}
}
