package com.ancao.treasures.constant;

/**
 * @package: com.banlv.util
 * @date: 2015年03月25日 下午1:22
 * @author: lying ======================================================
 * @discretion: 常量
 * @version: 1.0.0 Copyright (c) 2015 ruyi, Ltd. All rights reserved
 *           ======================================================
 */

public interface APIConstant {
	/**
	 * @Description 常用数值
	 */
	public static final int NUMS[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21,
			22, 23, 24, 25, 25, 27, 28, 20, 30 };

	/**
	 * @Description 常用字符串值
	 */
	public static final String STRING[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };

	/**
	 * @Description DelFlg:逻辑删除 1:删除 0：正常
	 */
	public final static String DELFLAG_1 = "1";
	public final static String DELFLAG_0 = "0";
	/**
	 * @Description DrawType 1:银行卡，2:支付宝
	 */
	public final static String DRAWTYPE_1 = "1";
	public final static String DRAWTYPE_2 = "2";
	/**
	 * @Description AccountType:contact用户类型 1：医生，2：患者，3：群
	 *              1：医生-医生，2：医生-患者，3：医生群，4：患者-患者，5：医患群 6：患患群 8:公众账号
	 */
	public final static String ACCOUNTTYPE_1 = "1";
	public final static String ACCOUNTTYPE_2 = "2";
	public final static String ACCOUNTTYPE_3 = "3";
	public final static String ACCOUNTTYPE_4 = "4";
	public final static String ACCOUNTTYPE_5 = "5";
	public final static String ACCOUNTTYPE_6 = "6";
	public final static String ACCOUNTTYPE_8 = "8"; // 8:公众账号

	/**
	 * @Description Agree 是否同意好友请求 0：等待同意 ，1：已同意
	 */
	public final static String AGREE_0 = "0";
	public final static String AGREE_1 = "1";
	/**
	 * @Description ShowInContactList 是否显示在联系人列表 0：否 1：是
	 */
	public final static String SHOWINCONTACTLIST_0 = "0";
	public final static String SHOWINCONTACTLIST_1 = "1";
	/**
	 * @Description showinchatlist 是否显示在会话列表 0：否 1：是
	 */
	public final static String SHOWINCHATLIST_0 = "0";
	public final static String SHOWINCHATLIST_1 = "1";
	/**
	 * @Description `dontDisturb`'0 消息模式 1:勿扰模式',
	 */
	public final static String DONTDISTURB_0 = "0";
	public final static String DONTDISTURB_1 = "1";

	/**
	 * @Description `creator`是否是创建人-1：是，0：否',
	 */
	public final static String CREATOR_0 = "0";
	public final static String CREATOR_1 = "1";
	/**
	 * @Description 好友添加状态(0:接受好友请求,1:已添加,2:未添加，3:等待验证,4:忽略)
	 */
	public final static String ADDSTATUS_0 = "0";
	public final static String ADDSTATUS_1 = "1";
	public final static String ADDSTATUS_2 = "2";
	public final static String ADDSTATUS_3 = "3";
	public final static String ADDSTATUS_4 = "4";

	/**
	 * @Description `readStatus` '查阅状态 1：已查看，0：未查看',
	 */
	public final static String READSTATUS_0 = "0";
	public final static String READSTATUS_1 = "1";

	/**
	 * @Description 'RemarkStatus'群名称是否被修改过
	 */
	public final static String REMARKSTATUS_0 = "0";
	public final static String REMARKSTATUS_1 = "1";

	/**
	 * @Description 当前用户
	 */
	public final static String CURRENT_USER = "userInfo";
	/**
	 * @Description 异常结果
	 */
	public final static String EXCEPTION_RESULT = "exception_result";
	/**
	 * @Description 每页条数
	 */
	public final static int PAGE_SIZE = 10;
	/**
	 * @Description 歩长
	 */
	public final static int STEP_SIZE = 9;
	/**
	 * @Fields status_enable : 1 状态可用
	 */
	public final static String status_enable = "1";
	/**
	 * @Fields status_enable : 0 状态不可用
	 */
	public final static String STATUS_DISABLE = "0";
	/**
	 * @Field is_locked：0：冻结
	 */
	public final static String IS_LOCKED_DISABLE = "0";
	/**
	 * @Field is_locked：1：启用
	 */

