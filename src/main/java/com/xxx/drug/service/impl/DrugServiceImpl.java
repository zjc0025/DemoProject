package com.xxx.drug.service.impl;

import com.xxx.common.model.Page;
import com.xxx.drug.dao.DrugMapper;
import com.xxx.drug.model.Drug;
import com.xxx.drug.service.IDrugService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName DrugServiceImpl
 * @Description
 * @Author ZJC
 * @Date 2019/4/17 15:52
 */
@Service("drugService")
public class DrugServiceImpl implements IDrugService {

    private final static Logger logger = LoggerFactory.getLogger(DrugServiceImpl.class);

    @Resource
    private DrugMapper drugMapper;

    @Override
    public List<Drug> drugPage(Drug drug, Page page) {
        this.drugMapper.page(drug, page);
        return null;
    }

}
