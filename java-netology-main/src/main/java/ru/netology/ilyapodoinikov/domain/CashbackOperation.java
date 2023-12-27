package ru.netology.ilyapodoinikov.domain;

public class CashbackOperation extends Operation{
    private int cashbackAmount;

    public CashbackOperation(int id, int customerId, int sum, String currency, String merchant) {
        super(id, customerId, sum, currency, merchant);
    }
}