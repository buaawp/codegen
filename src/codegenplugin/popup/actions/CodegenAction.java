package codegenplugin.popup.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.internal.resources.File;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import xml.XmlUtil;
import codegen.CodegenUtil;
import console.ConsoleFactory;

public class CodegenAction implements IObjectActionDelegate {

	private Shell shell;
	private ISelection selection;
	
	/**
	 * Constructor for Action1.
	 */
	public CodegenAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {		
		
		//读取选定的配置文件	
		IStructuredSelection selection =(IStructuredSelection) this.selection;		

		ConsoleFactory.printToConsole("--------Start Coding--------", true);
		
		for(Object element:selection.toList()){
			File file = (File)element;	
			String fullpath = file.getLocationURI().getPath();
			
			Map<String, String> params;
			List<Map<String,String>>  templateMapList=new ArrayList<Map<String,String>>(); 
			
			try {

				String configfilepath=fullpath.substring(1);
				ConsoleFactory.printToConsole("...load coding config "+configfilepath, true);
				
				params = XmlUtil.getVars(configfilepath);
				templateMapList=XmlUtil.getTemplates(configfilepath);
				
				for(Map<String ,String > templateMap:templateMapList){
					String templateFilePath=templateMap.get(XmlUtil.TEMPLATE_PATH);
					String templateFileName=templateMap.get(XmlUtil.TEMPLATE_NAME);
					String targetFilePath=templateMap.get(XmlUtil.TARGET_PATH);
					String targetFileName=templateMap.get(XmlUtil.TARGET_NAME);
					
					ConsoleFactory.printToConsole("... ... coding ... "+targetFilePath+"\\"+targetFileName, true);

					params.put(XmlUtil.TEMPLATE_PATH, templateFilePath);
					params.put(XmlUtil.TEMPLATE_NAME, templateFileName);
					params.put(XmlUtil.TARGET_PATH, targetFilePath);
					params.put(XmlUtil.TARGET_NAME, targetFileName);					
					
					String charset=params.get(XmlUtil.CHARSET);	
					
					CodegenUtil.genFile(templateFilePath,templateFileName,targetFilePath,targetFileName,charset,params);		

				}			
				
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}

		ConsoleFactory.printToConsole("--------Finish Coding--------", true);
		
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection=selection;
	}

}
