import javax.swing.*;
import java.io.*;

class Controller
{  static Model model;
   static InputView input;
   static boolean reserve;
   public static void main(String[] args)
   {
      Point p = null;
      reserve = true;
      input = new InputView();
      input.chooseClass();
      model = new Model(input.travelClass);
      //model.writeArray(model.seats);
      model.generateRandomSeats();
      
      
      OutputView output = new OutputView();
      while(reserve)
      {
         int passengers = input.numberOfPassengers();
         
         
         if(passengers <= model.freeSpace())
         {
         JOptionPane.showMessageDialog(null, "finished");
         int preference = input.aisleOrWindow();
         if(preference == 0)
         {p = model.searchLinear(passengers, model.seats,0);}
         else if(preference == 1)
         {p = model.linearWithAisle(passengers, model.seats);}
         else if(preference == 2)
         {p = model.linearWithWindow(passengers, model.seats);}
         if(p != null)
         {model.fill(p);}
         output.repaint(); //1
         if(p == null)
         {  boolean b;
            if(preference == 0)
            {b = model.searchDistributed(passengers, model.seats, 0);}
            else if(preference == 1 && model.hasAisle())
            {
               b = model.searchDistributedAisle(passengers, model.seats);
            }
            else if(preference == 2 && model.hasWindow())
            {
               b = model.searchDistributedWindow(passengers, model.seats);
            }
         }
         JOptionPane.showMessageDialog(null, "finished");
         output.repaint();
         String again = JOptionPane.showInputDialog("Do you want to reserve again?");
         if(again.equals("yes")){}
         else if(again.equals("no"))
         {reserve = false;output.repaint();JOptionPane.showMessageDialog(null, "Have a nice trip!");System.exit(0);}
      }
      else{JOptionPane.showMessageDialog(null, "Theres not enough free seats.");input.next();}
      }
   }
}