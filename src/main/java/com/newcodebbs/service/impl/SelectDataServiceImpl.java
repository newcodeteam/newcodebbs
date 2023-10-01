package com.newcodebbs.service.impl;

import com.newcodebbs.dto.Result;
import com.newcodebbs.dto.UserDTO;
import com.newcodebbs.entity.PostingsInfo;
import com.newcodebbs.entity.UserData;
import com.newcodebbs.service.*;
import com.newcodebbs.utils.UserHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author shanhe
 * @since 2023-10-01
 */
@Service
public class SelectDataServiceImpl implements SelectDataService {
    @Resource
    private IUserDataService iUserDataService;
    @Resource
    private IPostingsInfoService iPostingsInfoService;
    @Resource
    private ICommentsDataService iCommentsDataService;
    @Resource
    private IFileDataService iFileDataService;
    @Resource
    private ITagDataService iTagDataService;
    @Resource
    private ICategoryDataService iCategoryDataService;
    
    @Override
    public Result selectData(Integer name, Object[] data) {
        UserDTO userData = UserHolder.getUser();
        if (userData.getUserType() != 1) {
            Result.error("无权限");
        }
        switch (name){
            case 0:
                return this.adminSelectUserData(data);
            default:
                return Result.error("未有这个方法");
        }
    }
    
    private Result adminSelectUserData(Object[] data) {
        return Result.success("123");
    }
}
