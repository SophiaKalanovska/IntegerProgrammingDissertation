package model.Parser;

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
}