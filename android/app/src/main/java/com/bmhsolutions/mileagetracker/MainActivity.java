package com.bmhsolutions.mileagetracker;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.core.view.WindowCompat;
import com.getcapacitor.BridgeActivity;

public class MainActivity extends BridgeActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Don't draw behind the status bar — keeps header visible
        WindowCompat.setDecorFitsSystemWindows(getWindow(), true);

        // Dark status bar to match app background (#0a0a0a)
        Window w = getWindow();
        w.setStatusBarColor(0xFF0A0A0A);
    }
}
