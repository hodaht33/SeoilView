package seoil.capstone.som.util;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.airbnb.lottie.LottieAnimationView;
import seoil.capstone.som.R;

// 여러 공용 기능 포함
// 싱글턴 클래스
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

    // 키보드 활성화
    public void activateKeyboard(Activity activity) {

        InputMethodManager manager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    // 키보드 비활성화
    public void deactivateKeyboard(Activity activity) {

        InputMethodManager manager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        manager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    // 로딩 창 활성화
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

    // 로딩 창 비활성화
    public void deactivateProgressAnim() {

        mProgressProcess.endProgress();
    }

    // xml이 아닌 자바 코드에서 ui크기를 지정할 땐 px단위로 지정되므로
    // 일반적으로 안드로이드에서 사용하는 단위인 dp로 변경해주는 메서드
    public int changePxToDp(float density, int val) {

        return Math.round(val * density);
    }
}
