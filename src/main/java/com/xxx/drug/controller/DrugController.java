package com.xxx.drug.controller;

import com.xxx.common.model.Page;
import com.xxx.common.model.PageResult;
import com.xxx.drug.model.Drug;
import com.xxx.drug.service.IDrugService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName DrugController
 * @Description
 * @Author ZJC
 * @Date 2019/4/17 15:48
 */
@Controller
@RequestMapping("/drug")
public class DrugController {

    private final static Logger logger = LoggerFactory.getLogger(DrugController.class);

    @Resource
    private IDrugService drugService;

    @RequestMapping("/page")
    @ResponseBody
    public PageResult page(Drug drug, Page page) {
        List<Drug> drugs = this.drugService.drugPage(drug, page);
        page.setTotal(drugs.size());

        PageResult pageResult = new PageResult(page, drugs);

        return pageResult;
    }

}
