/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab11;
import java.util.Random;

/**
 *
 * @author padma
 */
public class threadapplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Number n=new Number();
        n.start();
        // TODO code application logic here
    }
    
}
class Square extends Thread
{
    int x;
    Square(int n)
    {
        x=n;
    }
    public void run()
    {
        int sqr=x*x;
        System.out.println("Square of"+x+"="+sqr);
    }
}
class Cube extends Thread
{
    int x;
    Cube(int n)
    {
        x=n;
        
    }
    public void run()
    {
        int cub=x*x*x;
        System.out.println("Cube of"+x+"="+cub);
        
    }
}
class Number extends Thread
{
    public void run()
    {
        Random random=new Random();
        for(int i=0;i<10;i++)
        {
            int randomInteger=random.nextInt(100);
            System.out.println("Random Integer generated :"+ randomInteger);
            Square s=new Square(randomInteger);
            s.start();
            Cube c=new Cube(randomInteger);
            c.start();
            try{
                Thread.sleep(100);
                
            }
            catch(InterruptedException ex){
                System.out.println(ex);
            }
                
            
        }
    }
}