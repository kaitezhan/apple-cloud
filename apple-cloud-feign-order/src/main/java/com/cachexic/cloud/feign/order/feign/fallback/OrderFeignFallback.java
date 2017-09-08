package com.cachexic.cloud.feign.order.feign.fallback;

import com.cachexic.cloud.common.base.Result;
import com.cachexic.cloud.feign.order.entity.Order;
import com.cachexic.cloud.feign.order.entity.query.OrderQuery;
import com.cachexic.cloud.feign.order.feign.OrderFeign;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author tangmin
 * @version V1.0
 * @Title: OrderFeignFallbackImpl.java
 * @Package com.cachexic.cloud.feign.order.feign.fallback.impl
 * @Description: feign hystrix快速返回实现
 * @date 2017-09-06 16:25:50
 */
@Component
public class OrderFeignFallback implements FallbackFactory<OrderFeign>{

    private static final Logger log = LoggerFactory.getLogger(OrderFeignFallback.class);

    @Override
    public OrderFeign create(Throwable cause) {
        return new OrderFeign() {
            @Override
            public Result<List<Order>> selectList(OrderQuery orderQuery) {
                return Result.FALLBACK(cause);
            }

            @Override
            public Result<Order> getById(Long id) {
                return Result.FALLBACK(cause);
            }
        };

    }
}