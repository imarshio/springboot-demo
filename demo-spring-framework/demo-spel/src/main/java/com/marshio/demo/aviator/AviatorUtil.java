package com.marshio.demo.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.AviatorEvaluatorInstance;
import com.googlecode.aviator.runtime.JavaMethodReflectionFunctionMissing;

import java.util.Map;

@SuppressWarnings({"unchecked", "unused"})
public class AviatorUtil {

    private final static AviatorEvaluatorInstance evaluator = AviatorEvaluator.newInstance();

    static {
        // Support for calling Java class methods.
        // Please use with care, there will be performance problems.
        evaluator.setFunctionMissing(JavaMethodReflectionFunctionMissing.getInstance());
        evaluator.setCachedExpressionByDefault(true);
    }

    /**
     * 执行表达式后，默认缓存
     *
     * @param expression 表达式
     * @param env        环境变量
     * @param <T>        表达式结果的数据类型
     * @return 表达式执行结果
     */
    public static <T> T execute(String expression, Map<String, Object> env) {
        return execute(expression, env, true);
    }

    public static <T> T execute(String expression, Map<String, Object> env, boolean cached) {
        return (T) evaluator.execute(expression, env, cached);
    }
}
