package com.zgxt.demo.respository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zgxt.demo.respository.dao.TraceFoodMapper;
import com.zgxt.demo.respository.po.TraceFoodPo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;


@Repository
public class TraceFoodServiceImpl{
    @Resource
    TraceFoodMapper traceFoodMapper;

    public int insert(TraceFoodPo traceFoodPo) {
        int insert = traceFoodMapper.insert(traceFoodPo);
        return insert;
    }

    public TraceFoodPo select(String roleModel, String traceNumber) {
        LambdaQueryWrapper<TraceFoodPo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TraceFoodPo::getRolemodel, roleModel)
                .eq(TraceFoodPo::getTracenumber, traceNumber)
                .select();
        TraceFoodPo traceFoodPo = traceFoodMapper.selectOne(queryWrapper);
        return traceFoodPo;
    }

    public TraceFoodPo selectLast(String traceNumber) {
        LambdaQueryWrapper<TraceFoodPo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(TraceFoodPo::getTracenumber, traceNumber)
                .select();
        List<TraceFoodPo> TraceFoodPo = traceFoodMapper.selectList(queryWrapper);
        if (TraceFoodPo.size() > 0) {
            return TraceFoodPo.get(0);
        }
        return null;
    }
}




