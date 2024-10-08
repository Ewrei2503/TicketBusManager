package com.jfb.lecture5;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfb.lecture5.exceptions.IllegalStartDateException;
import com.jfb.lecture5.exceptions.IllegalTicketTypeException;
import com.jfb.lecture5.model.BusTicket;

import java.util.Scanner;

public class Main {

  public static int parseError = 0;
  public static void main(String[] args) throws JsonProcessingException {
    int x = 0;

    do {
      String input = getInput();
      BusTicket busTicket;
      try {
        busTicket = new BusTicket(getBusTicket(input));
        System.out.println(busTicket);
      }catch (IllegalTicketTypeException | IllegalStartDateException e){
        System.err.println(e.getMessage());
      }
      x++;
    } while (x < 17);
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
}
