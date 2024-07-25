package com.marshio.demo.spel;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.expression.spel.support.StandardTypeLocator;

@SuppressWarnings("unused")
public class SpELUtil {

    private SpELUtil() {
        // singleton
    }

    private static final ExpressionParser parser = new SpelExpressionParser();

    public static Object parseExpressionAndGetValue(String expression, EvaluationContext context) {
        return parser.parseExpression(expression).getValue(null == context ? createStandardEvaluationContext() : context);
    }

    public static EvaluationContext createStandardEvaluationContext() {
        StandardEvaluationContext context = new StandardEvaluationContext();
        // 应对 Type cannot be found
        context.setTypeLocator(new StandardTypeLocator(SpELUtil.class.getClassLoader()));
        return context;
    }

    public static EvaluationContext transformContext(Object obj) {
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setTypeLocator(new StandardTypeLocator(SpELUtil.class.getClassLoader()));
        context.setRootObject(obj);
        return context;
    }
}
