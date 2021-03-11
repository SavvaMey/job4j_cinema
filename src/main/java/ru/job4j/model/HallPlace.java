package ru.job4j.model;

import java.util.Objects;

public class HallPlace implements Comparable<HallPlace> {
    private int id;
    private int row;
    private int col;
    private boolean status;
    private int accountId;

    public HallPlace(int id, int row, int col, boolean status, int accountId) {
        this.id = id;
        this.row = row;
        this.col = col;
        this.status = status;
        this.accountId = accountId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HallPlace hallPlace = (HallPlace) o;
        return id == hallPlace.id && row == hallPlace.row
                && col == hallPlace.col
                && accountId == hallPlace.accountId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, row, col, accountId);
    }

    @Override
    public String toString() {
        return "HallPlace{"
                + "id=" + id
                + ", row=" + row
                + ", col=" + col
                + ", status=" + status
                + ", accountId=" + accountId
                + '}';
    }

    @Override
    public int compareTo(HallPlace o) {
        return Integer.compare(id, o.id);
    }
}
