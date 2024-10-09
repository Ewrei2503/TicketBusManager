package com.jfb.lecture5.model;

import com.jfb.lecture5.enums.TicketType;
import com.jfb.lecture5.exceptions.IllegalPriceException;
import com.jfb.lecture5.exceptions.IllegalStartDateException;
import com.jfb.lecture5.exceptions.IllegalTicketTypeException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

@Getter
@Setter
@ToString
public class BusTicket {
    private String ticketClass;

    private String ticketType;

    private String startDate;

    private String price;


    public BusTicket() {}

    public BusTicket(BusTicket busTicket) throws IllegalStartDateException, IllegalTicketTypeException, IllegalPriceException {
        this.ticketClass = busTicket.getTicketClass();
        this.ticketType = validateTicketType(busTicket.ticketType, busTicket.getStartDate());
        this.startDate = validateStartDate(busTicket.getStartDate());
        this.price = validatePrice(busTicket.getPrice());
    }

    private static String validateTicketType(String ticketType, String startDate) throws IllegalTicketTypeException, IllegalStartDateException {
        if(ticketType==null){
            throw new IllegalTicketTypeException("Ticket type cannot be null");
        } else if(Arrays.stream(TicketType.values()).noneMatch(t -> t.name().equals(ticketType))){
            throw new IllegalTicketTypeException("Ticket type is not valid: " + ticketType);
        } else if(!ticketType.equals(TicketType.MONTH.name()) && (startDate == null || startDate.isEmpty())){
            throw new IllegalStartDateException("The start date cannot be empty for this ticket type: " + ticketType);
        } else return ticketType;
    }

    private static String validatePrice(String price) throws IllegalPriceException {
        if(price==null || BigDecimal.ZERO.compareTo(new BigDecimal(price)) == 0){
            throw new IllegalPriceException("The price can't be zero or null");
        } else if(new BigDecimal(price).remainder(BigDecimal.TWO).equals(BigDecimal.ONE)){
            throw new IllegalPriceException("The price can't be odd");
        } else return price;
    }

    private static String validateStartDate(String startDate) throws IllegalStartDateException {
        if(LocalDate.parse(startDate).isAfter(LocalDate.now())){
            throw new IllegalStartDateException("The start date can't be in the future");
        } else return startDate;
    }
}
