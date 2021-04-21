package seoil.capstone.som.util;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import okhttp3.internal.Util;

public class Utility {

    private static Utility mUtility;

    public static Utility getInstance() {

        if (mUtility == null) {

            mUtility = new Utility();
        }

        return mUtility;
    }

    public void renderKeyboard(Activity activity) {

        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

    }
}
