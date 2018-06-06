package nl.oug.hr.hrservice

import com.intuit.karate.FileUtils
import com.intuit.karate.cucumber.CucumberRunner
import com.intuit.karate.netty.FeatureServer
import cucumber.api.CucumberOptions
import junit.framework.Assert.assertEquals
import net.masterthought.cucumber.Configuration
import net.masterthought.cucumber.ReportBuilder
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.junit4.SpringRunner
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Collectors

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberOptions(tags = ["~@ignore", "@wip"])
class HrServiceApplicationTests {

	@LocalServerPort
	var randomPort: Int = 0

	@Before
	fun setUp() {
		val mockFile = FileUtils.getFileRelativeTo(HrServiceApplicationTests::class.java, "common/location-mock.feature")
		FeatureServer.start(mockFile, 23456, false, null)
	}

	@Test
	fun runKarate() {
		System.setProperty("hrPort", randomPort.toString())
		val stats = CucumberRunner.parallel(javaClass, 5)
		assertEquals("There are scenario failures", 0, stats.failCount)


		val jsonPaths = Files.list(Paths.get("target/surefire-reports"))
				.filter { path -> path.toString().endsWith(".json") }
				.map { path -> path.toAbsolutePath().toString() }
				.collect(Collectors.toList())

		val config = Configuration(File("target"), "vooruit")
		val reportBuilder = ReportBuilder(jsonPaths, config)

		reportBuilder.generateReports()
	}

}
