package com.xxx.drug.dao;

import com.xxx.common.model.Page;
import com.xxx.drug.model.Drug;
import com.xxx.drug.model.LogEntity;

import java.util.List;
import java.util.Map;

/**
 * @ClassName IDrugMapper
 * @Description
 * @Author ZJC
 * @Date 2019/4/17 15:50
 */
public interface IDrugMapper {

    List<Drug> page(Map param);

    void addDrug(Drug drug);

    void updateDrug(Drug drug);

    void inputDrug(Drug drug);

    int queryStock(String id);

    List<LogEntity> queryDrugLog(Map param);
}
