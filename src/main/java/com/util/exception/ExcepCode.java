package com.util.exception;
/**
 * 错误代码定义
 * @author xiejinzhong
 *</br>
 *90 系统错误码</br>
 *88 超时错误吗</br>
 *
 * 00 APP 模块</br>
 * 
 *   01  登录业务</br>
 *   02  商户业务</br>
 *   03  终端业务（绑定、设备列表、设备状态）</br>
 *   04 验证码业务</br>
 *   05 合作商业务 </br>
 *   06 提现业务 T+0 T+1</br>
 *   07 APP版本</br>
 *   08 交易异常业务（交易失败、风控不通过...）</br>
 *   09 路由业务</br>
 *   10 其他(电子签名...)      </br>
 *   11 短信业务</br>
 * 
 * 10   通讯渠道模块</br>
 *   01卡业务</br>
 *   02加密机业务 </br>
 *   03收单渠道业务</br>
 *   04快捷渠道业务</br>
 *   05便民业务</br>
 *   
 * </br>
 * 
 * 
 */
public class ExcepCode {
	//系统错误码
	/**
	 * 拦截器不通过
	 */
	public static final String EX999999 = "999999"; //
	/**
	 * 参数校验失败(为空、错值提示)
	 */
	public static final String EX900000 = "900000"; //字段不能为空
	
	/**
	 * 系统异常
	 */
	public static final String EX900001 = "900001"; //系统异常
	/**
	 * 获取配置信息错误
	 */
	public static final String EX900002 = "900002"; //
	/**
	 * 参数校验错误
	 */
	public static final String EX900003 = "900003"; //
	/**
	 * 未定义错误消息
	 */
	public static final String EX900004 = "900004"; //
	/**
	 * 数字签名校验错误
	 */
	public static final String EX900005 = "900005"; 
	/**
	 * APP客户端连接超时
	 */
	public static final String EX888888= "888888"; 
	/**
	 * APP客户端异地登陆
	 */
	public static final String EX888889= "888889"; 

	
	//mpapp业务错误码
	/**
	 * 登陆错误次数超限，商户被冻结
	 */
	public static final String EX000101 = "000101"; 
	/**
	 * 密码错误!
	 */
	public static final String EX000102 = "000102"; 
	/**
	 * 登录失败：更新登陆信息异常！
	 */
	public static final String EX000103 = "000103"; 
	
	
	/**
	 * 商户注册失败：1.商户注册异常! 2.商户注册失败!
	 */
	public static final String EX000201 = "000201"; 
	/**
	 * 商户已被注册！
	 */
	public static final String EX000202 = "000202"; 
	/**
	 * 商户信息修改失败：1.商户修改或找回密码异常! 2.商户修改或找回密码失败 3.该密码类型不存在  4.实名认证更新失败
	 */
	public static final String EX000205 = "000205"; 
	/**
	 * 1.商户校验异常！ 2.商户不存在
	 */
	public static final String EX000206 = "000206"; //
	/**
	 * 1.商户查询失败
	 */
	public static final String EX000207= "000207"; //
	/**
	 * 1.商户账户查询失败 2、商户账户更新失败 3、商户账户新增失败
	 */
	public static final String EX000208= "000208"; //
	/**
	 * 1、商户账户查询异常 2、商户账户更新异常 3、商户账户新增异常
	 */
	public static final String EX000212= "000212"; //
	/**
	 * 1.商户账户历史查询失败 2、商户账户历史更新失败 3、商户账户历史新增失败
	 */
	public static final String EX000213= "000213"; //
	/**
	 * 1、商户账户历史查询异常 2、商户账户历史更新异常 3、商户账户历史新增异常
	 */
	public static final String EX000214= "000214"; //
	/**
	 * 商户已被禁用
	 */
	public static final String EX000215= "000215"; //
	/**
	 * 绑定银行卡信息失败 : 1.失败和 银行卡信息查询异常 2.银行卡信息修改异常 3. 银行卡信息解绑异常
	 */
	public static final String EX000209 = "000209"; //
	/**
	 * 信息已在审核中：   银行卡信息已被编辑更新，请等待审核后修改
	 */
	public static final String EX000210 = "000210"; //
	/**
	 * 卡号不存在
	 */
	public static final String EX000211 = "000211"; //
	
	/**
	 * 请上传银行卡信息后再交易。
	 */
	public static final String EX000230 = "000230"; //
	/**
	 * 银行卡正在审核中,不能进行交易。
	 */
	public static final String EX000231 = "000231"; //
	
	/**
	 * 未通过实名认证,不能进行交易。
	 */
	public static final String EX000232 = "000232"; //
	
	
	//以下九个是银行卡实名认证相关错误信息
	
