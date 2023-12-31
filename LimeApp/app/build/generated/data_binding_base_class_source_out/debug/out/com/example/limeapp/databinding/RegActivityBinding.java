// Generated by view binder compiler. Do not edit!
package com.example.limeapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.limeapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class RegActivityBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final EditText EditTextAge;

  @NonNull
  public final EditText EditTextPass;

  @NonNull
  public final EditText EditTextPassRep;

  @NonNull
  public final EditText EditTextPhone;

  @NonNull
  public final EditText PIB;

  @NonNull
  public final ImageView buttonReg;

  @NonNull
  public final ImageView imageView;

  @NonNull
  public final ImageView imageView40;

  @NonNull
  public final TextView telephon;

  @NonNull
  public final ImageView textButLog;

  private RegActivityBinding(@NonNull ConstraintLayout rootView, @NonNull EditText EditTextAge,
      @NonNull EditText EditTextPass, @NonNull EditText EditTextPassRep,
      @NonNull EditText EditTextPhone, @NonNull EditText PIB, @NonNull ImageView buttonReg,
      @NonNull ImageView imageView, @NonNull ImageView imageView40, @NonNull TextView telephon,
      @NonNull ImageView textButLog) {
    this.rootView = rootView;
    this.EditTextAge = EditTextAge;
    this.EditTextPass = EditTextPass;
    this.EditTextPassRep = EditTextPassRep;
    this.EditTextPhone = EditTextPhone;
    this.PIB = PIB;
    this.buttonReg = buttonReg;
    this.imageView = imageView;
    this.imageView40 = imageView40;
    this.telephon = telephon;
    this.textButLog = textButLog;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static RegActivityBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static RegActivityBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.reg_activity, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static RegActivityBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.EditTextAge;
      EditText EditTextAge = ViewBindings.findChildViewById(rootView, id);
      if (EditTextAge == null) {
        break missingId;
      }

      id = R.id.EditTextPass;
      EditText EditTextPass = ViewBindings.findChildViewById(rootView, id);
      if (EditTextPass == null) {
        break missingId;
      }

      id = R.id.EditTextPassRep;
      EditText EditTextPassRep = ViewBindings.findChildViewById(rootView, id);
      if (EditTextPassRep == null) {
        break missingId;
      }

      id = R.id.EditTextPhone;
      EditText EditTextPhone = ViewBindings.findChildViewById(rootView, id);
      if (EditTextPhone == null) {
        break missingId;
      }

      id = R.id.PIB;
      EditText PIB = ViewBindings.findChildViewById(rootView, id);
      if (PIB == null) {
        break missingId;
      }

      id = R.id.button_reg;
      ImageView buttonReg = ViewBindings.findChildViewById(rootView, id);
      if (buttonReg == null) {
        break missingId;
      }

      id = R.id.imageView;
      ImageView imageView = ViewBindings.findChildViewById(rootView, id);
      if (imageView == null) {
        break missingId;
      }

      id = R.id.imageView40;
      ImageView imageView40 = ViewBindings.findChildViewById(rootView, id);
      if (imageView40 == null) {
        break missingId;
      }

      id = R.id.telephon;
      TextView telephon = ViewBindings.findChildViewById(rootView, id);
      if (telephon == null) {
        break missingId;
      }

      id = R.id.textButLog;
      ImageView textButLog = ViewBindings.findChildViewById(rootView, id);
      if (textButLog == null) {
        break missingId;
      }

      return new RegActivityBinding((ConstraintLayout) rootView, EditTextAge, EditTextPass,
          EditTextPassRep, EditTextPhone, PIB, buttonReg, imageView, imageView40, telephon,
          textButLog);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
