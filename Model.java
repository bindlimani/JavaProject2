import java.util.*;

class Point
   {
      boolean b;
      int x;
      int y;
      int size;
      int y2;
      
      public Point(boolean b, int x, int y, int size)
      {
         this.b = b;
         this.x = x;
         this.y = y;
         this.size = size;
         y2  = y + size - 1;
      }
      
      public boolean contains(int e)
      {
         boolean answer = false;
         if(y <= e && y2 >= e)
         answer = true;
         return answer;
      }
   }


class Model
{
   final int SEATS_PER_ROW_FIRSTCLASS = 9;
   final int SEATS_PER_ROW_ECONOMY = 12;
   final int ROWS = 10;
   int row_Length;
   //FALSE NENKUPTON QE NUK ESHTE E ZEN PRA KA VEND NDERSA TRUE I BJEN QE ESHTE E NXONME
   boolean[][] seats;
   boolean[][] startingSeats;
   
   //Konstruktori cakton llojin e udhetimit (economy apo firstclass) dhe formon matricen bazuar ne te.
   public Model(String flightClass)
   {
      if(flightClass.equals("economy"))
      {
         seats = new boolean[ROWS][SEATS_PER_ROW_ECONOMY];
         startingSeats = new boolean[ROWS][SEATS_PER_ROW_ECONOMY];
         row_Length = SEATS_PER_ROW_ECONOMY - 1;
      }
      else if(flightClass.equals("first class"))
      {
         seats = new boolean[ROWS][SEATS_PER_ROW_FIRSTCLASS];
         startingSeats = new boolean[ROWS][SEATS_PER_ROW_FIRSTCLASS];
         row_Length = SEATS_PER_ROW_FIRSTCLASS - 1;
      }
   }
   
   public void writeArray(boolean[][] b)
   {
      for(int i=0; i<b.length; i++)
      {
         for(int j=0; j<b[i].length; j++)
         {
            String s = Controller.input.test(i,j);
            if(s.equals("g"))
            {b[i][j]=false; startingSeats[i][j]=false;}
            else if(s.equals("r"))
            {b[i][j]=true; startingSeats[i][j]=true;}
         }
      }
   }
   
   public void generateRandomSeats()
   {
      Random a = new Random();
      int x = 0;
      int y = 0;
      for(int i=0; i<50; i++)
      {
         x = a.nextInt(ROWS);
         y = a.nextInt(row_Length+1);
         seats[x][y] = true;
         startingSeats[x][y] = true;
      }
   }
   
   public boolean hasWindow()
   {
      boolean answer = false;
      for(boolean[] row : seats)
      {
         if(row[0] == false)
         {answer = true;}
      }
      return answer;
   }
   
   public boolean hasAisle()
   {
      boolean answer = false;
      for(boolean[] row : seats)
      {
         if(row[row_Length] == false)
         {answer = true;}
      }
      return answer;
   }
   
   public int[] findAisle()
   {
      int[] a = new int[2];
      for(int i=0; i<seats.length; i++)
      {
         if(seats[i][2] == false)
         {a[0]=i;a[1]=2;break;}
         else if(seats[i][3] == false){a[0]=i;a[1]=3;break;}
         else if(seats[i][5] == false){a[0]=i;a[1]=5;break;}
         else if(seats[i][6] == false){a[0]=i;a[1]=6;break;}
         else if(row_Length==11)
         {
            if(seats[i][8] == false){a[0]=i;a[1]=8;break;}
            else if(seats[i][9] == false){a[0]=i;a[1]=9;break;}
         }
      }
      return a;
   }
   
   public int[] findWindow()
   {
      int[] a = new int[2];
      for(int i=0; i<seats.length; i++)
      {
         if(seats[i][0] == false){a[0]=i;a[1]=0;break;}
         else if(seats[i][row_Length] == false){a[0]=i;a[1]=row_Length;break;}
      }
      return a;
   }
   
   
   
   public void fill(Point p)
   {
      for(int i=p.y; i<=p.y2; i++)
      {seats[p.x][i] = true;}
   }
   
   public int freeSpace()
   {
      int space = 0;
      for(boolean[] r : seats)
      {
         for(boolean b : r)
         if(b == false)
         {space++;}
      }
      return space;
   }
   
