package com.hommin.security.validate.code;/**
 * Created by Hommin on 2018/3/21.
 */

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author Hommin
 * @ClassName: ImageCode
 * @Description: 存储验证码图片等信息
 * @data 2018年03月21日 上午8:42
 */
public class ImageCode {

    private BufferedImage image;
    private String code;

    private LocalDateTime expireTime;

    public ImageCode(BufferedImage image, String code, Integer expireIn) {
        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        this.image = image;
        this.code = code;
        this.expireTime = expireTime;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }
}
