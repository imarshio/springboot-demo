package com.marshio.demo.spel;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

@SuppressWarnings("unused")
public class SpELUtil {

    private SpELUtil() {
        // singleton
    }

    private static final ExpressionParser parser = new SpelExpressionParser();

    public static Object parseExpressionAndGetValue(String expression, EvaluationContext context) {
        return parser.parseExpression(expression).getValue(context);
    }
}
