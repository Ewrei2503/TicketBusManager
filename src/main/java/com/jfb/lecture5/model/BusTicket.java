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
    if(ticketType.equals(TicketType.MONTH.name()) && !(startDate == null || startDate.isEmpty())){
      throw new IllegalStartDateException("The start date must be empty");
    } else if(Arrays.stream(TicketType.values()).anyMatch(t -> t.name().equals(ticketType))){
      return ticketType;
    }else throw new IllegalTicketTypeException("Ticket type is not valid" + ticketType);
  }
}
