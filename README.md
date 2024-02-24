# Android Kotlin MainActivity Test App
This application was created for:
* Testing and illustrating the Application Lifecycles.
* Testing and illustrating the MainActivity Lifecycles.
* Invoking the Singular SDK from the MainActivity Class to track Installs.
* Testing the handling of a Singular Link with deeplink and deferred deep linking enabled.
* Triggering in-app events using Singular functions.

## Installation and Setup

1. **Clone the Repository**:

2. **Open the Project in Android Studio**:
- Launch Android Studio.
- Choose "Open an existing Android Studio project."
- Navigate to the cloned repository and select the project folder.

3. **Install Dependencies**:
- Ensure you have **Kotlin** and **Android SDK** installed.

## Configuration

- Set up any necessary environment variables or API keys.
  - This project is using SDK key and Secret from the Singular Platform, referenced from the `local.properties` file.
- Add the dependencies from the Singular Android SDK Installation Guide: [HERE](https://support.singular.net/hc/en-us/articles/360037581952-Android-SDK-Integration-Guide)
- Make sure you update the `AndroidManifest.xml` file with the required permissions:
```xml
  <uses-permission android:name="android.permission.INTERNET" />
  <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
  <uses-permission android:name="BIND_GET_INSTALL_REFERRER_SERVICE" />
  <uses-permission android:name="com.android.vending.CHECK_LICENSE" />
  <uses-permission android:name="com.google.android.gms.permission.AD_ID" />
```
- Add the Application Class name `android:name=".MyApplication"` to the `AndroidManifest.xml` file.
- Make sure you update the `AndroidManifest.xml` file with the required `intent-filter` to support Singular Links:
```xml
<intent-filter>
    <data android:scheme="YOUR_APP_SCHEME_HERE" />
    <action android:name="android.intent.action.VIEW" />
    <category android:name="android.intent.category.DEFAULT" />
    <category android:name="android.intent.category.BROWSABLE" />
</intent-filter>
```

## Building and Running the App

1. **Build the Project**:
- Click the "Build" button in Android Studio or run:
  ```
  ./gradlew build
  ```

2. **Run the App**:
- Click the "Run" button (green triangle) in Android Studio.
- Choose an emulator or a connected device to run the app.

## Usage

To examine the behavior of Singular Links, the deferred deeplink after a new install, or the deeplink if the app is already installed, follow the steps below.
- You will need a Singular Custom Link, configured for this app to perform the testing.
  - Make sure the App is configured with the scheme on the Singular Apps Page [Screenshot](https://github.com/jared-singular/android-kotlin-mainactivity/blob/main/screenshots/androidkotlinmainactivity_singular_apps_page_config.png)
- Send the Singular Link to an email account you can access from the Emulator.
1. Open the Android Emulator and make sure the App is Uninstalled.
2. Navigate to your email app and click the Singular Link.
3. Now, build the App from Android Studio to the Emulator. (This is simulating a User installing from the App Store)
4. When the App Opens, navigate to the `Logcat` pane in Android Studio and review the Logs (Filter for the tag:Singular). From the output, you will see the Deferred Deeplink (DDL) logged from the Singular SDK Link Handler.
5. Now, background the app.
6. Navigate back to your email app and click the link again. 
7. The App should open directly and the logcat output will display the deeplink provided from Singular.
   ![alt text](https://github.com/jared-singular/android-kotlin-mainactivity/blob/main/screenshots/androidkotlinmainactivity_logcat_deeplink_background.png)

8. Now, close the app.
9. Navigate back to your email app and click the link again.
10. The App should open directly and the logcat output will display the deeplink provided from Singular.
    ![alt text](https://github.com/jared-singular/android-kotlin-mainactivity/blob/main/screenshots/androidkotlinmainactivity_logcat_deeplink_closed.png)

This testing illustrates the desired behavior of Singular Links.

## Contributing

Contributions are welcome! Please follow our contribution guidelines.

## License

This project is licensed under the MIT License - see the LICENSE file for details.
