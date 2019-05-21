package com.etollpay.srpc.tool.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 数据合规性校验器
 * 使用Bean Validation
 */
public class DataStandardChecker {
    private static Logger log = LoggerFactory.getLogger(DataStandardChecker.class);

    /**
     * 校验数据合规性
     * @param bean  需要校验的Bean
     * @param list  将未通过校验的对象放入改列表中
     * @param <T>
     * @return      错误信息的List，如通过校验则返回null
     */
    public <T> List<String> check(T bean, List<T> list) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(bean);
        List<String> errors = null;
        if (!violations.isEmpty()) {
            errors = new ArrayList<String>();
            for (Iterator<ConstraintViolation<T>> it = violations.iterator(); it.hasNext(); ) {
                ConstraintViolation<T> violation = it.next();
                errors.add(violation.getPropertyPath().toString() + " " + violation.getMessage());
            }
            if (list != null && !list.contains(bean)) {
                list.add(bean);
            }
        }
        return errors;
    }

    /**
     * 校验数据合规性
     * @param bean  需要校验的Bean
     * @param <T>
     * @return      错误信息的List，如通过校验则返回null
     */
    public <T> List<String> check(T bean) {
        return check(bean, null);
    }
}

