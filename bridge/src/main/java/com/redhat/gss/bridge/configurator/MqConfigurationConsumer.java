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
package com.redhat.gss.bridge.configurator;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

import org.messaginghub.pooled.jms.JmsPoolConnectionFactory;
import org.apache.camel.component.jms.JmsComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter;

import com.ibm.mq.jms.MQConnectionFactory;
import com.redhat.gss.bridge.properties.ConsumerMQ;

/**
 * @author grovedc
 *
 */
@Configuration
public class MqConfigurationConsumer {
	@Autowired
	private ConsumerMQ consumer;
	
//    @Bean
    public ConnectionFactory connectionFactory() {
    	MQConnectionFactory connectionFactory = new MQConnectionFactory();
        
    	try {
			connectionFactory.setConnectionNameList(consumer.getConnectionNameList());
			connectionFactory.setChannel(consumer.getChannel());
			connectionFactory.setQueueManager(consumer.getQueueManager());
			connectionFactory.setTransportType(consumer.getTransportType());
			connectionFactory.setClientReconnectOptions(consumer.getClientReconnectOptions());
		} catch (JMSException e) {
			e.printStackTrace();
		}
    	
        return connectionFactory;
    }
    
 //   @Bean
    public ConnectionFactory userCredConnectionFactory() {
    	UserCredentialsConnectionFactoryAdapter userCred = new UserCredentialsConnectionFactoryAdapter();
    	
    	userCred.setTargetConnectionFactory(connectionFactory());
    	userCred.setUsername(consumer.getUsername());
    	userCred.setPassword(consumer.getPassword());
    	
    	return userCred;
    }
    
 //   @Bean
    public ConnectionFactory pooledConnectionFactory() {
    	JmsPoolConnectionFactory pooledCF = new JmsPoolConnectionFactory();
    	
    	pooledCF.setConnectionFactory(userCredConnectionFactory());
//    	pooledCF.setIdleTimeout(consumer.getIdleTimeout());
    	pooledCF.setMaxConnections(consumer.getMaxConnections());
    	
    	return pooledCF;
    }
    
	@Bean(name = "mq-src")
	public JmsComponent mqSrcComponent()
	{		
		JmsComponent mqComp= new JmsComponent();
		mqComp.setTransacted(consumer.isTransacted());
		mqComp.setCacheLevelName(consumer.getCacheLevelName());
		mqComp.setConnectionFactory(pooledConnectionFactory());
		mqComp.setConcurrentConsumers(consumer.getConcurrentConsumers());
		mqComp.setReceiveTimeout(consumer.getReceiveTimeout());
		return mqComp;
	}
}
