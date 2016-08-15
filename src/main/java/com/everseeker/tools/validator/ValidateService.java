package com.everseeker.tools.validator;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by everseeker on 16/8/15.
 */
public class ValidateService {
    //解析的入口
    public static Map<String, String> valid(Object object) {
        Map<String, String> map = new HashMap<String, String>();
        //获取object的class.
        Class<? extends Object> clazz = object.getClass();
        //获取类的所有申明的字段, 包括public, private, protected, 但是不包括父类的字段.
        Field[] fields = clazz.getDeclaredFields();
        //遍历属性
        for (Field field : fields) {
            //对于private成员变量, 修改访问权限
            field.setAccessible(true);
            try {
                validate(field, object);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                map.put(field.getName(), e.getMessage());
            }
            field.setAccessible(false);
        }

        return map.isEmpty() ? null : map;
    }

    public static void validate(Field field, Object object) throws Exception {
        //获取对象的字段的注解信息
        DataValidator dv = field.getAnnotation(DataValidator.class);
        if (dv == null)
            return;

        //获得字段的值
        Object value = field.get(object);
        String msg = dv.msg().equals("") ? field.getName() : dv.msg();

        //开始解析
        if (!dv.notNull()) {
            if (value == null) {
                throw new Exception(msg + "不能为空");
            }
        }

        if (value.toString().length() > dv.max() && dv.max() != 0) {
            throw new Exception(msg + "长度不能超过" + dv.max());
        }

        if (value.toString().length() < dv.min() && dv.min() != 0) {
            throw new Exception(msg + "长度不能小于" + dv.min());
        }

        if (dv.type() != RegexType.NONE) {
            switch (dv.type()) {
                case EMAIL:
                    if (!RegexUtils.isEmail(value.toString())) {
                        throw new Exception(msg + "格式不正确");
                    }
                    break;
                case IP:
                    if (!RegexUtils.isIp(value.toString())) {
                        throw new Exception(msg + "地址格式不正确");
                    }
                    break;
                case DIGITS:
                    if (!RegexUtils.isDigits(value.toString())) {
                        throw new Exception(msg + "不是数字");
                    }
                    break;
                case PHONE:
                    if (!RegexUtils.isPhone(value.toString())) {
                        throw new Exception(msg + "手机号码不正确");
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
