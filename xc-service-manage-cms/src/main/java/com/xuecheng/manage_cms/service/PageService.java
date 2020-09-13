package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @ClassName PageService
 * @Description TOOD
 * @Aothor zh
 * @Date 2020/8/31 0:20
 * @Version 1.0
 */
@Service
public class PageService {

    @Autowired
    CmsPageRepository cmsPageRepository;


    /**
     * 页面列表分页查询
     *
     * @param page             页码
     * @param size             每页显示数
     * @param queryPageRequest 查询条件
     * @return 页面列表
     * @create 2020/8/31 0:34
     */
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest) {

        if (queryPageRequest == null) {
            queryPageRequest = new QueryPageRequest();
        }

        if (page <= 0) {
            page = 1;
        }
        //为了适应mongodb的接口
        page = page - 1;
        if (size <= 20) {
            size = 20;
        }

        //条件匹配器
        //页面名称需要模糊查询
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());

        //条件
        CmsPage cmsPage = new CmsPage();
        //站点id
        if (StringUtils.isNotEmpty(queryPageRequest.getSiteId())) {
            cmsPage.setSiteId(queryPageRequest.getSiteId());
        }
        //页面别名
        if (StringUtils.isNotEmpty(queryPageRequest.getPageAliase())) {
            cmsPage.setPageAliase(queryPageRequest.getPageAliase());
        }

        //条件实例
        Example<CmsPage> example = Example.of(cmsPage, exampleMatcher);
        //分页对象
        Pageable pageable = new PageRequest(page, size);
        //分页查询
        Page<CmsPage> all = cmsPageRepository.findAll(example, pageable);
        QueryResult<CmsPage> result = new QueryResult<>();
        result.setList(all.getContent());
        result.setTotal(all.getTotalElements());
        //返回结果
        return new QueryResponseResult(CommonCode.SUCCESS, result);
    }


    /**
     * 添加页面
     *
     * @param cmsPage 页面实体
     * @create 2020/9/9 23:56
     */
    public CmsPageResult add(CmsPage cmsPage) {

        if (cmsPage == null){
            ExceptionCast.cast(CommonCode.ADD_NULL_EXISTS);
        }

        //校验页面是否存在  根据页面名称 站点id 页面webPath
        CmsPage page = cmsPageRepository.findByPageNameAndSiteIdAndPageWebPath(cmsPage.getPageName(),
                cmsPage.getSiteId(),
                cmsPage.getPageWebPath());
        if (page != null) {
           ExceptionCast.cast(CommonCode.CMS_ADD_EXISTS);
        }
        //由spring data 自动生成
        cmsPage.setPageId(null);
        cmsPageRepository.save(cmsPage);
        return new CmsPageResult(CommonCode.SUCCESS, cmsPage);

    }

    /**
     * 根据id查询
     *
     * @param id 页面id
     * @create 2020/9/9 23:58
     */
    public CmsPage getById(String id) {
        Optional<CmsPage> cmsPageOptional = cmsPageRepository.findById(id);
        return cmsPageOptional.orElse(null);
    }

    /**
     * 修改页面信息
     *
     * @param id      修改页面id
     * @param cmsPage 修改数据
     * @reate 2020/9/13 21:46
     */

    public CmsPageResult update(String id, CmsPage cmsPage) {
        //根据id查询页面
        CmsPage page = this.getById(id);
        if (page != null) {

            page.setTemplateId(cmsPage.getTemplateId());
            page.setSiteId(cmsPage.getSiteId());
            page.setPageAliase(cmsPage.getPageAliase());
            page.setPageName(cmsPage.getPageName());
            page.setPageWebPath(cmsPage.getPageWebPath());
            page.setPagePhysicalPath(cmsPage.getPagePhysicalPath());
            //更新
            CmsPage save = cmsPageRepository.save(page);
            if (save != null) {
                //返回成功
                return new CmsPageResult(CommonCode.SUCCESS, save);
            }
        }
        return new CmsPageResult(CommonCode.FAIL, null);
    }


    /**
     * 删除页面
     *
     * @param id 页面id
     * @create 2020/9/13 21:47
     */
    public ResponseResult delete(String id) {
        CmsPage page = this.getById(id);
        if (page != null) {
            //删除页面
            cmsPageRepository.deleteById(id);
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

}
