package a2_2101040180;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

import java.util.Scanner;
import java.util.Vector;
import utils.DomainConstraint;
import utils.NotPossibleException;
import utils.TextIO;

import javax.lang.model.util.ElementScanner6;

import static utils.TextIO.*;

/**
 * A program that captures data about a2_2101040180.PC objects and displays
 * a report about them on the console.
 */
public class PCProg {
    private static final Object YES = "Y";
    private Set<PC> objs;

    /**
     * Initialise this to have an empty set of PCs
     */
    public PCProg() {
        objs = new Set<>();
    }

    /**
     * @effects create a new PC object and record it into objs
     */

    public void createObjects() {
        while (true) {
            Set<String> comps = new Set<String>();

            System.out.print("Enter model: ");
            String model = getlnString();

            System.out.print("Enter year: ");
            int year = getInt();
            getln();

            System.out.print("Enter manufacturer: ");
            String manufacturer = getlnString();

            while (true) {
                System.out.print("Enter components: ");
                String comp = getlnString();

                if (comp.length() > 0) {
                    if (!comps.isIn(comp)) {
                        comps.insert(comp);
                    }
                } else {
                    break;
                }
            }
            objs.insert(PCFactory.getInstance().createPC(model, year, manufacturer, comps));

            System.out.print("Do you want to enter more PC? [Y/N] ");
            char ans =  getChar();
            getln();

            if(ans == 'N' || ans == 'n') {
                break;
            } else if(ans == 'Y' || ans == 'y') {

            } else {
                throw new NotPossibleException("PCProg.init: Invalid option: " + ans);
            }

        }
    }


    /**
     * If <tt>objs</tt> is not empty, displays a text-based tabular
     * report on <tt>objs</tt> to the standard console.
     * Displays nothing if <tt>objs</tt> is empty.
     *
     * @return this report if <tt>objs</tt> is not empty or <tt>null</tt> otherwise.
     */
    public String displayReport() {
        if (!objs.isEmpty()) {
            Vector<PC> pcs = objs.getElements();
            PCReport reportObj = new PCReport();
            return reportObj.displayReport(pcs.toArray(new PC[0]));
        } else {
            return null;
        }
    }

    /**
     * Saves report to a file <tt>pcs.txt</tt> in the program's working directory.
     */
    public void saveReport(String report) {
        String fileName = "pcs.txt";
        try (PrintWriter pw = new PrintWriter(fileName)) {
            pw.println(report);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Initializes an instance of <tt>a2_2101040180.PCProg</tt>.
     * Create objects from data entered by the user.
     * Display a report on the objects.
     * Prompt user to save report to file. If user answers "Y", save report.
     * Otherwise, end program.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PCProg prog = new PCProg();
        try {
            // create objects
            prog.createObjects();
            // display report
            String report = prog.displayReport();
            System.out.println(report);
            if (report != null) {
                // prompt user to save report
                System.out.println("Save report to file? [Y/N]");
                String toSave = sc.nextLine();
                if (toSave.equals("Y")) {
                    prog.saveReport(report);
                    System.out.println("report saved");
                }
            }
        } catch (NotPossibleException e) {
            System.err.printf("%s: %s%n", e, e.getMessage());
        }
        System.out.println("~END~");
    }
}
