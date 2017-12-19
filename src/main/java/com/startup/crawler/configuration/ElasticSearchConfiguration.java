package com.startup.crawler.configuration;

import org.apache.http.HttpHost;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConfiguration {

	@Bean(name="ElasticSearchRestClient")
	public RestClient getRestClientBuilder() {
		RestClientBuilder builder = RestClient.builder(new HttpHost("127.0.0.1", 9200, "http"));

		/*builder.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
			@Override
			public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
				return httpClientBuilder.setProxy(new HttpHost("127.0.0.1", 9200, "http"));
			}
		});*/
		return builder.build();
	}

}
