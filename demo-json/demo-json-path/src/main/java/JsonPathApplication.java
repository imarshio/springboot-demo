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
        String json_schema = "";
        String json_content = "";
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