	public final static String IS_LOCKED_ENABLE = "1";
	/**
	 * @Field is_del：0：删除
	 */
	public final static int IS_DEL_DISABLE = 0;
	/**
	 * @Field is_del：1：正常
	 */

	public final static int IS_DEL_ENABLE = 1;
	/**
	 * @Field 医生状态 1:待审核 2:审核通过 3:审核未通过
	 */
	public final static String DOCTOR_TYPE_1 = "1";

	public final static String DOCTOR_TYPE_2 = "2";

	public final static String DOCTOR_TYPE_3 = "3";
	/**
	 * @Field 是否需要主管审核 0:不需要主管审核 1：需要主管审核
	 */
	public final static String CHECK_TYPE_1 = "1";
	public final static String CHECK_TYPE_0 = "0";
	/**
	 * @Filed 拒绝原因：1：照片非本人 2:姓名不对 3:执照审核未通过 4：医院信息不对 5：地址信息不对 6：其他
	 * 
	 */
	public final static String REFUSAL_INFO_1 = "1";
	public final static String REFUSAL_INFO_2 = "2";
	public final static String REFUSAL_INFO_3 = "3";
	public final static String REFUSAL_INFO_4 = "4";
	public final static String REFUSAL_INFO_5 = "5";
	public final static String REFUSAL_INFO_6 = "6";
	public final static String REFUSAL_INFO_7 = "7";
	public final static String REFUSAL_INFO_1_RESULT = "照片非本人";
	public final static String REFUSAL_INFO_2_RESULT = "姓名不对";
	public final static String REFUSAL_INFO_3_RESULT = "执照审核未通过";
	public final static String REFUSAL_INFO_4_RESULT = "医院信息不对";
	public final static String REFUSAL_INFO_5_RESULT = "地址信息不对";
	public final static String REFUSAL_INFO_6_RESULT = "其他原因";
	public final static String REFUSAL_INFO_7_RESULT = "科室电话不对";

	/**
	 * 付款方式
	 */
	public final static String PAYMENT_METHOD_0 = "0"; // 付款方式-免费
	public final static String PAYMENT_METHOD_1 = "1"; // 付款方式-支付宝
	public final static String PAYMENT_METHOD_2 = "2"; // 付款方式-银联
	public final static String PAYMENT_METHOD_3 = "3"; // 付款方式-余额
	public final static String PAYMENT_METHOD_4 = "4"; // 付款方式-余额+支付宝
	public final static String PAYMENT_METHOD_5 = "5"; // 付款方式-余额+银联
	public final static String PAYMENT_METHOD_6 = "6"; // 分享红包

	/**
	 * 是否达标 0： 未达标 1： 达标
	 */
	public final static String IS_STANDARD_0 = "0";
	public final static String IS_STANDARD_1 = "1";

	/**
	 * 是否自定义医院 0：否 1：是
	 */
	public final static String IS_CUSTOM_0 = "0";
	public final static String IS_CUSTOM_1 = "1";

	/**
	 * 医生状态 '0'：待上传图片, 1：待验证 2：验证通过 3：验证不通过。
	 */
	public final static String AUTH_STATUS_0 = "0";
	public final static String AUTH_STATUS_1 = "1";
	public final static String AUTH_STATUS_2 = "2";
	public final static String AUTH_STATUS_3 = "3";

	/**
	 * @Description 排序方式 1:智能推荐(默认),2:今日义诊,3:三甲医院,4:评价最高,5:门诊最多,6:病人最多
	 */
	public final static String ORDERTYPE_1 = "1";
	public final static String ORDERTYPE_2 = "2";
	public final static String ORDERTYPE_3 = "3";
	public final static String ORDERTYPE_4 = "4";
	public final static String ORDERTYPE_5 = "5";
	public final static String ORDERTYPE_6 = "6";

	/**
	 * @Description 病程诊断类型 1:首诊，2:复诊，3:图文咨询，4：电话咨询
	 */
	public final static String DIAGNOSISTYPE_1 = "1";
	public final static String DIAGNOSISTYPE_2 = "2";
	public final static String DIAGNOSISTYPE_3 = "3";
	public final static String DIAGNOSISTYPE_4 = "4";

	/**
	 * @Description 医生服务状态 1：已开通，2：已购买，3：免费
	 */
	public final static String SERVICETYPE_1 = "1";
	public final static String SERVICETYPE_2 = "2";
	public final static String SERVICETYPE_3 = "3";

