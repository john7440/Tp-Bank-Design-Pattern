package service;

import dao.AccountDao;
import dao.ClientDao;
import exception.AccountNotFoundException;
import model.Account;
import model.Client;

import java.util.List;


public class AccountService {
    private final AccountDao accountDao;
    private final ClientService clientService;

    public  AccountService(){
        this.accountDao = new AccountDao();
        this.clientService = new ClientService();
    }

    /*------------------gestion comptes--------------------*/
    public List<Account> getClientAccounts(Long clientId) {
        Client client = clientService.findClientById(clientId);
        return accountDao.findByClientId(client.getId());
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
}
