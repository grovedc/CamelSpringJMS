/*
 * Copyright 2005-2016 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.redhat.gss.bridge.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author grovedc
 *
 */
@Configuration
@PropertySource("file:///${CONFIG_LOCATION}/mq_consumer.properties")
@ConfigurationProperties(prefix = "mq.src")
public class ConsumerMQ {
	// connection factory properties
	private String connectionNameList;
	private String queueManager;
	private String channel;
	private Integer transportType;
	private Integer clientReconnectOptions;
	
	// camel/spring JMS properties
	private Boolean transacted;
	private String cacheLevelName;
	private Integer concurrentConsumers;
	private Integer receiveTimeout;
	
	// user credentials
	private String username;
	private String password;
	
	// connection pooling
	private Integer maxConnections;
	private Integer idleTimeout;
	
	// queue name
	private String queueName;

	/**
	 * @return the connectionNameList
	 */
	public String getConnectionNameList() {
		return connectionNameList;
	}
	
	/**
	 * @param connectionNameList the connectionNameList to set
	 */
	public void setConnectionNameList(String connectionNameList) {
		this.connectionNameList = connectionNameList;
	}
	
	/**
	 * @return the queueManager
	 */
	public String getQueueManager() {
		return queueManager;
	}
	
	/**
	 * @param queueManager the queueManager to set
	 */
	public void setQueueManager(String queueManager) {
		this.queueManager = queueManager;
	}
	
	/**
	 * @return the channel
	 */
	public String getChannel() {
		return channel;
	}
	
	/**
	 * @param channel the channel to set
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	/**
	 * @return the transportType
	 */
	public Integer getTransportType() {
		return transportType;
	}
	
	/**
	 * @param transportType the transportType to set
	 */
	public void setTransportType(Integer transportType) {
		this.transportType = transportType;
	}
	
	/**
	 * @return the clientReconnectOptions
	 */
	public Integer getClientReconnectOptions() {
		return clientReconnectOptions;
	}
	
	/**
	 * @param clientReconnectOptions the clientReconnectOptions to set
	 */
	public void setClientReconnectOptions(Integer clientReconnectOptions) {
		this.clientReconnectOptions = clientReconnectOptions;
	}

	/**
	 * @return the transacted
	 */
	public Boolean isTransacted() {
		return transacted;
	}

	/**
	 * @param transacted the transacted to set
	 */
	public void setTransacted(Boolean transacted) {
		this.transacted = transacted;
	}

	/**
	 * @return the cacheLevelName
	 */
	public String getCacheLevelName() {
		return cacheLevelName;
	}

	/**
	 * @param cacheLevelName the cacheLevelName to set
	 */
	public void setCacheLevelName(String cacheLevelName) {
		this.cacheLevelName = cacheLevelName;
	}

	/**
	 * @return the concurrentConsumers
	 */
	public Integer getConcurrentConsumers() {
		return concurrentConsumers;
	}

	/**
	 * @param concurrentConsumers the concurrentConsumers to set
	 */
	public void setConcurrentConsumers(Integer concurrentConsumers) {
		this.concurrentConsumers = concurrentConsumers;
	}

	/**
	 * @return the receiveTimeout
	 */
	public Integer getReceiveTimeout() {
		return receiveTimeout;
	}

	/**
	 * @param receiveTimeout the receiveTimeout to set
	 */
	public void setReceiveTimeout(Integer receiveTimeout) {
		this.receiveTimeout = receiveTimeout;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the maxConnections
	 */
	public Integer getMaxConnections() {
		return maxConnections;
	}

	/**
	 * @param maxConnections the maxConnections to set
	 */
	public void setMaxConnections(Integer maxConnections) {
		this.maxConnections = maxConnections;
	}

	/**
	 * @return the idleTimeout
	 */
	public Integer getIdleTimeout() {
		return idleTimeout;
	}

	/**
	 * @param idleTimeout the idleTimeout to set
	 */
	public void setIdleTimeout(Integer idleTimeout) {
		this.idleTimeout = idleTimeout;
	}

	/**
	 * @return the queueName
	 */
	public String getQueueName() {
		return queueName;
	}

	/**
	 * @param queueName the queueName to set
	 */
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
}
