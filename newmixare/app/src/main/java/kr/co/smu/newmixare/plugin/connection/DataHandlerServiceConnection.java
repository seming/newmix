package kr.co.smu.newmixare.plugin.connection;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

import kr.co.smu.newmixare.plugin.PluginConnection;
import kr.co.smu.newmixare.data.convert.DataConvertor;
import kr.co.smu.newmixare.lib.service.IDataHandlerService;
import kr.co.smu.newmixare.plugin.remoteobjects.RemoteDataHandler;


public class DataHandlerServiceConnection extends PluginConnection implements
        ServiceConnection {

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        // get instance of the aidl binder
        IDataHandlerService iDataHandlerService = IDataHandlerService.Stub
                .asInterface(service);
        RemoteDataHandler rm = new RemoteDataHandler(iDataHandlerService);
        rm.buildDataHandler();
        DataConvertor.getInstance().addDataProcessor(rm);
        storeFoundPlugin();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        DataConvertor.getInstance().clearDataProcessors();
    }

}