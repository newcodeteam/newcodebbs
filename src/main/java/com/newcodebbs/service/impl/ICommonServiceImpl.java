package com.newcodebbs.service.impl;

import com.newcodebbs.dto.Result;
import com.newcodebbs.entity.AnalyseData;
import com.newcodebbs.service.IAnalyseDataService;
import com.newcodebbs.service.ICommonService;
import com.newcodebbs.service.IPostingsInfoService;
import com.newcodebbs.service.IPostingsOtherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.newcodebbs.Constants.RedisConstants.*;


@Service
@Slf4j
public class ICommonServiceImpl implements ICommonService {
    @Resource
    private IAnalyseDataService analyseDataService;
    @Resource
    private IPostingsInfoService postingsInfoService;
    @Resource
    private IPostingsOtherService postingsOtherService;
    private static ExecutorService CACHE_THREAD = Executors.newFixedThreadPool(10);
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public Result getBodyData() {
        // 首页基本数据
        // 采用逻辑过期
        String key  = CACHE_BODY_KEY + "body";
        // 判断是否存在缓存
        Map<Object, Object> bodymap = stringRedisTemplate.opsForHash().entries(key);
        // 不存在或者逻辑已过期 , 开始重构缓存
        String lockKey = CACHE_lOCK_KEY + "lock";
        boolean isLock = this.tryLock(lockKey);
        log.debug("执行到这里了0");
        if (bodymap.isEmpty() && isLock ) {
            CACHE_THREAD.submit(() ->{
               try {
                   Map<Object,Object> bodyData = this.saveBodyRedis(key);
                   log.debug("获取了数据{}",bodyData);
                   return Result.success(bodyData);
               } catch (Exception e) {
                   return Result.error("遭到不可抗力因素，程序停止，请联系管理员");
               } finally {
                   this.unlock(lockKey);
               }
            });
        }
        // 如果 互斥锁在被用时恰好被并发了，那就先返回过期的数据防止被击穿.
        // 实际上，这个程序应该首先建立起缓存数据才能运行。
        return Result.success(bodymap);
    }
    
    /**
     * 建立缓存
     * @param key key地址
     * @return 首页数据
     */
    private Map<Object,Object> saveBodyRedis(String key) {
        // bug
        List<?> analyseData = analyseDataService.selectAnalyseData();
        log.debug("查询的list map数据{}",analyseData);
        HashMap<Object, Object> listMap = new HashMap<>();
        for (int i = 0; i < analyseData.size(); i++) {
            // list 获取 数据之后转换实体类，然后获取数据
            AnalyseData analyseData1 = (AnalyseData) analyseData.get(i);
            log.debug("查询的数据{}",analyseData1);
            listMap.put("info"+i,postingsInfoService.selectPostingInfoData(analyseData1.getPostingsId()));
            listMap.put("other"+i,postingsOtherService.selectPostingOtherData(analyseData1.getPostingsId()));
        }
//        List<?> postsOther = postingsOtherService.selectPostingOtherData(analyseData.get("postingsId"));
//        List<?>  postsInfo = postingsInfoService.selectPostingInfoData(analyseData.get("postingsId"));
        return listMap;
    }
    
    /**
     * 销毁锁
     * @param lockKey 锁
     */
    private void unlock(String lockKey) {
        stringRedisTemplate.expire(lockKey,0,TimeUnit.SECONDS);
    }
    
    
    /**
     * 互斥锁
     * @param lockKey 锁地址
     * @return true and false
     */
    private boolean tryLock(String lockKey) {
        String lock = stringRedisTemplate.opsForValue().get(lockKey);
        if (lock == null){
            stringRedisTemplate.opsForValue().set(lockKey,"lock",CACHE_lOCK_TTL, TimeUnit.SECONDS);
            return true;
        }
        return false;
    }
}
