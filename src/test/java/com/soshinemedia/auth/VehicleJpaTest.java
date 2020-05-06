package com.soshinemedia.auth;

import com.soshinemedia.auth.domain.Vehicle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

//import static org.junit.Assert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VehicleJpaTest {

    @Autowired
    private TestEntityManager tem;

    @Test
    public void mapping() {
        Vehicle v = this.tem.persistFlushFind(Vehicle.builder().name("test").build());
        assertThat(v.getName()).isEqualTo("test");
        assertThat(v.getId()).isNotNull();
        assertThat(v.getId()).isGreaterThan(0);

        //assertThat(v.getCreatedDate()).isNotNull();
    }
}
