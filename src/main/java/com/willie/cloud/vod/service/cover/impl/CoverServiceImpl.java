package com.willie.cloud.vod.service.cover.impl;

import com.willie.cloud.vod.repository.cover.CoverRepository;
import com.willie.cloud.vod.service.cover.CoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/27 11:30</p>
 */
@Service
public class CoverServiceImpl implements CoverService {

    private final CoverRepository coverRepository;


    @Override
    public CoverRepository getCoverRepository() {
        return coverRepository;
    }

    @Autowired
    public CoverServiceImpl(CoverRepository coverRepository) {
        this.coverRepository = coverRepository;
    }
}
