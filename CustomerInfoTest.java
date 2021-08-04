/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oracletask;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.groupingBy;

/**
 *
 * @author com
 */
public class CustomerInfoTest {

    public static void main(String[] args) {

        String s = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\n"
                + "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\n"
                + "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\n"
                + "1233456,2345,us_west,BlueTeam,ProjectDate,2221s\n"
                + "3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s";

        String[] rows = s.split("\n");
        List<Customer> customerList = new ArrayList<>();
        String[] customerInfoArr = null;
        for (String row : rows) {
           customerInfoArr = row.split(",");
           Customer customer  = new Customer();
           customer.setCustomerId(customerInfoArr[0]);
           customer.setContractId(customerInfoArr[1]);
           customer.setGeozone(customerInfoArr[2]);
           customer.setTeamcode(customerInfoArr[3]);
           customer.setProjectcode(customerInfoArr[4]);
           customer.setBuildduration(Integer.parseInt(customerInfoArr[5].replace("s", "")));
           customerList.add(customer);
        }

        //Case 1: The number of unique customerId for each contractId.
        System.out.println(customerList.stream().collect(Collectors.groupingBy(e -> e.getContractId(),
                Collectors.groupingBy(e -> e.getCustomerId(), Collectors.counting()))));

        //Case 2 :The number of unique customerId for each geozone.
        System.out.println(customerList.stream().collect(Collectors.groupingBy(e -> e.getGeozone(),
                Collectors.groupingBy(e -> e.getCustomerId(), Collectors.counting()))));
       //Case 3 : The average buildduration for each geozone.
        System.out.println(customerList.stream().collect(groupingBy( p -> p.getGeozone(),
                        averagingInt(p -> p.getBuildduration()))));
        
        //Case 4 :The list of unique customerId for each geozone."
        System.out.println(customerList.stream().collect(Collectors.groupingBy(e -> e.getGeozone(),
                Collectors.mapping(e -> e.getCustomerId(),Collectors.toList()))));        

    }
}

























