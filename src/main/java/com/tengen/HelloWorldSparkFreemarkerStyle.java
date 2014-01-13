package com.tengen;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class HelloWorldSparkFreemarkerStyle  {
	public static void main(String[] args) {
		final Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(HelloWorldSparkFreemarkerStyle.class, "/");
		
		Spark.get(new Route("/") {
			@Override
			public Object handle(final Request request, final Response response) {
				
				StringWriter writer = new StringWriter();
				try {
					Template helloTemplate = configuration.getTemplate("hello.ftl");
					
					Map<String,Object> helloMap = new HashMap<String, Object>();
					helloMap.put("name", "Freemarker");
					helloTemplate.process(helloMap, writer);
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
