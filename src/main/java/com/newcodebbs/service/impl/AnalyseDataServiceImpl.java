package com.newcodebbs.service.impl;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.newcodebbs.entity.AnalyseData;
import com.newcodebbs.entity.UserData;
import com.newcodebbs.mapper.AnalyseDataMapper;
import com.newcodebbs.service.IAnalyseDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据分析以及推荐权重表 服务实现类
 * </p>
 *
 * @author shanhe
 * @since 2023-09-06
 */
@Slf4j
@Service
public class AnalyseDataServiceImpl extends ServiceImpl<AnalyseDataMapper, AnalyseData> implements IAnalyseDataService {
    
    @Resource
    private AnalyseDataMapper analyseDataMapper;
    
    @Override
    public List<?> selectAnalyseData() {
        Page page = new Page(1, 5);
// 创建QueryWrapper对象
        QueryWrapper<AnalyseData> qw = new QueryWrapper<>();
// 设置查询条件
// 调用selectMapsPage方法
        Page page1 = analyseDataMapper.selectMapsPage(page, qw);
// 打印查询结果
        List<?> map = page.getRecords();
//        qie
//        IPage page = new Page(1,30);
//        analyseDataMapper.selectPage(page,null);
//        Map<String,Object> map = MapUtil.newHashMap();
//        // 当前页数
//        // 当前页数
//        map.put("current",page.getCurrent());
//        // 总页数
//        map.put("pages",page.getPages());
//        // 当前页数内容
//        map.put("records",page.getRecords());
        return map;
    }
}
