package io.hongting.common.exception;

/**
 * @author hongting
 * @create 2021 11 09 12:32 PM
 */
public class LoginAcctExistedException extends  RuntimeException {

    private static final long serialVersionUID =1L;

    public LoginAcctExistedException() {
    }

    public LoginAcctExistedException(String message) {
        super(message);
    }

    public LoginAcctExistedException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginAcctExistedException(Throwable cause) {
        super(cause);
    }

    public LoginAcctExistedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
