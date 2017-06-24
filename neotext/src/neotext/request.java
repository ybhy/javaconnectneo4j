package neotext;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.omg.CORBA.portable.InputStream;

public class request {
	

	/**
	 * @param urlAll
	 *            :请求接口
	 * @param httpArg
	 *            :参数
	 * @return 返回结果
	 */
	
	public static String request(String httpUrl, String httpArg) {
	    BufferedReader reader = null;
	    String result = null;
	    StringBuffer sbf = new StringBuffer();

	    try {
	        URL url = new URL(httpUrl);
	        HttpURLConnection connection = (HttpURLConnection) url
	                .openConnection();
	        connection.setRequestMethod("POST");
	        connection.setRequestProperty("Content-Type",
	                        "application/x-www-form-urlencoded");
	        // 填入apikey到HTTP header
	        connection.setRequestProperty("apikey",  "eeeeed4b9a1c5bfb1be616d6f13df759");
	        connection.setDoOutput(true);
	        connection.getOutputStream().write(httpArg.getBytes("UTF-8"));
	        connection.connect();
	        java.io.InputStream is = connection.getInputStream();
	        reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        String strRead = null;
	        while ((strRead = reader.readLine()) != null) {
	            sbf.append(strRead);
	            sbf.append("\r\n");
	        }
	        reader.close();
	        result = sbf.toString();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}

	public static void main(String[] args){
		String httpUrl = "http://apis.baidu.com/baidu_openkg/xiaoshuo_kg/xiaoshuo_kg";
		String httpArg = "{\"query\": \"大主宰\", \"resource\": \"spo_novel\"}";
		String jsonResult = request(httpUrl, httpArg);
		System.out.println(jsonResult);
	}
}