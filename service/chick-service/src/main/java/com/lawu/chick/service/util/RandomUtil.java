package com.lawu.chick.service.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyong
 * @date 2018/6/20.
 */
public class RandomUtil {

    public static int getRandomRate(List<Double> lt) {
        double random = Math.random();
        double d0 = 0;
        double d1 = 0;
        int result = 0;
        for (int i = 0; i < lt.size(); i++) {
            if (i == 0) {
                d1 = lt.get(i);
            } else {
                d0 = d0 + lt.get(i);
                d1 = d1 + lt.get(i);
            }
            if (random >= d0 && random <= d1) {
                result = i;
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Double> list = new ArrayList<>();
        list.add(0.5);
        list.add(0.3);
        list.add(0.2);
        System.out.println(getRandomRate(list));
        System.out.println(10*0.01);
    }
}
