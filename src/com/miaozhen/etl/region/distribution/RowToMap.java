package com.miaozhen.etl.region.distribution;

/**
 * Created by jasonzhu on 18/2/15.
 */


import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDF;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RowToMap extends UDF implements Serializable {

    private static final char SEP = '^';
    private static final char KVSEP = '=';
    private static final String AVAILABLE = "available";

    // must exist
    private static final String PLT = "plt";
    private static final String CAID = "k";
    private static final String UUID = "uuid";
    private static final String SPID = "p";
    private static final String IP = "ip";
    private static final String TYPE = "tp";
    private static final String TIME = "ti";

    private static final List<String> MANDANTORY_FIELDS =
            Arrays.asList(new String[]{PLT, CAID, UUID, SPID, IP, TYPE, TIME});

    private static final String STABLE = "av";
    private static final String SDK = "kt";
    private static final String OS = "os";
    private static final String MD = "md";
    private static final String URL = "pr";
    private static final String IE = "e";
    private static final String APP= "pn";

    private static final List<String> OPTION_FIELDS = Arrays.asList(new String[]{STABLE, SDK, OS, MD,URL,IE,APP});

    public boolean evaluate(String s) {
//        System.out.println("JUDKKK");
        Map<String, String> map = new HashMap<String, String>();
        map.put("ss","111");
        return true;


//        String[] arr = StringUtils.split(s, SEP);
//        for(String str: arr)
//        {
//            int index = str.indexOf(KVSEP);
//            if(index>0)
//            {
//                String k = str.substring(0,index);
//                String v = str.substring(index+1);
//
//                map.put(k, v);
//            }
//        }
//
//        boolean badLog = true;
//        for (String mandantoryField : MANDANTORY_FIELDS) {
//            if (map.containsKey(mandantoryField)){
//                if (map.get(mandantoryField).isEmpty()) {
//                    badLog = false;
//                    break;
//                }
//            }else{
//                badLog = false;
//                break;
//            }
//        }
//
//        map.put(AVAILABLE, badLog ? "available" : "");
//
//        // OPTION_FIELDS
//        for (String optionField : OPTION_FIELDS) {
//            if (! map.containsKey(optionField)){
//
//                map.put(optionField, "");
//            }
//        }
//
//        return map;
    }

//    public static void main(String[] args) {
//        RowToMap fun = new RowToMap();
//        String test="tp=imp^ti=1419385154^md=?(≧▽≦)女厕所ぉ捡到的?р茫@";
//        // String t="^mh=720.0x1281.0^me=4.0.4^mf=70B419057A21DF9E82052E4C9ABEF720v1.1t1419385158kcom.letv.android.cl    ient^mk=1^m9=2ce61210^mn=%E4%B9%90%E8%A7%86%E8%A7%86%E9%A2%91^os=A^mp=com.letv.android.client^e=A___m^mo=0^kt=mma^m7=ec89f55680b8^mt=14193851    58847^a=wua3Y0eQfLY2^m1=1e0c8f0fd3cd6032^rawIp=123.189.185.74^m3=861290029504557^mw=1^k=2001641^av=00^ip=123.189.185.74^pf=n1^p=101335459^pu=m^pn=>    乐视视频^rt=2^uuid=1e0c8f0fd3cd6032^ag=14";
//        fun.evaluate(test);
//    }
}
