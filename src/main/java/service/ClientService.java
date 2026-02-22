package service;

import dao.ClientDao;
import exception.AccountNotFoundException;
import model.Client;

import java.util.List;

public class ClientService {
    private final ClientDao clientDao;

    public ClientService() {
        this.clientDao = new ClientDao();
    }

    public List<Client> getAllClients(){
        return clientDao.getAllClients();
    }

    public Client findClientById(long id){
        Client client = clientDao.findById(id);
        if (client ==null){
            throw new AccountNotFoundException("Client not found: "+ id);
        }
        return client;
    }
}
