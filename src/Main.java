import com.itech.api.utils.Config;
import org.apache.log4j.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class);
    public static void main(String[] args) {
        Config.configureLOG();
        LOGGER.info("Thanks for using ItechAPI tool.\n");
    }
}