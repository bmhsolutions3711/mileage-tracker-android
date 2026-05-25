# Mileage Tracker — Native Android

Capacitor wrapper around the BMH Mileage Tracker PWA. Adds a true Android **foreground service** so GPS tracking continues indefinitely — across screen-off, app-switch, and 24+ hour idle periods — without the browser background-throttling that kills the PWA.

## Architecture

- `www/` — the same HTML/JS that runs as a PWA at https://bmhsolutions3711.github.io/mileage-tracker/. Detects `window.Capacitor.isNativePlatform()` at runtime and routes GPS calls through the native plugin when present; falls back to `navigator.geolocation` in the browser.
- `android/` — Capacitor's scaffolded Android Studio project. Built by GitHub Actions.
- `.github/workflows/build-apk.yml` — cloud APK build. Triggers on push to main and on `v*` tags.

## Plugins

- `@capacitor-community/background-geolocation` — runs a foreground service with a persistent notification, gets GPS updates even when screen is off
- `@capacitor/local-notifications` — for future reminder hooks
- `@capacitor/preferences` — native key/value store

## Build (cloud)

```bash
git tag v0.1.0
git push --tags
```

GitHub Actions builds the APK, attaches it to the release.

## Install on phone

1. Download the `.apk` from the latest release on the **Releases** page
2. Allow "Install from unknown sources" for whichever app you used to download it (Chrome, Drive, etc.)
3. Open the APK to install
4. On first launch: grant **Location → Allow all the time**, grant **Notifications**, paste your `MILEAGE_TOKEN`
5. Tap **Start Tracking** — a persistent notification appears. Tracking continues until you tap **Stop**.

## Permissions

- `ACCESS_FINE_LOCATION` + `ACCESS_BACKGROUND_LOCATION` — required for indefinite tracking
- `FOREGROUND_SERVICE` + `FOREGROUND_SERVICE_LOCATION` — Android 9+/14+ to start the GPS service
- `POST_NOTIFICATIONS` — Android 13+ to show the persistent notification
- `RECEIVE_BOOT_COMPLETED` + `WAKE_LOCK` — survive reboots and CPU idle

## Development

```bash
npm install
npx cap sync android
# Build locally requires Android SDK + JDK 21
# Prefer pushing a tag and letting GitHub Actions build
```
