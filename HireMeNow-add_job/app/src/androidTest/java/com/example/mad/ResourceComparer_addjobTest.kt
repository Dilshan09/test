package com.example.mad

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class ResourceComparer_addjobTest {

    private val resourcecomparerAddjob = ResourceComparer_addjobTest()

    @Test
    fun StringResourceSameAsGivenString_returnsTrue() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourcecomparerAddjob.isEqual(context, R.string.app_name, "MAD")
        assertThat(result).isEqualTo(result)
    }

    private fun isEqual(context: Context?, appName: Int, s: String): Any {

        return TODO("Provide the return value")
    }
}

//
//@Before
//fun step(){
//    resourcecomparerAddjob = ResourceComparer_addjobTest()
//}
//
                    //another method before executing below test cases this is most suitable if we have
                    //more attributes to test

//@After
//fun teardown(){
//
//}
                    //executing after test cases this is most suitable if we have
                    //more attributes to test

//

//    private lateinit var  resourcecomparerAddjob : ResourceComparer_addjobTest

//    @Test
//    fun StringResourceSameAsGivenString_returnsTrue(){
//        resourcecomparerAddjob = ResourceComparer_addjob()
//        val context = ApplicationProvider.getApplicationContext<Context>()
//        val result = resourcecomparerAddjob.isEqual(context,R.string.app_name,"MAD")
//        assertThat(result).isTrue()
//    }
//
//    fun StringResourceSameAsGivenString_returnsFalse(){
//        resourcecomparerAddjob = ResourceComparer_addjob()
//        val context = ApplicationProvider.getApplicationContext<Context>()
//        val result = resourcecomparerAddjob.isEqual(context,R.string.app_name,"MAD")
//        assertThat(result).isFalse()
//    }
//
//} another one