	/**
	 * 请先通过银行卡实名认证再交易
	 */
	public static final String EX000240 = "000240"; //
	/**
	 * 银行卡已通过实名认证，无需再次操作
	 */
	public static final String EX000241 = "000241"; //
	
	/**
	 * 银行卡未通过实名认证，不能进行交易
	 */
	public static final String EX000242 = "000242"; //
	
	
	
	/**
	 * 银行卡实名认证通信异常
	 */
	public static final String EX000243 = "000243"; //
	/**
	 * 银行卡实名认证业务异常
	 */
	public static final String EX000244 = "000244"; //
	
	/**
	 * 银行卡实名认证失败
	 */
	public static final String EX000245 = "000245"; //
	
	/**
	 * 
	 * 银行卡实名认证列表查询异常
	 */
	public static final String EX000246 = "000246";
	
	/**
	 * 银行卡实名认证状态查询异常
	 */
	public static final String EX000247 = "000247";
	
	
	/**
	 * 实名认证卡删除异常
	 */
	public static final String EX000248 = "000248";
	
	/**
	 * 卡号信息错误，请重新输入
	 */
	public static final String EX000249 = "000249";
	
	
	
	
	/**
	 * 银行卡未绑定或不存在
	 */
	public static final String EX000217 = "000217"; //
	/**
	 * 银行卡已被商户绑定
	 */
	public static final String EX000222 = "000222"; //
	/**
	 * 银行卡类型限定为借记卡
	 */
	public static final String EX000223= "000223"; //
	/**
	 * 联行号异常：1联行号列表获取异常，2银行查询异常
	 */
	public static final String EX000224= "000224"; //
	/**
	 * 银行名查询失败
	 */
	public static final String EX000225= "000225"; //
	/**
	 * 请先实名认证再绑定银行卡
	 */
	public static final String EX000226 = "000226"; //
	/**
	 * 余额不足
	 */
	public static final String EX000218 = "000218"; //
	/**
	 * 商户账户不存在
	 */
	public static final String EX000219 = "000219"; //
	
	/**
	 * 不支持该卡片
	 */
	public static final String EX000220 = "000220"; //
	
	/**
	 * 获取信息失败: 获取省信息失败  获取市信息失败 ...
	 */
	public static final String EX001008 = "001008"; //
	
	
	/**
	 * 获取验证码失败
	 */
	public static final String EX000411 = "000411"; //
	/**
	 * 验证码验证失败
	 */
	public static final String EX000412 = "000412"; //
	
	/********************************合作商业务  start************************************/
	/**
	 * 合作商查询异常
	 */
	public static final String EX000500="000500";
	/**
	 * 合作商已被禁用
	 */
	public static final String EX000501="000501";
	/**
	 * 合作商已被冻结
	 */
	public static final String EX000502="000502";	
	/**
	 * 合作商不存在
	 */
	public static final String EX000503="000503";
	/********************************合作商业务  end************************************/
	/**
	 * 业务不可用 ： 路由已停用 ，查询路由信息异常（终端或快捷或便民）
	 */
	public static final String EX000913 = "000913"; //
	
	
	/**
	 * 终端不可用(查无数据，未绑定终端，终端信息状态校验失败)
	 */
	public static final String EX000301 = "000301";
	/**
	 * 终端绑定失败(更新)
	 */
	public static final String EX000303 = "000303";//添加详细描述
	
	/**
	 * 终端工作密钥更新失败
	 */
	public static final String EX000304 = "000304";
	
	/**
	 * 查询app版本信息失败
	 */
	public static final String EX000701 = "000701";//
	
	
	/**
	 * 交易失败
	 */
	public static final String EX000801 = "000801";
	
	/**
	 * 单笔消费金额必须在{}~{}之间
	 */
	public static final String EX000811 = "000811";
	/**
	 * 单卡单日消费不得超过{}次
	 */
	public static final String EX000812 = "000812";
	
	/**
	 * {}累计收款金额不得超过{}元
	 */
	public static final String EX000813 = "000813";
	
	/**
	 * 交易流水业务异常
	 */
	public static final String EX000814 = "000814";
	/**
	 * 交易信息新增失败
	 */
	public static final String EX000815 = "000815";
	/**
	 * 获取账户余额异常
	 */
	public static final String EX000816 = "000816";
	/**
	 * 获取账户余额失败
	 */
	public static final String EX000817= "000817";
	
	//其它业务-----------------------------------------------------------
	/**
	 * 文件存储失败!
	 */
	public static final String EX001001 = "001001";
	
	/**
	 * 卡BIN信息获取失败！
	 */
	public static final String EX001002 = "001002";
	
