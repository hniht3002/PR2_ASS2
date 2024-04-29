package a2_2101040180;
import utils.*;
public class PC {
    @DomainConstraint(type = "String", mutable = true, optional = false, length = 20)
    public String model;
    @DomainConstraint(type = "Integer", mutable = false, optional = false, min = 1984)
    private int year;
    @DomainConstraint(type = "String", mutable = false, optional = false, length = 15)
    private String manufacturer;
    @DomainConstraint(type = "Set<String>", mutable = true)
    private Set<String> comps;

    public PC(@AttrRef("model") String model, @AttrRef("year") int year, @AttrRef("manufacturer") String manufacturer,
              @AttrRef("comps") Set<String> comps) {

        if(validateModel(model)) {
            this.model = model;
        } else {
            throw new NotPossibleException("Invalid model: " + model);
        }

        if(validateYear(year)) {
            this.year = year;
        } else {
            throw new NotPossibleException("Invalid year: " + year);
        }

        if(validateManufacturer(manufacturer)) {
            this.manufacturer = manufacturer;
        } else {
            throw new NotPossibleException("nvalid manufacturer: " + manufacturer);
        }

        if(validateComps(comps)) {
            this.comps = comps;
        } else {
            throw new NotPossibleException("Invalid comps" + comps.getElements());
        }
    }

    /**
     * @requires model has String data type
     * @effects
     * <pre>
     *     if 0 < model.length <= 20 and model != null
     *      return true
     *     else
     *      return false
     * </pre>
     */
    public boolean validateModel(String model) {
        return model != null && model.length() >0 && model.length() <= 20;
    }

    /**
     * @requires year has int data type
     * @effects
     * <pre>
     *     if year >= 1984
     *      return true
     *     else
     *      return false
     * </pre>
     */
    public boolean validateYear(int year) {
        return year > 1984;
    }

    /**
     * @requires manufacturer has String data type
     * @effects
     * <pre>
     *     if 0 < manufacturer.length <= 15 and model != null
     *      return true
     *     else
     *      return false
     * </pre>
     */
    public boolean validateManufacturer(String manufacturer) {
        return manufacturer != null && manufacturer.length() >0 && manufacturer.length() <= 15;
    }

    /**
     * @requires comps is a Set of String
     * @effects
     * <pre>
     *     false if comp is null or comps has invalid element
     *
     *     else
     *      get array which stores all elements in comps
     *      if every element in that array has String data type
     *          if that element not null and has length > 0
     *              return true
     *          else return false
     *      else return false
     *      return true
     * </pre>
     */
    public boolean validateComps ( Set<String> comps) {
        if(comps == null || comps.size() == 0 || !comps.repOK()) {
            return false;
        } else {
            Object[] compsArray = comps.getElements().toArray();
            for(Object comp : compsArray) {
                if(comp instanceof String) {
                    if(comp != null && ((String) comp).length() > 0) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
            return true;
        }
    }

    @Override
    @DOpt(type = OptType.Default)
    public String toString() {
        String manuCol = "%15s";
        String yCol = "%6d";
        String mCol = "%20s";

        String output = String.format(mCol + " " + yCol +  " " + manuCol, this.model, this.year, this.manufacturer) + " " + comps.getElements();
        if (output.length() > 96) {
            return output.substring(0, 91) + "...]";
        } else {
            return output;
        }
    }


    @Override
    @DOpt(type = OptType.Default)
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        } else if (!(obj instanceof PC)) {
            return false;
        }

        PC pc = (PC) obj;


        if(this.manufacturer == null) {
            if(pc.manufacturer != null) {
                return false;
            }
        } else if (!this.manufacturer.equals(pc.manufacturer)) {
            return false;
        }

        if(this.model == null) {
            if(pc.model != null) {
                return false;
            }
        } else if(!this.model.equals(pc.model)) {
            return false;
        }

        if(this.comps == null) {
            if(pc.comps != null) {
                return false;
            }
        } else if (!this.comps.equals(pc.comps)) {
            return false;
        }


        return this.year == pc.year;
    }
}
