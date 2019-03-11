package com.example.daily.gt;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.http.IGtPush;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @auther: Zhiyuan Cui
 * @project: daily
 * @date: 2019/2/23 13:50
 */
public class PushMsgToApp {//采用"Java SDK 快速入门"， "第二步 获取访问凭证 "中获得的应用配置，用户可以自行替换
    //您应用的mastersecret
    private static final String MASTERSECRET = "iUhI1m3ptS9SNIWw9j18W7";
    //您应用的appkey
    private static final String APPKEY = "dIOX6LHJb090A4DZNLfzW6";
    //要查询的AppId
    private static final String APPID = "BiovgjkrzaADTfg13ORcK9";

    static String host ="http://sdk.open.api.igexin.com/apiex.htm";



    public static void main(String[] args) {
//        String TASKID = "OSL-0226_MSCXercNjM7LHOKaG5jhq1";
        String TASKID = "OSS-0228_5f32179b298cf29496a74fa2c3ec0fd2";



//        TASKID = "GT_0225_9e8e8e32f93ab3ff5bcfec7a4fe48dc2";
        IGtPush push = new IGtPush(host,APPKEY, MASTERSECRET);
        Map<String, Object> res = (Map<String, Object>) push.getPushResult(TASKID).getResponse();
        for(Map.Entry<String,Object> entry: res.entrySet()){
            System.out.println(entry.getKey()+" "+entry.getValue());
        }
        IPushResult result = push.getPushResultByTaskidList(new ArrayList<String>(){{
            add("OSL-0307_z36zMn93f58njNJuVNyaL3");
        }});

        List<Map<String, Object>> resList = (List<Map<String, Object>>) result.getResponse().get("resultList");

    }
}
