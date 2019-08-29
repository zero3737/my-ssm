package top.zero3737.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

public class Generator {

	public static void main(String[] args) throws Exception{

		List<String> warnings = new ArrayList<String>();  
	    boolean overwrite = true;  
	    //指向逆向工程配置文件，只需要把下面这个文件改为你自己写的配置文件即可
	    File configFile = new File("src/main/java/top/zero3737/generator/generatorConfig.xml");
	    ConfigurationParser cp = new ConfigurationParser(warnings);
	    Configuration config = cp.parseConfiguration(configFile);  
	    DefaultShellCallback callback = new DefaultShellCallback(overwrite);  
	    MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);  
	    myBatisGenerator.generate(null);
	    System.out.println("执行成功！");

	}
	
}