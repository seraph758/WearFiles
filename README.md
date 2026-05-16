<p align="center">
  <img src="art/banner.png" alt="NETI Next Banner">
</p>

# WearFiles

<a href="https://play.google.com/store/apps/details?id=com.dertefter.wearfiles">
  <img src="https://github.com/dertefter/some_stuff_for_me/blob/master/com.dertefter.wearfiles_downloads.svg" alt="Google Play" height="60">
</a>
<a href="https://play.google.com/store/apps/details?id=com.dertefter.wearfiles">
  <img src="https://github.com/dertefter/some_stuff_for_me/blob/master/com.dertefter.wearfiles_rating.svg" alt="Google Play" height="60">
</a>
<a href="README_RU.md">
  <img src="https://img.shields.io/badge/Russian-blue?style=for-the-badge" alt="Русский язык" height="60">
</a>

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

<p align="center">
  <img src="art/screenshot_1.png" width="160" alt="Screenshot 1">
  <img src="art/screenshot_2.png" width="160" alt="Screenshot 2">
  <img src="art/screenshot_3.png" width="160" alt="Screenshot 3">
  <img src="art/screenshot_4.png" width="160" alt="Screenshot 4">
  <img src="art/screenshot_5.png" width="160" alt="Screenshot 5">
</p>

## 💎 Support
If you find this project useful, you can support its development via TON:

`UQBvmXutAO5dEIwf46dP-TMaA_DqsGkLFkxrDxThIfdTLSE3`

