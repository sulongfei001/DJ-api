package com.sevenXnetworks.treasure.dao.impl;

import com.sevenXnetworks.treasure.dao.GradeDao;
import com.sevenXnetworks.treasure.entity.GradeEntity;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 * @Author sulongfei
 * @Date 19-1-28 下午3:31
 * @Version 1.0
 */
@Repository
public class GradeDaoImpl extends BaseDaoImpl<GradeEntity, Long> implements GradeDao {
    @Override
    public GradeEntity findMaleEntity(String groupId) {
        String hql = "from GradeEntity where gender = 1 and groupId = :groupId order by grade desc ";
        Query query = currentSession().createQuery(hql);
        query.setString("groupId", groupId);
        query.setMaxResults(1);
        return (GradeEntity) query.uniqueResult();
    }

    @Override
    public GradeEntity findFemaleEntity(String groupId) {
        String hql = "from GradeEntity where gender = 2 and groupId = :groupId order by grade desc ";
        Query query = currentSession().createQuery(hql);
        query.setString("groupId", groupId);
        query.setMaxResults(1);
        return (GradeEntity) query.uniqueResult();
    }

    @Override
    public GradeEntity findOwnEntity(String groupId, String identifyId) {
        String hql = "from GradeEntity where identifyId = :identifyId and groupId = :groupId ";
        Query query = currentSession().createQuery(hql);
        query.setString("identifyId",identifyId);
        query.setString("groupId", groupId);
        return (GradeEntity) query.uniqueResult();
    }

    @Override
    public List<GradeEntity> findTop20(String groupId) {
        String hql = "from GradeEntity where groupId = :groupId order by grade desc ";
        Query query = currentSession().createQuery(hql);
        query.setString("groupId", groupId);
        query.setMaxResults(20);
        return query.list();
    }

    @Override
    public List<GradeEntity> findAll(String groupId) {
        String hql = "from GradeEntity where groupId = :groupId order by grade desc ";
        Query query = currentSession().createQuery(hql);
        query.setString("groupId", groupId);
        return query.list();
    }
}
