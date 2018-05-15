package com.honghe.recordweb.action.frontend.tresource;


import com.honghe.recordhibernate.entity.tools.GlobalParameter;
import com.honghe.recordweb.action.BaseAction;
import com.honghe.recordweb.service.frontend.tresource.TresourceService;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.io.File;

@Controller
@Scope(value = "Prototype")
public class ResourceUploadAction extends BaseAction {
	private final static Logger logger = Logger.getLogger(ResourceUploadAction.class);
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -4681786593165174297L;
	private String ResourceVirtualPath = GlobalParameter.RESOURCE_VITUALPATH;
	private String ResourcePath = ServletActionContext.getServletContext().getRealPath("/msgResource") + "/data/resources/1/";
	private File upload;
	private String fileCaption;
	@SuppressWarnings("unused")
	private ServletContext context;
	private String uploadFileName;
	File target = null;
	@Resource
	private TresourceService tresourceService;

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	public String getFileCaption() {
		return fileCaption;
	}

	public void setFileCaption(String fileCaption) {
		this.fileCaption = fileCaption;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	/**
	 * todo 加注释
	 * @return
	 */
	public String doUpload() {
		if (uploadFileName == null || "".equals(uploadFileName.trim())) {
			return "refresh";
		} else {
			return "refresh";
		}
	}

	/*public void md5Validate() throws Exception {
		MD5 md5 = new MD5();
		int lastDotIndex = uploadFileName.lastIndexOf(".");
		String fileName = uploadFileName.substring(0, lastDotIndex);
		String fileFormat = uploadFileName.substring(lastDotIndex+1);
		String[] splitname = {fileName,fileFormat};
		@SuppressWarnings("static-access")
		String nameForMd5 = md5.getMD5(uploadFileName);
		@SuppressWarnings("unused")
		List<TResourceCatalog> resourceCatalog = tresourceService.findByProperty("md5", nameForMd5);
		uploadFileName = nameForMd5 + "." + splitname[1];
		String message = "我是MD5验证返回的信息";
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(message);
			String targetDirectory = ResourcePath;
			String targetFileName = uploadFileName;
			target = new File(targetDirectory, targetFileName);
			FileUtils.copyFile(upload, target);
			setUploadFileName(target.getPath());
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error("",e);
			System.out.println("异常信息" + e.getMessage().toString());
		}
		String videoType = "avi,wmv,mov,rm,rmvb,mpg,mpeg,mp4,vob,flv,f4v,mkv,3gp,m4v,ts";
		String imgType = "jpg,jpeg,png,bmp,gif";
		String wordType = "docx,doc";
		String excelType = "xlsx,xls";
		String pdfType = "pdf";
		String pptType = "pptx,ppt,pps";
		String flashType = "swf";
		int type = 0;
		String suffix = splitname[1].toLowerCase();
		int vValue = videoType.indexOf(suffix);
		if (vValue != -1) {

			type = 1;
		}
		int wValue = wordType.indexOf(suffix);
		if (wValue != -1) {
			type = 2;
		}
		int eValue = excelType.indexOf(suffix);
		if (eValue != -1) {
			type = 3;
		}
		int imgValue = imgType.indexOf(suffix);
		if (imgValue != -1) {
			type = 4;
		}
		int pValue = pdfType.indexOf(suffix);
		if (pValue != -1) {
			type = 5;
		}
		int pptValue = pptType.indexOf(suffix);
		if (pptValue != -1) {
			type = 6;
		}
		int swfValue = flashType.indexOf(suffix);
		if (swfValue != -1) {
			type = 7;
		}
		switch (type) {
		case 1:
			video(splitname, nameForMd5);
			break;
		case 2:
			word(splitname,nameForMd5);
			
			break;
		case 3:
			excel(splitname,nameForMd5);
			break;
		case 4:
			pic(splitname, nameForMd5);

			break;
		case 5:
			pdf(splitname,nameForMd5);
			break;
		case 6:
			ppt(splitname, nameForMd5);
			break;
		case 7:
			flash(splitname, nameForMd5);
			break;
		default:
			System.out.println("我其实什么都不是");
			break;
		}
	}

	public void ppt(String[] splitname, String nameForMd5) throws Exception {
		User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
		String userId = user.getUserId().toString();
		TResource resource = new TResource();
		TResourceIndex resourceIndex = new TResourceIndex();
		TResourceCatalog resourceCata = new TResourceCatalog();
		TResourceStatus resourceStatus = new TResourceStatus();
		//中间是将ppt相关的信息给读取出来并赋值给上面的四个对象。可以参考其他的处理
		resourceCata.setName(splitname[0]);
		resourceCata.setMd5(nameForMd5);
		resourceCata.setFormat(splitname[1]);
		resourceCata.setVirtual(ResourceVirtualPath);
		resourceCata.setFid(1);
		resourceCata.setType(6);
		resourceCata.setStatus(1);
		resourceCata.setDeep("");
		resourceCata.setFolder(true);
		resourceCata.setSubitems("子目录集");
		resourceCata.setIslock(false);
		resourceCata.setLayer("层级");
		resourceCata.setDeep("层的深度");
		resourceCata.setEdit(true);
		resourceIndex.settName(splitname[0]);
		resourceIndex.settMd5(nameForMd5);
		resourceIndex.settPath(target.getPath());
		resourceIndex.settStatus(1);
		resourceIndex.settType(6);
		resourceIndex.settConvert("转换的结果");
		resourceIndex.settDesc("本资源的描述信息");
		resourceIndex.settResult("资源的转换结果");
		resourceIndex.settVersion("资源的版本");
		resourceIndex.settSituation("这里是资源排队等待的状况");
		resource.setName(splitname[0]);
		resource.setSize(12292);
		resource.setFormat(splitname[1]);
		resource.setCreator(userId);
		resource.setDuration(0);
		resource.setUpdator("资源更新人，默认的时候是null");
		resource.setPath(target.getPath());
		resource.setLength(1);
		resource.setWidth(1);
		resourceStatus.setName(splitname[0]);
		resourceStatus.setUploadtime(null);
		resourceStatus.setUploader(userId);
		resourceStatus.setUpdatetime(null);
		resourceStatus.setUpdater(userId);
		resourceStatus.setChecker("资源的审核人");
		resourceStatus.setChecktime(null);
		resourceStatus.setFormatstatus("格式转换状态");
		resourceStatus.setUploadstatus(ResourceStatusFlag.Uploaded.getFlag());
		//上传完成
        tresourceService.saveResourceStatus(resourceStatus);
		try {
			resourceStatus.setUploadstatus(ResourceStatusFlag.Converting.getFlag());
			tresourceService.updateByEntiy(resourceStatus);
			String oo = target.getAbsolutePath();
			System.out.println(target.getAbsolutePath());
			new PPT2HTML().convert(target.getAbsolutePath());
		} catch (Exception e) {
			resourceStatus.setUploadstatus(ResourceStatusFlag.ConvertError.getFlag());
			tresourceService.updateByEntiy(resourceStatus);
			e.printStackTrace();
		}
		resourceStatus.setUploadstatus(ResourceStatusFlag.Converted.getFlag());
		this.tresourceService.saveResourceInfo(resource, resourceStatus, resourceIndex, resourceCata);
	}

	public void pic(String[] splitname, String nameForMd5) {
		try {
			PicInfo ImageInfo = PicConverter.imageInfo(target.getPath());
			TResource resource = new TResource();
			TResourceIndex resourceIndex = new TResourceIndex();
			TResourceCatalog resourceCata = new TResourceCatalog();
			TResourceStatus resourceStatus = new TResourceStatus();
			resourceCata.setName(splitname[0]);
			resourceCata.setMd5(nameForMd5);
			resourceCata.setFormat(splitname[1]);
			resourceCata.setVirtual(ResourceVirtualPath);
			resourceCata.setFid(1);
			resourceCata.setType(4);
			resourceCata.setStatus(1);
			resourceCata.setDeep("");
			resourceCata.setFolder(true);
			resourceCata.setSubitems("子目录集");
			resourceCata.setIslock(false);
			resourceCata.setLayer("层级");
			resourceCata.setDeep("层的深度");
			resourceCata.setEdit(true);
			resourceIndex.settName(splitname[0]);
			resourceIndex.settMd5(nameForMd5);
			resourceIndex.settPath(target.getPath());
			resourceIndex.settStatus(1);
			resourceIndex.settType(1);
			resourceIndex.settConvert("转换的结果");
			resourceIndex.settDesc("本资源的描述信息");
			resourceIndex.settResult("资源的转换结果");
			resourceIndex.settVersion("资源的版本");
			resourceIndex.settSituation("这里是资源排队等待的状况");
			resource.setName(splitname[0]);
			resource.setSize(3);
			resource.setFormat(splitname[1]);
			resource.setCreator(super.getUserNameBySession());
			resource.setCreator("test");
			resource.setDuration(0);
			resource.setUpdator("资源更新人，默认的时候是null");
			resource.setPath(target.getPath());
			resource.setLength(ImageInfo.getHeight());
			resource.setWidth(ImageInfo.getWidth());
			resourceStatus.setName(splitname[0]);
			resourceStatus.setUploadtime(null);
			resourceStatus.setUploader("test");
			resourceStatus.setUpdatetime(null);
			resourceStatus.setUpdater("test");
			resourceStatus.setChecker("资源的审核人");
			resourceStatus.setChecktime(null);
			resourceStatus.setFormatstatus("格式转换状态");
            resourceStatus.setUploadstatus(ResourceStatusFlag.Uploaded.getFlag());
			//上传完成
           tresourceService.saveResourceStatus(resourceStatus);
			try {
				resourceStatus.setUploadstatus(ResourceStatusFlag.Converting.getFlag());
				tresourceService.updateByEntiy(resourceStatus);
				PicConverter.imageConvert(target.getPath());
			} catch (Exception e) {
                resourceStatus.setUploadstatus(ResourceStatusFlag.ConvertError.getFlag());
				tresourceService.updateByEntiy(resourceStatus);
			}
			resourceStatus.setUploadstatus(ResourceStatusFlag.Converted.getFlag());
			this.tresourceService.saveResourceInfo(resource, resourceStatus,resourceIndex, resourceCata);
		} catch (Exception e) {
			logger.error("",e);
			//e.printStackTrace();
		}
	}

	public void word(String[] splitname, String nameForMd5) {
		try {
			User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
			String userId = user.getUserId().toString();
			TResource resource = new TResource();
			TResourceIndex resourceIndex = new TResourceIndex();
			TResourceCatalog resourceCata = new TResourceCatalog();
			TResourceStatus resourceStatus = new TResourceStatus();
			resourceCata.setName(splitname[0]);
			resourceCata.setMd5(nameForMd5);
			resourceCata.setFormat(splitname[1]);
			resourceCata.setVirtual(ResourceVirtualPath);
			resourceCata.setFid(1);
			resourceCata.setType(2);
			resourceCata.setStatus(1);
			resourceCata.setDeep("");
			resourceCata.setFolder(true);
			resourceCata.setSubitems("子目录集");
			resourceCata.setIslock(false);
			resourceCata.setLayer("层级");
			resourceCata.setDeep("层的深度");
			resourceCata.setEdit(true);
			resourceIndex.settName(splitname[0]);
			resourceIndex.settMd5(nameForMd5);
			resourceIndex.settPath(target.getPath());
			resourceIndex.settStatus(1);
			resourceIndex.settType(2);
			resourceIndex.settConvert("转换的结果");
			resourceIndex.settDesc("本资源的描述信息");
			resourceIndex.settResult("资源的转换结果");
			resourceIndex.settVersion("资源的版本");
			resourceIndex.settSituation("这里是资源排队等待的状况");
			resource.setName(splitname[0]);
			resource.setSize(12292);
			resource.setFormat(splitname[1]);
			resource.setCreator(userId);
			resource.setUpdator("资源更新人，默认的时候是null");
			resource.setLength(1);
			resource.setWidth(1);
			resource.setPath(target.getPath());
			resourceStatus.setName(splitname[0]);
			resourceStatus.setUploadtime(null);
			resourceStatus.setUploader(userId);
			resourceStatus.setUpdatetime(null);
			resourceStatus.setUpdater(userId);
			resourceStatus.setChecker("资源的审核人");
			resourceStatus.setChecktime(null);
			resourceStatus.setFormatstatus("格式转换状态");
             resourceStatus.setUploadstatus(ResourceStatusFlag.Uploaded.getFlag());
			
			//上传完成
             tresourceService.saveResourceStatus(resourceStatus);
		    try {
			resourceStatus.setUploadstatus(ResourceStatusFlag.Converting.getFlag());
			tresourceService.updateByEntiy(resourceStatus);
			new WordOrExcelToPng().word2Png(target.getAbsolutePath());
			
		    } catch (Exception e) {
				resourceStatus.setUploadstatus(ResourceStatusFlag.ConvertError.getFlag());
				tresourceService.updateByEntiy(resourceStatus);
			}
		       resourceStatus.setUploadstatus(ResourceStatusFlag.Converted.getFlag());
	        	this.tresourceService.saveResourceInfo(resource, resourceStatus,
				resourceIndex, resourceCata);
		
	      	} catch (Exception e) {
			logger.error("",e);
			//e.printStackTrace();
		}
  	}

	public void excel(String[] splitname, String nameForMd5) {
		try {
			User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
			String userId = user.getUserId().toString();
			TResource resource = new TResource();
			TResourceIndex resourceIndex = new TResourceIndex();
			TResourceCatalog resourceCata = new TResourceCatalog();
			TResourceStatus resourceStatus = new TResourceStatus();
			resourceCata.setName(splitname[0]);
			resourceCata.setMd5(nameForMd5);
			resourceCata.setFormat(splitname[1]);
			resourceCata.setVirtual(ResourceVirtualPath);
			resourceCata.setFid(1);
			resourceCata.setType(3);
			resourceCata.setStatus(1);
			resourceCata.setDeep("");
			resourceCata.setFolder(true);
			resourceCata.setSubitems("子目录集");
			resourceCata.setIslock(false);
			resourceCata.setLayer("层级");
			resourceCata.setDeep("层的深度");
			resourceCata.setEdit(true);
			resourceIndex.settName(splitname[0]);
			resourceIndex.settMd5(nameForMd5);
			resourceIndex.settPath(target.getPath());
			resourceIndex.settStatus(1);
			resourceIndex.settType(3);
			resourceIndex.settConvert("转换的结果");
			resourceIndex.settDesc("本资源的描述信息");
			resourceIndex.settResult("资源的转换结果");
			resourceIndex.settVersion("资源的版本");
			resourceIndex.settSituation("这里是资源排队等待的状况");
			resource.setName(splitname[0]);
			resource.setSize(12292);
			resource.setFormat(splitname[1]);
			resource.setCreator(userId);
			resource.setUpdator("资源更新人，默认的时候是null");
			resource.setLength(1);
			resource.setWidth(1);
			resource.setPath(target.getPath());
			resourceStatus.setName(splitname[0]);
			resourceStatus.setUploadtime(null);
			resourceStatus.setUploader(userId);
			resourceStatus.setUpdatetime(null);
			resourceStatus.setUpdater(userId);
			resourceStatus.setChecker("资源的审核人");
			resourceStatus.setChecktime(null);
			resourceStatus.setFormatstatus("格式转换状态");
            resourceStatus.setUploadstatus(ResourceStatusFlag.Uploaded.getFlag());
			
			//上传完成
            tresourceService.saveResourceStatus(resourceStatus);
			 try {
				   resourceStatus.setUploadstatus(ResourceStatusFlag.Converting.getFlag());
					tresourceService.updateByEntiy(resourceStatus);
					new WordOrExcelToPng().excel2Png(target.getAbsolutePath());
				
			 } catch (Exception e) {
				
                  resourceStatus.setUploadstatus(ResourceStatusFlag.ConvertError.getFlag());
						tresourceService.updateByEntiy(resourceStatus);
			}
			 resourceStatus.setUploadstatus(ResourceStatusFlag.Converted.getFlag());

				this.tresourceService.saveResourceInfo(resource, resourceStatus,
						resourceIndex, resourceCata);
		 	} catch (Exception e) {
			logger.error("",e);
			//e.printStackTrace();
		}
	}
	
	public void pdf(String[] splitname, String nameForMd5) {
		try {
			User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
			String userId = user.getUserId().toString();
			TResource resource = new TResource();
			TResourceIndex resourceIndex = new TResourceIndex();
			TResourceCatalog resourceCata = new TResourceCatalog();
			TResourceStatus resourceStatus = new TResourceStatus();
			resourceCata.setName(splitname[0]);
			resourceCata.setMd5(nameForMd5);
			resourceCata.setFormat(splitname[1]);
			resourceCata.setVirtual(ResourceVirtualPath);
			resourceCata.setFid(1);
			resourceCata.setType(5);
			resourceCata.setStatus(1);
			resourceCata.setDeep("");
			resourceCata.setFolder(true);
			resourceCata.setSubitems("子目录集");
			resourceCata.setIslock(false);
			resourceCata.setLayer("层级");
			resourceCata.setDeep("层的深度");
			resourceCata.setEdit(true);
			resourceIndex.settName(splitname[0]);
			resourceIndex.settMd5(nameForMd5);
			resourceIndex.settPath(target.getPath());
			resourceIndex.settStatus(1);
			resourceIndex.settType(5);
			resourceIndex.settConvert("转换的结果");
			resourceIndex.settDesc("本资源的描述信息");
			resourceIndex.settResult("资源的转换结果");
			resourceIndex.settVersion("资源的版本");
			resourceIndex.settSituation("这里是资源排队等待的状况");
			resource.setName(splitname[0]);
			resource.setSize(12292);
			resource.setFormat(splitname[1]);
			resource.setCreator(userId);
			resource.setUpdator("资源更新人，默认的时候是null");
			resource.setLength(1);
			resource.setWidth(1);
			resource.setPath(target.getPath());
			resourceStatus.setName(splitname[0]);
			resourceStatus.setUploadtime(null);
			resourceStatus.setUploader(userId);
			resourceStatus.setUpdatetime(null);
			resourceStatus.setUpdater(userId);
			resourceStatus.setChecker("资源的审核人");
			resourceStatus.setChecktime(null);
			resourceStatus.setFormatstatus("格式转换状态");
            resourceStatus.setUploadstatus(ResourceStatusFlag.Uploaded.getFlag());
			//上传完成
             tresourceService.saveResourceStatus(resourceStatus);
			try {
				resourceStatus.setUploadstatus(ResourceStatusFlag.Converting.getFlag());
				tresourceService.updateByEntiy(resourceStatus);
				new WordOrExcelToPng().pdf2Png(target.getAbsolutePath());
				
			} catch (Exception e) {
				
               resourceStatus.setUploadstatus(ResourceStatusFlag.ConvertError.getFlag());
				tresourceService.updateByEntiy(resourceStatus);
			}
               resourceStatus.setUploadstatus(ResourceStatusFlag.Converted.getFlag());
			    this.tresourceService.saveResourceInfo(resource, resourceStatus,
					resourceIndex, resourceCata);

		} catch (Exception e) {
			logger.error("",e);
			//e.printStackTrace();
		}
	}
	
	public void flash(String[] splitname, String nameForMd5) {
		try {
			User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
			String userId = user.getUserId().toString();
			TResource resource = new TResource();
			TResourceIndex resourceIndex = new TResourceIndex();
			TResourceCatalog resourceCata = new TResourceCatalog();
			TResourceStatus resourceStatus = new TResourceStatus();
			resourceCata.setName(splitname[0]);
			resourceCata.setMd5(nameForMd5);
			resourceCata.setFormat(splitname[1]);
			resourceCata.setVirtual(ResourceVirtualPath);
			resourceCata.setFid(1);
			resourceCata.setType(7);
			resourceCata.setStatus(1);
			resourceCata.setDeep("");
			resourceCata.setFolder(true);
			resourceCata.setSubitems("子目录集");
			resourceCata.setIslock(false);
			resourceCata.setLayer("层级");
			resourceCata.setDeep("层的深度");
			resourceCata.setEdit(true);
			resourceIndex.settName(splitname[0]);
			resourceIndex.settMd5(nameForMd5);
			resourceIndex.settPath(target.getPath());
			resourceIndex.settStatus(1);
			resourceIndex.settType(7);
			resourceIndex.settConvert("转换的结果");
			resourceIndex.settDesc("本资源的描述信息");
			resourceIndex.settResult("资源的转换结果");
			resourceIndex.settVersion("资源的版本");
			resourceIndex.settSituation("这里是资源排队等待的状况");
			resource.setName(splitname[0]);
			resource.setSize(12292);
			resource.setFormat(splitname[1]);
			resource.setCreator(userId);
			resource.setUpdator("资源更新人，默认的时候是null");
			resource.setLength(1);
			resource.setWidth(1);
			resource.setPath(target.getPath());
			resourceStatus.setName(splitname[0]);
			resourceStatus.setUploadtime(null);
			resourceStatus.setUploader(userId);
			resourceStatus.setUpdatetime(null);
			resourceStatus.setUpdater(userId);
			resourceStatus.setChecker("资源的审核人");
			resourceStatus.setChecktime(null);
			resourceStatus.setFormatstatus("格式转换状态");
            resourceStatus.setUploadstatus(ResourceStatusFlag.Uploaded.getFlag());
			//上传完成
            tresourceService.saveResourceStatus(resourceStatus);
			try {
				resourceStatus.setUploadstatus(ResourceStatusFlag.Converting.getFlag());
				tresourceService.updateByEntiy(resourceStatus);
				File source = new File(target.getAbsolutePath());
		        String filename = source.getName().substring(0,source.getName().indexOf(".") );
				String path =  source.getPath().replaceAll(source.getName(), "");
				new File(path+filename).mkdir();
				FileUtil.copyFile(source, new File(path + filename + "\\flash.swf"));
				FileUtil.copyFile(new File(path+"./../../../images/common/thumb.png"),new File(path+filename+"\\thumb.png"));
			} catch (Exception e) {
                resourceStatus.setUploadstatus(ResourceStatusFlag.ConvertError.getFlag());
				tresourceService.updateByEntiy(resourceStatus);
			}
             	resourceStatus.setUploadstatus(ResourceStatusFlag.Converted.getFlag());
			  	this.tresourceService.saveResourceInfo(resource, resourceStatus,resourceIndex, resourceCata);
		} catch (Exception e) {
			logger.error("",e);
			//e.printStackTrace();
		}
	}
	public void video(String[] splitname, String nameForMd5) {
		try {
			User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
			String userId = user.getUserId().toString();
			VideoEncoder videoEncoder = new VideoEncoder();
			MultimediaInfo mediaInfo = videoEncoder.getEncoder().getInfo(
					new File(target.getPath()));
			
			TResource resource = new TResource();
			TResourceIndex resourceIndex = new TResourceIndex();
			TResourceCatalog resourceCata = new TResourceCatalog();
			TResourceStatus resourceStatus = new TResourceStatus();
			resourceCata.setName(splitname[0]);
			resourceCata.setMd5(nameForMd5);
			resourceCata.setFormat(splitname[1]);
			resourceCata.setVirtual(ResourceVirtualPath);
			resourceCata.setFid(1);
			resourceCata.setType(1);
			resourceCata.setStatus(1);
			resourceCata.setDeep("");
			resourceCata.setFolder(true);
			resourceCata.setSubitems("子目录集");
			resourceCata.setIslock(false);
			resourceCata.setLayer("层级");
			resourceCata.setDeep("层的深度");
			resourceCata.setEdit(true);
			resourceIndex.settName(splitname[0]);
			resourceIndex.settMd5(nameForMd5);
			resourceIndex.settPath(target.getPath());
			resourceIndex.settStatus(1);
			resourceIndex.settType(1);
			resourceIndex.settConvert("转换的结果");
			resourceIndex.settDesc("本资源的描述信息");
			resourceIndex.settResult("资源的转换结果");
			resourceIndex.settVersion("资源的版本");
			resourceIndex.settSituation("这里是资源排队等待的状况");
			resource.setName(splitname[0]);
			resource.setSize(12292);
			resource.setFormat(splitname[1]);
			resource.setCreator(userId);
			resource.setDuration((int) mediaInfo.getDuration());
			resource.setUpdator("资源更新人，默认的时候是null");
			resource.setPath(target.getPath());
			resource.setLength(1);
			resource.setWidth(2);
			resourceStatus.setName(splitname[0]);
			resourceStatus.setUploadtime(null);
			resourceStatus.setUploader(userId);
			resourceStatus.setUpdatetime(null);
			resourceStatus.setUpdater(userId);
			resourceStatus.setChecker("资源的审核人");
			resourceStatus.setChecktime(null);
			resourceStatus.setFormatstatus("格式转换状态");
            resourceStatus.setUploadstatus(ResourceStatusFlag.Uploaded.getFlag());
			//上传完成
             tresourceService.saveResourceStatus(resourceStatus);
			try {
				resourceStatus.setUploadstatus(ResourceStatusFlag.Converting.getFlag());
				tresourceService.updateByEntiy(resourceStatus);
				videoEncoder.encode(target.getPath());
			} catch (Exception e) {
				resourceStatus.setUploadstatus(ResourceStatusFlag.ConvertError.getFlag());
				tresourceService.updateByEntiy(resourceStatus);
			}
			resourceStatus.setUploadstatus(ResourceStatusFlag.Converted.getFlag());
			this.tresourceService.saveResourceInfo(resource, resourceStatus,resourceIndex, resourceCata);
		} catch (Exception e) {
			logger.error("",e);
			//e.printStackTrace();
		}
	}*/

}