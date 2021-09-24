package com.zq.stream;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Lists;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.zq.stream.test.ProduceRealDTO;
import com.zq.stream.test.RedisDto;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamApiTest {

    public static void main(String[] args) {
        //集合排序
        List<String> list = new ArrayList<>();
        list.add("2031-01-02");
        list.add("2011-01-02");
        List<String> collect = list.stream().sorted((a1, a2) -> a1.compareTo(a2)).collect(Collectors.toList());
        System.out.println(collect);

        //自定义格式时间
        LocalDateTime nowTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(formatter.format(nowTime));
        //默认格式时间
        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE;
        LocalDateTime testNowTime = LocalDateTime.now();
        System.out.println(dtf.format(testNowTime));
        //时间   类型转换
        LocalDateTime ldt1 = LocalDateTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH/mm/ss");
        String timeOne = timeFormatter.format(ldt1);
        System.out.println("localDateTime类型转String:" + timeOne);
        String testTime = "2020/11/12 11/22/23";
        LocalDateTime timeTwo = LocalDateTime.parse(testTime, timeFormatter);
        System.out.println("String类型转换为LocalDateTime:" + timeTwo);
        //zoneDateTime
        LocalDateTime zoneTime = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println("zoneDateTime" + zoneTime);
        //方法计算字符串长度
        int length = testFun(testTime, s -> s.length());
        System.out.println("方法计算字符串长度" + length);
        //过滤获取数字开头的数据
        List<String> listTest = new ArrayList<>();
        listTest.add("1sdf");
        listTest.add("addd0");
        listTest.add("2asdf");
        listTest.add("d0909");
        listTest.add("basdfgh");
        List<String> collect1 = listTest.stream().filter(e -> Character.isDigit(e.charAt(e.length() / 2))).collect(Collectors.toList());
        collect1.forEach(s -> System.out.println("数据索引为" + s.length() / 2));
        System.out.println("过滤获取数字开头的数据" + collect1);
        //将两个数组集合组合为一个
        Stream<?> flatMap = Stream.of(Arrays.asList("a", "b"), Arrays.asList(1, 2, 3)).flatMap((s) -> s.stream());
        flatMap.forEach(System.out::println);
        //获取最大值和最小值
        int max = listTest.stream().map(l -> l.length()).max(Integer::compareTo).get();
        int maxTest = listTest.stream().max(Comparator.comparing(s -> s.length())).get().length();
        System.out.println(+max + "maxTest:" + maxTest);
        int min = listTest.stream().min(Comparator.comparing(s -> s.length())).get().length();
        System.out.println("长度最小值：" + min);

        /**
         *  reduce ：规约操作
         */
        List<Integer> list7 = Arrays.asList(9, 4, 4);
        Integer count2 = list7.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println(count2);

//        Optional<Double> sum = emps.stream()
//                .map(Employee::getSalary)
//                .reduce(Double::sum);
//        System.out.println(sum);

        Comparator<Integer> testComparetor = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 + o2;
            }
        };

        System.out.println("testComparetor:" + testComparetor.compare(1, 2));

        String result = testFunction1(5l, x -> x + x);
        System.out.println(result);
    }

    @Test
    public void testTime() throws ParseException {
//        SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = new Date("Tue Nov 17 15:18:39 CST 2020");
//        String format = sft.format(date);
//        System.out.println(format);

        SimpleDateFormat sft = new SimpleDateFormat("yyyy年M月");
        Date parse = sft.parse("2028-03");
        System.out.println(sft.format(parse));
        int month = parse.getMonth() + 1;
        int year = parse.getYear() + 1900;
        System.out.println(year + "年" + month + "月");
    }

    public static String testFunction1(Long str, Function<Long, Long> function) {
        Long result = function.apply(str);
        return "测试数据的结果为：" + result;
    }

    int c;
    int a = 1;
    int b = 2;

    @Test
    public void swap() {
        swapMethod(a, b);
        System.out.println(a + "/" + b);
    }

    public void swapMethod(int a, int b) {
        this.c = a;
        this.a = b;
        this.b = c;
    }

    @Test
    public void testTime1() throws ParseException {
        SimpleDateFormat sft = new SimpleDateFormat("EEE MMM d HH:mm:ss 'CST' yyyy", Locale.ENGLISH);
        Date parse = sft.parse("Wed Sep 01 06:40:24 CST 2021");
        System.out.println(parse);
        System.out.println(parse.getTime() / 1000L);

        String dateString = "Wed Sep 01 06:40:24 CST 2021";
        SimpleDateFormat sfEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sfStart = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", java.util.Locale.ENGLISH);
        System.out.println(sfEnd.format(sfStart.parse(dateString)));
    }

    @Test
    public void redisTest() {
        RedisDto redisDto = new RedisDto();
        redisDto.setOt(BigDecimal.ZERO);
        redisDto.setEf(BigDecimal.ZERO);
        redisDto.setAvgSpeed(BigDecimal.ZERO);
        redisDto.setOffTime(Long.valueOf(1));
        redisDto.setOnTime(Long.valueOf(1));
        redisDto.setPulse(Long.valueOf(1));
        redisDto.setSpeedSP("1");
        redisDto.setStopNum(1);
        redisDto.setStopTime(Long.valueOf(12));
        redisDto.setTime(Long.valueOf(1));
        redisDto.setUpTimeOt(BigDecimal.ZERO);
        redisDto.setUpTimePulse(Long.valueOf("2"));
        ProduceRealDTO produceRealDTO = new ProduceRealDTO();
        produceRealDTO.setFabChgId("1430352358010216450");
        produceRealDTO.setFabOt(BigDecimal.valueOf(9));
        List<ProduceRealDTO> list = Lists.newArrayList();
        list.add(produceRealDTO);
        redisDto.setAllDTO(list);
        System.out.println(JSON.toJSON(redisDto));
    }

    @Test
    public void timeUnitTest() {
        Integer time = 781293 * 1000;
        long milliseconds = time.longValue();
        long day = TimeUnit.MILLISECONDS.toDays(milliseconds);

        long hours = TimeUnit.MILLISECONDS.toHours(milliseconds)
                - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(milliseconds));

        long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds)
                - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliseconds));

        long seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds)
                - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliseconds));

        long ms = TimeUnit.MILLISECONDS.toMillis(milliseconds)
                - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(milliseconds));

        System.out.println("milliseconds :-" + milliseconds);
        System.out.println(String.format("%d 天 %d 时 %d 分 %d 秒 %d 毫秒", day, hours, minutes, seconds, ms));
    }


    @Test
    public void test888() {
        String testArray = "[\"94\",\"92\"]";
//        JSONArray objects = JSON.parseArray(testArray);
//        System.out.println(objects.get(0));
        String s = testArray.replaceAll("\"", "");
        System.out.println(s);
    }

    @Test
    public void testiii() {
        System.out.println(System.getProperty("user.dir"));
    }

    @Test
    public void test121() {
        BigDecimal bigDecimal = new BigDecimal("3.345");
        System.out.println(bigDecimal);
        System.out.println(bigDecimal.negate());
        BigDecimal bigDecimal1 = new BigDecimal(bigDecimal.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println(bigDecimal1);
        long num = Long.valueOf("3");
        Integer integer = Integer.valueOf((int) num);
        System.out.println(integer);
    }

    @Test
    public void yearTest() {
//        LocalDateTime.parse(new Date(),new DateTimeFormatter().format("yyyy-MM-dd"))
//        System.out.println(LocalDateTime.now().getYear());
        String str = "/gateway/010401000000/FFF219554539/cmd_resp";
        System.out.println(str.substring(0, str.length() - 8));
    }

    @Test
    public void test222ee() {
//        long num = '2';
//        int size = 9;
//        System.out.println(num != size);

        try {
            throw new Exception("11");
        } catch (Exception e) {
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(e, SerializerFeature.WriteDateUseDateFormat));
            System.out.println(jsonObject);
        }
    }

    @Test
    public void test43() {
//        Long e = Long.valueOf(1);
//        System.out.println(e);
        JSONObject jsonObject = JSONObject.parseObject("{" + "\"contentDescription\":\"dsa\",\"extFields\":{},\"name\":\"天友生活门店1号店\",\"rebateType\":3" + "}");
        System.out.println(jsonObject.get("rebateType"));
        if (!"3".equals(jsonObject.get("rebateType"))) {
            System.out.println(2);
        }
    }

    @Test
    public void testTime123() {
        Date date = new Date(1630976400);
        long time = date.getTime() / 1000;
        System.out.println(time);
    }

    @Test
    public void test222() {
        /**
         *  reduce ：规约操作
         */
        List<Integer> list7 = Arrays.asList(9, 4, 4);
        Integer count2 = list7.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println(count2);
    }

    @Test
    public void test() {
//        Stream.of("one", "two", "three", "four")
//                .filter(e -> e.length() > 3)
//                .peek(e -> System.out.println("Filtered value: " + e))
//                .map(String::toUpperCase)
//                .peek(e -> System.out.println("Mapped value: " + e))
//                .collect(Collectors.toList());

//        Stream.of("one", "two", "three", "four")
//                .filter(e ->e.length()>3)
//                .forEach(e -> System.out.println("排序前：" + e));

//                Stream.of("one", "two", "three", "four")
//                .filter(e -> e.length() > 3)
//                .peek(e -> System.out.println("Filtered value: " + e));

//        Stream<String> peek = Stream.of("one", "two", "three", "four")
//                .filter(e -> e.length() < 4)
//                .peek(e -> e.length());
//        System.out.println(peek.max(Comparator.comparing(e->e.length())));

        List<Integer> list = Arrays.asList(1, 2, 3);
        Integer i = 1;
        boolean contains = list.contains(i);
        System.out.println(contains);

        Set<String> list1 = new HashSet<>();
        list1.add("67351109154040005925pay");
        list1.add("67351109105550005908pay");
        list1.add("67351109154040005925refund");
        list1.add("67351109170047005932pay");

        List<String> list2 = Arrays.asList("67351109154040005925pay", "sdadas");

        list2 = list2.stream().filter(e -> !list1.contains(e)).collect(Collectors.toList());
        System.out.println(list2);

        System.out.println(Collections.singletonList(list));
    }

    @Test
    public void filterTest() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        List<Integer> list1 = list.stream().filter(e -> e == 1).collect(Collectors.toList());
        System.out.println(list1);
    }

    @Test
    public void test231() {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
    }

    /**
     * @param str      输入参数
     * @param function 表达式 String为输入类型  Integer为输出类型
     * @return 返回字符串长度
     */
    public static int testFun(String str, Function<String, Integer> function) {
        Integer length = function.apply(str);
        return length;
    }

    @Test
    public void testConsumer() {
        Consumer<Long> consumer = x -> System.out.println(x);
        //consumer.accept(100l);
        consumer.andThen(x -> System.out.println("2:" + x)).accept(200l);
    }

    @Test
    public void test1() {
        /**
         * 取map集合差集
         */
        Map<String, String> map1 = new HashMap<>();
        map1.put("name", "张三");
        map1.put("age", "4");
        map1.put("hah", "xxi");
        Map<String, String> map2 = new HashMap<>();
        map2.put("name", "张三");
        map2.put("age", "4");
        map2.put("gender", "男");
        map2.put("id", "2");

        MapDifference<String, String> mapDifference = Maps.difference(map1, map2);
        Map<String, String> mapLeft = mapDifference.entriesOnlyOnLeft();
        System.out.println("左侧：" + mapLeft);
        Map<String, String> mapRight = mapDifference.entriesOnlyOnRight();
        System.out.println("右侧：" + mapRight);

    }

    @Test
    public void test2() {
        /**
         * 取map集合并集
         */
        Map<String, String> map1 = new HashMap<>();
        map1.put("name", "张三");
        map1.put("age", "4");
        map1.put("wawa", "haha");
        Map<String, String> map2 = new HashMap<>();
        map2.put("name", "张三");
        map2.put("age", "4");
        map2.put("gender", "男");
        map2.put("id", "2");

        map1.putAll(map2);
        System.out.println(map1);
    }

    @Test
    public void test3() {
        /**
         * 取map集合交集
         */
        Map<String, String> map1 = new HashMap<>();
        map1.put("name", "张三");
        map1.put("age", "4");
        Map<String, String> map2 = new HashMap<>();
        map2.put("name", "张三");
        map2.put("age", "4");
        map2.put("gender", "男");
        map2.put("id", "2");

        MapDifference<String, String> map3 = Maps.difference(map1, map2);
        Map<String, String> map4 = map3.entriesInCommon();
        System.out.println("map3:" + map3);
//        Assert.assertEquals(map2,map4);
//        System.out.println(map1);
//        System.out.println(map2);
        System.out.println(map4);
        map4.forEach((k, v) -> {
            System.out.println(k + ":" + v);
        });
    }

    @Test
    public void test4() {
        List<String> list1 = Lists.newArrayList();
        list1.add("name");
        list1.add("age");
        list1.add("haha");

        List<String> list2 = Lists.newArrayList();
        list2.add("age");
        list2.add("gender");
        list2.add("id");
        /**
         * 取差集
         */
        list1.removeAll(list2);
        System.out.println("差集list1:" + list1);
//        System.out.println("差集list2:"+list2);
    }

    @Test
    public void test5() {
        List<String> list1 = Lists.newArrayList();
        list1.add("name");
        list1.add("age");
        list1.add("haha");

        List<String> list2 = Lists.newArrayList();
        list2.add("age");
        list2.add("gender");
        list2.add("id");
        /**
         * 取list集合交集
         */
        list1.retainAll(list2);
        System.out.println("交集list1：" + list1);
//        System.out.println("交集list2："+list2);
    }

    @Test
    public void test6() {
        List<String> list1 = Lists.newArrayList();
        list1.add("name");
        list1.add("age");
        list1.add("haha");

        List<String> list2 = Lists.newArrayList();
        list2.add("age");
        list2.add("gender");
        list2.add("id");
        /**
         * 取并集
         */
        list1.removeAll(list2);
        list1.addAll(list2);
        System.out.println("并集list1：" + list1);
//        System.out.println("并集list2："+list2);
    }

    @Test
    public void test7() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> c = String.class;
        Object o = c.newInstance();

        Constructor<?> constructor = c.getConstructor(String.class);
        Object o1 = constructor.newInstance("dsadas");
        System.out.println(o1);

    }

    @Test
    public void test8() {
        BigDecimal num1 = new BigDecimal(200);
        BigDecimal num2 = new BigDecimal(12);
        BigDecimal decimal = new BigDecimal(BigInteger.valueOf(17));
        System.out.println("测试：" + decimal);
//
//        System.out.println("round_down"+num1.divide(num2,0, BigDecimal.ROUND_DOWN));
//        System.out.println("round_down"+num1.divide(num2,0, BigDecimal.ROUND_DOWN));
//        System.out.println("round_down"+num1.divide(num2,0, BigDecimal.ROUND_DOWN));
//        System.out.println("round_down"+num1.divide(num2,0, BigDecimal.ROUND_DOWN));

    }

    @Test
    public void test9() {
//        List<String> list1 = Lists.newArrayList();
//        list1.add("tradeId");
//        list1.add("orderType");
//        list1.add("haha");
//        Set<String> retainAllResult = list1.stream().collect(Collectors.groupingBy(e -> "2020090722171401308260826" + "pay")).keySet();
//        System.out.println(retainAllResult);


        Map<String, String> payTypeMap = new HashMap<>();
        payTypeMap.put("1", "hhh");
        payTypeMap.put("2", "ccc");
        payTypeMap.put("3", "uyy");
        System.out.println("payTypeMap:" + payTypeMap);
        Set<String> keySet = payTypeMap.keySet();
        System.out.println("ketset:" + keySet);
        Map<String, List<String>> payMethodMap = new HashMap<>();
        for (String key : keySet) {
            String dbPartnerCode = payTypeMap.get(key);
            System.out.println("dbPartnerCode:" + dbPartnerCode);
            List<String> payMethodList = payMethodMap.get(dbPartnerCode);
            if (CollectionUtils.isEmpty(payMethodList)) {
                payMethodList = new ArrayList<>();
                payMethodMap.put(dbPartnerCode, payMethodList);
            }
            payMethodList.add(key);
            System.out.println("paymethodList:" + payMethodList);
        }
        List<String> payMethodList = payMethodMap.get("uyy");
        System.out.println(payMethodList);
    }

    @Test
    public void test10() throws ParseException {
        SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = sft.parse("1603967764000");
        System.out.println(parse);
    }

    @Test
    public void test12213() {
        try {
            // 使用基本编码
            String base64encodedString = Base64.getEncoder().encodeToString("runoob?java8".getBytes("utf-8"));
            System.out.println("Base64 编码字符串 (基本) :" + base64encodedString);
            // 解码
            byte[] base64decodedBytes = Base64.getDecoder().decode(base64encodedString);
            System.out.println("原始字符串: " + new String(base64decodedBytes, "utf-8"));

            //使用Url编码
            base64encodedString = Base64.getUrlEncoder().encodeToString("TutorialsPoint?java8".getBytes("utf-8"));
            System.out.println("Base64 编码字符串 (URL) :" + base64encodedString);

            //解码
            byte[] decode = Base64.getUrlDecoder().decode(base64encodedString);
            System.out.println("Base64.Url原始字符串:" + new String(decode, "utf-8"));

            //使用mime编码
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < 10; ++i) {
                stringBuilder.append(UUID.randomUUID().toString());
            }
            byte[] mimeBytes = stringBuilder.toString().getBytes("utf-8");
            String mimeEncodedString = Base64.getMimeEncoder().encodeToString(mimeBytes);
            System.out.println("Base64 编码字符串 (MIME) :" + mimeEncodedString);
            //解码
            byte[] mimeDecode = Base64.getMimeDecoder().decode(mimeEncodedString);
            System.out.println("Base64.MimeDecode:" + new String(mimeDecode, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            System.out.println("Error :" + e.getMessage());
        }
    }
}
