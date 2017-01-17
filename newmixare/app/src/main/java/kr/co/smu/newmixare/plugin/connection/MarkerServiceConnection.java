package kr.co.smu.newmixare.plugin.connection;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import kr.co.smu.newmixare.lib.service.IMarkerService;
import kr.co.smu.newmixare.plugin.PluginConnection;
import kr.co.smu.newmixare.plugin.PluginNotFoundException;

import java.util.HashMap;
import java.util.Map;

import kr.co.smu.newmixare.plugin.PluginConnection;

public class MarkerServiceConnection extends PluginConnection implements ServiceConnection {

    private Map<String, IMarkerService> markerServices = new HashMap<String, IMarkerService>();

    @Override
    public void onServiceDisconnected(ComponentName name) {
        markerServices.clear();
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        // get instance of the aidl binder
        IMarkerService iMarkerService = IMarkerService.Stub
                .asInterface(service);
        try {
            String markername = iMarkerService.getPluginName();
            markerServices.put(markername, iMarkerService);
            storeFoundPlugin();
        } catch (RemoteException e) {
            throw new PluginNotFoundException(e);
        }
    }

    public Map<String, IMarkerService> getMarkerServices() {
        return markerServices;
    }
}
