package champDB;

import com.mongodb.*;
import com.mongodb.client.*;
import org.bson.*;
import java.util.*;

public class Common {

	private MongoClient conn;
	
	public Common() {
		conn = null;
	}
	
	public MongoCollection<Document> getCollection() {
		String server = "35.227.165.117";
		int port = 27017;
		String id = "champ";
		String pwd = "Passw0rd!";
		String dbName = "Champions";
		String collectionName = "champinfo";
		
		//credential
		MongoCredential auth = MongoCredential.createCredential(id, dbName, pwd.toCharArray());
		
		//open connection
		conn = MongoClients.create(MongoClientSettings.builder()
				.applyToClusterSettings(builder ->
				builder.hosts(Arrays.asList
						(new ServerAddress(server, port))))
				.credential(auth)
				.build());
		System.out.println("Connected ... ");
		
		MongoDatabase db = conn.getDatabase(dbName);
		MongoCollection<Document> collection = 
				db.getCollection(collectionName);
		
		return collection;
		
	}
	
	public void closeConnection() {
		conn.close();
	}
}
