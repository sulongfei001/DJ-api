package com.sevenXnetworks.treasure.web.controller;

import com.sevenXnetworks.treasure.bean.GradeBean;
import com.sevenXnetworks.treasure.exception.ParameterException;
import com.sevenXnetworks.treasure.model.CustomerErrorConstants;
import com.sevenXnetworks.treasure.model.Validator;
import com.sevenXnetworks.treasure.service.GradeService;
import com.sevenXnetworks.treasure.vo.RankVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description
 * @Author sulongfei
 * @Date 19-1-28 下午2:49
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/grade")
public class GradeController {
    @Autowired
    private GradeService gradeService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Object createGrade(@RequestBody GradeBean bean) {
        verifyBean(bean);
        gradeService.createGrade(bean);
        return null;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Object listGrade(@RequestParam String groupId, @RequestParam String identifyId) {
        RankVo vo = gradeService.listGrade(groupId, identifyId);
        return vo;
    }

    private void verifyBean(GradeBean bean) {
        Validator.notEmpty(bean.getGroupId(), ParameterException.error(CustomerErrorConstants.GRADE_UPLOAD_FORMAT_ERROR));
        Validator.notEmpty(bean.getIdentifyId(), ParameterException.error(CustomerErrorConstants.GRADE_UPLOAD_FORMAT_ERROR));
    }

}
