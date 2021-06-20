package httpclient;

import com.github.crab2died.converter.ReadConvertible;

public class IsRunConvert implements ReadConvertible{

	@Override
	public Object execRead(String object) {
		
		if ("是".contains(object)) {
			return true;
		}
		return false;
		
	}

}
