package com.gaiaworks.springcloud.config;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.PipelineCluster;

@Configuration
public class PipelineClusterConfig {
		
	@Value("${spring.redis.cluster.nodes}")
	private String clusterNode;
	
	
	@Bean
	public PipelineCluster getPipelineCluster() {
		System.out.println(clusterNode);
		
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		poolConfig.setMaxWaitMillis(2 * 1000L);
		
		
		String[] cNodes = clusterNode.split(",");
		
		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
		
		for (String node : cNodes) {
			String[] hp = node.split(":");
			nodes.add(new HostAndPort(hp[0], Integer.parseInt(hp[1])));
		}
		
		PipelineCluster pipelineCluster = new PipelineCluster(poolConfig,nodes);
		
		return pipelineCluster;
	}
}
