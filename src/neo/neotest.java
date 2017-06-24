package neo;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.RelationshipType;

public class neotest {
	public enum Tutorials implements Label {
		entity, view, SQL, NEO4J;
	}

	public enum Relationships implements RelationshipType {
		link;
	}

	public static void main(String[] args) {

		GraphDatabaseFactory dbFactory = new GraphDatabaseFactory();
		GraphDatabaseService db = dbFactory
				.newEmbeddedDatabase("E:/neo4j/test.graphdb");
		try (Transaction tx = db.beginTx()) {

			Node entity1Node = db.createNode(Tutorials.entity);
			entity1Node.setProperty("instance", "aaa");
			entity1Node.setProperty("url", "http://www.baike1.com");
			Node entity3Node = db.createNode(Tutorials.entity);
			entity3Node.setProperty("instance", "bbb");
			entity3Node.setProperty("url", "http://www.baike3.com");

			Node entity2Node = db.createNode(Tutorials.view);
			entity2Node.setProperty("url", "http://www.baike2.com");
			Node entity4Node = db.createNode(Tutorials.view);
			entity4Node.setProperty("url", "http://www.baike4.com");

			Relationship relationship = entity1Node.createRelationshipTo(
					entity2Node, Relationships.link);
			Relationship relationship1 = entity3Node.createRelationshipTo(
					entity4Node, Relationships.link);

			tx.success();
		}
		System.out.println("Done successfully");
	}
}
