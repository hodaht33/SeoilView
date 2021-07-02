package seoil.capstone.som.base;

// MVP 공용 메서드
public class BaseContract {

    // 공용 모델 인터페이스
    public interface Interactor {

    }

    // 공용 뷰 인터페이스
    public interface View {

        void showProgress();    // 로딩 창 보이기
        void hideProgress();    // 로딩 창 숨기기
        void showDialog(String msg);    // 다이얼로그 창 보이기
    }

    // 공용 프레젠터 인터페이스
    public interface Presenter<T> {

        void setView(T view);   // 뷰(액티비티 또는 프레그먼트) 설정
        void releaseView(); // 뷰 해제
        void createInteractor();    // 인터렉터 생성
        void releaseInteractor();   // 인터렉터 해제
    }

}
