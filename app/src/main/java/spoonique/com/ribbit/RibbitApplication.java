package spoonique.com.ribbit;

import android.app.Application;

import com.parse.Parse;

/**
 * Class to initialise the Parse Framework.
 * Created by maloeffler on 28.10.2014.
 */
public class RibbitApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "TZk5ZdeWSe9wX2XICuQE4UkbI2sVuKKBOgiXFufn", "a2tCVRejw5Y1WW6BOtqsda8sShzK6e7m7kGbDPf6");
    }
}
