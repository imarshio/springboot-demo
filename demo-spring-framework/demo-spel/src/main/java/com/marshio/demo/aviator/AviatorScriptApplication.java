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
        evaluator.setCachedExpressionByDefault(true);
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

        String originalContent = "\n观点一：国务院国资委提出，2024年将对中央企业全面实施“一企一策”考核，增加反映价值创造能力的针对性考核指标，引导企业实现高质量发展。 观点二：国资委将全面推开上市公司市值管理考核，引导企业更加重视上市公司的内在价值和市场表现，传递信心、稳定预期，更好地回报投资者。 观点三：2023年以来，国资委对央国企的考核体系进行调整优化，如将主要经营指标由原来的“两利四率”调整为“一利五率”，以更好地衡量企业权益资本的投入产出效率，并放松对资产负债率的考核要求。 观点四：电力行业央国企占比高，作为能源保供的“压舱石”，估值仍有提升空间。申万电力行业A股上市的98家企业中，中央和地方国有企业共有74家，占比达75.5%。截至2024年1月29日，申万电力行业PE（TTM）与PB中值均低于全部A股的水平。 观点五十：电力行业有望迎来快速发展期，加上国家对于市值管理的考核有望引导电力央企更加关注盈利质量和股东回报，电力行业估值有望进一步提升。\n";
        env.put("originalContent", originalContent);

        // String expression = "if originalContent.length > 0 { return originalContent[0]; } return '';";
        String expression = "let str = string.split(originalContent,'\n'); let list = seq.list(); for s in str { if s == '' {continue;} if string.contains(s, '。') {seq.add_all(list, string.split(s, '。'));}} let rt = seq.list();for s in list {seq.add(rt, string.replace_first(s, '观点.{1,2}：','') + '。');} return rt;";

        // System.out.println(Arrays.toString(originalContent.split("\n")));
        System.out.println(JSONUtil.toJsonStr(evaluator.execute(expression, env)));

    }

}
