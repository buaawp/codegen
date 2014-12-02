package codegen;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

public class CodegenUtil
{
  public static void genFile(String templatePath, String templateFileName, String targetPath, String targetFileName, String charset, Map paramMap)
    throws IOException, TemplateException
  {
   
    File localFile = new File(targetPath, targetFileName);
    if (!localFile.exists()) {
      if (!localFile.getParentFile().exists())
        localFile.getParentFile().mkdirs();
      localFile.createNewFile();
    }
    OutputStreamWriter localOutputStreamWriter = new OutputStreamWriter(new FileOutputStream(localFile), charset);

    Configuration freemarkerConfigration = new Configuration();
    freemarkerConfigration.setDirectoryForTemplateLoading(new File(templatePath));

    Template localTemplate = freemarkerConfigration.getTemplate(templateFileName, charset);
    localTemplate.process(paramMap, localOutputStreamWriter);
    localOutputStreamWriter.close();
    
  }
}