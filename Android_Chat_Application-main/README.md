

## Activity Files

- ` The mainChatActivity.java`: activity for individual chat conversations.
- `LoginOtpActivity.java`: Handles user authentication using OTP.
- `LoginPhoneNumberActivity.java`: Manages phone number-based user login.
- `LoginUsernameActivity.java`: Controls user login using a username.
- `MainActivity.java`: The app's entry point and primary navigation hub.
- `SearchUserActivity.java`: Allows users to search for other users to initiate chats.
- `SplashActivity.java`: Displays a splash screen while the app initializes.

## Fragment Files

- `ChatFragment.java`: Manages chat UI and logic within the chat activity.
- `ProfileFragment.java`: Handles user profile display and editing.
- `SearchUserFragment.java`: Displays user search results and options for starting a chat.

## Service File

- `FCMNotificationService.java`: Integrates Firebase Cloud Messaging for push notifications.

## Getting Started

To use this app:

1. Clone or download the repository.
2. Set up your Firebase project and update the `google-services.json` file.
3. Build and run the app on your Android device or emulator.

## A Little Bit Explanation
1. LoginOtp mean, if u want to login to the app, you need to insert ur mobile phone number first.
2. After that you need to enter the otp code, otp code here mean the verification code on firebase authentication.
3. I choose phone sign in method, previously i'd like to use google authentication but when i use it, the app will force close.
4. So at the phone sign in method, u insert the phone number first, and then insert the verification code (otp code).
5. After that you can use the app without any error and force close.
6. But, if u try to delete one of the java file, i think it would be terrible, cause all the files already interconnect








