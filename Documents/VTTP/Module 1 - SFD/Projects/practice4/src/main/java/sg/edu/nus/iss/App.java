package sg.edu.nus.iss;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class App 
{
    public static void main( String[] args ) throws IOException
    {
        int serverNo = Integer.parseInt(args[0]);
        String fileName = args[1];

        ServerSocket server = new ServerSocket(serverNo);
        Socket socket = server.accept();
        String input = "";
        Cookie cookies = new Cookie();
        cookies.readCookieFile(fileName);

        try (InputStream is = socket.getInputStream()) {
            BufferedInputStream bis = new BufferedInputStream(is);
            DataInputStream dis = new DataInputStream(bis);

            try (OutputStream os = socket.getOutputStream()) {
                BufferedOutputStream bos = new BufferedOutputStream(os);
                DataOutputStream dos = new DataOutputStream(bos);

                while(!(input.equals("close"))) {

                    input = dis.readUTF();

                    if (input.equals("get-cookie")) {
                        dos.writeUTF(cookies.getCookie());
                        dos.flush();
                    } else {
                        dos.writeUTF("");
                        dos.flush();
                    }

                    dos.close();
                    bos.close();
                    os.close();
                }
            } catch (EOFException ex) {
                ex.printStackTrace();
            }

            dis.close();
            bis.close();
            is.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            socket.close();
            server.close();
        }
    }
