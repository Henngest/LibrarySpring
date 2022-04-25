package model.exceptions;

public class AuthorIdNotFoundException extends Exception{
    public AuthorIdNotFoundException(){
        System.out.println("Author not found for the given id.");
    }
}
