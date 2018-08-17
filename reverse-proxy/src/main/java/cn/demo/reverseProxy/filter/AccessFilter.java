package cn.demo.reverseProxy.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
* @Title: AccessFilter
* @Description: 访问过滤
* @author chy
* @date 2018/8/15 15:55
*/
public class AccessFilter extends ZuulFilter {

    private static org.slf4j.Logger logger= LoggerFactory.getLogger(AccessFilter.class);

    /**
     * 过滤器的类型
     * pre:路由之前执行
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 多个过滤器执行顺序
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 过滤开关
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤业务处理
     * @return
     */
    @Override
    public Object run() {
        // 获取请求内容
        RequestContext requestContext=RequestContext.getCurrentContext();

        HttpServletRequest request= requestContext.getRequest();

        logger.info("send {} request to {}",request.getMethod(),request.getRequestURL().toString());

        String url=request.getRequestURL().toString();

        //验证url是否授权
        if(url.contains("service-1")) {
            Object token=request.getParameter("token");
            if (token == null) {
                logger.info("token is empty");
                requestContext.setSendZuulResponse(false);
                requestContext.addZuulResponseHeader("Content-Type", "text/plain;charset=UTF-8");
                requestContext.setResponseBody("====未授权====");
                requestContext.setResponseStatusCode(401);
            }
        }
        else{
            if(url.contains("v2/myservice")) {
                logger.info("自定义路由");
                requestContext.setSendZuulResponse(false);
                requestContext.addZuulResponseHeader("Content-Type", "text/plain;charset=UTF-8");
                requestContext.setResponseBody("====自定义路由====");
                requestContext.setResponseStatusCode(401);
            }
        }

        return null;
    }
}
