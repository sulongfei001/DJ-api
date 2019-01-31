package com.sevenXnetworks.treasure.service.impl;

import com.sevenXnetworks.treasure.bean.GradeBean;
import com.sevenXnetworks.treasure.dao.GradeDao;
import com.sevenXnetworks.treasure.entity.GradeEntity;
import com.sevenXnetworks.treasure.service.GradeService;
import com.sevenXnetworks.treasure.vo.GradeVo;
import com.sevenXnetworks.treasure.vo.RankVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author sulongfei
 * @Date 19-1-28 下午2:54
 * @Version 1.0
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class GradeServiceImpl implements GradeService {
    @Autowired
    private GradeDao gradeDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void createGrade(GradeBean bean) {
        GradeEntity entity = new GradeEntity();
        entity = toEntity(entity, bean);
        gradeDao.saveOrUpdate(entity);
    }

    @Override
    public RankVo listGrade(String groupId, String identifyId) {
        GradeEntity maleEntity = gradeDao.findMaleEntity(groupId);
        GradeEntity femaleEntity = gradeDao.findFemaleEntity(groupId);
        GradeEntity ownEntity = gradeDao.findOwnEntity(groupId, identifyId);
        List<GradeEntity> gradeEntities = gradeDao.findTop20(groupId);
        List<GradeEntity> allEntities = gradeDao.findAll(groupId);
        int ownRank = 0;
        for (GradeEntity entity : allEntities) {
            ownRank++;
            if (identifyId.equals(entity.getIdentifyId())){
                break;
            }
        }
        List<GradeVo> gradeVos = new ArrayList<>();
        gradeEntities.forEach(entity -> gradeVos.add(toVo(entity)));
        RankVo rankVo = new RankVo();
        rankVo.setGroupId(groupId);
        rankVo.setMale(maleEntity == null ? null : toVo(maleEntity));
        rankVo.setFemale(femaleEntity == null ? null : toVo(femaleEntity));
        rankVo.setOwn(ownEntity == null ? null : toVo(ownEntity));
        rankVo.setOwnRank(ownRank);
        rankVo.setListGrade(gradeVos);
        return rankVo;
    }

    private GradeVo toVo(GradeEntity entity) {
        GradeVo vo = new GradeVo();
        vo.setIdentifyId(entity.getIdentifyId());
        vo.setGender(entity.getGender());
        vo.setHeadshot(entity.getHeadshot());
        vo.setName(entity.getName());
        vo.setGrade(entity.getGrade());
        return vo;
    }

    private GradeEntity toEntity(GradeEntity entity, GradeBean bean) {
        entity.setIdentifyId(bean.getIdentifyId());
        entity.setGroupId(bean.getGroupId());
        entity.setGender(bean.getGender());
        entity.setHeadshot(bean.getHeadshot());
        entity.setName(bean.getName());
        entity.setGrade(bean.getGrade());
        return entity;
    }
}
