package play.modules.facebook;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eric Jacob
 */
public class JsonUtil {

    /**
     * Converts a JSON string into Java object.
     *
     * @param   json - the JSON string
     * @param   clazz - the object class type
     * @return  a Java object
     */
    public static <T> T toJavaObject(String json, Class<T> clazz) {
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        return gson.fromJson(json, clazz);
    }

    /**
     * Converts a JSON object into Java object.
     *
     * @param   json - the JSON object
     * @param   clazz - the object class type
     * @return  a Java object
     */
    public static <T> T toJavaObject(JsonObject json, Class<T> clazz) {
        return toJavaObject(json.toString(), clazz);
    }

    /**
     * Converts a JSON array into a list of Java objects.
     *
     * @param   json - the JSON array
     * @param   clazz - the object class type
     * @return  a list of Java objects
     */
    public static <T> List<T> toJavaObject(JsonArray json, Class<T> clazz) {
        List<T> ojbs = new ArrayList<T>(json.size());
        for (int i = 0; i < json.size(); i++) {
            ojbs.add(JsonUtil.toJavaObject(json.get(i).getAsJsonObject(), clazz));
        }
        return ojbs;
    }
}
