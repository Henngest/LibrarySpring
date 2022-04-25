package model.exceptions;

public class BookIdNotFoundException extends Exception{
    public BookIdNotFoundException(){
        System.out.println("Book not found for the given id.");
    }
}
