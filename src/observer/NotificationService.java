package observer;

import java.util.ArrayList;
import java.util.List;

public class NotificationService {
    private final List<Observer> observers = new ArrayList<>();

    // ✅ This is the method you need
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(Object eventData) {
        for (Observer observer : observers) {
            observer.update(eventData);
        }
    }

    public void clearObservers() {
        observers.clear();
    }
}
