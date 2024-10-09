package com.jfb.lecture5;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfb.lecture5.model.BusTicket;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static com.jfb.lecture5.exceptions.IllegalPriceException.price;
import static com.jfb.lecture5.exceptions.IllegalStartDateException.startDate;
import static com.jfb.lecture5.exceptions.IllegalTicketTypeException.ticketType;

public class Main {

    public static int valid = 0;
    public static boolean validity = true;

    public static void main(String[] args) {
        getTicketsFromFile();
        totalScore();
    }

    private static void getTicketsFromFile() {
        try(BufferedReader br = new BufferedReader(new FileReader("src/main/resources/ticketData.txt"))) {
            String input;
            while((input=br.readLine())!=null){
                validity = true;
                getBusTicket(input);
                if(validity){
                    valid++;
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
                "Ticket type: " + ticketType +
                "\nStart date: " + startDate +
                "\nPrice: " + price +
                "\nTotal = " + (ticketType+startDate+price) +
                "\nValid = " + valid +
                "\nMost popular violation = " + max(ticketType,startDate,price));
    }

    private static String max(Integer ticketType, Integer startDate, Integer price){
        return (Math.max(price,Math.max(ticketType,startDate))==price) ? "price" :
                       ((Math.max(ticketType,startDate)==ticketType)? "ticket type" : "start date");
    }
}
