import java.util.ArrayList;

class Computer 
{ 
    public int ram;
    
    public Computer(int r) 
    { 
        ram = r; 
    }
    
    public boolean isFast() 
    { 
        return ram >= 8; 
    }
}

public class Test {


    public static int countFastComputers(ArrayList<Computer> computers) 
    {
        int count = 0;
        for (Computer computer : computers) 
        {
            if (computer.isFast()) 
            {
                count++;
            }
        }
        return count;
    }
    public static void main(String[] args) {

        // [8, 12, 4, 18, 6] 

        ArrayList<Computer> computers = new ArrayList<Computer>();


        computers.add(new Computer(8));
        computers.add(new Computer(12));
        computers.add(new Computer(4));
        computers.add(new Computer(18));    


        int fastComputerCount = countFastComputers(computers);
        System.out.println("Number of fast computers: " + fastComputerCount);
    }

}
