package Bitacora;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDB {

    private static MongoDB instance = null;

    public static MongoDB GetInstance() {
        if (instance == null)
            instance = new MongoDB();
        return instance;
    }

    public MongoCollection connectToBitacora(){
        String uri = "mongodb://MyMongoDBUser:gonzalomiele123@cluster0-shard-00-00.toreu.mongodb.net:27017,cluster0-shard-00-01.toreu.mongodb.net:27017,cluster0-shard-00-02.toreu.mongodb.net:27017/test?replicaSet=atlas-13q4b9-shard-0&ssl=true&authSource=admin";
        MongoClientURI clientURI = new MongoClientURI(uri);
        MongoClient mongoClient = new MongoClient(clientURI);

        MongoDatabase mongoDatabase = mongoClient.getDatabase("Diseno");
        MongoCollection collection = mongoDatabase.getCollection("bitacora");

        return collection;

    }

    public void addDocument(String tipoDeOperacion, String entidad, String fechaDeOperacion ) {

        MongoCollection bitacora = this.connectToBitacora();

        Document document = new Document("tipoDeOperacion", tipoDeOperacion);
        document.append("entidad",entidad);
        document.append("fechaDeOperacion", fechaDeOperacion);

        bitacora.insertOne(document);
    }
}
