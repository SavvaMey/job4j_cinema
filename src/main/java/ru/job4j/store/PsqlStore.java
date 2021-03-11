package ru.job4j.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.model.Account;
import ru.job4j.model.HallPlace;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class PsqlStore implements Store {
    private final BasicDataSource pool = new BasicDataSource();
    private static final Logger LOG = LoggerFactory.getLogger(PsqlStore.class.getName());

    private PsqlStore() {
        Properties cfg = new Properties();
        try (BufferedReader io = new BufferedReader(
                new FileReader("dbCinema.properties")
        )) {
            cfg.load(io);
        } catch (Exception e) {
            LOG.error("properties ex", e);
        }
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
        } catch (Exception e) {
            LOG.error("driver ex", e);
        }
        pool.setDriverClassName(cfg.getProperty("jdbc.driver"));
        pool.setUrl(cfg.getProperty("jdbc.url"));
        pool.setUsername(cfg.getProperty("jdbc.username"));
        pool.setPassword(cfg.getProperty("jdbc.password"));
        pool.setMinIdle(5);
        pool.setMaxIdle(10);
        pool.setMaxOpenPreparedStatements(100);
    }

    private static final class Lazy {
        private static final Store INST = new PsqlStore();
    }

    public static Store instOf() {
        return Lazy.INST;
    }

    @Override
    public Collection<HallPlace> getAllPlaces() {
        TreeSet<HallPlace> places = new TreeSet<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(
                     "SELECT * FROM halls")
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    places.add(new HallPlace(it.getInt("id"),
                            it.getInt("row"),
                            it.getInt("col"),
                            it.getBoolean("status"),
                            0));
                }
            }
        } catch (Exception e) {
            LOG.error("db ex", e);
        }
        return places;
    }

    @Override
    public void updatePlacesStore(Account account, String[] idS) {
        StringJoiner sj = new StringJoiner(", ", "(", ")");
        Arrays.stream(idS).forEach(sj::add);
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(
                     "UPDATE  halls SET status = TRUE, accaunt_id=(?) where id IN" + sj.toString())
        ) {
            ps.setInt(1, account.getId());
            ps.executeQuery();
        } catch (Exception e) {
            LOG.error("db ex", e);
        }
    }


    @Override
    public Collection<HallPlace> getReservedPlaces(String[] idS) {
        StringJoiner sj = new StringJoiner(", ", "(", ")");
        Arrays.stream(idS).forEach(sj::add);
        TreeSet<HallPlace> places = new TreeSet<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(
                     "SELECT * FROM halls WHERE id IN " + sj.toString())
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    places.add(new HallPlace(it.getInt("id"),
                            it.getInt("row"),
                            it.getInt("col"),
                            it.getBoolean("status"),
                            0));
                }
            }
        } catch (Exception e) {
            LOG.error("db ex", e);
        }
        return places;
    }

    @Override
    public Account saveUser(Account account) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(
                     "INSERT INTO accounts(fio, phone) VALUES (?, ?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, account.getFio());
            ps.setString(2, account.getPhone());
            ps.executeUpdate();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    account.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            LOG.error("db CRUD ex", e);
        }
        return account;
    }

    @Override
    public Account findAccount(String phone) {
        Account account = null;
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("select * from accounts where phone = ?")) {
            ps.setString(1, phone);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String fio = rs.getString("fio");
                    account = new Account(id, fio, phone);

                }
            }
        } catch (Exception e) {
            LOG.error("Ошибка запроса.", e);
        }
        return account;
    }


}
