package com.example.demo.Controller;

import com.example.demo.Service.CurrencyService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@Controller
public class CurrencyController {

    private CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;

        RestTemplate restTemplate = new RestTemplate();

        JsonNode currency = restTemplate.getForObject("https://api.exchangerate-api.com/v4/latest/PLN",
                JsonNode.class).get("rates");
       currencyService.addToCurrencyList(String.valueOf(currency));
       System.out.println(currency);
       System.out.println(currencyService.getCurrencyList());


    }


}
