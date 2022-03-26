package com.baidu.ai.aip.utils;

import com.google.gson.*;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class GsonUtils {
	private static Gson gson = new GsonBuilder().create();

	public static String toJson(Object value) {
		return gson.toJson(value);
	}

	public static <T> T fromJson(String json, Class<T> classOfT)
			throws JsonParseException {
		return gson.fromJson(json, classOfT);
	}

	@SuppressWarnings("unchecked")
	public static <T> T fromJson(String json, Type typeOfT)
			throws JsonParseException {
		return (T) gson.fromJson(json, typeOfT);
	}
}
