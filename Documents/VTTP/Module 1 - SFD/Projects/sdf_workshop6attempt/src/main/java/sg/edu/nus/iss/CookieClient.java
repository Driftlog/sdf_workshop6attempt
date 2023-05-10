package sg.edu.nus.iss;

import java.io.BufferedOutputStream;
import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class CookieClient {
    public static void main( String[] args ) throws Exception
    {
        int portNo = Integer.parseInt(args[0]);
        Socket sock = new Socket("localhost", portNo);

        try (OutputStream os = sock.getOutputStream()) {
            BufferedOutputStream bos = new BufferedOutputStream(os);
            DataOutputStream dos = new DataOutputStream(bos);

            InputStream is = sock.getInputStream();
            DataInputStream dis = new DataInputStream(is);

            String input = "";
            while (!input.equals("close")) {
                Console console = System.console();
                input = console.readLine("Enter command: ");
                
                if (input.toLowerCase().trim().equals("get-cookie")) {
                    dos.writeUTF("get-cookie");
                    dos.flush();
                    String cookie = dis.readUTF();
                    System.out.println(cookie);
                    cookie = dis.readUTF();
                    System.out.println(cookie);
                    
                }

                if (input.equals("close")) {
                    dos.writeUTF("close");
                    dos.flush();
                }
            }

            os.close();
            is.close();
        }
        sock.close();
    }

}

