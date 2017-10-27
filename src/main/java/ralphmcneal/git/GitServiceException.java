package ralphmcneal.git;

public class GitServiceException extends RuntimeException {
    int statusCode;

    public GitServiceException(int statusCode) {
        this.statusCode = statusCode;
    }

    public GitServiceException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public GitServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public int getStatusCode() {
        return statusCode;
    }
}
