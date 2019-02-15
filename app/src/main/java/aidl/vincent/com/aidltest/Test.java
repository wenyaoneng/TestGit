package aidl.vincent.com.aidltest;

import android.util.SparseIntArray;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vincent.Wen on 2017/10/20.
 */

public class Test {
    public static void main(String[] args) {
        System.out.println("hello");

        System.out.println(200>>1);
        cal();
    }

    public static void cal(){
        int [] nums = new int[]{1,2,4,5,6,7,8,9,5,4,5,5,5,5,5,5,5,5,5,5};
        SparseIntArray map = new SparseIntArray();
        for (int num : nums) {//这里真实的时候需要做判断空值的
            int count = map.get(num, 0) + 1;
            map.put(num, count);
            if (count == (nums.length >> 1)) {//这里为了相同的数只显示一次，所以才用==
                System.out.println(MessageFormat.format("{0}出现次数超过n>>1",num));//MessageFormat 这个会有点耗性能，当然这里只是用于演示而已
            }
        }
    }


    private static boolean is1() {
        System.out.println("is1");
        return false;
    }

    private static  boolean is2() {
        System.out.println("is2");

        return true;
    }
}
