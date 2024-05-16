package main.se.kth.iv1350.View;

import java.util.Scanner;

import main.se.kth.iv1350.Controller.Controller;
import main.se.kth.iv1350.Integration.DataBaseNotFoundException;
import main.se.kth.iv1350.Integration.ItemDTO;
import main.se.kth.iv1350.Integration.ItemIDNotFoundException;

public class View {
      Controller controller;
      Scanner in = new Scanner(System.in);

        public View(Controller controller){
            this.controller = controller;

        }

        public void takeInput(){
            System.out.println("Welcome to Daniel and Johannas Store!");
            System.out.println("Use [start] to start new sale");
            System.out.println("Use [scan quantity itemID] to scan an item");
            System.out.println("Use [end] to end the sale and go to payment");
            while (true) {
                String input[] = in.nextLine().split(" ");
                switch (input[0]) {
                    case "start":
                        controller.startSale();
                        System.out.println("Sale started");
                        break;
                
                    case "scan":
                        scanItem(Integer.parseInt(input[1]), input[2]);
                        break;    
                
                    case "end":
                        endSale();
                        break;
                    default:
                        break;
                }
            }
        }

        public void scanItem(int quantity, String itemID){
            try {
                ItemDTO item = controller.addItem(quantity, itemID);
                System.out.println(item.toString());
                System.out.println("Total: " + controller.getRunningTotal());
            } catch (ItemIDNotFoundException e) {
                System.out.println(e.getMessage());
                // TODO: handle exception
            } catch (DataBaseNotFoundException e){
                System.out.println("Internal error");
            }
        }

        private void endSale(){
            float sum = controller.getRunningTotal();
            System.out.println("Total cost: " + sum + " SEK");
            System.out.println("Paid in cash: ");
            float paid = in.nextFloat();
            controller.pay(paid);
            controller.sendToExternalSystems();
            System.out.println("Sale ended");
        }



    }

