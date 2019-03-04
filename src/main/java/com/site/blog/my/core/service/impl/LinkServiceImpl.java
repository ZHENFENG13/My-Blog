package com.site.blog.my.core.service.impl;

import com.site.blog.my.core.dao.BlogLinkMapper;
import com.site.blog.my.core.entity.BlogLink;
import com.site.blog.my.core.service.LinkService;
import com.site.blog.my.core.util.PageQueryUtil;
import com.site.blog.my.core.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LinkServiceImpl implements LinkService {

    @Autowired
    private BlogLinkMapper blogLinkMapper;

    @Override
    public PageResult getBlogLinkPage(PageQueryUtil pageUtil) {
        List<BlogLink> links = blogLinkMapper.findLinkList(pageUtil);
        int total = blogLinkMapper.getTotalLinks(pageUtil);
        PageResult pageResult = new PageResult(links, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public int getTotalLinks() {
        return blogLinkMapper.getTotalLinks(null);
    }

    @Override
    public Boolean saveLink(BlogLink link) {
        return blogLinkMapper.insertSelective(link) > 0;
    }

    @Override
    public BlogLink selectById(Integer id) {
        return blogLinkMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean updateLink(BlogLink tempLink) {
        return blogLinkMapper.updateByPrimaryKeySelective(tempLink) > 0;
    }

    @Override
    public Boolean deleteBatch(Integer[] ids) {
        return blogLinkMapper.deleteBatch(ids) > 0;
    }

    @Override
    public Map<Byte, List<BlogLink>> getLinksForLinkPage() {
        //获取所有链接数据
        List<BlogLink> links = blogLinkMapper.findLinkList(null);
        if (!CollectionUtils.isEmpty(links)) {
            //根据type进行分组
            Map<Byte, List<BlogLink>> linksMap = links.stream().collect(Collectors.groupingBy(BlogLink::getLinkType));
            return linksMap;
        }
        return null;
    }
}
