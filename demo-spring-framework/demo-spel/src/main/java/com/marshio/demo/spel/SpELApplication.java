package com.marshio.demo.spel;


import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpELApplication {

    public static void main(String[] args) {
        // string 解析
        ExpressionParser parser = new SpelExpressionParser();

        // map 数据准备
        Map<String, Object> map = new HashMap<>();
        map.put("title", "<p>2021年11月，据《证券时报》报道，中国证监会发布《关于推动提高上市公司质量的意见》，旨在提升A股市场板块质量。</p>");
        map.put("code", 200);
        map.put("msg", "success");
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(map);

        EvaluationContext context = SpELUtil.createStandardEvaluationContext();
        context.setVariable("input", list);

        System.out.println(parser.parseExpression(" #input[1]['title']").getValue(context));
    }
}
