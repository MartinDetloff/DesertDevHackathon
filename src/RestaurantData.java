package src;

public class RestaurantData 
{
    private String m_szName;
    private String m_szDesc;
    private int m_iMaxPrice;
    private int m_iMinPrice;

    public static void main(String[] args)
    {
        RestaurantData rd = new RestaurantData("Sample Name", "Sample Description", 1000, 10);

        System.out.println("Name: " + rd.GetName());
        System.out.println("Description: " + rd.GetDescription());
        System.out.println("Max Price: " + rd.GetMaxPrice());
        System.out.println("Min Price: " + rd.GetMinPrice());
    }

    public RestaurantData(String szName, String szDesc, int iMaxPrice, int iMinPrice)
    {
        m_szName = szName;
        m_szDesc = szDesc;
        m_iMaxPrice = iMaxPrice;
        m_iMinPrice = iMinPrice;
    }

    public String GetName()
    {
        return m_szName;
    }
    
    public String GetDescription()
    {
        return m_szDesc;
    }
    
    public int GetMaxPrice()
    {
        return m_iMaxPrice;
    }

    public int GetMinPrice()
    {
        return m_iMinPrice;
    }
}
