package cz.cuni.mff.xrg.odcs.procurementExtractor.tests;

import cz.cuni.mff.xrg.odcs.commons.configuration.ConfigException;
import cz.cuni.mff.xrg.odcs.dpu.test.TestEnvironment;
import cz.cuni.mff.xrg.odcs.procurementExtractor.core.CsvProcurementsExtractor;
import cz.cuni.mff.xrg.odcs.procurementExtractor.core.CsvProcurementsExtractorConfig;
import cz.cuni.mff.xrg.odcs.rdf.interfaces.RDFDataUnit;

public class TestIt {

    @org.junit.Test
    public void test() throws ConfigException {
        CsvProcurementsExtractor extractor = new CsvProcurementsExtractor();
        CsvProcurementsExtractorConfig config = new CsvProcurementsExtractorConfig();
        config.DebugProcessOnlyNItems = 10;
        extractor.configureDirectly(config);

        TestEnvironment env = TestEnvironment.create();
        try {
            RDFDataUnit output = env.createRdfOutput("output", false);
            // run the execution
            String input = null;
            env.run(extractor);
        } catch (Exception e) {
            e.printStackTrace(); // To change body of catch statement use File | Settings | File Templates.
        } finally {
            // release resources
            env.release();
        }
    }
}
