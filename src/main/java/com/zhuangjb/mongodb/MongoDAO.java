package com.zhuangjb.mongodb;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public class MongoDAO {
	private static final Log log = LogFactory.getLog(MongoDAO.class);
	public static String MONGODB_CONNECTION_STRING = null;
	public static String DATABASE_NAME = null;
	private MongoClient mongoClient = null;
	private static MongoDAO dao = null;

	private MongoDAO() {

	}

	public static MongoDAO getInstance() {
		if (dao == null) {
			dao = new MongoDAO();
		}
		return dao;
	}

	public String getMongodbConnectionString() {
		if (MONGODB_CONNECTION_STRING == null || DATABASE_NAME == null) {
			InputStream is = this.getClass().getResourceAsStream("/database.properties");
			Properties prop = new Properties();
			try {
				prop.load(is);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			MONGODB_CONNECTION_STRING = prop.get("mongodb_connection_string") == null ? null : prop.get(
					"mongodb_connection_string").toString();
			DATABASE_NAME = prop.get("mongodb_name") == null ? null : prop.get("mongodb_name").toString();
		}
		return MONGODB_CONNECTION_STRING;
	}

	public String getDatabaseName() {
		if (MONGODB_CONNECTION_STRING == null || DATABASE_NAME == null) {
			InputStream is = this.getClass().getResourceAsStream("/database.properties");
			Properties prop = new Properties();
			try {
				prop.load(is);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			MONGODB_CONNECTION_STRING = prop.get("mongodb_connection_string") == null ? null : prop.get(
					"mongodb_connection_string").toString();
			DATABASE_NAME = prop.get("mongodb_name") == null ? null : prop.get("mongodb_name").toString();
		}
		return DATABASE_NAME;
	}

	public synchronized MongoClient getClient() {
		// mongodb://[usdername:password@]host1[:port1][,host2[:port2],...[,hostN[:portN]]][/[database][?options]]
		// MongoClientURI connectionString = new MongoClientURI("mongodb://" +
		// username + ":" + password + "@" + seed1
		// + "," + seed2 + "/" + DEFAULT_DB + "?replicaSet=" + ReplSetName);

		if (mongoClient == null) {
			MongoClientURI connectionString = new MongoClientURI(getMongodbConnectionString());
			mongoClient = new MongoClient(connectionString);
		}
		return mongoClient;
	}

	public List<Document> find(String collectionName) {
		List<Document> list = new ArrayList<Document>(0);

		MongoClient mongoClient = getClient();
		try {
			MongoDatabase mongoDatabase = mongoClient.getDatabase(DATABASE_NAME);
			MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
			FindIterable<Document> findIterable = collection.find();

			findIterable.limit(10000);// 最大只获取1w条

			MongoCursor<Document> cursor = findIterable.iterator();
			while (cursor.hasNext()) {
				Document doc = cursor.next();
				list.add(doc);
			}
			cursor.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			// mongoClient.close();
		}

		return list;
	}

	public Document findById(String collectionName, final ObjectId id) {
		if (id == null) {
			return null;
		}

		Document doc = null;
		MongoClient mongoClient = getClient();
		try {
			MongoDatabase mongoDatabase = mongoClient.getDatabase(DATABASE_NAME);
			MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
			FindIterable<Document> findIterable = collection.find(new BasicDBObject("_id", id));

			findIterable.limit(1);// 最大只获取1条

			MongoCursor<Document> cursor = findIterable.iterator();
			while (cursor.hasNext()) {
				doc = cursor.next();
			}
			cursor.close();
		} catch (Exception e) {
			log.error("mongodb查询失败！", e);
			return null;
		} finally {
			// mongoClient.close();
		}
		return doc;
	}

	public Document findById(String collectionName, final String id) {
		if (id == null || id.trim().length() == 0) {
			return null;
		}

		ObjectId oid = null;
		try {
			oid = new ObjectId(id);
		} catch (Exception e) {
			log.error("ObjectId格式错误：" + id, e);
			return null;
		}
		return findById(collectionName, oid);
	}

	public List<Document> find(String collectionName, Bson filter) {
		List<Document> list = new ArrayList<Document>(0);

		MongoClient mongoClient = getClient();
		try {
			MongoDatabase mongoDatabase = mongoClient.getDatabase(DATABASE_NAME);
			MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
			FindIterable<Document> findIterable = null;
			if (filter == null) {
				findIterable = collection.find(filter);
			} else {
				findIterable = collection.find(filter);
			}

			// findIterable.limit(100000);// 最大只获取10w条

			MongoCursor<Document> cursor = findIterable.iterator();
			while (cursor.hasNext()) {
				Document doc = cursor.next();
				list.add(doc);
			}
			cursor.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			// mongoClient.close();
		}

		return list;
	}

	public Document findOne(String collectionName, Bson filter) {
		List<Document> list = find(collectionName, filter);
		if (list != null && list.size() == 1) {
			return list.get(0);
		} else {
			if (list.size() > 1) {
				log.warn("findOne的返回记录超过1条，将返回NULL");
			}
			return null;
		}
	}

	public long count(String collectionName, Bson filter) {
		MongoClient mongoClient = getClient();
		try {
			MongoDatabase mongoDatabase = mongoClient.getDatabase(DATABASE_NAME);
			MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);

			return collection.count(filter);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			// mongoClient.close();
		}
	}

	public List<Document> find(String collectionName, Bson filter, int limit, int skip) {
		return find(collectionName, filter, null, limit, skip);
	}

	public List<Document> find(String collectionName, Bson filter, Bson sort) {
		return find(collectionName, filter, sort, 0, 0);
	}

	public List<Document> find(String collectionName, Bson filter, Bson sort, int limit, int skip) {
		List<Document> list = new ArrayList<Document>(0);

		MongoClient mongoClient = getClient();
		try {
			MongoDatabase mongoDatabase = mongoClient.getDatabase(DATABASE_NAME);
			MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);

			FindIterable<Document> findIterable = collection.find(filter);

			if (limit > 0) {
				findIterable.limit(limit);
			}

			if (skip > 0) {
				findIterable.skip(skip);
			}

			if (sort != null) {
				findIterable.sort(sort);
			}

			MongoCursor<Document> cursor = findIterable.iterator();
			while (cursor.hasNext()) {
				Document doc = cursor.next();
				list.add(doc);
			}
			cursor.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			// mongoClient.close();
		}

		return list;
	}

	public Document findOneAndUpdate(String collectionName, Bson filter, Bson update) {
		MongoClient mongoClient = getClient();
		try {
			MongoDatabase mongoDatabase = mongoClient.getDatabase(DATABASE_NAME);
			MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);

			// BasicDBObject updateSetValue = new BasicDBObject("$set", update);
			return collection.findOneAndUpdate(filter, update);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			// mongoClient.close();
		}
	}

	public Document findOneAndDelete(String collectionName, Bson filter) {
		MongoClient mongoClient = getClient();
		try {
			MongoDatabase mongoDatabase = mongoClient.getDatabase(DATABASE_NAME);
			MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
			return collection.findOneAndDelete(filter);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			// mongoClient.close();
		}
	}

	public DeleteResult deleteOne(String collectionName, Bson filter) {
		MongoClient mongoClient = getClient();
		try {
			MongoDatabase mongoDatabase = mongoClient.getDatabase(DATABASE_NAME);
			MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
			return collection.deleteOne(filter);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			// mongoClient.close();
		}
	}

	public DeleteResult deleteMany(String collectionName, Bson filter) {
		MongoClient mongoClient = getClient();
		try {
			MongoDatabase mongoDatabase = mongoClient.getDatabase(DATABASE_NAME);
			MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
			return collection.deleteMany(filter);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			// mongoClient.close();
		}
	}

	public UpdateResult updateOne(String collectionName, Bson filter, Bson update) {
		MongoClient mongoClient = getClient();
		try {
			MongoDatabase mongoDatabase = mongoClient.getDatabase(DATABASE_NAME);
			MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);

			// BasicDBObject updateSetValue = new BasicDBObject("$set", update);
			return collection.updateOne(filter, update);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			// mongoClient.close();
		}
	}

	public UpdateResult updateMany(String collectionName, Bson filter, Bson update) {
		MongoClient mongoClient = getClient();
		try {
			MongoDatabase mongoDatabase = mongoClient.getDatabase(DATABASE_NAME);
			MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);

			// BasicDBObject updateSetValue = new BasicDBObject("$set", update);
			return collection.updateMany(filter, update);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			// mongoClient.close();
		}
	}

	public void insert(String collectionName, Document document) {
		MongoClient mongoClient = getClient();
		try {
			MongoDatabase mongoDatabase = mongoClient.getDatabase(DATABASE_NAME);
			MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
			collection.insertOne(document);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			// mongoClient.close();
		}
	}

	public void insert(String collectionName, List<Document> list) {
		MongoClient mongoClient = getClient();
		try {
			MongoDatabase mongoDatabase = mongoClient.getDatabase(DATABASE_NAME);
			MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
			collection.insertMany(list);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			// mongoClient.close();
		}
	}
}