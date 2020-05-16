import javax.swing.*;

class InputView
{
   String travelClass;
   String numberOfPassengers;
   public String chooseClass()
   {
      travelClass = JOptionPane.showInputDialog("What class do you want to travel? \n first class \n economy").toLowerCase().trim();
      if(travelClass.equals("economy") || travelClass.equals("first class"))
      {}
      else
      {JOptionPane.showMessageDialog(null, "Wrong class name \n Please write the travel class name again!"); chooseClass();}
      return travelClass;
      }
   
   public int numberOfPassengers()
   {
      numberOfPassengers = JOptionPane.showInputDialog("Write the number of passengers:");
      int x = -1;
      try
      {Integer.parseInt(numberOfPassengers);}
      catch(Exception e)
      {JOptionPane.showMessageDialog(null, "Not a number. \n Please write a number.");numberOfPassengers();}
      x = Integer.parseInt(numberOfPassengers);
      if(x <= 0)
      {JOptionPane.showMessageDialog(null, "Not a positive number. \n Please write a positive number.");numberOfPassengers();}
      return x;
   }
   
   public int aisleOrWindow()
   {
      int answer = -1;
      boolean correct = false;
      while(!correct)
      {
         String s = JOptionPane.showInputDialog("Do you want aisle or window or none? \n Type \n aisle \n window \n none").toLowerCase().trim();
         if(s.equals("aisle"))
         {answer = 1;correct = true; }
         else if(s.equals("window"))
         {answer = 2;correct = true;}
         else if(s.equals("none"))
         {answer = 0;correct = true;}
         else
         {JOptionPane.showMessageDialog(null, "You have made a mistake while typing.");}
      }
      return answer;
   }
   
   public void next()
   {
      String s = JOptionPane.showInputDialog("Type one of the options: \n reserve \n exit");
      if(s.equals("exit"))
      {Controller.reserve = false;}
   }
   
   public String test(int i, int j)
   {
      return JOptionPane.showInputDialog(i + "" + j);
   }
}