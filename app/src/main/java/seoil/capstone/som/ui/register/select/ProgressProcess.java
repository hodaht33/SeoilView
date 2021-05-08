package seoil.capstone.som.ui.register.select;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Debug;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import org.jetbrains.annotations.NotNull;

import java.util.logging.LogRecord;

import okhttp3.internal.http2.Http2Reader;
import seoil.capstone.som.R;

public class ProgressProcess extends AsyncTask<Void, Void, Void> {

    private LottieAnimationView mAinimationView;;
    private boolean mIsRunning = false;

    public ProgressProcess(LottieAnimationView animationView) {
        mAinimationView = animationView;
    }


    @Override
    protected void onPreExecute() {

        mAinimationView.setVisibility(View.VISIBLE);
        mAinimationView.playAnimation();
        mIsRunning = true;

        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {

        mAinimationView.setVisibility(View.GONE);
        mAinimationView.cancelAnimation();
        super.onPostExecute(aVoid);
    }

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

    public void endProgress() {
        mIsRunning = false;
    }

    public boolean getRunning() {
        
        return mIsRunning;
    }
}
