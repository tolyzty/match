package com.util;

import org.apache.commons.lang.StringUtils;

public class TdXOR {

	// private static String DES = "DES/ECB/NoPadding";

	/**
	 * 获取字符串按8byte异或数据 大写 1.字符串转16进制 2.16进制字符串做BCD压缩 3.压缩后的byte数组8个长度一组做异或
	 * 
	 * @param macData
	 * @return
	 */
	public static String Byte8XORData(String macData) {
		byte[] macByte = str2Bcd(toHexString(macData));
		return Byte8XOR(macByte).toUpperCase();
	}

	/**
	 * byte[]数组 每八位为一组，循环进行异或 最后得到一个八位长度的byte数组 得到的数组进行转16进制字符处理 计算MAC值
	 * 使用该值进行预加工
	 * 
	 * @param args
	 * @return
	 */
	public static String Byte8XOR(byte[] args) {
		byte[][] data = null; // 初始化一个二维数组

		// byte数组的长度
		int length = args.length;
		// 末位 余8的长度
		int end = length % 8;
		// 二维数组的长度
		int width = length / 8;
		// 如果末位不为0 则长度加1
		if (end != 0) {
			width++;
		}
		data = new byte[width][8];

		int colCount = 0;// 二维长度计数
		int linCount = 0;// 一维长度计数

		for (int i = 0; i < length; i++) {
			data[linCount][colCount] = args[i];
			colCount++;
			if (colCount == 8) {
				linCount++;
				colCount = 0;
			}
		}
		if (end > 0) {
			for (int j = end; j < 8; j++) {
				data[width - 1][j] = 0x00;
			}
		}

		// 临时数据
		byte[] tempData = data[0];

		for (int k = 1; k < width; k++) {
			byte[] secData = data[k];
			for (int i = 0; i < 8; i++) {
				tempData[i] = (byte) (tempData[i] ^ secData[i]);
			}
		}

		String result = bytesToHexString(tempData);
		return result;
	}

	/**
	 * 把字节数组转化成16进制的字符串 计算机中数据以补码形式存储。 负数：符号位不变，其他取反，最后加1
	 * 
	 * @param src
	 * @return
	 */
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	/**
	 * 把字节数组转化成16进制的字符串
	 * 
	 * @param src
	 * @return
	 */
	public static String BytesToHexStr(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString().toUpperCase();
	}

	/**
	 * 16进制字符串转BCD码 将字符串2位合1位 8421BCD码 最多可表示16个字符 即支持16进制转BCD 将字符0-9|a-f|A-F
	 * 转换为0-15的数值
	 * 
	 * @param asc
	 * @return
	 */
	public static byte[] str2Bcd(String asc) {
		int len = asc.length();
		int mod = len % 2;

		if (mod != 0) {
			asc = "0" + asc;
			len = asc.length();
		}

		byte[] abt = new byte[len];
		if (len >= 2) {
			len = len / 2;
		}

		byte[] bbt = new byte[len];
		abt = asc.getBytes();
		int j, k;

		for (int p = 0; p < asc.length() / 2; p++) {
			if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {
				j = abt[2 * p] - '0';
			} else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {
				j = abt[2 * p] - 'a' + 0x0a;
			} else {
				j = abt[2 * p] - 'A' + 0x0a;
			}

			if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {
				k = abt[2 * p + 1] - '0';
			} else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {
				k = abt[2 * p + 1] - 'a' + 0x0a;
			} else {
				k = abt[2 * p + 1] - 'A' + 0x0a;
			}

			int a = (j << 4) + k;
			byte b = (byte) a;
			bbt[p] = b;
		}
		return bbt;
	}

