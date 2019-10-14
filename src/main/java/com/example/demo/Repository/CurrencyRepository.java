package com.example.demo.Repository;

import com.example.demo.model.Currency;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CurrencyRepository {
    private List<Currency> currencyList;

    public CurrencyRepository() {
        this.currencyList = new ArrayList<>();
    }
    public List<Currency> getCurrencyList()
    {
        return currencyList;
    }
}