	/**
	 * @Description type类型
	 */
	public final static String TYPE_0 = "0";
	public final static String TYPE_1 = "1";
	public final static String TYPE_2 = "2";
	public final static String TYPE_3 = "3";
	public final static String TYPE_4 = "4";
	public final static String TYPE_5 = "5";
	public final static String TYPE_6 = "6";
	public final static String TYPE_7 = "7";

	/**
	 * @Description 通讯录数据用户类型
	 */
	public final static String PHONES_TYPE_DOCTOR = "1";
	public final static String PHONES_TYPE_PATIENT = "2";

	/**
	 * @Description 不限
	 */
	public final static String UNLIMITED = "不限";

	/**
	 * @Description 订单类型 0:免费 1：付费
	 */
	public final static String PAYORDERFLAG_0 = "0";
	public final static String PAYORDERFLAG_1 = "1";

	/**
	 * @Description 返回值消息显示类型 弹框
	 */
	public final static String MESSAGETYPE_0 = "popup";

	/**
	 * @Description reply消息类型 医生发送 doctor
	 */
	public final static String MSGTYPE_0 = "doctor";
	/**
	 * @Description reply消息类型 患者发送 patient
	 */
	public final static String MSGTYPE_1 = "patient";
	/**
	 * @Description reply消息类型 医生名片 businessCard
	 */
	public final static String MSGTYPE_2 = "businessCard";
	/**
	 * @Description reply消息类型 医生名片 businessCard2
	 */
	public final static String MSGTYPE_28 = "businessCard2";
	/**
	 * @Description reply消息类型 发送好友关注请求 sendFriendRequest
	 */
	public final static String MSGTYPE_3 = "sendFriendRequest";//
	/**
	 * @Description reply消息类型 接受好友关注请求 acceptedFriendRequest
	 */
	public final static String MSGTYPE_4 = "acceptedFriendRequest";//
	/**
	 * @Description reply消息类型 图文咨询订单结束消息 line
	 */
	public final static String MSGTYPE_5 = "line";//
	/**
	 * @Description reply消息类型 邀请开通图文咨询 openInvitation
	 */
	public final static String MSGTYPE_6 = "openInvitation";//
	/**
	 * @Description reply消息类型 邀请开通电话咨询 openPhoneConsult
	 */
	public final static String MSGTYPE_7 = "openPhoneConsult";//
	/**
	 * @Description reply消息类型 邀请开通私人医生 openSelfDoctor
	 */
	public final static String MSGTYPE_8 = "openSelfDoctor";//
	/**
	 * @Description reply消息类型 医生挂起图文咨询 pending
	 */
	public final static String MSGTYPE_9 = "pending";//
	/**
	 * @Description reply消息类型 购买图文咨询成功 buySuccess
	 */
	public final static String MSGTYPE_10 = "buySuccess";//
	/**
	 * @Description reply消息类型 购买电话咨询成功
	 */
	public final static String MSGTYPE_11 = "buyPhoneSuccess";//
	/**
	 * @Description reply消息类型 购买私人医生成功
	 */
	public final static String MSGTYPE_12 = "buySelfSuccess";//
	/**
	 * @Description reply消息类型 私人医生订单关闭提醒消息 endSelfDoctor
	 */
	public final static String MSGTYPE_13 = "endSelfDoctor";//
	/**
	 * @Description reply消息类型 系统文本消息 systemTextMsg
	 */
	public final static String MSGTYPE_14 = "systemTextMsg";//
	/**
	 * @Description reply消息类型 发起群聊 addContact
	 */
	public final static String MSGTYPE_15 = "addContact";//
	/**
	 * @Description reply消息类型 添加群联系人 addContactGroup
	 */
	public final static String MSGTYPE_16 = "addContactGroup";//
	/**
	 * @Description reply消息类型 删除群联系人及退群 delContactGroup
	 */
	public final static String MSGTYPE_17 = "delContactGroup";//
	/**
	 * @Description reply消息类型 群转发 groupQrCodeFw
	 */
	public final static String MSGTYPE_18 = "groupQrCodeFw";

	/**
	 * @Description reply消息类型 公众账号转发
	 */
	public final static String MSGTYPE_19 = "officialFw";

