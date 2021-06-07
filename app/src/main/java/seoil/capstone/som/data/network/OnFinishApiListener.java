package seoil.capstone.som.data.network;

// 서버 api의 수행 결과에 따른 처리를 위한 인터페이스
public interface OnFinishApiListener<T> {

    void onSuccess(T t);    // 성공 시 수행
    void onFailure(Throwable t);    // 에러 발생 시 예외 처리 수행
}