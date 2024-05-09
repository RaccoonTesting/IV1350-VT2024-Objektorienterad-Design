package main.se.kth.iv1350.View;

import main.se.kth.iv1350.Controller.Controller;

public class View {
      Controller controller;
        public View(Controller controller){
            this.controller = controller;


            controller.startSale();
            controller.addItem(2, "3");
            controller.addItem(4, "5");
    
            controller.endSale();
        }

    }

