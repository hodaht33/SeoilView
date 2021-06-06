package seoil.capstone.som.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.airbnb.lottie.LottieAnimationView;

import org.xmlpull.v1.XmlPullParser;

import okhttp3.internal.Util;
import seoil.capstone.som.R;
import seoil.capstone.som.ui.register.select.ProgressProcess;

public class Utility {

    private static Utility mUtility;
    private ProgressProcess mProgressProcess;

    public static Utility getInstance() {

        if (mUtility == null) {

            mUtility = new Utility();
        }

        return mUtility;
    }

    public Utility() {

        mProgressProcess = new ProgressProcess();
    }

    public void activateKeyboard(Activity activity) {

        InputMethodManager manager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    public void deactivateKeyboard(Activity activity) {

        InputMethodManager manager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        manager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    public void activateProgressAnim(Context context, ConstraintLayout layout) {

        float density = context.getResources().getDisplayMetrics().density;

        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(changePxToDp(density, 120), changePxToDp(density, 280), changePxToDp(density, 120), changePxToDp(density, 280));

        LottieAnimationView lottieAnimationView = new LottieAnimationView(context);
        lottieAnimationView.setLayoutParams(layoutParams);
        lottieAnimationView.setAnimation(R.raw.progress_animaiton);

        layout.addView(lottieAnimationView);

        mProgressProcess.setParameter(lottieAnimationView, (Activity) context).execute();
    }

    public void deactivateProgressAnim() {

        mProgressProcess.endProgress();
    }

    public int changePxToDp(float density, int val) {

        return Math.round(val * density);
    }
}
