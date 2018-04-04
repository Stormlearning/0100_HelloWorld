package com.ymd.learn;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Random;
import java.util.UUID;

public class GenData {
	
	public static void main(String[] args) throws Exception {
		
		String[] hosts = {"www.taobao.com"};
		String[] sessionIds = new String[5];
		sessionIds[0] = genSessionId();
		sessionIds[1] = genSessionId();
		sessionIds[2] = genSessionId();
		sessionIds[3] = genSessionId();
		sessionIds[4] = genSessionId();
		String[] times = {"2017-07-01 09:00:10", "2017-07-01 10:20:00", "2017-07-01 12:30:00", 
				"2017-07-01 14:00:02", "2017-07-01 18:00:09"};
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("source.txt")));
		Random random = new Random();
		for(int i=0; i<50; i++) {
			bw.write(hosts[0] + "&&" + sessionIds[random.nextInt(sessionIds.length)] + "&&" +
					times[random.nextInt(times.length)]);
			bw.newLine();
		}
		bw.flush();
		bw.close();
	} 
	
	private static String genSessionId() {
		return UUID.randomUUID().toString();
	}
}
