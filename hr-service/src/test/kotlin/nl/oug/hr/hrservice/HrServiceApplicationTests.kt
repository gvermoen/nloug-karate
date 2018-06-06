package nl.oug.hr.hrservice

import com.intuit.karate.cucumber.CucumberRunner
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HrServiceApplicationTests {

	@LocalServerPort
	var randomPort: Int = 0

	@Test
	fun runKarate() {
		System.setProperty("hrPort", randomPort.toString())
		val stats = CucumberRunner.parallel(javaClass, 5)
		assertEquals("There are scenario failures", 0, stats.failCount)
	}

}
