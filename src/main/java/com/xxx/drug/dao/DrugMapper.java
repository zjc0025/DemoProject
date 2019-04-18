package com.xxx.drug.dao;

import com.xxx.common.model.Page;
import com.xxx.drug.model.Drug;

import java.util.List;

/**
 * @ClassName DrugMapper
 * @Description
 * @Author ZJC
 * @Date 2019/4/17 15:50
 */
public interface DrugMapper {

    List<Drug> page(Drug drug, Page page);

}
