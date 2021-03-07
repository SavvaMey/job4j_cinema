package ru.job4j.store;

import ru.job4j.model.HallPlace;

import java.util.Collection;

public interface Store {
    Collection<HallPlace> getAllPlaces();

    void saveSelectedPlaces(HallPlace place);

    void updatePlacesStore(Collection<HallPlace> places);

    HallPlace findHallPlaceById (int id);

}
