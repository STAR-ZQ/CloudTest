package com.zq.stream;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
class StreamApplicationTests {
    @Test
    public void test(){
//        Stream.of(Arrays.asList(1,3,5)).forEach(e-> System.out.println(e));
        Stream.of(Arrays.asList(1,3,5)).peek(e-> System.out.println(e));
    }

//    @Test
//    public void test01() {
//        List<Aopi> aopiList = Lists.newArrayList();
//
//        Aopi aopi = new Aopi("1", 1);
//        Aopi aop2 = new Aopi("2", 2);
//        Aopi aop3 = new Aopi("3", 3);
//        Aopi aop4 = new Aopi("4", 4);
//
//        aopiList.addAll(Arrays.asList(aopi, aop2, aop3, aop4));
//
//        //第一种方式
//        aopiList.forEach(item -> item.setName(item.getName() + "_test"));
//        System.out.println(
//                aopiList.stream().min((o1, o2) -> {
//                    if (Objects.equals(o1.getAge(), o2.getAge()))
//                        return 0;
//                    return o1.getAge() > o2.getAge() ? 1 : -1;
//                }).get().toString()
//        );
//
//        System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
//
//        //第二种方式
//        System.out.println(
//                aopiList.stream().peek(item -> item.setName(item.getName() + "_test")).min((o1, o2) -> {
//                    if (Objects.equals(o1.getAge(), o2.getAge()))
//                        return 0;
//                    return o1.getAge() > o2.getAge() ? 1 : -1;
//                }).get().toString()
//        );
//
//    }

}
