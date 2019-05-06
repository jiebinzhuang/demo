package com.zhuangjb.client;

import com.mongodb.BasicDBObject;
import com.zhuangjb.busy.DBC;
import com.zhuangjb.mongodb.MongoDAO;
import com.zhuangjb.util.DateUtils;
import com.zhuangjb.util.HttpUtils;
import com.zhuangjb.util.JsonUtil;
import org.bson.Document;

import java.util.List;
import java.util.Map;

/**
 * Created by zhuangjb on 2019/4/25.
 */
public class InstagramUtils {

    public static String URL_ADDR="http://127.0.0.1:5000/";
    public static void main(String []atg){
        InstagramUtils.getUserPost("jaychou",10);
    }

    public static void updateUserInfo(String username){
        try {
            String json= HttpUtils.httpGet(URL_ADDR+"instagram/getUserInfo?username="+username);
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
            userdoc.put("ts", DateUtils.getTS());

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
            String json= HttpUtils.httpGet(URL_ADDR+"instagram/getPostList?username="+username+"&amount="+mount);
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
                userdoc.put("ts", DateUtils.getTS());
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


    public static void getTagPost(String tagname,int mount){
        try {
            String tablename="posts_"+tagname;
            String json= HttpUtils.httpGet(URL_ADDR+"instagram/getTagList?tagname="+tagname+"&amount="+mount);
            List<Map> resultMapList= JsonUtil.jsonArrayToMapList(json);
            for(Map<String,Object> map:resultMapList){

                BasicDBObject qryfilter2 = new BasicDBObject();
                qryfilter2.put("url", map.get("url"));
                Document userdoc = MongoDAO.getInstance().findOne(
                        tablename, qryfilter2);
                boolean isExist=true;
                if (userdoc == null) {
                    userdoc=new Document();
                    isExist=false;
                }
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                    userdoc.put(entry.getKey(), entry.getValue());
                }
//                userdoc.put("username",username);
                userdoc.put("ts", DateUtils.getTS());
                if (isExist){
                    MongoDAO.getInstance().findOneAndUpdate(
                            tablename, qryfilter2,
                            new BasicDBObject("$set", userdoc));
                }else{
                    MongoDAO.getInstance().insert(tablename,userdoc);
                }
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

//
//    public static void updateVideoUrl(String url){
//        try {
//            BasicDBObject qryfilter2 = new BasicDBObject();
//            qryfilter2.put("ts", new BasicDBObject("$gt", DateUtils.getCurrentDate()));
//            Document userdoc = MongoDAO.getInstance().find(
//                    "posts_info", qryfilter2);
//
//
//            String json= HttpUtils.httpGet(URL_ADDR+"instagram/getPostByUrl?username="+url);
//
//
//            List<Map> resultMapList= JsonUtil.jsonArrayToMapList(json);
//            for(Map<String,Object> map:resultMapList){
//
//                BasicDBObject qryfilter2 = new BasicDBObject();
//                qryfilter2.put("url", map.get("url"));
//                Document userdoc = MongoDAO.getInstance().findOne(
//                        "posts_info", qryfilter2);
//                boolean isExist=true;
//                if (userdoc == null) {
//                    userdoc=new Document();
//                    isExist=false;
//                }
//                for (Map.Entry<String, Object> entry : map.entrySet()) {
//                    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//                    userdoc.put(entry.getKey(), entry.getValue());
//                }
//                userdoc.put("username",username);
//                userdoc.put("ts", DateUtils.getTS());
//                if (isExist){
//                    MongoDAO.getInstance().findOneAndUpdate(
//                            "posts_info", qryfilter2,
//                            new BasicDBObject("$set", userdoc));
//                }else{
//                    MongoDAO.getInstance().insert("posts_info",userdoc);
//                }
//            }
//
//        } catch (Exception e1) {
//            e1.printStackTrace();
//        }
//    }
}
