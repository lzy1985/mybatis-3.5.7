import org.assertj.core.util.Lists;
import org.testcontainers.shaded.com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author lzy
 * @version 1.0.0
 * @date 2021/12/21 下午1:56
 * @Description
 */
public class Java8Stream {

    public static void main(String[] args) {
        generatorStream();
        demo();
        filter();
        map();
        flatMap();
        limit();
        skip();
    }

    /**
     * 如果是数组，可以用Arrays.steam() 或者Steam.of()方法转换为Stream，两种方法一样
     */
    public static void generatorStream() {
        Integer[] array1 = new Integer[]{3, 4, 8, 16, 19, 27, 23, 99, 76, 232, 33, 96};
        long count = Arrays.stream(array1).filter(c -> c > 4).count();
        System.out.println(count);

        List<Integer> list = Stream.of(array1).filter(a -> a > 5).collect(Collectors.toList());
        System.out.println(Arrays.toString(list.toArray()));


        long count3 = Stream.of(2,4,7,6,7).filter(c -> c > 4).count();
        System.out.println(count3);

        Stream stream =  Stream.generate(()->"c").limit(2);
        System.out.println(Arrays.toString(stream.toArray(String[] :: new)));

    }

    public static void demo(){
        List<Integer> numbers = new ArrayList<>();
        numbers.add(3);
        numbers.add(4);
        numbers.add(8);
        numbers.add(16);
        numbers.stream().forEach(number-> System.out.println(number));


        Spliterator spliterator = numbers.spliterator();
        StreamSupport.stream(spliterator,false).forEach(number-> System.out.println(number));
    }

    /**
     * filter方法就是过滤转换，生成一个新的流，其中包含符合某种特定条件的所有元素
     */
    public static void filter(){

        List<Integer> numbers = new ArrayList<>();
        numbers.add(3);
        numbers.add(4);
        numbers.add(8);
        numbers.add(16);
        numbers.add(19);
        numbers.add(27);
        numbers.add(23);

        List<Integer> list = numbers.stream().filter(c-> c> 15).collect(Collectors.toList());
        System.out.println(list);


        List<String> list1 = numbers.stream().map(key->String.valueOf(key)).collect(Collectors.toList());
        System.out.println(list1);

    }


    /**
     * map方法指对一个流中的值进行某种形式的转换，需要传递一个转换的函数作为参数
     */
    public static void map(){

        List<Integer> integerList = Lists.newArrayList();
        integerList.add(15);
        integerList.add(32);
        integerList.add(5);
        integerList.add(232);
        integerList.add(56);

        List<String> list1 = integerList.stream().map(key->String.valueOf(key)).collect(Collectors.toList());
        System.out.println(list1);

    }


    /**
     * flatMap方法，返回包含多个流的元素
     */
    public static void flatMap(){
        List<Integer> oneList = Lists.newArrayList(),
                twoList = Lists.newArrayList();
        oneList.add(34);
        oneList.add(23);
        oneList.add(87);

        twoList.add(29);
        twoList.add(48);
        twoList.add(92);
        Map<String,List<Integer>> testMap = Maps.newHashMap();
        testMap.put("1",oneList);
        testMap.put("2",twoList);

        List<Integer> list = testMap.values().stream().flatMap(number->number.stream()).collect(Collectors.toList());
        System.out.println(list);

    }


    /**
     * limit(n)方法返回一个包含n个元素的新的流（若总长度小于n，则返回原始流）
     */
    public static void limit(){
        List<Integer> myList = Lists.newArrayList();
        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.add(4);
        myList.add(5);
        myList.add(6);
        List<Integer> afterList = myList.stream().limit(4).collect(Collectors.toList());
        System.out.println(afterList);

    }
    /**
     * skip(n)方法与limit正好相反，它会丢弃前n个元素，返回剩下的额元素的一个新流
     */
    public static void skip(){
        List<Integer> myList = Lists.newArrayList();
        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.add(4);
        myList.add(5);
        myList.add(6);
        List<Integer> afterList = myList.stream().skip(4).collect(Collectors.toList());
        System.out.println(afterList);
    }

    /**
     * distinct方法会根据原始流中的元素返回具有相同顺序，去除了重复元素的流
     */
    public static void distinct(){

    }


    /**
     * sorted方法是需要遍历整个流的，并在产生任何元素之前对
     */
    public static void sorted(){

    }
}


