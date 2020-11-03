package com.soen343.shs.interfaces.observer;

public interface Subject {
    public void addObserver(Subscriber subscriber);

    public void removeObserver(Subscriber subscriber);

    public void notifySubscribers(long houseId);
}
