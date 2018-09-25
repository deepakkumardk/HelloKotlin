package com.deepak.hellokotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.Appcompat
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toast_btn.onClick { toast("hello toast!!") }
        snackbar_btn.onClick { snackbar(rootLayout,"A Snackbar from Anko") }

        alert_btn.onClick {
            _ ->       //just to remove the warning of it implicit parameter
            alert("Hello, I am an alert from Kotlin Anko Library") {
                yesButton { toast("Thanks for clicking me") }
                noButton { toast("Check another features of app") }
            }.show()
        }
        app_compat_alert_btn.onClick { _ ->
            alert(Appcompat,"Hello, I am an AppCompatAlert from Kotlin Anko") {
                yesButton { toast("Thanks for tapping me") }
                noButton { toast("Check another features of app") }
            }.show()
        }
        custom_alert_btn.onClick { _ ->
            alert("Enter Your message") {
                lateinit var message: EditText
                customView { message = editText { hint = "Message"} }
                yesButton { toast("Your Message is : ${message.text}") }
            }.show()
        }
        list_alert_btn.onClick {
            val hero = listOf("Iron Man","Thor","Dr. Strange","Hulk")
            selector("Select your favourite superhero.",hero) { _, i ->
                longToast("${hero[i]} That's amazing, I like it too.")
            }
        }

        progress_dialog_btn.onClick {
            val files = (1..9).random()
            indeterminateProgressDialog(title = "Downloading",message = "$files files are downloading...")
        }
        activity_without_xml.onClick { startActivity<XmlLessActivity>() }

    }

    private fun ClosedRange<Int>.random() = Random().nextInt(endInclusive + 1 - start) + start
}
