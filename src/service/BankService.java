package service;

import dao.AccountDao;
import dao.ClientDao;
import dao.OperationDao;
import exception.AccountNotFoundException;
import model.Account;
import model.Client;


import java.util.List;

public class BankService {
    private ClientDao clientDao;
    private AccountDao accountDao;
    private OperationDao operationDao;

    public BankService(){
        this.clientDao = new ClientDao();
        this.accountDao = new AccountDao();
        this.operationDao = new OperationDao();
    }

    /*----------------------gestion des clients----------------*/
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


   /*------------------gestion comptes--------------------*/
    public List<Account> getAllAccounts(){
        return accountDao.findAll();
    }
    
}
