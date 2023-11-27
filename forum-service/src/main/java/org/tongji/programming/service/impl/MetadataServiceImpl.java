package org.tongji.programming.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tongji.programming.mapper.TagMapper;
import org.tongji.programming.pojo.Tag;
import org.tongji.programming.service.MetadataService;

import java.time.LocalDateTime;

/**
 * @author cinea
 */
@Service
public class MetadataServiceImpl implements MetadataService {

    TagMapper tagMapper;

    @Autowired
    public MetadataServiceImpl(TagMapper tagMapper) {
        this.tagMapper = tagMapper;
    }

    static Tag[] tagsCache = new Tag[0];
    static LocalDateTime tagsCacheValidBefore = LocalDateTime.now().minusDays(1);


    @Override
    public Tag[] getTags() {
        if (LocalDateTime.now().isAfter(tagsCacheValidBefore)) {
            tagsCache = tagMapper.selectList(null).toArray(new Tag[0]);
            tagsCacheValidBefore = LocalDateTime.now().plusMinutes(30);
        }
        return tagsCache;
    }
}
