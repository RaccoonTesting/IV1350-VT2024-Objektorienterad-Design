package main.se.kth.iv1350.View;

import main.se.kth.iv1350.Controller.Controller;

public class View {
      Controller controller;
        public View(Controller controller){
            this.controller = controller;


            controller.startSale();
            scanItem(2, "3");
            scanItem(4, "a");
    
            controller.endSale();
        }

        private void scanItem(int quantity, String itemID){
            try {
                controller.addItem(quantity, itemID);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                // TODO: handle exception
            }
        }

    }

