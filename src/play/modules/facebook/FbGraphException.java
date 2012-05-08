package play.modules.facebook;

import com.google.gson.JsonObject;

/**
 * FbGraph Exception.
 *
 * @author Eric Jacob
 */
public class FbGraphException extends Exception {

    private String type = null;
    private String code = null;

    public FbGraphException(String error) {
        super(error);
    }

    public FbGraphException(JsonObject json) {
        super(createMessage(extractType(json), null, extractMessage(json)));
        this.type = extractType(json);
    }

    public FbGraphException(String type, String code, String message) {
        super(createMessage(type, code, message));
        this.type = type;
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public String getCode() {
        return code;
    }

    private static String extractType(JsonObject json) {
        return json.get("error").getAsJsonObject().get("type").getAsString();
    }

    private static String extractMessage(JsonObject json) {
        return json.get("error").getAsJsonObject().get("message").getAsString();
    }

    private static String createMessage(String type, String code, String message) {
        return type + ": " + (code != null ? code + ": " : "") + message;
    }
}
