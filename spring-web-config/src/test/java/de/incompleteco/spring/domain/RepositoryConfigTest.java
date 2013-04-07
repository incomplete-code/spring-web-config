package de.incompleteco.spring.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.incompleteco.spring.RepositoryConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=RepositoryConfig.class)
public class RepositoryConfigTest {

	@Test
	public void test() {
		//should have started up
	}

}
