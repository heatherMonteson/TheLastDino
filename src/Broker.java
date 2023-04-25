package src;

import java.util.ArrayList;
import java.util.List;


/*
 * Broker: register through the broker for event updates
 * send updates for collisions (point or life lost) and changing levels, end of game
 *
 * OO pattern: Observer
 *
 */

public class Broker {
    
    private static Broker broker = new Broker();
    private List<Observer> observers=new ArrayList<>();

    private Broker(){}

    public static Broker getBroker() {
        return broker; 
    }

    /*
     * event: collects notifications of events throughout the system: {LostLife, AteLeaves, LevelCompleted, PlayerDied}
     *
     * @param Enums.Event for event type used by observers to sort events/actions
     * @return nothing
     */
    public void event(Enums.Event type){
        notify(type);
    }

    /*
     * notify: notify registered observers of event
     *
     * @param Enums.Event for event type used by observers to sort events/actions
     * @return nothing
     */
    private void notify(Enums.Event type) {
        if (observers != null) {
            for (Observer observer : observers) {
                observer.update(type);
            }
        }
    }

    /*
     * register: register objects implementing the observer interface into the class list
     *
     * @param Observer
     * @return nothing
     */
    public void register(Observer o) {
        observers.add(o);
    }

}
