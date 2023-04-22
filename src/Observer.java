package src;

/*
 * Observer: implement to receive events from Broker
 * check from enums class: public enum Event {LostLife, AteLeaves, LevelCompleted}
 *
 * OO pattern: Observer
 */
public interface Observer {
    public void update(Enums.Event event);
}
