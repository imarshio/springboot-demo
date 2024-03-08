package com.marshio.demo.spel.test;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONUtil;
import org.junit.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.*;
import java.util.regex.Pattern;

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

    @Test
    public void test03() {
        EvaluationContext context = new StandardEvaluationContext();
        Map<String, Object> map = new HashMap<>();
        map.put("content", "国产游戏公司在移动游戏研发、运营上有先发优势，随着海外市场移动化趋势，以及细分品类和长尾地区渗透率提升，游戏出海有望成为重要业绩增量。\n中信证券认为，全球供应链问题与疫情反复导致市场波动，应保持投资组合多样化以应对风险。\n中信证券认为，行业景气度提升，部分企业有望受益。");
        map.put("code", 200);
        map.put("msg", "success");
        context.setVariable("map", map);
        // var value = parser.parseExpression("(#map['data'].split('\\n')).?[!empty and trim().length()>0].![split('认为，')].?[length == 2].![{'orgName':[0],'summary':[1]}]").getValue(context);
        // var value = parser.parseExpression("((((#map['data'].split('\\n')).?[!empty and trim().length()>0]).![split('认为，')]).?[length == 2]).![{'orgName':[0],'summary':[1]}]").getValue(context);
        var value = parser.parseExpression("").getValue(context);
        assert value != null;
        System.out.println(value);
    }

    @Test
    public void test04() {
        EvaluationContext context = new StandardEvaluationContext();
        String content = "国产游戏公司在移动游戏研发、运营上有先发优势，随着海外市场移动化趋势，以及细分品类和长尾地区渗透率提升，游戏出海有望成为重要业绩增量，感谢您的建议。\n中信证券认为。";
        context.setVariable("value", content);

        var value = parser.parseExpression("#value.replaceAll('，不便一一说明，|，感谢您的建议。|，请您及时关注公司公告|，谢谢。|，谢谢关注！|，谢谢您的关注。|。谢谢！|[^，。？！]{2,100}请以公告为准，|[^。？：！]{0,100}(请|请您)关注[^。？：！]{0,100}后续披露.*|[^。？：！]{0,100}会及时进行披露.*|[^。？：！]{0,100}理解[^。？：！]{0,100}感谢.*|[^。？：！]{0,200}(公司官网为|投资者专线|不便对外公布|公司会积极关注|高度重视|回报广大投资者|持续地积极|宝贵建议|投资者进行交流|持续跟进).*|[^。？：！]{0,200}(可|密切|持续|敬请|请|请您)(查询|届时查看|理性投资|参考|关注|留意|参阅|查阅|详见|客观理性看待|谨慎理性投资|参见|为准).*|[^。？：！]{0,200}不便回复.*|[^。？：！]{0,200}公告编号：.*|[^。？：！]{0,200}如果您.*|[^。？：！]{0,200}顺势而为[^。？：！]{0,200}|[^。？：！]{0,200}投资者谨慎.*|[^。？：！]{0,200}投资者热线.*|[^。？：！]{2,100}一一[^。？：！]{0,100}回复.*|非常感谢[^，。？！]{2,100}的关注。|非常感谢您的关注和建议。|非常感谢您对公司的关注与支持！|感谢[^。？：！，]{0,100}关注。|感谢关注[^，。？！]{2,20}。|感谢关注！|感谢关注。|感谢[^，。？！]{1,100}(关注|支持|建议|提问)(，|。|！)?|请广大投资者注意投资风险。|谢谢(！|。)|祝您投资愉快！|公司一直致力于.*|敬请关注[^，。？！]{2,20}相关公告。|敬请投资者理性投资，注意风险。|具体进展请以.{1,50}为准.{1,50}|具体情况可在[^。？：！]{0,200}查询。|具体情况请以公司公告为准，谢谢！|具体请您.*|您好！|您好，|您好。|请知悉|如有相关事项触及应披露事项标准，本公司将严格按照相关信息披露准则要求，及时披露相关公告。|投资者您好|投资者您好，感谢关注。|投资者您好，感谢您的关注！|投资者您好，感谢您对我们公司的关注。|现就您关注的问题回复如下：|相关内容请阅.*|相关事项请以公司.*|详情请参阅公司相关公告。|谢谢!|谢谢！|谢谢$|谢谢。|谢谢关注！|谢谢关注。|谢谢您的关注。|谢谢您的关注与支持！|再次感谢.*的关注！|最新资讯[^，。？！]{2,100}|尊敬的投资者，|尊敬的投资者，您好。|尊敬的投资者：|尊敬的投资者您好，|尊敬的投资者您好，感谢您对公司的关注。|请投资者关注！|，谢谢您的关注与支持。|尊敬的投资者|谢谢您的关注和支持！|谢谢您的关注！|[^。：？！]{2,200}(投资者联系电话|具体情况详见公司|相关信息已在|如涉及|印章管理制度|详见公司披露|投资者关系热线|欢迎搜索|参见公司官网|公司一直致力于|关于产品的具体内容详见公司).*|谢谢您的建议和关注！|[^：。？！]{2,200}以公司[^。？！]{2,200}为准.*|您的相关建议已收到.*|投资者您好，|谢谢您的提问。|如未来触及应披露事项标准.*|公司严格按照定期报告披露要求编制.*|后续公司如出.*|敬请您注意投资风险.*|相关[^。？！]{2,200}请查看.*|请投资者注意投资风险|届时您可通过.*|，请您注意投资风险|敬请注意投资风险。|请以公司公告为准.*|非常感谢投资者对公司的关心与认可！','')").getValue(context);
        assert value != null;

        System.out.println(Pattern.compile("\n").matcher("<p>三友医疗金属增材制造椎间融合器系列获美国FDA510(K)认证\n</p>").replaceAll(""));
    }
}
