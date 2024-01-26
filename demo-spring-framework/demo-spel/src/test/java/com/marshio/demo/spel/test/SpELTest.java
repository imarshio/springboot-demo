package com.marshio.demo.spel.test;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONUtil;
import org.junit.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.*;

public class SpELTest {

    // 定义 SpEL 解析器
    static final ExpressionParser parser = new SpelExpressionParser();

    @Test
    public void test01() {
        // some simple test case
        // 准备环境变量
        EvaluationContext context = new StandardEvaluationContext();

        // 简单类型
        context.setVariable("number", 1.0);
        context.setVariable("str", "ssss");
        context.setVariable("intel", 1);


        // 取context里的值 通过#开头，#key
        System.out.println(parser.parseExpression("#number").getValue(context));
        System.out.println(parser.parseExpression("#str").getValue(context));
        System.out.println(parser.parseExpression("#intel").getValue(context));

        // 数组类型
        String[] arr = new String[]{"1", "2", "3", "  "};
        context.setVariable("arr", arr);
        System.out.println(parser.parseExpression("#arr").getValue(context));
        // 过滤掉空值和长度小于0的数组
        System.out.println(Arrays.toString((String[]) parser.parseExpression("#arr.?[!empty and length()>0]").getValue(context)));
        // 过滤掉空值和去掉头尾空格后长度小于0的数组
        System.out.println(Arrays.toString((String[]) parser.parseExpression("#arr.?[!empty and trim().length()>0]").getValue(context)));

        // 字典类型
        Map<String, Object> map = new HashMap<>();
        map.put("data", "<p>2021年11月，据《证券时报》报道，中国证监会发布《关于推动提高上市公司质量的意见》，旨在提升A股市场板块质量。</p>");
        map.put("code", 200);
        map.put("msg", "success");
        context.setVariable("map", map);
        System.out.println(parser.parseExpression("#map").getValue(context));
        System.out.println(parser.parseExpression("#map['data']").getValue(context));
        System.out.println(parser.parseExpression("#map['code']").getValue(context));
        System.out.println(parser.parseExpression("#map['msg']").getValue(context));

        // 集合类型
        List<String> list = new ArrayList<>(2);
        list.add("111");
        list.add("test SpEL");
        context.setVariable("list", list);
        System.out.println(parser.parseExpression("#list").getValue(context));
        System.out.println(parser.parseExpression("#list[0]").getValue(context));
        System.out.println(parser.parseExpression("#list[1]").getValue(context));

        // more usage see https://docs.spring.io/spring-framework/docs/3.0.x/reference/expressions.html


    }

    @Test
    public void test02() {

        // 实际应用场景1：通过API获取到响应体（body），然后获取body里的data，在对data进行split操作
        String body = "{\"code\":200,\"data\":\"关于推动提高上市公司质量的意见\",\"msg\":\"SUCCESS\"}";
        EvaluationContext context = new StandardEvaluationContext();
        Map<String, Object> map = JSONUtil.toBean(body, new TypeReference<>() {
        }, true);

        context.setVariable("map", map);

        String value = parser.parseExpression("#map['data']").getValue(context, String.class);
        // 验证结果
        System.out.println(value);

        // 参照结果
        assert value != null;
        String[] split = value.split("\n");
        System.out.println(Arrays.toString(split));
        System.out.println(split.length);

        // 填充新的值
        context.setVariable("map", value);
        // 函数处理
        String[] splits = parser.parseExpression("#map.split('\n')").getValue(context, String[].class);
        System.out.println(Arrays.toString(splits));
        assert splits != null;
        System.out.println(splits.length);

        // 当然上面的用法中具体的对象都可以用Object代替，只要你确定你的表达式能正常处理即可
    }
}
