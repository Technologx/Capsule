package com.pitchedapps.capsule.library.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by Allan Wang on 2016-10-22.
 */
public class AnimUtils {

    public interface ViewCallback {
        void onFinish(View v);
    }

    public static void rootCircularReview(View rootView) {
        rootCircularReview(rootView, 0, 0);
    }

    public static void rootCircularReview(View rootView, final int x, final int y) {
        rootView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop,
                                       int oldRight, int oldBottom) {
                v.removeOnLayoutChangeListener(this);
                int x2 = x;
                int y2 = y;
                if (x2 > right) x2 = 0;
                if (y2 > bottom) y2 = 0;
                int radius = (int) Math.hypot(Math.max(x2, right - x2), Math.max(y2, bottom - y2));
                Animator reveal = ViewAnimationUtils.createCircularReveal(v, x2, y2, 0, radius);
                reveal.setInterpolator(new DecelerateInterpolator(1f));
                reveal.setDuration(500);
                reveal.start();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void circleReveal(Context c, View v, int x, int y, double radius) {
        circleReveal(c, v, x, y, radius, radius * 0.6);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void circleReveal(Context c, View v, int x, int y, double radius, double duration) {
        if (!v.isAttachedToWindow()) {
            v.setVisibility(View.VISIBLE);
            return;
        }
        // create the animator for this view (the start radius is zero)
        Animator anim =
                ViewAnimationUtils.createCircularReveal(v, x, y, 0, (int) radius).setDuration((long) duration);

        // make the view visible and start the animation
        v.bringToFront();
        v.setVisibility(View.VISIBLE);
        anim.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void circleReveal(Context c, View v, int x, int y, double radius, AnimatorListenerAdapter animListener) {
        circleReveal(c, v, x, y, radius, radius * 0.6, animListener);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void circleReveal(Context c, View v, int x, int y, double radius, double duration, final AnimatorListenerAdapter animListener) {
        if (!v.isAttachedToWindow()) {
            v.setVisibility(View.VISIBLE);
            return;
        }
        // create the animator for this view (the start radius is zero)
        Animator anim =
                ViewAnimationUtils.createCircularReveal(v, x, y, 0, (int) radius).setDuration((long) duration);

        // make the view visible and start the animation
        v.bringToFront();
        v.setVisibility(View.VISIBLE);
        anim.addListener(animListener);
        anim.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void circleHide(Context c, final View v, int x, int y, double radius, double duration) {
        if (!v.isAttachedToWindow()) {
            v.setVisibility(View.VISIBLE);
            return;
        }
        Animator anim =
                ViewAnimationUtils.createCircularReveal(v, x, y, (int) radius, 0).setDuration((long) duration);

        // make the view invisible when the animation is done
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                v.setVisibility(View.GONE);
            }
        });
        anim.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void circleHide(Context c, final View v, int x, int y, double radius, double duration, final AnimatorListenerAdapter animListener) {
        if (!v.isAttachedToWindow()) {
            v.setVisibility(View.VISIBLE);
            return;
        }
        Animator anim =
                ViewAnimationUtils.createCircularReveal(v, x, y, (int) radius, 0).setDuration((long) duration);

        // make the view invisible when the animation is done
        anim.addListener(animListener);
        anim.start();
    }

    public static Animation fadeInAnimation(Context c, double offset, double duration) {
        Animation fadeInAnimation = AnimationUtils.loadAnimation(c, android.R.anim.fade_in);
        fadeInAnimation.setStartOffset((int) offset);
        fadeInAnimation.setDuration((int) duration);
        return fadeInAnimation;
    }

    public static Animation fadeInAnimation(Context c, double offset, double duration, Animation.AnimationListener mListener) {
        Animation fadeInAnimation = AnimationUtils.loadAnimation(c, android.R.anim.fade_in);
        fadeInAnimation.setStartOffset((int) offset);
        fadeInAnimation.setDuration((int) duration);
        fadeInAnimation.setAnimationListener(mListener);
        return fadeInAnimation;
    }

    public static Animation fadeOutAnimation(Context c, double offset, double duration) {
        Animation fadeOutAnimation = AnimationUtils.loadAnimation(c, android.R.anim.fade_out);
        fadeOutAnimation.setStartOffset((int) offset);
        fadeOutAnimation.setDuration((int) duration);
        return fadeOutAnimation;
    }

    public static Animation fadeOutAnimation(Context c, double offset, double duration, Animation.AnimationListener mListener) {
        Animation fadeOutAnimation = AnimationUtils.loadAnimation(c, android.R.anim.fade_out);
        fadeOutAnimation.setStartOffset((int) offset);
        fadeOutAnimation.setDuration((int) duration);
        fadeOutAnimation.setAnimationListener(mListener);
        return fadeOutAnimation;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void fadeOut(Context c, View v, double offset, double duration) {
        v.setVisibility(View.GONE);
        if (v.isAttachedToWindow()) {
            v.startAnimation(fadeOutAnimation(c, offset, duration));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void fadeOut(Context c, View v, double offset, double duration, Animation.AnimationListener mListener) {
        v.setVisibility(View.GONE);
        if (v.isAttachedToWindow()) {
            v.startAnimation(fadeOutAnimation(c, offset, duration, mListener));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void fadeIn(Context c, View v, double offset, double duration) {
        v.setVisibility(View.VISIBLE);
        if (v.isAttachedToWindow()) {
            v.bringToFront();
            v.startAnimation(fadeInAnimation(c, offset, duration));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void fadeIn(Context c, View v, double offset, double duration, Animation.AnimationListener mListener) {
        v.setVisibility(View.VISIBLE);
        if (v.isAttachedToWindow()) {
            v.bringToFront();
            v.startAnimation(fadeInAnimation(c, offset, duration, mListener));
        }
    }

    public static void sequentialFadeIn(Context c, View[] viewList, int duration) {
        if (viewList == null) return;
        for (int i = 0; i < viewList.length; i++) {
            Animation fadeIn = AnimationUtils.loadAnimation(c, android.R.anim.fade_in);
            fadeIn.setDuration(duration);
            fadeIn.setInterpolator(c, android.R.interpolator.accelerate_quint);
            fadeIn.setStartOffset(i * duration / 5);
            viewList[i].startAnimation(fadeIn);
            viewList[i].setVisibility(View.VISIBLE);
        }
    }

    public static void slideEnter(final View v) {
        v.setVisibility(View.VISIBLE);
        v.setAlpha(0.0f);
        v.animate()
//                .translationY(v.getHeight())
                .alpha(1.0f)
                .setDuration(3000);
    }

    public static void slideExit(final View v, @Nullable final ViewCallback callback) {
        v.animate()
//                .translationY(0)
                .alpha(0.0f)
                .setDuration(3000)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        v.setVisibility(View.GONE);
                        if (callback != null) callback.onFinish(v);
                    }
                });
    }
}
