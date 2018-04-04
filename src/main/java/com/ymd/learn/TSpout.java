package com.ymd.learn;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichSpout;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

public class TSpout implements IRichSpout {

	private static final long serialVersionUID = 8413800325461406890L;

	BufferedReader br;
	SpoutOutputCollector collector;
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.collector = collector;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream("source.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("log"));
	}

	public void close() {
		
	}

	public void activate() {
		
	}

	public void deactivate() {
		
	}

	String line;
	
	public void nextTuple() {
		System.err.println("-------------------------Spout in-------------------------");
		try {
			while((line=br.readLine())!=null) {
				collector.emit(new Values(line));
				Thread.sleep(200);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.err.println("-------------------------Spout out-------------------------");
	}

	public void ack(Object msgId) {
		System.err.println("TSpout ---------- ack ---------- msgId " + msgId);
	}

	public void fail(Object msgId) {
		System.err.println("TSpout ---------- fail ---------- msgId " + msgId);
	}

	public Map<String, Object> getComponentConfiguration() {
		return null;
	}

}
