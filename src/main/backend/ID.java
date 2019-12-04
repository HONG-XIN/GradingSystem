package main.backend;

public class ID {
    private int number;
    ID(){
       number = 0;
    }
    ID(int number){
        this.number = number;
    }
    public boolean checkSame(ID id){
        if(this.number == id.number){
            return true;
        }
        return false;
    }
    //accesser
    public String getID(){
        return String.format("%08",this.number);
    }
}
