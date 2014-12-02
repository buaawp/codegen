package xml;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import codegen.CodegenException;

public class XmlUtil{
	public static final String CHARSET="charset";
	public static final String TEMPLATES="templates";
	public static final String TEMPLATE="template";
	public static final String TEMPLATE_PATH="templatepath";
	public static final String TEMPLATE_NAME="templatename";
	public static final String TARGET_PATH="targetpath";
	public static final String TARGET_NAME="targetname";
	
	
	public static Map getVars(String filePath) throws CodegenException{
		
		Map varMap=new HashMap();
		
		SAXReader localSAXReader = new SAXReader();
		Document localDocument = null;
		try {
			localDocument = localSAXReader.read(new File(filePath));
		} catch (DocumentException localDocumentException) {
			throw new CodegenException("读取XML出错!", localDocumentException);
		}
		
		//读取xml文件的根节点数据
		Element rootElement = localDocument.getRootElement();
		
		//读取Variables节点，获得项目级的配置数据
		Element variablesElement = rootElement.element("variables");
		Iterator variablesIterator = variablesElement.elementIterator("variable");
		while (variablesIterator.hasNext()) {
			Element variableElement = (Element) variablesIterator.next();
			String name = ((Element) variableElement).attributeValue("name");
			String value = ((Element) variableElement).attributeValue("value");
			varMap.put(name, value);
		}	
		
		return varMap;
		
	}		

	public static List<Map<String,String>> getTemplates(String filePath) throws CodegenException{
		
		List<Map<String,String>>  templateMapList=new ArrayList<Map<String,String>>(); 
		
		SAXReader localSAXReader = new SAXReader();
		Document localDocument = null;
		try {
			localDocument = localSAXReader.read(new File(filePath));
		} catch (DocumentException localDocumentException) {
			throw new CodegenException("读取XML出错!", localDocumentException);
		}
		
		//读取xml文件的根节点数据
		Element rootElement = localDocument.getRootElement();
		Element templatesElement = rootElement.element(TEMPLATES);
		
		List<Element> templateList = templatesElement.elements(TEMPLATE);
		for(Element templateElement : templateList){
			Map varMap=new HashMap();
			Iterator variablesIterator = templateElement.elementIterator("variable");
			while (variablesIterator.hasNext()) {
				Element variableElement = (Element) variablesIterator.next();
				String name = ((Element) variableElement).attributeValue("name");
				String value = ((Element) variableElement).attributeValue("value");
				varMap.put(name, value);
			}
			templateMapList.add(varMap);
		}		
		
		return templateMapList;
		
	}		
	
		
}