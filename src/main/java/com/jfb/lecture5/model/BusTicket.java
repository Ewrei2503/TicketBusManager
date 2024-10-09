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

    BusTicket(){}

    public static void validateBusTicket(BusTicket busTicket){
        validateTicketType(busTicket.getTicketType(), busTicket.getStartDate());
        validateStartDate(busTicket.getStartDate());
        validatePrice(busTicket.getPrice());
    }

    private static void validateTicketType(String ticketType, String startDate){
        try {
	        if(ticketType == null || Arrays.stream(TicketType.values()).noneMatch(t -> t.name().equals(ticketType))) {
                validateStartDateByNotValidTicketType(ticketType, startDate);
            }else if(ticketType.equals(TicketType.MONTH.name()) && !(startDate == null || startDate.isEmpty())) {
                throw new IllegalStartDateException("The start date must be empty for this ticket type: " + ticketType);
            }else if(!ticketType.equals(TicketType.MONTH.name()) && (startDate == null || startDate.isEmpty())) {
		        throw new IllegalStartDateException("The start date cannot be empty for this ticket type: " + ticketType);
	        }
        } catch(IllegalTicketTypeException | IllegalStartDateException e) {
	        System.err.println(e.getMessage() + ((e.getCause()==null)?"": "\n" + e.getCause().getMessage()));
        }
    }

    private static void validateStartDateByNotValidTicketType(String ticketType, String startDate) throws IllegalTicketTypeException {
        if(startDate == null || startDate.isEmpty()) {
            throw new IllegalTicketTypeException("Ticket type is not valid: " + ticketType);
        } else throw new IllegalTicketTypeException("Ticket type is not valid: " + ticketType,new IllegalStartDateException("Start date must be null or empty for this type: " + ticketType));
    }

    private static void validatePrice(String price){
	    try {
	        if(price==null || BigDecimal.ZERO.compareTo(new BigDecimal(price)) == 0){
	            throw new IllegalPriceException("The price can't be zero or null");
	        } else if(new BigDecimal(price).remainder(BigDecimal.valueOf(2L)).equals(BigDecimal.ONE)){
	            throw new IllegalPriceException("The price can't be odd");
	        }
	    } catch(IllegalPriceException e) {
			System.err.println(e.getMessage());
		}
    }

    private static void validateStartDate(String startDate) throws IllegalStartDateException {
	    try {
			if(startDate!=null && !startDate.isEmpty() && LocalDate.parse(startDate).isAfter(LocalDate.now())){
	            throw new IllegalStartDateException("The start date can't be in the future");
	        }
	    } catch(IllegalStartDateException e) {
		    System.err.println(e.getMessage());
	    }
    }
}
