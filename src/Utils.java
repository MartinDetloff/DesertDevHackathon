package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Utils 
{
    public static void main(String[] args)
    {   
        Utils utils = new Utils();

        String szTestName = "Sample Name ";
        String szTestDesc = "Sample Description ";
        String szTestAddress = "Sample Address ";
        int iTestMaxPrice = 1000;
        int iTestMinPrice = 100;
        for (int i = 0; i <= 5; i ++)
        {
            RestaurantData restaurantdata = new RestaurantData(szTestName + i, szTestDesc + i, szTestAddress + i, iTestMaxPrice, iTestMinPrice, "9:00-9:30");
            utils.AddRestaurant(restaurantdata);
        }

        utils.GetRestaurants();
        utils.ClearRestaurants();
    }
    
    public ArrayList<RestaurantData> GetRestaurants()
    {
        ArrayList<RestaurantData> rdList = new ArrayList<>();

        File myObj = new File("resources/data.txt");

        try ( Scanner myReader = new Scanner(myObj) )
        {
            while (myReader.hasNextLine()) 
            {
                String data = myReader.nextLine();
                String[] dataArr = data.split(",");

                int iID = Integer.parseInt(dataArr[0]);
                String szName = dataArr[1];
                String szDesc = dataArr[2];
                String szAddr = dataArr[3];
                int iMaxPrice = Integer.parseInt(dataArr[4]);
                int iMinPrice = Integer.parseInt(dataArr[5]);
                String szPickupTime = dataArr[6];

                RestaurantData rd = new RestaurantData(iID, szName, szDesc, szAddr, iMaxPrice, iMinPrice, szPickupTime);
                
                rdList.add(rd);
            }
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return rdList;
    }

    public boolean AddRestaurant(RestaurantData restaurantData)
    {
        try ( FileWriter fileWriter = new FileWriter("resources/data.txt", true) )
        {   
            fileWriter.write(restaurantData.GetID() + ",");
            fileWriter.write(restaurantData.GetName() + ",");
            fileWriter.write(restaurantData.GetAddress() + ",");
            fileWriter.write(restaurantData.GetDescription() + ",");
            fileWriter.write(restaurantData.GetMaxPrice() + ",");
            fileWriter.write(restaurantData.GetMinPrice() + ",");
            fileWriter.write(restaurantData.GetPickupTime() + "\n");
            fileWriter.close();
        } 
        catch (IOException e) 
        {
            System.err.println("FileWriter Failed to open");
            return false;
        }

        return true;
    }
    
    public boolean ClearRestaurants()
    {
        try ( FileWriter fileWriter = new FileWriter("resources/data.txt", false) )
        {   
            fileWriter.close();
        } 
        catch (IOException e) 
        {
            System.err.println("FileWriter Failed to open");
            return false;
        }

        return true;
    }
}
