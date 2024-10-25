package com.urbantreasurelimited.ecommerce.generic.fileutility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import com.urbantreasurelimited.ecommerce.constantspath.IPathConstants;

public class FileUtility {

	public String getDataFromPropertiesFile(String key) throws IOException {
		FileInputStream fis = new FileInputStream(IPathConstants.propertyFilePath);
		Properties pObj = new Properties();
		pObj.load(fis);
		String data = pObj.getProperty(key);
		return data;
	}

	public String convertingRelativePathToAbsolutePath(String relativePath)

	{

		File file = new File(relativePath);
		String absolutePath = file.getAbsolutePath();
		return absolutePath;

	}

}
