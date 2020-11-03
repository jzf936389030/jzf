package com.yimeng.seed.organ.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 序列号/流水号工具类
 * @author chenwei
 *
 */
public class DataSnUtil {
	private final static int MAX_SEQNUM = 999;
	private static AtomicInteger seqnum = new AtomicInteger(0); // 内部递增顺序号

	/**
	 * 产生时间戳相关的流水号
	 * @return 流水号
	 */
	public static long genTimestampSeq() {
		long ms = System.currentTimeMillis();
		int postfix = getSeqNum();
		return (ms/1000)*1000 + postfix;
	}
	
	/**
	 * 获取程序内部维护的递增顺序号，保证多线程唯一性
	 * @return 顺序号
	 */
	private static int getSeqNum() {
		int postfix = seqnum.incrementAndGet();
		if (postfix > MAX_SEQNUM) {
			boolean success = seqnum.compareAndSet(postfix, 0);
			while (!success) {
				postfix = seqnum.get();
				if (postfix < MAX_SEQNUM) {
					break;
				} else {
					success = seqnum.compareAndSet(postfix, 0);
				}
			}
			postfix = seqnum.incrementAndGet();
		}
		//
		return postfix;
	}
}
