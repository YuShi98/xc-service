package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import io.swagger.annotations.*;

@Api(value = "cms页面管理接口", description = "cms页面管理接口，提供页面的增、删、改、查")
public interface CmsPageControllerApi {
    //页面查询
    @ApiOperation("分页查询页面列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = true, paramType = "path", dataType = "int")
    })
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest);

    @ApiOperation("添加页面信息")
    public CmsPageResult add(CmsPage cmsPage);

    @ApiOperation("根据id查询页面")
    public CmsPage findById(@ApiParam(name = "id", value = "pageId", required = true) String id);

    @ApiOperation("修改页面信息")
    public CmsPageResult edit(@ApiParam(name = "id", value = "pageId", required = true) String id, CmsPage cmsPage);

    @ApiOperation("删除页面")
    public ResponseResult delete(@ApiParam(name = "id", value = "pageId", required = true) String id);
}
