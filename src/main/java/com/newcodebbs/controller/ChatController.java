package com.newcodebbs.controller;

import com.newcodebbs.dto.ChatDTO;
import com.newcodebbs.dto.Result;
import com.newcodebbs.service.IUserChatTwoService;
import com.newcodebbs.service.impl.UserChatOneServiceImpl;
import com.newcodebbs.service.impl.UserChatTwoServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;

/**
 * <p>
 * 聊天控制器
 * </p>
 *
 * @author shanhe
 * @since 2023-09-21
 */
public class ChatController {
    
    @Resource
    private IUserChatTwoService userChatOneService;
    @Resource
    private IUserChatTwoService userChatTwoService;
    
    @PostMapping("chat")
    public Result chat(@RequestBody ChatDTO chatDTO) {
        // todo 聊天信息
        return null;
    }
}