	/**
	 * @Description reply消息类型 链接文本
	 * 
	 *              msgType:linkText, context:xxxxx医药网xxxxxx, ex:
	 *              {type:'1',open:'0',url:'http://www.cba.com'}
	 *              {type:'2',event:
	 *              2,param1:xxx,param2:xxx,...},2:{type:'2',event
	 *              :2,param1:xxx,param2:xxx,...}
	 *              {type:'3',event:1,param1:xxx,param2
	 *              :xxx,...},2:{type:'2',event:3,param1:xxx,param2:xxx,...}
	 * 
	 *              链接的例子： 请点击<a href=
	 *              'ruyiApp://?json={type:'1',open:'0',url:'http://www.cba.com',param1:xxx,param2:x
	 *              x x , . . . . } ' > 医 药 网 < / a > ， 去 买 药 ! ! !
	 * 
	 *              事件的例子： 请点击<a href=
	 *              'ruyiApp://?json={type:'2',event:1,param1:xxx,param2:xxx,...
	 *              } ' > 加 好 友 < / a > ！ 请点击<a href=
	 *              'ruyiApp://?json={type:'2',event:2,param1:xxx,param2:xxx,...
	 *              } ' > 到 群 聊 < / a > ！
	 * 
	 *              弹框的例子： 请点击<a href=
	 *              'ruyiApp://?json={type:'3',event:1,param1:xxx,param2:xxx,...
	 *              } ' > 弹 框 1 < / a > 请点击<a href=
	 *              'ruyiApp://?json={type:'3',event:2,param1:xxx,param2:xxx,...
	 *              } ' > 弹 框 2 < / a >
	 * 
	 * 
	 * @description type:'1'.跳转指定url open:'0'APP内部打开,url:'' “小如最新推出“邀请赢现金活动”。
	 *              每邀请一名医生赢取20元现金奖励，每邀请一名患者赢取2元现金奖励。详情请参见邀请赢现金。”
	 * @description type:'2'.跳转至APP具体界面 event:1 医生服务价格设置界面
	 *              “小如根据各位医生大大的积极建议，现在已将私人医生服务调整成年费制，马上去设置吧！”
	 *              event:2：医生某一天的收入结算界面
	 *              “你邀请的医生用户,王志坚已经成功注册，现金奖励20元已经打入你账户，赶快去看看吧！” event:3：患者完善信息界面
	 *              event:4：医生开通服务界面 event:5：医生门诊处理界面 event:6：红包详情界面
	 *              event:7：“免费领取”药品捐赠 event:8：“散播爱心”药品捐赠 event:9：药品捐赠分享
	 */

	public final static String MSGTYPE_20 = "linkText";

	/**
	 * @Description reply消息类型 系统链接文本
	 * 
	 * @description 1.type:'2',event:3：“医生邀请你填写手机号，可享受拨打给你的免费电话 去填写”
	 * @description 2.type:'2',event:4：“XXX邀请您开通 门诊预约”
	 * @description 3.type:'2',event:5：“患者购买了您的门诊预约，请处理”
	 * @description 4.type:'2',event:6：“XXX领取了你的红包。”
	 * @description 5.type:'2',event:7：“免费领取”药品捐赠
	 * @description 6.type:'2',event:8：“散播爱心”药品捐赠
	 * @description 7.type:'1',event:9：药品捐赠分享
	 * 
	 */
	public final static String MSGTYPE_21 = "systemLinkText";

	/**
	 * @Description reply消息类型 红包
	 */
	public final static String MSGTYPE_22 = "luckyMoney";

	/**
	 * @Description reply消息类型 收红包系统消息
	 */
	public final static String MSGTYPE_23 = "sysLuckyMoney";

	/**
	 * @Description 门诊预约医生拒绝或者同意预约订单消息类型
	 */
	public final static String MSGTYPE_24 = "outpatientMsg";//
	/**
	 * 店铺
	 */
	public final static String MSGTYPE_25 = "itemFw";//
	/**
	 * 商品
	 */
	public final static String MSGTYPE_26 = "shopFw";//
	/**
	 * @Description reply消息类型 药物转发
	 */
	public final static String MSGTYPE_27 = "medicineMsg";

	/**
	 * REVIEW账号
	 */
	public final static String LOGIN_INREVIEW = "18918256986";

