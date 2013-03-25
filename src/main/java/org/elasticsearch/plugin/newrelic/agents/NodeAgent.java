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
package org.elasticsearch.plugin.newrelic.agents;

import java.util.concurrent.atomic.AtomicBoolean;

import org.elasticsearch.action.admin.cluster.node.stats.NodeStats;
import org.elasticsearch.common.logging.ESLogger;
import org.elasticsearch.common.logging.ESLoggerFactory;
import org.elasticsearch.plugin.newrelic.collector.MetricCollector;
import org.elasticsearch.plugin.newrelic.collector.NewRelicCollector;

public abstract class NodeAgent {
	
	protected ESLogger logger = ESLoggerFactory.getLogger(this.getClass().getName());
	
	protected MetricCollector collector = new NewRelicCollector();
	
	protected AtomicBoolean enabled = new AtomicBoolean(true);
	
	public void setCollector(MetricCollector collector) {
		this.collector = collector;
	}
	
	public abstract void execute(NodeStats nodeStats);
	
	public abstract String getName();
	
	public Boolean isEnabled(){
		return enabled.get();
	}
	
	
	public void setEnabled(Boolean bool){
		enabled.set(bool);
	}
}
