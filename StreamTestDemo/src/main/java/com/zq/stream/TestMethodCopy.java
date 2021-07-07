package com.zq.stream;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

public class TestMethodCopy {
    public static void main(String[] args) {
//        List<String> list = Lists.newArrayList();
//        list.add("test1");
//        list.add("test2");
//        list.add("test3");
//        System.out.println(new String[0].length);
        ListDto listDto = new ListDto();
        Integer num = 2;
        listDto.setName("里斯");
        testMethod(listDto, num);
        System.out.println(listDto.getName() + num);
    }

    public static void testMethod(ListDto dto, int num) {
        dto.setName("张三");
        num = num + 1;
    }

    @Test
    public void test() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 144, 16, 17, 18, 19, 20, 76};
        int index = 4;
        for (int i = 0; i < 20; i++) {
            int nextIndex = (index + 1) % arr.length;
            index = nextIndex;
            System.out.println(arr[index] + ",index=" + index);
        }
    }
}
