package runtimepermissionsmodule.farukydnn.com.runtimepermissionsmodule.dashboard;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import runtimepermissionsmodule.farukydnn.com.runtimepermissionsmodule.R;
import runtimepermissionsmodule.farukydnn.com.runtimepermissionsmodule.interfaces.PermissionsListener;
import runtimepermissionsmodule.farukydnn.com.runtimepermissionsmodule.ui.PermissionsActivity;

public class MainActivity extends PermissionsActivity implements PermissionsListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final int VIDEO_CALL_PERMISSIONS = 0;

    private Button askPermissionsButton;
    private String[] requestedPermissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        askPermissionsButton = findViewById(R.id.button_ask_permissions);
        createClickEvent();

        requestedPermissions = new String[]{Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CALL_PHONE, Manifest.permission.CAMERA};
    }

    private void createClickEvent() {
        askPermissionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Map<String, PermissionDialogMessage> messages = new HashMap<>();

                PermissionDialogMessage cameraMessage =
                        new PermissionDialogMessage("Camera", "Camera needed.");
                messages.put(Manifest.permission.CAMERA, cameraMessage);

                PermissionDialogMessage microphoneMessage =
                        new PermissionDialogMessage("Microphone", "Microphone needed.");
                messages.put(Manifest.permission.RECORD_AUDIO, microphoneMessage);

                requestAppPermissions(requestedPermissions, VIDEO_CALL_PERMISSIONS, messages, MainActivity.this);

            }
        });
    }

    @SuppressWarnings("MissingPermissions") // This is needed to suppress the false ide error.
    @Override
    public void onPermissionsGranted(int requestCode) {
        switch (requestCode) {
            case VIDEO_CALL_PERMISSIONS:
                Log.d(TAG, "All permissions granted. Start video call!");
                Toast.makeText(this, "All permissions granted. Start video call!", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, int errorType,
                                    @Nullable List<String> permissionsThisTimeDenied,
                                    @Nullable List<String> permissionsAlwaysDenied) {

        if (requestCode == VIDEO_CALL_PERMISSIONS) {

            if (errorType == PERMISSION_DENIED) {

                if (permissionsAlwaysDenied != null) {
                    Log.d(TAG, "These permissions have been denied permanently: "
                            + permissionsAlwaysDenied.toString());

                    Toast.makeText(this, "You must go settings and grant these permissions too: "
                            + permissionsAlwaysDenied.toString(), Toast.LENGTH_LONG).show();

                    return;
                }

                if (permissionsThisTimeDenied != null) {
                    Log.d(TAG, "These permissions have been denied but can be asked again: "
                            + permissionsThisTimeDenied.toString());

                    Toast.makeText(this, "You must grant these permissions too: "
                            + permissionsThisTimeDenied.toString(), Toast.LENGTH_LONG).show();
                }

            } else if (errorType == PERMISSION_CANCELLED) {

                Log.d(TAG, "Permissions Request Cancelled.");
                Toast.makeText(this, "Permissions Request Cancelled.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}