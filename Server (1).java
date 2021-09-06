import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    public static void main(String[] args) throws IOException {
        System.out.println("Hello World");
        //create socket obj
        Socket socket = null;
        InputStreamReader inputStreamReader=null;
        OutputStreamWriter outputStreamWriter=null;
        //read blocks to save time(efficincy) using buffer thats flushed when full
        BufferedReader bufferedReader=null;
        BufferedWriter bufferedWriter=null;
        ServerSocket serverSocket=null;

        serverSocket=new ServerSocket(4444);
        //creates new socket evertime client accepts conn
        while(true)
        {
            try
            {
                socket=serverSocket.accept();
                inputStreamReader=new InputStreamReader(socket.getInputStream());
                outputStreamWriter=new OutputStreamWriter(socket.getOutputStream());
                bufferedReader=new BufferedReader(inputStreamReader);
                bufferedWriter=new BufferedWriter(outputStreamWriter);
                //while loop 2 is for sending data back and forth
                while(true)
                {
                    String msgFromClient = bufferedReader.readLine();
                    char infoType = msgFromClient.charAt(0);
                    int msgLength = msgFromClient.length();
                    
                    switch(infoType){
                        case '1':
                            System.out.println("Student Name: " + msgFromClient.substring(2,msgLength));
                            bufferedWriter.write("Received student name\n");
                            break;
                        case '2':
                            System.out.println("Student Number" + msgFromClient.substring(2,msgLength));
                            bufferedWriter.write("Received student number\n");
                            break;
                        case '3':
                            System.out.println("Degree" + msgFromClient.substring(2,msgLength));
                            bufferedWriter.write("Received student degree\n");
                            break;
                        case '4':
                            System.out.println("Full Details" + msgFromClient.substring(2,msgLength));
                            bufferedWriter.write("Received student name\n");
                            break;
                    }
                    
                    //System.out.println("Client: "+ msgFromClient);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                    if(msgFromClient.equalsIgnoreCase("BYE"))
                        break;
                }
                socket.close();
                inputStreamReader.close();
                outputStreamWriter.close();
                bufferedReader.close();
                bufferedWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
public class Student{
    String name;
    int number;
    String degree;
    public Student(String name, int number, String degree){
        this.name = name;
        this.number = number;
        this.degree = degree;
    }
}
