package sg.edu.nus.iss;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class ServerMain {
    public static void main(String[] args) throws Exception {
        int portNo = Integer.parseInt(args[0]);
        ServerSocket socket = new ServerSocket(portNo);
        ExecutorService thrPool = Executors.newFixedThreadPool(3);
        Socket sock = socket.accept();
        System.out.println("Connection Established");
        List<Future<Void>> futures = new ArrayList<>();

        Future<Void> result = thrPool.submit(new CookieClientHandler(sock), null);
        Future<Void> result2 = thrPool.submit(new CookieClientHandler(sock), null);
        Future<Void> result3 = thrPool.submit(new CookieClientHandler(sock), null);

        futures.add(result);
        futures.add(result2);
        futures.add(result3);
        
        for (Future<Void> future : futures) {
            future.get();
        }
        /* if(result.get() && result2.get() && result3.get())
        {
            thrPool.shutdown();
            thrPool.awaitTermination(10, TimeUnit.SECONDS);
        }
        /* 
        Future<Void> result = thrPool.submit(new CookieClientHandler(sock), null);
        Future<Void> result2 = thrPool.submit(new CookieClientHandler(sock), null);
        Future<Void> result3 = thrPool.submit(new CookieClientHandler(sock), null);

        try {
            result.get();
            result2.get();
            result3.get();
            // After task is executed successfully
          } catch (ExecutionException e) {
            Throwable c = e.getCause();
            System.out.println("Something happened running task");
            // After task is aborted by exception
          }

          */

        System.out.println("Terminated");
        socket.close();
        sock.close();
        thrPool.shutdown();
    }
}
