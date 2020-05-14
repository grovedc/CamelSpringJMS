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
import com.redhat.gss.bridge.properties.ProducerMQ;

/**
 * @author grovedc
 *
 */
@Configuration
public class MqConfigurationProducer {
	@Autowired
	private ProducerMQ producer;

//    @Bean
    public ConnectionFactory connectionFactory() {
    	MQConnectionFactory connectionFactory = new MQConnectionFactory();
        
    	try {
			connectionFactory.setConnectionNameList(producer.getConnectionNameList());
			connectionFactory.setChannel(producer.getChannel());
			connectionFactory.setQueueManager(producer.getQueueManager());
			connectionFactory.setTransportType(producer.getTransportType());
			connectionFactory.setClientReconnectOptions(producer.getClientReconnectOptions());
		} catch (JMSException e) {
			e.printStackTrace();
		}
    	
        return connectionFactory;
    }

 //   @Bean
    public ConnectionFactory userCredConnectionFactory() {
    	UserCredentialsConnectionFactoryAdapter userCred = new UserCredentialsConnectionFactoryAdapter();
    	
    	userCred.setTargetConnectionFactory(connectionFactory());
    	userCred.setUsername(producer.getUsername());
    	userCred.setPassword(producer.getPassword());
    	
    	return userCred;
    }
    
 //   @Bean
    public ConnectionFactory pooledConnectionFactory() {
    	JmsPoolConnectionFactory pooledCF = new JmsPoolConnectionFactory();
    	
    	pooledCF.setConnectionFactory(userCredConnectionFactory());
//    	pooledCF.setIdleTimeout(producer.getIdleTimeout());
    	pooledCF.setMaxConnections(producer.getMaxConnections());
    	
    	return pooledCF;
    }
    
	@Bean(name = "mq-dest")
	public JmsComponent mqDestComponent()
	{		
		JmsComponent mqComp= new JmsComponent();
		mqComp.setTransacted(producer.isTransacted());
		mqComp.setConnectionFactory(pooledConnectionFactory());
		return mqComp;
	}
}
