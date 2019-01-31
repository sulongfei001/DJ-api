package com.sevenXnetworks.treasure.dao;

import com.sevenXnetworks.treasure.entity.GradeEntity;

import java.util.List;

/**
 * @Description
 * @Author sulongfei
 * @Date 19-1-28 下午3:30
 * @Version 1.0
 */
public interface GradeDao extends BaseDao<GradeEntity, Long> {
    GradeEntity findMaleEntity(String groupId);

    GradeEntity findFemaleEntity(String groupId);

    GradeEntity findOwnEntity(String groupId, String identifyId);

    List<GradeEntity> findTop20(String groupId);

    List<GradeEntity> findAll(String groupId);
}
