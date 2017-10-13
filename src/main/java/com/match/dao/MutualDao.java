package com.match.dao;

import java.util.List;
import java.util.Map;


/**
 * 互助项目接口
 * @author 13376
 *
 */
public interface MutualDao {
	
	/**
	 * 查询所有项目列表
	 * @param param
	 * @return
	 */
	List<Map<String,Object>> getMutualList(Map<String,Object> param);
	
	/**
	 * 根据条件查询项目
	 * @param param
	 * @return
	 */
	Map<String, Object> findMutual(Map<String, Object> param);
	
	/**
	 * 修改项目记录,修改金额和人数
	 * @param param
	 * @return
	 */
	int updateMutual(Map<String, Object> param);
	
	/**
	 * 后台编辑项目
	 * @param param
	 * @return
	 */
	int updateMutualHt(Map<String, Object> param);
	
	/**
	 * 后台新增项目
	 * @param param
	 * @return
	 */
	int insertMutualHt(Map<String, Object> param);
}
