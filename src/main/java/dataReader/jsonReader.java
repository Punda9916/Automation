package dataReader;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class jsonReader {

	
	 //my first commit in git
	public void getJsontoHasmap() throws IOException {
		
		//converting json to string
		
		String jsondata =FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\resources\\ProductData.json"), StandardCharsets.UTF_8);
		
		//converting string to HasMap
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data =mapper.readValue(jsondata, new TypeReference<List<HashMap<String ,String>>>(){});
		
	}
	
	
	
	
	
	//converting string to HasMap
	
}
