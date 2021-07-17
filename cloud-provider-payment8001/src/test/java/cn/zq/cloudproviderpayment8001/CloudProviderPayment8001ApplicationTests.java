package cn.zq.cloudproviderpayment8001;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.HttpURLConnection;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CloudProviderPayment8001ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void test() throws IOException {
//        sendPost("http://localhost:8001/test");
        sendPost_body("http://localhost:8001/test", null);
    }

    @Test
    public void doPostTestThree() {

        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 创建Post请求
        // 参数
        URI uri = null;
        try {
            // 将参数放入键值对类NameValuePair中,再放入集合中
            List<org.apache.http.NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("code", "7010"));
            params.add(new BasicNameValuePair("token", "7DF72F9A741DFD817906A063EBF9548F"));
            params.add(new BasicNameValuePair("method", "test"));
            // 设置uri信息,并将参数集合放入uri;
            // 注:这里也支持一个键值对一个键值对地往里面放setParameter(String key, String value)
            uri = new URIBuilder().setScheme("http").setHost("localhost").setPort(12345)
                    .setPath("/doPostControllerThree").setParameters(params).build();
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }

        HttpPost httpPost = new HttpPost(uri);
        // HttpPost httpPost = new
        // HttpPost("http://localhost:12345/doPostControllerThree1");

        // 创建user参数
//		User user = new User();
//		user.setName("潘晓婷");
//		user.setAge(18);
//		user.setGender("女");
//		user.setMotto("姿势要优雅~");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("test", "hah");
        // 将user对象转换为json字符串，并放入entity中
        StringEntity entity = new StringEntity(JSON.toJSONString(jsonObject), "UTF-8");

        // post请求是将参数放在请求体里面传过去的;这里将entity放入post请求体中
        httpPost.setEntity(entity);

        httpPost.setHeader("Content-Type", "application/json;charset=utf8");

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();

            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //通过post请求
    public static String sendPost(String urlParame) throws IOException {
        //创建httpClient实例对象
        HttpClient httpClient=new HttpClient();
        //设置httpClient连接主机服务器超时时间： 以毫秒为单位 1000ms=1s，连接超时：为http连接主机服务器无法在规定时间内完成
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(10000);
        //创建post请求方法实例对象
        PostMethod postMethod=new PostMethod(urlParame);
        //设置post请求超时时间,value单位为毫秒    请求超时：请求超时就是连接成功了，但你发出去的请求在指定时间内没有任何回应
        postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,10000);
        postMethod.addRequestHeader("Content-Type", "application/json");
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("test1", "ooo");
        NameValuePair um  = new NameValuePair("code", "7010");
        NameValuePair am= new NameValuePair("jsonObject", jsonObject1.toJSONString());
        NameValuePair arm = new NameValuePair("token", "7DF72F9A741DFD817906A063EBF9548F");
        NameValuePair pn= new NameValuePair("phoneNumber", "phone_number");
        NameValuePair pd = new NameValuePair("password", "MD5Util.MD5(pwd)");
        System.out.println(postMethod);
        NameValuePair[] data = { um,am,arm,pn,pd };


        // 将表单的值放入postMethod中
        postMethod.setRequestBody(data);
        //执行post
        httpClient.executeMethod(postMethod);
        //获得返回
        String result=postMethod.getResponseBodyAsString();
        //释放连接
        postMethod.releaseConnection();


        return result;
    }


    public String sendPost_body(String urls, String s) {

// 创建url资源
        OutputStreamWriter out = null;
        URL url = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
//            url = new URL(arl);

             url = new URL(null, urls, new sun.net.www.protocol.https.Handler());// 建立http连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
// 设置允许输出
            conn.setDoOutput(true);
            conn.setDoInput(true);
// 设置不用缓存
            conn.setUseCaches(false);
// 设置传递方式
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setInstanceFollowRedirects(true);
            conn.setRequestMethod("POST"); // 设置请求方式
            conn.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
            conn.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");//设置消息头，解决508错误
// 开始连接请求
            conn.connect();
            out = new OutputStreamWriter(
                    conn.getOutputStream(), "UTF-8"); // utf-8编码
// 写入请求的字符串
            JSONObject jsonObject = new JSONObject();
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("test1", "ooo");
            jsonObject.put("code", "hahah");
            jsonObject.put("jsonObject", jsonObject1);
            out.append(s);
            out.flush();
            out.close();
//	System.out.println(conn.getResponseCode());
            if (conn.getResponseCode() == 200) {
//	System.out.println("success");
                in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
                String line;
                while ((line = in.readLine()) != null) {
                    result.append(line);
                }
            }

        } catch (Exception e) {
//	System.out.println("发送 POST 请求出现异常！" + e);
            result = new StringBuilder("{\"resCode\":\"1\",\"errCode\":\"1001\",\"resData\":\"\"}");
            e.printStackTrace();
//	log.error("远程服务未开启", e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result.toString();

    }

}
