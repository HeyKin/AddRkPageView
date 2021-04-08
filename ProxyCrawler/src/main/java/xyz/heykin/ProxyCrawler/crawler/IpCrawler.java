package xyz.heykin.ProxyCrawler.crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import xyz.heykin.ProxyCrawler.bean.IpBean;
import xyz.heykin.ProxyCrawler.utlis.HttpSendUtils;

public class IpCrawler {
	
	private final static String API = "https://www.xicidaili.com/wt/";
	
	private final static String RK_URL = "http://www.rkpass.cn/addtrace.jsp?url_save=http://www.rkpass.cn/u.jsp___u=xxxx";
	
	private final static int TOTAL_PAGE = 200;
	
	public static void main(String[] args) {
		int i = 0;
		while((++i) < TOTAL_PAGE) {
			crawler(API, i);
		}
	}

	private static void crawler(String api, int index){
		Map map = new HashMap();
	    map.put("url", api + index);
	    Map map1 = new HashMap();
	    map1.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
	    map1.put("Accept-Language", "zh-Hans-CN,zh-Hans;q=0.5");
	    map1.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.140 Safari/537.36 Edge/17.17134");
	    map.put("headers", map1);
	    map = HttpSendUtils.sendGet(map);
	    System.out.println(map);
        String html = (String) map.get("result");
        
        Document document = Jsoup.parse(html);
        Elements eles = document.selectFirst("table").select("tr");

        List ipList = new ArrayList();
        for (int i = 0; i < eles.size(); i++){
            if (i == 0) continue;
            Element ele = eles.get(i);
            String ip = ele.children().get(1).text();
            int port = Integer.parseInt(ele.children().get(2).text().trim());
            String typeStr = ele.children().get(5).text().trim();
            

            IpBean ipBean = new IpBean(ip, port);
            addRkPageView(ipBean);
            //ipList.add(ipBean);
        }
        //return ipList;
    }
	
	public static void addRkPageView(IpBean ipBean) {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ipBean.getIp(), ipBean.getPort()));
        BufferedReader br = null;
        try {
            URLConnection httpCon = new URL(RK_URL).openConnection(proxy);
            httpCon.setConnectTimeout(5000);
            httpCon.setReadTimeout(5000);
            httpCon.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            httpCon.setRequestProperty("Accept-Language", "zh-Hans-CN,zh-Hans;q=0.5");
            httpCon.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.140 Safari/537.36 Edge/17.17134");
            br = new BufferedReader(new InputStreamReader(httpCon.getInputStream(), "UTF-8"));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while((line = br.readLine()) != null){
                sb.append(line + "\n");
            }
            System.out.println(sb.toString());
        } catch (IOException e) {
            //e.printStackTrace();
        	System.out.println("请求失败");
        } finally {
            if(br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            
        }
    }
}
