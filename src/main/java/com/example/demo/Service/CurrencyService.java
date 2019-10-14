package com.example.demo.Service;

import com.example.demo.model.Currency;
import com.example.demo.Repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService {

    private CurrencyRepository currencyRepository;
    private StringBuilder stringBuilder;

    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;

    }
    public List<Currency> getCurrencyList()
    {
        return currencyRepository.getCurrencyList();
    }
    public void addToCurrencyList(String currencyString)
    {
        currencyString = removeBrackets(currencyString);

        String[] tabOfCurrency;
        String currency;
        Double value;

        tabOfCurrency = currencyString.split(",");
        for(String str : tabOfCurrency)
        {
            value = Double.parseDouble(str.substring(str.indexOf(":")+1));
            currency = str.substring(str.indexOf(":")-4,str.indexOf(":")-1);

            currencyRepository.getCurrencyList().add(new Currency(currency,value));
        }
    }
    public String removeBrackets(String str)
    {
        stringBuilder = new StringBuilder(str);
        stringBuilder.deleteCharAt(str.indexOf("}"));
        stringBuilder.deleteCharAt(str.indexOf("{"));
        return stringBuilder.toString();
    }
    public Currency getRandomCurrency()
    {
        int random = (int) (Math.random()* currencyRepository.getCurrencyList().size());
        return currencyRepository.getCurrencyList().get(random);

    }
}
