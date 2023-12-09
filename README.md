## THE FLOW OF GLOBAL CHAT APP

    Sign In Using Google Account:
    - Users sign in using their Google accounts.

    Chat With Other Users:
    - After signing in, users can engage in group chat with other logged-in users.

## DESIGNING THE UI

    Drawable: Utilize drawable resources to design specific UI components.
    Layout: Design UI layouts using XML in the layout directory.

## ACTIVITY

    LoginActivity: Handles the sign-in process using a Google account.
    MainActivity: Represents the main chat interface for group conversations.

    ChatAdapter: Manages the RecyclerView for displaying chat messages.
    ChatModel: Represents the data structure for chat messages.

    FirebaseCords: Contains constants related to Firebase, such as database paths.
    MyFirebaseMessagingService: Handles incoming Firebase Cloud Messaging (FCM) messages.

    Notification doesn't seem to be working :<

    NotificationUtils: Provides utility functions for handling notifications.
    NotificationVO: Represents the data structure for notification content.
    SendPushNotification: Sends push notifications using Firebase Cloud Messaging.

    
