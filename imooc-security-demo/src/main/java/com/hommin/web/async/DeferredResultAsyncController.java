package com.hommin.web.async;/**
 * Created by Hommin on 2018/3/11.
 */

import com.hommin.dto.User;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @author Hommin
 * @ClassName: DeferredResultAsyncController
 * @Description: 异步延迟处理controller
 * @data 2018年03月11日 下午6:14
 */
@RestController
public class DeferredResultAsyncController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder defferedResultHolder;

    @GetMapping("/order2")
    public DeferredResult<User> order() throws Exception {
        logger.info("主线程开始");

        String numeric = RandomStringUtils.randomNumeric(8);
        // 模拟发送消息队列请求
        mockQueue.setPlaceOrder(numeric);

        // 异步延迟处理消息key为numeric 的返回消息
        DeferredResult<User> result = new DeferredResult<>();
        defferedResultHolder.getMap().put(numeric, result);

        logger.info("主线程返回");
        return result;
    }
/*
console打印：
2018-03-11 18:42:43.196  INFO 3238 --- [nio-8060-exec-1] c.h.w.a.DeferredResultAsyncController    : 主线程开始
2018-03-11 18:42:43.200  INFO 3238 --- [      Thread-10] com.hommin.web.async.MockQueue           : 模拟消息队列接受到请求
2018-03-11 18:42:43.201  INFO 3238 --- [nio-8060-exec-1] c.h.w.a.DeferredResultAsyncController    : 主线程返回
2018-03-11 18:42:44.204  INFO 3238 --- [      Thread-10] com.hommin.web.async.MockQueue           : 模拟消息队列处理完请求
2018-03-11 18:42:44.250  INFO 3238 --- [       Thread-7] com.hommin.web.async.QueueListener       : 消息队列处理返回结果69240141
2018-03-11 18:42:44.256  INFO 3238 --- [       Thread-7] com.hommin.web.async.QueueListener       : 延迟处理完成69240141

浏览器返回：
{"id":"qwer","name":"hommin","pass":"456","birthday":1520764964250}

分析：
此案例采用的模拟消息队列的方式，所以MockQueue可以忽略。在实际应用中，将QueueListener中去监听消息队列，并将if判断条件对应更改即可。

实际需要关注的三部分：
1. DeferredResultHolder：这里定义了一个容器，以便能够通过key找到对应的result
2. DeferredResultAsyncController：接受请求controller, 发送消息，然后制造一个DeferredResult并扔到容器中，返回值就是DeferredResult。
3. QueueListener：监听消息队列，当监听到消息后，根据消息从容器中找到对应的DeferredResult并实际、最终返回。
 */

/*
特殊的：当使用DeferredResult或者Callable进行异步时，实际上是创建了多个线程去处理请求。拦截器只能拦截主线程的进与出，但是请求的实际返回是在主线程返回之后，毕竟是异步的嘛，所以一般的拦截器是不使用的。

在使用springmvc的javaconfig配置中一般会实现WebMvcConfigurerAdapter或者相同功能类，在类中复写configureAsyncSupport(AsyncSupportConfigurer configurer)方法，可以使用configurer去配置异步拦截器去异步拦截，还能设置线程池
 */

}
