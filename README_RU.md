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

Простой файловый менеджер для часов на Wear OS.

## Функции
- Просмотр и открытие файлов
- Удаление файлов
- Вырезать/Копировать/Вставить файлы

## Важно
- В связи с ограничениями платформы Wear OS приложение не может выдать разрешение MANAGE_EXTERNAL_STORAGE автоматически. Вам необходимо сделать это вручную. Это разрешение необходимо для доступа к файловой системе устройства.
- Вы сможете пользоваться приложением без этого разрешения в ограниченном режиме. Вам будет доступен просмотр списка фото, видео и аудиофайлов.

### Как выдать разрешение вручную:
1. Подключите часы к компьютеру через ADB
2. Выполните команду:
   ```sh
   adb shell appops set --uid com.dertefter.wearfiles MANAGE_EXTERNAL_STORAGE allow
   ```
3. Перезапустите приложение

## Скриншоты

<p align="center">
  <img src="art/screenshot_1.png" width="160" alt="Screenshot 1">
  <img src="art/screenshot_2.png" width="160" alt="Screenshot 2">
  <img src="art/screenshot_3.png" width="160" alt="Screenshot 3">
  <img src="art/screenshot_4.png" width="160" alt="Screenshot 4">
  <img src="art/screenshot_5.png" width="160" alt="Screenshot 5">
</p>

## 💎 Поддержка
Если вам полезен этот проект, вы можете поддержать его разработку через TON:

`UQBvmXutAO5dEIwf46dP-TMaA_DqsGkLFkxrDxThIfdTLSE3`

