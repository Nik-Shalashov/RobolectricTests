package com.geekbrains.tests

import androidx.test.core.app.ActivityScenario
import com.geekbrains.tests.presenter.details.DetailsPresenter
import com.geekbrains.tests.view.details.DetailsActivity
import org.junit.After
import org.junit.Before
import org.junit.Test
import android.os.Build
import android.widget.TextView
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase.assertEquals
import org.junit.runner.RunWith
import org.robolectric.annotation.Config


@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class DetailsPresenterTest {

    private lateinit var scenario: ActivityScenario<DetailsActivity>
    private lateinit var presenter: DetailsPresenter

    @Before
    fun setUp() {
        scenario = ActivityScenario.launch(DetailsActivity::class.java)
        presenter = DetailsPresenter()
        scenario.onActivity {
            presenter.onAttach(it)
        }
    }

    @After
    fun onDetach_closeScenario() {
        scenario.onActivity {
            presenter.onDetach(it)
        }
        scenario.close()
    }

    @Test
    fun test_setCounter() {
        presenter.setCounter(10)
        presenter.onIncrement()
        scenario.onActivity {
            val totalCountTextView = it.findViewById<TextView>(R.id.totalCountTextView)
            assertEquals("Number of results: 11", totalCountTextView.text)
        }
    }

    @Test
    fun test_onIncrement() {
        presenter.onIncrement()
        scenario.onActivity {
            val totalCountTextView = it.findViewById<TextView>(R.id.totalCountTextView)
            assertEquals("Number of results: 1", totalCountTextView.text)
        }
    }

    @Test
    fun test_onDecrement() {
        presenter.onDecrement()
        scenario.onActivity {
            val totalCountTextView = it.findViewById<TextView>(R.id.totalCountTextView)
            assertEquals("Number of results: 10", totalCountTextView.text)
        }
    }

}