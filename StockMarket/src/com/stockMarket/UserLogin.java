package com.stockMarket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.net.HttpURLConnection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.stockMarket.Parse.ParseXMTradeRecord;
import com.stockMarket.dao.XMTradeDao;
import com.stockMarket.model.XMTrade;


public class UserLogin {

	  private List<String> cookies;
	  private HttpURLConnection conn;

	  private final String USER_AGENT = "Mozilla/5.0";
	
	  public static void main(String[] args) throws Exception {

			String loginInterfaceUrl = "http://www.stockradar.hk/page.php?p=login";
			String loginUrl = "http://www.stockradar.hk/member/_login.php";
			String stockRadar = "http://www.stockradar.hk/page.php?p=newipo";
			String stockCASS = "http://www.stockradar.hk/page.php?p=ccass_update_vip";
			String stockXMTrade = "http://www.stockradar.hk/page.php?p=t_XM_vip&s=%26wdate%3D2015_11_12";
			
			UserLogin http = new UserLogin();

			// make sure cookies is turn on
			CookieHandler.setDefault(new CookieManager());

			// 1. Send a "GET" request, so that you can extract the form's data.
			String page = http.GetPageContent(loginInterfaceUrl,true);
			System.out.println("Page : " + page);
			
			// 2. Construct above post's content and then send a POST request for
			// authentication
			http.sendPost(loginUrl, "username=tmkk83&password=95430901");

			// 3. success then go to gmail.
			String result = http.GetPageContent(stockRadar,false);
			System.out.println(result);
			
		    //result = http.GetPageContent(stockCASS,false);
			//System.out.println(result);
			
			result = http.GetPageContent(stockXMTrade,false);
			System.out.println(result);
			/*
			ParseXMTradeRecord parser = new ParseXMTradeRecord();
			ArrayList<XMTrade> trades = parser.parseFromHTML(result,"2015-11-12");
			
			XMTradeDao dao = new XMTradeDao();
			
			Iterator<XMTrade> iterator = trades.iterator();
			while (iterator.hasNext()) {
				dao.addXMTrade(iterator.next());
			}
			*/
			
		  }
	 
	  private String GetPageContent(String url, boolean initialFlag) throws Exception {

			URL obj = new URL(url);
			conn = (HttpURLConnection) obj.openConnection();

			// default is GET
			conn.setRequestMethod("GET");

			conn.setUseCaches(false);

			// act like a browser
			conn.setRequestProperty("User-Agent", USER_AGENT);
			conn.setRequestProperty("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			if (cookies != null) {
				for (String cookie : this.cookies) {
					conn.addRequestProperty("Cookie", cookie.split(";",1)[0]);
					System.out.println("Cookie : "+cookie.split(";",1)[0]);
				}
			}
			int responseCode = conn.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = 
		            new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// Get the response cookies
			if(initialFlag)
			{
				setCookies(conn.getHeaderFields().get("Set-Cookie"));
				System.out.println("Set Cookie : "+conn.getHeaderFields().get("Set-Cookie"));
			}
				
			return response.toString();

		  }
	  
	  public List<String> getCookies() {
			return cookies;
	  }

      public void setCookies(List<String> cookies) {
			this.cookies = cookies;
	  }
      
      public String getFormParams(String html, String username, String password)
    			throws UnsupportedEncodingException {

    		System.out.println("Extracting form's data...");

    		Document doc = Jsoup.parse(html);

    		// Google form id
    		Element loginform = doc.getElementById("colleft");
    		Elements inputElements = loginform.getElementsByTag("input");
    		List<String> paramList = new ArrayList<String>();
    		for (Element inputElement : inputElements) {
    			String key = inputElement.attr("name");
    			String value = inputElement.attr("value");

    			if (key.equals("username"))
    			{
    				value = username;
    				paramList.add(key + "=" + URLEncoder.encode(value, "UTF-8"));
    			}
    			else if (key.equals("password"))
    			{
    				value = password;
    				paramList.add(key + "=" + URLEncoder.encode(value, "UTF-8"));
    			}
    				
    		}

    		// build parameters list
    		StringBuilder result = new StringBuilder();
    		for (String param : paramList) {
    			if (result.length() == 0) {
    				result.append(param);
    			} else {
    				result.append("&" + param);
    			}
    		}
    		return result.toString();
      }
      
      private void sendPost(String url, String postParams) throws Exception {

    		URL obj = new URL(url);
    		conn = (HttpURLConnection) obj.openConnection();

    		// Acts like a browser
    		conn.setUseCaches(false);
    		conn.setRequestMethod("POST");
    		
    		conn.setRequestProperty("Accept",
        			"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
    		conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
    		conn.setRequestProperty("Accept-Language", "en-US,en;q=0.8,zh-TW;q=0.6,zh;q=0.4");
    		
    		conn.setRequestProperty("Cache-Control", "max-age=0");
    		
    		conn.setRequestProperty("Connection", "keep-alive");
    		conn.setRequestProperty("Content-Length", Integer.toString(postParams.length()));
    		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

    		for (String cookie : this.cookies) {
    			conn.addRequestProperty("Cookie", cookie.split(";", 2)[0]);
    			System.out.println("Cookie : "+ cookie.split(";", 2)[0]);
    		}
    		
    		conn.setRequestProperty("Host", "www.stockradar.hk");
    		conn.setRequestProperty("Origin", "http://www.stockradar.hk");
    		conn.setRequestProperty("Referer", "http://www.stockradar.hk/page.php?p=login");
    		conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.86 Safari/537.36");

    		conn.setDoOutput(true);
    		conn.setDoInput(true);

    		// Send post request
    		
    		DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
    		wr.writeBytes(postParams);
    		wr.flush();
    		wr.close();
    		

    		
    		int responseCode = conn.getResponseCode();
    		System.out.println("\nSending 'POST' request to URL : " + url);
    		System.out.println("Post parameters : " + postParams);
    		System.out.println("Response Code : " + responseCode);

    		BufferedReader in = 
    	             new BufferedReader(new InputStreamReader(conn.getInputStream()));
    		String inputLine;
    		StringBuffer response = new StringBuffer();

    		while ((inputLine = in.readLine()) != null) {
    			response.append(inputLine);
    		}
    		in.close();
    		System.out.println("Response Msg: "+response.toString());

    	  }
  
}
