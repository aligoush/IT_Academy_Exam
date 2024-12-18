package utils;

public class IDGenerator {
    private static int id = 1;

    public static int generateId(){
        return id++;
    }
}
