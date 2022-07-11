package com.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
public class EmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
	}
	
//	 @Bean
//	 public MongoDbFactory mongoDbFactory() throws Exception {
//	        return new SimpleMongoDbFactory(new Mongo(), "employee");
//	    }
//
//	    @Bean
//	    public MongoTemplate mongoTemplate() throws Exception {
//
//	        //remove _class
//	        MappingMongoConverter converter = new MappingMongoConverter(mongoDbFactory(), new MongoMappingContext());
//	        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
//
//	        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory(), converter);
//
//	        return mongoTemplate;
//
//	    }
	
}
