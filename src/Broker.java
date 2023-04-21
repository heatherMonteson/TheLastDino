package src;

import java.util.ArrayList;
import java.util.List;

//OO pattern: Observer
//registering player and database
//send updates to even for collisions (point or life lost) and changing levels
//events check from enums class: public enum Event {LostLife, AteLeaves, LevelCompleted}

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
