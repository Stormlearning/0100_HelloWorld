package com.ymd.learn;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.topology.TopologyBuilder;

public class Main {

	public static void main(String[] args) {

		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("tspout", new TSpout());
		builder.setBolt("tbolt", new TBolt(), 2).shuffleGrouping("tspout");

		Config conf = new Config();
		conf.setDebug(true);

		if (args.length > 0) {
			try {
				StormSubmitter.submitTopology("ttopology", conf, builder.createTopology());
			} catch (AlreadyAliveException e) {
				e.printStackTrace();
			} catch (InvalidTopologyException e) {
				e.printStackTrace();
			} catch (AuthorizationException e) {
				e.printStackTrace();
			}
		} else {
			LocalCluster cluster = new LocalCluster();
			cluster.submitTopology("ttopology", conf, builder.createTopology());
		}

	}

}
