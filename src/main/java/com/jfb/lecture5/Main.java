package com.jfb.lecture5;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfb.lecture5.model.BusTicket;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static com.jfb.lecture5.exceptions.IllegalPriceException.countOfIllegalPrice;
import static com.jfb.lecture5.exceptions.IllegalStartDateException.countOfIllegalStartDate;
import static com.jfb.lecture5.exceptions.IllegalTicketTypeException.countOfIllegalTicketType;

public class Main {

    public static int countOfValidTickets = 0;
    public static boolean isValid = true;

    public static void main(String[] args) {
        getTicketsFromFile();
        totalScore();
    }

    private static void getTicketsFromFile() {
        try(BufferedReader br = new BufferedReader(new FileReader("src/main/resources/ticketData.txt"))) {
            String input;
            while((input=br.readLine())!=null){
                isValid = true;
                getBusTicket(input);
                if(isValid){
                    countOfValidTickets++;
                }
            }
        } catch (IOException e) {
            System.err.println("File not found!");
        }
    }

    private static void getBusTicket(String input){
        try {
            BusTicket.validateBusTicket(new ObjectMapper().readValue(input, BusTicket.class));
        }
        catch (JsonProcessingException e) {
            input = input.replace('“','\"');
            getBusTicket(input);
        }
    }

    private static void totalScore(){
        System.out.println(
                "Ticket type: " + countOfIllegalTicketType +
                "\nStart date: " + countOfIllegalStartDate +
                "\nPrice: " + countOfIllegalPrice +
                "\nTotal = " + (countOfIllegalTicketType+countOfIllegalStartDate+countOfIllegalPrice) +
                "\nValid = " + countOfValidTickets +
                "\nMost popular violation = " + max(countOfIllegalTicketType,countOfIllegalStartDate,countOfIllegalPrice));
    }

    private static String max(Integer ticketType, Integer startDate, Integer price){
        return (Math.max(price,Math.max(ticketType,startDate))==price) ? "price" :
                       ((Math.max(ticketType,startDate)==ticketType)? "ticket type" : "start date");
    }
}
