package functional;

import java.awt.datatransfer.DataFlavor;
import java.text.ParseException;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.function.Predicate;

import static java.util.stream.Collectors.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaTester {

    public static void main(String[] args) throws IOException {


/** Create a Collection of Persons (ArrayList) for experimenting with streams and lambda expressions
 *  Each method houses a different experiment.
 *  To execute a experiment method, remove call comments 		
 */


        Person roland = new Person(703, "Roland", "DePratti", "06/18/1953", "860-111-1111", "860-111-1111");
        Person steven = new Person(421, "Steven", "DePratti", "12/15/1954", "860-111-1111", "860-111-1111");
        Person mike = new Person(003, "Michael", "DePratti", "04/10/1960", "860-111-1111", "860-111-1111");
        Person david = new Person(723, "David", "DePratti", "06/18/1953", "860-111-1111", "860-111-1111");


        ArrayList<Person> team = new ArrayList<Person>();
        team.add(roland);
        team.add(steven);
        team.add(mike);
        team.add(david);

        /** Each one of these calls executes a method that explores some of the capabilities of streams and
         *  functional programming.
         *  There is a comment about the test followed by the method call.
         *  Comment out method call to execute.
         */
//		      Lab 8 Exercise 1

    /* AUTHOR: Alan Schwartz */
        /* START Lab 8 Exercise 1 */
        ArrayList<Person> contacts = new ArrayList<>();
        File inFile = new File("data\\Persons-Contacts.txt");
        Scanner in = new Scanner(inFile);
        while (in.hasNext())
        {
            String[] line = in.next().split(",");
            contacts.add(new Person(Integer.parseInt(line[0]), line[1], line[2], line[3], line[4], line[5]));
        }
        in.close();
        countDavids(contacts);
        System.out.println();

        /* END Lab 8 Exercise 1 */

//		      Lab 8 Exercise 2

    /* AUTHOR: Alan Schwartz */
        /* START Lab 8 Exercise 2 */

        displayFirstNameofOLastNames(contacts);
        System.out.println();

        /* END Lab 8 Exercise 2 */

//		      Wordcount on Shakespeare's Poems
        processShakespearesPoems("data/poems.txt");

    }

    /**  Count all People with Roland as a first name
     */
	public static void countDavids(ArrayList<Person> team){

		long dCount = 0;

    /* AUTHOR: Alan Schwartz */
        /* START countDavids */

        dCount = team.stream().filter(p -> p.getFirstName().equals("David")).count();

        /* END countDavids */

		System.out.println("David Count : " + dCount);

	}

    /**  Display First Names of O Last Names
     */
	public static void displayFirstNameofOLastNames(ArrayList<Person> team){

    /* AUTHOR: Alan Schwartz */
        /* START displayFirstNameofOLastNames */

        team.stream().filter(p -> p.getLastName().toUpperCase().contains("O"))
                .map(Person::getFirstName)
                .distinct()
                .forEach(System.out::println);

        /* END displayFirstNameofOLastNames */
	}

    /**
     * Word Count in Shakepseares Poems
     */
    public static void processShakespearesPoems(String fname) throws IOException {

        Map<String, Long> wordsCount = null;

        try
        {
            wordsCount = Files.lines(new File(fname).toPath())
                    .flatMap(s -> Stream.of(s.split("\\s+")))
                    .map(s -> s.toUpperCase().replaceAll("\\W+", "").trim())
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        }
        catch (Exception e) {System.err.println("IO Error" + e.getMessage());}

        Iterator iter = wordsCount.entrySet().iterator();
        for (int i = 0; i < 10; i++) {
            if (iter.hasNext()) {
                System.out.println(iter.next());
            }
        }
    }
}
