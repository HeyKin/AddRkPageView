package xyz.heykin.ProxyCrawler.utlis;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

import net.sf.json.JSONObject;

public class HttpSendUtils {

    private static final int OK = 200;   //成功
    private static final int FAIL = 400; //参数不正确
    private static final int EXC = 404;  //异常
    private static final String CHARNAME_NAME = "UTF-8";


    /** 
    * @Description: 发送Get
    * @Param: [paramsMap] url(必要),请求url.
    * params请求参数(格式为字符串"参数1=11&参数2=22").
    * headers,请求头部(格式为Map)
    * @return: java.util.Map code状态码,result结果
    * @Author: heykin
    * @Date: 2018/1/17 
    */ 
    public static Map sendGet(Map paramsMap){
        Map map = new HashMap();
        BufferedReader br = null;
        URLConnection conn = null;
        String url = "";
        if(paramsMap.get("url")!=null&&!"".equals(paramsMap.get("url"))){
            url = (String)paramsMap.get("url");
            if(paramsMap.get("params")!=null&&!"".equals(paramsMap.get("params"))){
                url += "?" + paramsMap.get("params");
            }

            try {
                URL realURL = new URL(url);
                //打开和url之间的连接
                conn = realURL.openConnection();
                //设置属性,若不传递参数则默认给空
                if(paramsMap.get("headers")!=null){
                    Set<String> keys = ((Map)paramsMap.get("headers")).keySet();
                    for(String key:keys){
                        conn.setRequestProperty(key,((Map)paramsMap.get("headers")).get(key).toString());
                    }
                }
               
                br = new BufferedReader(new InputStreamReader(conn.getInputStream(), CHARNAME_NAME));
                String line = null;
                StringBuilder sb = new StringBuilder();
                while((line = br.readLine()) != null){
                    sb.append(line + "\n");
                }
                map.put("code",OK);
                map.put("result",sb.toString());
            } catch (MalformedURLException e) {
                map.put("code",EXC);
                map.put("result","url转换异常,请检查url");
                e.printStackTrace();
            } catch (IOException e) {
                map.put("code",EXC);
                map.put("result","请求连接失败");
                e.printStackTrace();
            }finally {
                if(br != null){
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                
            }

        }else{
            map.put("code",FAIL);
            map.put("result","请求url不能为空");
        }

        return map;
    }
   
}
