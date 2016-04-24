import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Admin on 24.04.2016.
 */
public class GeoIpServiceTest {
    @Test
    public void testGeoIpService(){
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("194.44.212.20");
        Assert.assertEquals(geoIP.getCountryCode(), "UKR");
    }
    @Test
    public void testInvalidIp(){
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("www.111.22");
        Assert.assertEquals(geoIP.getReturnCodeDetails(), "Invalid IP address");
    }
}