	/**
	 * 电子签名上传失败！
	 */
	public static final String EX001003 = "001003";
	
	/**
	 * 未找到电子签名记录！
	 */
	public static final String EX001004 = "001004";
	
	/**
	 * 轮播图获取失败！
	 */
	public static final String EX001005 = "001005";
	//短信业务----------------------------------------------------------
	/**
	 * 短信验证码获取失败！
	 */
	public static final String EX001101 = "001101";
	
	/**
	 * 短信校验码错误！
	 */
	public static final String EX001102 = "001102";
	
	/**
	 * 短信校验码已过期！
	 */
	public static final String EX001103 = "001103";
	
	/**
	 * 短信发送模版获取失败！
	 */
	public static final String EX001104 = "001104";
	
	/**
	 * 短信发送失败！
	 */
	public static final String EX001105 = "001105";
	
	/**
	 * 短信发送记录保存失败！
	 */
	public static final String EX001106 = "001106";
	
	/**
	 * 更新短信发送状态失败！
	 */
	public static final String EX001107 = "001107";


	
	//mpaychl业务错误代码--------------------------------------------------
	/**
	 * 磁道数据错误。
	 */
	public static final String EX100101 = "100101"; //
	
	/**
	 * 计算MAC失败
	 */
	public static final String EX100201 = "100201"; //
	/**
	 * 转加密PIN失败
	 */
	public static final String EX100202 = "100202"; //
	/**
	 * 生成密钥失败（终端主密钥、工作密钥、MACKEY转本地主密钥失败、PINKEY转本地主密钥失败）
	 */
	public static final String EX100203 = "100203"; //
	
	/**
	 * 密钥分算失败
	 */
	public static final String EX100208 = "100208"; //
	/**
	 * 数据解密失败
	 */
	public static final String EX100209 = "100209"; //
	
	/**
	 * 通道方签到失败
	 */
	public static final String EX100205 = "100205"; //
	
	/**
	 * 交易失败(发送第三方异常)
	 */
	public static final String EX100301 = "100301"; 
	/**
	 * 交易超时  接收第三方系统数据超时
	 */
	public static final String EX100302 = "100302"; 
	
	
	
	
	/**
	 * 交易失败(快捷发送第三方失败)
	 */
	public static final String EX100401 = "100401";
	/**
	 * 交易超时(快捷发送第三方连接失败)
	 */
	public static final String EX100402 = "100402";
	
	/**
	 * 交易失败(便民业务发送失败)
	 */
	public static final String EX100501 = "100501"; //
	/**
	 * 交易失败(便民业务服务器连接失败)
	 */
	public static final String EX100502 = "100502"; //
	
	
	
	
	
	/**
	 * 终端业务 终端不可用 
	 */
	public static final String EX030001 = "030001"; 
	
	/**
	 * 终端业务 不能跨合作商绑定终端  
	 */
	public static final String EX030002 = "030002";
	
	/**
	 * 终端业务 终端绑定成功，不需重复提交
	 */
	public static final String EX030003 = "030003";
	/**
	 * 终端业务 终端绑定失败，请重试。
	 */
	public static final String EX030004 = "030004";
	
	/**
	 * 终端业务 终端查询异常
	 */
	public static final String EX030005 = "030005";
	/**
	 * 终端业务 终端不能跨合作商绑定
	 */
	public static final String EX030006 = "030006";

	/**
	 * 商品订单不存在
	 */
	public static final String EX080001 = "080001";

	/**
	 * 支付金额与商品订单金额不一致
	 */
	public static final String EX080002 = "080002";
	
	/**
	 * 创建支付订单失败
	 */
	public static final String EX080003 = "080003";
	/**
	 * 商品订单已支付,请重新下单
	 */
	public static final String EX080004 = "080004";
	
	/**
	 * 商品订单未支付,不能进行退货处理。
	 */
	public static final String EX080014 = "080014";
	/**
	 * 支付密码错误
	 */
	public static final String EX080005 = "080005";
	/**
	 * 商品订单处理中
	 */
	public static final String EX080006 = "080006";
	
	/**
	 * 提现订单异常
	 */
	public static final String EX060000 = "060000";
	/**
	 * 提现订单创建失败
	 */
	public static final String EX060001 = "060001";
	/**
	 * 费率查询失败 异常
	 */
	public static final String EX060002 = "060002";
	
	//2015/04/25 添加
	/**
	 * 通道单笔消费金额必须在{}~{}之间
	 */
	public static final String EX000821 = "000821";
	/**
	 * 通道单日消费不得超过{}元
	 */
	public static final String EX000822 = "000822";
	/**
	 * 通道单月消费不得超过{}元
	 */
	public static final String EX000823 = "000823";
	
