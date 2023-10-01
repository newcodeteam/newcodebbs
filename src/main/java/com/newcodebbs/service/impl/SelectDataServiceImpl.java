package com.newcodebbs.service.impl;

import com.newcodebbs.dto.Result;
import com.newcodebbs.dto.UserDTO;
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
    public Result selectIdData(Integer name, Integer type, Object data) {
        UserDTO userData = UserHolder.getUser();
        if (userData.getUserType() != 1) {
            Result.error("无权限");
        }
        switch (name){
            case 0:
                switch (type){
                    case 0:
                        return this.adminSelectUserIdData((String) data);
                    case 1:
                        return this.adminSelectUserMail((String) data);
                    case 2:
                        return this.adminSelectUserName((String) data);
                    default:
                        return Result.error("未有这个方法");
                }
                
            default:
                return Result.error("未有这个方法");
        }
    }
    
    /**
     * 查询单个名字数据
     * @param name
     * @return
     */
    private Result adminSelectUserName(String name) {
        return Result.success(iUserDataService.userSelectNameData(name));
    }
    
    /**
     * 查询单个id数据
     * @param userId
     * @return
     */
    private Result adminSelectUserIdData(String userId) {
        return Result.success(iUserDataService.userSelectUserIdData(userId));
    }
    
    /**
     * 查询单个mail数据
     * @param mail
     * @return
     */
    private Result adminSelectUserMail(String mail){
        return Result.success(iUserDataService.userSelectMailData(mail));
    }
}
