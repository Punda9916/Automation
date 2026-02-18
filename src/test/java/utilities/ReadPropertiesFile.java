package utilities;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertiesFile {
	
    public static void main(String args[]) throws IOException
    {
    	
    //FileReader class to read file 
	FileReader fr =new FileReader("C:\\Users\\NAGAVENI P\\eclipse-workspace\\Automation\\src\\test\\resources\\configfiles\\config.properties");
	
	//Properties reader class object has created
	
	Properties p =  new Properties();
	
	//loading file reader
	
	p.load(fr);
	
	System.out.println(p.getProperty("browser"));
	System.out.println(p.getProperty("testurl"));
		
}
}