package com.simplifiedlauncher.permissions;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseIntArray;

public abstract class RuntimePermissionsActivity extends AppCompatActivity {
    private SparseIntArray mErrorString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mErrorString = new SparseIntArray();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int permission : grantResults) {
            permissionCheck = permissionCheck + permission;
        }
        if ((grantResults.length > 0) && permissionCheck == PackageManager.PERMISSION_GRANTED) {
            onPermissionsGranted(requestCode);
        } else {
            requestAppPermissions(permissions, requestCode, requestCode);
        }
    }

    public void requestAppPermissions(final String[] requestedPermissions,
                                      final int stringId, final int requestCode) {

        mErrorString.put(requestCode, stringId);
        int permissionCheck = PackageManager.PERMISSION_GRANTED;

        for (String permission : requestedPermissions) {
            if (!checkPermission(permission)) {
                ActivityCompat.requestPermissions(this, requestedPermissions, requestCode);
            } else {
                onPermissionsGranted(requestCode);
            }

            permissionCheck = permissionCheck + ContextCompat.checkSelfPermission(this, permission);
        }
    }

    public abstract void onPermissionsGranted(int requestCode);

    /*============================================CHECK==============================================================*/

    /**
     * Checks if the permission is granted.
     *
     * @param permission The permission to check.
     * @return true if the permission is granted, false if denied.
     */
    public boolean checkPermission(String permission) {
        int granted = ContextCompat.checkSelfPermission(this, permission);
        return granted == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Checks if the permissions are granted.
     *
     * @param permissions The permissions to check.
     * @return true if the permission is granted, false if denied.
     */
    public boolean checkPermission(String[] permissions) {
        boolean allGranted = true;

        for (String permission : permissions) {
            allGranted &= this.checkPermission(permission);
        }

        return allGranted;
    }
}
