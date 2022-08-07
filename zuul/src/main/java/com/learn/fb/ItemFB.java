package com.learn.fb;

import com.web.utils.JsonResult;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 添加Hystrix降级：
 * 1. Zuul的依赖包含Hystrix和Ribbon，Zuul默认已启用Hystrix
 * 2. 实现FallbackProvider接口，按zuul的规则实现接口的方法
 * 3. 添加@Component
 */

@Component
public class ItemFB implements FallbackProvider {
    /*
    场景：当调用某个服务的时候出现异常，getRoute就指定哪个服务通过这里的fallbackResponse来处理返回给客户端的任务
     设置调用哪个后台服务，会应用当前降级类
     - "item-service"  只针对商品降级
     - "*"  对所有服务都降级
     - null 对所有服务都降级
      */
    @Override
    public String getRoute() {
        return "item-service";
    }

    // 发回给客户端的降级响应数据
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.INTERNAL_SERVER_ERROR;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return HttpStatus.INTERNAL_SERVER_ERROR.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
            }

            @Override
            public void close() {
                //用来关闭下面方法中的流
                //BAIS 流内存数组的流，不占用底层系统资源，不需要关闭
            }

            @Override
            public InputStream getBody() throws IOException {
                String json = JsonResult
                        .err().code(500).msg("调用商品失败").toString();

                return new ByteArrayInputStream(json.getBytes("UTF-8"));
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders h = new HttpHeaders();
                h.add("Content-Type", "application/json;charset=UTF-8");
                return h;
            }
        };
    }
}
