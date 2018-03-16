package com.nice.utils;


/**
 * Created by DELL on 2017/8/29.
 */
public class ShortUtil {
//    public static void main(String[] args) {
//        // 长连接
//        String longUrl = "http://data.13322.com/basket/team/27_0_1.html";
//        // 转换成的短链接后6位码
//        String shortCode = shortUrl(longUrl,"vpclub");
//
//        System.out.println(shortCode);// 任意一个都可以作为短链接码
//    }

    public static String shortUrl(String url,String key) {
        // 要使用生成 URL 的字符
        String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h",
                "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
                "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z" };

        // 对传入网址进行 MD5 加密
        String hex = Encodes.encodeToMD5(key + url);
        // 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算
        String sTempSubString = hex.substring(hex.length()-8);
        // 这里需要使用 long 型来转换，因为 Integer .parseInt() 只能处理 31 位 , 首位为符号位 , 如果不用
        // long ，则会越界
        long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
        String outChars = "";
        for (int j = 0; j < 6; j++) {
            // 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
            long index = 0x0000003D & lHexLong;
            // 把取得的字符相加
            outChars += chars[(int) index];
            // 每次循环按位右移 5 位
            lHexLong = lHexLong >> 5;
        }
        return outChars;
    }
}
