# Flashlight App (Kotlin)

This is a simple Android flashlight application developed using **Kotlin**. It provides users with basic torch functionality, including an **ON/OFF switch** and a **blinking feature**. The app interacts with the device's flashlight using the CameraManager API and demonstrates background task management for blinking.

---

## Features

* Toggle flashlight **ON/OFF** with a single tap
* **Start/Stop blinking** flashlight at regular intervals
* Clean and minimal user interface
* Safe permission handling for camera and flashlight use

---

## Technologies Used

* **Kotlin** for app logic
* **Android Studio** as the development environment
* **CameraManager API** for flashlight control
* **Handler & Runnable** for blinking mechanism
* **View Binding** for safer UI element access
* **Gradle** for project build management

---

## How It Works

1. The app requests camera permission on launch.
2. Once granted, users can:

   * Tap the **ON/OFF button** to toggle the flashlight.
   * Use the **Blink button** to start flashing the torch in intervals.
   * Tap **Stop** to end blinking.
3. All operations are safely handled using background threading to avoid blocking the UI.

---

## Setup Instructions

1. Clone the repository:

   ```bash
   git clone https://github.com/Tahadar7/FlashLight-App
   ```
2. Open the project in **Android Studio**
3. Connect a physical Android device (with a flashlight)
4. Run the app and grant camera permissions when prompted

---


## License

This project is open-source and created for educational purposes. Feel free to use or modify it for non-commercial use.

---

## Author

Developed by **Taha Manshoor**

---

## GitHub

🔗 [View the repository](https://github.com/Tahadar7/FlashLight-App)