package com.xuecheng.manage_cms.web.controller;

import com.xuecheng.api.cms.CmsPageControllerApi;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_cms.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @ClassName CmsPageController
 * @Description cms页面
 * @Aothor zh
 * @Date 2020/8/30 17:54
 * @Version 1.0
 */
@RestController
@RequestMapping("/cms/page")
public class CmsPageController implements CmsPageControllerApi {


    @Autowired
    private PageService pageService;


    @GetMapping("/list/{page}/{size}")
    @Override
    public QueryResponseResult findList(@PathVariable("page") int page, @PathVariable("size") int size,
                                        QueryPageRequest queryPageRequest) {
        return pageService.findList(page, size, queryPageRequest);
    }

    @PostMapping("/add")
    @Override
    public CmsPageResult add(@RequestBody CmsPage cmsPage) {
        return pageService.add(cmsPage);
    }

    @GetMapping("/get/{id}")
    @Override
    public CmsPage findById(@PathVariable("id") String id) {
        return pageService.getById(id);
    }

    @PutMapping("edit/{id}")
    @Override
    public CmsPageResult edit(@PathVariable("id") String id, @RequestBody CmsPage cmsPage) {
        return pageService.update(id, cmsPage);
    }

    @DeleteMapping("/del/{id}")
    @Override
    public ResponseResult delete(@PathVariable("id") String id) {
        return pageService.delete(id);
    }
}
