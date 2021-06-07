package seoil.capstone.som.base;

// MVP 공용 메서드
public class BaseContract {

    // 공용 모델 인터페이스
    public interface Interactor {


    }

    // 공용 뷰 인터페이스
    public interface View {

        void showProgress();
        void hideProgress();
    }

    // 공용 프레젠터 인터페이스
    public interface Presenter<T> {

        void setView(T view);
        void releaseView();
        void createInteractor();
        void releaseInteractor();
    }

}
