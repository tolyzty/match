package com.match.quqrtz;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.match.service.DBJoinService;
import com.match.service.DBMutualService;
import com.sun.org.apache.bcel.internal.generic.IREM;
import com.util.DateUtils;
import com.util.constants.Constants;

@Service
@Component
@Lazy(false)
public class QuqrtzPayBackCall {
	
	private final static Logger log = LoggerFactory.getLogger(QuqrtzPayBackCall.class);
	
	@Autowired
	private DBJoinService dbJoinService;
	@Autowired
	private DBMutualService dbMutualService;
	private static boolean con = true;
	 /** 
     * cron表达式：* * * * * *（共6位，使用空格隔开，具体如下） 
     * cron表达式：*(秒0-59) *(分钟0-59) *(小时0-23) *(日期1-31) *(月份1-12或是JAN-DEC) *(星期1-7或是SUN-SAT) 
     */   
    /** 定时。每天凌晨 01:25 执行一次  **/  
	//@Scheduled(cron = "0 25 01 * * ?") //每天凌晨1点25分触发 
	 //@Scheduled(cron = "*/45 * * * * ?")
	//@Scheduled(cron = "0 0/2 * * * ?")
	//@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void execute()throws Exception{
		if (con) {
			try {
				int u = updateNoBal();
				if (u==0) {
					log.info("所有加入帐户修改完成,无修改数据");
				}else{
					log.info("修改加入帐户成功,修改数:[{}]",u);
				}
			} catch (Exception e) {
				log.info("修改会员和修改危险期内容");
				e.printStackTrace();
			}
			con=false;
		}
		
		
	}
	
	/**
	 * 执行修改批量操作,如果帐户金额小于项目最小金额,修改状态ov,设置危险期到期时间 cisis_time,时间为30天
	 * 
	 **/
	public int updateNoBal()throws Exception{
	 Map<String, Object> param = new HashMap<String, Object>();
		param.put("mutualType",Constants.MUTUAL_TYPE_YEONG);
		String BeTime = DateUtils.getCurrentDateTime2();//获取当前时间
		String Format = "yyyy-MM-dd";//时间格式
		String StartTime = DateUtils.dateAddDay(BeTime, Format, 30);//日期相减
		StartTime =  StartTime+Constants.DATEHHMMS;
		log.info("获取开始时间[{}]",StartTime);
		int  u = 0;
		try {
			//修改当天帐户金额小于3元的,修改字段joinzt 和  危险期时间
			Map<String, Object> muMap = dbMutualService.findMutual(param);
			log.info("根据项目类型获取参数:[{}]",muMap);
			String zxamt = muMap.get("mutualZxamt").toString();
			log.info("帐户最少金额:[{}]",zxamt);
			param.put("joinAmt", zxamt);
			param.put("joinCrisisTime", StartTime);
			param.put("joinZt", "ov");
			log.info("批量修改所有余额不足:[{}] ",param);
			u = dbJoinService.updateNotBal(param);
			log.info("修改完成:[{}]",u);
			if (u==0) {
				//throw new Exception("批量修改遇到异常");
			}	
		} catch (Exception e) {
			log.info("执行批量异常:",e);
			e.printStackTrace();
		}
		return u;
		
	}

	/**
	 * 执行修改会员的方法。查询当天是否有到期成为会员的
	 * @return
	 * @throws Exception
	 */
	public int updateVipHy()throws Exception{
		int u = 0;
		try {
			   Map<String, Object> joMap = new HashMap<String, Object>();
				String data = DateUtils.getCurrentDate2();
				String joinStatrTime = data + " 00:00:00";
				String joinEndTime = data + " 23:59:59";
				joMap.put("joinStatrTime", joinStatrTime);
				joMap.put("joinEndTime",joinEndTime);
				joMap.put("joinMutype", Constants.MUTUAL_TYPE_YEONG);
				joMap.put("joinStatus", Constants.JOINSTATUS_1);
				joMap.put("joinVipzg", Constants.JOINVIPZG_01);
				log.info("根据join条件查询:[{}]",joMap);
				List<Map<String, Object>> lists = dbJoinService.queryByJoinitem(joMap);
				log.info("获取全部加入信息:[{}],数量:[{}]",lists,lists.size());
				try {
					if (lists.isEmpty()) {
						log.info("没有加入的数据,不做修改");
					}else{
						joMap.put("joinVipzg", Constants.JOINVIPZG_02);
						log.info("修改会员状态:[{}]",joMap);
						u = dbJoinService.updateItemJoin(joMap);
						log.info("修改会员状态:[{}]",u);	
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
		} catch (Exception e) {
			log.info("修改会员失败:[{}]",e);
			e.printStackTrace();
		}
		return u;
	}
	
	/****/
	
	
	public static void main(String[] args){
		//ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring-context.xml");
		 // AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");  
		/*String BeTime = DateUtils.getCurrentDateTime2();//获取当前时间
		System.out.println(BeTime);
		BeTime = "2017-10-06 00:00:00";
		String Format = "yyyy-MM-dd HH:mm:ss";//时间格式
		String StartTime = DateUtils.dateAddDay(BeTime, Format, 30);//日期相减
		log.info("获取开始时间[{}]",StartTime);*/
	}


}



/*CRON表达式    含义 
"0 0 12 * * ?"    每天中午十二点触发 
"0 15 10 ? * *"    每天早上10：15触发 
"0 15 10 * * ?"    每天早上10：15触发 
"0 15 10 * * ? *"    每天早上10：15触发 
"0 15 10 * * ? 2005"    2005年的每天早上10：15触发 
"0 * 14 * * ?"    每天从下午2点开始到2点59分每分钟一次触发 
"0 0/5 14 * * ?"    每天从下午2点开始到2：55分结束每5分钟一次触发 
"0 0/5 14,18 * * ?"    每天的下午2点至2：55和6点至6点55分两个时间段内每5分钟一次触发 
"0 0-5 14 * * ?"    每天14:00至14:05每分钟一次触发 
"0 10,44 14 ? 3 WED"    三月的每周三的14：10和14：44触发 
"0 15 10 ? * MON-FRI"    每个周一、周二、周三、周四、周五的10：15触发 */
