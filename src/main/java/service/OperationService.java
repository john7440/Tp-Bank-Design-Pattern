package service;

import dao.AccountDao;
import dao.OperationDao;
import exception.InsufficientBalanceException;
import model.Account;
import model.CurrentAccount;
import model.Operation;

import java.time.LocalDateTime;
import java.util.List;

public class OperationService {
    private final OperationDao operationDao;
    private final AccountDao accountDao;
    private final AccountService accountService;

    public OperationService(){
        this.operationDao = new OperationDao();
        this.accountDao = new AccountDao();
        this.accountService = new AccountService();
    }

    /*-------------------gestion opératios----------*/
    public void deposit(String accountNumber, double amount){
        if (amount < 0){
            throw new IllegalArgumentException("Amount must be positive");
        }

        Account account = accountService.findAccountByNumber(accountNumber);

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

        Account account = accountService.findAccountByNumber(accountNumber);
        if (account instanceof CurrentAccount currentAccount){
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

    public void displayAccountHistory(String accountNumber){
        Account account = accountService.findAccountByNumber(accountNumber);
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
