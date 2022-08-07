package com.learn.filter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.web.utils.JsonResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


@Component
public class AccessFilter extends ZuulFilter {
    // 过滤器的类型：pre,route,post,error
    @Override
    public String filterType() {
        // return "pre";
        return FilterConstants.PRE_TYPE;
    }


    // 顺序号
    @Override
    public int filterOrder() {
        return 6;
    }
    // 针对当前请求进行判断
    // 是否要执行下面的过滤代码
    @Override
    public boolean shouldFilter() {
        // 调用后台商品服务需要检查权限
        // 调用用户和订单可以直接访问

        // 获得 RequestContext 对象
        // 从上下文对象获取访问的后台服务id
        // 判断服务id是否是 "item-service"
        RequestContext ctx = RequestContext.getCurrentContext();
        String serviceId = (String) ctx.get(FilterConstants.SERVICE_ID_KEY);//"serviceId"
        return "item-service".equals(serviceId);
    }

    // 过滤代码，检查用户权限
    @Override
    public Object run() throws ZuulException {
        // http://localhost:3001/item-service/t45t4?token=65345rt

        // 获取请求上下文对象
        // 从上下文对象获取 request 对象
        // 接收 token 参数
        // 如果没有 token，阻止继续调用，直接返回响应
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String token = request.getParameter("token");
        if (StringUtils.isBlank(token)) {
            // 阻止继续调用
            ctx.setSendZuulResponse(false);
            // 直接返回响应
            // JsonResult -- {code:400, msg:未登录, data:null}
            String json = JsonResult
                    .err()
                    .code(400)
                    .msg("Not Login, 未登录")
                    .toString();
            ctx.addZuulResponseHeader("Content-Type","application/json;charset=UTF-8");
            ctx.setResponseBody(json);
        }
        // zuul当前版本中，这个返回值不起任何作用
        return null;
    }
}
