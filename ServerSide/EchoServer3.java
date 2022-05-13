import java.io.*;
import java.net.*;

public class EchoServer3 {
   static int yesCount = 0;
   static int noCount = 0;
   static int dontCareCount = 0;
   public static void main(String[] args) {
      int serverPort = 7;    // default port
      String message;
      if (args.length == 1 )
         serverPort = Integer.parseInt(args[0]);       
      try {
         // instantiates a stream socket for accepting
         //   connections
       ServerSocket myConnectionSocket = 
            new ServerSocket(serverPort); 
/**/     System.out.println("Echo server ready.");  
         while (true) {  // forever loop
            // wait to accept a connection 
/**/        System.out.println("Waiting for a connection.");
            MyStreamSocket myDataSocket = new MyStreamSocket
                (myConnectionSocket.accept( ));
/**/        System.out.println("connection accepted");
            // Start a thread to handle this client's sesson
            Thread theThread = 
               new Thread(new EchoServerThread(myDataSocket));
            theThread.start();
            // and go on to the next client
            } //end while forever
       } // end try
     catch (Exception ex) {
          ex.printStackTrace( );
     } // end catch
   } //end main
   static public synchronized void incrementYes()
   {
     yesCount++;
   }
   static public synchronized void incrementNo()
   {
     noCount++;
   }
   static public synchronized void incrementDontCare()
   {
     dontCareCount++;
   }  
   static public int getYesCount()
   {
     return yesCount;
   } 
   static public int getNoCount()
   {
     return noCount;
   }
   static public int getDontCareCount()
   {
     return dontCareCount;
   }      
} // end class
