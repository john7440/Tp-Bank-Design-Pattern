package service;

import dao.AccountDao;
import dao.ClientDao;
import dao.OperationDao;
import exception.AccountNotFoundException;
import model.Account;
import model.Client;
import model.Operation;


import java.time.LocalDateTime;
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

    public Account findAccountByNumber(String accountNumber){
        Account account = accountDao.findByNumber(accountNumber);
        if (account ==null){
            throw new AccountNotFoundException("Account not found: "+ accountNumber);
        }
        return account;
    }

    public double getBalance(String accountNumber){
        Account account = findAccountByNumber(accountNumber);
        return account.getBalance();
    }

    /*-------------------gestion opératios----------*/
    public void deposit(String accountNumber, double amount){
        if (amount < 0){
            throw new IllegalArgumentException("Amount must be positive");
        }

        Account account = findAccountByNumber(accountNumber);

        account.credit(amount);
        accountDao.update(account);

        Operation operation = new Operation();
        operation.setType("DEPOSIT");
        operation.setAmount(amount);
        operation.setAccountId(account.getId());
        operation.setOperationDate(LocalDateTime.now());
        operationDao.save(operation);

        System.out.println("Deposited " + amount + " € on account: " + account.getNumber());
        System.out.println("New balance: " + account.getBalance() + " €");
    }


}
