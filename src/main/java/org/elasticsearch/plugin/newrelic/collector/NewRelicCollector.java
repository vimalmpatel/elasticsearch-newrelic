/*
 * Licensed to ElasticSearch under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. ElasticSearch licenses this
 * file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.elasticsearch.plugin.newrelic.collector;

import org.elasticsearch.common.logging.ESLogger;
import org.elasticsearch.common.logging.ESLoggerFactory;
import org.elasticsearch.plugin.newrelic.model.Metric;

import com.newrelic.api.agent.NewRelic;

public class NewRelicCollector implements MetricCollector {

	private ESLogger logger = ESLoggerFactory.getLogger(NewRelicCollector.class.getName());
	
	@Override
	public void recordMetric(String name, Number value) {
		if(value != null){
			logger.debug("Pushing metric [{}:{}]", name, value.floatValue());
			NewRelic.recordMetric(name, value.floatValue());
		}
	}

	@Override
	public void recordResponseTimeMetric(String name, long millis) {
		NewRelic.recordResponseTimeMetric(name, millis);
	}

	@Override
	public void recordMetric(Metric metric) {
		if(metric.getValue() != null){
			NewRelic.recordMetric(metric.getName(), metric.getValue().floatValue());
		}
	}

	
}
