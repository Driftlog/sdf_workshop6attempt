package sg.edu.nus.iss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cookie {

    private List<String> cookies;
    
    public Cookie() {
        this.cookies = new ArrayList<>();
        
    }

    public void readCookieFile(String fileName) throws FileNotFoundException, IOException {
        File cookie = new File("cookie_file.txt");
        if (cookie.exists()) {
        FileReader fr = new FileReader(cookie);
        BufferedReader br = new BufferedReader(fr);
        String cookieLine = "";
        while ((cookieLine = br.readLine()) != null) {
            this.cookies.add(cookieLine);
        }

        br.close();
        fr.close();
        }
    }


    public String getCookie() {
        Random rnd = new Random();
        int cookieNo = rnd.nextInt(this.cookies.size());

        return this.cookies.get(cookieNo);
    }
}
