package com.zhuangjb.client;

import com.mongodb.BasicDBObject;
import com.zhuangjb.busy.DBC;
import com.zhuangjb.mongodb.MongoDAO;
import com.zhuangjb.util.HttpUtils;
import com.zhuangjb.util.JsonUtil;
import org.bson.Document;

import java.util.List;
import java.util.Map;

/**
 * Created by zhuangjb on 2019/4/25.
 */
public class InstagramUtils {

    public static void main(String []atg){

        InstagramUtils.getUserPost("yao",10);
    }

    public static void updateUserInfo(String username){
        try {
            String json= HttpUtils.httpGet("http://127.0.0.1:5000/instagram/getUserInfo?username="+username);
            Map<String, Object> resultMap= JsonUtil.jsonToMap(json);

            BasicDBObject qryfilter2 = new BasicDBObject();
            qryfilter2.put(DBC.username, username);
            Document userdoc = MongoDAO.getInstance().findOne(
                    "user_info", qryfilter2);
            boolean isExist=true;
            if (userdoc == null) {
                  userdoc=new Document();
                isExist=false;
            }
            for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
                System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                userdoc.put(entry.getKey(), entry.getValue());
            }

            if (isExist){
                MongoDAO.getInstance().findOneAndUpdate(
                        "user_info", qryfilter2,
                        new BasicDBObject("$set", userdoc));
            }else{
                MongoDAO.getInstance().insert("user_info",userdoc);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }


    public static void getUserPost(String username,int mount){
        try {
            String json= HttpUtils.httpGet("http://127.0.0.1:5000/instagram/getPostList?username="+username+"&amount="+mount);
            List<Map> resultMapList= JsonUtil.jsonArrayToMapList(json);
            for(Map<String,Object> map:resultMapList){

                BasicDBObject qryfilter2 = new BasicDBObject();
                qryfilter2.put("url", map.get("url"));
                Document userdoc = MongoDAO.getInstance().findOne(
                        "posts_info", qryfilter2);
                boolean isExist=true;
                if (userdoc == null) {
                    userdoc=new Document();
                    isExist=false;
                }
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                    userdoc.put(entry.getKey(), entry.getValue());
                }
                userdoc.put("username",username);

                if (isExist){
                    MongoDAO.getInstance().findOneAndUpdate(
                            "posts_info", qryfilter2,
                            new BasicDBObject("$set", userdoc));
                }else{
                    MongoDAO.getInstance().insert("posts_info",userdoc);
                }
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
