import java.io.*;
import java.net.*;
import java.util.Scanner;

import jdk.javadoc.internal.doclets.formats.html.SourceToHTMLConverter;

public class Client
{
    public static void main(String[] args)
    {
        //create socket obj
        Socket socket = null;
        InputStreamReader inputStreamReader=null;
        OutputStreamWriter outputStreamWriter=null;
        //read blocks to save time(efficincy) using buffer thats flushed when full
        BufferedReader bufferedReader=null;
        BufferedWriter bufferedWriter=null;

        try
        {
            System.out.println("Connection established");
            System.out.println("Enter your choice:\n 1.Name\n 2.Student Number\n 3.Degree\n 4.Full Details");
            /*
            
            */
            //create socket
            socket =new Socket("localhost",4444);
            inputStreamReader=new InputStreamReader(socket.getInputStream());
            outputStreamWriter= new OutputStreamWriter(socket.getOutputStream());

            bufferedReader=new BufferedReader(inputStreamReader);
            bufferedWriter=new BufferedWriter(outputStreamWriter);

            Scanner scanner=new Scanner(System.in);
            Scanner sc=new Scanner(System.in);
            
            while(true)
            {
                System.out.println("Enter your choice:\n 1.Name\n 2.Student Number\n 3.Degree\n 4.Full Details");
                String msgToSend = "";
                int choice = sc.nextInt();
                
                switch(choice){
                    case 1: 
                        //prompt use for input
                        System.out.println("Enter your name: ");
                        //pad message with info id to specify detail provided
                        msgToSend ="1 " + sc.next();
                        break;
                    case 2: 

                        System.out.println("Enter your student number: ");
                        msgToSend ="2 " + sc.next();
                        break;
                    case 3: 
                        System.out.println("Enter your degree: ");
                        msgToSend ="3 " + sc.next();
                        break;
                    case 4: 
                        System.out.println("Enter full details: ");
                        msgToSend ="4 " + sc.next();
                        break;
                    default: 
                        System.out.println("Choose a valid option");
                        break;
                }
                

                bufferedWriter.write(msgToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();

                System.out.println("Server: "+ bufferedReader.readLine());
                if(msgToSend.equalsIgnoreCase("BYE"))
                    break;
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally
        {
            try
            {
                if(socket!=null)
                    socket.close();
                if(inputStreamReader!=null)
                    inputStreamReader.close();
                if(outputStreamWriter!=null)
                    outputStreamWriter.close();
                if(bufferedReader!=null)
                    bufferedReader.close();
                if(bufferedWriter!=null)
                    bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
