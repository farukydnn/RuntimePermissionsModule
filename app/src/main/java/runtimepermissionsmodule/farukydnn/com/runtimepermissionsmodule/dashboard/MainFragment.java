package runtimepermissionsmodule.farukydnn.com.runtimepermissionsmodule.dashboard;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import runtimepermissionsmodule.farukydnn.com.runtimepermissionsmodule.R;
import runtimepermissionsmodule.farukydnn.com.runtimepermissionsmodule.interfaces.PermissionsListener;
import runtimepermissionsmodule.farukydnn.com.runtimepermissionsmodule.ui.PermissionsActivity;
import runtimepermissionsmodule.farukydnn.com.runtimepermissionsmodule.ui.PermissionsFragment;

public class MainFragment extends PermissionsFragment implements PermissionsListener {

    private static final String TAG = MainFragment.class.getSimpleName();

    private static final int WRITE_PERMISSIONS = 0;

    private View view;
    private Button askButton;
    private String[] requestedPermissions;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);

        requestedPermissions = new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_CALENDAR};

        askButton = view.findViewById(R.id.button_permissions);

        askButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                requestAppPermissions((PermissionsActivity) getActivity(), requestedPermissions,
                        WRITE_PERMISSIONS, null, MainFragment.this);

            }
        });

        return view;
    }


    @SuppressWarnings("MissingPermissions") // This is needed to suppress the false ide error.
    @Override
    public void onPermissionsGranted(int requestCode) {

        if (requestCode == WRITE_PERMISSIONS) {
            Log.d(TAG, "Write permissions granted!");
            Toast.makeText(getActivity(), "Write permissions granted!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, int errorType,
                                    @Nullable List<String> permissionsThisTimeDenied,
                                    @Nullable List<String> permissionsAlwaysDenied) {

        Log.d(TAG, "Denied permissions request code is: " + requestCode);
        Toast.makeText(getActivity(), "Denied permissions request code is: " + requestCode, Toast.LENGTH_SHORT).show();
    }
}