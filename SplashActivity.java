package shrinkcom.user.kaptap.Activities;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import shrinkcom.user.kaptap.R;
import shrinkcom.user.kaptap.other.PermissionChecker;


public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;
    private static final String[] RequiredPermissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION};

    PermissionChecker permissionChecker = new PermissionChecker();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        checkPermissions();

    }

    private void checkPermissions() {
        permissionChecker.verifyPermissions(this, RequiredPermissions, new PermissionChecker.VerifyPermissionsCallback() {
            @Override
            public void onPermissionAllGranted()
            {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                            Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(i);


                        }





                }, SPLASH_TIME_OUT);
            }
            @Override
            public void onPermissionDeny(String[] permissions) {
                Toast.makeText(SplashActivity.this, "Please grant required permissions.", Toast.LENGTH_LONG).show();
            }
        });


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionChecker.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


}
