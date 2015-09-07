package youten.redo.jarserver.core;

public interface DataStoreListener {
    public void onChange(String key, String value);
    public void onRead(String key, String value);
}
