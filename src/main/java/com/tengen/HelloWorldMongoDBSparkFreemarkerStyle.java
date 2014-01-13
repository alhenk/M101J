package com.tengen;

import java.io.IOException;
import java.io.StringWriter;
import java.net.UnknownHostException;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class HelloWorldMongoDBSparkFreemarkerStyle {
	public static void main(String[] args) throws UnknownHostException {
		final Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(HelloWorldSparkFreemarkerStyle.class, "/");
		
		MongoClient client = new MongoClient(new ServerAddress("localhost",27017));
		
		DB database = client.getDB("course");
		final DBCollection collection = database.getCollection("hello");
		Spark.setPort(8080);
		Spark.get(new Route("/") {
			@Override
			public Object handle(final Request request, final Response response) {
				
				StringWriter writer = new StringWriter();
				try {
					Template helloTemplate = configuration.getTemplate("hello.ftl");
					
					DBObject document = collection.findOne();
					helloTemplate.process(document, writer);
				} catch (IOException e) {
					halt(500);
					e.printStackTrace();
				} catch (TemplateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return writer;
			}
		});
	}
}
