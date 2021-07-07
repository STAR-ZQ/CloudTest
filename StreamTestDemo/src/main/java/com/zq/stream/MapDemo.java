package com.zq.stream;

import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapDemo {
    public static void main(String[] args) {
        List<ListDto> list = new ArrayList<>();
        ListDto listDto = new ListDto();
        listDto.setSameId(Long.valueOf(1));
        listDto.setName("zhangsan");
        ListDto listDto1 = new ListDto();
        listDto1.setSameId(Long.valueOf(1));
        listDto1.setName("lisi");
        ListDto listDto2 = new ListDto();
        listDto2.setSameId(Long.valueOf(2));
        listDto2.setName("lisiee");

        listDto.setId(Long.valueOf(11));
        listDto1.setId(Long.valueOf(12));
        listDto2.setId(Long.valueOf(13));

        list.add(listDto);
        list.add(listDto1);
        list.add(listDto2);
//        System.out.println(list);

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId()==11){
                list.remove(i);
            }
            System.out.println(list);
        }

//        List<Long> list1 = list.stream()
//                .collect(Collectors.groupingBy(a -> a.getSameId(), Collectors.counting()))
//                .entrySet().stream().filter(entry -> entry.getValue() > 1).map(entry -> entry.getKey())
//                .collect(Collectors.toList());
//        System.out.println(list1);

//        Map<Long, ListDto> listDtoMap = list.stream().collect(Collectors.toMap(ListDto::getSameId, Function.identity(), (v1, v2) -> v1));
//        System.out.println(listDtoMap);
//
//        Map<Long, List<ListDto>> longListMap = list.stream().collect(Collectors.groupingBy(e->(e.getSameId()+e.getId())));
//        System.out.println(longListMap);
//        for (int i=0;i<list.size();i++){
//            if (longListMap.containsKey(list.get(i).getSameId())){
//                System.out.println("dsada");
//            }
//        }
    }

    @Test
    public void removeTest(){
        List<Integer> list = Arrays.asList(1, 2, 2, 3, 4, 5);
        Iterator<Integer> it = list.iterator();
        while(it.hasNext()){
            Integer x = it.next();
            if(x == 2){
                it.remove();
            }
        }
//        for (int i = list.size() - 1; i >= 0; i--) {
//            int finalI = i;
//            list.removeIf(v->list.get(finalI).equals(2));
////            if (list.get(i).equals(2)){
////                list.remove(i);
////
////            }
//        }
        System.out.println(list);
    }
}
