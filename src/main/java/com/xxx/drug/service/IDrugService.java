package com.xxx.drug.service;

import com.xxx.common.model.Page;
import com.xxx.drug.model.Drug;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName IDrugService
 * @Description
 * @Author ZJC
 * @Date 2019/4/17 15:48
 */
public interface IDrugService {
    List<Drug> drugPage(Drug drug, Page page);
}
