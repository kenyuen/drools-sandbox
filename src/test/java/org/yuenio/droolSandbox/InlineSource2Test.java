package org.yuenio.droolSandbox;

import org.droolsassert.DroolsAssert;
import org.droolsassert.DroolsSession;
import org.droolsassert.TestRules;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.Assert.assertEquals;

@DroolsSession(source = "dialect 'mvel'\n"
        + "import java.util.concurrent.atomic.AtomicInteger\n"
        + ""
        + "rule 'atomic int rule'\n"
        + "when\n"
        + "    $atomicInteger: AtomicInteger()\n"
        + "then\n"
        + "    $atomicInteger.incrementAndGet()\n"
        + "end")
public class InlineSource2Test {

    @RegisterExtension
    public DroolsAssert drools = new DroolsAssert();

    @Test
    @TestRules(expected = "atomic int rule")
    public void testInlineSource() {
        drools.insertAndFire(new AtomicInteger(), new AtomicLong());
        assertEquals(1, drools.getObject(AtomicInteger.class).get());
        assertEquals(0, drools.getObject(AtomicLong.class).get());
    }
}