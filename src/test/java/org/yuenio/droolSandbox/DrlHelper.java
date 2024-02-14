package org.yuenio.droolSandbox;

// https://geoffles.github.io/testing/2018/01/18/drools-testing-with-junit.html

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.Message;
import org.kie.api.command.Command;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.ExecutionResults;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.command.CommandFactory;
import org.kie.internal.io.ResourceFactory;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DrlHelper {
    private static final String GET_OBJECTS_KEY = "_getObjects";

    private StatelessKieSession kSession;
    private final KieFileSystem kieFileSystem;
    private final KieServices kieServices;

    public DrlHelper() {
        kieServices = KieServices.Factory.get();
        kieFileSystem = kieServices.newKieFileSystem();
    }

    public DrlHelperOutput execute(Map<String, Object> facts) {
        if (kSession == null){
            throw new IllegalStateException("No Kie Session defined. Did you call 'build()'?");
        }

        List<Command> commands = new ArrayList<>();

        for (Map.Entry<String, Object> entry : facts.entrySet()) {
            Command insertFactCommand = CommandFactory.newInsert(entry.getValue(), entry.getKey());
            commands.add(insertFactCommand);
        }

        commands.add(CommandFactory.newFireAllRules());
        commands.add(CommandFactory.newGetObjects(GET_OBJECTS_KEY));

        ExecutionResults executionResults = kSession.execute(CommandFactory.newBatchExecution(commands));
        Map<String, Object> results = new HashMap<>(executionResults.getIdentifiers().size());

        for (String identifier : executionResults.getIdentifiers()) {
            results.put(identifier, executionResults.getValue(identifier));
        }

        return new DrlHelperOutput( (List<Object>)(executionResults.getValue(GET_OBJECTS_KEY)), results);
    }

    public DrlHelper withDrl(Object context, String ruleName){
        URL resource = context.getClass().getResource(ruleName);
        if (resource == null){
            throw new IllegalArgumentException("ruleName '" + ruleName + "' does not resolve to a resource");
        }

        File ruleFile = new File(resource.getPath());
        kieFileSystem.write(ResourceFactory.newFileResource(ruleFile).setResourceType(ResourceType.DRL));
        return this;
    }

    public DrlHelper build() {
        if (kSession != null){
            throw new IllegalStateException("Kie Session has already been built.");
        }

        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
        kieBuilder.buildAll();

        if (kieBuilder.getResults().hasMessages(Message.Level.ERROR)) {
            throw new RuntimeException("Build Errors:\n" + kieBuilder.getResults().toString());
        }

        KieRepository kieRepository = kieServices.getRepository();
        KieContainer kContainer = kieServices.newKieContainer(kieRepository.getDefaultReleaseId());

        kSession = kContainer.newStatelessKieSession();

        return this;
    }
}
