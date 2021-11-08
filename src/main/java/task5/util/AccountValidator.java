package task5.util;

import task5.dao.api.AccountInfoDAO;
import task5.dao.impl.AccountInfoDAOIml;
import task5.exception.AccountNotFoundException;

import java.util.logging.Logger;

public class AccountValidator {

    private static final Logger log = Logger.getLogger(AccountValidator.class.getName());

    private static final AccountInfoDAO accountInfoDAO = new AccountInfoDAOIml();

    public static void verifyAccountExist(String userName) {
        final boolean accExist = accountInfoDAO.findAll().stream().anyMatch(account -> account.getUserName().equals(userName));
        if (!accExist) {
            throw new AccountNotFoundException(LogUtil.logBusinessException(AccountValidator.class.getName(),
                    String.format("Account with name [%s] not found", userName)));
        }
    }
}
