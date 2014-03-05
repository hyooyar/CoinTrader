package org.moze.constant.fxbtc;

/*
 * 2. 交易API
 * 请求方式 POST.以此LOGIN_QUEST为路径，使用HTTP协议 POST数据或者提交JSON数据即可，提交后服务器以JSON返回处理结果。
 * 例：
 * post data : op=get_token&username=test&password=test
 * json data : {"op":"get_token", "username":"test","password":"test"}
 * 
 */
public class OperateConstants {
	
	public static final long ILLEGAL_TOKEN = -3;
	public static final long NET_ERROR = -2;
	
	public static final String LOGIN_QUEST = "https://trade.fxbtc.com/api";
	
	//使用POST时所需要的必须参数
	public static final String OPERATION = "op";
	public static final String ID = "id";
	public static final String TOKEN = "token";
	
	/*
	 * 2-1. 获取用户凭证(token)
	 * 
	 * 参数列表：
	 * 名称		备注
	 * op		get_token
	 * username	用户名
	 * password	用户密码
	 * 返回结果：
	 * {
	 * 		"result":true,
	 * 		"token":"PU8JXNRHC452L8NBZ67BSLE9WK2B8H69JABRDBWR98SRHYA457J5GUGWNJXT5D9V",
	 * 		"timeout":1371633804
	 * }
	 * 
	 * token : 用户临时凭证，任何用户操作都需要附带此凭证才能成功操作。
	 * timeout : 凭证过期时间(UTC时间)，一般凭证有效期为24小时。
	 */
	
	//op参数
	public static final String GET_TOKEN = "get_token";
	//除op外参数列表名称
	public static final String USER_NAME = "username";
	public static final String PASSWORD = "password";
	
	public static final String ERROR = "error";
	
	public static final String TOKEN_ERROR = "token_error";
	
	/*
	 * 2-2. 测试token
	 * 参数列表：
	 * token	用户凭证
	 * op		check_token
	 * 
	 * 返回结果：
	 * {
	 * 		"result":true
	 * }
	 * 
	 * result : token是否有效( true / false )
	 * 
	 */
	
	//op参数
	public static final String CHECK_TOKEN = "check_token";
	
	/*
	 * 2-3. 获取用户信息
	 * 参数列表：
	 * token	用户凭证
	 * op		get_info
	 * 返回结果：
	 * {
	 * 		"result":true,
	 * 		"info":{
	 * 			"funds":{
	 * 				"free":{
	 * 					"cny":"166.14273500",
	 * 					"btc":"5.45168404",
	 * 					"ltc":"23.74322960"
	 * 				},
	 * 				"locked":{
	 * 					"cny":"500.83818499",
	 * 					"btc":"1.22457200",
	 * 					"ltc":"11.85418799"
	 * 				}
	 * 			},
	 * 			"status":{
	 * 				"trade":true,
	 * 				"server_time":1371549835
	 * 			}
	 * 		}
	 * }
	 * 名称							备注
	 * info -> funds -> free		可用资金
	 * info -> funds -> locked		锁定资金( 挂单后锁定 )
	 * info -> status -> trade		是否允许交易( true / false )
	 * info ->status -> server_time	服务器时间( UTC )
	 */
	public static final String GET_INFO = "get_info";
	
	/*
	 * 2-4. 获取用户当前挂单
	 * 参数列表：
	 * token	用户凭证
	 * op	get_orders
	 * symbol	当前货币兑(btc_cny,ltc_cny,ltc_btc)
	 * 返回结果：
	 * {
	 * 		"result":true,
	 * 		"symbol":"btc_cny",
	 * 		"orders":[
	 * 		{
	 * 			"id":1357571743002,
	 * 			"type":"bid",
	 * 			"rate":80,
	 * 			"vol":0.30339321,
	 * 			"date":1357574443
	 * 		},
	 * 		{
	 * 			"id":1357571743004,
	 * 			"type":"ask",
	 * 			"rate":9999,
	 * 			"vol":1,
	 * 			"date":1357574556
	 * 		}
	 * 		]
	 * }
	 * 名称	备注
	 * orders -> id		订单号
	 * orders -> type	挂单类型(ask/bid[卖单/买单])
	 * orders -> rate	兑换价格
	 * orders -> vol	兑换量
	 * orders -> date	挂单日期
	 */

	public static final String GET_ORDERS = "get_orders";
	
	/* 
	 * 2-5. 撤销挂单
	 * 参数列表：
	 * token	用户凭证
	 * op		cancel_order
	 * symbol	当前货币兑(btc_cny,ltc_cny,ltc_btc)
	 * id		订单号
	 * 
	 * 返回结果：
	 * {
	 * 		"result":true,
	 * 		"id":1357571743002
	 * }
	 */

	public static final String CANCEL_ORDER = "cancel_order";
	
	/*
	 * 2-6. 交易命令
	 * 参数列表：
	 * token	用户凭证
	 * op		trade
	 * symbol	当前货币兑(btc_cny,ltc_cny,ltc_btc)
	 * type	买卖类型(buy/sell)
	 * rate	最高价（买入）/最低价（卖出）
	 * vol	买入卖出量
	 * 返回结果：
	 * {
	 * 		"result" : true,
	 * 		"symbol" : "btc_cny",
	 * 		"type" : "buy",
	 * 		"trade_info" : {
	 * 			"traded_orders" : [
	 * 				{
	 * 					"rate" : 100,
	 * 					"vol" : 5
	 * 				}
	 * 			],
	 * 			"limit_orders" : [
	 * 				{
	 * 					"id" : 1357571743002,
	 * 					"rate" : 101,
	 * 					"vol" : 6.66
	 * 				}
	 * 			]
	 * 		}
	 * }
	 * 名称	备注
	 * trade_info -> traded_orders	已成交单据
	 * trade_info -> limit_orders	限价挂单单据
	 * * 为了避免自动交易影响普通用户交易，加入一些自动交易限制如下
	 * -- rate 挂单价格有效位数 BTC / XXX 0.01, LTC / XXX 0.0001
	 * -- vol 最小挂单量为 BTC / XXX 0.02, LTC / XXX 0.5
	 */

	public static final String TRADE = "trade";
	
	public static final String TYPE = "type";

	public static final String RATE = "rate";
	
	public static final String VOL = "vol";
	
}
