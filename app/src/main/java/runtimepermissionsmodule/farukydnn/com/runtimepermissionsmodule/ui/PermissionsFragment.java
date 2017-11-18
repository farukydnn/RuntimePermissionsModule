package runtimepermissionsmodule.farukydnn.com.runtimepermissionsmodule.ui;

import java.util.Map;

import runtimepermissionsmodule.farukydnn.com.runtimepermissionsmodule.interfaces.PermissionsListener;

/**
 * Created by Faruk Aydın on 18.11.2017.
 **/

public class PermissionsFragment extends BaseFragment {

    public void requestAppPermissions(PermissionsActivity permissionActivity,
                                      String[] requestedPermissions, int requestCode,
                                      Map<String, PermissionsActivity.PermissionDialogMessage> messages,
                                      PermissionsListener responseCallback) {

        permissionActivity.requestAppPermissions(requestedPermissions, requestCode, messages,
                responseCallback);
    }
}
