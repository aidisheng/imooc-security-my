package com.hommin.web.async;/**
 * Created by Hommin on 2018/3/11.
 */

import com.hommin.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Calendar;

/**
 * @author Hommin
 * @ClassName: QueueListener
 * @Description: 队列监听器（模拟接受消息队列的返回消息）
 * @data 2018年03月11日 下午6:24
 */
@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent>{
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MockQueue mockQueue;
    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        new Thread(() -> {
            while (true){
                if(!StringUtils.isEmpty(mockQueue.getCompleteOrder())){
                    logger.info("消息队列处理返回结果"+mockQueue.getCompleteOrder());
                    DeferredResult<User> result = deferredResultHolder.getMap().get(mockQueue.getPlaceOrder());

                    // 根据消息队列消息，延迟处理业务逻辑并返回
                    result.setResult(new User("qwer", "hommin", "456", Calendar.getInstance().getTime()));
                    logger.info("延迟处理完成"+mockQueue.getCompleteOrder());

                    mockQueue.setCompleteOrder(null);
                }else {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


    }
}
