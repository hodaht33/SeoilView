package seoil.capstone.som.base;

import android.content.Context;
import android.content.res.Resources;

public class BaseContract {

    // 공용 뷰 인터페이스
    public interface View {
        void showProgressBar(); // 로딩 시 뷰 출력
    }

    // 공용 프레젠터 인터페이스
    public interface Presenter<T> {
        void setView(T view);   // 사용할 뷰 초기화
        void releaseView();     // 사용한 뷰 해제
        void setContext(Context context);   // 사용할 컨텍스트 초기화
        void releaseContext();              // 사용한 컨텍스트 해제
        void setResources(Resources res);   // 사용할 리소스 초기화
        void releaseResources();            // 사용한 리소스 해제
    }

}
