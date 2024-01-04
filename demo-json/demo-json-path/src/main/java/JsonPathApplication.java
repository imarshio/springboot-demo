import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author marshio
 * @desc
 * @create 2024-01-03 17:09
 */
@Slf4j
public class JsonPathApplication {

    public static void main(String[] args) {
        String json_schema = "{\"$id\":\"http://localhost/aigc_content_schema.json\",\"$schema\":\"http://json-schema.org/draft-07/schema#\",\"description\":\"AIGC Content Schema\",\"title\":\"AIGC Content Schema\",\"type\":\"object\",\"pureTextSwitch\":true,\"additionalProperties\":false,\"properties\":{\"plateCode\":{\"type\":\"string\",\"description\":\"板块代码\",\"index\":1,\"widget\":\"text\",\"editable\":false},\"plateName\":{\"type\":\"string\",\"description\":\"板块名称\",\"index\":2,\"widget\":\"text\",\"editable\":false},\"publishDate\":{\"type\":\"string\",\"description\":\"发布日期\",\"index\":3,\"widget\":\"date\",\"editable\":false},\"summaryNews\":{\"type\":\"string\",\"description\":\"消息面总结\",\"index\":4,\"widget\":\"multitext\",\"editable\":true,\"api\":{\"url\":\"https://qiankunquan-sit.deepq.tech/runtime/apps/893979b656/form/run\",\"method\":\"POST\",\"paramsPath\":{\"plate\":\"ext->>'$.plate_code'\",\"content\":\"ext->>'$.aigcOriginalContent.summaryNews'\"},\"paramsValue\":{\"plate\":\"\",\"content\":\"\"}}},\"reportSummary\":{\"type\":\"string\",\"description\":\"研报总结\",\"index\":5,\"widget\":\"multitext\",\"editable\":true},\"reportStatistic\":{\"type\":\"string\",\"description\":\"研报统计\",\"index\":6,\"widget\":\"multitext\",\"editable\":true},\"reportAnalysis\":{\"type\":\"array\",\"description\":\"研报解读\",\"index\":7,\"items\":[{\"$ref\":\"#/$defs/analysisContent\"}]},\"relatedStocks\":{\"type\":\"array\",\"description\":\"相关股票\",\"index\":8,\"items\":[{\"$ref\":\"#/$defs/relatedStock\"}]},\"tags\":{\"$ref\":\"#/$defs/tags\",\"description\":\"标签\",\"index\":9}},\"required\":[\"plateCode\",\"plateName\",\"summaryNews\",\"reportSummary\",\"reportStatistic\",\"reportAnalysis\",\"relatedStocks\",\"tags\"],\"$defs\":{\"analysisContent\":{\"type\":\"object\",\"description\":\"研报解读\",\"properties\":{\"title\":{\"type\":\"string\",\"description\":\"研报标题\",\"index\":1,\"widget\":\"text\",\"editable\":true},\"rating\":{\"$ref\":\"#/$defs/ratingEnum\",\"description\":\"研报评级\",\"index\":2,\"widget\":\"text\",\"editable\":true},\"viewpoints\":{\"type\":\"array\",\"description\":\"观点\",\"items\":{\"type\":\"string\",\"widget\":\"multitext\",\"editable\":true,\"aigcAPI\":{\"url\":\"https://ai-doc.deepq.tech/llm/gpt\",\"method\":\"POST\",\"paramsPath\":{\"promptId\":\"1720363401114288128\",\"singleContent\":\"ext->'$.aigcOriginalContent.viewpoint[*]'\"},\"paramsValue\":{\"promptId\":\"1720363401114288128\",\"singleContent\":\"\"}}},\"index\":3}}},\"relatedStock\":{\"type\":\"object\",\"description\":\"相关股票\",\"properties\":{\"code\":{\"type\":\"string\",\"description\":\"股票代码\",\"index\":1,\"widget\":\"text\",\"editable\":true},\"name\":{\"type\":\"string\",\"description\":\"股票名称\",\"index\":2,\"widget\":\"text\",\"editable\":true},\"desc\":{\"type\":\"string\",\"description\":\"股票描述\",\"index\":3,\"widget\":\"text\",\"editable\":true},\"changeRatio\":{\"type\":\"string\",\"description\":\"股票涨跌幅\",\"index\":4,\"widget\":\"text\",\"editable\":true},\"tags\":{\"$ref\":\"#/$defs/tags\",\"description\":\"标签\",\"index\":5}}},\"tags\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"widget\":\"text\",\"editable\":false}},\"ratingEnum\":{\"enum\":[\"看好\",\"中立\",\"谨慎\",\"无评级\"]}}}";
        String json_content = "{\n" +
                "  \"plateCode\": \"991002\",\n" +
                "  \"plateName\": \"工程咨询服务\",\n" +
                "  \"summaryNews\": \"近期暂无消息面。\",\n" +
                "  \"reportSummary\": \"申万宏源证券研报认为，中央金融工作会议指出加快&ldquo;三大工程&rdquo;建设，城中村改造、保障房建设有望提速，地产产业链迎来布局窗口，相关行业公司有望受益。同时，国内地方政府债务方案的推出和国债的发行也为国央企提供了配置价值。天风证券研报认为，玻纤行业正筑底，关注国内需求恢复和新产能投放，电子纱电子布价格或有持续向上机会，中长期玻纤板块具有较好性价比。\",\n" +
                "  \"reportStatistic\": \"截止12月25日，近一个月，工程咨询服务行业总共有84家券商研报覆盖，其中74家看好，6家维持中立，0家评级为谨慎，4家无评级。\",\n" +
                "  \"reportAnalysis\": [\n" +
                "    {\n" +
                "      \"title\": \"长江证券：他山之石系列一：美国设计咨询行业\",\n" +
                "      \"rating\": \"看好\",\n" +
                "      \"viewpoints\": [\n" +
                "        \"美国设计咨询行业具有一定的集中度，大型公司在市场份额上逐渐增加，龙头公司的营收增速快于整个行业。改造和翻新设计项目的份额逐年增加，特别是在工商业和公共福利建筑行业。\",\n" +
                "        \"美国设计行业的盈利能力相比90年代大幅提升。设计咨询公司斯坦泰克通过并购扩大业务范围，并采取融资策略实现内生增长与国际市场开拓，致力于平衡风险和收益的战略布局。\",\n" +
                "        \"美国设计咨询行业的全流程服务包括方案设计、设计开发、施工文件、招标谈判和施工管理。施工文件阶段费用占整个设计阶段投资的40%，设计开发阶段耗时不到六个月。住宅、办公楼和医疗是行业下游主要的收入来源，占比约为四成。\"\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"rating\": \"看好\",\n" +
                "      \"title\": \"申万宏源证券：申万宏源建筑周报：广州城改条例率先启动，重视城改机遇\",\n" +
                "      \"viewpoints\": [\n" +
                "        \"本周建筑装饰板块表现强于大市，其中装饰幕墙、生态园林等子行业涨幅较大。而基建国企和设计咨询子行业的年涨幅最大。\",\n" +
                "        \"广州市人大常委会公开征求意见推进城中村改造，上海市发布土地资源利用意见，城改和保障房建设有望加速。\",\n" +
                "        \"中央金融工作会议提出加快城中村改造和保障房建设，地产产业链有望迎来布局机会，建筑设计、投融资+建造和房屋施工企业将受益。\"\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"rating\": \"看好\",\n" +
                "      \"title\": \"天风证券：玻纤周跟踪：价格持续底部盘整，需求维持平稳\",\n" +
                "      \"viewpoints\": [\n" +
                "        \"目前玻纤行业仍处于磨底阶段，但价格下滑空间有限，需重点关注国内需求恢复节奏和新产能投放节奏。\",\n" +
                "        \"电子纱市场稳定，多数厂按需采购，市场观望心态较强。预计下周电子纱厂将继续稳价出货为主。\",\n" +
                "        \"消费电子需求有复苏迹象，关注下游补库需求和CCL、PCB开工回升节奏，电子纱价格或有持续向上机会。同时，华为Mate60系列新产品发布和联想AI PC等产品的推出，也能提振消费电子需求。\"\n" +
                "      ]\n" +
                "    }\n" +
                "  ],\n" +
                "  \"relatedStocks\": [\n" +
                "    {\n" +
                "      \"changeRatio\": \"-0.58%\",\n" +
                "      \"code\": \"600720\",\n" +
                "      \"desc\": \"水泥、商品混凝土的研究、开发、制造、销售。\",\n" +
                "      \"name\": \"祁连山\",\n" +
                "      \"tags\": [\n" +
                "        \"most_related\"\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"changeRatio\": \"8.84%\",\n" +
                "      \"code\": \"300492\",\n" +
                "      \"desc\": \"提供建筑工程设计及相关咨询服务\",\n" +
                "      \"name\": \"华图山鼎\",\n" +
                "      \"tags\": []\n" +
                "    },\n" +
                "    {\n" +
                "      \"changeRatio\": \"8.26%\",\n" +
                "      \"code\": \"836208\",\n" +
                "      \"desc\": \"从事工程造价咨询、工程财务咨询、工程管理咨询、工程招标代理、工程项目管理、系统开发与集成等业务。\",\n" +
                "      \"name\": \"青矩技术\",\n" +
                "      \"tags\": []\n" +
                "    }\n" +
                "  ],\n" +
                "  \"tags\": []\n" +
                "}";
        String json_ext_data = "{}";

        // 组合
        String combinedJson = combined(json_schema, json_content, json_ext_data);

        log.info(combinedJson);
        // 拆除
        dismantle(combinedJson);

    }

