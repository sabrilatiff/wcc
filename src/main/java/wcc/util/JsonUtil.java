package wcc.util;

import com.google.gson.Gson;

public class JsonUtil {
	public static String getString(Object obj) {
		Gson g= new Gson();
		return g.toJson(obj);
	}
}
