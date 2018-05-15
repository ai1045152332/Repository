package com.honghe.recordweb.util.base.util.picconverter;

import java.io.*;import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import com.sun.image.codec.jpeg.*;
import net.coobird.thumbnailator.Thumbnails;

public class GenerateThumb {

	private String srcFile;
    private String destFile;
    private int width;
    private int height;
    private BufferedImage img;  
    
    /**
     * ���캯��
    * @param fileName String
     * @throws IOException
     */
    public GenerateThumb(String fileName) throws IOException {
      File _file = new File(fileName); 
     this.srcFile = _file.getName();
		String filename = _file.getName().substring(0,_file.getName().indexOf(".") );
   		
       	String path =  _file.getPath().replaceAll(_file.getName(), "");
      this.destFile = path+filename+"/"+"thumb.png";//this.srcFile.substring(0, this.srcFile.lastIndexOf(".")) +"_s.jpg";
     img = ImageIO.read(_file); 
     width = img.getWidth(null); 
     height = img.getHeight(null); 
   }   /**
     
    * @param w int �¿��
    * @param h int �¸߶�
    * @throws IOException
     */
    public void resize(int w, int h) throws IOException {
      BufferedImage _image = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB);
      _image.getGraphics().drawImage(img, 0, 0, w, h, null); //������С���ͼ
     FileOutputStream newimageout = new FileOutputStream(destFile); //������ļ���
     
      JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimageout);
      encoder.encode(_image); //��JPEG����
     newimageout.close();
    }   /**
   
    * @param t double ����
    * @throws IOException
     */
    public void resize(double t) throws IOException {
      int w = (int) (width * t);
      int h = (int) (height * t);
      resize(w, h);
    }   /**
  
    * @param w int �¿��
    * @throws IOException
     */
    public void resizeByWidth(int w) throws IOException {
      int h = (int) (height * w / width);
      resize(w, h);
    }   /**
  
    * @param h int �¸߶�
    * @throws IOException
     */
    public void resizeByHeight(int h) throws IOException {
      int w = (int) (width * h / height);
      resize(w, h);
    }   /**

    * @param w int �����
    * @param h int ���߶�
    * @throws IOException
     */
    public void resizeFix(int w, int h) throws IOException {
      if (width / height > w / h) {
        resizeByWidth(w);
      }
      else {
        resizeByHeight(h);
      }
    }   
    /*
     * resize (the best function)
     */
    public static void resize(String fileName,
			int newWidth) throws IOException {
    	///////
    	String bmpName=fileName;
    	File f=null;
    	if(fileName.contains(".bmp")){
    		bmpName=fileName.replace(".bmp", "/image.png");
    		f=new File(fileName);
    	}
    	File bmpImage=null;
    	if(fileName.contains(".bmp")){
    		 bmpImage=new File(bmpName);
    	}else{
    		 bmpImage=new File(fileName);
    	}
    	 bmpImage=new File(bmpName);
    	 File originalFile = new File(bmpName); 
         String srcFile = originalFile.getName();
         String filename="";
         if(fileName.contains(".bmp")){
        	  filename = f.getName().substring(0,f.getName().indexOf(".") );
         }else{
        	  filename = originalFile.getName().substring(0,originalFile.getName().indexOf(".") );
         }
    	 
    	 String  path="";
       		if(fileName.contains(".bmp")){
       			path =  f.getPath().replaceAll(f.getName(), "");
       		}else{
       		   path =  originalFile.getPath().replaceAll(originalFile.getName(), "");
       		}
         
          String destFile = path+filename+"\\thumb.png";
    	File resizedFile = new File(destFile);
    	
    	float quality = 0.7f;
    	/////

		if (quality > 1) {
			throw new IllegalArgumentException(
					"Quality has to be between 0 and 1");
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
    public static void getGifThumb(String gifPath){
	 
	 File originalFile = new File(gifPath); 
     String srcFile = originalFile.getName();
	 String filename = originalFile.getName().substring(0,originalFile.getName().indexOf(".") );
   		
       String path =  originalFile.getPath().replaceAll(originalFile.getName(), "");
      String destFile = path+filename+"\\thumb.png";
	 try {
		Thumbnails.of(gifPath)
		 .size(100, 200)
		 .toFile(destFile);
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	 
 }
    public static void main(String[] args) throws Exception {
    	//GenerateThumb ccc = new GenerateThumb("D:\\pic\\world.jpeg");
      // ccc.resizeFix(500, 300);
    	getGifThumb("E:\\thumbnail\\ThumbnailatorTest\\images\\33.gif");
    	//resize("D:/video/金泫雅.png",99 );
    }
 }


