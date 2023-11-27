// Generated by view binder compiler. Do not edit!
package com.example.limeapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.example.limeapp.R;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityFirstScreenBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final SpringDotsIndicator Adapter;

  @NonNull
  public final TextView UpText1;

  @NonNull
  public final ConstraintLayout View;

  @NonNull
  public final ImageView imageView5;

  @NonNull
  public final ImageView imageView8;

  @NonNull
  public final LinearLayout linearLayout;

  @NonNull
  public final ImageView navBuy;

  @NonNull
  public final ImageView navFirstScreen;

  @NonNull
  public final ImageView navProfile;

  @NonNull
  public final ViewPager2 pager1;

  @NonNull
  public final TextView textView3;

  @NonNull
  public final TextView textView5;

  @NonNull
  public final TextView textView6;

  @NonNull
  public final TextView textView7;

  @NonNull
  public final TextView textView8;

  private ActivityFirstScreenBinding(@NonNull ConstraintLayout rootView,
      @NonNull SpringDotsIndicator Adapter, @NonNull TextView UpText1,
      @NonNull ConstraintLayout View, @NonNull ImageView imageView5, @NonNull ImageView imageView8,
      @NonNull LinearLayout linearLayout, @NonNull ImageView navBuy,
      @NonNull ImageView navFirstScreen, @NonNull ImageView navProfile, @NonNull ViewPager2 pager1,
      @NonNull TextView textView3, @NonNull TextView textView5, @NonNull TextView textView6,
      @NonNull TextView textView7, @NonNull TextView textView8) {
    this.rootView = rootView;
    this.Adapter = Adapter;
    this.UpText1 = UpText1;
    this.View = View;
    this.imageView5 = imageView5;
    this.imageView8 = imageView8;
    this.linearLayout = linearLayout;
    this.navBuy = navBuy;
    this.navFirstScreen = navFirstScreen;
    this.navProfile = navProfile;
    this.pager1 = pager1;
    this.textView3 = textView3;
    this.textView5 = textView5;
    this.textView6 = textView6;
    this.textView7 = textView7;
    this.textView8 = textView8;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityFirstScreenBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityFirstScreenBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_first_screen, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityFirstScreenBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.Adapter;
      SpringDotsIndicator Adapter = ViewBindings.findChildViewById(rootView, id);
      if (Adapter == null) {
        break missingId;
      }

      id = R.id.UpText1;
      TextView UpText1 = ViewBindings.findChildViewById(rootView, id);
      if (UpText1 == null) {
        break missingId;
      }

      id = R.id.View;
      ConstraintLayout View = ViewBindings.findChildViewById(rootView, id);
      if (View == null) {
        break missingId;
      }

      id = R.id.imageView5;
      ImageView imageView5 = ViewBindings.findChildViewById(rootView, id);
      if (imageView5 == null) {
        break missingId;
      }

      id = R.id.imageView8;
      ImageView imageView8 = ViewBindings.findChildViewById(rootView, id);
      if (imageView8 == null) {
        break missingId;
      }

      id = R.id.linearLayout;
      LinearLayout linearLayout = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout == null) {
        break missingId;
      }

      id = R.id.nav_buy;
      ImageView navBuy = ViewBindings.findChildViewById(rootView, id);
      if (navBuy == null) {
        break missingId;
      }

      id = R.id.nav_firstScreen;
      ImageView navFirstScreen = ViewBindings.findChildViewById(rootView, id);
      if (navFirstScreen == null) {
        break missingId;
      }

      id = R.id.nav_profile;
      ImageView navProfile = ViewBindings.findChildViewById(rootView, id);
      if (navProfile == null) {
        break missingId;
      }

      id = R.id.pager1;
      ViewPager2 pager1 = ViewBindings.findChildViewById(rootView, id);
      if (pager1 == null) {
        break missingId;
      }

      id = R.id.textView3;
      TextView textView3 = ViewBindings.findChildViewById(rootView, id);
      if (textView3 == null) {
        break missingId;
      }

      id = R.id.textView5;
      TextView textView5 = ViewBindings.findChildViewById(rootView, id);
      if (textView5 == null) {
        break missingId;
      }

      id = R.id.textView6;
      TextView textView6 = ViewBindings.findChildViewById(rootView, id);
      if (textView6 == null) {
        break missingId;
      }

      id = R.id.textView7;
      TextView textView7 = ViewBindings.findChildViewById(rootView, id);
      if (textView7 == null) {
        break missingId;
      }

      id = R.id.textView8;
      TextView textView8 = ViewBindings.findChildViewById(rootView, id);
      if (textView8 == null) {
        break missingId;
      }

      return new ActivityFirstScreenBinding((ConstraintLayout) rootView, Adapter, UpText1, View,
          imageView5, imageView8, linearLayout, navBuy, navFirstScreen, navProfile, pager1,
          textView3, textView5, textView6, textView7, textView8);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
