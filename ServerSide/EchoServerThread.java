import java.io.*;

class EchoServerThread implements Runnable {
   static final String endMessage = ".";
   MyStreamSocket myDataSocket;
   EchoServerThread(MyStreamSocket myDataSocket) {
      this.myDataSocket = myDataSocket;
   }
   public void run( ) {
      boolean done = false;
      String message;
      String error = "Sorry incorrect input please try again";
      try {
        while(true)
        {
          message = myDataSocket.receiveMessage();     //receive
          if((message.trim()).equals("I want to vote"))
          {
            break;
          }
          else
          {
           message = error;
           myDataSocket.sendMessage(message);     //optional send
          }
        }
        while(true)
        {
          message = "Vote(1) yes, (2) no, or (3) don't care" ;
          myDataSocket.sendMessage(message);       //send 
          message = myDataSocket.receiveMessage();  //recieve
          if((message.trim()).equals("1"))
          {
            EchoServer3.incrementYes();
            break;
          }
          else if((message.trim()).equals("2"))
          {
            EchoServer3.incrementNo();
            break;
          }
          else if((message.trim()).equals("3"))
          {
            EchoServer3.incrementDontCare();
            break;
          }
          else
          {
            message = error;
            myDataSocket.sendMessage(message);   //optional send
          }
        }      
         while (!done) 
         {
             message = ("Vote recorded, Press any number to see result or . to exit") ;
             myDataSocket.sendMessage(message);   // send 
             message = myDataSocket.receiveMessage( );  //receive          
             if ((message.trim()).equals (endMessage)){
                //Session over; close the data socket.
/**/            System.out.println("Session over.");
                myDataSocket.close( );
                done = true;
             } //end if
             else {
                // Now send the echo to the requesto
                message = ("Scores are: " + EchoServer3.getYesCount() + " Yes, " +
                EchoServer3.getNoCount() + " No, " + EchoServer3.getDontCareCount()
                + " Don't Care");             
                myDataSocket.sendMessage(message); //send 
             } //end else
          } //end while !done
        }// end try
        catch (Exception ex) {
           System.out.println("Exception caught in thread: " + ex);
        } // end catch
   } //end run
} //end class 
