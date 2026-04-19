package src;

public class RestaurantData 
{
    private String m_szName;
    private String m_szDesc;
    private String m_szAddress;
    private String m_szPickupTime;
    private String m_szCuisine;
    private int m_iMaxPrice;
    private int m_iMinPrice;
    private int m_iID;

    private static int m_iNextID = 0;
    public static void main(String[] args)
    {
        RestaurantData rd = new RestaurantData("Sample Name", "Sample Description", "Sample Address", 1000, 10, "9:00-9:30", "Italian");

        System.out.println("Name: " + rd.GetName());
        System.out.println("Description: " + rd.GetDescription());
        System.out.println("Address: " + rd.GetAddress());
        System.out.println("Max Price: " + rd.GetMaxPrice());
        System.out.println("Min Price: " + rd.GetMinPrice());
    }

    public RestaurantData(String szName, String szDesc, String szAddress, int iMaxPrice, int iMinPrice, String szPickupTime, String szCuisine)
    {
        m_szName = szName;
        m_szDesc = szDesc;
        m_iMaxPrice = iMaxPrice;
        m_iMinPrice = iMinPrice;
        m_szAddress = szAddress;
        m_szPickupTime = szPickupTime;
        m_szCuisine = szCuisine;
        m_iID = m_iNextID++;
    }

    public RestaurantData(int iID, String szName, String szDesc, String szAddress, int iMaxPrice, int iMinPrice, String szPickupTime, String szCuisine)
    {
        m_szName = szName;
        m_szDesc = szDesc;
        m_iMaxPrice = iMaxPrice;
        m_iMinPrice = iMinPrice;
        m_szAddress = szAddress;
        m_szPickupTime = szPickupTime;
        m_szCuisine = szCuisine;
        m_iID = iID;
    }

    public String GetName()
    {
        return m_szName;
    }
    
    public String GetDescription()
    {
        return m_szDesc;
    }
    
    public String GetAddress()
    {
        return m_szAddress;
    }

    public String GetPickupTime()
    {
        return m_szPickupTime;
    }
    
    public String GetCuisine()
    {
        return m_szCuisine;
    }

    public int GetMaxPrice()
    {
        return m_iMaxPrice;
    }

    public int GetMinPrice()
    {
        return m_iMinPrice;
    }

    public int GetID()
    {
        return m_iID;
    }
}
