package ru.netology.pusher

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import java.io.FileInputStream

fun main() {
    val options =
        FirebaseOptions.builder().setCredentials(GoogleCredentials.fromStream(FileInputStream("fcm.json"))).build()

    FirebaseApp.initializeApp(options)

    val message = Message.builder()
        .putData("action", "LIKE")
        .putData( "content", """{
          "userId": 1,
          "userName": "Evgeny",
          "postId": 2,
          "postAuthor": "Netology"
        }""".trimIndent()
    ).setToken(token).build()

    val newPost = Message.builder()
        .putData("action", "NEWPOST")
        .putData("content", """{
          "userId": 1,
          "userName": "Evgeny",
          "postText": "Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов."
        }""".trimIndent()
    ).setToken(token).build()

    FirebaseMessaging.getInstance().send(message)
}