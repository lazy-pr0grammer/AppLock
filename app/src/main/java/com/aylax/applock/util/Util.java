package com.aylax.applock.util;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.PowerManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsetsController;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.aylax.applock.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;

public class Util {
  public static void toolbarFont(@NonNull Toolbar toolbar) {
    for (int pos = 0; pos < toolbar.getChildCount(); pos++) {
      if (toolbar.getChildAt(pos) instanceof TextView) {
        ((TextView) toolbar.getChildAt(pos))
            .setTypeface(ResourcesCompat.getFont(toolbar.getContext(), R.font.manrope_bold));
      }
    }
  }

  public static void navigationFont(View v) {
    try {
      if (v instanceof ViewGroup) {
        ViewGroup vg = (ViewGroup) v;
        for (int i = 0; i < vg.getChildCount(); i++) {
          View child = vg.getChildAt(i);
          navigationFont(child);
        }
      } else if (v instanceof TextView) {
        ((TextView) v).setTypeface(ResourcesCompat.getFont(v.getContext(), R.font.manrope_bold));
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static boolean isScreenOff(@NonNull Context context) {
    PowerManager manager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
    return !manager.isInteractive();
  }
  
  public static void removeFromRecent(@NonNull Context context) {
    ActivityManager activityManager =
        (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
    if (activityManager != null) {
      List<ActivityManager.AppTask> tasks = activityManager.getAppTasks();
      if (tasks != null && tasks.size() > 0) {
        tasks.get(0).setExcludeFromRecents(true);
      }
    }
  }

  public static void transparentStatusBar(Window window) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
      window.setStatusBarColor(Color.TRANSPARENT);
    } else {
      window
          .getDecorView()
          .setSystemUiVisibility(
              View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }
  }

  public static void transparentNavigationBar(Window window) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
      window
          .getInsetsController()
          .setSystemBarsBehavior(WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
    } else {
      window.setNavigationBarColor(Color.TRANSPARENT);
    }
  }

  public static void loadFragment(Fragment fragment, @NonNull AppCompatActivity activity) {
    FragmentManager manager = activity.getSupportFragmentManager();
    manager.beginTransaction().replace(R.id.frame, fragment).commit();
  }

  public static void showToast(Context context, String message) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
  }
}
