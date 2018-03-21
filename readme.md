# 功能
## 参数校验器
com.hommin.dto.User 定义校验规则

com.hommin.web.controller.UserController.getUser()  实施校验

## 自定义校验器
com.hommin.validator.MyConstraint 自定义校验

com.hommin.validator.MyConstraintValidator 自定义校验器

## 异常控制器
com.hommin.web.exceptionhandler.ControllerExcetionHandler

## 过滤器
com.hommin.web.filter.TimeFilter 定义过滤器

com.hommin.web.WebConfig.timeFilter() 注册过滤器

## 拦截器
com.hommin.web.interceptor.TimeInterceptor 定义拦截器

com.hommin.web.WebConfig.addInterceptors() 注册拦截器（注意实现接口）

## 切面
com.hommin.web.aspect.TimeAspect 无需注册

## 文件上传与下载
com.hommin.web.controller.FileController.uploadFile() 上传

## 异步处理请求
### callable(创建副线程处理)
com.hommin.web.async.CallableAsyncController 

### DeferredResult(延迟处理，可用于消息队列)
com.hommin.web.async.DeferredResultHolder 延迟请求处理DeferredResult容器，便于后期查找对应DeferredResult
com.hommin.web.async.DeferredResultAsyncController 接受请求，创建DeferredResult并放于容器
com.hommin.web.async.QueueListener  监听消息队列，查找容器，并最终返回

### swagger自动生成文档
com.hommin.web.controller.UserController @ApiOperation方法解释注解；@ApiParam参数解释注解

com.hommin.DemoApplication 启用swagger(@EnableSwagger)

# spring security
## 用户自定义认证
com.hommin.security.browser.security.UserDetailsServiceImpl 获取登录用户
com.hommin.security.browser.security.MyUserDetails   校验用户逻辑
com.hommin.web.WebConfig.passwordEncoder() & com.hommin.security.UserDetailsServiceImpl.passwordEncoder 密码加密

## 自定义页面
com.hommin.security.browser.security.BrowserSecurityController 请求判断区别跳转

com.hommin.security.browser.BorwserSecurityConfig 配置自定义登录页面

### 灵活配置自定义页面（ConfigurationProperties）
com.hommin.security.core.properties.SecurityProperties ConfiguritionProperties入口
com.hommin.security.core.properties.BrowserProperties  入口子类

com.hommin.security.core.SecurityCoreConfig 启用ConfiguritionProperties

## 登录成功后的处理
com.hommin.security.browser.security.authentication.MyLoginSuccessHandler 登录成功处理（登录请求时ajax访问还是页面提交判断）

## 登录失败后的处理
com.hommin.security.browser.security.authentication.MyLoginFailureHandler 登录失败处理（登录请求时ajax访问还是页面提交判断）

## 图片验证码



