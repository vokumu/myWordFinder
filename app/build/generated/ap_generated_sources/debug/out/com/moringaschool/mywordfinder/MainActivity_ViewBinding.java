// Generated code from Butter Knife. Do not modify!
package com.moringaschool.mywordfinder;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;

import com.moringaschool.mywordfinder.ui.MainActivity;

import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(MainActivity target, View source) {
    this.target = target;

    target.mFindWord = Utils.findRequiredViewAsType(source, R.id.findWord, "field 'mFindWord'", Button.class);
    target.mWordEditText = Utils.findRequiredViewAsType(source, R.id.wordEditText, "field 'mWordEditText'", EditText.class);
    target.mAppNameTextView = Utils.findRequiredViewAsType(source, R.id.appNameTextView, "field 'mAppNameTextView'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mFindWord = null;
    target.mWordEditText = null;
    target.mAppNameTextView = null;
  }
}
