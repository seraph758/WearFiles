# WearFiles

![Google Play Rating](https://playbadges.pavi2410.me/badge/ratings?id=com.dertefter.wearfiles&pretty) ![Google Play Rating](https://playbadges.pavi2410.me/badge/downloads?id=com.dertefter.wearfiles)

[![Get it on Google Play](screenshots/gb_badge.png)](https://play.google.com/store/apps/details?id=com.dertefter.wearfiles)

![WearFiles Banner](screenshots/banner_new.jpg)

Simple file manager for Wear OS smartwatches.

## Features
- View and open files
- Delete files
- Cut/Copy/Paste files

## Important
- Due to Wear OS platform limitations, the application cannot automatically grant the MANAGE_EXTERNAL_STORAGE permission. You need to do this manually. This permission is necessary to access the device's file system.
- You will be able to use the application without this permission in a limited mode. You will have access to viewing a list of photos, videos, and audio files.

### How to manually grant access to files:  
1. Connect your watch to a computer via ADB.
2. Run the following command:

   ```sh
   adb shell appops set --uid com.dertefter.wearfiles MANAGE_EXTERNAL_STORAGE allow
   ```

3. Restart the app.

## 📸 Screenshots

![Screenshot 1](screenshots/1.png) ![Screenshot 2](screenshots/2.png) ![Screenshot 3](screenshots/3.png)

## 💎 Support
If you find this project useful, you can support its development via TON:

`UQBvmXutAO5dEIwf46dP-TMaA_DqsGkLFkxrDxThIfdTLSE3`

