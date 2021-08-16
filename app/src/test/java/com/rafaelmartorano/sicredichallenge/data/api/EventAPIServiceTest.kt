package com.rafaelmartorano.sicredichallenge.data.api

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EventAPIServiceTest {

    private lateinit var service: EventAPIService
    private lateinit var server: MockWebServer

    @Before
    fun setUp(){
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EventAPIService::class.java)
    }

    private fun enqueueMockResponse(
        fileName: String
    ){
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)
    }

    @Test
    fun getEventsList_sentRequest_receivedExpected(){
        runBlocking {
            enqueueMockResponse("eventsresponse.json")
            val responseBody = service.getEvents().body()
            val request = server.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/api/events")
        }
    }

    @Test
    fun getEventsLis_receivedResponse_correctContent(){
        runBlocking {
            enqueueMockResponse("eventsresponse.json")
            val responseBody = service.getEvents().body()
            val eventList = responseBody!!
            val event = eventList[0]
            assertThat(event.title).isEqualTo("Feira de adoção de animais na Redenção")
            assertThat(event.image).isEqualTo("http://lproweb.procempa.com.br/pmpa/prefpoa/seda_news/usu_img/Papel%20de%20Parede.png")
            assertThat(event.longitude).isEqualTo(-51.2146267)
            assertThat(event.price).isEqualTo(29.99)
            assertThat(event.date).isEqualTo(1534784400)
        }
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

}