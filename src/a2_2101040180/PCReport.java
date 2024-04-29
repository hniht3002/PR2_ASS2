package a2_2101040180;

public class PCReport {
    /**
     * @overview Displays a  tabular report on some {@link PC} objects.
     *
     */
    public String displayReport(PC[] objs) {
        if(objs != null) {
            String output = "";
            String line = "---------------------------------------------------------------------------------------------------\n";

            output += line;
            output += "                                           PC Report\n";
            output += line;

            for(int i = 0; i < objs.length; i++) {
                output += "  " + (i+1) + " " + objs[i].toString() + "\n";
            }

            return output;
        } else return null;
    }
}