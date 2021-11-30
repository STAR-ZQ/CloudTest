package com.zq.stream;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListDemo {
    private static Logger logger = LoggerFactory.getLogger(ListDemo.class);

    @Autowired
    @Test
    public void loggerTest() {
        logger.info("测试logger日志：logger=========");
    }

    @Test
    public void testTimes() throws ParseException {
        Calendar c = Calendar.getInstance();
        System.out.println(c.getTimeInMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = sdf.parse("2020-02-02");
        String date = sdf.format(parse);
        try {
            Date dateD = sdf.parse(date);
            System.out.println("dateD:" + dateD.getTime());
            if (new Date().getTime() >= dateD.getTime()) {

            }
        } catch (ParseException e1) {
        }
        System.out.println(sdf.parse(sdf.format(new Date())).getTime());
    }

    @Test
    public void chicaTest() {
        for (int i = 0; i < 101; i++) {
            System.out.println(String.format("%02d", i));
        }
    }

    @Test
    public void testCompareTo() {
        List<ListDto> compareToList = Lists.newArrayList();
        ListDto listDto = new ListDto();
        listDto.setName("hzang");
        ListDto listDto1 = new ListDto();
        listDto1.setName("");
        ListDto listDto2 = new ListDto();
        listDto2.setName("hah");
        compareToList.add(listDto);
        compareToList.add(listDto1);
        compareToList.add(listDto2);

        for (ListDto s : compareToList) {
            System.out.println(s.getName());
        }
        Collections.sort(compareToList, new Comparator<ListDto>() {
            @Override
            public int compare(ListDto o1, ListDto o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        System.out.println("==================================");
        for (ListDto dto : compareToList) {
            System.out.println(dto.getName());
        }
    }

    @Test
    public void testCalender() {
        Integer period = 3;
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(Long.valueOf("1614588179000"));
        String[] s = format.split(" ");
        SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd");
        String newDate = sft.format(new Date()) + " " + s[1];
        try {
            System.out.println(s[1]);
            System.out.println(newDate);
            if (c.getTimeInMillis() > sdf.parse(newDate).getTime()) {
                period++;
            }
        } catch (ParseException e) {
        }
        System.out.println(period);
        Date time = null;
        if (!"null".equals(time)) {
            System.out.println("jkj");
        }
    }

    @Test
    public void timeTest() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM d HH:mm:ss 'CST' yyyy", Locale.ENGLISH);
        Date date = dateFormat.parse("Sun Mar 07 00:00:00 CST 2021");
        System.out.println(JSON.toJSONString(date));
    }

    @Test
    public void test33() throws ParseException {
        String dbtime1 = "2021-02-23";

        String dbtime2 = "2021-02-28";
//算两个日期间隔多少天
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = format.parse(dbtime1);
        Date date2 = format.parse(dbtime2);
        int a = (int) ((date1.getTime() - date2.getTime()) / (1000 * 3600 * 24));
        System.out.println(a);
    }

    @Test
    public void time() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //创建日期转换对象：年 月 日
        String date = "2018-11-11"; //假设 设定日期是  2018-11-11
        Date today = new Date();    //今天 实际日期是  2018-11-12    Debug：Wed Nov 12 12:08:12 CST 2018
        try {
            Date dateD = sdf.parse(date); //将字符串转换为 date 类型  Debug：Sun Nov 11 00:00:00 CST 2018
            boolean flag = dateD.getTime() >= today.getTime();
            System.err.println("flag = " + flag);  // flag = false
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<ListDto> list = new ArrayList<>();
        ListDto demo1 = new ListDto();
        demo1.setAge(1);
        demo1.setGender("男");
        demo1.setId(Long.valueOf(1));
        demo1.setName("Demo1");
        ListDto demo2 = new ListDto();
        demo2.setAge(2);
        demo2.setGender("女");
        demo2.setId(Long.valueOf(2));
        demo2.setName("Demo2");
        ListDto demo3 = new ListDto();
        demo3.setAge(3);
        demo3.setGender("男");
        demo3.setId(Long.valueOf(3));
        demo3.setName("Demo3");

        ListDto demo4 = new ListDto();
        demo3.setAge(4);
        demo3.setGender("男");
        demo3.setId(Long.valueOf(4));
        demo3.setName("Demo4");

        demo1.setSameId(Long.valueOf(1));
        demo2.setSameId(Long.valueOf(2));
        demo3.setSameId(Long.valueOf(3));

        list.add(demo1);
        list.add(demo2);
        list.add(demo3);
        PageInfo<ListDto> pageInfo = new PageInfo<ListDto>(list);
        System.out.println(pageInfo);
        list.remove(1);
        pageInfo.setList(list);
        PageInfo<ListDto> info = new PageInfo<>(list);
        System.out.println(info);
//        System.out.println(pageInfo);
//list.remove(1);
//        PageInfo<ListDto> listDtoPageInfo = PageHelperTool.initPageInfoObj(2, list.size(), 2, pageInfo);
//        listDtoPageInfo.setSize(listDtoPageInfo.getEndRow());
//        System.out.println(listDtoPageInfo);
//        Map<Long, ListDto> listDtoMap = list.stream().collect(Collectors.toMap(ListDto::getId, Function.identity(), (i1, i2) -> i1));
//
//        System.out.println("listDtoMap:"+JSON.toJSONString(listDtoMap));
//
//        Map<Long, List<ListDto>> listMap = list.stream().collect(Collectors.groupingBy(ListDto::getSameId));
//        System.out.println("listMap:"+JSON.toJSONString(listMap));
//        System.out.println("listMap1:"+ JSON.toJSONString(listMap.get(Long.valueOf(1))));
    }

    @Test
    public void testkkk() {
        List<ListDto> list = new ArrayList<>();
        ListDto demo1 = new ListDto();
        demo1.setAge(1);
        demo1.setGender("男");
        demo1.setId(Long.valueOf(1));
        demo1.setName("Demo1");
        ListDto demo2 = new ListDto();
        demo2.setAge(2);
        demo2.setGender("女");
        demo2.setId(Long.valueOf(1));
        demo2.setName("Demo2");
        ListDto demo3 = new ListDto();
        demo3.setAge(3);
        demo3.setGender("男");
        demo3.setId(Long.valueOf(3));
        demo3.setName("Demo3");

        list.add(demo1);
        list.add(demo2);
        list.add(demo3);

        Map<Long, List<ListDto>> listMap = list.stream().collect(Collectors.groupingBy(ListDto::getId));
        for (int i = 0; i < list.size(); i++) {
            if (listMap.get(list.get(i).getId()).size() != 2) {
                list.remove(i);
                i--;
            }
        }
        System.out.println(list);
    }

    @Test
    public void testkkklh() {
        String kk = "{\"memberId\":d34567890-3456789}";
        Object o = JSON.toJSON(kk);
        jkjk(o.toString());
        System.out.println(o);
    }

    public void jkjk(String jj) {
        System.out.println(jj);
    }


    @Test
    public void testTimess() {
        System.out.println(Calendar.getInstance().getTimeInMillis());
        Date date = new Date();

        System.out.println(new SimpleDateFormat("yyyyMMddHHmmss").format(date));
    }

    @Test
    public void testDate() {
        //只能设置当前月的
        Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
        //当前月份
        Integer currentMonth = aCalendar.get(Calendar.MONTH) + 1;
        System.out.println(currentMonth);
    }

    @Test
    public void test999() {
        System.out.println(9 / 4 + 1);
    }

    @Test
    public void jjkjk() throws ParseException {
        String date = "2021-04";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        System.out.println(simpleDateFormat.format(simpleDateFormat.parse(date)));
    }

}
