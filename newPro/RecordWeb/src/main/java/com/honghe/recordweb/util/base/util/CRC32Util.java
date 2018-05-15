package com.honghe.recordweb.util.base.util;

import java.util.zip.CRC32;

/**
 * 可用于计算数据流的 CRC-32 的工具类
 * 
 * @author zhuangwei
 * 
 */
public class CRC32Util {
	private CRC32 crc = new CRC32();

	/**
	 * 使用指定的字节数组更新校验和
	 * 
	 * @param bs
	 *            更新校验和使用的字节数组
	 */
	public void update(byte[] b) {
		crc.update(b);
	}

	/**
	 * 使用指定的字符串更新校验和
	 * 
	 * @param b
	 *            更新校验和要使用的字符串
	 */
	public void update(String b) {
		update(b.getBytes());
	}

	/**
	 * 使用指定的字节数组更新 CRC-32
	 * 
	 * @param b
	 *            更新校验和要使用的字节数组
	 * @param off
	 *            数据的初始偏移量
	 * @param len
	 *            用于更新的字节数
	 */
	public void update(byte[] b, int off, int len) {
		crc.update(b, off, len);
	}

	/**
	 * 使用指定的字节数组更新校验和
	 * 
	 * @param b
	 *            更新校验和使用的字节数组
	 */
	public void update(int b) {
		crc.update(b);
	}

	/**
	 * 将 CRC-32 重置为初始值
	 */
	public void reset() {
		crc.reset();
	}

	/**
	 * 当前的校验和值
	 * 
	 * @return Long 类型 CRC-32 值
	 */
	public long getValueLong() {
		return crc.getValue();
	}

	/**
	 * 当前的校验和值
	 * 
	 * @return String类型 CRC-32 值
	 */
	public String getValueString() {
		return Long.toHexString(crc.getValue());
	}

	public static void main(String[] args) {
		CRC32Util crc = new CRC32Util();
		crc.update("fdjf");
		// crc.reset();
		System.out.println(crc.getValueString());
		
		CRC32 c = new CRC32();
		c.update("fdjf".getBytes());
		System.out.println(c.getValue());

	}
}