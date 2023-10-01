package com.newcodebbs.service;

import com.newcodebbs.dto.Result;

/**
 * @author shanhe
 */
public interface SelectDataService {
    Result selectData(Integer name, Object[] data);
}
