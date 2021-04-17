package seoil.capstone.som.data.repository;

public interface OnFinishRepositoryListener<T> {
    void onSuccess(T t);
    void onFailure(Throwable t);
}
