package com.ymd.learn;

import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Tuple;

public class TBolt implements IRichBolt {

	private static final long serialVersionUID = 5552406845140219566L;

	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		
	}

	int count = 0;
	public void execute(Tuple input) {
		//System.err.println("-------------------------bolt in-------------------------");
		String log = input.getStringByField("log");
		if(log != null) {
			System.err.println("Thread " + Thread.currentThread().getId() + "-------line : " + count + "------------------log" + log);
			count++;
		}
		//System.err.println("-------------------------bolt out-------------------------");
	}

	public void cleanup() {
		
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		
	}

	public Map<String, Object> getComponentConfiguration() {
		return null;
	}

}
