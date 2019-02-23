package Model.Parser;

public class Exceptions {

    static class NoWeightException extends Throwable {
        NoWeightException(String s) {
        }
    }

    static class ExceptionNotZero extends Throwable {
        ExceptionNotZero(String something_else) {
        }
    }

    static class ExceptionNotATerm extends Throwable {
        ExceptionNotATerm(String s) {
        }
    }

    static class ExceptionNotNumber extends Throwable {
        ExceptionNotNumber(String s) {
        }
    }
}