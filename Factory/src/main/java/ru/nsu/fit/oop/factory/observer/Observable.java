package ru.nsu.fit.oop.factory.observer;

import java.util.Vector;
import java.util.concurrent.Flow;

public class Observable implements Flow.Publisher<Boolean> {
    private final Vector<Flow.Subscriber<? super Boolean>> observers = new Vector<>();

    public void addObserver(Flow.Subscriber<? super Boolean> observer) {
        observers.add(observer);
    }

    public void removeObserver(Flow.Subscriber<? super Boolean> observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Flow.Subscriber<? super Boolean> observer : observers) {
            observer.onNext(true);
        }
    }

    @Override
    public synchronized void subscribe(Flow.Subscriber<? super Boolean> subscriber) {
        addObserver(subscriber);
    }
}
