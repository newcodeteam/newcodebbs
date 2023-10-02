package com.newcodebbs.service;

import com.newcodebbs.dto.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * @author shanhe
 */
public interface SelectDataService {
    Result selectIdData(Integer name, Integer type, Object data);
    
    Result selectAllData(Integer name, Integer type, Object data, boolean blur);
}
