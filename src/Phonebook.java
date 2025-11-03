import Exceptions.ImmutablePhonebookExceptions;

import java.util.HashMap;
import java.util.Map;

public class Phonebook implements PhonebookInterface {
    private Map<String, Caller> callers;

    public Phonebook() {
        callers = new HashMap<String, Caller>();
    }

    public void addCaller(String phoneNumber){
        if(!callers.containsKey(phoneNumber)) {
            callers.put(phoneNumber, new Caller(phoneNumber));
        }
        callers.put(phoneNumber, new Caller(phoneNumber));
    }

    public Map<String, Caller> getCallers() {
        return Map.copyOf(callers);
    }

    @Override
    public Caller put(String key, Caller value) {
        throw new ImmutablePhonebookExceptions("This phonebook cannot be modified");
    }

    @Override
    public Caller remove(Object key) {
        throw new ImmutablePhonebookExceptions("This phonebook cannot be modified");
    }

    @Override
    public void clear() {
        throw new ImmutablePhonebookExceptions("This phonebook cannot be modified");
    }

}
