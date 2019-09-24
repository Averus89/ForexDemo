package pl.dexbytes.forexdemo.util;

import java.util.Random;

import pl.dexbytes.forexdemo.db.quote.mock.QuoteEntityMock;

public class StringUtils {
    public static boolean isEmpty(String string){
        return string == null || string.length() == 0;
    }

    public static boolean isNotEmpty(String string){
        return !isEmpty(string);
    }

    public static String getRandomString(final int sizeOfRandomString, String allowedCharacters) {
        final Random random = new Random();
        final StringBuilder sb = new StringBuilder(sizeOfRandomString);
        for(int i = 0 ; i < sizeOfRandomString ; ++i)
            sb.append(allowedCharacters.charAt(random.nextInt(allowedCharacters.length())));
        return sb.toString();
    }
}
