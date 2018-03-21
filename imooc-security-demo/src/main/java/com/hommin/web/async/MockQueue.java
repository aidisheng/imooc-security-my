package com.hommin.web.async;/**
 * Created by Hommin on 2018/3/11.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Hommin
 * @ClassName: MockQueue
 * @Description: 模拟消息队列（为测试DeferredResult）
 * @data 2018年03月11日 下午6:07
 */
@Component
public class MockQueue {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private String placeOrder;

    private String completeOrder;

    public String getPlaceOrder() {
        return placeOrder;
    }

    public void setPlaceOrder(String placeOrder) throws Exception {
        new Thread(() -> {
            logger.info("模拟消息队列接受到请求");
            this.placeOrder = placeOrder;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("模拟消息队列处理完请求");
            this.completeOrder = placeOrder;
        }).start();

    }

    public String getCompleteOrder() {
        return completeOrder;
    }

    public void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }
}
