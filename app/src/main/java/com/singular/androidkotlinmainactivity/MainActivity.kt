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
import com.singular.sdk.Singular
import com.singular.sdk.SingularConfig
import com.singular.sdk.SingularLinkParams

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("Singular", "--MainActivity-- Lifecycle onCreate")

        // Access application context
        val context = applicationContext
        Log.d("Singular", "--MainActivity-- Application context: $context")

        // Initialize Singular SDK from MainActivity onCreate
        initSingularSDK()

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

    private fun initSingularSDK(){
        val singularSdkKey = BuildConfig.SINGULAR_SDK_KEY
        val singularSdkSecret = BuildConfig.SINGULAR_SDK_SECRET

        // Singular Initialization Object
        val config = SingularConfig(singularSdkKey, singularSdkSecret)
            .withCustomUserId("jared123")
            .withDDLTimeoutInSec(30)
            .withLoggingEnabled().withLogLevel(3)

        // Call AttributionInfoHandler
        config.withSingularDeviceAttribution { attributionInfo ->
            attributionInfoHandler(attributionInfo)
        }

        // Call SingularLinkHandler
        config.withSingularLink(intent) { singularLinkParams ->
            handleDeeplink(singularLinkParams)
        }

        Singular.init(this, config)

        // Send Simple Custom Event
        Singular.event("Singular SDK Initialized")

//        // Send Standard Event with Args
//        val att = JSONObject().apply {
//            put(Attributes.sngAttrContent.toString(), "GenericTutorialFlow")
//            put(Attributes.sngAttrContentId.toString(), 32)
//            put(Attributes.sngAttrContentType.toString(), "WelcomeVideo")
//            put(Attributes.sngAttrSuccess.toString(), 75)
//        }
//        Singular.eventJSON(Events.sngTutorialComplete.toString(), att)
//
//        // Send Simple Revenue (IAP) Event
//        Singular.revenue("USD", 0.99)
//
//        // Send Custom Revenue
//        Singular.customRevenue("Subscription_1yr", "USD", 49.99)
//
//        Singular.event(Events.sngTutorialComplete.toString())

    }

    private fun attributionInfoHandler(attributionInfo: Map<String?, Any?>?) {
        if (attributionInfo == null) {
            Log.d("Singular", " -- Singular attributionInfo is nil")
            return
        } else{
            Log.d("Singular", " -- Singular Attribution Info: $attributionInfo")
            // Add Attribution handling code here
        }
    }

    private fun handleDeeplink(singularLinkParams: SingularLinkParams){
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