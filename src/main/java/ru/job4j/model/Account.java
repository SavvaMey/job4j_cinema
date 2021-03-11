package ru.job4j.model;

public class Account {
    private int id;
    private String fio;
    private String phone;

    public Account(int id, String fio, String phone) {
        this.id = id;
        this.fio = fio;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Account{"
                + "id=" + id
                + ", fio='" + fio + '\''
                + ", phone='" + phone + '\''
                + '}';
    }
}
