package kr.co.smu.newmixare.plugin;

public abstract class PluginConnection {

    private PluginType pluginType;

    public void setPluginType(PluginType pluginType) {
        this.pluginType = pluginType;
    }

    public PluginType getPluginType() {
        return pluginType;
    }

    public String getPluginName() {
        return pluginType.getActionName();
    }

    protected void storeFoundPlugin(){
        PluginLoader.getInstance().addFoundPluginToMap(pluginType.toString(), this);
    }

    protected void storeFoundPlugin(String pluginName){
        PluginLoader.getInstance().addFoundPluginToMap(pluginName, this);
    }
}
