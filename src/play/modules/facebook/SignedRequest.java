package play.modules.facebook;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.regex.Pattern;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Eric Jacob
 */
public class SignedRequest {

    private String encodedSig;
    private String encodedData;
    private String sig;
    private JsonObject data;

    public SignedRequest(String value) {
        String[] splits = value.split(Pattern.quote("."));
        encodedSig = splits[0];
        sig = base64UrlDecode(encodedSig);
        encodedData = splits[1];
        data = (JsonObject) new JsonParser().parse(base64UrlDecode(encodedData));
    }

    public boolean verify(String secret) {
        boolean sigVerified = false;
        String algorithm = data.get("algorithm").getAsString();
        if ("HMAC-SHA256".equals(algorithm)) {
            try {
                SecretKeySpec key = new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256");
                Mac hmac = Mac.getInstance("HmacSHA256");
                hmac.init(key);
                String expectedSig = new String(hmac.doFinal(encodedData.getBytes("UTF-8")));
                if (expectedSig.equals(sig)) {
                    sigVerified = true;
                }
            } catch (Exception ex) {
            }
        }
        return sigVerified;
    }

    public String getSignature() {
        return sig;
    }

    public JsonObject getData() {
        return data;
    }

    private String base64UrlDecode(String encoded) {
        Base64 base64 = new Base64(true); // URL-safe mode
        return new String(base64.decode(encoded));
    }
}
