package az.spring.studentmanagementservice.studentmanagementservice.exception;

public class ServiceException extends RuntimeException {

    private final String code;
    private final String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ServiceException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ServiceException of(String code, String message) {
        return new ServiceException(code, message);
    }

}