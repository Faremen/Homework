package edu.hw7.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RWLPersonDatabase implements PersonDatabase {

    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    Lock readLock = readWriteLock.readLock();
    Lock writeLock = readWriteLock.writeLock();

    private final Map<Integer, Person> personMap = new HashMap<>();

    private final Map<String, List<Person>> nameMap = new HashMap<>();

    private final Map<String, List<Person>> addressMap = new HashMap<>();

    private final Map<String, List<Person>> phoneNumberMap = new HashMap<>();

    @Override
    public void add(Person person) {
        Objects.requireNonNull(person);

        writeLock.lock();
        try {
            if (!personMap.containsKey(person.id())) {
                personMap.put(person.id(), person);
                addInMap(nameMap, person.name(), person);
                addInMap(addressMap, person.address(), person);
                addInMap(phoneNumberMap, person.phoneNumber(), person);
            }
        } finally {
            writeLock.unlock();
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
        writeLock.lock();
        try {
            if (personMap.containsKey(id)) {
                Person deleted = personMap.get(id);
                personMap.remove(id);
                removeFromMap(nameMap, deleted.name(), deleted);
                removeFromMap(addressMap, deleted.address(), deleted);
                removeFromMap(phoneNumberMap, deleted.phoneNumber(), deleted);
            }
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public Person getById(int id) {
        readLock.lock();
        try {
            return personMap.get(id);
        } finally {
            readLock.unlock();
        }
    }

    private <T, V> void removeFromMap(Map<T, List<V>> map, T key, V value) {
        if (map.containsKey(key)) {
            map.get(key).remove(value);
        }
    }

    @Override
    public List<Person> findByName(String name) {
        Objects.requireNonNull(name);

        readLock.lock();
        try {
            return nameMap.get(name);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public List<Person> findByAddress(String address) {
        Objects.requireNonNull(address);

        readLock.lock();
        try {
            return addressMap.get(address);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public List<Person> findByPhone(String phone) {
        Objects.requireNonNull(phone);

        readLock.lock();
        try {
            return phoneNumberMap.get(phone);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public int size() {
        readLock.lock();
        try {
            return personMap.size();
        } finally {
            readLock.unlock();
        }
    }
}
