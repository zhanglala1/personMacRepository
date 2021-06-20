package apiTest.apitest;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.github.crab2died.converter.ReadConvertible;

public class ParamtersConvert implements ReadConvertible {

	@Override
	public Object execRead(String object) {

		if (object != null) {

			if (StringUtils.endsWith(object, "txt")) {

				String pathString = System.getProperty("user.dir") + File.separator + "data" + File.separator + object;

				File file = new File(pathString);
				try {
					return FileUtils.readFileToString(file, "utf-8");

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return object;
	}

}
