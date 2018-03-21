package com.hommin.validator;/**
 * Created by Hommin on 2018/3/1.
 */

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Hommin
 * @ClassName: MyConstraintValidator
 * @Description: 校验注解的校验器
 * @data 2018年03月01日 下午5:44
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, String> {
    @Override
    public void initialize(MyConstraint myConstraint) {
        System.out.println("MyConstraint init");
    }

    @Override
    public boolean isValid(String str, ConstraintValidatorContext constraintValidatorContext) {
        if (str == null || !"Hommin".equalsIgnoreCase(str)) {
            return false;
        }
        return true;
    }
}
