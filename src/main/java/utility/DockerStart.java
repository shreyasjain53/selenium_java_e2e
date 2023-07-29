package utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import base.BaseClass;

public class DockerStart   {
	
	private Properties prop = BaseClass.prop;
	private static final Logger log = LogManager.getLogger(DockerStart.class);

	
	public void startContainer() throws IOException, InterruptedException {

		boolean flag = false;
		
		Runtime runtime = Runtime.getRuntime();
		runtime.exec("cmd /c start "+prop.getProperty("DockerUpFileName"));
		
		String file = prop.getProperty("DockerLogFileName");

		Calendar calendar = Calendar.getInstance();  
		calendar.add(Calendar.SECOND, 40);            
		long stopNow = calendar.getTimeInMillis();   
		
		Thread.sleep(3000);
		
		while (System.currentTimeMillis() < stopNow) { 

			if (flag) {
				break;
			} 

			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

			String currentline = bufferedReader.readLine();

			while (currentline != null && !flag) {
				if (currentline.contains(prop.getProperty("DockerServerStartTextVerify"))) {
					flag = true;
					break;
				}
				currentline = bufferedReader.readLine();
			}
			bufferedReader.close();
		}
		
		Assert.assertTrue(flag);
		Thread.sleep(3000);
	}
}