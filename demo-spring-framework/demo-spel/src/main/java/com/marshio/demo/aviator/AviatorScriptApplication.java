package com.marshio.demo.aviator;

import cn.hutool.json.JSONUtil;
import com.googlecode.aviator.*;
import com.googlecode.aviator.annotation.Import;
import com.googlecode.aviator.runtime.JavaMethodReflectionFunctionMissing;
import com.googlecode.aviator.runtime.type.AviatorFunction;

import java.util.*;

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
        String originalContent = "\n观点一：2023年全球智能手机面板出货约21.1亿片，同比增长约18.1%，创历史新高。英特尔、德州仪器等企业供应商接单与出货平淡，TrendForce集邦咨询预估今年第一季MLCC供应商出货总量仅达11,103亿颗，环比减少7%。2023年全球折叠手机出货量1,590万支，年增25%，占整体智能手机市场约1.4%。 观点二：上周，美国费城半导体指数和中国台湾半导体指数均呈现上涨趋势。2月23日，美国费城半导体指数为4615.03，周涨幅为1.93%，中国台湾半导体指数为470.28，周涨幅为2.80%。上周，申万半导体指数上涨5.09%，跑赢沪深300指数1.38个百分点。 观点三：我们认为当前消费电子有所回暖，半导体国产化进程持续推进，此外，AI带来的算力产业链也将持续受益，半导体行业当前处于周期筑底阶段，待下游行情复苏，将推动半导体新一轮上升周期，看好行情复苏及AI算力产业链两条主线。推荐卓胜微、芯碁微装、北方华创、中科飞测、华海清科等；关注AI+半导体投资机会，推荐源杰科技、长光华芯。\n";
        env.put("originalContent", originalContent);

        // String expression = "if originalContent.length > 0 { return originalContent[0]; } return '';";
        // String expression = "let str = string.split(originalContent,'观点.{1,2}：'); let list = seq.list(); for s in str { if s == '' {continue;} if string.contains(s, '。') {seq.add_all(list, string.split(s, '。'));}} let rt = seq.list();for s in list {seq.add(rt, string.replace_first(s, '观点.{1,2}：','') + '。');} return rt;";
        String expression = "let str = string.split(originalContent,'观点.{1,2}：'); let list = seq.list(); for s in str { s = trim(s);if s == '' {continue;} seq.add(list, s);} return list;";
        // expression.split()
        expression.trim();
        // System.out.println(Arrays.toString(originalContent.split("\n")));
        System.out.println(JSONUtil.toJsonStr(evaluator.execute(expression, env)));
        // System.out.println(JSONUtil.toJsonStr(originalContent.split("观点.{1,2}：")));

    }

}
