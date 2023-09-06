package com.newcodebbs.service.impl;

import com.newcodebbs.entity.FileData;
import com.newcodebbs.mapper.FileDataMapper;
import com.newcodebbs.service.IFileDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文件和图片表 服务实现类
 * </p>
 *
 * @author shanhe
 * @since 2023-09-06
 */
@Service
public class FileDataServiceImpl extends ServiceImpl<FileDataMapper, FileData> implements IFileDataService {

}
