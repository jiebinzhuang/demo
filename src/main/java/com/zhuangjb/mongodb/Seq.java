package com.zhuangjb.mongodb;

import org.bson.Document;

import com.zhuangjb.busy.DBC;
import com.mongodb.BasicDBObject;

public class Seq {
	public static long get(String type) {
		BasicDBObject filter = new BasicDBObject("type", type);
		BasicDBObject update = new BasicDBObject("$inc", new BasicDBObject("value", 1L));
		Document doc = MongoDAO.getInstance().findOneAndUpdate(DBC.SEQ, filter, update);
		if (doc != null) {
			return doc.getLong("value");
		} else {
			Document seq = new Document();
			seq.put("type", "appsq");
			seq.put("value", 2L);
			MongoDAO.getInstance().insert(DBC.SEQ, seq);
			return 1l;
		}
	}
}