	/**
	 * 群头像：1:医生群、2:医患群、3:患医群、4:患患群
	 */
	public final static String GROUP_ICON_TYPE_1 = "avatar/groupIconUrl1.png";
	public final static String GROUP_ICON_TYPE_2 = "avatar/groupIconUrl2.png";
	public final static String GROUP_ICON_TYPE_3 = "avatar/groupIconUrl3.png";
	public final static String GROUP_ICON_TYPE_4 = "avatar/groupIconUrl4.png";

	/**
	 * 用户名字_默认值
	 */
	public final static String NICKNAME_DEFAULT = "匿名用户";

	/**
	 * 用户名字_分隔符:"、"
	 */
	public final static String SEPARATOR_NICKNAME = "、";

	/**
	 * 消息显示用户id_分隔符:","
	 */
	public final static String SEPARATOR_SEND = ",";

	/**
	 * 扫一扫类型：1:群信息含群成员数（去除已删除人数）
	 */
	public final static String SCAN_TYPE_GROUP_INFO = "1";

	/**************************** 字符串 *****************************************/

	/**
	 * 新闻
	 */
	public final static String STR_NEWS = "[新闻]";

	/**
	 * xiaoru
	 */
	public final static String STR_XIAORU = "xiaoru";

	/**
	 * 患者端免费问题name：等待医生回答
	 */
	public final static String STR_00001 = "等待医生回答";

	/**
	 * 新闻中心
	 */
	public final static String STR_00002 = "新闻中心";

	/**
	 * 私人助理小如
	 */
	public final static String STR_00003 = "私人助理小如";

	/**
	 * 导诊护士小如
	 */
	public final static String STR_00004 = "导诊护士小如";

	/**
	 * [名片]
	 */
	public final static String STR_00005 = "[名片]";

	/**
	 * 此消息只适于2.5.1以上版本显示，请先升级APP版本！
	 */
	public final static String STR_00006 = "此消息只适于2.5.1以上版本显示，请先升级APP版本！";

	/**
	 * 此消息只适于2.2以上版本显示，请先升级APP版本！
	 */
	public final static String STR_00007 = "此消息只适于2.2以上版本显示，请先升级APP版本！";

	/**
	 * 私人医生服务设置提醒
	 */
	public final static String STR_00008 = "小如根据各位医生大大的积极建议，现在已将私人医生服务调整成年费制，<a href='ruyiApp://?json={0}'>马上去设置吧</a>";

	/**
	 * 医生邀请用户注册奖励提醒
	 */
	// public final static String STR_00009 =
	// "小如最新推出“邀请赢现金活动”。每邀请一名医生赢取30元现金奖励，每邀请一名患者赢取5元现金奖励。详情请参见<a href='ruyiApp://?json={0}'>邀请赢现金</a>。";
	public final static String STR_00013 = "<a href='ruyiApp://?json={0}'>赶快去看看吧</a>";
	/**
	 * 医生邀请患者完善电话
	 */
	public final static String STR_00010 = "医生邀请你填写手机号，可享受拨打给你的免费电话 <a href='ruyiApp://?json={0}'>去填写</a>";

	/**
	 * 医生邀请你填写手机号
	 */
	public final static String STR_00011 = "医生邀请你填写手机号";

	/**
	 * 对方已填写手机号，你可以随时给他拨打免费电话了
	 */
	public final static String STR_00012 = "对方已填写手机号，你可以随时给他拨打免费电话了";

	/**
	 * 邀请您开通
	 */
	public final static String STR_00014 = "邀请您开通";

	/**
	 * 邀请开通图文咨询
	 */
	public final static String STR_00015 = "邀请您开通 <a href='ruyiApp://?json={0}'>图文咨询</a>";
	/**
	 * 邀请开通电话咨询
	 */
	public final static String STR_00016 = "邀请您开通 <a href='ruyiApp://?json={0}'>电话咨询</a>";
	/**
	 * 邀请开通私人医生
	 */
	public final static String STR_00017 = "邀请您开通 <a href='ruyiApp://?json={0}'>私人医生</a>";
	/**
	 * 邀请开通门诊预约
	 */
	public final static String STR_00018 = "邀请您开通 <a href='ruyiApp://?json={0}'>门诊预约</a>";
	/**
	 * 患者购买了您的门诊预约，请处理
	 */
	public final static String STR_00019 = "患者购买了您的门诊预约，<a href='ruyiApp://?json={0}'>请处理</a>";

