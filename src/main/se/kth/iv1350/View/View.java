package main.se.kth.iv1350.View;

import java.util.Scanner;

import main.se.kth.iv1350.Controller.Controller;
import main.se.kth.iv1350.Integration.ItemDTO;

public class View {
      Controller controller;
      Scanner in = new Scanner(System.in);

        public View(Controller controller){
            this.controller = controller;
            controller.addTotalRevenueObserver(new TotalRevenueView());

            while(true) takeInput();
        }

        private void takeInput(){
            String input[] = in.nextLine().split(" ");
            switch (input[0]) {
                case "start":
                    controller.startSale();
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

        private void scanItem(int quantity, String itemID){
            try {
                ItemDTO item = controller.addItem(quantity, itemID);
                System.out.println(itemID.toString());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                // TODO: handle exception
            }
        }

        private void endSale(){
            float sum = controller.getRunningTotal();
            System.out.println("Total cost: " + sum);
            System.out.println("Paid in cash: ");
            Scanner in = new Scanner(System.in);
            float paid = in.nextFloat();
            System.out.println(controller.pay(paid));
            controller.sendToExternalSystems();
            in.close();
        }



    }

