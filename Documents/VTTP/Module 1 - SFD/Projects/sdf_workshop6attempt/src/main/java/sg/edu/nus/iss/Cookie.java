package sg.edu.nus.iss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cookie {

    private List<String> cookieList;

    public Cookie() {
        this.cookieList = new ArrayList<>();
        File cookieFile = new File("cookie_file.txt");
        try {
            FileReader fr = new FileReader(cookieFile);
            BufferedReader br = new BufferedReader(fr);
            String input = "";
            while ((input = br.readLine()) != null) {
                if (!input.equals("")) {
                    cookieList.add(input);
                }
            }
            br.close();
            fr.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getCookie() {
        Random rnd = new Random();
        int cookieNo = rnd.nextInt(this.cookieList.size()-1);
        return this.cookieList.get(cookieNo);
    }
}
