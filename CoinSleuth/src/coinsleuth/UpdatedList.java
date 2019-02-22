package coinsleuth;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.collections.ObservableList;

/**
 *
 * @author Edward Conn
 * @param <T>
 * 
 */
public abstract class UpdatedList<T> extends Observable {
    protected List<T> list;
    public UpdatedList() {
        list = updateList();
        //Updates the List every 60 seconds.
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(() -> {
            list = (ObservableList<T>) updateList();
            setChanged();
            notifyObservers();
        }, -1L, 60L, TimeUnit.SECONDS);

    }

    public List<T> getObservableList() {
        return list;
    }
    
    //Settable update time.
    public UpdatedList(final long seconds) {
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(() -> {
            list = updateList();
            setChanged();
            notifyObservers();
        }, -1L, seconds, TimeUnit.SECONDS);

    }

    abstract public List<T> updateList();
}
