package model.exceptions;

public class BookNameExistsException extends Exception{
    public BookNameExistsException(){
        System.out.println("There's already a book with the given name.");
    }
}
