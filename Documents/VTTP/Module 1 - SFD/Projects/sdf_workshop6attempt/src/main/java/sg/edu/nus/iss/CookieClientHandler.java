package sg.edu.nus.iss;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class CookieClientHandler implements Runnable {

    private final Socket sock;

    public CookieClientHandler(Socket s) {
        sock = s;

    }

    public void run() {

        try {

            InputStream is = sock.getInputStream();
            DataInputStream dis = new DataInputStream(is);

            OutputStream os = sock.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);

            String input = "";
            Cookie cookie = new Cookie();

            while (!input.equals("close")) {
                input = dis.readUTF();

                if (input.trim().toLowerCase().equals("get-cookie")) {
                    dos.writeUTF("cookie-text");
                    dos.writeUTF(cookie.getCookie());
                    dos.flush();
                }
            }

            is.close();
            os.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
