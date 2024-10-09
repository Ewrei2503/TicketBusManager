package com.jfb.lecture5;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfb.lecture5.exceptions.IllegalPriceException;
import com.jfb.lecture5.exceptions.IllegalStartDateException;
import com.jfb.lecture5.exceptions.IllegalTicketTypeException;
import com.jfb.lecture5.model.BusTicket;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import static com.jfb.lecture5.exceptions.IllegalPriceException.price;
import static com.jfb.lecture5.exceptions.IllegalStartDateException.startDate;
import static com.jfb.lecture5.exceptions.IllegalTicketTypeException.ticketType;

public class Main {

  public static int parseError = 0;
  public static int valid = 0;

  public static void main(String[] args) {
    getTicketsFromFile("src/main/resources/ticketData.txt");
    totalScore();
    totalScoreWithParseError();
  }

  private static void getTicketsFromFile(String fileName) {
    try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      String input;
      BusTicket busTicket;
      while((input=br.readLine())!=null){
        try {
          busTicket = new BusTicket(getBusTicket(input));
          System.out.println(busTicket);
          valid++;
        } catch (IllegalTicketTypeException | IllegalStartDateException | IllegalPriceException e) {
          System.err.println(e.getMessage());
        }
      }
    } catch (IOException e) {
      System.err.println("File not found!");
    }
  }

  private static BusTicket getBusTicket(String input) throws JsonProcessingException {
    try {
      return new ObjectMapper().readValue(input, BusTicket.class);
    }
    catch (JsonParseException e) {
      System.err.println("Parsing error");
      input = input.replace('“','\"');
      parseError++;
      return getBusTicket(input);
    }
  }

  private static String getInput() {
    return new Scanner(System.in).nextLine();
  }

  private static void totalScoreWithParseError() {
    System.out.println(
            "Total = " + (parseError+ticketType+startDate+price) +
            "\nValid = " + valid +
            "\nMost popular violation = " + max(parseError,ticketType,startDate,price)
    );
  }

  private static String max(Integer parseError, Integer ticketType, Integer startDate, Integer price){
    return (Math.max(parseError,Math.max(price,Math.max(ticketType,startDate)))==parseError)? "parse error" :
            ((Math.max(price,Math.max(ticketType,startDate))==price) ? "price" :
                    ((Math.max(ticketType,startDate)==ticketType)? "ticket type" : "start date"));
  }

  private static void totalScore(){
    System.out.println(
            "Total = " + (ticketType+startDate+price) +
                    "\nValid = " + valid +
                    "\nMost popular violation = " + max(ticketType,startDate,price)
    );
  }

  private static String max(Integer ticketType, Integer startDate, Integer price){
    return (Math.max(price,Math.max(ticketType,startDate))==price) ? "price" :
                    ((Math.max(ticketType,startDate)==ticketType)? "ticket type" : "start date");
  }
}
