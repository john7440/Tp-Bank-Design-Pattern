package service;

import dao.AccountDao;
import dao.ClientDao;
import dao.OperationDao;
import exception.AccountNotFoundException;
import exception.InsufficientBalanceException;
import model.Account;
import model.Client;
import model.CurrentAccount;
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
    public List<Account> getClientAccounts(Long clientId) {
        Client client = findClientById(clientId);
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

    public void withdraw(String accountNumber, double amount){
        if (amount < 0){
            throw new IllegalArgumentException("Amount must be positive");
        }

        Account account = findAccountByNumber(accountNumber);
        if (account instanceof CurrentAccount){
            CurrentAccount currentAccount = (CurrentAccount) account;
            if (!currentAccount.canWithdraw(amount)){
                throw new InsufficientBalanceException("Insufficient balance");
            }
        } else {
            if (account.getBalance() < amount){
                throw new InsufficientBalanceException("Insufficient balance");
            }
        }
        account.debit(amount);
        accountDao.update(account);

        Operation operation = new Operation();
        operation.setType("WITHDRAWAL");
        operation.setAmount(amount);
        operation.setAccountId(account.getId());
        operation.setOperationDate(LocalDateTime.now());
        operationDao.save(operation);

        System.out.println("Withdrawal successful: " + amount +" € from account: " + account.getNumber());
        System.out.println("New balance: "+ account.getBalance() + " €");
    }

    public List<Operation> getAccountHistory(String accountNumber){
        Account account = findAccountByNumber(accountNumber);
        return operationDao.findByAccountId(account.getId());
    }

    public void displayAccountHistory(String accountNumber){
        Account account = findAccountByNumber(accountNumber);
        List<Operation> operations = operationDao.findByAccountId(account.getId());

        System.out.println("\n=====================================");
        System.out.println("History of account: " + account.getNumber());
        System.out.println("Current balance: " + account.getBalance() + "€");
        System.out.println("\n=====================================");

        if (operations.isEmpty()){
            System.out.println("No operations yet");
        } else {
            for (Operation op : operations){
                String symbol = op.getType().equals("DEPOSIT") ? "+" : "-";
                System.out.printf("%s | %s %s%.2f€%n",
                        op.getOperationDate().toString().substring(0,19),
                        op.getType(),
                        symbol,
                        op.getAmount()
                );
            }
        }
        System.out.println("\n====================================");
    }

}
