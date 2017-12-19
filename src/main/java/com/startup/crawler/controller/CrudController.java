package com.startup.crawler.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.startup.crawler.client.ElasticSearchClient;

@Controller
@RequestMapping("/crud")
public class CrudController {

	@Autowired
	ElasticSearchClient elasticSearchClient;
	
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST)
	void home(@RequestBody Map<String,Object> map) {
		try {
			elasticSearchClient.insert(map);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
}
