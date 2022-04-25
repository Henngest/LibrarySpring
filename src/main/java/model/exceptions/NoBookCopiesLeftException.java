package model.exceptions;

public class NoBookCopiesLeftException extends Exception{
    public NoBookCopiesLeftException(){
        System.out.println("There are no copies of the book left.");
    }
}