	/**
	 * Android
	 */
	public final static String STR_00020 = "Android";

	/**
	 * 领取红包的系统消息
	 */
	public final static String STR_00021 = "{0}领取了{1}的红包，你的红包已被领完";

	/**
	 * 领取红包的系统消息
	 */
	public final static String STR_00022 = "{0}领取了{1}的红包";

	/**
	 * 领取红包的系统消息
	 */
	public final static String STR_00023 = "{0}领取了{1}的<a href='ruyiApp://?json={2}'>红包</a>，你的红包已被领完";

	/**
	 * 领取红包的系统消息
	 */
	public final static String STR_00024 = "{0}领取了{1}的<a href='ruyiApp://?json={2}'>红包</a>";

	/**
	 * 自定义标签名，用户自定义标签，暂无相关解释。
	 */
	public final static String STR_00025 = "用户自定义标签，暂无相关解释";

	/**
	 * 我已同意您的请求，请核实收货信息。点击分享，传递爱与希望！
	 */
	public final static String STR_00026 = "我已同意您的【{1}】求助请求，请核实收货信息。<a href='ruyiApp://?json={0}'>点击分享</a>，传递爱与希望！";

	/**
	 * xx捐赠了xx，快来免费领取吧！
	 */
	public final static String STR_00027 = "{0}，快来<a href='ruyiApp://?json={1}'>免费领取</a>吧！";

	/**
	 * xx想要免费领取xx，快来撒播爱心吧！
	 */
	public final static String STR_00028 = "{0}，快来<a href='ruyiApp://?json={1}'>撒播爱心</a>吧！";

	/**************************** 字符串 *****************************************/

	/**
	 * 发布类型 1:图文 2:链接
	 */
	public final static String MOMENTS_TYPE_1 = "1";

	/**
	 * 发布类型 1:图文 2:链接
	 */
	public final static String MOMENTS_TYPE_2 = "2";

	/**
	 * 发布/查看规则 0：所有人 1：仅好友 2:仅医生 3：仅患者
	 */
	public final static String ROLE_VIEW_BOTH = "0";

	/**
	 * 发布/查看规则 0：所有人 1：仅好友 2:仅医生 3：仅患者
	 */
	public final static String ROLE_VIEW_FRIEND = "1";

	/**
	 * 发布/查看规则 0：所有人 1：仅好友 2:仅医生 3：仅患者
	 */
	public final static String ROLE_VIEW_DOCOTR = "2";

	/**
	 * 发布/查看规则 0：所有人 1：仅好友 2:仅医生 3：仅患者
	 */
	public final static String ROLE_VIEW_PATIENT = "3";

	/**
	 * 体验账号登录标识
	 */
	public final static String EXPFLG = "1";

	/**
	 * @Description status状态
	 */
	public final static String STATUS_0 = "0";
	public final static String STATUS_1 = "1";
	public final static String STATUS_2 = "2";
	public final static String STATUS_3 = "3";
	public final static String STATUS_4 = "4";
	public final static String STATUS_5 = "5";

	/**
	 * @Description freeFlag 是否免费 0:否，1：是
	 */
	public final static String FREEFLAG_0 = "0";
	public final static String FREEFLAG_1 = "1";

	/**
	 * 性别 0:女 1：男
	 */
	public final static String GENDER_0 = "0";
	public final static String GENDER_1 = "1";
	public final static String GENDER_2 = "女";
	public final static String GENDER_3 = "男";

	/**
	 * 是否隐藏 0:否，1：是
	 */
	public final static String HIDEFLAG_0 = "0";
	public final static String HIDEFLAG_1 = "1";

	/**
	 * 导诊护士小如（患者端）
	 */
	public final static Long ACCOUNTID_XIAORU_PATIENT = -99l;

	/**
	 * 私人助理小如（医生端）
	 */
	public final static Long ACCOUNTID_XIAORU_DOCTOR = -99l;

	/**
	 * 公众号关注状态 0:未关注，1关注
	 */
	public final static String ATTENTIONSTATUS_0 = "0";
	public final static String ATTENTIONSTATUS_1 = "1";

	/**
	 * 新闻中心id
	 */
	public final static String NEWSCENTER_OFFICIALID = "-1";

}
