package com.mxp.car.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * EMAIL menxipeng@gmail.com
 * AUTHOR:menxipeng
 * DATE: 2019/12/1
 * TIME: 14:30
 */
@Log4j2
@Component
public class Utils {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CarUtil {
        public static LocalDateTime strToTime(String value) {
            var dft = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return LocalDateTime.parse(value, dft);
        }

        public static LocalDate strToDate(String value) {
            var dft = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(value, dft);
        }

        public static BigDecimal strToDecimal(String value) {
            return new BigDecimal(value);
        }

        public static Map<String, Object> mapTo(Map<String, String> map) {
            return map.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, x -> {
                if ((x.getKey().toUpperCase()).contains("TIME")) {
                    return strToTime(x.getValue());
                } else if ((x.getKey().toUpperCase()).contains("DATE")) {
                    if (!x.getValue().equals("")) {
                        return strToDate(x.getValue());
                    }
                }
                return x.getValue();
            }));
        }

        public static Long getId() {
            var dft = DateTimeFormatter.ofPattern("yyMMddHHmmss");
            var timestamp = dft.format(LocalDateTime.now());
            var id = new StringBuilder(timestamp);
            new Random().ints(4, 0, 10).forEach(id::append);
            return Long.parseLong(id.toString());
        }

        public static String getMachine() {
            String result = null;
            try {
                Process process = Runtime.getRuntime().exec(new String[]{"wmic", "cpu", "get", "ProcessorId"});
                process.getOutputStream().close();
                Scanner sc = new Scanner(process.getInputStream());
                sc.next();
                result = sc.next();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        public static  List<String> listSort(List<String> list){
            list.sort(Comparator.comparing(x -> x.substring(x.lastIndexOf("-")+1)));
            return list;
        }

        public static String getUID() {
            return UUID.randomUUID().toString().replaceAll("-","");
        }

        public static void main(String[] args) {
            var list = new ArrayList<String>();
            var ss = "/usr/local/2f4683608c7844ac9baa995d19528ff4-1.jsp";
            list.add("/usr/local/"+getUID()+"-3.jsp");
            list.add("/usr/local/"+getUID()+"-1.jsp");
            list.add("/usr/local/"+getUID()+"-4.jsp");
            list.add("/usr/local/"+getUID()+"-2.png");
            list.sort(Comparator.comparing(x -> x.substring(x.lastIndexOf("-")+1)));
            System.out.println(list);
        }

    }
}
