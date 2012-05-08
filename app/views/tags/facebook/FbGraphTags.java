package views.tags.facebook;

import groovy.lang.Closure;
import java.io.PrintWriter;
import java.util.Map;
import play.modules.facebook.FbGraph;
import play.templates.FastTags;
import play.templates.GroovyTemplate.ExecutableTemplate;

/**
 *
 * @author Eric Jacob
 */
@FastTags.Namespace("fbg")
public class FbGraphTags extends FastTags {

    public static void _script(Map<?, ?> args, Closure body, PrintWriter out, ExecutableTemplate template, int fromLine) {
        out.println("<div id='fb-root'></div>");
        out.println("<script src='//connect.facebook.net/" + getLocale(args.get("locale")) + "/all.js'></script>");
        out.println("<script>");
        out.println("    FB.init({");
        out.println("        appId  : '" + FbGraph.getAppId() + "',");
        out.println("        status : true,");
        out.println("        cookie : true,");
        out.println("        xfbml  : true");
        out.println("    });");
        out.println("</script>");
    }

    public static void _scriptAsync(Map<?, ?> args, Closure body, PrintWriter out, ExecutableTemplate template, int fromLine) {
        out.println("<div id='fb-root'></div>");
        out.println("<script>");
        out.println("    window.fbAsyncInit = function() {");
        out.println("        FB.init({");
        out.println("            appId  : '" + FbGraph.getAppId() + "',");
        out.println("            status : true,");
        out.println("            cookie : true,");
        out.println("            xfbml  : true");
        out.println("        });");
        out.println("    };");
        out.println("    (function(d) {");
        out.println("        var js, id = 'facebook-jssdk'; if (d.getElementById(id)) {return;}");
        out.println("        js = d.createElement('script'); js.id = id; js.async = true;");
        out.println("        js.src = '//connect.facebook.net/" + getLocale(args.get("locale")) + "/all.js';");
        out.println("        d.getElementsByTagName('head')[0].appendChild(js);");
        out.println("    }(document));");
        out.println("</script>");
    }

    private static String getLocale(Object obj) {
        String locale = String.valueOf(obj);
        if (!locale.equals("null") && !locale.isEmpty()) {
            return locale;
        } else {
            return "en_US";
        }
    }
}