	/**
	 * 通道限额检查异常
	 */
	public static final String EX000825 = "000825";
	/**
	 * 通道限额添加异常
	 */
	public static final String EX000826 = "000826";
	
	/**
	 * 无效推荐人
	 */
	public static final String EX000221 = "000221";
	
	/**
	 * 无效金额
	 */
	public static final String EX200313 = "200313"; 
	/**
	 * 无效卡号
	 */
	public static final String EX200314 = "200314"; 
	/**
	 * 无效应答
	 */
	public static final String EX200320 = "200320"; 
	/**
	 * 作弊卡
	 */
	public static final String EX200334 = "200334"; 
	/**
	 * 无效交易
	 */
	public static final String EX200345 = "200345"; 
	/**
	 * 密码错误次数超限
	 */
	public static final String EX200338 = "200338"; 
	/**
	 * 发卡方不支持的交易
	 */
	public static final String EX200340 = "200340"; 
	/**
	 * 此卡被没收
	 */
	public static final String EX200343 = "200343"; 
	/**
	 * 资金不足（可用余额不足）
	 */
	public static final String EX200351 = "200351"; 
	/**
	 * 该卡已过期
	 */
	public static final String EX200354 = "200354"; 
	/**
	 * 密码错误
	 */
	public static final String EX200355 = "200355"; 
	/**
	 * 卡片校验错误
	 */
	public static final String EX200359 = "200359"; 
	/**
	 * 交易金额超限
	 */
	public static final String EX200361 = "200361"; 
	/**
	 * 受限制的卡
	 */
	public static final String EX200362 = "200362"; 
	/**
	 * 超出取款次数限制
	 */
	public static final String EX200365 = "200365"; 
	/**
	 * 交易超时,请重试
	 */
	public static final String EX200368 = "200368"; 
	/**
	 * 密码错误次数超限
	 */
	public static final String EX200375 = "200375"; 
	/**
	 * 发卡方超时
	 */
	public static final String EX200398 = "200398"; 
	/**
	 * PIN格式错误,请重新签到
	 */
	public static final String EX200399 = "200399"; 
	/**
	 * MAC错误
	 */
	public static final String EX2003A0 = "2003A0"; 
	
	/**
	 * 错误日志新增异常
	 */
	public static final String EX100500 = "100500"; //
	

	/**
	 * 终端未设定刷卡费率.
	 */
	public static final String EX030007 = "030007";
	
	/**
	 * 提现时间段为早上{}点至下午{}点。
	 */

	public static final String EX000235 = "000235"; //
	/**
	 * 该收款订单已提现！
	 */

	public static final String EX000236 = "000236"; //
	/**
	 * 获取提现时间失败！
	 */
	public static final String EX060003 = "060003";
	
	/**
	 * 黑名单卡
	 */
	public static final String EX000898 = "000898"; //

	/**
	 * 交易限额不通过。
	 */
	public static final String EX000899 = "000899"; //
	
	
	/**
	 * 无效金额
	 */
	public static final String EX000800 = "000800"; 
	
	/**
	 * 金额超限（超过系统最大处理金额）
	 */
	public static final String EX000802 = "000802"; 
	
	/**
	 * 余额账户可存储资金超限
	 */
	public static final String EX000803 = "000803"; 
	
	
	/**
	 * 单笔交易金额不能小于{}元。
	 */
	public static final String EX000851 = "000851"; 
	
	/**
	 * 单笔交易金额不能大于{}元。
	 */
	public static final String EX000852 = "000852"; //
	
	/**
	 * 日交易金额不能大于{}元。
	 */
	public static final String EX000853 = "000853"; //
	/**
	 * 日交易次数不能超过{}笔。
	 */
	public static final String EX000854 = "000854"; //
	/**
	 * 月交易金额不能大于{}元。
	 */
	public static final String EX000855 = "000855"; //
	
	/**
	 * 月交易次数不能超过{}笔。
	 */
	public static final String EX000856 = "000856"; //

	
	/**
	 * 单笔提现金额不能小于{}元。
	 */
	public static final String EX000857 = "000857"; 
	
	/**
	 * 单笔提现金额不能大于{}元。
	 */
	public static final String EX000858 = "000858"; //
	
	/**
	 * 日提现金额不能大于{}元。
	 */
	public static final String EX000859 = "000859"; //
	/**
	 * 日提现次数不能超过{}笔。
	 */
	public static final String EX000860 = "000860"; //
	
	/**
	 * 未绑定合作商(未激活)
	 */
	public static final String EX000861 = "000861";
}
