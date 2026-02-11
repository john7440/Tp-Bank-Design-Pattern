package service;

import dao.AccountDao;
import dao.ClientDao;
import dao.OperationDao;

public class BankService {
    private ClientDao clientDao;
    private AccountDao accountDao;
    private OperationDao operationDao;

    public BankService(){
        this.clientDao = new ClientDao();
        this.accountDao = new AccountDao();
        this.operationDao = new OperationDao();
    }
}
