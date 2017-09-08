package com.sellf.exerciseconvertini.utils;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

/**
 * Created by pconvertini on 08/09/2017.
 */

public abstract class MyFragment extends Fragment {

    public void hideLoadingIndicator(View view, int id){
        view.findViewById(id);
        if (view.getVisibility() == View.VISIBLE) {
            view.setVisibility(View.GONE);
        }
    }

    public void errorText(View view, int id, int text) {
        if (view != null) {
            TextView errorTxtView = view
                    .findViewById(id);
            errorTxtView.setText(text);
        }
    }


}
