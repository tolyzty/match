package com.util.constants;

public interface Constants {
	
	/**时间分时秒**/
	public static final String DATEHHMMS = " 00:00:00";
	
	/**加入状态 ov:帐户余额不足,进入危险期标识**/
	public static final String JOIN_ZT_OV= "ov";
	
	
	/**项目类型 青年：03**/	
	public static final String MUTUAL_TYPE_ZN = "03";
	/**项目类型 老年：02**/	
	public static final String MUTUAL_TYPE_LN = "02";
	/**项目类型 儿童：01**/	
	public static final String MUTUAL_TYPE_ET = "01";
	/**
	 * 被推荐人
	 */
	public static final String ISLOCK_STATUS_BTJ = "2";
	
	/**
	 * 设置是推荐人
	 */
	public static final String ISLOCK_STATUS_SU= "1";
	/**
	 * 默认暂不是推荐人，也未加入
	 */
	public static final String ISLOCK_STATUS_NULL = "0";
	
	/**申请互助人状态-审核中**/
	public static final String CUST_STATUC_AUDIT = "0";
	/**申请互助人状态-审核成功**/
	public static final String CUST_STATUC_SCU = "1";
	
	
	
	
	/**
	 * 未支付
	 */
	public static final String PAY_ORDER_STATUS_UNTREATED = "0";
	/**
	 * 支付成功
	 */
	public static final String PAY_ORDER_STATUS_SUC = "1";
	/**
	 * 支付失败
	 */
	public static final String PAY_ORDER_STATUS_CANCEL = "2";
	/**
	 * 资金你不足
	 */
	public static final String PAY_ORDER_STATUS_FAILURE = "3";
	/**
	 * 失败啊
	 */
	public static final String PAY_ORDER_STATUS_IN_HAND = "4";
	
	/**项目类型：02青年版*/
	String MUTYPE_02 = "02";
	/**项目类型：03老年版*/
	String MUTYPE_03 = "03";
	/**项目类型：04儿童版*/
	String MUTYPE_04 = "04";
	/**项目类型：05意外伤害*/
	String MUTYPE_05 = "05";
	/**加入状态:1*/
	String JOINSTATUS_1 = "1";
	/**加入状态:0未加入*/
	String JOINSTATUS_0 = "0";
	/**会员级别：01观察期*/
	String JOINVIPZG_01 = "01";
	/**会员级别：02 会员期*/
	String JOINVIPZG_02 = "02";
	String JOINVIPZG_03 = "03";
	String JOINVIPZG_04 = "04";
	
	
	
	String CHL_PAY_TYPE_WEIXIN = "weixin";	
	/**提现订单状态 0:未处理*/
	String CAS_ORDER_STATUS_UNTREATED = "0";
	/**提现订单状态 1:成功*/
	String CAS_ORDER_STATUS_SUCCESS = "1";
	/**提现订单状态 2:已取消*/
	String CAS_ORDER_STATUS_CANCEL = "2";
	/**提现订单状态 3:失败*/
	String CAS_ORDER_STATUS_FAILURE = "3";
	/**提现订单状态 4:处理中*/
	String CAS_ORDER_STATUS_IN_HAND = "4";
	/**提现订单状态 5:部分成功*/
	String CAS_ORDER_STATUS_PART_SUCCESS = "5";
	
	/**交易订单状态 0:处理中*/
	String PRD_ORDER_STATUS_IN_HAND = "0";
	/**交易订单状态 1:成功*/
	String PRD_ORDER_STATUS_SUCCESS = "1";
	/**交易订单状态 2:失败*/
	String PRD_ORDER_STATUS_FAILURE = "2";
	/**交易订单状态 3:可疑*/
	String PRD_ORDER_STATUS_SUSPICIOUS = "3";
	
	/**
	 * 回调类型 01 同步返回
	 */
	String CALL_BACK_TYPE_SYN = "01";
	
	/**
	 * 回调类型 02 异步回调
	 */
	String CALL_BACK_TYPE_ASYN = "02";
	
	/**结算方式: 1-直接结算*/
	String CAS_WAY_DIRECTLY = "1";
	/**结算方式: 2-拆分多笔结算*/
	String CAS_WAY_SPLIT = "2";
	
	/**提现类型 00:T0提现*/
	String CAS_TYPE_T0 = "00";
	/**提现类型 01:T1提现*/
	String CAS_TYPE_T1 = "01";
	
	
	/**路由编号:创建订单获取支付信息数据*/
	String ROUTE_CODE_CREATE = "create";
	/**路由编号:支付*/
	String ROUTE_CODE_PAY = "pay";
	/**路由编号:支付结果查询*/
	String ROUTE_CODE_PRDORDQUERY = "prdOrdQuery";
	/**路由编号:提现*/
	String ROUTE_CODE_WITHDRAWAL = "withdrawal";
	/**路由编号:提现查询*/
	String ROUTE_CODE_WITHDRAWAL_QUERY = "withdrawalQuery";
	/**路由编号:余额查询*/
	String ROUTE_CODE_BALANCE_QUERY = "balanceQuery";
	
