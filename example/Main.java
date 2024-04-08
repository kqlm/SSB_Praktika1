package org.example;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception{
        IRepo<Clients> clientsRepo = new ClientsRepozitory();
        IRepo<Photorgafs> stylistsRepo = new PhotograsfRepozitory();

        List<Clients> clients = clientsRepo.getList();
        List<Photorgafs> photorgafs = stylistsRepo.getList();

        for (Clients client : clients) {
            Bd.printClient(client);
        }

        for (Photorgafs photorgaf : photorgafs) {
            Bd.printPhotorgaf(photorgaf);
        }

        for (Clients client : clients) {
            Bd.printClientsPhotorgafs(clients);
        }
    }
}