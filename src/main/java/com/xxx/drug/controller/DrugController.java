package com.xxx.drug.controller;

import com.xxx.common.model.Page;
import com.xxx.common.model.PageResult;
import com.xxx.drug.aspect.DrugLog;
import com.xxx.drug.model.Drug;
import com.xxx.drug.model.LogEntity;
import com.xxx.drug.service.IDrugService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        PageResult pageResult = new PageResult(page, drugs);

        return pageResult;
    }

    @RequestMapping("/addDrug")
    @ResponseBody
    public boolean addDrug(@RequestBody Drug drug) {
        boolean f = this.drugService.addDrug(drug);
        return f;
    }

    @RequestMapping("/updateDrug")
    @ResponseBody
    public boolean updateDrug(@RequestBody Drug drug) {
        boolean f = this.drugService.updateDrug(drug);
        return f;
    }

    @RequestMapping("/inputDrug")
    @ResponseBody
    @DrugLog(module = "药品维护", methods = "入库记录")
    public boolean inputDrug(@RequestBody Drug drug) {
        boolean f = this.drugService.inputDrug(drug);
        return f;
    }

    @RequestMapping("/outputDrug")
    @ResponseBody
    @DrugLog(module = "药品维护", methods = "出库记录")
    public boolean outputDrug(@RequestBody Drug drug) {
        boolean f = this.drugService.outputDrug(drug);
        return f;
    }

    @RequestMapping("/queryDrugLog")
    @ResponseBody
    public PageResult queryDrugLog(Drug drug, Page page) {
        List<LogEntity> logs = this.drugService.queryDrugLog(drug, page);
        PageResult pageResult = new PageResult(page, logs);

        return pageResult;
    }

    @RequestMapping("/drugCount")
    @ResponseBody
    public Map drugCount() {
        List<Drug> drugs = this.drugService.drugCount();

        Map map = new HashMap();
        map.put("status", "查询成功");
        map.put("data", drugs);

        return map;
    }

    @RequestMapping("/checkDrugCode")
    @ResponseBody
    public boolean checkDrugCode(@RequestBody String drugCode) {
        boolean f = this.drugService.checkDrugCode(drugCode);
        return f;
    }

}
