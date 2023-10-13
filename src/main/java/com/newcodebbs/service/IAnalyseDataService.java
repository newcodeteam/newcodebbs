package com.newcodebbs.service;

import com.newcodebbs.entity.AnalyseData;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据分析以及推荐权重表 服务类
 * </p>
 *
 * @author shanhe
 * @since 2023-09-06
 */
public interface IAnalyseDataService extends IService<AnalyseData> {
    
    List<?> selectAnalyseData();
}
