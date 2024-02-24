package com.singular.androidkotlinmainactivity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.singular.androidkotlinmainactivity.ui.theme.AndroidKotlinMainActivityTheme
import com.singular.sdk.Attributes
import com.singular.sdk.Events
import com.singular.sdk.Singular
import com.singular.sdk.SingularConfig
import com.singular.sdk.SingularLinkParams
import org.json.JSONObject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("Singular", "--MainActivity-- Lifecycle onCreate")

        // Access application context
        val context = applicationContext
        Log.d("Singular", "--MainActivity-- Application context: $context")

        // Initialize Singular SDK from MainActivity onCreate
        initSingularSDK()

        // Call function to send a group of Singular Events for Testing.
        sendSingularEvents()

        setContent {
            AndroidKotlinMainActivityTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("Singular", "--MainActivity-- Lifecycle onResume")
    }

    override fun onStart() {
        super.onStart()
        Log.d("Singular", "--MainActivity-- Lifecycle onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Singular", "--MainActivity-- Lifecycle onStop")
    }

    private fun initSingularSDK() {
        val singularSdkKey = BuildConfig.SINGULAR_SDK_KEY
        val singularSdkSecret = BuildConfig.SINGULAR_SDK_SECRET

        // Singular Initialization Object
        val config = SingularConfig(singularSdkKey, singularSdkSecret)
            .withDDLTimeoutInSec(30)

        // Call SingularLinkHandler
        config.withSingularLink(intent) { singularLinkParams ->
            handleDeeplink(singularLinkParams)
        }

        Singular.init(this, config)

    }

    private fun sendSingularEvents(){
        /* Singular Standard Events: Full List and Recommended Events by Vertical
        See: https://support.singular.net/hc/en-us/articles/7648172966299-Singular-Standard-Events-Full-List-and-Recommended-Events-by-Vertical */

        /* EXAMPLE: Send Singular Standard Event "TutorialComplete" when User finishes viewing the tutorial */
        Singular.event(Events.sngTutorialComplete.toString())

        /* EXAMPLE: Alternatively, you may include attributes using a JSON Object. Send Singular Standard Event "TutorialComplete" when User finishes viewing the tutorial with attributes */
        val att = JSONObject().apply {
            put(Attributes.sngAttrContent.toString(), "GenericTutorialFlow")
            put(Attributes.sngAttrContentId.toString(), 32)
            put(Attributes.sngAttrContentType.toString(), "WelcomeVideo")
            put(Attributes.sngAttrSuccess.toString(), 75)
        }
        Singular.eventJSON(Events.sngTutorialComplete.toString(), att)

        // EXAMPLE: Send a Simple Revenue (IAP) Event
        Singular.revenue("USD", 0.99)

        // EXAMPLE: Send Custom Revenue Event with custom name
        Singular.customRevenue("Subscription_1yr", "USD", 49.99)
    }

    private fun handleDeeplink(singularLinkParams: SingularLinkParams){
        /* The handleDeeplink function parses the specific values from the Intent passed to the Singular SDK. */
        Log.d("Singular", "Singular handleDeeplink()")
        val deeplink = singularLinkParams.deeplink
        val passthrough = singularLinkParams.passthrough
        val isDeferred = singularLinkParams.isDeferred
        val urlParams = singularLinkParams.urlParameters

        // Add deep link handling code here
        Log.d("Singular", "Deeplink: $deeplink")
        Log.d("Singular", "Passthrough: $passthrough")
        Log.d("Singular", "isDeferred: $isDeferred")
        Log.d("Singular", "urlParams: $urlParams")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidKotlinMainActivityTheme {
        Greeting("Android")
    }
}