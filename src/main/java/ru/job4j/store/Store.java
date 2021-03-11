package ru.job4j.store;

import ru.job4j.model.Account;
import ru.job4j.model.HallPlace;

import java.util.Collection;

public interface Store {
    Collection<HallPlace> getAllPlaces();

    Collection<HallPlace> getReservedPlaces(String[] id);

    Account saveUser(Account account);

    Account findAccount(String phone);

    void updatePlacesStore(Account account, String[] idS);
}
