package com.hommin.security.core;/**
 * Created by Hommin on 2018/3/14.
 */

import com.hommin.security.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Hommin
 * @ClassName: SecurityCoreConfig
 * @Description: core工程配置
 * @data 2018年03月14日 下午2:21
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {
}
