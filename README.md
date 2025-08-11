# WearFiles

![Google Play Rating](https://playbadges.pavi2410.me/badge/ratings?id=com.dertefter.wearfiles&pretty)

![WearFiles Banner](screenshots/banner.jpg)

**WearFiles** is a simple file manager for Wear OS smartwatches.

## 📌 Features
- 📂 View and open files
- 🗑 Delete files
- ✂️ Cut/📋 Copy/📌 Paste files

## ⚠️ Important
Due to Wear OS platform restrictions, the app cannot grant file access permissions on its own. You need to do this manually:

1. Connect your watch to a computer via ADB.
2. Run the following command:

   ```sh
   adb shell appops set --uid com.dertefter.wearfiles MANAGE_EXTERNAL_STORAGE allow
   ```

3. Restart the app.

## 📸 Screenshots

![Screenshot 1](screenshots/1.png) ![Screenshot 2](screenshots/2.png) ![Screenshot 3](screenshots/3.png)

