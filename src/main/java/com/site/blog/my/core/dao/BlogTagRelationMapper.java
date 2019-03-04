package com.site.blog.my.core.dao;

import com.site.blog.my.core.entity.BlogTagRelation;
import com.site.blog.my.core.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BlogTagRelationMapper {
    int deleteByPrimaryKey(Long relationId);

    int insert(BlogTagRelation record);

    int insertSelective(BlogTagRelation record);

    BlogTagRelation selectByPrimaryKey(Long relationId);

    BlogTagRelation selectByBlogIdAndTagId(@Param("blogId") Long blogId, @Param("tagId") Integer tagId);

    List<Long> selectDistinctTagIds(Integer[] tagIds);

    int updateByPrimaryKeySelective(BlogTagRelation record);

    int updateByPrimaryKey(BlogTagRelation record);

    int batchInsert(@Param("relationList") List<BlogTagRelation> blogTagRelationList);

    int deleteByBlogId(Long blogId);
}