package org.example.Command;

public class AbstractCommand  {
    protected String name;

    public  AbstractCommand(String name)
    {
        this.name = name;
    }

    public boolean execute()  {
        return false;
    }

    @Override
    public String toString() {
        return  name;
}
}


