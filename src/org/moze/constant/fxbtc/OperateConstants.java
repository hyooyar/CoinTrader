package org.moze.constant.fxbtc;

/*
 * 2. ����API
 * ����ʽ POST.�Դ�LOGIN_QUESTΪ·����ʹ��HTTPЭ�� POST���ݻ����ύJSON���ݼ��ɣ��ύ���������JSON���ش�������
 * ����
 * post data : op=get_token&username=test&password=test
 * json data : {"op":"get_token", "username":"test","password":"test"}
 * 
 */
public class OperateConstants {
	
	public static final long ILLEGAL_TOKEN = -3;
	public static final long NET_ERROR = -2;
	
	public static final String LOGIN_QUEST = "https://trade.fxbtc.com/api";
	
	//ʹ��POSTʱ����Ҫ�ı������
	public static final String OPERATION = "op";
	public static final String ID = "id";
	public static final String TOKEN = "token";
	
	/*
	 * 2-1. ��ȡ�û�ƾ֤(token)
	 * 
	 * �����б�
	 * ����		��ע
	 * op		get_token
	 * username	�û���
	 * password	�û�����
	 * ���ؽ����
	 * {
	 * 		"result":true,
	 * 		"token":"PU8JXNRHC452L8NBZ67BSLE9WK2B8H69JABRDBWR98SRHYA457J5GUGWNJXT5D9V",
	 * 		"timeout":1371633804
	 * }
	 * 
	 * token : �û���ʱƾ֤���κ��û���������Ҫ������ƾ֤���ܳɹ�������
	 * timeout : ƾ֤����ʱ��(UTCʱ��)��һ��ƾ֤��Ч��Ϊ24Сʱ��
	 */
	
	//op����
	public static final String GET_TOKEN = "get_token";
	//��op������б�����
	public static final String USER_NAME = "username";
	public static final String PASSWORD = "password";
	
	public static final String ERROR = "error";
	
	public static final String TOKEN_ERROR = "token_error";
	
	/*
	 * 2-2. ����token
	 * �����б�
	 * token	�û�ƾ֤
	 * op		check_token
	 * 
	 * ���ؽ����
	 * {
	 * 		"result":true
	 * }
	 * 
	 * result : token�Ƿ���Ч( true / false )
	 * 
	 */
	
	//op����
	public static final String CHECK_TOKEN = "check_token";
	
	/*
	 * 2-3. ��ȡ�û���Ϣ
	 * �����б�
	 * token	�û�ƾ֤
	 * op		get_info
	 * ���ؽ����
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
	 * ����							��ע
	 * info -> funds -> free		�����ʽ�
	 * info -> funds -> locked		�����ʽ�( �ҵ������� )
	 * info -> status -> trade		�Ƿ�������( true / false )
	 * info ->status -> server_time	������ʱ��( UTC )
	 */
	public static final String GET_INFO = "get_info";
	
	/*
	 * 2-4. ��ȡ�û���ǰ�ҵ�
	 * �����б�
	 * token	�û�ƾ֤
	 * op	get_orders
	 * symbol	��ǰ���Ҷ�(btc_cny,ltc_cny,ltc_btc)
	 * ���ؽ����
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
	 * ����	��ע
	 * orders -> id		������
	 * orders -> type	�ҵ�����(ask/bid[����/��])
	 * orders -> rate	�һ��۸�
	 * orders -> vol	�һ���
	 * orders -> date	�ҵ�����
	 */

	public static final String GET_ORDERS = "get_orders";
	
	/* 
	 * 2-5. �����ҵ�
	 * �����б�
	 * token	�û�ƾ֤
	 * op		cancel_order
	 * symbol	��ǰ���Ҷ�(btc_cny,ltc_cny,ltc_btc)
	 * id		������
	 * 
	 * ���ؽ����
	 * {
	 * 		"result":true,
	 * 		"id":1357571743002
	 * }
	 */

	public static final String CANCEL_ORDER = "cancel_order";
	
	/*
	 * 2-6. ��������
	 * �����б�
	 * token	�û�ƾ֤
	 * op		trade
	 * symbol	��ǰ���Ҷ�(btc_cny,ltc_cny,ltc_btc)
	 * type	��������(buy/sell)
	 * rate	��߼ۣ����룩/��ͼۣ�������
	 * vol	����������
	 * ���ؽ����
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
	 * ����	��ע
	 * trade_info -> traded_orders	�ѳɽ�����
	 * trade_info -> limit_orders	�޼۹ҵ�����
	 * * Ϊ�˱����Զ�����Ӱ����ͨ�û����ף�����һЩ�Զ�������������
	 * -- rate �ҵ��۸���Чλ�� BTC / XXX 0.01, LTC / XXX 0.0001
	 * -- vol ��С�ҵ���Ϊ BTC / XXX 0.02, LTC / XXX 0.5
	 */

	public static final String TRADE = "trade";
	
	public static final String TYPE = "type";

	public static final String RATE = "rate";
	
	public static final String VOL = "vol";
	
}
