/*
    Available for use publically
    Check out my blog: rdquest.com
 */
package com.rdquest.ignite.compute.count;

import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;
import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.cluster.ClusterGroup;

/**
 *
 * @author Corey
 */
public class IgniteComputeWordCount {

    public static void main(String[] args) {
        try (Ignite ignite = Ignition.start()) {
            
            Scanner reader = new Scanner(System.in);
            System.out.println("Please Enter a Sentence To Be Counted: ");
            String toCount = reader.next();

            Collection<Integer> result = ignite.compute().apply(
                    (String word) -> {
                        System.out.println("Counting characters in word " + word + " ");
                        return word.length();
                    },
                    Arrays.asList(toCount.split(" "))
            );

            int total = result.stream().mapToInt(Integer::intValue).sum();

            System.out.println("Total Characters " + total);
        }
    }

}
