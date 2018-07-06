package com.cooperativa.model;


public interface UseCaseCallback<T> {

    void onSuccess(T data);

    void onError(Exception e);

}