    private static String combined(String jsonSchema, String jsonContent, String jsonExtData) {
        JSONObject schemaJson = new JSONObject(jsonSchema);
        JSONObject contentJson = new JSONObject(jsonContent);
        JSONObject extDataJson = new JSONObject(jsonExtData);
        // 解析json schema
        processJsonSchema(schemaJson.getJSONObject("properties"), schemaJson.getJSONObject("$defs"));
        // 处理schemaJson下properties节点
        processJson(schemaJson.get("properties"), schemaJson, contentJson, extDataJson);

        return schemaJson.toString();
    }

    private static void processJsonSchema(Object json, JSONObject defs) {
        // 替换$ref为实体
        if (json instanceof JSONArray) {

        } else if (json instanceof JSONObject) {
            JSONObject jo = (JSONObject) json;
            jo.keySet().forEach(key -> {
                Object value = jo.get(key);
                if ("$ref".equals(key)) {
                    // 获取值
                    JSONObject replacement = getReplacement(defs, (String) value);
                    // 把replacement里的key-value逐一放到当前对象

                }
            });
        } else {
            // k-v
            // if
        }
    }

    private static JSONObject getReplacement(JSONObject defs, String value) {
        // value == #/$defs/analysisContent
        // defs
        String key = value.substring(value.lastIndexOf("//"));
        return defs.getJSONObject(key);
    }

    private static void processJson(Object obj, JSONObject schemaJson, JSONObject contentJson, JSONObject extDataJson) {
        if (obj instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) obj;
            jsonObject.keySet().forEach(key -> {
                Object value = jsonObject.get(key);
                if (value instanceof String) {
                    String strValue = (String) value;
                    if (strValue.startsWith("")) {

                    }
                    if (strValue.startsWith("$.")) {
                        // This is a jsonPath, get the value from contentJson
                        Object contentValue = JsonPath.read(contentJson.toString(), strValue);
                        jsonObject.put(key, contentValue);
                    } else if (extDataJson.has(strValue)) {
                        // This is a key in extDataJson, get the value from extDataJson
                        Object extDataValue = extDataJson.get(strValue);
                        jsonObject.put(key, extDataValue);
                    }
                } else {
                    // This is a nested object or array, process it recursively
                    processJson(value, contentJson, extDataJson, extDataJson);
                }
            });
        } else if (obj instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray) obj;
            for (int i = 0; i < jsonArray.length(); i++) {
                Object value = jsonArray.get(i);
                processJson(value, contentJson, extDataJson, extDataJson);
            }
        }
    }

    private static void dismantle(String jsonSchema) {


    }
}
