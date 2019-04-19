package com.xxx.common.service.impl;

import com.xxx.common.dao.LogMapper;
import com.xxx.common.service.ILogService;
import com.xxx.drug.model.LogEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName LogServiceImpl
 * @Description
 * @Author ZJC
 * @Date 2019/4/19 9:04
 */
@Service("logService")
public class LogServiceImpl implements ILogService {

    @Resource
    private LogMapper logMapper;

    @Override
    public void saveLog(LogEntity logEntity) {
        logMapper.saveLog(logEntity);
    }

}
