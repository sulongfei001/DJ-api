package com.sevenXnetworks.treasure.service;

import com.sevenXnetworks.treasure.bean.GradeBean;
import com.sevenXnetworks.treasure.vo.RankVo;

/**
 * @Description
 * @Author sulongfei
 * @Date 19-1-28 下午2:54
 * @Version 1.0
 */
public interface GradeService {
    void createGrade(GradeBean bean);

    RankVo listGrade(String groupId, String identifyId);
}