	/**银行卡账户类型 1: 对公账户*/
	String BANK_ACCOUNT_TYPE_IS_COMPAY = "1";
	/**银行卡账户类型 2: 对私个人账户*/
	String BANK_ACCOUNT_TYPE_PRIVATE = "2";
	
	/**银行卡类型 0:默认,根据平台往各个渠道的时候根据个个渠道自定义 */
	String CARD_TYPE_DEFAULT = "0";
	/**银行卡类型 1:借记卡*/
	String CARD_TYPE_DEBIT = "1";
	/**银行卡类型 2:信用卡*/
	String CARD_TYPE_CREDIT = "2";
	
	/**收款订单记账状态 00 未记账*/
	public final static String ACC_STATUS_UNTREATED = "00";
	/**收款订单记账状态 01 记账成功*/
	public final static String ACC_STATUS_SUCCESS = "01";
	/**收款订单记账状态 02 记账失败*/
	public final static String ACC_STATUS_FAILURE = "02";
	/**收款订单记账状态 03 已退款*/
	public final static String ACC_STATUS_REFUND = "03";
	/**收款订单记账状态 04 退款失败*/
	public final static String ACC_STATUS_REFUND_FAILURE = "04";
	
	/**机构记账类型 -1:不记账*/
	public static final String ORG_ACC_TYPE_NO_ACC = "-1";
	/**机构记账类型 00:总账户记账*/
	public static final String ORG_ACC_TYPE_TOTAL_ACCOUNT = "00";
	/**机构记账类型 00:渠道记账*/
	public static final String ORG_ACC_TYPE_CHL = "01";
	/**机构记账类型 01:商户记账*/
	public static final String ORG_ACC_TYPE_MER = "02";
	/**机构记账类型 02:终端记账*/
	public static final String ORG_ACC_TYPE_TER = "03";
	
	/**账户类型  01:总账户*/
	public static final String ACCOUNT_TYPE_TOTAL = "01";
	/**账户类型  02:子账户*/
	public static final String ACCOUNT_TYPE_SUB = "02";
	
	/**项目类型  01:抗癌版**/
	public static final String MUTUAL_TYPE_CNDY = "01";
	/**项目类型  02:青年版**/
	public static final String MUTUAL_TYPE_YEONG = "02";
	/**项目类型  03:老年版**/
	public static final String MUTUAL_TYPE_OLD = "03";
	/**项目类型  04:儿童版**/
	public static final String MUTUAL_TYPE_TONG = "04";
	/**项目类型  05:意外版**/
	public static final String MUTUAL_TYPE_YWAI = "05";
	
	
	/** 记账类型 1:增加(入账)*/
	String ACC_TYPE_ADD = "1";
	
	/** 记账类型 2:扣款(出账)*/
	String ACC_TYPE_SUB = "2";
	
	/**
	 * 账户余额变动类型 1001-收款
	 * 
	 */
	public static final String ACCOUT_CHANGE_TYPE_1001="1001";
	/**
	 * 账户余额变动类型 1002-其他虚拟账户转入
	 */
	public static final String ACCOUT_CHANGE_TYPE_1002="1002";
	/**
	 * 账户余额变动类型1003-退款(从虚拟账户中扣除原订单金额)
	 */
	public static final String ACCOUT_CHANGE_TYPE_1003="1003";
	/**
	 * 账户余额变动类型 1004-分润清算
	 */
	public static final String ACCOUT_CHANGE_TYPE_1004="1004";
	/**
	 * 账户余额变动类型 2001-提现
	 */
	public static final String ACCOUT_CHANGE_TYPE_2001="2001";
	/**
	 * 账户余额变动类型 2002-虚拟账户转出
	 */
	public static final String ACCOUT_CHANGE_TYPE_2002="2002";
	/**
	 * 账户余额变动类型 2003-虚拟账号支付
	 */
	public static final String ACCOUT_CHANGE_TYPE_2003="2003";
	
	/**
	 * 账户余额变动类型 2004-提现失败退回虚拟账户
	 */
	public static final String ACCOUT_CHANGE_TYPE_2004="2004";
	/**
	 * 账户余额变动类型 3001-T0审核退回
	 */
	public static final String ACCOUT_CHANGE_TYPE_3001="3001";
	/**
	 * 账户余额变动类型 3002-提现订单当天未清算退回或者提现失败
	 */
	public static final String ACCOUT_CHANGE_TYPE_3002="3002";
	
