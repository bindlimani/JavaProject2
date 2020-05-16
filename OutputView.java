import java.awt.*;
import javax.swing.*;

class OutputView extends JPanel
{  
   public OutputView()
   {
      JFrame frame = new JFrame();
      frame.setSize(700,400);
      frame.getContentPane().add(this);
      frame.setVisible(true);
   }
   
   public void paintComponent(Graphics g)
   {  
      int x = 20;
      int y = 20;
      int i = 0;
      
      g.setColor(Color.white);
      g.fillRect(0,0,800,400);
      g.setColor(Color.black);
      Font text = new Font("Times", Font.ITALIC, 18);
      g.setFont(text);
      if(Controller.input.travelClass.equals("economy"))
      {g.drawString("Economy class seats", 200, 20);}
      else if(Controller.input.travelClass.equals("first class"))
      {g.drawString("First class seats", 200, 20);}
      Font indexes = new Font("Arial", Font.PLAIN, 12);
      g.setFont(indexes);
      drawSeats(g, Color.green, Color.red);
      
      g.setColor(Color.black);
   }
   
   public void drawSeats(Graphics g, Color green, Color red)
   {
      int y = 40;
      for(int i=0; i<Controller.model.ROWS; i++)
      {
         int x = 40;
         for(int j=0; j<Controller.model.row_Length+1; j++)
         {
            if(j==3 || j==6)
            {
               x+=40;
            }
            if(j==9 && Controller.model.row_Length==11)
            {x+=40;}
            if(Controller.model.seats[i][j]==false)
            {g.setColor(green);}
            else{g.setColor(red);}
            if(Controller.model.startingSeats[i][j] != Controller.model.seats[i][j])
            {g.setColor(Color.blue);}
            g.fillRect(x,y,20,20);
            g.drawString(i+" " + j,x,y);
            x += 30;
         }
         y += 30;
      }
   }
   
   public void drawReservations(Graphics g, boolean[][] a)
   {
      for(int i=0; i<a.length; i++)
      {
         for(int j=0; j<a[i].length; j++)
         {
            if(a[i][j] != Controller.model.seats[i][j])
            {
               
            }
         }
      }
   }
}