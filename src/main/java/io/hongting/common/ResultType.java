package io.hongting.common;

import ch.qos.logback.classic.spi.ILoggingEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author hongting
 * @create 2021 11 03 3:54 PM
 */
@Getter
@AllArgsConstructor
@ToString
public enum ResultType {

    SUCCESS(200, "Success"),
    ERROR(500, "Server internal error"),
    USERNAME_EXIST_ERROR(1001, "Username existed"),
    SERVLET_ERROR(-102, "servlet request exception"),
    SQL_GRAMMAR_ERROR(-101, "sql grammar error"),
    LOGIN_ACCT_EXISTED(208, "Username has been created"),
    LOGIN_PASSWORD_ERROR(209, "Incorrect password"),
    LOGIN_ACCT_ERROR(207, "Username is not registered"),
    LOGIN_AUTH_ERROR(401, "Have not logged in, Please re-login "),
    NO_TOKEN_ERROR (401, "Could not find the token"),
    INVALID_TOKEN_ERROR(401,"Invalid token, Please re-login"),
    UPLOAD_ERROR(-103, "Fail to upload!"),
    CATEGORY_NOT_UNIQUE (-103, "Category has been created"),
    HIGH_REQUEST_FREQUENCY(403, "Request frequency is too high"),
    ARCHIVE_IS_NULL(301 , "Archive is null"),
    NO_PERMISSION (401, "This account has no permission to access this resource") ,
    TOKEN_EXPIRED (401, "Token has expired, Please re-login");

    private  final Integer code;
    private  final String message;


}

