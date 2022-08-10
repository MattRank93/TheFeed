package com.interactions.thefeed.middleware;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class RanksSanitizers {


    /**
     * This method will strip away characters and character strings that are known to be used
     * for Nosql injection against MongoDb. The method is written so that it will not remove characters from
     * standard user input. The method may not completely eliminate bad user input but will sufficiently disrupt the
     * command rendering it ineffective.
     * @param unsanitized the original input string
     * @return returns the string after bad characters/phrases removed.
     */
    public String MongoInput(String unsanitized){
        try {
            Pattern pattern = Pattern.compile(
                    "(gte)|(gt)|(eq)|(lt)|(lte)|[$]|" +
                            "(sleep)|[()]|[;]|[:]|[ ]|(ne)|" +
                            "(where:)|('1 == 1')|[{}]|(password)"
                    , Pattern.CASE_INSENSITIVE);
            return pattern.matcher(unsanitized).replaceAll("");
        }catch(Exception e){
            return e.toString();
        }
    }



    





    /**
     * Uses the same logic as the previous method but returns a boolean value if a simple check is needed
     * @param unsanitized the original input string
     * @return true if good and false if bad input
     */
    public boolean MongoInputBool(String unsanitized){

        try{
        Pattern pattern = Pattern.compile(
                "(gte)|(gt)|(eq)|(lt)|(lte)|[$]|" +
                        "(sleep)|[()]|[;]|[:]|[ ]|(ne)|" +
                        "(where:)|('1 == 1')|[{}]|(password)"
                , Pattern.CASE_INSENSITIVE);
        String sanitized = pattern.matcher(unsanitized).replaceAll("");
        return (!unsanitized.equals(sanitized));
        }catch(Exception e){
            System.out.println(e);
            return true;
        }
    }

}
