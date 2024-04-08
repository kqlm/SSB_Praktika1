package org.example;
import java.util.List;
public class Bd {
    public static void printPhotorgaf(Photorgafs photorgaf) {
        System.out.println(photorgaf.getId() + " " + photorgaf.getName() + " " + photorgaf.getIsDeleted());
    }
    public static void printClient(Clients client) {
        System.out.println(client.getId() + " " + client.getName() + " " + client.getIdPhotorgafs().getName() + " " + client.isDeleted());
    }
    public static void printClientPhotograf(Clients client) {
        System.out.print(client.getId() + " " + client.getName() + " " + client.isDeleted() + " ");
        printPhotorgaf(client.getIdPhotorgafs());
    }
    public static void printStylists(List<Photorgafs> photorgafs) {
        for (Photorgafs photorgaf : photorgafs) {
            printPhotorgaf(photorgaf);
        }
    }
    public static void printClients(List<Clients> clients) {
        for (Clients client : clients) {
            printClient(client);
        }
    }
    public static void printClientsPhotorgafs(List<Clients> clients) {
        for (Clients client : clients) {
            printClientsPhotorgafs(clients);
        }
    }
}