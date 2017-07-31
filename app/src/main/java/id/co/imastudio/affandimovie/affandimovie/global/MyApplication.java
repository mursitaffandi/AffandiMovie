package id.co.imastudio.affandimovie.affandimovie.global;

import android.app.Application;
import android.content.Context;
import com.facebook.stetho.Stetho;

class MyApplication extends Application {
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        Stetho.initializeWithDefaults(this);
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(
                                Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(
                                Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }

    static Context getContext(){
        return mContext;
    }
}
