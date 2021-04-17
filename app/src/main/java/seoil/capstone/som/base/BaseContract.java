package seoil.capstone.som.base;

import android.content.Context;
import android.content.res.Resources;

import seoil.capstone.som.data.repository.OnFinishRepositoryListener;

public class BaseContract {

    // 공용 모델 중계 인터페이스
    public interface Interactor {


    }

    // 공용 뷰 인터페이스
    public interface View {

        void showProgress(); // 로딩 시 뷰 출력
        void hideProgress();
    }

    // 공용 프레젠터 인터페이스
    public interface Presenter<T> {

        void setView(T view);   // 사용할 뷰 초기화
        void releaseView();     // 사용한 뷰 해제
        void createInteractor();
        void releaseInteractor();
    }

}
