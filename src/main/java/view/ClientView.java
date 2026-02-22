package view;

import model.Client;
import service.ClientService;

import java.util.List;
import java.util.Scanner;

public class ClientView implements View{
    private final ClientService clientService;
    private final Scanner scanner;

    public ClientView(ClientService clientService, Scanner scanner) {
        this.clientService = clientService;
        this.scanner = scanner;
    }

    @Override
    public void display() {
        displayAllClients();
    }

    private void displayAllClients() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("     All Clients     ");
        System.out.println("\n" + "=".repeat(60));

        List<Client> clients = clientService.getAllClients();

        if (clients.isEmpty()) {
            System.out.println("No clients found");
        } else {
            System.out.printf("%-5s | %-20s | %-30s | %-15s%n",
                    "ID", "Name", "Email", "Phone");
            System.out.println("-".repeat(60));

            for (Client client : clients) {
                System.out.printf("%-5d | %-20s | %-30s | %-15s%n",
                        client.getId(),
                        client.getName(),
                        client.getEmail(),
                        client.getPhone() != null ? client.getPhone() : "N/A"
                );
            }
            System.out.println("\n" + "=".repeat(60));
            System.out.println("Total clients: " + clients.size());
        }
    }

    public void displayClientDetails(Long clientId) {
        Client client = clientService.findClientById(clientId);
        System.out.println("\n" + "=".repeat(60));
        System.out.println("Client Details:");
        System.out.println("ID: " + client.getId());
        System.out.println("Name: " + client.getName());
        System.out.println("Email: " + client.getEmail());
        System.out.println("Phone: " + (client.getPhone() != null ? client.getPhone() : "N/A"));
        System.out.println("=".repeat(60));
    }
}
