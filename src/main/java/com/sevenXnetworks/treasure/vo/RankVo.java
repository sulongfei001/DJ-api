package com.sevenXnetworks.treasure.vo;

import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Author sulongfei
 * @Date 19-1-28 下午3:45
 * @Version 1.0
 */
@Data
public class RankVo {
    private String groupId;
    private GradeVo female;
    private GradeVo male;
    private GradeVo own;
    private int ownRank;
    private List<GradeVo> listGrade;
}
