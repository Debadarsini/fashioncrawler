package com.startup.crawler.client;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.ResponseListener;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ElasticSearchClient {
	
	
	@Autowired
	private RestClient restClient;
	
	
	public void insert(Map<String, Object> map) throws IOException{
		Map<String, String> params = Collections.emptyMap();
		
		
		ObjectMapper mapper = new ObjectMapper();
		HttpEntity entity = new NStringEntity(mapper.writeValueAsString(map), ContentType.APPLICATION_JSON);
		ResponseListener responseListener = new ResponseListener() {
		    @Override
		    public void onSuccess(Response response) {
		        System.out.println(response.toString());
		    }

		    @Override
		    public void onFailure(Exception exception) {
		        exception.printStackTrace();
		    }
		};
		restClient.performRequestAsync("POST", "/crawler/item", params, entity,responseListener); 
		/*Map<String, String> params = Collections.emptyMap();
		String jsonString = "{" +
		            "\"user\":\"kimchy\"," +
		            "\"postDate\":\"2013-01-30\"," +
		            "\"message\":\"trying out Elasticsearch\"" +
		        "}";
		HttpEntity entity = new NStringEntity(jsonString, ContentType.APPLICATION_JSON);
		Response response = restClient.performRequest("PUT", "/posts/doc/1", params, entity); */
		
	}

}
