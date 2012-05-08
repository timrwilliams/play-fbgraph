package play.modules.facebook;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Eric Jacob
 */
public class Parameter {

    private Map<String, String> parameters = null;

    private Parameter(String name, String value) {
        this.parameters = new HashMap<String, String>();
        this.parameters.put(name, value);
    }

    public static Parameter with(String name, String value) {
        return new Parameter(name, value);
    }

    public Parameter and(String name, String value) {
        this.parameters.put(name, value);
        return this;
    }

    public Map<String, String> parameters() {
        return this.parameters;
    }
}
