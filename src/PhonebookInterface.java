public interface PhonebookInterface {
    Caller put(String key, Caller value);

    Caller remove(Object key);

    void clear();
}
