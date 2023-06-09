package src.Command;

import src.BaseObjects.*;

import src.User.User;
import src.Utils.HeliosConnectable;
import src.Utils.ManagerOfCollection;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;


public class Receiver implements Serializable{
    public final Invoker commandInvoker;

    public Receiver(Invoker commandInvoker) {
        this.commandInvoker = commandInvoker;
    }

    public void help() {
        commandInvoker.invokerHashMap.forEach((name, command) -> command.writeInfo());
    }

    public void info() {
        ManagerOfCollection.getInformationAbout();
    }

    public void show() {
        ManagerOfCollection.show();
    }

    public void add(SpaceMarine spaceMarineFromClient, User user) throws IOException, SQLException, ClassNotFoundException {
        spaceMarineFromClient.setCreatedBy(user.getLogin());
        Connection con = HeliosConnectable.createConToDB();
        ManagerOfCollection.insertSpaceMarine(spaceMarineFromClient, con);
        Long id = ManagerOfCollection.getCurrentIdInPostgres();
        System.out.println("An element with ID has been created: " + id);
        spaceMarineFromClient.setId(id);
        ManagerOfCollection.add(spaceMarineFromClient);
    }
    // TODO: ���������� ��� ���� ���� � ��������
    public void update(Long id, SpaceMarine spaceMarineFromClient, User user) {
        try {
            long ID = id;
            if (ManagerOfCollection.elemExist(ID)) {
                if (Objects.equals(ManagerOfCollection.getElemByID(id).getCreatedBy(), user.getLogin())) {
                    ManagerOfCollection.update(spaceMarineFromClient, ID);
                    ManagerOfCollection.save();
                    System.out.println("Update completed");
                }
                else {
                    System.out.println("You can not modify this object!!!");
                }
            }
            else {System.out.println("The item with this ID is not in the collection.");}
        } catch (NumberFormatException e) {
            System.out.println("The command is not executed. You have entered an incorrect argument.");
        } catch (IOException | ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove_by_id(Long id, User user) {
        try {
            long ID = id;
            if (ManagerOfCollection.elemExist(ID)) {
                if (Objects.equals(ManagerOfCollection.getElemByID(id).getCreatedBy(), user.getLogin())) {
                    ManagerOfCollection.remove_by_id(ID);
                    System.out.println("Element with ID " + ID + " was deleted successfully");
                }
                else {
                    System.out.println("This user can not modify this object!!!");
                }
            } else {System.out.println("There is no element with such ID in the collection");}
        } catch (NumberFormatException e) {
            System.out.println("The command is not executed. You have entered an incorrect argument.");
        }
    }

    public void clear(User user) throws IOException, SQLException, ClassNotFoundException {
        ManagerOfCollection.clear(user);
        ManagerOfCollection.save();
    }

    public void exit() throws IOException, SQLException, ClassNotFoundException {
        System.out.println("Save you progress in collection? [yes/no]");

        Scanner exitScanner = new Scanner(System.in);
        while (true) {
            if (exitScanner.hasNextLine()) {
                String ans = exitScanner.nextLine();
                if (ans.equals("yes")) {
                    ManagerOfCollection.save();
                    break;
                } else if (ans.equals("no")) {
                    break;
                }
                else {System.out.println("Invalid answer. [yes/no]");}
            }
        }
        System.out.println("Program is ending, bye-bye!");
        System.exit(0);
    }

    public void remove_greater(SpaceMarine spaceMarineFromClient, User user) throws IOException, SQLException, ClassNotFoundException {
        ManagerOfCollection.remove_greater(spaceMarineFromClient, user);
        ManagerOfCollection.save();
    }

    public void remove_lower(SpaceMarine spaceMarineFromClient, User user) throws IOException, SQLException, ClassNotFoundException {
        ManagerOfCollection.remove_lower(spaceMarineFromClient, user);
        ManagerOfCollection.save();
    }

    public void save() throws IOException, SQLException, ClassNotFoundException {
        ManagerOfCollection.save();
    }

    public void history() {
        if (commandInvoker.invokerListOfCommand.size() >= 11) {
            for (int i = commandInvoker.invokerListOfCommand.size() - 1; i > commandInvoker.invokerListOfCommand.size() - 11; i--) {
                System.out.println(commandInvoker.invokerListOfCommand.get(i));
            }
        }
        else {
            System.out.println("There are not enough elements to output the last 11 elements. Current number of items: " + commandInvoker.invokerListOfCommand.size());
        }
    }

    public void remove_all_by_health(Double health, User user) throws IOException, SQLException, ClassNotFoundException {
        double HP = health;

        ManagerOfCollection.remove_all_by_health(HP, user);
        ManagerOfCollection.save();
    }

    public void max_by_melee_weapon() {
        ManagerOfCollection.max_by_melee_weapon();
    }

    public void print_unique_chapter() {
        ManagerOfCollection.print_unique_chapter();
    }

}
