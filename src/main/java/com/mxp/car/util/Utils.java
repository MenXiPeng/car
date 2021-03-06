package com.mxp.car.util;

import com.mxp.car.config.Config;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

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

        public static Map<String, Object> mapTo(Map<String, Object> map) {
            return map.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, x -> {
                if ((x.getKey().toUpperCase()).contains("TIME")) {
                    return strToTime(String.valueOf(x.getValue()));
                } else if ((x.getKey().toUpperCase()).contains("DATE")) {
                    if (!x.getValue().equals("")) {
                        return strToDate(String.valueOf(x.getValue()));
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

        public static String photoUrl(String url) {
            String path = url.split("/photo")[0];
            return Config.UPLOAD_PATH + path;
        }

        public static <T> List<T> objectcastList(Object obj, Class<T> clazz) {
            List<T> result = new LinkedList<>();
            if (obj instanceof List<?>) {
                for (Object o : (List<?>) obj) {
                    result.add(clazz.cast(o));
                }
                return result;
            }
            return null;
        }

        public static String getUID() {
            return UUID.randomUUID().toString().replaceAll("-", "");
        }

        public static void main(String[] args) {
            System.out.println(Utils.CarUtil.photoUrl("http://127.0.0.1:3344/photo/2020-01-05/屏幕快照"));
        }

    }
}
