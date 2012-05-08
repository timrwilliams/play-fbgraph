package play.modules.facebook;

import play.PlayPlugin;

/**
 * FbGraph Play Plugin.
 *
 * @author Eric Jacob
 */
public class FbGraphPlugin extends PlayPlugin {

    @Override
    public void onApplicationStart() {
        FbGraph.init();
    }
}
