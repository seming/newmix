package kr.co.smu.newmixare.plugin.connection;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import kr.co.smu.lib.service.IBootStrap;
import kr.co.smu.newmixare.plugin.PluginConnection;
import kr.co.smu.newmixare.plugin.PluginLoader;
import kr.co.smu.newmixare.plugin.PluginNotFoundException;

public class BootStrapActivityConnection extends PluginConnection implements ActivityConnection{

    private IBootStrap iBootStrap;
    private Intent activityIntent;
    private int bootstrapRequestCode = -1;

    @Override
    public void startActivityForResult(Activity activity) {
        PluginLoader.getInstance().decreasePendingActivitiesOnResult();
        if(activityIntent == null){
            throw new PluginNotFoundException();
        }
        try{
            bootstrapRequestCode = iBootStrap.getActivityRequestCode();
            activity.startActivityForResult(activityIntent, bootstrapRequestCode);
        }catch(RemoteException e){
            throw new PluginNotFoundException(e);
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        iBootStrap = IBootStrap.Stub.asInterface(service);
        try {
            buildIntent();
            String pluginName = iBootStrap.getPluginName();
            storeFoundPlugin(pluginName);
            PluginLoader.getInstance().increasePendingActivitiesOnResult();
            PluginLoader.getInstance().startPlugin(getPluginType(), pluginName);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    private void buildIntent() throws RemoteException{
        activityIntent = new Intent();
        String packageName = iBootStrap.getActivityPackage();
        String className = iBootStrap.getActivityName();
        activityIntent.setClassName(packageName, className);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        iBootStrap = null;
        activityIntent = null;
    }

}
