package com.zhuangjb.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtil {
	private static Gson gson = new Gson();
	private static Gson disableHtmlEscapingGson = new GsonBuilder().disableHtmlEscaping().create();

	public static final String EMPTY_JSON_STRING = "[]";

	public static Map<String, String> jsonToStringMap(String json) {
		if (SysUtil.isEmpty(json)) {
			return new HashMap<String, String>();
		}
		Type type = new TypeToken<Map<String, String>>() {
		}.getType();
		Map<String, String> result = gson.fromJson(json, type);
		return result;
	}

	@SuppressWarnings("rawtypes")
	public static String mapToJson(Map map) {
		if (map == null || map.isEmpty()) {
			return EMPTY_JSON_STRING;
		}
		return gson.toJson(map);
	}

	@SuppressWarnings("rawtypes")
	public static String mapToJsonDisableHtmlEscaping(Map map) {
		if (map == null || map.isEmpty()) {
			return EMPTY_JSON_STRING;
		}
		return disableHtmlEscapingGson.toJson(map);
	}

	public static String objectToJsonDisableHtmlEscaping(Object object) {
		if (object == null) {
			return EMPTY_JSON_STRING;
		}
		return disableHtmlEscapingGson.toJson(object);
	}

	public static <T> T fromJson(String json, Class<T> clazz) {
		if (SysUtil.isEmpty(json) || EMPTY_JSON_STRING.equals(json)) {
			return null;
		}
		return gson.<T> fromJson(jsonStandardizing(json), clazz);
	}

	// 排除无用字符 规范json
	public static String jsonStandardizing(String json) {
		return json.replace("\n", "").replace("\r", "").replace(" ", "").replace("[]", "{}");
	}

	public static String objectToJson(Object object) {
		if (object == null) {
			return EMPTY_JSON_STRING;
		}
		return gson.toJson(object);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object jsonToObject(String json, Class clazz) {
		if (SysUtil.isEmpty(json) || EMPTY_JSON_STRING.equals(json)) {
			return null;
		}
		return gson.fromJson(json, clazz);
	}

	public static Map<String, Object> jsonToMap(String json) {
		if (SysUtil.isEmpty(json) || EMPTY_JSON_STRING.equals(json)) {
			return null;
		}
		return gson.fromJson(json, Map.class);
	}

	public static <T> List<T> jsonArrayToList(String jsonStr, Class<T> clazz, List<T> results) {
		if (results == null) {
			results = new ArrayList<T>();
		}
		List<T> list = gson.fromJson(jsonStr, new TypeToken<List<T>>() {
		}.getType());
		if (list != null && !list.isEmpty()) {
			for (T t : list) {
				results.add(gson.<T> fromJson(gson.toJson(t), clazz));
			}
		}
		return results;
	}

	public static boolean isEmpty(String jsonStr) {
		return "[]".equals(jsonStr.trim());
	}
}
