package com.bombadu.mytesting

import com.bombadu.mytesting.ui.AdderFragment
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class MainActivityTest {


    @Test
    fun calculate_AdditionIsCorrect_EqualsSixteen() {
        val mainActivity = AdderFragment()
        val result = mainActivity.calculate(10, 6)

        assertThat(result, `is`(16))
    }


    @Test
    fun calculate_AdditionIs_Correct_EqualsZero() {
        val mainActivity = AdderFragment()
        val result = mainActivity.calculate(0, 0)
        assertThat(result, `is`(0))
    }
}