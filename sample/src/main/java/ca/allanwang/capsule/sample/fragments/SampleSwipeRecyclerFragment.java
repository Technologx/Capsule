package ca.allanwang.capsule.sample.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ca.allanwang.capsule.library.adapters.CapsuleAdapter;
import ca.allanwang.capsule.library.event.CFabEvent;
import ca.allanwang.capsule.library.fragments.SwipeRecyclerFragmentAnimated;
import ca.allanwang.capsule.sample.R;
import ca.allanwang.capsule.sample.helpers.SampleAdapter;

/**
 * Created by Allan Wang on 2016-12-21.
 */

public class SampleSwipeRecyclerFragment extends SwipeRecyclerFragmentAnimated<String, SampleAdapter.SampleViewHolder> {

    @Override
    protected CapsuleAdapter<String, SampleAdapter.SampleViewHolder> getAdapter(Context context) {
        return new SampleAdapter(null);
    }

    @Override
    @CallSuper
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView = (TextView) inflate(R.layout.textview);
        textView.setText("This is a header");
        cLinear.addView(textView, 0);
    }

    @Nullable
    @Override
    protected CFabEvent updateFab() {
        return null;
    }

    @Override
    public int getTitleId() {
        return 0;
    }

    @Override
    protected void updateList(List<String> oldList) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<String> words = new ArrayList<>();
                Random rnd = new Random();
                for (int i = 0; i < 80; i++) {
                    words.add(String.valueOf(rnd.nextInt(100000)));
                }
                cAdapter.updateList(words);
                hideRefresh();
            }
        }, 2000);
    }

}
