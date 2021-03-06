package cz.cuni.mff.xrg.odcs.procurementExtractor.tests;

import cz.cuni.mff.xrg.odcs.commons.configuration.ConfigException;
import cz.cuni.mff.xrg.odcs.dpu.test.TestEnvironment;
import cz.cuni.mff.xrg.odcs.procurementExtractor.core.CsvProcurementsExtractor;
import cz.cuni.mff.xrg.odcs.procurementExtractor.core.CsvProcurementsExtractorConfig;
import cz.cuni.mff.xrg.odcs.rdf.enums.FileExtractType;
import cz.cuni.mff.xrg.odcs.rdf.interfaces.RDFDataUnit;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class Test {

    @org.junit.Test
    public void test() throws ConfigException, URISyntaxException, MalformedURLException {
        CsvProcurementsExtractor extractor = new CsvProcurementsExtractor();
        CsvProcurementsExtractorConfig config = new CsvProcurementsExtractorConfig();
        config.DebugProcessOnlyNItems = 10;
        URL url = this.getClass().getResource("/procurements_dataset.csv");
        String remoteUrl = "http://localhost:8000/procurements_dataset.csv";

        String fileUrl = url.toURI().toURL().toExternalForm();
        config.Path = fileUrl;
        config.fileExtractType = FileExtractType.PATH_TO_FILE;
        extractor.configureDirectly(config);
        TestEnvironment env = TestEnvironment.create();
        try {
            RDFDataUnit output = env.createRdfOutput("output", false);
            // run the execution
            env.run(extractor);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // release resources
            env.release();
        }
    }
}
