package edu.hw7.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SyncPersonDatabase implements PersonDatabase {

    private final Object monitor = new Object();

    private final Map<Integer, Person> personMap = new HashMap<>();

    private final Map<String, List<Person>> nameMap = new HashMap<>();

    private final Map<String, List<Person>> addressMap = new HashMap<>();

    private final Map<String, List<Person>> phoneNumberMap = new HashMap<>();

    @Override
    public void add(Person person) {
        Objects.requireNonNull(person);

        synchronized (monitor) {
            if (!personMap.containsKey(person.id())) {
                personMap.put(person.id(), person);
                addInMap(nameMap, person.name(), person);
                addInMap(addressMap, person.address(), person);
                addInMap(phoneNumberMap, person.phoneNumber(), person);
            }
        }
    }

    private <T, V> void addInMap(Map<T, List<V>> map, T key, V value) {
        if (map.containsKey(key)) {
            map.get(key).add(value);
        } else {
            var list = new ArrayList<V>();
            list.add(value);
            map.put(key, list);
        }
    }

    @Override
    public void delete(int id) {
        synchronized (monitor) {
            if (personMap.containsKey(id)) {
                Person deleted = personMap.get(id);
                personMap.remove(id);
                removeFromMap(nameMap, deleted.name(), deleted);
                removeFromMap(addressMap, deleted.address(), deleted);
                removeFromMap(phoneNumberMap, deleted.phoneNumber(), deleted);
            }
        }
    }

    @Override
    public Person getById(int id) {
        return personMap.get(id);
    }

    private <T, V> void removeFromMap(Map<T, List<V>> map, T key, V value) {
        if (map.containsKey(key)) {
            map.get(key).remove(value);
        }
    }

    @Override
    public List<Person> findByName(String name) {
        Objects.requireNonNull(name);

        synchronized (monitor) {
            return nameMap.get(name);
        }
    }

    @Override
    public List<Person> findByAddress(String address) {
        Objects.requireNonNull(address);

        synchronized (monitor) {
            return addressMap.get(address);
        }
    }

    @Override
    public List<Person> findByPhone(String phone) {
        Objects.requireNonNull(phone);

        synchronized (monitor) {
            return phoneNumberMap.get(phone);
        }
    }

    @Override
    public int size() {
        synchronized (monitor) {
            return personMap.size();
        }
    }
}
