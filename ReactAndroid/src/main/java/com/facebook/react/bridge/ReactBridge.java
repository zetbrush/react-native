/**
 * Copyright (c) 2015-present, Facebook, Inc.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.facebook.react.bridge;

import android.util.Log;

import com.facebook.soloader.SoLoader;

import java.lang.reflect.Method;

public class ReactBridge {
  private static boolean sDidInit = false;
  public static void staticInit() {
    // No locking required here, worst case we'll call into SoLoader twice
    // which will do its own locking internally
    if (!sDidInit) {
      Method[] arr = SoLoader.class.getDeclaredMethods();
      for (int i = 0; i < arr.length; i++) {
        if(arr[i].toString().contains("loadLibrary")) {
          Log.d("SoLoaderCheck", arr[i].toString());
        }
      }
      SoLoader.loadLibrary("reactnativejni");
      sDidInit = true;
    }
  }
}
