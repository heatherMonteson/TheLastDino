package src;

import java.util.ArrayList;
import java.util.List;

public class Broker {
    
    private static Broker broker = new Broker();
    private List<Observer> observers=new ArrayList<>();

    private Broker(){}

    public static Broker getBroker() {
        return broker; 
    }

    public void event(Enums.Event type){
        notify(type);
    }

    private void notify(Enums.Event type) {
        if (observers != null) {
            for (Observer observer : observers) {
                observer.update(type);
            }
        }
    }

    public void register(Observer o) {
        observers.add(o);
    }

}
