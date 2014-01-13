package com.tengen;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class HelloWorldFreemarkerStyle {
	public static void main(String[] args) throws TemplateException {
		Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(HelloWorldFreemarkerStyle.class, "/");
		
		try {
			Template helloTemplate = configuration.getTemplate("hello.ftl");
			StringWriter writer = new StringWriter();
			Map<String,Object> helloMap = new HashMap<String, Object>();
			helloMap.put("name", "Freemarker");
			helloTemplate.process(helloMap, writer);
			System.out.println(writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
