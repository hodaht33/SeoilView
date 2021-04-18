package seoil.capstone.som.data.network;

public interface OnFinishApiListener<T> {
    void onSuccess(T t);
    void onFailure(Throwable t);
}
