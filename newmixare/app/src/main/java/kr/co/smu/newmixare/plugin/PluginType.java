package kr.co.smu.newmixare.plugin;

import kr.co.smu.newmixare.plugin.connection.BootStrapActivityConnection;
import kr.co.smu.newmixare.plugin.connection.DataHandlerServiceConnection;
import kr.co.smu.newmixare.plugin.connection.MarkerServiceConnection;

public enum PluginType{
    /** A bootstrap plugin that will be loaded first I.E. a splashscreen */
    BOOTSTRAP_PHASE_1(){

        public String getActionName() {
            return "org.mixare.plugin.bootstrap1";
        }

        public PluginConnection getPluginConnection() {
            PluginConnection pluginConnection = new BootStrapActivityConnection();
            pluginConnection.setPluginType(this);
            return pluginConnection;
        }

        public Loader getLoader() {
            return Loader.Activity;
        }
    },
    /** A bootstrap plugin that will be loaded after the fist boostrap phase */
    BOOTSTRAP_PHASE_2(){

        public String getActionName() {
            return "org.mixare.plugin.bootstrap2";
        }

        public PluginConnection getPluginConnection() {
            PluginConnection pluginConnection = new BootStrapActivityConnection();
            pluginConnection.setPluginType(this);
            return pluginConnection;
        }

        public Loader getLoader() {
            return Loader.Activity;
        }
    },
    /** A plugin that returns a custom datasource */
    DATASELECTOR(){

        public String getActionName() {
            return "org.mixare.plugin.dataselector";
        }

        public PluginConnection getPluginConnection() {
            PluginConnection pluginConnection = new BootStrapActivityConnection();
            pluginConnection.setPluginType(this);
            return pluginConnection;
        }

        public Loader getLoader() {
            return Loader.Activity;
        }
    },
    /** A plugin that contains a custom marker */
    MARKER(){

        public String getActionName() {
            return "org.mixare.plugin.marker";
        }

        public PluginConnection getPluginConnection() {
            PluginConnection pluginConnection = new MarkerServiceConnection();
            pluginConnection.setPluginType(this);
            return pluginConnection;
        }

        public Loader getLoader() {
            return Loader.Service;
        }
    },
    /** A plugin that handles the conversion of data to marker*/
    DATAHANDLER(){

        public String getActionName() {
            return "org.mixare.plugin.datahandler";
        }

        public PluginConnection getPluginConnection() {
            PluginConnection pluginConnection = new DataHandlerServiceConnection();
            pluginConnection.setPluginType(this);
            return pluginConnection;
        }

        public Loader getLoader() {
            return Loader.Service;
        }
    };

    /** The package name to find the plugin */
    public abstract String getActionName();
    /** The loader to know how to handle a plugin (activity / service) */
    public abstract Loader getLoader();
    /** Returns the instance of an activity plugin loader that can load activity plugins */
    public abstract PluginConnection getPluginConnection();
}

/**
 * A loader enum, a activity loader means that the plugin is a activity, and it should be loaded
 * like an activity, A service loader is a plugin that can run in the background and is not visible.
 * @author A. Egal
 */
enum Loader {
    Activity,
    Service
}
