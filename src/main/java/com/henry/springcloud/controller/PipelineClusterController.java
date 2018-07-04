package com.henry.springcloud.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import redis.clients.jedis.PipelineCluster;

@RestController
public class PipelineClusterController {
	
	@Autowired
	private PipelineCluster pipelineCluster;
	
	
	@GetMapping("set")
	public String setValue(String key, String value) {
		pipelineCluster.set(key, value);
		return "success";
	}
	
	@GetMapping("get")
	public String getValue(String key) {
		String value = pipelineCluster.get(key);
		return value;
	}
	
	@PostMapping("mset")
	public String mset(@RequestBody Map<String, String> map) {
		String mset = pipelineCluster.mset(map);
		return mset;
	}
	
	@PostMapping("msetnx")
	public Long msetnx(@RequestBody String... arg0) {
		Long msetnx = pipelineCluster.msetnx(arg0);
		return msetnx;
	}
	
	@PostMapping("mget")
	public Map<String, String> mget(@RequestBody List<String> keys) {
		
		Map<String, String> mget = pipelineCluster.mget(keys);
		
		return mget;
	}
	
	@PostMapping("getByTag")
	public void mget2(@RequestBody String... key) {
		List<String> mget = pipelineCluster.mget(key);
		System.out.println(mget);
		
	}
	
	@DeleteMapping("mdel")
	public long mdel(@RequestBody List<String> keys) {
		
		Long mdel = pipelineCluster.mdel(keys);
		
		return mdel;
	}
	
	@RequestMapping("show")
	public String show() {
		return "Hello World";
	}
}
