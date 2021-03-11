package ru.job4j.store;

import ru.job4j.model.Account;
import ru.job4j.model.HallPlace;

import java.util.Collection;

public class MemStore implements Store {
    private static final Store INST = new MemStore();

    public static Store instOf() {
        return INST;
    }

    @Override
    public Collection<HallPlace> getAllPlaces() {
        return null;
    }

    @Override
    public Collection<HallPlace> getReservedPlaces(String[] id) {
        return null;
    }

    @Override
    public Account saveUser(Account account) {
        return null;
    }

    @Override
    public Account findAccount(String phone) {
        return null;
    }

    @Override
    public void updatePlacesStore(Account account, String[] idS) {

    }
}
