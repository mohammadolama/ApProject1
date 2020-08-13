package Server;

import Server.Controller.MainLogic.HibernateCore;
import Server.Controller.MainLogic.Server;
import org.hibernate.SessionFactory;

import java.util.Scanner;

public class ServerMain {

    public static void main(String[] args) {
        HibernateCore.connectToDataBase();
        SessionFactory sessionFactory = HibernateCore.getInstance();
        new Server(8000).start();
    }
}