	/**
	 * 账户余额变动类型 3003-T1余额转T1Y
	 */
	public static final String ACCOUT_CHANGE_TYPE_3003="3003";
	
	/**
	 * 账户余额变动类型 3004-商户分润金额转T1
	 */
	public static final String ACCOUT_CHANGE_TYPE_3004="3004";
	
	/**订单类型1:卡类订单*/
	String ORDER_TYPE_CARD = "1";
	/**订单类型 2:网银订单*/
	String ORDER_TYPE_BANK = "2";
	/**订单类型 3:提现订单*/
	String ORDER_TYPE_CAS = "3";
	
	/** 路由类型 01:支付*/
	String RTR_TYPE_PAY = "01";
	/** 路由类型 02:支付结果查询*/
	String RTR_TYPE_PAY_QUERY = "02";
	/** 路由类型 11:提现*/
	String RTR_TYPE_CAS = "11";
	/** 路由类型 12:提现结果查询*/
	String RTR_TYPE_CAS_QUERY = "12";
	
	/**
	 * 费率类型 : 0000 全费率
	 */
	public static final String RATE_TYPE_ALL = "0000";
	
	/**
	 * 费率类型 : 0101 网关PC
	 */
	public static final String RATE_TYPE_GATEWAY_PC = "0101";
	
	/**
	 * 费率类型 : 0102 网关 WAP
	 */
	public static final String RATE_TYPE_GATEWAY_WAP = "0102";
	
	/**
	 * 费率类型 : 0201 微信扫码(跳转到收银台扫码)
	 */
	public static final String RATE_TYPE_WEIXIN_QR = "0201";
	
	/**
	 * 费率类型 : 0202 微信-API直连(返回二维码)
	 */
	public static final String RATE_TYPE_WEIXIN_API = "0202";
	
	/**
	 * 费率类型 : 0203 微信-app
	 */
	public static final String RATE_TYPE_WEIXIN_APP = "0203";
	
	/**
	 * 费率类型 : 0301 支付宝扫码
	 */
	public static final String RATE_TYPE_ZHIFUBAO_QR = "0301";
	
	/**
	 * 费率类型 : 0302 支付宝-API直连
	 */
	public static final String RATE_TYPE_ZHIFUBAO_API = "0302";
	
	/**
	 * 费率类型 : 0303 支付宝APP
	 */
	public static final String RATE_TYPE_ZHIFUBAO_APP = "0303";
	
	/**
	 * 费率类型 : 0501 qq钱包扫码
	 */
	public static final String RATE_TYPE_QQ_QR = "0501";
	/**
	 * 费率类型 : 0502 qq钱包直连
	 */
	public static final String RATE_TYPE_QQ_API = "0502";
	
	/**
	 * 费率类型 : 9999 提现
	 */
	public static final String RATE_TYPE_CAS = "9999";
	
	//回调状态，-1 不回调,0:未通知,1：成功 ,2：失败
	/** 回调状态，-1 不回调 */
	int CALL_STATUS_NO = -1;
	
	/** 回调状态，0:未通知 */
	int CALL_STATUS_UNTREATED = 0;
	
	/** 回调状态，1：成功  */
	int CALL_STATUS_SUCCESS = 1;
	
	/** 回调状态，2：失败 */
	int CALL_STATUS_FAILURE = 2;
	
	String API_SIGN_NAME = "sign";
	
	/**商户禁用状态 1:已禁用*/
	int USER_IS_LOCK_TRUE = 1;
	/**商户禁用状态 0:启用*/
	int USER_IS_LOCK_FALSE = 0;
	
	/** 请求数据类型2:表单提交 跳转到收银台*/
	public final static String OPEN_TYPE_FORM = "0";
	
	/** 请求数据类型 1:API接口 返回json*/
	public final static String OPEN_TYPE_API = "1";
	
	/**提现模式 0:根据交易订单提现*/
	public static final String CAS_MODEL_PRD = "0";
	/**提现模式 1:通过余额提现*/
	public static final String CAS_MODEL_BALANCE  = "1";
	
	/**
	 * 提现 账号获得方案 00 : 默认
	 */
	public static final String CAS_ACCOUNT_PLAN_DEFAULT = "00";
	
	/**数据格式 01:字符串*/
	public final static String DATA_TYPE_STRING = "01";
	/**数据格式 02:JSON*/
	public final static String DATA_TYPE_JSON = "02";
	/**数据格式 03:HTML*/
	public final static String DATA_TYPE_HTML = "03";
	
	public final static String REQ_KEY_SIGN_KEY = "signKey";
	
}
