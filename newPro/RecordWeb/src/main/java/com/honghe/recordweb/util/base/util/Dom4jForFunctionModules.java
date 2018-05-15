package com.honghe.recordweb.util.base.util;


import com.honghe.recordweb.util.base.entity.FunctionModule;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.ResourceBundle;
import java.util.Set;

@SuppressWarnings("all")

public class Dom4jForFunctionModules {
	public static Set<FunctionModule> read(String filePath,ResourceBundle rb) throws DocumentException {
		
		Set<FunctionModule> dom4jModules = new LinkedHashSet<FunctionModule>();
		// 创建解析器
		SAXReader reader = new SAXReader();
		// 解析文档
		Document document = reader
				.read(filePath);

		Element root = document.getRootElement();

		for (Iterator moduleIterator = root.elementIterator(); moduleIterator
				.hasNext();) {
			FunctionModule fm = new FunctionModule();
			Element module = (Element) moduleIterator.next();
			Element moduleID = module.element("ModuleID");
			Element moduleName = module.element("ModuleName");
			Element moduleUrlTag = module.element("ModuleUrlTag");

			fm.setId(Integer.parseInt(moduleID.getText().toString()));
			String moduleName2 = getModuleName(moduleName.getText().toString(), rb);
			fm.setName(moduleName2);
			fm.setUrlPath(moduleUrlTag.getTextTrim());
			fm.setType(null);

			Set<FunctionModule> dom4jModule = new LinkedHashSet<FunctionModule>();

			Element subModules = module.element("SubModules");
			if(subModules==null){
				dom4jModules.add(fm);
			}
			else {
				for (Iterator subModulesIterator = subModules
						.elementIterator("SubModule"); subModulesIterator.hasNext();) {
					
					Element subModule = (Element) subModulesIterator.next();
					Attribute attribute = subModule.attribute("id");
					Attribute attributeType = subModule.attribute("type");
					FunctionModule module2 = new FunctionModule();
					
					module2.setId(Integer.parseInt(attribute.getValue()));
					if(attributeType!=null){
						module2.setType(Integer.parseInt(attributeType.getValue()));
					}else{
						module2.setType(null);
					}
					String moduleName3 = getModuleName(subModule.getText().toString(), rb);
					module2.setName(moduleName3);
					module2.setSubModules(null);
					
					dom4jModule.add(module2);
					fm.setSubModules(dom4jModule);
					dom4jModules.add(fm);

				}
			}
			
		}
		return dom4jModules;
	}
	public static String getModuleName(String moduleName,ResourceBundle rb){
		if(moduleName!=null&&!("".equals(moduleName))){
			if(moduleName.startsWith("${")&&moduleName.endsWith("}")){
				return rb.getString(moduleName.substring(2, moduleName.length()-1));				
			}
		}
		return "";
	}
	public static void main(String[] args) throws Exception {
		//Set<FunctionModule> read = read("src/main/resources/FunctionModules.xml");
	}
}