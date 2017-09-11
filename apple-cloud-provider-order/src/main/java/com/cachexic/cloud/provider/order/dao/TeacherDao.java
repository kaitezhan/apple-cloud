package com.cachexic.cloud.provider.order.dao;

import com.cachexic.cloud.common.base.dao.BaseDao;
import com.cachexic.cloud.config.mybatis.annotation.MybatisDao;
import com.cachexic.cloud.feign.order.entity.Teacher;

/**
 * 教师管理
 * @author tangmin
 * @date 2017-09-11 17:33:34
 */
@MybatisDao
public interface TeacherDao extends BaseDao<Teacher, TeacherQuery>{

}