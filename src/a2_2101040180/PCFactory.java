package a2_2101040180;
import utils.*;
/**
 * @overview
 * PCFactory class is used to create Pc
 */
public class PCFactory {
    /**
     * @effects
     *  initialise INSTANCE
     */
    private static final PCFactory PC_INSTANCE = new PCFactory();


    private PCFactory(){};

    /**
     * @effects
     *  return INSTANCE
     */
    public static PCFactory getInstance() {
        return PC_INSTANCE;
    }

    /**
     * @effects
     *  create a new PC and return it
     */
    public PC createPC(@AttrRef("model") String model, @AttrRef("year") int year, @AttrRef("manufacturer") String manufacturer,
                       @AttrRef("comps") Set<String> comps) {
        return new PC(model, year, manufacturer, comps);
    }
}
