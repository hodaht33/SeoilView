package seoil.capstone.som.util;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.airbnb.lottie.LottieAnimationView;

// 로딩 창 스레드
public class ProgressProcess extends AsyncTask<Void, Void, Void> {

    private LottieAnimationView mAinimationView;
    private Activity mActivity;
    private boolean mIsRunning = false;

    public ProgressProcess setParameter(LottieAnimationView animationView, Activity activity) {

        mAinimationView = animationView;
        mActivity = activity;

        return this;
    }

    // 스레드 실행 전
    @Override
    protected void onPreExecute() {

        // 키보드 내리기
        Utility.getInstance().deactivateKeyboard(mActivity);

        // 터치 불가 설정
        mActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        mAinimationView.setVisibility(View.VISIBLE);
        mAinimationView.playAnimation();
        mIsRunning = true;

        super.onPreExecute();
    }

    // 스레드 종료 전
    @Override
    protected void onPostExecute(Void aVoid) {

        // 터치 가능 설정
        mActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        mAinimationView.setVisibility(View.GONE);
        mAinimationView.cancelAnimation();

        super.onPostExecute(aVoid);
    }

    // 스레드 실행 중
    @Override
    protected Void doInBackground(Void... voids) {

        try {

            while(mIsRunning) {

                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {

            Log.d("sleep", e.getMessage());
        }

        return null;
    }

    // 스레드 종료 상태 갱신
    public void endProgress() {
        mIsRunning = false;
    }

    // 스레드 상태 반환
    public boolean getRunning() {
        
        return mIsRunning;
    }
}
