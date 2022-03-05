package com.example

import com.jessecorbett.diskord.api.common.UserStatus
import com.jessecorbett.diskord.bot.bot
import com.jessecorbett.diskord.bot.classicCommands
import com.jessecorbett.diskord.bot.events
import com.jessecorbett.diskord.util.authorId
import kotlinx.coroutines.runBlocking


/*
 * This can be replaced with any method to load the bot token.  This specific method is provided only for convenience
 * and as a way to prevent accidental disclosure of bot tokens.
 *
 * Alternative methods might include reading from the environment, using a system property, or reading from the CLI.
 */
private val BOT_TOKEN = try {
    ClassLoader.getSystemResource("bot-token.txt").readText().trim()
} catch (error: Exception) {
    throw RuntimeException("Failed to load bot token. Make sure to create a file named bot-token.txt in" +
            " src/main/resources and paste the bot token into that file.", error)
}
//
//private const val MESSAGE_COUNT = 60
//private const val DELAY_MS = 60_000L
private const val RAQBOT_APP_ID = "936331504089460816"

fun main() {

    runBlocking {
        bot(BOT_TOKEN) {
            events {

                onMessageCreate { message ->

                    if(message.usersMentioned.isNotEmpty()) {
                        val userMention = message.usersMentioned.first()

                        if(userMention.isBot == true && userMention.id == RAQBOT_APP_ID) {

                            if (message.content.contains("kumusta ka", true)
                                || message.content.contains("how are you", true)) {
                                message.reply("yooooo! <@${message.authorId}> eto gwapo parin. hehe")
                            } else if(message.content.contains("sino mas gwapo sating dalawa", true)) {
                                message.reply("tinatanong pa ba yan? syempre wala")
                            } else {
                                message.reply("Yes master <@${message.authorId}> lodi wazzup")
                            }


                        }
                    }
//                    logger.error(">>>> onMessageCreate")
//                    logger.error(">>>> usersMentioned: ${message.usersMentioned.size}}")
//                    logger.error(">>>> ${message.content}")
//                    logger.error(">>> users mentioned count : ${message.usersMentioned.size}")


                    if (message.content.contains("@raqbot")) {
                        message.reply("Yes master <@${message.authorId}> lodi wazzup")
                    }

                }
            }
            classicCommands {

                command("talk-to-winter") { message ->
                    message.reply("talk ka pala winter eh!")
                }
                command("idle-status") { message ->
                    message.reply("setting bot status to idle. lodi!")

                    setStatus("I'm idle", UserStatus.IDLE)
                }
                command("shutdown") { message ->
                    message.reply("Stopping bot.")

                    shutdown()
                }
            }
        }
    }

}
