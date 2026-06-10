package com.secretchat.app;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
public class IconSwitcher extends CordovaPlugin {
    private static final String[] ALIASES={".MainAlias_Calculator",".MainAlias_Flashlight",".MainAlias_Clock",".MainAlias_Notes"};
    @Override
    public boolean execute(String action,JSONArray args,CallbackContext cb){
        if("switchIcon".equals(action)){
            try{
                String alias=args.getString(0);
                PackageManager pm=cordova.getActivity().getPackageManager();
                String pkg=cordova.getActivity().getPackageName();
                pm.setComponentEnabledSetting(new ComponentName(pkg,pkg+".MainActivity"),PackageManager.COMPONENT_ENABLED_STATE_DISABLED,PackageManager.DONT_KILL_APP);
                for(String a:ALIASES){pm.setComponentEnabledSetting(new ComponentName(pkg,pkg+a),PackageManager.COMPONENT_ENABLED_STATE_DISABLED,PackageManager.DONT_KILL_APP);}
                if("default".equals(alias)){pm.setComponentEnabledSetting(new ComponentName(pkg,pkg+".MainActivity"),PackageManager.COMPONENT_ENABLED_STATE_ENABLED,PackageManager.DONT_KILL_APP);}
                else{pm.setComponentEnabledSetting(new ComponentName(pkg,pkg+alias),PackageManager.COMPONENT_ENABLED_STATE_ENABLED,PackageManager.DONT_KILL_APP);}
                cb.success();return true;
            }catch(Exception e){cb.error(e.getMessage());}
        }
        return false;
    }
}