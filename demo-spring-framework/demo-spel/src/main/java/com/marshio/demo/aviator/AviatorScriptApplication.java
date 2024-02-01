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

        String originalContent = "德邦证券认为，CCER市场重启，国际-clean energy日强调逐步淘汰化石燃料，湖南省拓展风光储氢等新能源应用场景，ESG产品规模扩大，国际可持续准则委员会委员建议中国企业尽早采用ISSB准则披露ESG信息。\n\n华福证券认为，储能项目容量环比增长，中标均价下降，火电核准量较高省份为广东、安徽、新疆；发电量与用电量增速放缓，装机容量和利用小时数同比变化。\n\n银河证券，CCER正式重启，与CEA市场互补衔接，未来将逐步纳入高耗能高排放行业，带动CCER需求增长，预计2024/2030年碳交易覆盖额为60亿吨/106亿吨，碳价有望长期上涨。\n";
        env.put("originalContent", originalContent);

        // String expression = "if originalContent.length > 0 { return originalContent[0]; } return '';";
        String expression = "let contents = string.split(originalContent, '\n'); let stringModules = require('stringModules'); let list = seq.list(); for s in contents { if !stringModules.isBlank(s) && string.indexOf(s,'认为') > 0 { let ret = seq.map(); let index = string.indexOf(s,'认为，'); seq.add(ret,'orgName',string.substring(s,0,index)); seq.add(ret,'summary',string.substring(s,index + 3)); seq.add(list, ret); }} return list;";

        // System.out.println(Arrays.toString(originalContent.split("\n")));
        System.out.println(JSONUtil.toJsonStr(evaluator.execute(expression, env)));

    }

}
