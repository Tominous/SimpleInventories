package org.screamingsandals.simpleinventories.loaders;

import groovy.lang.Binding;
import groovy.util.GroovyScriptEngine;
import org.screamingsandals.simpleinventories.dependencies.DependencyHelper;
import org.screamingsandals.simpleinventories.groovy.builder.MainGroovyBuilder;
import org.screamingsandals.simpleinventories.inventory.LocalOptions;
import org.screamingsandals.simpleinventories.inventory.Origin;

import java.io.File;
import java.net.URL;

public class GroovyLoader implements Loader {
    @Override
    public Origin readData(File file, String configPath, LocalOptions options) throws Exception {
        DependencyHelper.GROOVY.load();

        Binding binding = new Binding();
        MainGroovyBuilder builder = new MainGroovyBuilder(options);

        binding.setVariable("inventory", builder);
        binding.setVariable("section", configPath);
        GroovyScriptEngine engine = new GroovyScriptEngine(new URL[]{file.getParentFile().toURI().toURL()});

        engine.run(file.getName(), binding);

        Origin origin = new Origin(file, builder.getList());

        origin.setOpenCallbacks(builder.getOpenCallbacks());
        origin.setRenderCallbacks(builder.getRenderCallbacks());
        origin.setPreClickCallbacks(builder.getPreClickCallbacks());
        origin.setBuyCallbacks(builder.getBuyCallbacks());
        origin.setPostClickCallbacks(builder.getPostClickCallbacks());
        origin.setCloseCallbacks(builder.getCloseCallbacks());

        return origin;
    }
}
