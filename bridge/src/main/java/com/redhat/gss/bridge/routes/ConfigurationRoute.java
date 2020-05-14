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
package com.redhat.gss.bridge.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/**
 * @author grovedc
 *
 */
public class ConfigurationRoute extends RouteBuilder {
	@Value("${track}")
	private Boolean track;

	@Value("${maximumRedeliveries}")
	private int maximumRedeliveries;

	@Value("${redeliveryDelay}")
	private int redeliveryDelay;

	@Value("${uriEndPointDLQ}")
	private String uriEndPointDLQ;

	@Value("${errorHandle}")
	private Boolean errorHandle;

	@Value("${loggingName}")
	private String logName;

	@Bean
	public Logger getLogger() {
		return org.slf4j.LoggerFactory.getLogger(logName);
	}

	@Override
	public void configure() throws Exception {
		getContext().setTracing(track);
		getContext().setStreamCaching(true);
		getContext().setUseMDCLogging(true);
		onException(Exception.class)
			.log(LoggingLevel.ERROR,"Error: ${routeId} ${exception} ${exception.message}");
	}
}
