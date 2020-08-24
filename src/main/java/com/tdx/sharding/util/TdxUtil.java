package com.tdx.sharding.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tdx.sharding.domain.ResultModel;

import java.security.MessageDigest;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class TdxUtil {

    private static final String hexDigIts[] = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};

    public static ResultModel getResultModel(int count, String[] items, List<Map<String, Object>> list) {
        JSONArray data = new JSONArray();

        for (Map<String, Object> map : list) {
            JSONObject json = new JSONObject(true);
            if (map == null) {
                continue;
            }
            for (String item : items) {
                json.put(item, map.get(item));
            }
            data.add(json);
        }

        return ResultModel.getSuccessRM(count, null, data);
    }

    public static ResultModel getResultModel(String[] items, Map<String, Object> map) {
        JSONArray data = new JSONArray();

        int count = map == null ? 0 : 1;

        JSONObject json = new JSONObject(true);
        for (String item : items) {
            json.put(item, map.get(item));
        }
        data.add(json);

        return ResultModel.getSuccessRM(count, null, data);
    }

    public static ResultModel getResultModel(String item, int count) {
        JSONArray data = new JSONArray();

        JSONObject json = new JSONObject(true);
        json.put(item, count);
        data.add(json);

        return ResultModel.getSuccessRM(1, null, data);
    }

    public static ResultModel getResultModel(String item, String value) {
        JSONArray data = new JSONArray();
        JSONObject json = new JSONObject(true);
        json.put(item, value);
        data.add(json);

        return ResultModel.getSuccessRM(1, null, data);
    }

    public static String MD5Encode(String origin, String charsetname){
        String resultString = null;
        try{
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if(null == charsetname || "".equals(charsetname)){
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            }else{
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultString;
    }


    public static String byteArrayToHexString(byte b[]){
        StringBuffer resultSb = new StringBuffer();
        for(int i = 0; i < b.length; i++){
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    public static String byteToHexString(byte b){
        int n = b;
        if(n < 0){
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigIts[d1] + hexDigIts[d2];
    }

    public static String getUUID() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }

    public static void main(String[] args) {
//        int a = Math.abs("5d933c58316248588831bf739efc455c".hashCode());
//
//        int b = Math.abs("87472cfb53cb46578252c1f0295a3b3a".hashCode());

        int a = 1;
        int b = 1;

//        System.out.println(a);
//        System.out.println(b);

        System.out.println(a^b);
        System.out.println(a&b);
        System.out.println(a|b);
//
//        System.out.println(Integer.MAX_VALUE);

//        String dbNameStr = "ds-0-1073741823";
//        String dbRange = dbNameStr.substring(dbNameStr.indexOf("-") + 1);
//        String[] dbRangeArr = dbRange.split("-");
//        Integer begin = Integer.valueOf(dbRangeArr[0]);
//        Integer end = Integer.valueOf(dbRangeArr[1]);
//        System.out.println(begin);
//        System.out.println(end);
//
//        if (b>= begin && b <= end) {
//            System.out.println("命中");
//        }
    }
}
