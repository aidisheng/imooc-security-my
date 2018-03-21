package com.hommin.web.async;/**
 * Created by Hommin on 2018/3/11.
 */
import com.hommin.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.concurrent.Callable;

/**
 * @author Hommin
 * @ClassName: CallableAsyncController
 * @Description: callable方式的异步处理请求demo
 * @data 2018年03月11日 下午5:50
 */
@RestController
public class CallableAsyncController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/order1")
    public Callable<User> order(){
        logger.info("主线程开始");
        Callable<User> callable = new Callable<User>() {
            @Override
            public User call() throws Exception {
                logger.info("副线程开始");
                Thread.sleep(1000);
                logger.info("副线程结束");
                return new User("asdf", "hommin", "123", Calendar.getInstance().getTime());
            }
        };
        logger.info("主线程返回");
        return callable;
    }
    /*
console打印结果：
2018-03-11 18:01:34.456  INFO 3169 --- [nio-8060-exec-1] c.h.web.async.CallableAsyncController    : 主线程开始
2018-03-11 18:01:34.457  INFO 3169 --- [nio-8060-exec-1] c.h.web.async.CallableAsyncController    : 主线程返回
2018-03-11 18:01:34.463  INFO 3169 --- [      MvcAsync1] c.h.web.async.CallableAsyncController    : 副线程开始
2018-03-11 18:01:35.467  INFO 3169 --- [      MvcAsync1] c.h.web.async.CallableAsyncController    : 副线程结束

页面返回值：
{"id":"asdf","name":"hommin","pass":"123","birthday":1520762495467}

分析：
主线程中单开副线程，不处理任何业务，直接将副线程作为方法请求返回。springboot经过处理，在副线程处理完后，将副线程的返回结果作为最终结果。（实际还是很简单，缺点的是代码不够优雅）
     */
}
