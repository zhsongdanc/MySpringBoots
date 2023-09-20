package com.fileservices.length;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2022/9/13 19:47
 */

public class CalLength {
    public static void main(String[] args) {
        long plainLength = 10000L;
        int encryptSegLength = 4094;
        long mod = plainLength % encryptSegLength;
        int calNum=16-encryptSegLength%16;
        long cipherLength = plainLength + (long) (plainLength / encryptSegLength)*calNum + 16-mod%16;
        System.out.println(cipherLength);
    }
}
