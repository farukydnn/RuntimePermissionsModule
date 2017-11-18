package runtimepermissionsmodule.farukydnn.com.runtimepermissionsmodule.interfaces;

import android.support.annotation.Nullable;

import java.util.List;

/**
 * Created by Faruk Aydın on 17.10.2017.
 **/

public interface PermissionsListener {

    /**
     * Called when all of the requested permissions are granted.
     *
     * @param requestCode The given int number of the request.
     */
    void onPermissionsGranted(int requestCode);

    /**
     * Called when if any permission is denied.
     *
     * @param requestCode               The given int number of the request.
     * @param errorType                 The constant error code. Can be
     *                                  {@link runtimepermissionsmodule.farukydnn.com.runtimepermissionsmodule.ui.PermissionsActivity#PERMISSION_DENIED PERMISSION_DENIED}
     *                                  or {@link runtimepermissionsmodule.farukydnn.com.runtimepermissionsmodule.ui.PermissionsActivity#PERMISSION_CANCELLED PERMISSION_CANCELLED}.
     * @param permissionsThisTimeDenied The list of permissions that have been denied but can be asked again.
     * @param permissionsAlwaysDenied The list of permissions that have been denied and can't be asked again. User must go to settings and grant them manually.
     */
    void onPermissionsDenied(int requestCode, int errorType,
                             @Nullable List<String> permissionsThisTimeDenied,
                             @Nullable List<String> permissionsAlwaysDenied);
}