	/**
	 * BCD码转16进制字符串
	 * 
	 * @param asc
	 * @return
	 */
	public static String bcd2Str(byte[] bytes) {
		StringBuffer temp = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			// &操作舍弃低四位
			temp.append((byte) ((bytes[i] & 0xf0) >>> 4));
			// &操作舍弃高四位
			temp.append((byte) (bytes[i] & 0x0f));
		}
		// 对自动补充的0 去掉
		return temp.toString().substring(0, 1).equalsIgnoreCase("0") ? temp
				.toString().substring(1) : temp.toString();
	}

	// public static String SubMsgBodyHexXOR(byte [] args, int pos, int len){
	// OutputStream out = Context.peekInstance(OutputStream.class);
	// len = args.length - len - pos;
	// byte[] dest = new byte[len];
	// System.arraycopy(args, pos, dest, 0, len);
	// String hexStr = Byte8XOR(dest).toUpperCase();
	// System.out.println("转换成16进制的字符为：  "+hexStr);
	// System.out.println(hexStr);
	//
	// try {
	// out.write(hexStr.getBytes());
	// } catch (IOException e) {
	// Log.info("截取8583报文体出错！", "");
	// }
	//
	// return "";
	// }

	/**
	 * 字符串转16进制显示
	 * 
	 * @param s
	 * @return
	 */
	public static String toHexString(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			int ch = (int) s.charAt(i);
			String s4 = Integer.toHexString(ch);
			str = str + s4;
		}
		return str;
	}

	/**
	 * 计算pinBlock
	 * 
	 * @param account
	 *            主账号（可为空）
	 * @param passwd
	 *            密码
	 * @return
	 */
	public static String PinEncrypt(String account, String passwd) {
		String result = "";
		String accountTemp1 = "";
		int passwdLen = passwd.length();
		if (passwdLen == 0) {
			passwd = "FFFFFF";
		} else if (passwdLen < 6) {
			for (int i = 0; i < 6 - passwdLen; i++) {
				passwd += "F";
			}
		}
		// 密码临时数据长度为16
		String passwdTemp1 = "0" + passwdLen + passwd + "FFFFFFFF";
		// 账户临时数据也为16
		if (account != null && !"".equals(account)) {
			int len = account.length();
			String accountTemp = account.substring(len - 13, len - 1);
			accountTemp1 = "0000" + accountTemp;
		}

		// 如果账户为空
		if (accountTemp1.equals("")) {
			result = passwdTemp1;
		} else {
			// 账户与密码首先进行BCD压缩(计算pinblock)
			byte[] accountByte = str2Bcd(accountTemp1);
			byte[] passwdByte = str2Bcd(passwdTemp1);

			byte[] resultByte = new byte[8];

			// 账户与密码异或
			for (int i = 0; i < 8; i++) {
				resultByte[i] = (byte) (accountByte[i] ^ passwdByte[i]);
			}
			// 异或的结果转16进制字符串
			result = bytesToHexString(resultByte);
		}

		return result.toUpperCase();
	}

	/**
	 * 转加密
	 * 
	 * @param Tmk
	 *            源终端主密钥
	 * @param zmk
	 *            目标端终端主密钥
	 * @param Tpink
	 *            源工作密钥
	 * @param pinkey
	 *            目标工作密钥
	 * @param account
	 *            主账号
	 * @param TPinBlk
	 *            源PinBlk
	 * @return 目标PinBlk
	 */
	public static String pinReEncry(String Tmk, String zmk, String Tpink,
			String pinkey, String account, String TPinBlk) {
		return pinReEncry(Tmk, zmk, Tpink, pinkey, account, TPinBlk, "06", "01");
	}

	/**
	 * 转加密
	 * 
	 * @param Tmk
	 *            源终端主密钥
	 * @param zmk
	 *            目标端终端主密钥
	 * @param Tpink
	 *            源工作密钥
	 * @param pinkey
	 *            目标工作密钥
	 * @param account
	 *            主账号
	 * @param TPinBlk
	 *            源PinBlk
	 * @param srcPinType
	 *            原密码加密方式 01带卡号 06 不带卡号
	 * @param tarPinType
	 *            目标密码加密方式 01带卡号 06 不带卡号
	 * @return 目标PinBlk
	 */
	public static String pinReEncry(String Tmk, String zmk, String Tpink,
			String pinkey, String account, String TPinBlk, String srcPinType,
			String tarPinType) {
		
		//默认加密方式 ： 原密码默认不带卡号，目标密码默认带卡号
		if(StringUtils.isEmpty(srcPinType)){
			srcPinType = "06";
		}
		
		if(StringUtils.isEmpty(tarPinType)){
			tarPinType = "01";
		}
		
		byte[] tmkkByte = str2Bcd(Tmk);
		byte[] tpinkByte = str2Bcd(Tpink);
		byte[] MwPinkByte = TdDesUtil.Union3DesDecrypt(tmkkByte, tpinkByte);

		byte[] tpinblackByte = str2Bcd(TPinBlk);
		byte[] pinBlack = TdDesUtil.Union3DesDecrypt(MwPinkByte, tpinblackByte);
		
		if ("01".equals(srcPinType)) {// 原密码带卡号
			if ((account != null) && (!"".equals(account))) {
				int len = account.length();
				String accountTemp = account.substring(len - 13, len - 1);
				String accountTemp1 = "0000" + accountTemp;
				byte[] accountByte = str2Bcd(accountTemp1);
				byte[] resultByte = new byte[8];
				for (int i = 0; i < 8; i++) {
					resultByte[i] = ((byte) (pinBlack[i] ^ accountByte[i]));
				}
				pinBlack = resultByte;
			}
		}

//		System.out.println("pin:" + TdXOR.BytesToHexStr(pinBlack));

		if ("01".equals(tarPinType)) {// 目标密码带卡号
			if ((account != null) && (!"".equals(account))) {
				int len = account.length();
				String accountTemp = account.substring(len - 13, len - 1);
				String accountTemp1 = "0000" + accountTemp;
				byte[] accountByte = str2Bcd(accountTemp1);
				byte[] resultByte = new byte[8];
				for (int i = 0; i < 8; i++) {
					resultByte[i] = ((byte) (pinBlack[i] ^ accountByte[i]));
				}
				pinBlack = resultByte;
			}
		}

		byte[] pinkeyByte = str2Bcd(pinkey);
		byte[] zmkByte = str2Bcd(zmk);
		byte[] pinkeyMwByte = TdDesUtil.Union3DesDecrypt(zmkByte, pinkeyByte);
//		System.out.println("pin:" + TdXOR.BytesToHexStr(pinBlack));
		byte[] pinResultByte = TdDesUtil.Union3DesEncrypt(pinkeyMwByte,
				pinBlack);
		return bytesToHexString(pinResultByte).toUpperCase();
	}

	/**
	 * MAC9606
	 * 
	 * @param TMK
	 * @param PMK
	 * @param macData
	 * @return
	 */
	public static String Mac9609(String TMK, String PMK, String macData) {
		byte[] tmkByte = str2Bcd(TMK);
		byte[] pmkByte = str2Bcd(PMK);

		byte[] MwPmkByte = TdDesUtil.DoubleDesDecrypt(tmkByte, pmkByte);

		String MwPmkString = bytesToHexString(MwPmkByte).toUpperCase();

		return Mac9609Mw(MwPmkString, macData);
	}

	/**
	 * MAC密文9606
	 * 
	 * @param key
	 * @param data
	 * @return
	 */
	public static String Mac9609Mw(String key, String macData) {
		String mac = "";

		byte[] keyByte = str2Bcd(key); // key经过BCD压缩
		byte[] dataByte = str2Bcd(toHexString(macData)); // 先把数据进行转16进制 然后BCD压缩

		byte[][] data = null; // 预算数据

		// 数据长度
		int length = dataByte.length;
		// 末位长度
		int end = length % 8;
		// 宽度
		int width = length / 8;
		// 末位长度不为0 宽度加1
		if (end != 0) {
			width++;
		}
		data = new byte[width][8];

		int colCount = 0;// 二维计数
		int linCount = 0;// 一维计数

		for (int i = 0; i < length; i++) {
			data[linCount][colCount] = dataByte[i];
			colCount++;
			if (colCount == 8) {
				linCount++;
				colCount = 0;
			}
		}
		if (end > 0) {
			for (int j = end; j < 8; j++) {
				data[width - 1][j] = 0x00;
			}
		}

		// 临时数据
		byte[] tempData = data[0];

		for (int k = 1; k < width; k++) {
			byte[] secData = data[k];
			tempData = TdDesUtil.DoubleDesEncrypt(keyByte, tempData);
			for (int i = 0; i < 8; i++) {
				tempData[i] = (byte) (tempData[i] ^ secData[i]);
			}
		}

		// 对异或的数据进行两次DES加密
		tempData = TdDesUtil.DoubleDesEncrypt(keyByte, tempData);

		mac = bytesToHexString(tempData).toUpperCase();

		return mac;
	}

	/**
	 * MAC99
	 * 
	 * @param TMK
	 * @param PMK
	 * @param macData
	 * @return
	 */
	public static String Mac99(String TMK, String PMK, String macData) {
		byte[] tmkByte = str2Bcd(TMK);
		byte[] pmkByte = str2Bcd(PMK);

		byte[] MwPmkByte = TdDesUtil.Union3DesDecrypt(tmkByte, pmkByte);
		String MwPmkString = bytesToHexString(MwPmkByte).toUpperCase();

		return Mac99Mw(MwPmkString, macData);
	}

	/**
	 * MAC99
	 * 
	 * @param key
	 * @param data
	 * @return
	 */
	public static String Mac99Mw(String key, String macData) {
		String mac = "";

		byte[] keyByte = str2Bcd(key); // MAC key
		byte[] dataByte = str2Bcd(macData); // mac数据

		byte[][] data = null; // 分组数据

		// 数据长度
		int length = dataByte.length;
		// 末组长度
		int end = length % 8;
		// 一维长度
		int width = length / 8;
		// 末组长度不为0 一维长度加1
		if (end != 0) {
			width++;
		}
		data = new byte[width][8];

		int colCount = 0;// 二维长度
		int linCount = 0;// 一维长度

		for (int i = 0; i < length; i++) {
			data[linCount][colCount] = dataByte[i];
			colCount++;
			if (colCount == 8) {
				linCount++;
				colCount = 0;
			}
		}
		if (end > 0) {
			for (int j = end; j < 8; j++) {
				data[width - 1][j] = 0x00;
			}
		}
		// 临时数据 存放异或数据
		byte[] tempData = data[0];
		for (int k = 1; k < width; k++) {
			byte[] secData = data[k];
			tempData = TdDesUtil.DoubleDesEncrypt(keyByte, tempData);
			for (int i = 0; i < 8; i++) {
				tempData[i] = (byte) (tempData[i] ^ secData[i]);
			}
		}
		// 异或的数据进行两次DES加密
		tempData = TdDesUtil.DoubleDesEncrypt(keyByte, tempData);
		mac = bytesToHexString(tempData).toUpperCase();
		return mac;
	}

	/**
	 * MAC9.19
	 * 
	 * @param key
	 * @param data
	 * @return
	 */
	public static String Mac919Mw(String key, String macData) {
		String mac = "";

		byte[] keyByte1 = str2Bcd(key.substring(0, 16)); // MAC key 左半段
		byte[] keyByte2 = str2Bcd(key.substring(16)); // MAC key 右半段
		byte[] dataByte = str2Bcd(macData); // mac数据

		byte[][] data = null; // 分组数据

		// 数据长度
		int length = dataByte.length;
		// 末组长度
		int end = length % 8;
		// 一维长度
		int width = length / 8;
		// 末组长度不为0 一维长度加1
		if (end != 0) {
			width++;
		}
		data = new byte[width][8];

		int colCount = 0;// 二维长度
		int linCount = 0;// 一维长度

		for (int i = 0; i < length; i++) {
			data[linCount][colCount] = dataByte[i];
			colCount++;
			if (colCount == 8) {
				linCount++;
				colCount = 0;
			}
		}
		if (end > 0) {
			for (int j = end; j < 8; j++) {
				data[width - 1][j] = 0x00;
			}
		}
		// 临时数据 存放异或数据
		byte[] tempData = data[0];
		for (int k = 1; k < width; k++) {
			byte[] secData = data[k];
			tempData = TdDesUtil.UnionDesEncrypt(keyByte1, tempData);
			for (int i = 0; i < 8; i++) {
				tempData[i] = (byte) (tempData[i] ^ secData[i]);
			}
		}
		// 异或的数据进行两次DES加密
		tempData = TdDesUtil.UnionDesEncrypt(keyByte1, tempData);
		tempData = TdDesUtil.UnionDesDecrypt(keyByte2, tempData);
		tempData = TdDesUtil.UnionDesEncrypt(keyByte1, tempData);
		mac = bytesToHexString(tempData).toUpperCase().substring(0, 8);
		return mac;
	}

	/**
	 * MAC9.19 16进制字符全Mac
	 * 
	 * @param key
	 * @param data
	 * @return
	 */
	public static String Mac919All(String key, String macData) {
		String mac = "";

		byte[] keyByte1 = str2Bcd(key.substring(0, 16)); // MAC key 左半段
		byte[] keyByte2 = str2Bcd(key.substring(16)); // MAC key 右半段
		byte[] dataByte = str2Bcd(macData); // mac数据

		byte[][] data = null; // 分组数据

		// 数据长度
		int length = dataByte.length;
		// 末组长度
		int end = length % 8;
		// 一维长度
		int width = length / 8;
		// 末组长度不为0 一维长度加1
		if (end != 0) {
			width++;
		}
		data = new byte[width][8];

		int colCount = 0;// 二维长度
		int linCount = 0;// 一维长度

		for (int i = 0; i < length; i++) {
			data[linCount][colCount] = dataByte[i];
			colCount++;
			if (colCount == 8) {
				linCount++;
				colCount = 0;
			}
		}
		if (end > 0) {
			for (int j = end; j < 8; j++) {
				data[width - 1][j] = 0x00;
			}
		}
		// 临时数据 存放异或数据
		byte[] tempData = data[0];
		for (int k = 1; k < width; k++) {
			byte[] secData = data[k];
			tempData = TdDesUtil.UnionDesEncrypt(keyByte1, tempData);
			for (int i = 0; i < 8; i++) {
				tempData[i] = (byte) (tempData[i] ^ secData[i]);
			}
		}
		// 异或的数据进行两次DES加密
		tempData = TdDesUtil.UnionDesEncrypt(keyByte1, tempData);
		tempData = TdDesUtil.UnionDesDecrypt(keyByte2, tempData);
		tempData = TdDesUtil.UnionDesEncrypt(keyByte1, tempData);
		mac = bytesToHexString(tempData).toUpperCase();
		return mac;
	}

	/**
	 * MAC值ECB
	 * 
	 * @param TMK
	 * @param PMK
	 * @param macData
	 * @return
	 */
	public static String MacEcb(String TMK, String PMK, String macData) {
		byte[] tmkByte = str2Bcd(TMK);
		byte[] pmkByte = str2Bcd(PMK);
		// TMK对PMK做两次解密运算
		byte[] MwPmkByte = TdDesUtil.DoubleDesDecrypt(tmkByte, pmkByte);
		String MwPmkString = bytesToHexString(MwPmkByte).toUpperCase();
		return MacEcbMw(MwPmkString, macData);
	}

	/**
	 * MAC ECB
	 * 
	 * @param key
	 * @param macData
	 * @return
	 */
	public static String MacEcbMw(String key, String macData) {
		String mac = "";
		byte[] keyByte = str2Bcd(key); // key值
		byte[] dataByte = str2Bcd(macData); // mac值转BCD码
		// 分组异或获取8长度的byte数组转化的16进制字符串
		String tempData = Byte8XOR(dataByte).toUpperCase();
		// 把字符串拆分为16进制数进行表示
		String hexTempData = toHexString(tempData);
		String leftData = hexTempData.substring(0, 16);
		String rightData = hexTempData.substring(16);
		byte[] leftByte = str2Bcd(leftData);
		byte[] rightByte = str2Bcd(rightData);
		// 对leftByte进行两次DES加密
		byte[] tempByte = TdDesUtil.DoubleDesEncrypt(keyByte, leftByte);
		// 使用rightByte对tempByte进行异或
		for (int i = 0; i < 8; i++) {
			tempByte[i] = (byte) (tempByte[i] ^ rightByte[i]);
		}
		// 对tempByte进行两次DES加密
		tempByte = TdDesUtil.DoubleDesEncrypt(keyByte, tempByte);
		String tempByteString = toHexString(bytesToHexString(tempByte)
				.toUpperCase());
		mac = tempByteString.substring(0, 16);
		return mac;
	}

	/**
	 * MAC值ECB,MAC16
	 * 
	 * @param key
	 * @param macData
	 * @return
	 */
	public static String MacEcb16Mw(String key, String macData) {
		String mac = "";
		byte[] keyByte = str2Bcd(key); // key值BCD压缩
		byte[] dataByte = str2Bcd(macData); // 数据BCD压缩
		// 临时数据 数据进行8位byte数组异或，对byte数组转化为16进制字符串
		String tempData = Byte8XOR(dataByte).toUpperCase();
		String hexTempData = toHexString(tempData);
		String leftData = hexTempData.substring(0, 16);
		String rightData = hexTempData.substring(16);
		byte[] leftByte = str2Bcd(leftData);
		byte[] rightByte = str2Bcd(rightData);
		byte[] tempByte = TdDesUtil.UnionDesEncrypt(keyByte, leftByte);
		for (int i = 0; i < 8; i++) {
			tempByte[i] = (byte) (tempByte[i] ^ rightByte[i]);
		}
		tempByte = TdDesUtil.UnionDesEncrypt(keyByte, tempByte);
		String tempByteString = toHexString(bytesToHexString(tempByte)
				.toUpperCase());
		mac = tempByteString.substring(0, 16).toUpperCase();
		return mac;
	}

	public static String pinResultMak(String zmk, String pinKey,
			String account, String passwd) {
		String pinBlack = PinEncrypt(account, passwd);
		byte[] tmkByte = str2Bcd(zmk);
		byte[] pinkByte = str2Bcd(pinKey);
		byte[] pinkBlackByte = str2Bcd(pinBlack);
		byte[] MwPinkByte = TdDesUtil.Union3DesDecrypt(tmkByte, pinkByte);
		byte[] pinResultByte = TdDesUtil.DoubleDesEncrypt(MwPinkByte,
				pinkBlackByte);
		String pinResult = bytesToHexString(pinResultByte).toUpperCase();
		return pinResult;
	}

	public static void main(String[] args) throws Exception {
		String zmk = TdXOR.XOR("ADADE6D5F8DA8FC4FDCE80831F19737F", "ECCD670B79E076F1381FC226DFE00D83");
		System.out.println(zmk);

	}
	
	/*public static String string2Unicode(String string) {
        
        StringBuffer unicode = new StringBuffer();
      
        for (int i = 0; i < string.length(); i++) {
      
            // 取出每一个字符
            char c = string.charAt(i);
      
            // 转换为unicode
            unicode.append("\\u" + Integer.toHexString(c));
        }
      
        return unicode.toString();
    }*/

	public static String string2ascii(String str) throws Exception {
		// utf-8 转为 gbk
		// str = new String(str.getBytes("UTF-8"),"iso8859-1");
		// str = new String(str.getBytes("iso8859-1"),"GBK");
		// System.out.println(str);
		// int[] strInt = string2ASCII(str);
		// for (int i = 0; i < strInt.length; i++) {
		// System.out.print(strInt[i]);
		// }
		// System.out.println();
		return TdXOR.BytesToHexStr(str.getBytes("GBK"));
	}

	public static int[] string2ASCII(String s) {// 字符串转换为ASCII码
		if (s == null || "".equals(s)) {
			return null;
		}

		char[] chars = s.toCharArray();
		int[] asciiArray = new int[chars.length];

		for (int i = 0; i < chars.length; i++) {
			asciiArray[i] = char2ASCII(chars[i]);
		}
		return asciiArray;
	}

	public static int char2ASCII(char c) {
		return (int) c;
	}
	
	public static String XOR(String str1,String str2){
		return bytesToHexString(XOR(str2Bcd(str1),str2Bcd(str2))).toUpperCase();
	}
	public static byte[] XOR(byte[] bt1,byte[] bt2){
		byte[] bt = new byte[bt1.length];
		for (int i = 0; i < bt1.length; i++) {
			bt[i] = (byte) (bt1[i] ^ bt2[i]);
		}
		return bt;
	}
}