   public Point searchLinear(int size,boolean[][] seats, int row)
   {
      int x = -1;
      int y = -1;
      int count = 0;
      Point p = null;
      Bigloop:
      for(int i = row;i<seats.length; i++)
      {
         count = 0;
         y = 0;
         if(seats[i][0] == false)
         {
            count++;
         }
         x = i;
         for(int j=1; j<seats[i].length; j++)
         {
            if(count == size)
            {
               p = new Point(true, x, y, count);
               break Bigloop;
            }  
            else if(seats[i][j] == false && seats[i][j-1] == true)
            {
               y = j;
               count = 1;
            }
            else if(seats[i][j] == false && seats[i][j-1] == false)
            {
               count++;
            }
            else if(seats[i][j] == true)
            {
               count = 0;
            }
            if(count == size)
            {
               p = new Point(true, x, y, count);
               break Bigloop;
            }  
         }
      }
      return p;
   }
   
   public Point linearWithAisle(int size, boolean[][] seats)
   {
      Point p = null;
      for(int i=0; i<seats.length; i++)
      {
         p = searchLinear(size, seats, i);
         if(p == null)
         {break;}
         else if(p != null)
         {
            if(p.contains(2) || p.contains(3) || p.contains(5) || p.contains(6) || p.contains(8) || p.contains(9))
            {
               return p;
            }
            else
            {p = null;}
         }
      }
      return p;
   }
   
   //2
   
   public Point linearWithWindow(int size, boolean[][] seats)
   {
      Point p = null;
      for(int i=0; i<seats.length; i++)
      {
         p = searchLinear(size, seats, i);
         if(p == null)
         {break;}
         else if(p != null)
         {
            if(p.contains(0) || p.contains(row_Length))
            {
               return p;
            }
            else
            {p = null;}
         }
      }
      return p;
   }
   
   public boolean searchDistributed(int size, boolean[][] seats, int startRow)
   {
      int count = 0;
      boolean[][] seatsModified = seats.clone();
      boolean answer = false;
      Bigloop:
      for(int i=startRow; i<seats.length; i++)
      {
         for(int j=0; j<seats[i].length; j++)
         {
            if(seatsModified[i][j] == false)
            {
               count++;
               seatsModified[i][j] = true;
               if(count == size)
               {
                  answer = true;
                  seats = seatsModified.clone();
                  break Bigloop;
               }
            }
         }
      }
      return answer;
   }
   
   public boolean searchDistributedAisle(int size, boolean[][] seats, int startRow, int startColumn)
   {
      int count = 0;
      int firstBreak = 0;
      int j = startColumn;
      boolean[][] seatsModified = seats.clone();
      boolean answer = false;
      Bigloop:
      for(int i=startRow; i<seats.length; i++)
      {
         while(j<seats[i].length)
         {  
            if(firstBreak==1)
            {j=0;firstBreak++;}
            if(seatsModified[i][j] == false)
            {
               count++;
               seatsModified[i][j] = true;
               if(count == size)
               {
                  answer = true;
                  seats = seatsModified.clone();
                  break Bigloop;
               }
            }
            j++;
            firstBreak++;
            if(count == size)
               {
                  answer = true;
                  seats = seatsModified.clone();
                  break Bigloop;
               }
         }
         
      }
      return answer;
   }
   
   public boolean searchDistributedWindow(int size, boolean[][] seats, int startRow, int startColumn)
   {
      int count = 0;
      int firstBreak = 0;
      int j = startColumn;
      boolean[][] seatsModified = seats.clone();
      boolean answer = false;
      Bigloop:
      for(int i=startRow; i<seats.length; i++)
      {
         while(j<seats[i].length)
         {  
            if(firstBreak==1)
            {j=0;firstBreak++;}
            if(seatsModified[i][j] == false)
            {
               count++;
               seatsModified[i][j] = true;
               if(count == size)
               {
                  answer = true;
                  seats = seatsModified.clone();
                  break Bigloop;
               }
            }
            j++;
            firstBreak++;
         }
      }
      return answer;
   }
   
   boolean searchDistributedWindow(int size, boolean[][] a)
   {
      int[] b = findWindow();
      a[b[0]][b[1]] = true;
      boolean c = false;
      if(size == 1)
      {c = true;}
      else
      c = searchDistributed(size-1,a,0);
      return c;
   }
   
   boolean searchDistributedAisle(int size, boolean[][] a)
   {
      int[] b = findAisle();
      a[b[0]][b[1]] = true;
      boolean c = false;
      if(size == 1)
      {c = true;}
      else
      c = searchDistributed(size-1,a,0);
      return c;
   }
   
}