package com.xxx.drug.service.impl;

import com.xxx.common.model.Page;
import com.xxx.common.utils.UUIDStr;
import com.xxx.drug.dao.IDrugMapper;
import com.xxx.drug.model.Drug;
import com.xxx.drug.model.LogEntity;
import com.xxx.drug.service.IDrugService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
    private IDrugMapper drugMapper;

    Lock lock = new ReentrantLock();

    @Override
    public List<Drug> drugPage(Drug drug, Page page) {

        Map<String, String> param = new HashMap<>();
        param.put("pageIndex", String.valueOf(page.getPageIndex()));
        param.put("pageSize", String.valueOf(page.getPageSize()));
        param.put("sortField", page.getSortField());
        param.put("sortOrder", page.getSortOrder());
        param.put("name", drug.getName());

        return this.drugMapper.page(param);
    }

    @Override
    public boolean addDrug(Drug drug) {
        try{
            drug.setId(UUIDStr.getUUID());
            this.drugMapper.addDrug(drug);
            return true;
        }catch (Exception e){
            logger.error("添加药品失败",e);
            return false;
        }
    }

    @Override
    public boolean updateDrug(Drug drug) {
        try{
            this.drugMapper.updateDrug(drug);
            return true;
        }catch (Exception e){
            logger.error("更新药品失败",e);
            return false;
        }
    }

    @Override
    public boolean inputDrug(Drug drug) {
        try{
            lock.lock();
            //查询数据库库存
            long oldStock = this.drugMapper.queryStock(drug.getId());
            //增加请求的库存
            drug.setStock(oldStock + drug.getStock());
            //更新库存
            this.drugMapper.inputDrug(drug);
            return true;
        }catch (Exception e){
            logger.error("药品入库失败",e);
            return false;
        }finally {
            lock.unlock();
        }
    }

    @Override
    public boolean outputDrug(Drug drug) {
        try{
            lock.lock();
            //查询数据库库存
            long oldStock = this.drugMapper.queryStock(drug.getId());
            //减去请求的库存
            drug.setStock(oldStock - drug.getStock());
            //更新库存
            this.drugMapper.inputDrug(drug);
            return true;
        }catch (Exception e){
            logger.error("药品出库失败",e);
            return false;
        }finally {
            lock.unlock();
        }
    }

    @Override
    public List<LogEntity> queryDrugLog(Drug drug, Page page) {
        Map<String, String> param = new HashMap<>();
        param.put("pageIndex", String.valueOf(page.getPageIndex()));
        param.put("pageSize", String.valueOf(page.getPageSize()));
        param.put("sortField", page.getSortField());
        param.put("sortOrder", page.getSortOrder());
        param.put("drugId", drug.getId());
        return this.drugMapper.queryDrugLog(param);
    }

    @Override
    public List<Drug> drugCount() {
        return this.drugMapper.drugCount();
    }

    @Override
    public boolean checkDrugCode(String drugCode) {
        Drug drug = drugMapper.checkDrugCode(drugCode);
        if(null == drug){
            return true;
        }
        return false;
    }

}
