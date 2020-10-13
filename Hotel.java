/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab11;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 *
 * @author padma
 */
public class Hotel {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ArrayList<String> menu = new ArrayList<String>();
        HotelThread Thread1 = new HotelThread("Thread 1");
        HotelThread Thread2 = new HotelThread("Thread 2");

        Thread1.start();

        try
        {
            Thread1.join();
        }
        catch (InterruptedException e)
        {
            System.out.println(e);
        }

        System.out.println();

        Thread2.start();

        try
        {
            Thread2.join();
        }
        catch (InterruptedException e)
        {
            System.out.println(e);
        }

        System.out.println();

        System.out.println("From, " + Thread.currentThread().getName());
        System.out.println("Enter Menu Items, okay to finish ");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try
        {
            String item = reader.readLine();

            while (!item.equalsIgnoreCase("okay"))
            {
                menu.add(item);
                item = reader.readLine();
            }

        }
        catch (IOException e)
        {
            System.out.println(e);
            System.exit(1);
        }

        Customer customer1 = Thread1.GetCustomer();
        Customer customer2 = Thread2.GetCustomer();

        System.out.println("\nResult For Customer 1 ...");

        for (String food : customer1)
        {
            if (menu.contains(food))
            {
                System.out.println(food + " is Available. ");
            } else
            {
                System.out.println(food + " is Not Available. ");
            }
        }
         System.out.println("GoodBye, Come again");

        System.out.println("\nResult For Customer 2 ...");

        for (String food : customer2)
        {
            if (menu.contains(food))
            {
                System.out.println(food + " is Available. ");
            } else
            {
                System.out.println(food + " is Not Available. ");
            }
        }

        System.out.println("GoodBye, Come again");
    
        
    }
    }
class Customer extends ArrayList<String>
{
   

    // Stores coustomer name
    private String name;

    // Default Constructor
    public Customer()
    {
        super();
    }

    // constructs with iterator of obj
    public Customer(Customer obj)
    {
        super(obj);
    }

    // constructs with size
    public Customer(int size)
    {
        super(size);
    }

    // Sets Coustomer name
    public void SetName(String Name)
    {
        name = Name;
    }

    // gets Coustomer name
    public String GetName()
    {
        return name;
    }
}

class HotelThread extends Thread
{
    private Customer customer;

    public HotelThread(String ThreadName)
    {
        super(ThreadName);
        customer = new Customer();
    }

    public void run()
    {
        synchronized (customer)
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            try
            {
                System.out.println("From, " + super.getName());

                System.out.println("Enter Name : ");
                String name = reader.readLine();

                customer.SetName(name);

                System.out.println("Order Items, okay to finish ");

                String food = reader.readLine();

                while (!food.equalsIgnoreCase("okay"))
                {
                    customer.add(food);
                    food = reader.readLine();
                }

            }
            catch (IOException e)
            {
                System.out.println(e);
                System.exit(1);
            }
        }
    }

    // get customer
    Customer GetCustomer()
    {
        return customer;
    }
}
