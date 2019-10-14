package com.example.demo.gui;

import com.example.demo.model.Currency;
import com.example.demo.Service.CurrencyService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Theme(Lumo.class)
@Route("")
public class GameGui extends VerticalLayout {

    private CurrencyService currencyService;
    private Currency currency;
    private TextField textField;
    private Button buttonConfirm;
    private Details infoAboutGame;
    private Label labelOne,labelTwo;
    private BigDecimal bd;
    private Double currencyToGuessValue;
    private int counter = 0;

    @Autowired
    public GameGui(CurrencyService currencyService) {
        this.currencyService = currencyService;
        currency = currencyService.getRandomCurrency();
        UI.getCurrent().getElement().setAttribute("theme",Lumo.DARK);

        labelOne = new Label("Guess the value of "+currency.getName()+" in PLN !");
        textField = new TextField();
        buttonConfirm = new Button("OK");

        infoAboutGame = new Details("Info about this game",
                new Text("You need to guess what the value of the currency is converted to PLN today. The values are rounded to two decimal places."));
        labelTwo = new Label("");

        bd = new BigDecimal(1/currency.getValue()).setScale(2, RoundingMode.HALF_UP);
        currencyToGuessValue = bd.doubleValue();

        buttonConfirm.addClickListener(event -> {
        try {
                if (Double.parseDouble(textField.getValue()) > currencyToGuessValue) {
                    counter++;
                    labelTwo.setText("Too much!");
                } else if (Double.parseDouble(textField.getValue()) < currencyToGuessValue) {
                    counter++;
                    labelTwo.setText("Not enough!");
                } else {
                    counter++;
                    labelTwo.setHeight("100px");
                    labelTwo.setText("Congratulations, YOU WON! Needed " + counter + " tries!");
                    buttonConfirm.setVisible(false);
                }
            }
        catch (NumberFormatException exeption)
        {
            exeption.getStackTrace();
            labelTwo.setText("You didn't enter a number, try again!");
        }
        });
        setAlignItems(Alignment.CENTER);
        add(labelOne,textField,buttonConfirm,labelTwo,infoAboutGame);
    }
}
