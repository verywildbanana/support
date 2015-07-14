package com.lhd.util;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	// Pull all links from the body for easy retrieval
	public static final ArrayList<String> pullLinks(String text) {
		ArrayList<String> links = new ArrayList<String>();

		String regex = "\\(?\\b(http://|www[.])[-A-Za-z0-9+&@#/%?=~_()|!:,.;]*[-A-Za-z0-9+&@#/%=~_()|]";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(text);
		while (m.find()) {
			String urlStr = m.group();
			if (urlStr.startsWith("(") && urlStr.endsWith(")")) {
				urlStr = urlStr.substring(1, urlStr.length() - 1);
			}
			links.add(urlStr);
		}
		return links;
	}

	public static final boolean isNull(String text) {

		if (text == null || text.trim().equals("")
				|| text.trim().equalsIgnoreCase("null")) {

			return true;

		}

		return false;
	}

	public static String readTextToAssets(Context context, String fileName)
			throws IOException {
		// InputStream is = getAssets().open(fileName);
		//
		// int size = is.available();
		// byte[] buffer = new byte[size];
		// is.read(buffer);
		// is.close();
		//
		// String text = new String(buffer);
		//
		// return text;

		// String mLine = null;
		//
		// try {
		// BufferedReader reader = new BufferedReader(
		// new InputStreamReader(getAssets().open(file), "UTF-8"));
		//
		// // do reading, usually loop until end of file reading
		// mLine = reader.readLine();
		// while (mLine != null) {
		// //process line
		// mLine = reader.readLine();
		// }
		//
		// reader.close();
		// } catch (IOException e) {
		// //log the exception
		// }
		//
		// return mLine;
		StringBuilder ReturnString = new StringBuilder();
		InputStream fIn = null;
		InputStreamReader isr = null;
		BufferedReader input = null;
		try {
			fIn = context.getResources().getAssets()
					.open(fileName, context.MODE_WORLD_READABLE);
			isr = new InputStreamReader(fIn);
			input = new BufferedReader(isr);
			String line = "";
			while ((line = input.readLine()) != null) {
				ReturnString.append(line);
				ReturnString.append('\n');
			}
		} catch (Exception e) {
			e.getMessage();
		} finally {
			try {
				if (isr != null)
					isr.close();
				if (fIn != null)
					fIn.close();
				if (input != null)
					input.close();
			} catch (Exception e2) {
				e2.getMessage();
			}
		}
		return ReturnString.toString();

		// InputStream inputStream =
		// getResources().openRawResource(R.raw.lbs_info);
		// ByteArrayOutputStream byteArrayOutputStream = new
		// ByteArrayOutputStream();
		//
		// int i;
		// try {
		// i = inputStream.read();
		// while (i != -1)
		// {
		// byteArrayOutputStream.write(i);
		// i = inputStream.read();
		// }
		// inputStream.close();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// // e.printStackTrace();
		// }
		//
		// return byteArrayOutputStream.toString();

	}

	/**
	 * 3자리마다 comma 찍도록 하는 함수.
	 * @param value 현재 값.
	 * @return comma 가 있는 String 값.
	 */
	public static String makeStringComma(String param) {
		long value = Long.parseLong(param);
		
		DecimalFormat format = new DecimalFormat("###,###");
		return format.format(value);
	}
	
	/**
	 * comma 를 제거하는 함수.
	 * @param value comma 가 있는 String 
	 * @return comma 가 없는 값 
	 */
	public static String removeStringComma(String value) {
		return value.replaceAll(",", "").replaceAll("원", "");
	}
	
	/**
	 * 두 자리로 자릿수를 맞춰주는 함수. 
	 * @param val 자릿수를 맞추고자 하는 값
	 * @return 00 형식의 값
	 */
	public static String formatInteger(int val) {
		return String.format("%02d", val);
	}
	
}
