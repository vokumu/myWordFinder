// Generated code from Butter Knife. Do not modify!
package com.moringaschool.mywordfinder;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;

import com.moringaschool.mywordfinder.adapters.MyAdapter;

import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyAdapter$MyViewHolder_ViewBinding implements Unbinder {
  private MyAdapter.MyViewHolder target;

  @UiThread
  public MyAdapter$MyViewHolder_ViewBinding(MyAdapter.MyViewHolder target, View source) {
    this.target = target;

    target.mWordTextView = Utils.findRequiredViewAsType(source, R.id.wordTextView, "field 'mWordTextView'", TextView.class);
    target.mScoreTextView = Utils.findRequiredViewAsType(source, R.id.scoreTextView, "field 'mScoreTextView'", TextView.class);
    target.mnumSyllablesTextView = Utils.findRequiredViewAsType(source, R.id.numSyllablesTextView, "field 'mnumSyllablesTextView'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MyAdapter.MyViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mWordTextView = null;
    target.mScoreTextView = null;
    target.mnumSyllablesTextView = null;
  }
}
