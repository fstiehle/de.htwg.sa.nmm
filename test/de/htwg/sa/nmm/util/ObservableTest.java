package de.htwg.sa.nmm.util;

import static org.junit.Assert.*;

import de.htwg.sa.nmm.util.observer.IObserver;
import de.htwg.sa.nmm.util.observer.Observable;
import org.junit.Before;
import org.junit.Test;

public class ObservableTest {
    private boolean ping=false;
    private TestObserver testObserver;
    private Observable testObservable;

    class TestObserver implements IObserver {
        @Override
        public void update() {
            ping=true;
        }
    }

    @Before
    public void setUp() throws Exception {
        testObserver = new TestObserver();
        testObservable = new Observable();
        testObservable.addObserver(testObserver);
    }

    @Test
    public void testNotify() {
        assertFalse(ping);
        testObservable.notifyObservers();
        assertTrue(ping);
    }

    @Test
    public void testRemove() {
        assertFalse(ping);
        testObservable.removeObserver(testObserver);
        testObservable.notifyObservers();
        assertFalse(ping);
    }

    @Test
    public void testRemoveAll() {
        assertFalse(ping);
        testObservable.removeAllObservers();
        testObservable.notifyObservers();
        assertFalse(ping);
    }
}
