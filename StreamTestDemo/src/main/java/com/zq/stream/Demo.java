//package com.zq.stream;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import sun.misc.BASE64Encoder;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.URL;
//import java.net.URLConnection;
//import java.net.URLEncoder;
//import java.security.MessageDigest;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
///**
// * @author 01384752
// * @date 2019/7/9 11:23
// */
//@Slf4j
//public class Demo {
//    /**
//     * 向指定 URL 发送POST FORM方法的请求
//     *
//     * @param url   发送请求的 URL
//     * @param param 请求参数，请求参数应该是 key-value 的形式。
//     * @return 所代表远程资源的响应结果
//     */
//    public static String sendPostForm(String url, Map<String, String> param) {
//        PrintWriter out = null;
//        BufferedReader in = null;
//        String result = "";
//
//        try {
//            URL realUrl = new URL(url);
//            // 打开和URL之间的连接
//            URLConnection conn = realUrl.openConnection();
//            // 设置通用的请求属性
//            conn.setRequestProperty("accept", "*/*");
//            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
//            conn.setRequestProperty("connection", "Keep-Alive");
//            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//            // 发送POST请求必须设置如下两行
//            conn.setDoOutput(true);
//            conn.setDoInput(true);
//            // 获取URLConnection对象对应的输出流
//            out = new PrintWriter(conn.getOutputStream());
//
//            String body = "";
//            for (String key : param.keySet()) {
//                body += "&" + key + "=" + URLEncoder.encode(param.get(key), "UTF-8");
//            }
//            body = body.substring(1);
//            // 发送请求参数
//            out.print(body);
//            log.info("Demo.sendPostForm request , url:{},body:{}", url, body);
//            // flush输出流的缓冲
//            out.flush();
//            // 定义BufferedReader输入流来读取URL的响应
//            in = new BufferedReader(
//                    new InputStreamReader(conn.getInputStream()));
//            String line;
//            while ((line = in.readLine()) != null) {
//                result += line;
//            }
//        } catch (Exception e) {
//            log.error("Demo.sendPostForm is error, url={}|param={}|e={}", url, param, e);
//        }
//        //使用finally块来关闭输出流、输入流
//        finally {
//            try {
//                if (out != null) {
//                    out.close();
//                }
//                if (in != null) {
//                    in.close();
//                }
//            } catch (IOException ex) {
//                log.error("Demo.sendPostForm close is error, url={}, params={}", url, param, ex);
//            }
//        }
//        return result;
//    }
//
//    public static void main(String[] args) throws ParseException {
////        String time = "2020-12-12";
////        SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd");
////        System.out.println(sft.format(sft.parse(time)));
//
////        String extension = "[{\"minOrderTime\":\"2020-12-24 14:09:57\",\"maxOrderTime\":\"2020-12-24 18:19:44\"}" +
////                "{\"minOrderTime\":\"2020-12-28 14:09:57\",\"maxOrderTime\":\"2020-12-28 18:19:44\"}]";
////        JSONObject pa = JSONObject.parseObject(extension);
//
//        String json = "[{\"condition\":{},\"conditionText\":\"${flag==false}\",\"name\":\"不同意\"},{\"condition\":{},\"conditionText\":\"${flag==true}\",\"name\":\"同意\"}]";
//
//        JSONArray ja = JSONArray.parseArray(json);
//        for (int i=0;i<ja.size();i++){
//            System.out.println(ja.get(i));
//            JSONObject jsonObject = ja.getJSONObject(i);
//            System.out.println(jsonObject.get("conditionText"));
//            String conditionText = jsonObject.get("conditionText").toString().substring(8,jsonObject.get("conditionText").toString().length()-1);
//            System.out.println(conditionText);
////            JSONObject jo = JSONArray.parseObject(conditionText);
////            System.out.println(jo.get("flag"));
//        }
//
////        Object minOrderTime = pa.get("minOrderTime");
////        SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////        Date parse = sft.parse(minOrderTime.toString());
////        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(parse));
//    }
//
//    @Test
//    public void test() {
//        try {
//            Map<String, String> params = new HashMap<>();
//            Long timestamp = System.currentTimeMillis();
//            //这里面是文档里面的参数，格式是JSON
//
//            String msgData = "{\"orderid\":\"o20190429203991149013\",\"sendCompany\":\"寄件公司\",\"sendAddress\":\"广东光电科技产业基地\",\"sendContact\":\"李四\",\"sendTel\":\"0755-82458892\",\"sendMobile\":\"15988888888\",\"sendShippercode\":\"cn\",\"sendCountry\":\"中国\",\"sendProvince\":\"广东\",\"sendCity\":\"广州\",\"sendCounty\":\"越秀区\",\"sendPostCode\":\"510000\",\"deliveryAddress\":\"软件产业园基地1C\",\"deliveryCompany\":\"顺丰科技\",\"deliveryTel\":\"0755-75821855\",\"deliveryMobile\":\"18686608899\",\"deliveryDeliverycode\":\"0086\",\"deliveryCountry\":\"中国\",\"deliveryProvince\":\"广东\",\"deliveryCity\":\"深圳\",\"deliveryCounty\":\"南山\",\"deliveryContact\":\"张三\",\"deliveryPostCode\":\"518000\",\"custid\":\"8866600052\",\"payMethod\":1,\"expressType\":\"154\",\"parcelQuantity\":10,\"cargoLength\":88.12,\"cargoHeight\":10,\"cargoWidth\":40,\"volume\":152.45,\"cargoTotalWeight\":103,\"declaredValue\":5000,\"declaredValueCurrency\":\"cny\",\"customsBatchs\":\"fx25\",\"sendstarttime\":\"2019-04-19 08:00:00\",\"isDocall\":1,\"needReturnTrackingNo\":1,\"dTaxNo\":\"taxno89398\",\"taxPayType\":1,\"taxSetAccounts\":2130940328,\"originalNumber\":\"jd58691231\",\"paymentTool\":\"wx\",\"paymentNumber\":\"2165465463496746165\",\"goodsCode\":\"2515485451522\",\"inProcessWaybillNo\":\"sf5451548452\",\"brand\":\"cherry mx\",\"specifications\":\"3.0\",\"tempRange\":1,\"orderName\":\"下单人名字\",\"orderCertType\":\"身份证\",\"orderCertNo\":\"360782199507110582\",\"orderSource\":\"互联网\",\"template\":\"bspi2243\",\"remark\":\"商品\",\"oneselfPickupFlg\":0,\"dispatchSys\":\"managerServer\",\"isGenEletricPic\":1,\"waybillSize\":16.41,\"filterField\":\"efhei\",\"totalNetWeight\":133.4,\"sendRemarkTwo\":\"256415815@qq.com\",\"urlFlag\":1,\"specialDeliveryTypeCode\":\"证件后八位\",\"specialDeliveryValue\":13243543,\"realnameNum\":\"s23445542324344\",\"merchantPayOrder\":\"m123344545\",\"routelabelforreturn\":1,\"routelabelservice\":0,\"jTaxNo\":\"t345554534343\",\"isUnifiedWaybillNo\":0,\"isinstallservice\":1,\"installcargotype\":\"6\",\"iselevator\":0,\"boxno\":\"5\",\"restype\":2,\"cargotype\":\"34545\",\"cargoname\":\"键盘\",\"getcargomode\":2,\"reqdeliverydt\":\"2019-04-18 20:00:00\",\"extramap\":[{\"attrname\":\"attrname1\",\"attrval\":\"attrval1\"},{\"attrname\":\"attrname2\",\"attrval\":\"attrval2\"}],\"cargolist\":[{\"name\":\"food\",\"count\":1,\"unit\":\"包\",\"length\":10,\"width\":50,\"amount\":12.525,\"currency\":\"\",\"sourcearea\":\"cn\",\"productrecordno\":\"fe23445\",\"goodPrepardNo\":\"no3433432232\",\"taxNo\":\"t3354654545\",\"hsCode\":\"hs232343545\",\"volume\":3232.2323,\"boxno\":32,\"installcargotype\":6,\"height\":50}],\"addedservice\":[{\"name\":\"cod\",\"value\":\"10000\",\"value1\":\"20000\"}]}";
//            //这个是授权的MD5Key
//            String md5Key = "2Z7YPBNG2CKKBV17";
//            //这个是接口对应的service code
//            params.put("serviceCode", "FOP_RECE_LTL_CREATE_ORDER");
//
//            params.put("partnerID", "fop_test");
//            params.put("requestID", UUID.randomUUID().toString());
//            params.put("timestamp", timestamp + "");
//            params.put("msgDigest", genDigest(timestamp.toString(), msgData, md5Key));
//            params.put("msgData", msgData);
//
//            String data = sendPostForm("http://sfapi.sit.sf-express.com:45290/std/service", params);
//            System.out.println("oooooo======" + data);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static String genDigest(String timestamp, String mgsData, String md5key) throws Exception {
//        //将业务报文+时间戳+秘钥组合成需加密的字符串(注意顺序)
//        String toVerifyText = mgsData + timestamp + md5key;
//
//        //因业务报文中可能包含加号、空格等特殊字符，需要urlEnCode处理
//        toVerifyText = URLEncoder.encode(toVerifyText, "UTF-8");
//
//        //进行Md5加密
//        MessageDigest md5 = MessageDigest.getInstance("MD5");
//        md5.update(toVerifyText.getBytes("UTF-8"));
//        byte[] md = md5.digest();
//
//        //通过BASE64生成数字签名
//        String msgDigest = new String(new BASE64Encoder().encode(md));
//
//        return msgDigest;
//    }
//}
