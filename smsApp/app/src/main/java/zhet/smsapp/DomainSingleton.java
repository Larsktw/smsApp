package zhet.smsapp;

import android.content.Context;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mikael on 16.09.2016.
 */

public class DomainSingleton {
    public static DomainSingleton SINGLETON;

    private List<String> data = Arrays.asList(
        "Alphabet",
        "Betasomething",
        "Other");

    private DomainSingleton() {}


    public static synchronized DomainSingleton getSingleton(Context context) {
        if(SINGLETON == null) {
            SINGLETON = new DomainSingleton();
        }

        return SINGLETON;
    }

    public synchronized List<String> getData() {
        return data;
    }
}
