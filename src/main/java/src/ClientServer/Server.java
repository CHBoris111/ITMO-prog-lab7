package src.ClientServer;
// �� �������� �������� ����!
import src.Command.Command;
import src.User.User;
import src.Utils.ManagerOfCollection;
import src.Utils.PasswordUtils.LoginPasswordManager;

import java.io.*;
import java.net.*;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.sql.SQLException;
import java.util.Scanner;

public class Server
{
    private static boolean sendResponse(User user, BufferedWriter writer) throws IOException, ClassNotFoundException
    {
        boolean response = LoginPasswordManager.compareUser(user);

        writer.write(String.valueOf(response));
        writer.newLine();
        writer.flush();
        return response;
    }
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        ManagerOfCollection.createMyCollection();
        ManagerOfCollection.fillFromPostgres();

        LoginPasswordManager.fillMap();

        int port = MyPortReader.read("Write a port (in integer format, more than 1024):");

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(port));
        Selector selector = Selector.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true)
        {
            selector.select();
            Socket clientSocket = serverSocket.accept();

            File fileName = new File("outServer.txt");

            try
            {
                ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                PrintStream out = new PrintStream(new FileOutputStream(fileName));
                System.setOut(out);

                Scanner scanner = new Scanner(fileName);

                try
                {
                    User user = (User) objectInputStream.readObject();
                    boolean responseToClient = sendResponse(user, writer);
                    if(responseToClient)
                    {
                        Command command = (Command) objectInputStream.readObject();
                        command.execute(user);
                        System.out.println(command);
                        out.close();
                        while (scanner.hasNextLine())
                        {
                            writer.write(scanner.nextLine() + "@");
                        }
                        writer.newLine();
                        writer.flush();
                    }
                } catch (ClassNotFoundException e)
                {
                    System.out.println(e.getMessage());
                } catch (IllegalStateException | NullPointerException | SQLException ex)
                {
                    System.out.println("There is no command " + ". For reference, use � help");
                }
            }
            catch (SocketException e)
            {
                System.out.println("Client sent nothing and left.");
            }
        }
    }
}
