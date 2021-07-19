package cn.zq.cloudproviderpayment8001.rest;

import cn.zq.cloudproviderpayment8001.common.FileUtil;
import cn.zq.cloudproviderpayment8001.common.RestResponse;
import cn.zq.cloudproviderpayment8001.dao.ExportTestDto;
import cn.zq.cloudproviderpayment8001.entity.Payment;
import cn.zq.cloudproviderpayment8001.service.PaymentService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.microsoft.schemas.office.x2006.encryption.CTKeyEncryptor;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.poi.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class PaymentRest {
    private static Logger logger = LoggerFactory.getLogger(PaymentRest.class);
    @Autowired
    private PaymentService paymentService;

    @PostMapping(value = "/insert", produces = "application/json")
    public int inert(Payment paymentEo) {
        return paymentService.insert(paymentEo);
    }

    @GetMapping(value = "/{id}/queryById")
    public RestResponse<List<Payment>> queryPaymentInfoById(@Validated @PathVariable("id") Long id) {
        logger.info("哈哈哈哈哈范德萨哈哈哈哈");
        return new RestResponse<>(paymentService.queryPaymentEoById(id));
    }

    @PostMapping(value = "/test", produces = "application/json")
    public String testMethod(String code, String name, String token, @RequestBody JSONObject jsonObject) {
        System.out.println("测试:code:" + code + ",name:" + name + ",token:" + token + "\n" + JSON.toJSONString(jsonObject));
        return code + "/" + JSON.toJSONString(jsonObject);
    }

    @PostMapping("/savePicByFormData")
    public String savePicByFormData(@RequestParam("file") MultipartFile file) throws IOException {
        String fileName = savePicByFormDatas(file);
        return fileName;
    }

    public static String savePicByFormDatas(MultipartFile file) throws IOException {

        // 图片存储路径
        String path = "C:\\image\\factory";
        // 判断是否有路径
        if (!new File(path).exists()) {
            new File(path).mkdirs();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File tempFile = new File(path, fileName);
        if (!tempFile.exists()) {
            tempFile.createNewFile();
        }
        file.transferTo(tempFile);
        return fileName;
    }

    @RequestMapping("export")
    public void export(HttpServletResponse response) {

        //模拟从数据库获取需要导出的数据
        List<ExportTestDto> personList = new ArrayList<>();
        ExportTestDto person1 = new ExportTestDto(1, "路飞");
        ExportTestDto person2 = new ExportTestDto(2, "娜美");
        ExportTestDto person3 = new ExportTestDto(3, "索隆");
        ExportTestDto person4 = new ExportTestDto(4, "小狸猫");
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);

        //导出操作
        FileUtil.exportExcel(personList, "花名册", "草帽一伙", ExportTestDto.class, "海贼王.xls", response);
    }
//    @PostMapping("/savePicByFormData")
//    public String savePicByFormData(@RequestParam("file") MultipartFile file) throws IOException {
//        String fileName = savePicByFormDatas(file);
//        return fileName;
//    }
//
//    public static String savePicByFormDatas(MultipartFile file) throws IOException {
//
//        // 图片存储路径
//        String path = "C:\\image\\factory";
//        // 判断是否有路径
//        if (!new File(path).exists()) {
//            new File(path).mkdirs();
//        }
//        String fileName ="test.jpg";
//        File tempFile = new File(path,fileName);
//        if (!tempFile.exists()) {
//            tempFile.createNewFile();
//        }
//        file.transferTo(tempFile);
//        return fileName;
//    }

    public static void upload2() throws ClientProtocolException, IOException, URISyntaxException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse httpResponse = null;
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(200000).setSocketTimeout(200000000).build();
        URI uri = new URIBuilder().setScheme("http").setHost("localhost").setPort(12345)
                .setPath("/savePicByFormData").build();
        HttpPost httpPost = new HttpPost(uri);
        httpPost.setConfig(requestConfig);
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        //multipartEntityBuilder.setCharset(Charset.forName("UTF-8"));

        //File file = new File("F:\\Ken\\1.PNG");
        //FileBody bin = new FileBody(file);

        File file = new File("E:\\IdeaProjects\\dahua\\Image\\2021-07-15\\Big_Time_20210715_173716_1-1-0.jpg");

        //multipartEntityBuilder.addBinaryBody("file", file,ContentType.create("image/png"),"abc.pdf");
        //当设置了setSocketTimeout参数后，以下代码上传PDF不能成功，将setSocketTimeout参数去掉后此可以上传成功。上传图片则没有个限制
        //multipartEntityBuilder.addBinaryBody("file",file,ContentType.create("application/octet-stream"),"abd.pdf");
        multipartEntityBuilder.addBinaryBody("file", file);
        //multipartEntityBuilder.addPart("comment", new StringBody("This is comment", ContentType.TEXT_PLAIN));
        multipartEntityBuilder.addTextBody("comment", "this is comment");
        HttpEntity httpEntity = multipartEntityBuilder.build();
        httpPost.setEntity(httpEntity);

        httpResponse = httpClient.execute(httpPost);
        HttpEntity responseEntity = httpResponse.getEntity();
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode == 200) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(responseEntity.getContent()));
            StringBuffer buffer = new StringBuffer();
            String str = "";
            while (!StringUtils.isEmpty(str = reader.readLine())) {
                buffer.append(str);
            }

            System.out.println(buffer.toString());
        }

        httpClient.close();
        if (httpResponse != null) {
            httpResponse.close();
        }

    }


    //通过post请求
    public static String sendPost(String urlParame) throws IOException {
        //创建httpClient实例对象
        HttpClient httpClient = new HttpClient();
        //设置httpClient连接主机服务器超时时间： 以毫秒为单位 1000ms=1s，连接超时：为http连接主机服务器无法在规定时间内完成
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(10000);
        //创建post请求方法实例对象
        PostMethod postMethod = new PostMethod(urlParame);
        //设置post请求超时时间,value单位为毫秒    请求超时：请求超时就是连接成功了，但你发出去的请求在指定时间内没有任何回应
        postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 10000);
        postMethod.addRequestHeader("Content-Type", "application/json");

        NameValuePair um = new NameValuePair("code", "7010");
        NameValuePair am = new NameValuePair("name", "");
        NameValuePair arm = new NameValuePair("token", "7DF72F9A741DFD817906A063EBF9548F");
        NameValuePair pn = new NameValuePair("phoneNumber", "phone_number");
        NameValuePair pd = new NameValuePair("password", "MD5Util.MD5(pwd)");
        System.out.println(postMethod);
        NameValuePair[] data = {um, am, arm, pn, pd};


        // 将表单的值放入postMethod中
        postMethod.setRequestBody(data);
        //执行post
        httpClient.executeMethod(postMethod);
        //获得返回
        String result = postMethod.getResponseBodyAsString();
        //释放连接
        postMethod.releaseConnection();


        return result;
    }
}
