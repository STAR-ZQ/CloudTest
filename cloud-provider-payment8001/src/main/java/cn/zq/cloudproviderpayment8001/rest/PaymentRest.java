package cn.zq.cloudproviderpayment8001.rest;

import cn.zq.cloudproviderpayment8001.common.FileUtil;
import cn.zq.cloudproviderpayment8001.common.RestResponse;
import cn.zq.cloudproviderpayment8001.dao.ExportTestDto;
import cn.zq.cloudproviderpayment8001.entity.Payment;
import cn.zq.cloudproviderpayment8001.service.PaymentService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.HttpURLConnection;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
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
        System.out.println("测试:code:" + code + ",name:" + name + ",token:" + token + "\n" + jsonObject);
        return code + "/" + jsonObject;
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

    public static void main(String[] args) throws Exception {
        Class<?> clazz = null;
        Field field = null;
        Method method = null;

        clazz = Class.forName("cn.zq.cloudproviderpayment8001.entity.Payment");
        //field = clazz.getField("num");       getField() 方法不能获取私有的属性
        // field = clazz.getField("type");     访问私有字段时会报 NoSuchFieldException异常
        field = clazz.getDeclaredField("type");     //获取私有type 属性
        Field id = clazz.getField("id");
        field.setAccessible(true);  //对私有字段的访问取消检查
        Payment fruit = (Payment) clazz.newInstance();  //创建无参对象实例
        field.set(fruit, "Apple");   //为无参对象实例属性赋值
        id.set(fruit, Long.valueOf("123456"));
        Object type = field.get(fruit); //通过fruit 对象获取属性值
        System.out.println(type + "/" + id.get(fruit));

        method = clazz.getMethod("show", null);
        method.invoke(fruit, null);

        method = clazz.getMethod("show", int.class);
        method.invoke(fruit, 20);
        //sendPost("http://localhost:8081");
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
