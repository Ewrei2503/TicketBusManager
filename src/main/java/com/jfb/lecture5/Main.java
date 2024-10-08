package com.jfb.lecture5;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfb.lecture5.exceptions.IllegalStartDateException;
import com.jfb.lecture5.exceptions.IllegalTicketTypeException;
import com.jfb.lecture5.model.BusTicket;

import java.util.Scanner;

import static com.jfb.lecture5.exceptions.IllegalPriceException.price;
import static com.jfb.lecture5.exceptions.IllegalStartDateException.startDate;
import static com.jfb.lecture5.exceptions.IllegalTicketTypeException.ticketType;

public class Main {

  public static int parseError = 0;
  public static int valid = 0;

  public static void main(String[] args) throws JsonProcessingException {
    int x = 0;

    do {
      String input = getInput();
      BusTicket busTicket;
      try {
        busTicket = new BusTicket(getBusTicket(input));
        System.out.println(busTicket);
        valid++;
      }catch (IllegalTicketTypeException | IllegalStartDateException e){
        System.err.println(e.getMessage());
      }
      x++;
    } while (x < 17);
    totalScore();
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

  private static void totalScore(){
    System.out.println(
            "Total = " + (parseError+ticketType+startDate+price) +
            "\nValid = " + valid +
            "\nMost popular violation = " + max(parseError,ticketType,startDate,price)
    );
  }

  private static int max(int a, int b, int c, int d){
    return Math.max(a,Math.max(Math.max(b,c),d));
  }
}
