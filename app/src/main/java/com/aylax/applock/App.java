package com.aylax.applock;

import android.app.Application;
import android.content.Context;

import com.google.android.material.color.DynamicColors;

public class App extends Application {
  private static Context context;

  @Override
  public void onCreate() {
    super.onCreate();
    context = this;
    DynamicColors.applyToActivitiesIfAvailable(this);
  }

  public static Context getContext() {
    return context;
  }
}
