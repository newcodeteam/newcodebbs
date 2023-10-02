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
        // todo 待完成评论等基本功能
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
            case 1:
                    return this.adminSelectPostId((Integer) data);
            default:
                return Result.error("未有这个方法");
        }
    }
    
    @Override
    public Result selectAllData(Integer name, Integer type, Object data, boolean blur) {
        UserDTO userData = UserHolder.getUser();
        if (userData.getUserType() != 1) {
            Result.error("无权限");
        }
        // todo 模糊查询与精确查询
        switch (name){
            case 0:
                if (blur) {
                    switch (type){
                        case 0:
                            // fixme 已知Bug，暂时无法使用
                            return this.adminSelectUserMailBlur((String) data);
                        case 1:
                            return this.adminSelectUserNameBlur((String) data);
                        case 2:
                            return this.adminSelectUserNicknameBlur((String) data);
                        default:
                            return Result.error("未有这个方法");
                    }
                }else {
                    if (type == 0) return this.adminSelectUserNicknameData((String) data);
                    else return Result.error("未有这个方法");
                }
            case 1:
                return this.adminSelectPostId((Integer) data);
            default:
                return Result.error("未有这个方法");
        }
    }
    
    /**
     * 模糊查询昵称
     * @param nickname 昵称
     * @return list 集合
     */
    private Result adminSelectUserNicknameBlur(String nickname) {
        return Result.success(iUserDataService.userSelectNicknameBlurData(nickname));
    }
    
    /**
     *  模糊查询账号
     * @param name 账号
     * @return list 集合
     */
    private Result adminSelectUserNameBlur(String name) {
        return Result.success(iUserDataService.userSelectNameBlurData(name));
    }
    
    /**
     * 模糊查询邮箱
     * @param mail 邮箱
     * @return list 集合
     */
    private Result adminSelectUserMailBlur(String mail) {
        return Result.success(iUserDataService.userSelectMailBlurData(mail));
    }
    
    /**
     * 精确查询昵称
     * @param nickname 昵称
     * @return list 集合
     */
    private Result adminSelectUserNicknameData(String nickname) {
        return Result.success(iUserDataService.userSelectNicknameData(nickname));
    }
    
    /**
     * 根据Id查询单个帖子信息
     * @param data 帖子id
     * @return 单个数据
     */
    private Result adminSelectPostId(Integer data) {
        // todo 查询单个帖子id
        return null;
    }
    
    /**
     * 查询单个名字数据
     * @param name 名字
     * @return 单个数据
     */
    private Result adminSelectUserName(String name) {
        return Result.success(iUserDataService.userSelectNameData(name));
    }
    
    /**
     * 查询单个id数据
     * @param userId 用户id
     * @return 单个数据
     */
    private Result adminSelectUserIdData(String userId) {
        return Result.success(iUserDataService.userSelectUserIdData(userId));
    }
    
    /**
     * 查询单个mail数据
     * @param mail 邮箱
     * @return 单个数据
     */
    private Result adminSelectUserMail(String mail){
        return Result.success(iUserDataService.userSelectMailData(mail));
    }
}
