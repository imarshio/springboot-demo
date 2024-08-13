package com.marshio.demo.aviator;

import cn.hutool.json.JSONUtil;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.AviatorEvaluatorInstance;
import com.googlecode.aviator.EvalMode;
import com.googlecode.aviator.Options;
import com.googlecode.aviator.runtime.JavaMethodReflectionFunctionMissing;

import java.util.HashMap;
import java.util.Map;

public class AviatorScriptApplication {
    public static void main(final String[] args) throws NoSuchMethodException, IllegalAccessException {
        // 创建执行器
        AviatorEvaluatorInstance evaluator = AviatorEvaluator.newInstance(EvalMode.INTERPRETER);
        // 打开跟踪执行
        evaluator.setOption(Options.TRACE_EVAL, false);
        evaluator.setFunctionMissing(JavaMethodReflectionFunctionMissing.getInstance());
        evaluator.setCachedExpressionByDefault(false);
        evaluator.addModule(AviatorModules.StringModule.class);

        // String[] str = new String[3];
        // str[0] = "aaaa";
        // str[1] = "bbbb";
        // str[2] = "ccccc";
        // List<String> str = new ArrayList<>();
        // str.add("aaa");
        // str.add("bbb");
        // str.add("ccc");
        // 定义表达式所需的环境变量
        Map<String, Object> env = new HashMap<>();

        // String originalContent = "\n观点一：国务院国资委提出，2024年将对中央企业全面实施“一企一策”考核，增加反映价值创造能力的针对性考核指标，引导企业实现高质量发展。 观点二：国资委将全面推开上市公司市值管理考核，引导企业更加重视上市公司的内在价值和市场表现，传递信心、稳定预期，更好地回报投资者。 观点三：2023年以来，国资委对央国企的考核体系进行调整优化，如将主要经营指标由原来的“两利四率”调整为“一利五率”，以更好地衡量企业权益资本的投入产出效率，并放松对资产负债率的考核要求。 观点四：电力行业央国企占比高，作为能源保供的“压舱石”，估值仍有提升空间。申万电力行业A股上市的98家企业中，中央和地方国有企业共有74家，占比达75.5%。截至2024年1月29日，申万电力行业PE（TTM）与PB中值均低于全部A股的水平。 观点五十：电力行业有望迎来快速发展期，加上国家对于市值管理的考核有望引导电力央企更加关注盈利质量和股东回报，电力行业估值有望进一步提升。\n";
        // String originalContent = "    观点一：本周食品指数（中信）累计涨跌幅+5.19%，涨跌幅前三的个股为：惠发食品（+32.97%）、品渥食品（+24.06%）、佳隆股份（+22.34%）；涨跌幅后三的个股为涪陵榨菜（-1.63%）、盐津铺子（-2.09%）、洽洽食品（-2.55%）     观点二：返乡人群带来礼赠需求，聚餐拉动餐饮消费场景。据交通运输部数据，1月26日-2月26日铁路/民航客运量同比增长55%+/65%+，相比2019年增长23%/18%左右。同时，根据美团数据，生活服务业的春节假期日均消费规模同比+36%，较2019年同比+155%以上。 观点三：返乡人员大幅增长，带来大众品礼赠需求；旅游、返乡聚餐等拉动餐饮消费场景。礼赠需求推荐乳制品龙头伊利股份，餐饮产业链推荐千味央厨、安井食品、宝立食品。";
        String originalContent = "\n浦银国际认为,游戏行业上半年平稳增长,ChinaJoy参展人数创新高,有望迎来新一轮产品周期。\n\n\n\n华泰证券认为,游戏行业增长趋稳，新游表现优异，出海前景广阔。\n\n\n\n招商证券认为,版号发放增多提振游戏行业信心，推荐关注相关公司。\n";
        env.put("originalContent", originalContent);

        // String expression = "if originalContent.length > 0 { return originalContent[0]; } return '';";
        // String expression = "let str = string.split(originalContent,'观点.{1,2}：'); let list = seq.list(); for s in str { if s == '' {continue;} if string.contains(s, '。') {seq.add_all(list, string.split(s, '。'));}} let rt = seq.list();for s in list {seq.add(rt, string.replace_first(s, '观点.{1,2}：','') + '。');} return rt;";
        String expression = "let contents = string.split(originalContent, '\\n'); let str = require('str'); let list = seq.list(); for s in contents { if !str.isBlank(s) && string.indexOf(s,'认为') > 0 { let ret = seq.map(); let index = string.indexOf(s,'认为，'); seq.add(ret,'orgName',string.substring(s,0,index)); seq.add(ret,'summary',string.substring(s,index + 3)); seq.add(list, ret); }} return list;";
        // expression.split()
        expression.trim();
        // System.out.println(Arrays.toString(originalContent.split("\n")));
        System.out.println(JSONUtil.toJsonStr(evaluator.execute(expression, env)));
        // System.out.println(JSONUtil.toJsonStr(originalContent.split("观点.{1,2}：")));
        // Map<String, Object> env = new HashMap<>();
        // env.put("variable", "\\n浦银国际认为,游戏行业上半年平稳增长,ChinaJoy参展人数创新高,有望迎来新一轮产品周期。\\n\\n\\n\\n华泰证券认为,游戏行业增长趋稳，新游表现优异，出海前景广阔。\\n\\n\\n\\n招商证券认为,版号发放增多提振游戏行业信心，推荐关注相关公司。\\n");
        // AviatorUtil.execute("let content = variable.data;let contents = string.split(content, '\\n'); let str = require('str'); let list = seq.list(); for s in contents { if !str.isBlank(s) && string.indexOf(s,'认为') > 0 { let ret = seq.map(); let index = string.indexOf(s,'认为，'); seq.add(ret,'orgName',string.substring(s,0,index)); seq.add(ret,'summary',string.substring(s,index + 3)); seq.add(list, ret); }} return list;", env);
        // System.out.println();

    }


}
